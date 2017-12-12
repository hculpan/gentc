package com.velogica.gentc

import com.velogica.gentc.Variable

abstract class ParserBaseScript extends groovy.lang.Script {
    void with(String name) {
        this.binding.template.with name
    }

    void use(Map map, Variable variable) {
        this.binding.template.use map, variable
    }

    String getDefaultTemplate() {
        return "Default"
    }
}
