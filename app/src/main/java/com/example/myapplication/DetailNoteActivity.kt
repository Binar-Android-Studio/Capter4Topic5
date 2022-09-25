package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityDetailNoteBinding
import com.example.myapplication.room.DataNote

class DetailNoteActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_note)

        binding = ActivityDetailNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var getDetail = intent.getSerializableExtra("detail") as DataNote

        binding.detailTitle.text = getDetail.title
        binding.detailNote.text = getDetail.content

    }
}