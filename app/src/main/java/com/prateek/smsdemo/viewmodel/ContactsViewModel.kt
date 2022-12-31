package com.prateek.smsdemo.viewmodel

import android.text.TextUtils
import android.util.Log
import android.util.Patterns
import android.view.Display
import android.widget.MultiAutoCompleteTextView
import android.widget.Toast
import androidx.lifecycle.*
import com.google.android.material.snackbar.Snackbar
import com.prateek.smsdemo.interfaces.OnContactSaveListener
import com.prateek.smsdemo.models.Contact
import com.prateek.smsdemo.models.SaveStatusTypes
import com.prateek.smsdemo.repository.ContactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class ContactsViewModel(private val repository: ContactsRepository) : ViewModel() {

    val contactsLiveData : LiveData<List<Contact>>
        get() = repository.getAllContacts()

    val nameLiveData : MutableLiveData<String> = MutableLiveData()
    val addressLiveData : MutableLiveData<String> = MutableLiveData()
    val mobileNoLiveData : MutableLiveData<String> = MutableLiveData()
    val emailLiveData : MutableLiveData<String> = MutableLiveData()
    val savedStatus : MutableLiveData<SaveStatusTypes> = MutableLiveData()

    fun verifyAndAdd(){
        if(
            TextUtils.isEmpty(nameLiveData.value) ||
            TextUtils.isEmpty(addressLiveData.value) ||
            !(Patterns.EMAIL_ADDRESS.matcher(emailLiveData.value.toString()).matches()) ||
            (mobileNoLiveData.value?.length != 10)
        ){
            savedStatus.value = SaveStatusTypes.RECHECK
        }
        else{
            saveOrUpdateContact()
        }
    }

    private fun saveOrUpdateContact() {
            insertContact(Contact(0, nameLiveData.value!!, addressLiveData.value!!, Date(), mobileNoLiveData.value!! , emailLiveData.value!!))
            savedStatus.value = SaveStatusTypes.SAVED
    }


    fun insertContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertContact(contact)
        }
    }

    fun updateContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateContact(contact)
        }
    }

    fun deleteContact(contact: Contact){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteContact(contact)
        }
    }

}