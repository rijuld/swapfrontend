package com.example.kotlinpracrice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlinpracrice.adapter.MyAdapter
import com.example.kotlinpracrice.databinding.FragmentPage4Binding
import com.example.kotlinpracrice.model.Post
import com.example.kotlinpracrice.repository.Repository
import com.example.kotlinpracrice.viewmodelfactory.page3viewmodelfactory
import com.example.kotlinpracrice.viewmodels.page3viewmodel


class page4 : Fragment(),MyAdapter.OnItemClickListener {
    private lateinit var viewModel: page3viewmodel
    private  val myAdapter = MyAdapter(this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
       val binding: FragmentPage4Binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page4, container, false)
        Log.i("page4Fragment","Called page4 ViewModel.of")
        val repository=Repository()
        val viewModelFactory=page3viewmodelfactory(repository)
        viewModel= ViewModelProviders.of(this,viewModelFactory).get(page3viewmodel::class.java)

        viewModel.customPost(2,"id","desc")
        viewModel.myCustomPosts.observe(this, Observer { response->
            if(response.isSuccessful){
                Log.d("Response",response.body().toString())
                response.body()?.let { myAdapter.setData(it) }
            }
            else{
                Log.i("Response",response.errorBody().toString())
            }


        })
        setupRecyclerview(binding)
        binding.buttonpage4.setOnClickListener { view :View->
            view.findNavController().navigate(page4Directions.actionPage42ToPage32())
        }
        Log.d("Response","problem not here")

            binding.button41.setOnClickListener { view :View->
            view.findNavController().navigate(page4Directions.actionPage42ToTitlefragment())
        }
        return binding.root
    }

    private fun setupRecyclerview(binding: FragmentPage4Binding) {
         binding.rrr.adapter=myAdapter
         binding.rrr.layoutManager=LinearLayoutManager(requireActivity())
    }

    override fun onItemClick(position: TextView, position1: Int) {
        Toast.makeText(requireActivity(), position.text.toString(), Toast.LENGTH_SHORT).show()
        myAdapter.notifyItemChanged(position1)
        val t=position.text.toString().trim().toInt()
        viewModel.deletePost(t)
        viewModel.deleteResponse.observe(this, Observer { response->

            if(response.isSuccessful){
                Log.i("Response",response.body().toString())
            }
            else{
                Log.i("Response",response.errorBody().toString())
            }


        })
    }

}