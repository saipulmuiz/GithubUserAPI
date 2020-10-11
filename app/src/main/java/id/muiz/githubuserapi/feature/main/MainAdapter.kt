package id.muiz.githubuserapi.feature.main

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.data.entity.UserSearchResponseItem
import id.muiz.githubuserapi.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class MainAdapter(val context: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items = mutableListOf<UserSearchResponseItem>()
    private lateinit var mainActivity: MainActivity

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(data: UserSearchResponseItem, activity: MainActivity) {
            with(itemView) {
                Glide.with(context)
                    .load(data.avatarUrl)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.github_icon_white)
                    .into(img_foto_user)

                tv_item_name.text = data.login
                tv_item_id.text = data.id.toString()
            }
            itemView.setOnClickListener {
                val options: ActivityOptionsCompat =
                    ActivityOptionsCompat
                        .makeSceneTransitionAnimation(
                            activity,
                            itemView.img_foto_user,
                            "image"
                        )
                val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.USERNAME_KEY, data.login)
                    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                }.also {
                    itemView.context.startActivity(it)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_row_user, viewGroup, false))
    }

    fun setActivity(activity: MainActivity) {
        this.mainActivity = activity
    }

    fun setItems(data: MutableList<UserSearchResponseItem>) {
        this.items = data
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bind(items[position], activity = mainActivity)
    }
}