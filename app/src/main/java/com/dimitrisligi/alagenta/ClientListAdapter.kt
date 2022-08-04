package com.dimitrisligi.alagenta

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import models.Client

class ClientListAdapter(private val clientList: List<Client>):
    RecyclerView.Adapter<ClientListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val mClientAddress: TextView? = itemView.findViewById(R.id.tv_eventAddress)
        val mClientName: TextView? = itemView.findViewById(R.id.tv_event_description)
        val mClientNumber: TextView? = itemView.findViewById(R.id.tv_event_time)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ClientListAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ClientListAdapter.ViewHolder, position: Int) {
        val currentClient = clientList[position]
        holder.mClientAddress?.text = currentClient.address
        holder.mClientName?.text = currentClient.name
        holder.mClientNumber?.text = currentClient.phone
    }

    override fun getItemCount(): Int {
        return clientList.size
    }
}