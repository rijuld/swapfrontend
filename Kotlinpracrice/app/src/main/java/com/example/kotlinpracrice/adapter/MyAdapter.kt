package com.example.kotlinpracrice.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinpracrice.R
import com.example.kotlinpracrice.model.Post
import kotlinx.android.synthetic.main.row_layout.view.*

class MyAdapter(private val listener: MyAdapter.OnItemClickListener) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private  var myList= emptyList<Post>()
    var myListsize= myList.size
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        val deletea: ImageView=itemView.findViewById(R.id.delete)
        val cid: TextView=itemView.findViewById(R.id.userId_txt)
        val coursereq: TextView=itemView.findViewById(R.id.id_txt)
        val coursegiv: TextView=itemView.findViewById(R.id.title_txt)
        val user: TextView=itemView.findViewById(R.id.body_txt)
        init {
            deletea.setOnClickListener(this)//onclick listener interface
        }

        override fun onClick(v: View?) {
            val position :Int =adapterPosition
            if(position!=RecyclerView  .NO_POSITION)
            listener.onItemClick(cid,position)
            itemView?.visibility=View.GONE


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.userId_txt.text=myList[position].id.toString()
        holder.itemView.id_txt.text=myList[position].coursereq.toString()
        holder.itemView.title_txt.text=myList[position].coursegiv.toString()
        holder.itemView.body_txt.text=myList[position].user.toString()
    }

    override fun getItemCount(): Int {
        return myList.size
    }
    fun setData(newList: List<Post>){
        myList=newList
        notifyDataSetChanged()
    }
    interface OnItemClickListener{

        fun onItemClick(position: TextView, position1: Int)
    }
}
