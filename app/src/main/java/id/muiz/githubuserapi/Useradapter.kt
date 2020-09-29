package id.muiz.githubuserapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_row_user.view.*

class Useradapter(private val onItemClickListener: (UserItems) -> Unit) :
    RecyclerView.Adapter<Useradapter.UserViewHolder>() {
    private val mData = ArrayList<UserItems>()

    fun setData(items: ArrayList<UserItems>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val mView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_row_user, parent, false)
        return UserViewHolder(mView)
    }

    override fun getItemCount(): Int = mData.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userItems: UserItems) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(userItems.foto)
                    .apply(RequestOptions().override(80, 80))
                    .into(img_foto_user)
                tv_item_name.text = userItems.username
                tv_item_id.text = userItems.id.toString()
                setOnClickListener {
                    onItemClickListener(userItems)
                }
            }
        }
    }
}