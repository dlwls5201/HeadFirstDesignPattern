package chapter09

import chapter09.Problem04.*
import java.util.*
import kotlin.collections.ArrayList

/**
 *  객체 마을 카페 메뉴를 합쳐봅시다.
 */
class Problem04 {

    data class MenuItem(
        val name: String = "",
        val description: String = "",
        val vegetarian: Boolean = false,
        val price: Double = 0.0
    )

    interface Menu {

        fun createIterator(): Iterator<MenuItem>
    }

    class PancakeHouseMenu : Menu {

        private val menuItems: ArrayList<MenuItem> = arrayListOf()

        init {

            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }

        override fun createIterator() = menuItems.iterator()
    }

    class DinerMenu : Menu {

        private val menuItems: Array<MenuItem> = Array(6) { MenuItem() }

        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
        
        override fun createIterator() = menuItems.iterator()
    }

    class CafeMenu {

        private val menuItems = Hashtable<String, MenuItem>()

        init {

            addItem("음식 1", "채식주의자용", true, 2.99)
            addItem("음식 2", "혼합", false, 1.99)
            addItem("음식 3", "육식", false, 0.99)
        }

        fun addItem(name: String, description: String, vegetarian: Boolean, price: Double) {
            val menuItem = MenuItem(name, description, vegetarian, price)
            menuItems[name] = menuItem
        }

        fun getItem() = menuItems
    }


    class Waitress(var pancakeHouseMenu: Menu, var dinerMenu: Menu) {

        fun printMenu() {

            val pancakeHouseMenu = pancakeHouseMenu.createIterator()

            val dinerMenu = dinerMenu.createIterator()

            println("-- 아침메뉴 --")
            printMenu(pancakeHouseMenu)

            println("-- 저녁메뉴 --")
            printMenu(dinerMenu)
        }

        private fun printMenu(iterator: Iterator<MenuItem>) {
            while (iterator.hasNext()) {
                val menuItem = iterator.next()
                println(menuItem.name)
            }
        }
    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu()

    val dinerMenu = DinerMenu()

    Waitress(pancakeHouseMenu, dinerMenu).printMenu()
}