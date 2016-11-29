package siteminder.domain

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@CompileStatic
@Entity
class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    String uuid

    String name

    String address
}
