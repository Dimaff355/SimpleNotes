package com.dimaf.simplenotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel (application: Application) : AndroidViewModel(application) {
    private val db = DatabaseNotes.getInstance(application)

    private val _livedataNotes = MutableLiveData<List<EntityNote>>()
    val livedataNotes : LiveData<List<EntityNote>> = _livedataNotes


    fun loadNotesList() {
        CoroutineScope((Dispatchers.IO)).launch {
            _livedataNotes.postValue(db.daoNotes().getAllNotes())
        }
    }

    fun removeNote(noteId : Int) {
        CoroutineScope((Dispatchers.IO)).launch {
            db.daoNotes().deleteNoteById(noteId)
            loadNotesList()
        }
    }

    override fun onCleared() {
        super.onCleared()

    }
}