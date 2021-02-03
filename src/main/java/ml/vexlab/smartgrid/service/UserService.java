package ml.vexlab.smartgrid.service;

import javax.servlet.http.HttpServletRequest;

import ml.vexlab.smartgrid.entity.UserEntity;
import ml.vexlab.smartgrid.transport.dto.TokenDTO;
import ml.vexlab.smartgrid.transport.dto.UserDataDTO;

public interface UserService {

	TokenDTO signin(String username, String password);

	TokenDTO signup(UserDataDTO user);

	void delete(String username);

	UserEntity search(String username);

	UserEntity whoami(HttpServletRequest req);

	TokenDTO refresh(String username);
}
