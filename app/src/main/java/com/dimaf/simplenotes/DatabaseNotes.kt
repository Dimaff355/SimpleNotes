package com.dimaf.simplenotes

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database (entities = arrayOf(EntityNote::class), version = 1)
abstract class DatabaseNotes : RoomDatabase() {
    companion object {
        private var db : DatabaseNotes? = null
        private const val DB_NAME = "db_notes"
        private val LOCK = Any()

        fun getInstance (application: Application) : DatabaseNotes {
            db?.let { return it }
            synchronized(LOCK) {
                db?.let { return it }
                val instance = Room.databaseBuilder(
                    application,
                    DatabaseNotes::class.java,
                    DB_NAME
                ).build()
                db = instance
                return instance
            }
        }
    }
    abstract fun daoNotes() : DaoNotes
}