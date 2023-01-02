package com.prateek.smsdemo.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.google.android.material.snackbar.Snackbar
import com.prateek.smsdemo.R
import com.prateek.smsdemo.data.ContactDatabase
import com.prateek.smsdemo.databinding.ActivityMainBinding
import com.prateek.smsdemo.models.Contact
import com.prateek.smsdemo.models.SaveStatusTypes
import com.prateek.smsdemo.repository.ContactsRepository
import com.prateek.smsdemo.viewmodel.ContactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddContactActivity : ParentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.lifecycleOwner = this

        val viewModel : ContactsViewModel by viewModels<ContactsViewModel>()

        binding.viewModel = viewModel


        viewModel.emailLiveData.observe(this) {
            if (!Patterns.EMAIL_ADDRESS.matcher(it).matches())
                binding.etEmail.error = "Email Address Not Valid"
        }

        viewModel.mobileNoLiveData.observe(this) {
            if(it.length != 10)
                binding.etMobileNumber.error = "Please Enter 10 Digit Mobile Number"
        }

        viewModel.savedStatus.observe(this) {
            when (it) {
                SaveStatusTypes.SAVED -> {
                    startActivity(Intent(this@AddContactActivity, ListContactsActivity::class.java)
                        .putExtra("last_saved", true))
                    finish()
                }

                SaveStatusTypes.RECHECK -> Snackbar.make(
                    binding.parentLinear,
                    "Please Check Contents Again",
                    Snackbar.LENGTH_SHORT
                ).show()

                SaveStatusTypes.UPDATED -> Snackbar.make(
                    binding.parentLinear,
                    "Successfully Updated",
                    Snackbar.LENGTH_SHORT
                ).show()

                null -> {}
            }
        }
    }
}