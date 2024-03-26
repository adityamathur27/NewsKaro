package com.example.newskaro.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newskaro.Adapter.RecyclerViewAdapter
import com.example.newskaro.ApiInterface
import com.example.newskaro.Data.Article
import com.example.newskaro.Data.data
import com.example.newskaro.R
import com.example.newskaro.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.ArrayList

class SearchFragment : Fragment(R.layout.fragment_search)
{
    private lateinit var adapter: RecyclerViewAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.recyclerVew)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerViewAdapter(ArrayList())
        recyclerView.adapter = adapter

        val searchText : EditText = view.findViewById(R.id.searchText)
        val searchButton : Button = view.findViewById(R.id.searchButton)

        searchButton.setOnClickListener{
            val text = searchText.text.toString()
            fetchData(text)
        }

    }
    private fun fetchData(text : String){
        val apiService : ApiInterface = RetrofitClient.retrofit.create(ApiInterface::class.java)
        val apiKey = "815a8d04fde34b06b7cd33220034147d"
        val call : Call<data> = apiService.getAllData(text , apiKey)

        call.enqueue(object : Callback<data>{
            override fun onResponse(call: Call<data>, response: Response<data>) {
                val dataModel: data? = response.body()
                if (dataModel != null) {
                    val articles = dataModel.articles
                    val list = ArrayList<Article>()
                    for (article in articles){
                        if(article.title != null && article.content != null){
                            list.add(article)
                        }
                    }
                    adapter.updateData(list)
                }
            }

            override fun onFailure(call: Call<data>, t: Throwable) {
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
            }

        })
    }
}

