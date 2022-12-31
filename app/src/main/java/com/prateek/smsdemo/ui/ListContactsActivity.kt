package com.prateek.smsdemo.ui
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.prateek.smsdemo.R
import com.prateek.smsdemo.data.ContactDatabase
import com.prateek.smsdemo.databinding.ActivityListContactsBinding
import com.prateek.smsdemo.interfaces.OnItemClickListener
import com.prateek.smsdemo.models.Contact
import com.prateek.smsdemo.repository.ContactsRepository
import com.prateek.smsdemo.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class ListContactsActivity : ParentActivity(), OnItemClickListener {

    private lateinit var binding : ActivityListContactsBinding
    private lateinit var adapter : ContactsAdapter
    val viewModel : ContactsViewModel by viewModels<ContactsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_contacts)

        binding.viewModel  = viewModel
        binding.lifecycleOwner = this

        if(intent.getBooleanExtra("last_saved", false))
            Snackbar.make(binding.root, "Successfully Saved", Snackbar.LENGTH_SHORT).show()

        binding.newContactFab.setOnClickListener {
            startActivity(Intent(this@ListContactsActivity, AddContactActivity::class.java))
        }

        binding.mainRecycler.layoutManager = LinearLayoutManager(this)
        binding.mainRecycler.setHasFixedSize(true)
        adapter = ContactsAdapter(this)
        binding.adapter = adapter

        viewModel.contactsLiveData.observe(this, ({
            adapter.differ.submitList(it)
        }))

    }

    override fun onItemClicked(contact: Contact) {
       Toast.makeText(this, "Clicked ${contact.fullName}", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClicked(contact: Contact) {
        viewModel.deleteContact(contact)
        Snackbar.make(binding.root, "${contact.fullName} Deleted", Snackbar.LENGTH_SHORT).setAction("Undo", View.OnClickListener {
            viewModel.insertContact(contact)
        }).show()
    }


}