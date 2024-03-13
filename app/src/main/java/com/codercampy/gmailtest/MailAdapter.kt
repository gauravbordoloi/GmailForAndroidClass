package com.codercampy.gmailtest

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.codercampy.gmailtest.databinding.ItemMailBinding

class MailAdapter(
    private val mails: List<Mail>,
    private val listener: MailClickListener
) : RecyclerView.Adapter<MailAdapter.MyViewHolder>(), Filterable {

    private var filteredList: List<Mail> = mails

    private val starredItems = mutableListOf<Int>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MyViewHolder(ItemMailBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val mail = filteredList[position]
        holder.binding.tvFrom.text = mail.from
        holder.binding.tvSubject.text = mail.subject
        holder.binding.tvBody.text = mail.body
            .replace("<h1>", "")
            .replace("</h1>", "")
            .replace("<p1>", "")
            .replace("</p1>", "")
        holder.binding.tvTime.text = parseTime(mail.timestamp)
        holder.binding.ivAvatar.text = getRandomAlphabets().uppercase()
        holder.binding.ivAvatar.backgroundTintList = ColorStateList.valueOf(getRandomColor())

        holder.binding.root.setOnClickListener {
            listener.onMailClick(mail, starredItems.contains(position))
        }

        if (starredItems.contains(position)) {
            holder.binding.btnStar.setImageResource(R.drawable.star)
        } else {
            holder.binding.btnStar.setImageResource(R.drawable.star_outline)
        }

        holder.binding.btnStar.setOnClickListener {
            if (starredItems.contains(position)) {
                holder.binding.btnStar.setImageResource(R.drawable.star_outline)
                starredItems.remove(position)
            } else {
                holder.binding.btnStar.setImageResource(R.drawable.star)
                starredItems.add(position)
            }
        }
    }

    class MyViewHolder(val binding: ItemMailBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val text = constraint.toString()
                val filterResults = FilterResults()
                val temp = mutableListOf<Mail>()

                mails.forEach {
                    if (
                        it.from.contains(text) ||
                        it.subject.contains(text) ||
                        it.body.contains(text)
                    ) {
                        temp.add(it)
                    }
                }

                filterResults.values = temp
                filterResults.count = temp.size

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = results!!.values as List<Mail>
                notifyDataSetChanged()
            }
        }
    }

}

fun interface MailClickListener {

    fun onMailClick(mail: Mail, isStarred: Boolean)

}