package adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimitrisligi.alagenta.R
import models.Event

/*  This adapter is so awesome that can take a list as an argument, and populate that list on the
    RecyclerView
* */
class EventAdapter(private val eventList: List<Event>):
    RecyclerView.Adapter<EventAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val mEventAddress: TextView? = itemView.findViewById(R.id.tv_eventAddress)
        val mEventDescription: TextView? = itemView.findViewById(R.id.tv_event_type)
        val mEventTime: TextView? = itemView.findViewById(R.id.tv_event_time)
//        val mTime: TextView? = itemView.findViewById(R.id.tv_event_time)
//        val mEventDescription: TextView? = itemView.findViewById(R.id.tv_event_description)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_view_item,parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentEventItem = eventList[position]
        holder.mEventAddress?.text = currentEventItem.address
        holder.mEventTime?.text = currentEventItem.time
        holder.mEventDescription?.text = currentEventItem.appointmentType.type.toString()

    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}