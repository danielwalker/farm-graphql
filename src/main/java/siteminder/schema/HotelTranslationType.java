package siteminder.schema;


import com.oembedler.moon.graphql.engine.stereotype.GraphQLIgnore;
import com.oembedler.moon.graphql.engine.stereotype.GraphQLObject;
import siteminder.domain.HotelTranslation;

import java.util.function.Function;

@GraphQLObject
public class HotelTranslationType {

    @GraphQLIgnore
    static final Function<HotelTranslation, HotelTranslationType> fromHotelTranslation = hotelTranslation -> {
        HotelTranslationType type = new HotelTranslationType();
        type.name = hotelTranslation.getName();
        type.language = hotelTranslation.getLanguage();
        type.description = hotelTranslation.getDescription();
        return type;
    };

    String name;

    String language;

    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
