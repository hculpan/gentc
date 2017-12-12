package com.velogica.gentc

class Main {
    def options() {
        def cli = new CliBuilder(usage: 'gentc -[hs] [commands]')
        // Create the list of options.
        cli.with {
            h longOpt: 'help', 'Show usage information'
            s longOpt: 'script', args: 1, argName: 'filename', 'Script to execute'
//            f longOpt: 'format-full',   'Use DateFormat#FULL format'
//            l longOpt: 'format-long',   'Use DateFormat#LONG format'
//            m longOpt: 'format-medium', 'Use DateFormat#MEDIUM format (default)'
//            s longOpt: 'format-short',  'Use DateFormat#SHORT format'
        }
        return cli
    }

    def run(args) {
        def cli = options()
        String script = null

        def options = cli.parse(args)
        if (!options) {
            return
        }
        // Show usage text when -h or --help option is used.
        if (options.h) {
            cli.usage()
            return
        }

        if (options.s) {
            println "Script file: ${options.s}"
        }

        if (options.arguments().size() == 1) {
            script = options.arguments().get(0)
        }

        if (!script) {
            println "Error: No script"
            cli.usage()
            return
        }

        def parser = new Parser()
        parser.execute script
        println parser
    }
}
