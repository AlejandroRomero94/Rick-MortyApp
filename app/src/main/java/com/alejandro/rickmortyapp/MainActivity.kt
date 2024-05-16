package com.alejandro.rickmortyapp

import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.alejandro.frikiapp.rickandmorty.ApiServiceRMCharacters
import com.alejandro.frikiapp.rickandmorty.RickMortyAdapter
import com.alejandro.frikiapp.rickandmorty.RickMortyDataResponse
import com.alejandro.rickmortyapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var retrofit: Retrofit
    private lateinit var adapter: RickMortyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.svRickMorty.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByRickId(query.orEmpty())
                return false

            }

            override fun onQueryTextChange(newText: String?) = false


        })
        val searchText=binding.svRickMorty.findViewById<EditText>(androidx.appcompat.R.id.search_src_text)
        searchText.setTextColor(ContextCompat.getColor(this, R.color.white))
        adapter = RickMortyAdapter()
        binding.rvRickMorty.setHasFixedSize(true)
        binding.rvRickMorty.layoutManager= LinearLayoutManager(this)
        binding.rvRickMorty.adapter=adapter
    }

    private fun searchByRickId(query: String) {
        binding.progressBarRM.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse: Response<RickMortyDataResponse> =
                retrofit.create(ApiServiceRMCharacters::class.java).getRickyCharacter(query)
            if (myResponse.isSuccessful) {
                Log.i("frikiapp", "funciona")
                val response: RickMortyDataResponse? = myResponse.body()
                if (response != null) {
                    Log.i("frikiapp", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.characters)
                        binding.progressBarRM.isVisible = false
                    }
                }
            } else {
                Log.i("frikiapp", "no funciona")
            }

        }
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://rickandmortyapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }




}

