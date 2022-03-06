package fr.rigonij.resto.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import fr.rigonij.resto.MainActivity
import fr.rigonij.resto.R
import fr.rigonij.resto.RestoModel
import fr.rigonij.resto.RestoRepository
import java.util.*

class AddRestoFragment(
    private val context: MainActivity
) : Fragment() {

    private var uploadedImage:ImageView? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_resto, container, false)

        // recup uploadedImage pour lui associer son compsant
        uploadedImage = view.findViewById(R.id.prewiew_image)

        // on recup le boutton pour charger l'image
        val pickupImageButton = view.findViewById<Button>(R.id.upload_button)

        // l'orsqu'on clique sur le boutton, cela ouvre les image du tel

        pickupImageButton.setOnClickListener { pickUpImage() }

        // on recup le bouton confirmer
        val confirmButton = view.findViewById<Button>(R.id.confirm_button)
        confirmButton.setOnClickListener { sendForm(view) }
        return view
    }

    // sert a recup les champs
    private fun sendForm(view: View) {
        val repo = RestoRepository()
        val restoName = view.findViewById<EditText>(R.id.name_input).text.toString() // .text.toString sert a avoir le contenu
        val restoDescription = view.findViewById<EditText>(R.id.description_input).text.toString()
        val restoEmplacement = view.findViewById<EditText>(R.id.emplacement_input).text.toString()

        // creer un nouvel objet RestoModel
        val resto = RestoModel(
            UUID.randomUUID().toString(),
            restoName,
            restoDescription,
            restoEmplacement
        )
        // envoyer en base de donnée
        repo.insertResto(resto)
    }

    private fun pickUpImage() {
        val intent = Intent()
        intent.type = "image/"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1)
    }
 // on recolter ces info. On recup le requestCode, le resultCode et les donnée
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
     if(requestCode == 1 && resultCode == Activity.RESULT_OK) // sert si l'utilisateur veut revenir en arriere meme sans avoir pris une image
         // on verif si les données sont nulle
             if (data == null || data.data == null) return

     // recup l'image selectionner
     val selectedImage = data?.data

     // mettre a jour de l'apercu de l'image
     uploadedImage?.setImageURI(selectedImage)

    }
}