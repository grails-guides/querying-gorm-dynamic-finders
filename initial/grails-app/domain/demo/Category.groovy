package demo

import grails.rest.Resource

@Resource
class Category {
    String name

    static constraints = {
        name blank: false, unique: true
    }
}