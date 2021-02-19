package ml.vexlab.smartgrid.service.impl;

import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ml.vexlab.smartgrid.entity.UserEntity;
import ml.vexlab.smartgrid.enums.Role;
import ml.vexlab.smartgrid.exception.CustomException;
import ml.vexlab.smartgrid.repository.UserRepository;
import ml.vexlab.smartgrid.security.JwtTokenProvider;
import ml.vexlab.smartgrid.service.UserService;
import ml.vexlab.smartgrid.transport.dto.TokenDTO;
import ml.vexlab.smartgrid.transport.dto.UserDataDTO;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

  @Autowired private UserRepository userRepository;

  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private JwtTokenProvider jwtTokenProvider;

  @Autowired private AuthenticationManager authenticationManager;

  public TokenDTO signin(String username, String password) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      UserEntity user = userRepository.findByUsername(username);
      final Date lastLogin = user.getLastLogin();
      user.setLastLogin(new Date());
      userRepository.save(user);
      return new TokenDTO(jwtTokenProvider.createToken(user, lastLogin));
    } catch (AuthenticationException e) {
      throw new CustomException(
          "Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public TokenDTO signup(UserDataDTO userDTO) {
    if (userDTO.getUsername() != null && !userRepository.existsByUsername(userDTO.getUsername())) {
      UserEntity user = new UserEntity();
      user.setUsername(userDTO.getUsername());
      user.setFirstName(userDTO.getFirstname());
      user.setLastName(userDTO.getLastname());
      user.setEmail(userDTO.getEmail());
      user.setRoles(
          userDTO.getRoles() != null ? userDTO.getRoles() : Arrays.asList(Role.ROLE_DEFAULT));
      user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
      Date lastLogin = new Date();
      user.setLastLogin(lastLogin);
      userRepository.save(user);
      return new TokenDTO(jwtTokenProvider.createToken(user, lastLogin));
    } else {
      throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
    }
  }

  public void delete(String username) {
    userRepository.deleteByUsername(username);
  }

  public UserEntity search(String username) {
    UserEntity user = userRepository.findByUsername(username);
    if (user == null) {
      throw new CustomException("The user doesn't exist", HttpStatus.NOT_FOUND);
    }
    return user;
  }

  public UserEntity whoami(HttpServletRequest req) {
    return userRepository.findByUsername(
        jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  public TokenDTO refresh(String username) {
    UserEntity user = userRepository.findByUsername(username);
    return new TokenDTO(jwtTokenProvider.createToken(user, user.getLastLogin()));
  }
}
