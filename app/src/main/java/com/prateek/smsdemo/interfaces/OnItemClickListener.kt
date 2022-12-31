package com.prateek.smsdemo.interfaces

import com.prateek.smsdemo.models.Contact

interface OnItemClickListener {

    fun onItemClicked(contact : Contact)

    fun onItemLongClicked(contact: Contact)

}