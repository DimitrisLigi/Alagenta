package adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.dimitrisligi.alagenta.R
import models.Client

class ClientListAdapter(private val clientList: List<Client>,val context: Context):
    RecyclerView.Adapter<ClientListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val mClientAddress: TextView? = itemView.findViewById(R.id.tv_eventAddress)
        val mClientName: TextView? = itemView.findViewById(R.id.tv_event_type)
        val mClientNumber: TextView? = itemView.findViewById(R.id.tv_event_time)
//        var mcardView: View? = itemView.findViewById(R.id.cv_event_card)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).
        inflate(R.layout.recycler_view_item,parent,false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentClient = clientList[position]
        holder.mClientAddress?.text = currentClient.address
        holder.mClientName?.text = currentClient.name
        holder.mClientNumber?.text = currentClient.phone
        holder.itemView.setOnClickListener {
            Toast.makeText(context,holder.mClientName?.text.toString(),
                Toast.LENGTH_SHORT).show()
        }
//        holder.mcardView?.setBackgroundColor(Color.BLUE)
    }

    override fun getItemCount(): Int {
        return clientList.size
    }
}