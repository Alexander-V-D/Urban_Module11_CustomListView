package com.example.urban_module11_customlistview

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.io.IOException

const val GALLERY_REQUEST = 145

class MainActivity : AppCompatActivity() {

    private lateinit var productsLV: ListView
    private lateinit var productPictureIV: ImageView
    private lateinit var productNameET: EditText
    private lateinit var productPriceET: EditText
    private lateinit var productInfoET: EditText
    private lateinit var addBTN: Button
    private var pictureURI: Uri? = null
    private val products = mutableListOf<Product>()
    private lateinit var mainToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainToolbar = findViewById(R.id.mainToolbar)
        title = getString(R.string.app_title)
        setSupportActionBar(mainToolbar)
        mainToolbar.overflowIcon?.setTint(Color.WHITE)

        init()
        productPictureIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        val listAdapter = ListAdapter(this, products)
        addBTN.setOnClickListener {
            addProduct()

            productsLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()
            clearEditFields()
        }

        productsLV.onItemClickListener =
            AdapterView.OnItemClickListener { adapterView, view, position, id ->
                val product = listAdapter.getItem(position)
                val intent = Intent(this, ProductDetailsActivity::class.java)
                intent.putExtra(Product::class.java.simpleName, product)
                startActivity(intent)
            }
    }

    private fun addProduct() {
        val productName = productNameET.text.toString()
        val productPrice = productPriceET.text.toString()
        val productPicture = pictureURI.toString()
        val productInfo = productInfoET.text.toString()
        val product = Product(productName, productPrice, productInfo, productPicture)
        products.add(product)
    }

    private fun clearEditFields() {
        productNameET.text.clear()
        productPriceET.text.clear()
        productPictureIV.setImageResource(R.drawable.ic_add)
        productInfoET.text.clear()
    }

    private fun init() {
        productsLV = findViewById(R.id.productsLV)
        productPictureIV = findViewById(R.id.productPictureIV)
        productNameET = findViewById(R.id.productNameET)
        productPriceET = findViewById(R.id.productPriceET)
        productInfoET = findViewById(R.id.productInfoET)
        addBTN = findViewById(R.id.addBTN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        productPictureIV = findViewById(R.id.productPictureIV)
        when(requestCode) {
            GALLERY_REQUEST -> {
                pictureURI = data?.data
                productPictureIV.setImageURI(pictureURI)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }
}