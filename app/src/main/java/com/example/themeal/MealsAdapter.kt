package com.example.themeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.util.Log

import java.io.IOException

import android.graphics.BitmapFactory

import java.io.BufferedInputStream

import java.net.URL

import android.graphics.Bitmap
import android.os.AsyncTask

import com.squareup.picasso.Picasso


class MealsAdapter: RecyclerView.Adapter<MealsAdapter.MealsViewHolder>() {
    private val dataSource = mutableListOf<DataClass>()
    private var design = 1

    override fun getItemViewType(position: Int): Int{
        return R.layout.ingredient_elem;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealsViewHolder {
        val elementView = LayoutInflater.from(parent.context).inflate(
            viewType,
            parent,
            false
        )
        return MealsViewHolder(elementView,viewType)
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }

    override fun onBindViewHolder(holder: MealsViewHolder, position: Int) {
        val item = dataSource[position]
        holder.bindData(item)
        holder.itemView.setOnClickListener{
            notifyDataSetChanged()        }
    }

    fun insert(meal: DataClass){
        dataSource.add(0,meal)
        this.notifyItemInserted(0)
    }

    fun setDesign(design:Int){
        this.design = design
        notifyDataSetChanged()
    }


    inner class MealsViewHolder(view: View, design: Int): RecyclerView.ViewHolder(view){
        private var nameTextView: TextView
        private var image: ImageView
        init{
            nameTextView = itemView.findViewById(R.id.tvMealName)
            image = itemView.findViewById(R.id.imageId)
        }

        fun bindData(item: DataClass){
            nameTextView.text = "OKkkk"//item.strMeal
           // image.setImageBitmap(getImageBitmap(item.strMealThumb))
            val imageUrl = "https://www.themealdb.com/images/media/meals/vwwspt1487394060.jpg"
//            Glide.with(itemView).load(imageUrl)
////                .centerCrop()
////                .placeholder(R.drawable.ok)
////                .into(image);

            Picasso.get().load("http://i.imgur.com/DvpvklR.png")
                .fit()
                .into(itemView.findViewById<ImageView>(R.id.imageId))

          //  image.setImageResource(R.drawable.ok)
        }
    }

    private fun getImageBitmap(url: String): Bitmap? {
        var bm: Bitmap? = null
        try {
            val aURL = URL(url)
            val conn = aURL.openConnection()
            conn.connect()
            val `is` = conn.getInputStream()
            val bis = BufferedInputStream(`is`)
            bm = BitmapFactory.decodeStream(bis)
            bis.close()
            `is`.close()
        } catch (e: IOException) {
            println("Error getting bitmap")
        }
        return bm
    }


    private class DownloadImageTask(var bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }

        override fun doInBackground(vararg params: String?): Bitmap? {
            val urldisplay = params[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                Log.e("Error", e.message!!)
                e.printStackTrace()
            }
            return mIcon11
        }
    }
}


