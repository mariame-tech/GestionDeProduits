package com.example.myapplication

data class Produit(
    var nom:String,
    var description:String,
    var prix: String,
    var imagesBlob: ByteArray

){
    var id = -1
    constructor(id:Int,nom: String,description: String,prix: String,imagesBlob: ByteArray) : this(nom, description, prix, imagesBlob){
        this.id=id
    }
    }
