package chapter09

import chapter09.Problem09.*

class Problem09 {

    data class MenuItem(
        val name: String,
        val description: String,
        val vegetarian: Boolean,
        val price: Double
    )

    class PancakeHouseMenu {

        private val menuItems: ArrayList<MenuItem> = arrayListOf()

        init {
            addItem("펜케이크 세트 1", "세트 1", true, 2.99)
            addItem("펜케이크 세트 2", "세트 2", true, 1.99)
            addItem("펜케이크 세트 3", "세트 3", true, 0.99)
        }

        fun addItem(name: String, description: String, vegetarian: Boolean, price: Double) {
            val menuItem = MenuItem(name, description, vegetarian, price)
            menuItems.add(menuItem)
        }

        fun getItem() = menuItems
    }

    class DinerMenu {

        private val MAX_ITEMS = 6
        private val menuItems: Array<MenuItem?> = Array(MAX_ITEMS) { null }

        private var numberOfItems = 0

        init {
            addItem("음식 1", "채식주의자용", true, 2.99)
            addItem("음식 2", "혼합", false, 1.99)
            addItem("음식 3", "육식", false, 0.99)
        }

        fun addItem(name: String, description: String, vegetarian: Boolean, price: Double) {
            val menuItem = MenuItem(name, description, vegetarian, price)

            if(numberOfItems >= MAX_ITEMS) {
                 println("죄송합니다. 메뉴가 꽉 찼습니다.")
            } else {
                menuItems[numberOfItems] = menuItem
                numberOfItems++
            }

        }

        fun getItem() = menuItems
    }

    /**
     *  ArrayList 와 Array 를 검색 하기 위해서는 별도의 printMenu()가 필요하다.
     */
    class Waitress(
        private val pancakeHouseMenu: PancakeHouseMenu,
        private val dinerMenu: DinerMenu) {

        fun printMenu() {

            val pancakeHouseMenu = pancakeHouseMenu.getItem()

            val dinerMenu = dinerMenu.getItem()

            println("-- 팬케이크 하우스 메뉴 --")
            printMenu(pancakeHouseMenu)

            println("-- 저녁메뉴 --")
            printMenu(dinerMenu)

        }

        private fun printMenu(arrayList: ArrayList<MenuItem>) {

            //ArrayList
            for(menu in arrayList) {
                println(menu)
            }
        }

        private fun printMenu(array: Array<MenuItem?>) {

            //Array
            for(menu in array) {
                println(menu)
            }
        }
    }
}

fun main() {

    val pancakeHouseMenu = PancakeHouseMenu()

    val dinerMenu = DinerMenu()

    Waitress(pancakeHouseMenu, dinerMenu).printMenu()

}