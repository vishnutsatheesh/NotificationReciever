package com.y.notlistener

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.y.notlistener.db.NotifcationViewModel
import com.y.notlistener.db.Notification
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
public class FirstFragment : Fragment() {

    internal var txtView: TextView? = null
    private var nReceiver: NotificationReceiver? = null

    private lateinit var viewModel: NotifcationViewModel

    lateinit var notifcationList: ArrayList<Notification>

    lateinit var adapter_: NotificationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        nReceiver = NotificationReceiver()
        val filter = IntentFilter()
        filter.addAction("com.y.notlistener.NOTIFICATION_LISTENER_EXAMPLE")
        activity!!.registerReceiver(nReceiver, filter)


        init()


    }

    fun init() {

        viewModel = ViewModelProviders.of(this).get(NotifcationViewModel::class.java)

        viewModel?.getitemNofication()?.observe(
            this@FirstFragment,
            object : Observer<List<Notification>> {
                override fun onChanged(@Nullable itemAndPeople: List<Notification>) {

                    notifcationList = ArrayList()

                    notifcationList.addAll(itemAndPeople)

                    adapter_ = NotificationListAdapter(
                        requireContext(),
                        notifcationList
                    )
                    adapter_.view_Visibility = false
                    rv_invoice_list.apply {
                        layoutManager = LinearLayoutManager(requireContext())
                        adapter = adapter_
                    }

                }
            })
    }

    override fun onDestroy() {
        super.onDestroy()
        activity!!.unregisterReceiver(nReceiver)
    }


    class NotificationReceiver : BroadcastReceiver() {
        override fun onReceive(
            context: Context,
            intent: Intent
        ) {
            val temp =
                """
                ${intent.getStringExtra("notification_event")}
                """.trimIndent()
            Log.e("?????", "$temp")
            (context as FirstFragment).init()

        }
    }

}
