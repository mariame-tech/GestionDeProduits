package com.example.myapplication

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf
import java.lang.Exception
import java.sql.Blob
import kotlin.collections.ArrayList

class SQliteHelper(mContext:Context):SQLiteOpenHelper(mContext, DB_NAME, null, DB_VERSION){

    override fun onCreate(db: SQLiteDatabase?) {
        //creation des tables
        val createTableProduit="""
            CREATE TABLE $DB_TABLE_NAME(
            $ID integer primary key,
           $NOM varchar(100),
           $DESCRIPTION TEXT,
            $PRIX double,
            $IMAGE blob
            )
        """.trimIndent()
       db?.execSQL(createTableProduit)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //suppression des anciennes tables et recréation des nouvelles
        db?.execSQL("DROP TABLE IF EXISTS $DB_TABLE_NAME")
        onCreate(db)

    }
    fun addProduit(produit:Produit): Boolean {
        val db=writableDatabase
        val values=ContentValues()
        values.put(NOM,produit.nom)
        values.put(DESCRIPTION,produit.description)
        values.put(PRIX,produit.prix)
        values.put(IMAGE,produit.imagesBlob)
        val result=db.insert(DB_TABLE_NAME,null,values)
        db.close()
        return result != -1L

    }
    fun findProduits():ArrayList<Produit>{
        val produits= ArrayList<Produit>()
        val db= readableDatabase
        val selectQuery="SELECT * FROM $DB_TABLE_NAME"
        val cursor= db.rawQuery(selectQuery,null)
        if(cursor != null){
            if(cursor.moveToFirst()){
                do {
                    val id=cursor.getInt(cursor.getColumnIndexOrThrow(ID))
                    val nom=cursor.getString(cursor.getColumnIndexOrThrow(NOM))
                    val description=cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION))
                    val prix=cursor.getDouble(cursor.getColumnIndexOrThrow(PRIX))
                    val image=cursor.getBlob(cursor.getColumnIndexOrThrow(IMAGE))
                    val produit=Produit(id,nom, description.toString(), prix.toString(),image)
                    produits.add(produit)

                }while (cursor.moveToNext())
            }
        }

        db.close()

        return produits
    }

    fun deleteProduits(id: Int) : Boolean{
        val db= writableDatabase

        val rawDeleted= db.delete(DB_TABLE_NAME,"id=?", arrayOf(id.toString()))
        return rawDeleted>0
    }

    companion object{
       private val DB_NAME="Produit"
       private val DB_VERSION=1
        private val DB_TABLE_NAME="TBL_produit"
        private val ID="id"
        private val NOM="nom"
        private val DESCRIPTION="description"
        private val PRIX="prix"
        private val IMAGE="imagesBlob"

    }


}