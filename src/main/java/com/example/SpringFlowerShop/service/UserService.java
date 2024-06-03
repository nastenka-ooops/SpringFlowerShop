package com.example.SpringFlowerShop.service;

import com.example.SpringFlowerShop.entity.MyUserDetails;
import com.example.SpringFlowerShop.dto.UserDto;
import com.example.SpringFlowerShop.entity.Customer;
import com.example.SpringFlowerShop.entity.Role;
import com.example.SpringFlowerShop.entity.User;
import com.example.SpringFlowerShop.mapping.UserMapper;
import com.example.SpringFlowerShop.repository.CustomerRepository;
import com.example.SpringFlowerShop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CustomerRepository customerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        return user.map(MyUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException(username + "There is not such user in repository"));
    }

    public Optional<UserDto> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::mapToUserDto);
    }

    public Optional<UserDto> findByUsername(String username) {
        return userRepository.findByName(username).map(UserMapper::mapToUserDto);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    public boolean saveUser(UserDto userDto) {
        Optional<User> userFromBD = userRepository.findByName(userDto.getEmail());

        if (userFromBD.isEmpty()) {
            User newUser = new User();
            newUser.setName(userDto.getEmail());
            newUser.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            newUser.setRole(Role.CUSTOMER);
            userRepository.save(newUser);

            Customer newCustomer = new Customer();
            newCustomer.setFirstName(userDto.getFirstName());
            newCustomer.setLastName(userDto.getLastName());
            newCustomer.setAddress(userDto.getAddress());
            newCustomer.setEmail(userDto.getEmail());
            newCustomer.setPhone(userDto.getPhone());
            customerRepository.save(newCustomer);
            return true;
        }
        return false;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
