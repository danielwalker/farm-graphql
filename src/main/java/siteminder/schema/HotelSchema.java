package siteminder.schema;

import com.oembedler.moon.graphql.engine.stereotype.*;
import groovy.transform.CompileStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;
import siteminder.domain.Hotel;
import siteminder.repository.HotelRepository;

import java.util.List;
import java.util.stream.Collectors;

@GraphQLSchema
public class HotelSchema {

    @GraphQLSchemaQuery
    public Query query;

    @GraphQLObject
    public static class Query {

        @Autowired
        @GraphQLIgnore
        HotelRepository hotelRepository;

        @GraphQLField
        public List<HotelType> hotel(@GraphQLIn("name") String name, @GraphQLIn("page") Integer page) {

            // Maximum 20 hotels at a time.
            PageRequest pageRequest = new PageRequest((page == null) ? 0 : page, 20);

            // Find a list of hotels.
            List<Hotel> hotels;
            if (StringUtils.isEmpty(name)) {
                hotels = hotelRepository.findAll(pageRequest).getContent();
            } else {
                hotels = hotelRepository.findByNameLikeIgnoreCase(name, pageRequest).getContent();
            }

            // Transform Hotel to HotelType and return.
            return hotels.stream().map(HotelType.fromHotel).collect(Collectors.toList());
        }
    }

    @GraphQLMutation
    @GraphQLOut("message")
    @GraphQLDescription("A demo mutation that echos the supplied message.")
    public String echo(@GraphQLIn("message") String message) {

        /*
         * Every Schema must have a mutation. I think this is simply an issue within graphiql. Documentation of spring-graphql-common
         * is explicit about it being optional.
         *
         * mutation {
         *     echo(input: {clientMutationId:"1", ,message: "hello"}) {
         *         clientMutationId
         *         message
         *     }
         * }
         */
        return message;
    }
}
