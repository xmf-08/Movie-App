package com.example.movieapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.movieapp.adapters.MyMovieAdapter
import com.example.movieapp.adapters.RvClick
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.db.MySharedPreference
import com.example.movieapp.models.MyMovie

class MainActivity : AppCompatActivity(), RvClick {
    lateinit var binding: ActivityMainBinding
    lateinit var myMovieAdapter: MyMovieAdapter
    private lateinit var list: ArrayList<MyMovie>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        MySharedPreference.init(this)
        list = MySharedPreference.catchList
        myMovieAdapter = MyMovieAdapter(this, list, this)
        binding.rvMovies.adapter = myMovieAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        list.clear()
        list.addAll(MySharedPreference.catchList)
        myMovieAdapter.notifyDataSetChanged()
    }

    override fun deleteMovie(movie: MyMovie, position: Int) {
        list.removeAt(position)
        MySharedPreference.catchList = list
        myMovieAdapter.notifyItemRemoved(position)
        Toast.makeText(this, "${movie.name} o'chirildi", Toast.LENGTH_SHORT).show()
    }

    override fun editMovie(movie: MyMovie, position: Int) {
        MySharedPreference.catchList = list
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("name",list[position].name)
        intent.putExtra("author",list[position].author)
        intent.putExtra("about",list[position].about)
        intent.putExtra("date",list[position].date)
        intent.putExtra("position",position)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, AddActivity::class.java)
        intent.putExtra("key", 0)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }
}
