package siteminder.domain

import groovy.transform.CompileStatic

import javax.persistence.*

@CompileStatic
@Entity
class HotelTranslation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id

    @JoinColumn(name = "hotel_id")
    @ManyToOne
    Hotel hotel

    String name

    String language

    @Column(columnDefinition = "text")
    String description
}
