package com.prateek.smsdemo.repository

import androidx.lifecycle.LiveData
import com.prateek.smsdemo.data.ContactsDao
import com.prateek.smsdemo.models.Contact
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ContactsRepository @Inject constructor(private val dao : ContactsDao) {

    fun getAllContacts() : Flow<List<Contact>> = dao.getAllContacts()

    suspend fun insertContact(contact: Contact) = dao.insert(contact)

    suspend fun updateContact(contact: Contact) = dao.update(contact)

    suspend fun deleteContact(contact: Contact) = dao.delete(contact)

}