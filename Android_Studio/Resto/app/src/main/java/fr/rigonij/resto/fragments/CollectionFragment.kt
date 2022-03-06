package fr.rigonij.resto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.rigonij.resto.MainActivity
import fr.rigonij.resto.R
import fr.rigonij.resto.RestoRepository.Singleton.restoList
import fr.rigonij.resto.adapter.RestoAdapter
import fr.rigonij.resto.adapter.RestoItemDecoration


class CollectionFragment(
    private val context: MainActivity
) : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_collection, container, false )

        // on recup notre recycler view
        val collectionRecyclerView = view.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView.adapter = RestoAdapter(context, restoList.filter { it.liked }, R.layout.item_vertical_resto)
        collectionRecyclerView.layoutManager = LinearLayoutManager(context)
        collectionRecyclerView.addItemDecoration(RestoItemDecoration()) // cela va nous permettre l'espacement entre les item

        return view
    }
}