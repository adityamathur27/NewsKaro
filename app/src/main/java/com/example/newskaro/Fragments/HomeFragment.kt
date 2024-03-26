package com.example.newskaro.Fragments


import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
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
import java.util.ArrayList

class HomeFragment : Fragment(R.layout.fragment_home){
    private lateinit var adapter: RecyclerViewAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView : RecyclerView = view.findViewById(R.id.rvHomeFragment)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = RecyclerViewAdapter(ArrayList())
        recyclerView.adapter = adapter
        var map = HashMap<String , String>();
        map.put("0","us");
        map.put("1","uk");
        map.put("2","in");
        map.put("3","at")
        map.put("4","it")
        map.put("5" , "jp")
        var i = "0"
        var str = map.getValue(i)
        val spinner : Spinner = view.findViewById(R.id.spinnerBar);
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                i = position.toString()
                str = map.getValue(i)
                fetchData(str)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        fetchData(str)
    }

    private fun fetchData(str : String) {
        val apiService : ApiInterface = RetrofitClient.retrofit.create(ApiInterface::class.java)
        val apiKey = "815a8d04fde34b06b7cd33220034147d"
        val call: Call<data> = apiService.getData(str , apiKey)
        call.enqueue(object : Callback<data> {
            override fun onResponse(call: Call<data>, response: retrofit2.Response<data>) {
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