package adapters

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
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
class EventAdapter(private val eventList: List<Event>,private val context: Context):
    RecyclerView.Adapter<EventAdapter.ViewHolder>()
{
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val mEventAddress: TextView? = itemView.findViewById(R.id.tv_eventAddress)
        val mEventDescription: TextView? = itemView.findViewById(R.id.tv_event_type)
        val mEventTime: TextView? = itemView.findViewById(R.id.tv_event_time)
        val mEventPhone: TextView? = itemView.findViewById(R.id.tv_event_phone)
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
        holder.mEventAddress?.text = currentEventItem.event_address
        holder.mEventTime?.text = currentEventItem.eventTime
        holder.mEventDescription?.text = currentEventItem.eventAppointmentType.type.toString()

        holder.itemView.setOnClickListener {
            val dialog = Dialog(context)

            dialog.setContentView(R.layout.event_details_popup)

            dialog.findViewById<TextView>(R.id.tv_adress_details_popup).text = currentEventItem.event_address
            dialog.findViewById<TextView>(R.id.tv_phone_details_event_popup).text = currentEventItem.eventPhone
            dialog.findViewById<TextView>(R.id.tv_name_ditails_popup).text = currentEventItem.eventName
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.show()


        }
    }

    override fun getItemCount(): Int {
        return eventList.size
    }
}