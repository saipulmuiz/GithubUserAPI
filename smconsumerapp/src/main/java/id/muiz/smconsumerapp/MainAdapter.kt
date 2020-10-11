package id.muiz.smconsumerapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import id.muiz.smconsumerapp.data.UserFavorit
import kotlinx.android.synthetic.main.item_row_user.view.*

class MainAdapter(private val mContext: Context) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var items: MutableList<UserFavorit> = mutableListOf()

    fun setItems(items: MutableList<UserFavorit>) {
        this.items = items
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserFavorit) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(data.foto)
                    .circleCrop()
                    .into(img_foto_user)

                tv_item_name.text = data.username
                tv_item_id.text = data.id.toString()

                itemView.setOnClickListener {
                    Toast.makeText(context, context.getString(R.string.user_detail_information), Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_row_user, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}