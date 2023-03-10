package com.example.movieapp.adapters
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemBinding
import com.example.movieapp.models.MyMovie

class MyMovieAdapter(val context: Context, val list:List<MyMovie>, val rvClick: RvClick)
    :RecyclerView.Adapter<MyMovieAdapter.Vh>() {
    inner class Vh(val itemRvBinding:ItemBinding):RecyclerView.ViewHolder(itemRvBinding.root){
        fun onBind(myMovie: MyMovie, position: Int){

            val animation = AnimationUtils.loadAnimation(context,R.anim.rv_anim)
            itemRvBinding.root.startAnimation(animation)

            itemRvBinding.tvName.text = myMovie.name
            itemRvBinding.tvAuthor.text = myMovie.author
            itemRvBinding.tvDate.text = myMovie.date

            itemRvBinding.btnDelete.setOnClickListener {
                rvClick.deleteMovie(myMovie, position)
            }
            itemRvBinding.btnEdit.setOnClickListener {
                rvClick.editMovie(myMovie, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
interface RvClick{
    fun deleteMovie(movie: MyMovie,position: Int)
    fun editMovie(movie: MyMovie,position: Int)
}