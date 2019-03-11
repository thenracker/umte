package cz.uhk.umteapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.uhk.umteapp.model.User
import kotlinx.android.synthetic.main.fragment_list.*

class ListFragment : Fragment() {

    val list = mutableListOf<User>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = Adapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        addButton.setOnClickListener {
            val user = User(editText.text.toString(), "", 0, 0)
            editText.setText("")
            list.add(user)
            adapter.notifyDataSetChanged()
        }
    }

    inner class Adapter : RecyclerView.Adapter<Adapter.Holder>() {

        override fun onCreateViewHolder(root: ViewGroup, p1: Int): Holder {
            return Holder(LayoutInflater.from(context)
                .inflate(R.layout.row_user, root, false))
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.onBind()
        }

        override fun getItemCount(): Int {
            return list.size
        }

        inner class Holder(val item: View) : RecyclerView.ViewHolder(item),
            View.OnClickListener {

            init {
                item.setOnClickListener(this)
            }

            fun onBind() {
                val textView = item.findViewById<TextView>(R.id.nameTextView)
                val user = list[layoutPosition]

                textView.text = user.name
            }

            override fun onClick(v: View?) {
                // otevřít detail uživatele todo
            }
        }
    }

}