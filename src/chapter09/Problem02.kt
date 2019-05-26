package chapter09

import chapter09.Problem02.*

class Problem02 {

    interface Iterator<T> {

        fun hasNext(): Boolean

        fun next(): T
    }

    data class MenuItem(
        val name: String,
        val description: String,
        val vegetarian: Boolean,
        val price: Double
    )

    class PancakeHouseMenu(
        val menuItems: ArrayList<MenuItem?> = arrayListOf()
    ) {
        init {

            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }

       fun createIterator() = PancakeHouseMenuIterator(menuItems)
    }


    class PancakeHouseMenuIterator(
        private val menuItems: ArrayList<MenuItem?>
    ): Iterator<MenuItem> {

        private var position = 0

        override fun next(): MenuItem {

            val menuItem = menuItems[position]
            position++
            return menuItem!!
        }

        override fun hasNext(): Boolean {
            return !(position >= menuItems.size || menuItems[position] == null)
        }
    }


    class DinerMenu(
       val menuItems: Array<MenuItem?> = Array(6) { null }
    ) {

        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
        
        fun createIterator() = DinerMenuIterator(menuItems)
    }

    class DinerMenuIterator(
        private val menuItems: Array<MenuItem?>
    ) : Iterator<MenuItem> {

        private var position = 0

        override fun next(): MenuItem {

            val menuItem = menuItems[position]
            position++
            return menuItem!!
        }

        override fun hasNext(): Boolean {
            return !(position >= menuItems.size || menuItems[position] == null)
        }

    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu().createIterator()

    val dinerMenu = DinerMenu().createIterator()

    while (pancakeHouseMenu.hasNext()) {
        val menuItem = pancakeHouseMenu.next()
        println(menuItem.name)
    }

    while (dinerMenu.hasNext()) {
        val menuItem = pancakeHouseMenu.next()
        println(menuItem.name)
    }
}