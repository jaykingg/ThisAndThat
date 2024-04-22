package org.example.thisandthat

import org.example.thisandthat.kotlinpractice.functions.ScopeFunctions
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ThisAndThatApplication

fun main(args: Array<String>) {
    //runApplication<ThisAndThatApplication>(*args)    // == , hashcode, equals

    // 확장 함수

    // 영역 함수
    ScopeFunctions().doFunctions() {
        takeIfTest()
    }
}
