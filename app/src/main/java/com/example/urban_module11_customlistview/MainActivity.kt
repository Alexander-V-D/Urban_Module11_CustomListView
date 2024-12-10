package com.example.urban_module11_customlistview

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.io.IOException

const val GALLERY_REQUEST = 145

class MainActivity : AppCompatActivity() {

    private lateinit var productsLV: ListView
    private lateinit var productPictureIV: ImageView
    private lateinit var productNameET: EditText
    private lateinit var productPriceET: EditText
    private lateinit var addBTN: Button
    private var bitmap: Bitmap? = null
    private val products = mutableListOf<Product>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        productPictureIV.setOnClickListener {
            val photoPickerIntent = Intent(Intent.ACTION_PICK)
            photoPickerIntent.type = "image/*"
            startActivityForResult(photoPickerIntent, GALLERY_REQUEST)
        }

        addBTN.setOnClickListener {
            addProduct()

            val listAdapter = ListAdapter(this, products)
            productsLV.adapter = listAdapter
            listAdapter.notifyDataSetChanged()

            clearEditFields()
        }
    }

    private fun addProduct() {
        val productName = productNameET.text.toString()
        val productPrice = productPriceET.text.toString()
        val productPicture = bitmap
        val product = Product(productName, productPrice, productPicture)
        products.add(product)
    }

    private fun clearEditFields() {
        productNameET.text.clear()
        productPriceET.text.clear()
        productPictureIV.setImageResource(R.drawable.ic_add)
    }

    private fun init() {
        productsLV = findViewById(R.id.productsLV)
        productPictureIV = findViewById(R.id.productPictureIV)
        productNameET = findViewById(R.id.productNameET)
        productPriceET = findViewById(R.id.productPriceET)
        addBTN = findViewById(R.id.addBTN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        productPictureIV = findViewById(R.id.productPictureIV)
        when(requestCode) {
            GALLERY_REQUEST -> {
                val selectedImage: Uri? = data?.data
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImage)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
                productPictureIV.setImageBitmap(bitmap)
            }
        }
    }
}