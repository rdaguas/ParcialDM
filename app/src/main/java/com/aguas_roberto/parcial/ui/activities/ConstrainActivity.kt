package com.aguas_roberto.parcial.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.aguas_roberto.parcial.databinding.ActivityConstrainBinding
import com.aguas_roberto.parcial.logic.userCases.GetArtistasUserCase
import com.aguas_roberto.parcial.ui.adapters.ArtistasAdapter
import com.aguas_roberto.parcial.ui.entities.ArtistasDataUI
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConstrainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConstrainBinding
    private var items: MutableList<ArtistasDataUI> = mutableListOf()
    private lateinit var artistasAdapter: ArtistasAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConstrainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initVariables()
        initListeners()
        initData()
    }

    private fun initVariables() {
        artistasAdapter = ArtistasAdapter { descriptionItem(it) }
        binding.rvNobel.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvNobel.adapter = artistasAdapter
    }

    private fun initListeners() {
        binding.refreshRV.setOnRefreshListener {
            initData()
            binding.refreshRV.isRefreshing = false
        }
    }

    private fun initData() {
        binding.pgbarLoadData.visibility = View.VISIBLE
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val resultItems = GetArtistasUserCase().invoke()
                Log.d("ConstrainActivity", "API Result: $resultItems")
                withContext(Dispatchers.Main) {
                    binding.pgbarLoadData.visibility = View.INVISIBLE
                    resultItems.onSuccess {
                        Log.d("ConstrainActivity", "Success: $it")
                        items = it.toMutableList()
                        artistasAdapter.itemList = items
                        artistasAdapter.notifyDataSetChanged()
                    }
                    resultItems.onFailure {
                        showError(it.message.toString())
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    binding.pgbarLoadData.visibility = View.INVISIBLE
                    Log.e("ConstrainActivity", "Error fetching data", e)
                    showError(e.message.toString())
                }
            }
        }
    }

    private fun showError(message: String) {
        Snackbar.make(binding.refreshRV, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun descriptionItem(data: ArtistasDataUI) {
        Snackbar.make(binding.rvNobel, "Artistass", Snackbar.LENGTH_LONG).show()
        val intent = Intent(this, DetailItemActivity::class.java)
        startActivity(intent)
    }
}
