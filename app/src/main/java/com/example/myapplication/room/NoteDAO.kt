package com.example.myapplication.room

import androidx.room.*

@Dao
interface NoteDAO {
    @Insert
    fun insertNote(note: DataNote)

    @Query(" SELECT * FROM datanote ORDER BY id DESC")
    fun getDataNote() : List<DataNote>

    @Delete
    fun deleteNote(note: DataNote) : Int

    @Update
    fun updateNote(note: DataNote)
}