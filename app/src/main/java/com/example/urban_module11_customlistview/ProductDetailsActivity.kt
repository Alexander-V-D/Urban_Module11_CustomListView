package com.example.urban_module11_customlistview

import android.content.Intent
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

    private var pictureURI: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        detailsToolbar = findViewById(R.id.detailsToolbar)
        title = getString(R.string.product)
        setSupportActionBar(detailsToolbar)
        detailsToolbar.overflowIcon?.setTint(Color.WHITE)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

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

        detailsPictureIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitMenu -> {
                finishAffinity()
            }
            android.R.id.home -> finish()
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        detailsPictureIV = findViewById(R.id.detailsPictureIV)
        when (requestCode) {
            GALLERY_REQUEST -> {
                pictureURI = data?.data
                detailsPictureIV.setImageURI(pictureURI)
                Database.products[intent.getIntExtra("index", 0)] = Product(
                    detailsNameTV.text.toString(),
                    detailsPriceTV.text.toString(),
                    detailsInfoTV.text.toString(),
                    pictureURI.toString()
                )
            }
        }
    }
}