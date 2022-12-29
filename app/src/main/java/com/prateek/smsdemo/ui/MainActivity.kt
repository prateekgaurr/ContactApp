package com.prateek.smsdemo.ui

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.prateek.smsdemo.R
import com.prateek.smsdemo.data.ContactDatabase
import com.prateek.smsdemo.databinding.ActivityMainBinding
import com.prateek.smsdemo.models.SaveStatusTypes
import com.prateek.smsdemo.repository.ContactsRepository
import com.prateek.smsdemo.viewmodel.ContactsViewModel
import com.prateek.smsdemo.viewmodel.ContactsViewModelFactory



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

//        val db = Room.databaseBuilder(applicationContext, ContactDatabase::class.java, "contacts_db").build()
        val dao = ContactDatabase.getDatabase(applicationContext).dao()
//        val dao = db.dao()
        val repository = ContactsRepository(dao)
        val viewModel = ViewModelProvider(this, ContactsViewModelFactory(repository))[ContactsViewModel::class.java]

        binding.viewModel = viewModel

        viewModel.emailLiveData.observe(this) {
            if (!Patterns.EMAIL_ADDRESS.matcher(it).matches())
                binding.etEmail.error = "Email Address Not Valid"
        }

        viewModel.mobileNoLiveData.observe(this) {
            if (it.length != 10 || it.length != 13)
                binding.etMobileNumber.error = "Enter 10 or 13 Digits Mobile Number"
        }

        viewModel.savedStatus.observe(this) {
            if (it == SaveStatusTypes.SAVED)
                Snackbar.make(binding.parentLinear, "Saved Successfully", 6000).show()
            else
                Snackbar.make(binding.parentLinear, "Please Check Contents Again", 6000).show()

        }
    }
}