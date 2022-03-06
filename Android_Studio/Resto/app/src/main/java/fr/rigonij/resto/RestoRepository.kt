package fr.rigonij.resto

import android.net.Uri
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.rigonij.resto.RestoRepository.Singleton.databaseRef
import fr.rigonij.resto.RestoRepository.Singleton.restoList
import fr.rigonij.resto.RestoRepository.Singleton.storageRef
import java.net.URI
import java.util.*
import kotlin.coroutines.Continuation

class RestoRepository {

    object Singleton{
        // donner le lien pour acceder au bucket
        private val BUCKET_URL: String = "gs://resto-9f24a.appspot.com"

        // on veut se co a notre espace de stockage
        val storageRef = FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)
        // se co a la ref
        val databaseRef = FirebaseDatabase.getInstance().getReference("resto")
        // creer une liste qui va contenir nos resto
        val restoList = arrayListOf<RestoModel>()
    }

    fun updateData(callback: () -> Unit) {
        // absorber les donnée qu'on a recup dans la databaseRef -> liste de resto
        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // retire les anciennes
                restoList.clear()
                // recolter la liste
                for (ds in snapshot.children) {
                    // construire un objet resto
                    val resto = ds.getValue(RestoModel::class.java)

                    // verif si la plnate n'est pas null
                    if (resto != null) {
                        // ajoute le resto a notre liste
                        restoList.add(resto)
                    }
                }
                // actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {}

        })
    }

    // mettre a jour un objet resto en bdd ( base de donnée )
    fun updateResto(resto: RestoModel) = databaseRef.child(resto.id).setValue(resto) // setValue met a jour la valeur actuel

    // inserer un nv resto en bdd
    fun insertResto(resto: RestoModel) = databaseRef.child(resto.id).setValue(resto)
    // on supp le resto de la base
    fun deleteResto(resto: RestoModel) = databaseRef.child(resto.id).removeValue()
}