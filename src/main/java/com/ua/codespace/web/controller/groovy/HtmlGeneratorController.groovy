package com.ua.codespace.web.controller.groovy

import groovy.xml.MarkupBuilder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class HtmlGeneratorController {

    @RequestMapping("/generate")
    @ResponseBody
    def listPage() {
        def writer = new StringWriter()
        new MarkupBuilder(writer).html {
            head {
                title('Groovy page')
            }
            body {
                h2("I'm inside h1 tag")
                ol {
                    h3("I'm inside h3 tag")
                    li {
                        p("One")
                    }
                    li {
                        p("Two")
                    }

                }
            }
        }
        writer.toString()
    }
}
