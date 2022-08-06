package com.example.myapplication

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.io.ByteArrayOutputStream



class MainActivity2 : AppCompatActivity() {
     lateinit var nom:EditText
     lateinit var description:EditText
     lateinit var prix:EditText
     lateinit var imageUrl: ImageView
     lateinit var btnAdd:Button
     lateinit var db:SQliteHelper
    var bitmap: Bitmap? = null
    lateinit var btnView:Button
/* */
   lateinit var sqliteHelper:SQliteHelper
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main2)
    db= SQliteHelper(this)
    btnAdd=findViewById(R.id.btnAdd)
    imageUrl=findViewById(R.id.image_produit)
    prix=findViewById(R.id.prix)
    nom=findViewById(R.id.nom)
    description=findViewById(R.id.description)
//Ouverture de galerie
    val gallerylauncher= registerForActivityResult(ActivityResultContracts.GetContent()){ data ->
        val inputStream=contentResolver.openInputStream(data)
       bitmap=BitmapFactory.decodeStream(inputStream)
        imageUrl.setImageBitmap(bitmap)
    }
    imageUrl.setOnClickListener(){
        //ouvrir gallery
        gallerylauncher.launch("image/*")
    }
    //Ajout image
    btnAdd.setOnClickListener(){
        val intent=Intent(this,MainActivity4::class.java)
        //récupérer les données de la base
        val nom=nom.text.toString()
        val description=description.text.toString()
        val prix=prix.text.toString()

        if(nom.isEmpty()||description.isEmpty()||prix.isEmpty()||bitmap==null){
            Toast.makeText(this,"Remplir tous les champs",Toast.LENGTH_SHORT).show()
        }else{
            val imagesBlob:ByteArray=getBytes(bitmap)
            val produit=Produit(nom,description,prix, imagesBlob)
            db.addProduit(produit)
            //supprimer le formulaire
            nom.setText("")
            description.setText("")
            prix.setText("")
            bitmap = null
            startActivity(intent)
            finish()
        }


    }

}//fin de onCreate

 fun getBytes(bitmap: Bitmap?): ByteArray {
     val stream=ByteArrayOutputStream()
     bitmap?.compress(Bitmap.CompressFormat.JPEG,0,stream)
     return stream.toByteArray()

    }


}

private fun String.setText(s: String) {

}
