package chapter09

import chapter09.Problem09.*

class Problem09 {

    data class MenuItem(
        val name: String,
        val description: String,
        val vegetarian: Boolean,
        val price: Double
    )

    class PancakeHouseMenu(
        val menuItems: ArrayList<MenuItem> = arrayListOf()
    ) {
        init {

            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }
    }

    class DinerMenu(
       val menuItems: Array<MenuItem> = Array(6) { MenuItem("", "", false, 0.0) }
    ) {
        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu().menuItems

    val dinerMenu = DinerMenu().menuItems

    for(menu in pancakeHouseMenu) {
        println(menu.name)
    }

    for(menu in dinerMenu) {
        println(menu.name)
    }
}