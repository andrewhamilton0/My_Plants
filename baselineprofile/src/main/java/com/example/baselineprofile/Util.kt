package com.example.baselineprofile

import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Direction
import androidx.test.uiautomator.Until

fun MacrobenchmarkScope.scrollDownAndClickPlant(){
    val list = device.findObject(By.res("plant_list"))
    val addButton = device.findObject(By.res("add_button"))

    list?.fling(Direction.DOWN)
    addButton?.click()
    device.wait(Until.hasObject(By.text("Second Page")), 5000)


}