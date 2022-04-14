package com.example.themeal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText


class MainActivity : AppCompatActivity() {

    lateinit var mealsAdapter: MealsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val ingredient = findViewById<TextInputEditText>(R.id.ingredientId)
        mealsAdapter = MealsAdapter()

        val sendButton = findViewById<Button>(R.id.searchIngredientButtonId)
        sendButton.setOnClickListener{
            //Do API call
            mealsAdapter.insert(
                DataClass(
                    strMeal = "Meal",
                    strMealThumb = "https:\\/\\/www.themealdb.com\\/images\\/media\\/meals\\/vwwspt1487394060.jpg"//message.text.toString(),
                )
            )
            recyclerView.layoutManager?.scrollToPosition(0)
            ingredient.setText("")
            ingredient.clearFocus()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mealsAdapter
        }
    }


}