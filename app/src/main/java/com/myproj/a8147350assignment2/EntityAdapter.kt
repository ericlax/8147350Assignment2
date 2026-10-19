package com.myproj.a8147350assignment2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject

class EntityAdapter(
    private val entities: List<JsonObject>,
    private val onClick: (JsonObject) -> Unit
) : RecyclerView.Adapter<EntityAdapter.EntityViewHolder>() {

    class EntityViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val tvEntity: TextView =
            view.findViewById(R.id.tvEntity)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EntityViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_entity, parent, false)

        return EntityViewHolder(view)
    }

    override fun getItemCount(): Int = entities.size

    override fun onBindViewHolder(
        holder: EntityViewHolder,
        position: Int
    ) {
        val entity = entities[position]

        val summary = entity.entrySet()
            .filter { it.key != "description" }
            .joinToString("\n") {
                "${it.key}: ${it.value.asString}"
            }

        holder.tvEntity.text = summary

        holder.itemView.setOnClickListener {
            onClick(entity)
        }
    }
}
