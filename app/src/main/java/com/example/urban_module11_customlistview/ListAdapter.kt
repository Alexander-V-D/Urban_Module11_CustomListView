package com.example.urban_module11_customlistview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context: Context, productList: MutableList<Product>):
ArrayAdapter<Product>(context, R.layout.list_item, productList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val product = getItem(position)
        if (view == null) {
            view =
                LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val itemPictureIV = view?.findViewById<ImageView>(R.id.itemPictureIV)
        val itemNameTV = view?.findViewById<TextView>(R.id.itemNameTV)
        val itemPriceTV = view?.findViewById<TextView>(R.id.itemPriceTV)

        itemPictureIV?.setImageBitmap(product?.image)
        itemNameTV?.text = product?.name
        itemPriceTV?.text = product?.price.toString()
        return view!!
    }
}