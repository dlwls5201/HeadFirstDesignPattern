package chapter09

import chapter09.Problem03.*

/**
 *  인터페이스를 개선해 봅시다.
 *
 *  PancakeHouseMenu 와 DinerMenu 클래스 에서는 Menu 인터페이스를 구현합니다.
 *  이렇게 함으로서 Waitress 의 매개변수를 추상화 할 수 있습니다.
 */
class Problem03 {

    data class MenuItem(
        val name: String = "",
        val description: String = "",
        val vegetarian: Boolean = false,
        val price: Double = 0.0
    )

    interface Menu {
        fun createIterator(): Iterator<MenuItem>
    }

    class PancakeHouseMenu(
        private val menuItems: ArrayList<MenuItem> = arrayListOf()
    ) : Menu {

        init {
            menuItems.add(MenuItem("펜케이크 세트 1", "세트 1", true, 2.99))
            menuItems.add(MenuItem("펜케이크 세트 2", "세트 2", true, 1.99))
            menuItems.add(MenuItem("펜케이크 세트 3", "세트 3", true, 0.99))
        }

        override fun createIterator() = menuItems.iterator()
    }

    class DinerMenu(
        private val menuItems: Array<MenuItem> = Array(6) { MenuItem() }
    ) : Menu {

        init {

            menuItems[0] = MenuItem("음식 1", "채식주의자용", true, 2.99)
            menuItems[1] = MenuItem("음식 2", "혼합", false, 1.99)
            menuItems[2] = MenuItem("음식 3", "육식", false, 0.99)
        }
        
        override fun createIterator() = menuItems.iterator()
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