package com.prateek.smsdemo.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "contacts_table")
data class Contact(

    @PrimaryKey(autoGenerate = true)
    val id : Long,

    @ColumnInfo(name = "full_name")
    val fullName : String,

    @ColumnInfo(name = "address")
    val address : String,

    @ColumnInfo(name = "date_created")
    val dateCreated : Date,

    @ColumnInfo(name = "mobile_number")
    val mobileNumber : String

)
