package id.muiz.githubuserapi.feature.favorit

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import id.muiz.githubuserapi.R
import id.muiz.githubuserapi.data.db.entity.UserFavorite
import id.muiz.githubuserapi.feature.detail.DetailActivity
import kotlinx.android.synthetic.main.item_row_user.view.*

class FavoritUserAdapter(private val mContext: Context) : RecyclerView.Adapter<FavoritUserAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorite> = mutableListOf()

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)  {
        fun bind(user : UserFavorite) {
            with(itemView) {
                Glide.with(context)
                    .load(user.foto)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.github_icon_white)
                    .into(img_foto_user)
                tv_item_name.text = user.username
                tv_item_id.text = user.id.toString()

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.USERNAME_KEY, user.username)
                        setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }.also {
                        itemView.context.startActivity(it)
                    }
                }

            }
        }
    }

    fun setItems(items: MutableList<UserFavorite>) {
        this.items = items
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