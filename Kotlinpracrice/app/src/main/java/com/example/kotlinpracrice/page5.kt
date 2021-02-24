package com.example.kotlinpracrice

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.kotlinpracrice.databinding.FragmentPage5Binding
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import okio.IOException


class page5 : Fragment() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var binding: FragmentPage5Binding
    private var navController: NavController? = null
    private lateinit var viewModel: page3viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_page5, container, false)
        Log.i("page5Fragment", "Called page5 ViewModel.of")
        val repository= Repository()
        val viewModelFactory= page3viewmodelfactory(repository)
        viewModel= ViewModelProviders.of(this, viewModelFactory).get(page3viewmodel::class.java)

        binding.buttonpage5.setOnClickListener { view: View->
            signIn();
        }
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("75980394763-bj0hr3nhh40qk6qaoeh6ddi4p0svhfa0.apps.googleusercontent.com")
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        return binding.root

    }
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
                signInIntent, RC_SIGN_IN
        )
    }

    override fun onStart() {
        super.onStart()
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
        if (account != null) {
            updateUI(account)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task =
                    GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                    ApiException::class.java
            )

            // Signed in successfully
            val googleId = account?.id ?: ""
            Log.i("Google ID",googleId)

            val googleFirstName = account?.givenName ?: ""
            Log.i("Google First Name", googleFirstName)

            val googleLastName = account?.familyName ?: ""
            Log.i("Google Last Name", googleLastName)

            val googleEmail = account?.email ?: ""
            Log.i("Google Email", googleEmail)

            val googleProfilePicURL = account?.photoUrl.toString()
            Log.i("Google Profile Pic URL", googleProfilePicURL)

            val googleIdToken = account?.idToken ?: ""
            Log.i("Google ID Token", googleIdToken)
            navController = view?.let { Navigation.findNavController(it) }
            navController!!.navigate(R.id.action_page53_to_phonenumber)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                    "failed code=", e.statusCode.toString()
            )
        }
    }
    private fun signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(requireActivity()) {
                    // Update your UI here
                }
    }
    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(requireActivity()) {
                    // Update your UI here
                }
    }
    private fun updateUI(account: GoogleSignInAccount?)
    {
        val idToken = account!!.idToken
        val acct = GoogleSignIn.getLastSignedInAccount(activity)
        if (acct != null) {
            Log.d("Response", acct.displayName.toString())
            val personName = acct.displayName
            val personGivenName = acct.givenName
            val personFamilyName = acct.familyName
            val personEmail = acct.email
            val personId = acct.id
            val personPhoto: Uri? = acct.photoUrl
        }
        view?.findNavController()?.navigate(page5Directions.actionPage53ToPhonenumber())
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }



}
//You can also get the user's email address with getEmail, the user's Google ID (for client-side use) with getId, and an ID token for the user with getIdToken. If you need to pass the currently signed-in user to a backend server, send the ID token to your backend server and validate the token on the server.
