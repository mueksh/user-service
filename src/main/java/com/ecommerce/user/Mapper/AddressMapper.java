package com.ecommerce.user.Mapper;

import com.ecommerce.user.dto.request.AddressRequest;
import com.ecommerce.user.dto.response.AddressResponse;
import com.ecommerce.user.model.Address;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class AddressMapper {

    //AddressRequest → Address
    public static Address toAddressEntity(AddressRequest addressRequest){

        if (addressRequest==null){
            log.warn("AddressRequest is Null");
            return null;
        }

        log.info("Converting AddressRequest to Address");
        return Address.builder()
                .street(addressRequest.getStreet())
                .city(addressRequest.getCity())
                .state(addressRequest.getState())
                .zip(addressRequest.getZip())
                .country(addressRequest.getCountry())
                .build();
    }

    // Address → AddressResponse
    public static AddressResponse toAddressResponse(Address address) {

        if (address == null) {
            log.warn("Address is Null");
            return null;
        }

        log.info("Converting Address to AddressResponse");
        return AddressResponse.builder()
                .street(address.getStreet())
                .city(address.getCity())
                .state(address.getState())
                .zip(address.getZip())
                .country(address.getCountry())
                .build();
    }

    // List<AddressRequest> → List<Address>
    public static List<Address> toAddressEntityList(List<AddressRequest> addressRequests){

        if (addressRequests == null) {
            log.warn("Address list is Null");
            return List.of();
        }

        log.info("Converting AddressRequestList to AddressEntityList");
        return addressRequests.stream()
                .filter(Objects::nonNull)
                .map(AddressMapper::toAddressEntity)
                .collect(Collectors.toList());
    }


    // List<Address> → List<AddressResponse>
    public static List<AddressResponse> toAddressResponseList(List<Address> addresses) {
        if (addresses == null) return List.of();

        return addresses.stream()
                .filter(Objects::nonNull)
                .map(AddressMapper::toAddressResponse)
                .collect(Collectors.toList());
    }




}
