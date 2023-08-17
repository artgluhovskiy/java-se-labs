package org.art.samples.core.groovy.regexp;

/**
 * Find and replace group for each line.
 */
class RegExpSample {

    static void main(String[] args) {
        def data = '''
value1: *1234
value2: *5678
value3: *59101
        '''
        println data.replaceAll('(?m)(.*):\\s+(\\*[\\d]+)', '$1: \"$2\"')
    }
}
