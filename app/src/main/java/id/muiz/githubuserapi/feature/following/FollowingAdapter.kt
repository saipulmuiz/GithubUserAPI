package id.muiz.githubuserapi.feature.following

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.data.entity.UserFollowingResponseItem
import id.muiz.githubuserapi.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class FollowingAdapter(private val mContext: Context) : RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private var items = mutableListOf<UserFollowingResponseItem>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFollowingResponseItem) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.github_icon_white)
                    .into(img_foto_user)

                tv_item_name.text = data.login
                tv_item_id.text = data.id.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.USERNAME_KEY, data.login)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }

    fun setItems(data : MutableList<UserFollowingResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}