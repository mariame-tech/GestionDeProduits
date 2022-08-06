package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.io.ByteArrayOutputStream

class MainActivity4 : AppCompatActivity() {
    lateinit var listProduits:ListView
    var bitmap: Bitmap? = null
    var produitsArray=ArrayList<Produit>()
    lateinit var db:SQliteHelper
    lateinit var adapter: ProduitsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
       db = SQliteHelper(this)
        val btnAjout = findViewById<Button>(R.id.btnAjout)
        btnAjout.setOnClickListener() {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
            finish()
        }
       listProduits = findViewById(R.id.listproduits)
        produitsArray = db.findProduits()
         adapter = ProduitsAdapter(this, R.layout.produit_item, produitsArray)
        listProduits.adapter = adapter


        listProduits.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, position, id ->
            val clickedProduit = produitsArray[position]
            Intent(this, DetailsProduitActivity::class.java).also {
                it.putExtra("nom", clickedProduit.nom)
                it.putExtra("description", clickedProduit.description)
                it.putExtra("prix", clickedProduit.prix)
                //decompresser image
                it.putExtra("image", clickedProduit.imagesBlob)
                startActivity(it)
            }

        })

    }
}