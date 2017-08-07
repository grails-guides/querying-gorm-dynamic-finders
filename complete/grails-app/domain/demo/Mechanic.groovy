package demo

import grails.rest.Resource

@Resource
class Mechanic {
    String name

    static constraints = {
        name blank: false, unique: true
    }
}