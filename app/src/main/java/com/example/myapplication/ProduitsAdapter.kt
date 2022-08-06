package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import java.io.ByteArrayOutputStream


class ProduitsAdapter(
    var mContext:Context,
    var resource:Int,
    var values:ArrayList<Produit>

):ArrayAdapter<Produit>(mContext,resource,values) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val produit=values[position]
        val itemView=LayoutInflater.from(mContext).inflate(resource,parent,false)
        val tvNom=itemView.findViewById<TextView>(R.id.tvNom)
        val tvPrix=itemView.findViewById<TextView>(R.id.tvPrix)
        val imageProduit=itemView.findViewById<ImageView>(R.id.image)
        val tvDescription=itemView.findViewById<TextView>(R.id.tvDescription)

        val imageShowPopup=itemView.findViewById<ImageView>(R.id.imageShowPopup)
        imageShowPopup.setOnClickListener(){
            val db=SQliteHelper(mContext)
            val popupMenu=PopupMenu(mContext,imageShowPopup)
            popupMenu.menuInflater.inflate(R.menu.popup_menu,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.itemShow ->{
                        Intent(mContext, DetailsProduitActivity::class.java).also{
                        it.putExtra("nom",produit.nom)
                            it.putExtra("prix",produit.prix)
                            it.putExtra("description",produit.description)
                            it.putExtra("image",produit.imagesBlob)
                            mContext.startActivity(it)
                    }
                }
                    R.id.itemDelete->{
                        val resultDelete=db.deleteProduits(produit.id)
                        if(resultDelete){
                            values.removeAt(position)
                            notifyDataSetChanged()
                        }else{
                            Toast.makeText(mContext,"Erreur de suppression",Toast.LENGTH_SHORT).show()
                        }
                    }
                    }
                    true

            }
            popupMenu.show()
        }

        tvNom.text= produit.nom
        tvPrix.text=produit.prix
        tvDescription.text=produit.description

        val bitmap= Companion.getBitmap(produit.imagesBlob)
        imageProduit.setImageBitmap(bitmap)
        return itemView
    }

    companion object {
        fun getBitmap(byteArray: ByteArray) : Bitmap{
            val bitmap=BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)
            return bitmap
        }
    }
}