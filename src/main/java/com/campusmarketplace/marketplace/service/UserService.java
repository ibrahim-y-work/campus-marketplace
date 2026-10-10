package com.campusmarketplace.marketplace.service;

import com.campusmarketplace.marketplace.dto.UserRegistrationRequest;
import com.campusmarketplace.marketplace.entity.User;
import com.campusmarketplace.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User convertDTOToUser(UserRegistrationRequest user){
        User realUser=new User();
        realUser.setFirstName(user.getFirstName());
        realUser.setLastName(user.getLastName());
        realUser.setEmail(user.getEmail());
        realUser.setPhone(user.getPhone());
        return realUser;
    }
    @Transactional
    public User addUser(UserRegistrationRequest user) {
        // email must be unique
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email is used before");
        }

        // phone number must be unique
        if(user.getPhone() != null && userRepository.existsByPhone(user.getPhone())) {
            throw new RuntimeException("Phone Number is used before");
        }

        User newUser = convertDTOToUser(user);

        return userRepository.save(newUser);
    }

    @Transactional
    public void deleteUser(UUID id){
        if(!userRepository.existsById(id)) {
            throw new RuntimeException("User with this ID does not exist");
        }

        userRepository.deleteById(id);
    }

    @Transactional
    public User updateUser(UUID id, UserRegistrationRequest request){
        User existingUser=userRepository.findById(id).orElseThrow(()->new RuntimeException("User with this ID does not exist"));

        if(userRepository.existsByEmail(request.getEmail())&&!existingUser.getEmail().equals(request.getEmail())){
            throw new RuntimeException("Email is already in use by another account");
        }

        if(request.getPhone() != null && !request.getPhone().equals(existingUser.getPhone()) && userRepository.existsByPhone(request.getPhone())){
            throw new RuntimeException("Phone is already in use by another account");
        }

        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());

        return userRepository.save(existingUser);

    }

    @Transactional(readOnly = true)
    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This user doesn't exist"));
    }
}
