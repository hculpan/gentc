package com.velogica.gentc

class MainTest extends GroovyTestCase {
    void testRun() {
        def main = new Main()
        String fileContents = new File('./test_data/first-test.txt').text
        main.run([fileContents])
    }
}
