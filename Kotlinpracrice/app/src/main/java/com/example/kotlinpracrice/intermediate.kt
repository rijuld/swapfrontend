package com.example.kotlinpracrice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.kotlinpracrice.databinding.FragmentIntermediateBinding
import com.example.kotlinpracrice.databinding.FragmentPage3Binding
import com.example.kotlinpracrice.model.Usersend
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessaging

private var navController: NavController? = null

class intermediate : Fragment() {
    private lateinit var viewModel: page3viewmodel
    private lateinit var auth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentIntermediateBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_intermediate, container, false)
        val repository= Repository()
        val viewModelFactory= page3viewmodelfactory(repository)
        Log.d("Response","problem not here")
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)
        // Inflate the layout for this fragment
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
            auth = Firebase.auth
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
            })

        viewModel.use.observe(viewLifecycleOwner, Observer { response->
            if(response.isSuccessful){
                Log.i("Response",response.body().toString())
                navController = view?.let { Navigation.findNavController(it) }
                navController!!.navigate(R.id.action_intermediate_to_titlefragment)
            }
            else{
                Log.i("Responseerror",response.errorBody().toString())
                binding.textView3.text="GO BACK YOU TRAITOR!(u not from bits)"
            }
        })
        return binding.root

    }

    companion object {

    }
}