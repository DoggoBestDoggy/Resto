package fr.rigonij.resto

class RestoModel(
    val id: String = "plant0",
    val name: String = "KFC",
    val description: String = "C'est bon, surtout le mardi",
    val imageUrl: String = "http://cdn.pixabay.com/photo/2016/08/06/13/40/kfc-1574389_1280.jpg",
    val type: String = "FastFood",
    var liked: Boolean = false
)