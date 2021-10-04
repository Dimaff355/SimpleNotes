package com.dimaf.simplenotes

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DaoNotes {

    @Query ("SELECT * FROM notes_table")
    suspend fun getAllNotes() : List<EntityNote>

    @Query ("DELETE FROM notes_table WHERE id=:id")
    suspend fun deleteNoteById (id : Int)

    @Query("SELECT * FROM notes_table WHERE id=:id LIMIT 1")
    suspend fun loadNoteById (id: Int): List<EntityNote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNoteToDb (entityNote: EntityNote)
}