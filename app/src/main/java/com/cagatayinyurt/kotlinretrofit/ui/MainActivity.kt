package com.cagatayinyurt.kotlinretrofit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cagatayinyurt.kotlinretrofit.R
import com.cagatayinyurt.kotlinretrofit.adapter.CryptoAdapter
import com.cagatayinyurt.kotlinretrofit.databinding.ActivityMainBinding
import com.cagatayinyurt.kotlinretrofit.model.CryptoModel
import com.cagatayinyurt.kotlinretrofit.service.CryptoAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private val BASE_URL = "https://api.nomics.com/v1/"
    private var cryptoModelArrayList: ArrayList<CryptoModel>? = null
    private lateinit var cryptoAdapter: CryptoAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 2cf802e0d549fd6abfb795a3c2f72284453d4af0
        // https://api.nomics.com/v1/
        // prices?key=2cf802e0d549fd6abfb795a3c2f72284453d4af0

        // RecyclerViewAdapter
        val layoutManager : RecyclerView.LayoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        loadData()
    }

    private fun loadData() {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(CryptoAPI::class.java)
        val call = service.getData()

        call.enqueue(object: Callback<List<CryptoModel>> {
            override fun onResponse(
                call: Call<List<CryptoModel>>,
                response: Response<List<CryptoModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        cryptoModelArrayList = ArrayList(it)

                        cryptoModelArrayList?.let {
                            cryptoAdapter = CryptoAdapter(it)
                            binding.recyclerView.adapter = cryptoAdapter
                        }

//                        for (cryptoModel: CryptoModel in cryptoModelArrayList!!) {
//                            println(cryptoModel.currency)
//                            println(cryptoModel.price)
//                        }
                    }
                }
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

//    override fun onItemClick(cryptoModel: CryptoModel) {
//        Toast.makeText(this, "Clicked: ${cryptoModel.currency}", Toast.LENGTH_LONG).show()
//    }
}