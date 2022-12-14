package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.room.DataNote
import com.example.myapplication.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var NoteDB : NoteDatabase? = null
    lateinit var adapterNote: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        NoteDB = NoteDatabase.getInstance(this)
        binding.btnAddNote.setOnClickListener {
            startActivity(Intent(this, AddNoteActivity::class.java))
        }

        getAllNote()
    }

    fun getAllNote(){
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            runOnUiThread {
                adapterNote =NoteAdapter(data!!)
                binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                binding.rvNote.adapter = adapterNote
            }

        }
    }

    override fun onStart(){
        super.onStart()
        GlobalScope.launch {
            var data = NoteDB?.noteDao()?.getDataNote()
            NoteDB?.noteDao()?.getDataNote()
            runOnUiThread {
                data.let {
                    adapterNote =NoteAdapter(data!!)
                    binding.rvNote.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
                    binding.rvNote.adapter = adapterNote
                }
            }
        }
    }
}