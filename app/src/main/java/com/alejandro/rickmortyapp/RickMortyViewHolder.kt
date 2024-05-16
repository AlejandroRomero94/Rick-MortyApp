package com.alejandro.frikiapp.rickandmorty

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.alejandro.rickmortyapp.databinding.ItemRickmortyBinding
import com.squareup.picasso.Picasso

class RickMortyViewHolder(view:View): RecyclerView.ViewHolder(view) {
    private val binding=ItemRickmortyBinding.bind(view)
    fun bind(rickMortyItemResponse:RickMortyItemResponse){
        binding.tvCharacterName.text=rickMortyItemResponse.name
Picasso.get().load(rickMortyItemResponse.charactersImage).into(binding.ivRickMorty)
    }
}