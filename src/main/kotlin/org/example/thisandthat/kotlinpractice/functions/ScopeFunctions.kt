package org.example.thisandthat.kotlinpractice.functions

import org.example.thisandthat.kotlinpractice.domain.Gender
import org.example.thisandthat.kotlinpractice.domain.Person
import java.time.LocalDate

class ScopeFunctions {

    fun doFunctions(configure: ScopeFunctions.() -> Unit) {
        configure()
    }

    /** <Apply>
     *  <T,R> : T
     *  자기 자신을 다시 리턴
     *  객체 초기화 및 변경에 주로 사용
     */
    fun applyTest() {
        val person = Person(
            name = "jin",
            birth = LocalDate.of(2024, 4, 19),
            gender = Gender.MALE,
            height = 183,
            weight = 85
        )

        person.apply {
            name = "santa"
        }

        println(person)
    }

    /** <Let>
     * 블럭을 처리하고 결과를(마지막줄) 리턴
     * non-null 처리에 유용, 기본값 세팅 가능
     * 객체의 값을 변경할 때 사용
     */
    fun letTest() {
        val person = Person(
            name = "jin",
            birth = LocalDate.of(2024, 4, 19),
            gender = Gender.MALE,
            height = 183,
            weight = 85
        )

        val result = person?.let {
            it.name = "santa"
            it.name
        } ?: throw Exception("test")

        println(result)
    }

    /** <Also>
     * 블러글 처리하고 자기자신을 리턴
     * 객체의 속성을 변경하지 않거나 변경하지 않고 사용할때 사용
     */
    fun alsoTest() {
        val person = Person(
            name = "jin",
            birth = LocalDate.of(2024, 4, 19),
            gender = Gender.MALE,
            height = 183,
            weight = 85
        )

        val result = person.also {
            it.name = "also_santa"
        }

        println(result)
    }

    /** <Run>
     * 블럭을 처리하고 람다의 결과(마지막)을 리턴함
     * 값을 계산하거나, 블럭을 제한할 때 사용
     */
    fun runTest() {
        val person = Person(
            name = "jin",
            birth = LocalDate.of(2024, 4, 19),
            gender = Gender.MALE,
            height = 183,
            weight = 85
        )

        val birth = person.run {
            this.name = "santa"
            this.birth
        }
        println(birth)
        println(person)

    }

    /** <.Map>
     * Array 값에 대한 처리한 뒤 List 리턴
     */
    fun mapTest() {
        val array: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = array.map {
            it * 10
        }
        println(result)
    }

    /** <Filter>
     *  조건문을 만족한 Array 원소들을 List 로 리턴
     */
    fun filterTest() {
        val array: IntArray = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val result = array.filter {
            it < 5
        }
        println(result)
    }

    /** <ReduceAndFold<>
     *  Collection 내 데이터를 모드 모으는 함수(SUM)
     *  Reduce 는 Collection 이 비어있으면 Exception 발생
     *  Fold 는 초기값 지정하여 그 값부터 계산 진행
     */
    fun reduceAndFold() {
        val array: IntArray = intArrayOf(7, 8, 9, 1, 30)
        val sumOfReduce = array.reduce { total, sum -> total + sum }
        println("reduce::$sumOfReduce")

        val sumOfFold = array.fold(10) { total, sum -> total + sum }
        println("fold::$sumOfFold")

    }

    /** <FlatMap>
     * Iterator 안에 여러 Iterator 를 1개의 Single Collection 으로 반환해준다고 이해하면 됨
     */
    fun flatMapTest() {
        val list = mutableListOf(mutableListOf("a", "b", "c"), mutableListOf("d", "e", "f"))
        val resultOfMap = list.map {
            it.toList()
        }
        println("Map::$resultOfMap")

        val resultOfFlatMap = list.flatMap {
            it.toList()
        }
        println("FlatMap::$resultOfFlatMap")


    }

    /** <GroupByAndGroupingByTest>
     *  GroupBy 각 요소들에 대해 블럭을 기준으로 Key 를 추출하며, Map<K, List<>> 로 반환한다.
     *  GroupingBy 각 요소들에 대해 블럭을 기준으로 Key 를 추출, 계산하여 Grouping<T,K> 로 반환
     */
    fun groupByAndGroupingByTest() {
        val list = mutableListOf("king", "queen", "prince", "princess", "load", "kingdom")
        val resultOfGroupBy = list.groupBy {
            it.length
        }
        println("GroupBy::$resultOfGroupBy")

        val resultOfGroupingBy = list.groupingBy {
            it.length
        }.eachCount()
        println("GroupingBy::$resultOfGroupingBy")
    }

    /** <Take>
     * 앞에서부터 파라미터의 수만큼 잘라서 리턴함
     */
    fun takeTest() {
        val array = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
        println(array.take(3))
        println(array.takeLast(3))
    }

    /** <TakeIf>
     * IF를 보다 간결하게 바꿀 수 있다
     */
    fun takeIfTest() {
        val person = Person(
            name = "jin",
            birth = LocalDate.of(2024, 4, 19),
            gender = Gender.MALE,
            height = 183,
            weight = 85
        )
        val status = true

        // 이전 코드
        if (person != null && status) {
            println("okay")
        }

        // 개선 코드
        person?.takeIf { status }?.apply { println("simply okay") }
    }
}