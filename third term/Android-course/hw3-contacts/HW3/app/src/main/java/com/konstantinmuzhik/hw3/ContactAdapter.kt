package com.konstantinmuzhik.hw3

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(private val context: Context, contacts: List<Contact>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    private val contacts = contacts.toList()

    class ContactViewHolder(root: View, val name: Button = root.findViewById(R.id.name),
        val phone: Button = root.findViewById(R.id.phone),
        val sms: Button = root.findViewById(R.id.sms)) : RecyclerView.ViewHolder(root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.contact_list, parent, false)
        )
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val current = contacts[position]

        holder.name.text = current.name
        holder.phone.text = current.phoneNumber

        holder.name.setOnClickListener { setNumber(current) }

        holder.phone.setOnClickListener { setNumber(current) }

        holder.sms.setOnClickListener {
            context.startActivity(
                Intent(
                    Intent.ACTION_SENDTO,
                    Uri.parse("smsto:${current.phoneNumber}")
                )
            )
        }
    }

    private fun setNumber(current: Contact) {
        context.startActivity(
            Intent(
                Intent.ACTION_DIAL,
                Uri.parse("tel:${current.phoneNumber}")
            )
        )
    }
}