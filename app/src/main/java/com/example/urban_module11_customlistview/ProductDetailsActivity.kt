package com.example.urban_module11_customlistview

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ProductDetailsActivity : AppCompatActivity() {

    private lateinit var detailsPictureIV: ImageView
    private lateinit var detailsNameTV: TextView
    private lateinit var detailsPriceTV: TextView
    private lateinit var detailsInfoTV: TextView
    private lateinit var detailsToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        detailsToolbar = findViewById(R.id.detailsToolbar)
        title = getString(R.string.product)
        setSupportActionBar(detailsToolbar)
        detailsToolbar.overflowIcon?.setTint(Color.WHITE)

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finishAffinity()
        return super.onOptionsItemSelected(item)
    }
}