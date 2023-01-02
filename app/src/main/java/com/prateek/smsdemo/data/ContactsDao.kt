package com.prateek.smsdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.prateek.smsdemo.models.Contact
import kotlinx.coroutines.flow.Flow


@Dao
interface ContactsDao {

    @Insert
    suspend fun insert(contact : Contact)

    @Update
    suspend fun update(contact: Contact)

    @Delete
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts() : Flow<List<Contact>>

}