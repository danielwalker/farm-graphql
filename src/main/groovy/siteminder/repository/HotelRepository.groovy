package siteminder.repository

import groovy.transform.CompileStatic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository
import siteminder.domain.Hotel

@CompileStatic
interface HotelRepository extends CrudRepository<Hotel, Long> {

    Page<Hotel> findByNameLikeIgnoreCase(String name, Pageable pageable)

    Page<Hotel> findAll(Pageable pageable)
}
