package com.dimaf.simplenotes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "notes_table")
data class EntityNote(
    @PrimaryKey (autoGenerate = true)
    val id : Int = 0,

    val title : String?,
    val body : String?
)
