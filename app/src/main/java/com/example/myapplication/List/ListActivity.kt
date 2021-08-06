package com.example.myapplication.List

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.R
import com.example.myapplication.Repository.Repository

class ListActivity : AppCompatActivity() {

    private lateinit var viewModel: ListViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val button = findViewById<Button>(R.id.button2)

        val repository = Repository()
        val viewModelFactory = ListViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ListViewModel::class.java)
        viewModel.getPost()
        viewModel.myResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                Log.d("ResponseLog", response.body()?.userId.toString())
                Log.d("ResponseLog", response.body()?.id.toString())
                Log.d("ResponseLog", response.body()?.title!!)
                Log.d("ResponseLog", response.body()?.body!!)
            } else {
                Log.d("ResponseLog", response.errorBody().toString())

            }
        })

        button.setOnClickListener {
            Toast.makeText(this, "Back", Toast.LENGTH_SHORT).show();
            finish()
        }
    }
}