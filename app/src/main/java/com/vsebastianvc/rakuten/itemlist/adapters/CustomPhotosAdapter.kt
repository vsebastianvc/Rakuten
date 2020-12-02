package com.vsebastianvc.rakuten.itemlist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.vsebastianvc.rakuten.R
import com.vsebastianvc.rakuten.models.Photo
import com.vsebastianvc.rakuten.utils.PhotoUtils.Companion.constructPhotoURL
import com.vsebastianvc.rakuten.utils.PhotoUtils.Companion.loadImageWithGlide

/**
 * Created by Sebastian on 11/29/2020.
 */
class CustomPhotosAdapter(context: Context, private var listPhoto: List<Photo>) : BaseAdapter() {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getCount(): Int {
        return listPhoto.size
    }

    override fun getItem(position: Int): Photo {
        return listPhoto[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val holder: ViewHolder
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item_photo, parent, false)
            holder = ViewHolder()
            holder.photo = view.findViewById<View>(R.id.iv_photo) as ImageView
            holder.photoTitle = view.findViewById<View>(R.id.tv_photo_title) as TextView
            view.tag = holder
        } else {
            holder = view.tag as ViewHolder
        }
        loadImageWithGlide(holder.photo, constructPhotoURL(getItem(position)))
        holder.photoTitle.text = getItem(position).title
        return view!!
    }

    fun refreshData(listPhoto: List<Photo>) {
        this.listPhoto = listPhoto
        notifyDataSetChanged()
    }

    internal class ViewHolder {
        lateinit var photo: ImageView
        lateinit var photoTitle: TextView
    }

}