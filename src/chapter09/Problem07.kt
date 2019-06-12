package chapter09

import chapter09.Problem07.*
import java.io.UnsupportedEncodingException
import java.lang.UnsupportedOperationException
import java.util.*
import kotlin.collections.ArrayList

/**
 *  객체마을 식당메뉴에 디저트 서브메뉴를 추가해야 합니다. -> Composite 패턴으로 새로 작성해보겠습니다.
 */
class Problem07 {

    abstract class MenuComponent {

        open fun add(menuComponent: MenuComponent) {
            throw UnsupportedOperationException()
        }

        open fun remove(menuComponent: MenuComponent) {
            throw UnsupportedOperationException()
        }

        open fun getChild(i: Int): MenuComponent {
            throw UnsupportedEncodingException()
        }

        /*fun getName(): String {
            throw UnsupportedEncodingException()
        }

        fun getDescription(): String {
            throw UnsupportedEncodingException()
        }

        fun getPrice(): Double {
            throw UnsupportedEncodingException()
        }*/

        open fun isVegetarian(): Boolean {
            throw UnsupportedEncodingException()
        }

        open fun print() {
            throw UnsupportedEncodingException()
        }

        open fun createIterator(): Iterator<MenuComponent> {
            throw UnsupportedEncodingException()
        }
    }

    class Menu(
        val name: String,
        val description: String
    ): MenuComponent() {

        private val menuComponents = ArrayList<MenuComponent>()

        override fun add(menuComponent: MenuComponent) {
            menuComponents.add(menuComponent)
        }

        override fun remove(menuComponent: MenuComponent) {
            menuComponents.remove(menuComponent)
        }

        override fun getChild(i: Int): MenuComponent {
            return menuComponents[i]
        }

        override fun print() {
            print("\n $name")
            println(", $description")
            println("-----------")

            val iterator = menuComponents.iterator()
            while (iterator.hasNext()) {
                val menuComponent = iterator.next()
                menuComponent.print()
            }

        }
    }


    class MenuItem(
        val name: String,
        val description: String,
        val vegetarian: Boolean,
        val price: Double
    ): MenuComponent() {

        override fun isVegetarian(): Boolean {
            return vegetarian
        }

        override fun print() {
            print(" $name")
            if(isVegetarian()) {
                print("(v)")
            }

            println(" , $price")
            println(" -- $description")
        }

        override fun createIterator(): Iterator<MenuComponent> {
            return NullIterator()
        }
    }

    class NullIterator : Iterator<MenuComponent> {

        override fun hasNext(): Boolean {
            return false
        }

        override fun next(): MenuComponent {
            return object : MenuComponent() {}
        }

    }

    class Waitress(
        private val allMenus: MenuComponent
    ) {

        fun printMenu() {
            allMenus.print()
        }

        fun printVegetarianMenu() {
            val iterator = allMenus.createIterator()
            println("VEGETARIAN MENU")
            while (iterator.hasNext()) {
                val menuComponent = iterator.next()
                if(menuComponent.isVegetarian()) menuComponent.print()
            }
        }
    }
}

fun main() {

    val pancakeHouseMenu = Menu("팬케이트하우스 메뉴","아침 메뉴")
    val dinerMenu = Menu("객체마을식당 메뉴","점심 메뉴")
    val cafeMenu = Menu("카페 메뉴","저녁 메뉴")
    val dessertMenu = Menu("디저트 메뉴","디저트를 즐겨 보세요!")

    val allMenus = Menu("전체 메뉴","전체 메뉴")

    allMenus.add(pancakeHouseMenu)
    allMenus.add(dinerMenu)
    allMenus.add(cafeMenu)

    dinerMenu.add(
        MenuItem("파스타","미라나라 소스 스파게티, 효모빵도 드립니다.", true, 3.89))

    dinerMenu.add(dessertMenu)

    dessertMenu.add(
        MenuItem("애플 파이", "바삭바삭한 크러스트에 바닐라 아이스크림이 얹혀 있는 애플 파이", true, 1.59))

    Waitress(allMenus).printMenu()
}