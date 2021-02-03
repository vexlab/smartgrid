package ml.vexlab.smartgrid.transport;

import javax.servlet.http.HttpServletRequest;
import ml.vexlab.smartgrid.entity.UserEntity;
import ml.vexlab.smartgrid.service.UserService;
import ml.vexlab.smartgrid.transport.dto.LoginFormDTO;
import ml.vexlab.smartgrid.transport.dto.TokenDTO;
import ml.vexlab.smartgrid.transport.dto.UserDataDTO;
import ml.vexlab.smartgrid.transport.dto.UserResponseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin()
public class UserTransport {

  @Autowired private UserService userService;

  @Autowired private ModelMapper modelMapper;

  @PostMapping("/signin")
  public TokenDTO login(@RequestBody LoginFormDTO loginForm) {
    return userService.signin(loginForm.getUsername(), loginForm.getPassword());
  }

  @PostMapping("/signup")
  public TokenDTO signup(@RequestBody UserDataDTO user) {
    return userService.signup(user);
  }

  @DeleteMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO delete(@PathVariable String username) {
    UserEntity user = userService.search(username);
    UserResponseDTO userResponseDTO = new UserResponseDTO();
    userResponseDTO.setUsername(username);
    userResponseDTO.setEmail(user.getEmail());
    userResponseDTO.setId(user.getId());
    userResponseDTO.setRoles(user.getRoles());
    userService.delete(username);
    return userResponseDTO;
  }

  @GetMapping(value = "/{username}")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public UserResponseDTO search(@PathVariable String username) {
    return modelMapper.map(userService.search(username), UserResponseDTO.class);
  }

  @GetMapping(value = "/whoami")
  public UserResponseDTO whoami(HttpServletRequest req) {
    return modelMapper.map(userService.whoami(req), UserResponseDTO.class);
  }

  @GetMapping("/refresh")
  public TokenDTO refresh(HttpServletRequest req) {
    return userService.refresh(req.getRemoteUser());
  }
}
