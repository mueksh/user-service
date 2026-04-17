package com.ecommerce.user.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressResponse {

    private String street;
    private String city;
    private String state;
    private String zip;
    private String country;
}
