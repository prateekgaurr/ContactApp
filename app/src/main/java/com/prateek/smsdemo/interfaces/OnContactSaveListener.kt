package com.prateek.smsdemo.interfaces

import com.prateek.smsdemo.models.Contact

interface OnContactSaveListener {
    fun onContactSave (contact: Contact)
}