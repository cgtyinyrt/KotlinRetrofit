package com.cagatayinyurt.kotlinretrofit.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cagatayinyurt.kotlinretrofit.databinding.CryptoRowBinding
import com.cagatayinyurt.kotlinretrofit.model.CryptoModel

class CryptoAdapter(
    val cryptoModelList: ArrayList<CryptoModel>
) : RecyclerView.Adapter<CryptoAdapter.CryptoHolder>() {

    private val colors: Array<String> = arrayOf(
        "#DAF7A6",
        "#FFC300",
        "#FF5733",
        "#C70039",
        "#900C3F",
        "#581845",
        "#FF00FF",
        "#00FFFF"
    )

    inner class CryptoHolder(
        val binding: CryptoRowBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoHolder {
        val binding =
            CryptoRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return CryptoHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoHolder, position: Int) {
        holder.binding.tvCrypto.text = cryptoModelList[position].currency
        holder.binding.tvPrice.text = cryptoModelList[position].price
        holder.itemView.setBackgroundColor(Color.parseColor(colors[position%8]))
        holder.itemView.setOnClickListener {
            Toast.makeText(holder.itemView.context, "Currency:${cryptoModelList[position].currency}", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return cryptoModelList.count()
    }
}