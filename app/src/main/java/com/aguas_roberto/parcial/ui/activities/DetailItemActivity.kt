package com.aguas_roberto.parcial.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aguas_roberto.parcial.databinding.ItemArtistasBinding

class DetailItemActivity : AppCompatActivity() {

    private lateinit var binding: ItemArtistasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ItemArtistasBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}