package siteminder.schema;

import com.oembedler.moon.graphql.engine.stereotype.GraphQLField;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLIn;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import org.springframework.beans.factory.annotation.Autowired;
import siteminder.domain.Hotel;
import siteminder.repository.HotelTranslationRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@GraphQLObject
public class HotelType {

    @Autowired
    @GraphQLIgnore
    HotelTranslationRepository hotelTranslationRepository;

    @GraphQLIgnore
    static final Function<Hotel, HotelType> fromHotel = hotel -> {
        HotelType hotelType = new HotelType();
        hotelType.uuid = hotel.getUuid();
        hotelType.name = hotel.getName();
        hotelType.address = hotel.getAddress();
        return hotelType;
    };

    String uuid;

    String name;

    String address;

    @GraphQLField("tranlsations")
    public List<HotelTranslationType> getTranslations(@GraphQLIn HotelType hotel, @GraphQLIn("language") String language) {
        return hotelTranslationRepository
                .findTranslations(hotel.uuid, language)
                .stream()
                .map(HotelTranslationType.fromHotelTranslation)
                .collect(Collectors.toList());
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
