# GestionDeProduits
Projet android en kotlin et sqlite
Cette application a été faite en Kotlin avec une base de données SQlite.
Structure
Pour le projet que nous avons créé une application en Kotlin composée de 7 classes, 5 fichiers xml pour l’affichage. Par la suite nous les éléments d’implémentation que nous avons choisi d’intégrer pour la réalisation de l’application.
Tout d’abord les classes que nous avons :
•	MainActivity : qui est la page d’entrée de l’application avec un bouton Visitez notre site qui permet d’accéder à la page d’accueil où on a liste des produits (avec un fichier xml activity_main)
•	MainActivity4 : page qui permet d’afficher la liste des produits avec un bouton d’ajout ADD PRODUCT et aussi donner la possibilité d’ajouter, de modifier ou de supprimer des produits à partir d’un popup menu pour chaque produit (avec un fichier xml activity_main4)
•	MainActivity2 : Page d’ajout d’un produit avec un bouton ADD qui permet d’enregistrer un produit et ajouter dans la page d’accueil (avec un fichier activity_main2)
•	DetailsProduitActivity : page qui permet d’afficher les informations sur un produit (avec un fichier xml   activity_details_produits)
•	Produit : représentant la classe des produits et donc contenant les données qui seront obtenues pour chaque produit 
•	ProduitsAdapter : c’est à travers cette classe que l’on gère l’affichage de la vue de notre liste de produits, de la fonction de mise à jour d’un produit (modification) et la suppression.
•	SQLiteHelper : cette classe est celle qui nous permet d’établir notre base de données conformément à la gestion de produits.
•	On a également un fichier produit_item qui permet de définir la structure d’un seul produit.
