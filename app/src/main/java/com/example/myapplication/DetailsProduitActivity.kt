package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsProduitActivity : AppCompatActivity() {
    var bitmap: Bitmap? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_produit)
        val tvNom=findViewById<TextView>(R.id.tvNom)

        val tvPrix=findViewById<TextView>(R.id.tvPrix)
        val prix=intent.getStringExtra("prix")
        tvPrix.text=prix

        val tvDescription= findViewById<TextView>(R.id.tvDescription)
        val description=intent.getStringExtra("description")
        tvDescription.text=description
        //recuperer image
        val image=findViewById<ImageView>(R.id.image)
       // val bundle:Bundle?=intent.extras
        val imageBlob=intent.getByteArrayExtra("image")
        //image.setImageBitmap(bitmap)
        val bitmap= ProduitsAdapter.getBitmap(imageBlob as ByteArray)
        image.setImageBitmap(bitmap)


        val nom=intent.getStringExtra("nom")
        tvNom.text=nom

        supportActionBar!!.title=nom
    }
    fun getBitmap(byteArray: ByteArray) : Bitmap{
        val bitmap= BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
        return bitmap
    }
}

