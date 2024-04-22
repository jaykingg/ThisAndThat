package org.example.thisandthat

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ThisAndThatApplication

fun main(args: Array<String>) {
    runApplication<ThisAndThatApplication>(*args)

    // == , hashcode, equals

    // 확장 함수 테스트

    // 영역 함수 테스트
//    ScopeFunctions().doFunctions() {
//        takeIfTest()
//    }
}
