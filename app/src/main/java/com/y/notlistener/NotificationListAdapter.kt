package com.y.notlistener


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.y.notlistener.databinding.ItemNotificationviewBinding
import com.y.notlistener.db.Notification


class NotificationListAdapter(
    var context: Context,
    val notifications: List<Notification>
) :
    RecyclerView.Adapter<NotificationListAdapter.ViewHolderCart>() {

    private val TAG = NotificationListAdapter::class.java.simpleName

    var view_Visibility = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCart {

        val itemInvoice: ItemNotificationviewBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_notificationview, parent, false
        )

        return ViewHolderCart(itemInvoice)

    }

    override fun onBindViewHolder(holder: ViewHolderCart, position: Int) {

        holder.bind(notifications[position])

        val appIcon = context.getPackageManager().getApplicationIcon(notifications[position].packageid)
        holder.img_icon.setImageDrawable(appIcon)

    }

    override fun getItemCount(): Int {
        return notifications.size
    }


    override fun getItemViewType(position: Int): Int {
        return position
    }


    class ViewHolderCart(itemInvoice: ItemNotificationviewBinding) :
        RecyclerView.ViewHolder(itemInvoice.root) {
        val _itemProductChangeBinding = itemInvoice
        val img_icon = itemInvoice.imgIcon

        fun bind(obj: Notification) {
            _itemProductChangeBinding.setVariable(BR.notification, obj)
            _itemProductChangeBinding.executePendingBindings()

        }
    }


}

