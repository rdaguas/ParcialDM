package com.aguas_roberto.parcial.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.aguas_roberto.parcial.R
import com.aguas_roberto.parcial.databinding.ItemArtistasBinding
import com.aguas_roberto.parcial.ui.entities.ArtistasDataUI
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop


class ArtistasAdapter(
    private val onClickItem: (ArtistasDataUI) -> Unit
) :
    RecyclerView.Adapter<ArtistasAdapter.ArtistasViewHolder>() {
    var itemList: List<ArtistasDataUI> = emptyList()
    class ArtistasViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ItemArtistasBinding.bind(view)

        @SuppressLint("CheckResult")
        fun render(
            data: ArtistasDataUI,
            onClickItem: (ArtistasDataUI) -> Unit
        ) {
            Glide.with(binding.root)
                .load(data.profile_image)
                .transform(CircleCrop())
                .into(binding.imgArtist)

            binding.txtName.text = data.name
            binding.txtMessage.text = data.message


            itemView.setOnClickListener {
                onClickItem(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistasViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArtistasViewHolder(
            inflater.inflate(
                R.layout.item_artistas,
                parent,
                false
            )
        )
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: ArtistasViewHolder, position: Int) {
        holder.render(
            itemList[position],
            onClickItem
        )
    }
}