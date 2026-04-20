package com.ecommerce.user.controller;


import com.ecommerce.user.dto.request.AddressRequest;
import com.ecommerce.user.dto.response.AddressResponse;
import com.ecommerce.user.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/e-commerce")
@RequiredArgsConstructor
@Slf4j
public class AddressController {

    private final AddressService addressService;

    //Fetching address
    @GetMapping("/address/{mail}")
    public ResponseEntity<List<AddressResponse>> getAddressByUser(@PathVariable("mail") String mail){

        log.info("Fetching Address using User mail: "+mail);
        List<AddressResponse> addressResponse=addressService.getAddressByUserMail(mail);
        log.info("Address fetched successfully with mail : "+mail);

        return ResponseEntity.ok(addressResponse);

    }

    //adding address for specific user
    @PostMapping("/address/update/{mail}")
    public ResponseEntity<List<AddressResponse>> addAddress(@PathVariable("mail") String mail,
                                            @RequestBody AddressRequest addressRequest){

        log.info("Adding address for specific user: "+mail);
        List<AddressResponse> addressResponses
                = addressService.addAddress(mail,addressRequest);

        return ResponseEntity.ok(addressResponses);

    }


}
