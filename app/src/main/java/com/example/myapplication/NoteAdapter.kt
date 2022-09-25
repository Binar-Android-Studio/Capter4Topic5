package com.example.myapplication

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemNoteBinding
import com.example.myapplication.room.DataNote
import com.example.myapplication.room.NoteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class NoteAdapter(var listNote : List<DataNote>): RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var DBNote : NoteDatabase? = null
    class ViewHolder(var binding: ItemNoteBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ItemNoteBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.id.text = listNote[position].id.toString()
        holder.binding.judul.text = listNote[position].title
        holder.binding.delete.setOnClickListener {
            DBNote = NoteDatabase.getInstance(it.context)
            GlobalScope.async {
                val del = DBNote?.noteDao()?.deleteNote(listNote[position])
                (holder.itemView.context as MainActivity).getAllNote()
                (holder.itemView.context as MainActivity).runOnUiThread {
                    if ( del != 0){
                        Toast.makeText(it.context, "Data ${listNote[position].id} Success Deleted", Toast.LENGTH_SHORT).show()
//                             .   ga pake ini data ga ke update
                        (holder.itemView.context as MainActivity).getAllNote()
                    }else{
                        Toast.makeText(it.context, "Data ${listNote[position].id} Failed to Delete ", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        holder.binding.edit.setOnClickListener {
            var move = Intent(it.context, EditActivity::class.java)
            move.putExtra("dataedit", listNote[position])
            it.context.startActivity(move)
        }

        holder.binding.klik.setOnClickListener{
            var detail = Intent(it.context, DetailNoteActivity::class.java)
            detail.putExtra("detail", listNote[position])
            it.context.startActivity(detail)
        }

    }

    override fun getItemCount(): Int {
        return listNote.size
    }

    fun setNoteData(listNote: ArrayList<DataNote>)
    {
        this.listNote=listNote
    }
}