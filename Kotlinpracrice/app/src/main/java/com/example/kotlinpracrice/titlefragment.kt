package com.example.kotlinpracrice

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.kotlinpracrice.databinding.FragmentTitlefragmentBinding
import com.example.kotlinpracrice.messaging.MyMessagingService
import com.example.kotlinpracrice.model.Usersend
import com.example.kotlinpracrice.model.idt
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class titlefragment : Fragment() {
    private lateinit var viewModel: page3viewmodel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding:FragmentTitlefragmentBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_titlefragment, container, false)
        Log.i("TitleFragment","Called Title ViewModel.of")
        val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
        auth=Firebase.auth
        if (account != null) {
            binding.page1.text="Hi "+account.displayName
        }
        val repository= Repository()
        val viewModelFactory= page3viewmodelfactory(repository)
        Log.d("Response","problem not here")
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)
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
        return NavigationUI.onNavDestinationSelected(item!!,requireView().findNavController()) ||super.onOptionsItemSelected(item)
    }
    companion object {

    }
}