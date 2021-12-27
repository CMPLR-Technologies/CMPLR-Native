package com.cmp.cmplr.Adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cmp.cmplr.R
import com.google.gson.JsonObject
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.cmp.cmplr.Shared.getImage
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import java.io.IOException
import java.io.InputStream
import java.net.URL


/**
 * This Adapter is responsible for rendering the Notes view
 *
 */

class NotesAdapter : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    var comments_list : ArrayList<JsonObject> = ArrayList()
    var images_list : ArrayList<Bitmap?> = ArrayList()


    /**
     * Member function used to set the comments data
     *
     * @param comments_list List of comments and users of this comments
     */
    fun notifyChanges(){
        notifyDataSetChanged()
    }
    fun setImages(images_list : ArrayList<Bitmap?>)
    {
        this.images_list = images_list
    }
    fun setList(comments_list : ArrayList<JsonObject>)
    {
        this.comments_list = comments_list
    }

    /**
     * Inner class used to set the view with real API data
     * Data comming from setList function
     *
     * @param itemView
     */
    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var commentView : TextView = itemView.findViewById(R.id.user_comment)
        var user_name : TextView = itemView.findViewById(R.id.notes_user_name)
        var user_img : ImageView = itemView.findViewById(R.id.comment_user_img)
        fun bind(comment : JsonObject , img : Bitmap?)
        {
            user_img.setImageBitmap(img)
            commentView.text = comment["content"].asString
            user_name.text = comment["blog_name"].asString
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.comment_layout,parent,false)
        return  NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        var comment : JsonObject = comments_list[position]
        var img : Bitmap? = images_list[position]
        holder.bind(comment,img)
    }

    /**
     * Member function used to know how many views will be rendered
     *
     * @return
     */
    override fun getItemCount(): Int {
        return comments_list.size
    }
}