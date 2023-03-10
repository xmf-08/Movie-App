package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.movieapp.databinding.ActivityAddBinding
import com.example.movieapp.db.MySharedPreference
import com.example.movieapp.models.MyMovie

class AddActivity : AppCompatActivity() {
    lateinit var binding: ActivityAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        MySharedPreference.init(this)

        if (intent.getIntExtra("key", -1) == 0){
            addTodo()
        }else{
            edit()
        }

        }
    fun addTodo(){
        binding.apply {
            btnSave.setOnClickListener {
                val myMovie = MyMovie(
                    edtName.text.toString().trim(),
                    edtAuthor.text.toString().trim(),
                    edtAbout.text.toString().trim(),
                    edtDate.text.toString().trim(),
                )
                val list = MySharedPreference.catchList
                list.add(myMovie)
                Toast.makeText(this@AddActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                MySharedPreference.catchList = list
                finish()
            }
        }
    }

    fun edit(){
        binding.edtName.setText(intent.getStringExtra("name"))
        binding.edtAuthor.setText(intent.getStringExtra("author"))
        binding.edtAbout.setText(intent.getStringExtra("about"))
        binding.edtDate.setText(intent.getStringExtra("date"))
        val position = intent.getIntExtra("position",0)
        binding.apply {
            btnSave.setOnClickListener {
                val myMovie = MyMovie(
                    edtName.text.toString().trim(),
                    edtAuthor.text.toString().trim(),
                    edtAbout.text.toString().trim(),
                    edtDate.text.toString().trim(),
                )
                val list = MySharedPreference.catchList
                list[position] = myMovie
                Toast.makeText(this@AddActivity, "Saqlandi", Toast.LENGTH_SHORT).show()
                MySharedPreference.catchList = list
                finish()
            }
        }
    }
}