package siteminder.domain

import groovy.transform.CompileStatic

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@CompileStatic
@Entity
class HotelTranslation {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    Long id

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    Hotel hotel

    String name

    String language

    String description

    String heading
}
