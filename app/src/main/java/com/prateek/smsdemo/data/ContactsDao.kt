package com.prateek.smsdemo.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.prateek.smsdemo.models.Contact


@Dao
interface ContactsDao {

    @Insert
    suspend fun insert(contact : Contact)

    @Update
    suspend fun update(contact: Contact)

    @Update
    suspend fun delete(contact: Contact)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts() : LiveData<List<Contact>>

}