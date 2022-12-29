package com.prateek.smsdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.prateek.smsdemo.repository.ContactsRepository

class ContactsViewModelFactory(private val repository: ContactsRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ContactsViewModel(repository) as T
    }


}