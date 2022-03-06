package fr.rigonij.resto.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.rigonij.resto.*

// sert a adapter pour chaque resto sont equivalent en image
class RestoAdapter(
    val context: MainActivity,
    private val restoList: List<RestoModel>,
    private val layoutId: Int
    ) : RecyclerView.Adapter<RestoAdapter.ViewHolder>() {

    // boite pour ranger tout les composants a controler
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val restoImage = view.findViewById<ImageView>(R.id.image_item)
        val restoName:TextView? = view.findViewById(R.id.name_item)
        val restoDescription:TextView? = view.findViewById(R.id.description_item)
        val starIcon = view.findViewById<ImageView>(R.id.star_icon)
    }

    // onCreateViewHolder sert a injecter notre layout de item_horitonal_resto
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(layoutId, parent, false)

        return ViewHolder(view)
    }

    // onBindViewHolder sert a mettre a jour chaque model avec le resto en question
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // recupere les info du resto
        val currentResto = restoList[position]

        // recup le repo
        val repo = RestoRepository()

        // utiliser Glide pour recupere l'image a partir de son lien et venir l'ajouter a notre composant
        // le context est une base de donnée interne qui va contenir toute les infos contextuelle de l'appli comme le numero de version par exemple
        Glide.with(context).load(Uri.parse(currentResto.imageUrl)).into(holder.restoImage)

        // mettre a jour le nom du resto
        holder.restoName?.text = currentResto.name

        // mettre a jour la description du resto
        holder.restoDescription?.text = currentResto.description

        // verifier si le resto a été liké
        if (currentResto.liked){
            holder.starIcon.setImageResource(R.drawable.ic_like)
        }
        else{
            holder.starIcon.setImageResource(R.drawable.ic_unlike)
        }

        // rajouter une interaction sur cette etoile
        holder.starIcon.setOnClickListener {
            // inverser si le bouton est like ou non
            currentResto.liked = !currentResto.liked
            //mettre a jour l'objet resto
            repo.updateResto(currentResto)
        }

        // interaction lors du clique sur un resto
        holder.itemView.setOnClickListener {
            // afficher la popup
            RestoPopup(this, currentResto).show()
        }
    }

    // getItemCount sert a renvoyer combien d'item on veut afficher dynamiquement
    override fun getItemCount(): Int = restoList.size // cela va compter le nombre de plante qu'on a dans la liste

}