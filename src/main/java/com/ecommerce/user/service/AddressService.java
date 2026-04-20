package com.ecommerce.user.service;

import com.ecommerce.user.dto.request.AddressRequest;
import com.ecommerce.user.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {

    //Getting specific user address
    List<AddressResponse> getAddressByUserMail(String mail);

    //adding address for specific user
    List<AddressResponse> addAddress(String mail, AddressRequest addressRequest);

}
