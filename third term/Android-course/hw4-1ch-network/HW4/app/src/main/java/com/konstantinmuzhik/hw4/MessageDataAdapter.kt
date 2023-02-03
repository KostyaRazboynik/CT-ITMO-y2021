package com.konstantinmuzhik.hw4

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.toBitmap
import androidx.recyclerview.widget.RecyclerView
import com.konstantinmuzhik.hw4.Constants.IMAGE
import com.konstantinmuzhik.hw4.Constants.TEXT
import com.konstantinmuzhik.hw4.databinding.ImageMessageItemBinding
import com.konstantinmuzhik.hw4.databinding.TextMessageItemBinding
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MessageDataAdapter(
    private val context: Context,
    private val messages: List<MessageData>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val dateFormat: DateFormat = SimpleDateFormat("HH:mm  dd MMM", Locale.ENGLISH)

    class TextViewHolder(
        binding: TextMessageItemBinding,
        val text: TextView = binding.messageText,
        val username: TextView = binding.username,
        val messageTime: TextView = binding.messageTime
    ) : RecyclerView.ViewHolder(binding.root)

    class ImageViewHolder(
        binding: ImageMessageItemBinding,
        val image: ImageView = binding.imageMessage,
        val username: TextView = binding.usernameImage,
        val messageTime: TextView = binding.messageTimeImage
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val holder: RecyclerView.ViewHolder
        if (viewType == TEXT) {
            holder = TextViewHolder(
                TextMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            holder = ImageViewHolder(
                ImageMessageItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        return holder
    }

    override fun getItemCount() = messages.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currMessage = messages[position]

        if (getItemViewType(position) == TEXT) {
            val textHolder = holder as TextViewHolder
            textHolder.text.text = currMessage.data.Text?.text ?: ""
            textHolder.username.text = currMessage.from
            textHolder.messageTime.text =
                "${Date().time} (${dateFormat.format(Date(currMessage.time.toLong()))})"
        } else {
            val imageHolder = holder as ImageViewHolder
            if (currMessage.data.Image?.bitmap == null) {
                imageHolder.image.setImageBitmap(
                    AppCompatResources.getDrawable(
                        context,
                        R.drawable.loading
                    )?.toBitmap(200, 200)
                )
            } else
                imageHolder.image.setImageBitmap(currMessage.data.Image.bitmap)

            imageHolder.username.text = currMessage.from
            imageHolder.messageTime.text =
                "${Date().time} (${dateFormat.format(Date(currMessage.time.toLong()))})"
            imageHolder.image.setOnClickListener {
                if (currMessage.data.Image?.bitmap != null) {
                    val intent = Intent(context, ImageActivity::class.java)
                    intent.putExtra("messagePosition", position)
                    context.startActivity(intent)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].data.Image?.link?.isNotEmpty() == true)
            IMAGE
        else
            TEXT
    }
}