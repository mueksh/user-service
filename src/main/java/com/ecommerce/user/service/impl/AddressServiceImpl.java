package com.ecommerce.user.service.impl;

import com.ecommerce.user.Mapper.AddressMapper;
import com.ecommerce.user.Mapper.UserMapper;
import com.ecommerce.user.dto.request.AddressRequest;
import com.ecommerce.user.dto.response.AddressResponse;
import com.ecommerce.user.exception.UserNotFoundException;
import com.ecommerce.user.model.Address;
import com.ecommerce.user.model.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.AddressService;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;


   //Fetching address of a specific user based on there mail
    @Override
    public List<AddressResponse> getAddressByUserMail(String mail) {

        if (mail==null){
            log.warn("Mail is Null, please provide valid mail");
            return null;
        }

        log.info("Fetching User from DB with mail : "+mail);
        User user= userRepository.findByEmail(mail)
                .orElseThrow(
                        ()-> new UserNotFoundException("User not found with mail : "+ mail));
        log.info("User fetched successfully from DB with mail : "+mail);

        List<Address> address=user.getAddresses();

        return AddressMapper.toAddressResponseList(address);
    }

    //Adding address with user
    @Override
    public List<AddressResponse> addAddress(String mail, AddressRequest addressRequest) {
        if (mail==null){
            log.warn("Mail is Null, please provide valid mail");
            return null;
        }
        log.info("Fetching User from DB with mail : "+mail);
        User user= userRepository.findByEmail(mail)
                .orElseThrow(
                        ()-> new UserNotFoundException("User not found with mail : "+ mail));
        log.info("User fetched successfully from DB with mail : "+mail);

        log.info("fetching list of all address with user: "+mail);
        List<Address> address=user.getAddresses();
        address.add(AddressMapper.toAddressEntity(addressRequest));

        log.info("Address added in user profile");
        user.setAddresses(address);

        log.info("Saving user with added new address with user mail: "+mail);
        User savedUser=userRepository.save(user);

        return UserMapper.toUserResponse(savedUser).getAddresses();
    }
}
