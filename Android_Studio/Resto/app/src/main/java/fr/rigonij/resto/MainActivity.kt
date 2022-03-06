package fr.rigonij.resto

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.rigonij.resto.fragments.AddRestoFragment
import fr.rigonij.resto.fragments.CollectionFragment
import fr.rigonij.resto.fragments.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this), R.string.home_page_title)

        // importer la bottomnavigationview
        val navigationView = findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.home_page -> {
                    loadFragment(HomeFragment(this), R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.collection_page -> {
                    loadFragment(CollectionFragment(this), R.string.fav_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.add_resto_page -> {
                    loadFragment(AddRestoFragment(this), R.string.resto_add_page_title)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment, string: Int) {
        // charger notre RestoRepository
        val repo = RestoRepository()

        // actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text = resources.getString(string)

        // mettre a jour la liste de resto
        repo.updateData {
            // injetcer le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, fragment) // quand on va crée le homefragment, on va lui donnée l'activiter principale en parametre
            // par la suite le context va etre transfere a notre restoAdapter et celui-ci va recupere le context
            // pour le transmettre a glide pour qu'il puisse jouer son role
            transaction.addToBackStack(null) // sert a ne pas avoir de retour sur notre composant
            transaction.commit() // sert a envoyer les changements
        }
    }

}