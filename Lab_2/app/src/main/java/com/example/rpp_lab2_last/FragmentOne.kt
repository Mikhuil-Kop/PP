package com.example.rpp_lab2_last

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_fragment_one.*
import kotlinx.android.synthetic.main.row_layout.*
import java.sql.Types.NULL

class FragmentOne constructor(page: String) : Fragment() {
    var item: String = ""
    init { item = page }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        val element = technologies_list.adapter.getItem(0)
//        dynamicImageView.setImageResource(element.toString())
        textView.text = item
        Picasso.get().load("https://raw.githubusercontent.com/wesleywerner/ancient-tech/02decf875616dd9692b31658d92e64a20d99f816/src/images/tech/" + item).into(dynamicImageView)
    }
}