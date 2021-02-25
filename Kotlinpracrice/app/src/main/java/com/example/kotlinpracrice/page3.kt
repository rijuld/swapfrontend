package com.example.kotlinpracrice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.kotlinpracrice.databinding.FragmentPage3Binding
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel
import com.google.android.gms.auth.api.signin.GoogleSignIn

class page3 : Fragment() {
    private lateinit var viewModel: page3viewmodel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentPage3Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page3, container, false)
        Log.i("page3Fragment","Called page3 ViewModel.of")


        val repository=Repository()
        val viewModelFactory=page3viewmodelfactory(repository)
        Log.d("Response","problem not here")
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)

        viewModel.customPost("errijuldahiya@gmail.com","id","desc")
        val hashMap:HashMap<String,Int> = HashMap<String,Int>()
        hashMap["HSS F325"] = 12
        hashMap["HSS F237"] = 5
        hashMap["HSS F244"] = 6
        hashMap["HSS F255"] = 7
        hashMap["HSS F317"] = 10
        hashMap["HSS F248"] = 8
        hashMap["HSS F235"] = 3
        hashMap["HSS F322"] = 11
        hashMap["HSS F236"] = 4

        viewModel.myCustomPosts.observe(viewLifecycleOwner, Observer { response->
            if(response.isSuccessful){
                Log.i("response",response.body().toString())
               response.body()?.forEach{

                    Log.i("response",it.id.toString())
                    Log.i("response",it.coursegiv.toString())
                    Log.i("response",it.coursereq.toString())
                   Log.i("response",it.user.toString())
                }

            }
            else{
                Log.d("Response",response.errorBody().toString())
            }


        })
        //parameters are the owner and the second view model factory
        binding.buttonpage3.setOnClickListener { view :View->
            val have=binding.havetype.text.toString().toUpperCase().trim()
            val want=binding.wanttype.text.toString().toUpperCase().trim()
            val account = GoogleSignIn.getLastSignedInAccount(requireActivity())
            val userid= account?.email.toString()
            val mypost= Post(0, have, want,userid)
            Log.i("Responsehello",mypost.toString())
            viewModel.pushPost(mypost)
            viewModel.postPosts.observe(viewLifecycleOwner, Observer { response->
                if(response.isSuccessful){
                    Log.i("Responsefinal",response.body().toString())
                }
                else{
                    //Log.i("Responsefinal",response.errorBody().toString())
                    //view.findNavController().navigate(page3Directions.actionPage32ToPage42())
                }
                view.findNavController().navigate(page3Directions.actionPage32ToPage42())

            })

        }
        return binding.root
    }


}