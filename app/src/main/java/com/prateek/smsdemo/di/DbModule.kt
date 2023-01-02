package com.prateek.smsdemo.di

import android.content.Context
import androidx.room.Room
import com.prateek.smsdemo.data.ContactDatabase
import com.prateek.smsdemo.data.ContactsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    @Singleton
    fun getDatabase(@ApplicationContext context: Context): ContactDatabase = Room.databaseBuilder(
            context,
            ContactDatabase::class.java,
            "contacts_db"
        ).build()

    @Provides
    @Singleton
    fun getContactsDao(database: ContactDatabase) : ContactsDao = database.dao()

}