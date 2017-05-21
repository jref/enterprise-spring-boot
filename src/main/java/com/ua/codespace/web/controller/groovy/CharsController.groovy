package com.ua.codespace.web.controller.groovy

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CharsController {

    @RequestMapping("/chars/{n}/{m}")
    def chars(@PathVariable n, @PathVariable m) {
        (n..m).join(" ")
    }

}