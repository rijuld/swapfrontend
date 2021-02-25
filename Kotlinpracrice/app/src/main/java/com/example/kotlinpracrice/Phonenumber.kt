package com.example.kotlinpracrice

import android.content.ContentValues.TAG
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.kotlinpracrice.databinding.FragmentPhonenumberBinding
import com.example.kotlinpracrice.model.Usersend
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.Phonenumberviewmodel
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging
import java.util.concurrent.TimeUnit
private var navController: NavController? = null

class Phonenumber : Fragment() {
    private lateinit var viewModel: page3viewmodel
    private lateinit var binding:FragmentPhonenumberBinding
    private lateinit var auth: FirebaseAuth
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_phonenumber, container, false)
        Log.i("PhonenumberFragment", "Called Phonenumber ViewModel.of")
        val repository= Repository()
        val viewModelFactory= page3viewmodelfactory(repository)
        Log.d("Response","problem not here")
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)
        binding.buttonphone.setOnClickListener { view: View->
            val code = binding.verificationno.text.toString()
            if (TextUtils.isEmpty(code)) {
                binding.verificationno.error = "Cannot be empty."
            }
            verifyPhoneNumberWithCode(storedVerificationId, code)
        }
        binding.getcode.setOnClickListener { view: View->
            if (!validatePhoneNumber()) {
            }
            startPhoneNumberVerification(binding.editTextPhone.text.toString())
        }
        auth = Firebase.auth

        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {

                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                updateUI(STATE_VERIFY_SUCCESS, credential)
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false

                if (e is FirebaseAuthInvalidCredentialsException) {
                    binding.editTextPhone.error = "Invalid phone number."
                } else if (e is FirebaseTooManyRequestsException) {
                    Log.i("message","QUOTA EXCEEDED")
                }
                updateUI(STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {

                storedVerificationId = verificationId
                resendToken = token
                updateUI(STATE_CODE_SENT)
            }
        }


        return binding.root
    }
    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
        if (verificationInProgress && validatePhoneNumber()) {
            startPhoneNumberVerification(binding.editTextPhone.text.toString())
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_VERIFY_IN_PROGRESS, verificationInProgress)
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        verificationInProgress = true
    }

    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }
    private fun resendVerificationCode(
        phoneNumber: String,
        token: PhoneAuthProvider.ForceResendingToken?
    ) {
        val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(callbacks)
        if (token != null) {
            optionsBuilder.setForceResendingToken(token)
        }
        PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    updateUI(STATE_SIGNIN_SUCCESS, user)
                } else {
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        binding.editTextPhone.error = "Invalid code."
                    }
                    updateUI(STATE_SIGNIN_FAILED)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            updateUI(STATE_SIGNIN_SUCCESS, user)
        } else {
            updateUI(STATE_INITIALIZED)
        }
    }

    private fun updateUI(uiState: Int, cred: PhoneAuthCredential) {
        updateUI(uiState, null, cred)
    }

    private fun updateUI(
        uiState: Int,
        user: FirebaseUser? = auth.currentUser,
        cred: PhoneAuthCredential? = null
    ) {
        when (uiState) {
            STATE_INITIALIZED -> {
                binding.getcode.visibility=View.VISIBLE
                binding.editTextPhone.visibility=View.VISIBLE
                binding.buttonphone.visibility=View.INVISIBLE
                binding.buttonphone.visibility=View.INVISIBLE
                binding.verificationno.visibility=View.INVISIBLE
                binding.detail.text = null
            }
            STATE_CODE_SENT -> {
                binding.buttonphone.visibility=View.VISIBLE
                binding.buttonphone.visibility=View.VISIBLE
                binding.editTextPhone.visibility=View.VISIBLE
                binding.verificationno.visibility=View.VISIBLE
                binding.getcode.visibility=View.INVISIBLE
                Log.i("response","status_code_sent")

            }
            STATE_VERIFY_FAILED -> {
                    binding.getcode.visibility=View.VISIBLE
                    binding.buttonphone.visibility=View.VISIBLE
                    binding.buttonphone.visibility=View.VISIBLE
                    binding.editTextPhone.visibility=View.VISIBLE
                    binding.verificationno.visibility=View.VISIBLE
                Log.i("response","status_verification_failed")
            }
            STATE_VERIFY_SUCCESS -> {

                    binding.getcode.visibility=View.INVISIBLE
                    binding.buttonphone.visibility=View.INVISIBLE
                    binding.buttonphone.visibility=View.INVISIBLE
                    binding.editTextPhone.visibility=View.INVISIBLE
                    binding.verificationno.visibility=View.INVISIBLE
                Log.i("response","status_verification_succeeded")
                if (cred != null) {
                    if (cred.smsCode != null) {
                        binding.verificationno.setText(cred.smsCode)
                    } else {
                        Log.i("response","instant_validation")
                    }
                }
            }
            STATE_SIGNIN_FAILED ->
                Log.i("response","status_sign_in_failed")

            STATE_SIGNIN_SUCCESS -> {
            }
        }

        if (user == null) {
        } else {
            /*val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
            val idToken= account?.idToken
            val phonenumber= auth.currentUser?.phoneNumber
            FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("Token", "Fetching FCM registration token failed", task.exception)
                    return@OnCompleteListener
                }
                val token = task.result
                Log.d("TAG", token.toString())
                Toast.makeText(requireContext(),token.toString(), Toast.LENGTH_SHORT).show()
                val kk= Usersend(idToken.toString(),phonenumber.toString(),token.toString())
                viewModel.getUser(kk)
            })*/

            /*viewModel.use.observe(viewLifecycleOwner, Observer { response->
                if(response.isSuccessful){
                    Log.i("Response",response.body().toString())
                    navController = view?.let { Navigation.findNavController(it) }
                    navController!!.navigate(R.id.action_phonenumber_to_intermediate)
                }
                else{
                    Log.i("Responseerror",response.errorBody().toString())
                }
            })*/
            navController = view?.let { Navigation.findNavController(it) }
            navController!!.navigate(R.id.action_phonenumber_to_intermediate)
            val phoneNumber = binding.editTextPhone.text.toString()
            //add retrofit here

        }
    }

    private fun validatePhoneNumber(): Boolean {
        val phoneNumber = binding.editTextPhone.text.toString()
        if (TextUtils.isEmpty(phoneNumber)) {
            binding.editTextPhone.error = "Invalid phone number."
            return false
        }
        return true
    }

    private fun enableViews(vararg views: View) {
        for (v in views) {
            v.isEnabled = true
        }
    }

    private fun disableViews(vararg views: View) {
        for (v in views) {
            v.isEnabled = false
        }
    }

    companion object {
        private const val TAG = "PhoneAuthActivity"
        private const val KEY_VERIFY_IN_PROGRESS = "key_verify_in_progress"
        private const val STATE_INITIALIZED = 1
        private const val STATE_VERIFY_FAILED = 3
        private const val STATE_VERIFY_SUCCESS = 4
        private const val STATE_CODE_SENT = 2
        private const val STATE_SIGNIN_FAILED = 5
        private const val STATE_SIGNIN_SUCCESS = 6
    }


}

