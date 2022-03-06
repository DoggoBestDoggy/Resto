package fr.rigonij.resto

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.rigonij.resto.adapter.RestoAdapter

class RestoPopup(
    private val adapter: RestoAdapter,
    private val currentResto: RestoModel
    ) : Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE) // requestWindow veut dire qu'on demande quelque chose, pour le coup on ne veut pas de titre sur notre fenetre
        setContentView(R.layout.popup_resto_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupStarButton()
    }

    private fun updateUnlike(button: ImageView) {
        if(currentResto.liked) {
            button.setImageResource(R.drawable.ic_like)
        }
        else {
            button.setImageResource(R.drawable.ic_unlike)
        }
    }

    private fun setupStarButton() {
        // on le recupere
        val unlikeButton = findViewById<ImageView>(R.id.unlike_button)
        updateUnlike(unlikeButton)

        // l'interaction
        unlikeButton.setOnClickListener {
            currentResto.liked = !currentResto.liked  // le point d'exclamation sert a donner l'inverse de la val courante
            val repo = RestoRepository()
            repo.updateResto(currentResto)
            updateUnlike(unlikeButton)
        }
    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener {
            // on supp le resto de la base de donnée
            val repo = RestoRepository()
            repo.deleteResto(currentResto)
            dismiss()
        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener {
            // sert a fermer la fenetre popup
            dismiss()
        }
    }

    private fun setupComponents() {
        // on actualise l'image du resto
        val restoImage = findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentResto.imageUrl)).into(restoImage)

        // on actualise le Nom du resto
        findViewById<TextView>(R.id.popup_resto_name).text = currentResto.name

        // on actualise la description du resto
        findViewById<TextView>(R.id.popup_resto_description_subtitle).text = currentResto.description

        // on actualise le type du resto
        findViewById<TextView>(R.id.popup_resto_type_subtitle).text = currentResto.type
    }

}