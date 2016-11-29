package siteminder.repository

import groovy.transform.CompileStatic
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import siteminder.domain.HotelTranslation

@CompileStatic
interface HotelTranslationRepository extends CrudRepository<HotelTranslation, Long> {

    @Query("SELECT t FROM HotelTranslation t WHERE t.hotel.uuid = :hotelUuid AND t.language = :language")
    List<HotelTranslation> findTranslations(@Param('hotelUuid') String hotelUuid, @Param('language') String language);
}
