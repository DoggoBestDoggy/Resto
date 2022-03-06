package fr.rigonij.resto.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.rigonij.resto.MainActivity
import fr.rigonij.resto.R
import fr.rigonij.resto.RestoModel
import fr.rigonij.resto.RestoRepository.Singleton.restoList
import fr.rigonij.resto.adapter.RestoAdapter
import fr.rigonij.resto.adapter.RestoItemDecoration

class HomeFragment(
    private val context: MainActivity
) : Fragment() {

    // le oncreateview est une fonction qui va contenir du code et qui se charger de pouvoir injecter notre layout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        // recupere le recyclerview
        val horizontalRecyclerView =view.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView.adapter = RestoAdapter(context, restoList.filter { !it.liked }, R.layout.item_horizontal_resto)

        // recupere le second recyclerview
        val verticalRecyclerView = view.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView.adapter = RestoAdapter(context, restoList, R.layout.item_vertical_resto)
        verticalRecyclerView.addItemDecoration(RestoItemDecoration())

        return view
    }
}