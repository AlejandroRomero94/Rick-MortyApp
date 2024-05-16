package com.alejandro.frikiapp.rickandmorty

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.rickmortyapp.R

class RickMortyAdapter(var characterList: List<RickMortyItemResponse> = emptyList()) :
    RecyclerView.Adapter<RickMortyViewHolder>() {
    fun updateList(characterList: List<RickMortyItemResponse>){
        this.characterList=characterList
        notifyDataSetChanged()
        //this es el de la clase y el otro el del listado.
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RickMortyViewHolder {
        return RickMortyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_rickmorty, parent, false))
    }

    override fun onBindViewHolder(viewholder: RickMortyViewHolder, position: Int) {
        viewholder.bind(characterList[position])
    }

    override fun getItemCount()=characterList.size
    }
