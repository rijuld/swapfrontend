package com.example.kotlinpracrice

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlinpracrice.databinding.FragmentTitlefragmentBinding
import com.example.kotlinpracrice.viewmodels.titleviewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth


class titlefragment : Fragment() {
    private lateinit var viewModel: titleviewmodel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding:FragmentTitlefragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_titlefragment, container, false)
        Log.i("TitleFragment","Called Title ViewModel.of")
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
        val idToken= account?.idToken
        val phonenumber= auth.currentUser?.phoneNumber
        Log.i("TitleFragment",idToken.toString())
        viewModel=ViewModelProviders.of(this).get(titleviewmodel::class.java)
        binding.button3.setOnClickListener { view :View->

            view.findNavController().navigate(titlefragmentDirections.actionTitlefragmentToPage32())
        }

        setHasOptionsMenu(true)
          return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.overflow_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!,view!!.findNavController()) ||super.onOptionsItemSelected(item)
    }
    companion object {

    }
}