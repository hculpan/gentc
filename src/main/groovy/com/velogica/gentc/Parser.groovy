package com.velogica.gentc

import groovy.transform.TupleConstructor

enum Variable { age, lastname, firstname }

enum TimeUnit { years, months, days }

@TupleConstructor
class Time {
    Number amount
    String segment

    String toString() { "$amount $segment" }
}

class TimeCategory {
    static Time getYears(Number n) {
        new Time(n, "years")
    }

    static Time getDays(Number n) {
        new Time(n, segment: "days")
    }

    static Time getMonths(Number n) {
        new Time(n, "months")
    }
}

class Parser {
    String templateName

    def variables = []

    void execute(String script) {
        def binding = new Binding( template: this,
                Default: "Default",
                with: this.&with,
                set: this.&set,
                *: Variable.values().collectEntries { [(it.name()): it] },
                *: TimeUnit.values().collectEntries { [(it.name()): it] }
        )

        use(TimeCategory) {
            def shell = new GroovyShell(this.class.classLoader, binding)
            shell.evaluate(script)
        }
    }

    void with(String name) {
        this.templateName = name
    }

    void set(Map map, Variable variable) {
        map.each { k, v -> println "${k}: ${v}" }
        this.variables.add(variable)
    }

    void set(Variable variable) {
        this.variables.add(variable)
    }

    String toString() {
        def result = ""
        if (templateName) {
            result += "template: $templateName\n"
        }

        variables.each { v -> result += "variable: $v\n"}
        return result
    }
}
