package id.muiz.githubuserapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import de.hdodenhof.circleimageview.CircleImageView
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.model.FollowingItems
import kotlinx.android.synthetic.main.item_row_user.view.*

var followingsFilterList = ArrayList<FollowingItems>()
class FollowingAdapter(listFollowingItems: ArrayList<FollowingItems>) :
    RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder>() {
    init {
        followingsFilterList = listFollowingItems
    }

    inner class FollowingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto: CircleImageView = itemView.img_foto_user
        var username: TextView = itemView.tv_item_name
        var id: TextView = itemView.tv_item_id
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(followingItems: FollowingItems)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FollowingAdapter.FollowingViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        val dataHolder = FollowingViewHolder(mView)
        mcontext = parent.context
        return dataHolder
    }

    override fun getItemCount(): Int = followingsFilterList.size

    override fun onBindViewHolder(holder: FollowingAdapter.FollowingViewHolder, position: Int) {
        val data = followingsFilterList[position]
        Glide.with(holder.itemView.context)
            .load(data.foto)
            .apply(RequestOptions().override(80, 80))
            .into(holder.foto)
        holder.username.text = data.username
        holder.id.text = data.id.toString()
        holder.itemView.setOnClickListener {
        }
    }
}