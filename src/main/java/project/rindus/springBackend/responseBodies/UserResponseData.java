package project.rindus.springBackend.responseBodies;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data class representing the object received after GET REST call for retrieving an user by ID.
 * NOTE: The class is not used anymore as Objects instances will be used instead.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseData {

    private String id;

    private String name;

    private String userName;

    private String email;

    private Address address;

    public static class Address {

        private String street;

        private String suit;

        private String city;

        private String zipcode;

        private Geo geo;

        public static class Geo {

            private String lat;

            private String lng;
        }
    }
}
