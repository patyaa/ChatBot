package com.example.chatbot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.databinding.TextItemViewBinding
import com.example.chatbot.model.Message

class TextItemViewHolder(private val binding: TextItemViewBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(message: Message) {
        binding.content.text = message.content
        binding.image.setColorFilter(message.senderColor)
    }
}

class WordsAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    var data = listOf<Message>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = createBinding(layoutInflater, parent)
        return TextItemViewHolder(binding)
    }

    private fun createBinding(layoutInflater : LayoutInflater, parent: ViewGroup) = TextItemViewBinding.inflate(layoutInflater, parent, false).apply {
        val lp = RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        root.setLayoutParams(lp)
    }
}

