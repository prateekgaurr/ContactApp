package com.prateek.smsdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.prateek.smsdemo.databinding.SingleContactBinding
import com.prateek.smsdemo.interfaces.OnItemClickListener
import com.prateek.smsdemo.models.Contact

class ContactsAdapter constructor(
    val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: SingleContactBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(contact: Contact){
            binding.contact = contact
            binding.root.setOnClickListener { onItemClickListener.onItemClicked(differ.currentList[adapterPosition]) }
            with(binding) {
                root.setOnLongClickListener {
                    onItemClickListener.onItemLongClicked(differ.currentList[adapterPosition])
                    true
                }
            }
        }
    }

    private lateinit var binding: SingleContactBinding
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = SingleContactBinding.inflate(LayoutInflater.from(parent.context), parent, false )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact = differ.currentList[position]
        holder.bind(contact)
    }

    override fun getItemCount(): Int = differ.currentList.size

    private val differCallBack = object : DiffUtil.ItemCallback<Contact>(){
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean = (oldItem.id == newItem.id)
        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean = (oldItem == newItem)
    }


    val differ = AsyncListDiffer(this, differCallBack)


}