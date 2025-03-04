package com.example.dajewelry_cuoiky.Service;

import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

import com.example.dajewelry_cuoiky.dto.UserDto;
import com.example.dajewelry_cuoiky.Entity.User;
import com.example.dajewelry_cuoiky.Entity.Role;
import com.example.dajewelry_cuoiky.Repository.RoleRepository;
import com.example.dajewelry_cuoiky.Repository.UserRepository;
@Service
public class UserServiceImpl implements UserService {
   private UserRepository userRepository;
   private RoleRepository roleRepository;
   private PasswordEncoder passwordEncoder;
   public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
       this.userRepository = userRepository;
       this.roleRepository = roleRepository;
       this.passwordEncoder = passwordEncoder;
   }
   @Override
   public void saveUser(UserDto userDto) {
       User user = new User();
       user.setName(userDto.getFirstName() + " " + userDto.getLastName());
       user.setEmail(userDto.getEmail());
       //encrypt the password using spring security
       user.setPassword(passwordEncoder.encode(userDto.getPassword()));

       Role role = roleRepository.findByName("ROLE_ADMIN");
       if (role == null) {
           role = checkRoleExist();
       }
       user.setRoles(List.of(role));
       userRepository.save(user);
   }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> convertEntityToDto(user))
                .collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user) {
        UserDto userDto = new UserDto();
        String[] name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
