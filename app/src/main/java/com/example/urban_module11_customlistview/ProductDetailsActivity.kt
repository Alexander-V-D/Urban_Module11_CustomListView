package com.example.urban_module11_customlistview

import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var detailsPictureIV: ImageView
    private lateinit var detailsNameTV: TextView
    private lateinit var detailsPriceTV: TextView
    private lateinit var detailsInfoTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        detailsPictureIV = findViewById(R.id.detailsPictureIV)
        detailsNameTV = findViewById(R.id.detailsNameTV)
        detailsPriceTV = findViewById(R.id.detailsPriceTV)
        detailsInfoTV = findViewById(R.id.detailsInfoTV)

        val product = intent.getParcelableExtra<Product>(Product::class.java.simpleName)
        val imageURI = Uri.parse(product?.image)
        detailsPictureIV.setImageURI(imageURI)
        detailsNameTV.text = product?.name
        detailsPriceTV.text = product?.price
        detailsInfoTV.text = product?.info
    }
}