GraphQL with GraphiQL on Spring Boot
====================================

A demo of graphql in spring boot. Runs the graphiql user interface on root so you can play around.

Example Queries
---------------

Find all hotels (20 per page)

```
query {
    hotel {
        uuid
	    name
	}
}
```

Find all hotels, specifying a page number.

```
query {
    hotel(page: 2) {
        uuid
	    name
	}
}
```

Find hotels named `tropi%`, drill down to show all available translations.

```
query {
    hotel(name: "tropi%") {
        uuid
        address
	    name
        translations {
            language
            name
            description
        }
	}
}
```

Show only the french translation.

```
query {
    hotel(name: "tropi%") {
        uuid
        address
	    name
        translations(language:"fr") {
            language
            name
            description
        }
	}
}
```

Gotchas
-------

In graphql-spring-boot all public attributes are exposed through graphql. This causes problems when using groovy objects because it tries to expose metaClass and all the other GroovyObject fluff.
Practically, this isn't a big problem. Schema "type" objects represent the data exposed in the graph.

The graphiql starter appears to need the schema to contain at least one mutation.

Running
-------

You'll need the following schema (copied from the farm schema).

```
DROP TABLE IF EXISTS `hotel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hotel` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address` TEXT DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `suburb` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `postcode` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `time_zone` varchar(255) DEFAULT NULL,
  `address_component_json` text,
  `chain` varchar(255) DEFAULT NULL,
  `circles` decimal(19,2) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `description` text,
  `emails_json` text,
  `google_name` varchar(255) DEFAULT NULL,
  `google_place_id` varchar(255) DEFAULT NULL,
  `heading` varchar(255) DEFAULT NULL,
  `images_json` text,
  `last_updated` datetime DEFAULT NULL,
  `last_updated_address` datetime DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `longitude` double DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `number_of_rooms` int(11) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `stars` decimal(19,2) DEFAULT NULL,
  `tag` varchar(255) DEFAULT NULL,
  `uuid` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `last_rating_id` int(10) unsigned,
  `shop_rating` bit(1) NOT NULL,
  `shop_range_short` int(11) DEFAULT NULL,
  `shop_range_long` int(11) DEFAULT NULL,
  `shop_max_length_of_stay` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  INDEX hotel_uuid_idx(uuid),
  INDEX hotel_website_idx(website),
  INDEX hotel_scrape_selector_idx(time_zone, shop_range_short, shop_range_long),
  INDEX hotel_stars_idx(stars),
  INDEX hotel_circles_idx(circles),
  INDEX hotel_shop_range_short(shop_range_short),
  INDEX hotel_shop_range_long(shop_range_long),
  INDEX hotel_shop_rating(shop_rating),
  INDEX hotel_last_updated_address(last_updated_address),
  KEY hotel_latitude_longitude_idx(latitude, longitude),
  FULLTEXT (name, address)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


DROP TABLE IF EXISTS hotel_translation;
CREATE TABLE hotel_translation
(
  id bigint NOT NULL AUTO_INCREMENT,
  name VARCHAR(255) COLLATE utf8_unicode_ci,
  description text COLLATE utf8_unicode_ci,
  hotel_id bigint NOT NULL,
  language VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  heading VARCHAR(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (id)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

Configure application.yml to point to your mysql database and run **gradlew bootrun**.

The graphiql interface is available at `http://localhost:8080/`.

