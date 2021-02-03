package ml.vexlab.smartgrid.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ADMIN, ROLE_CLIENT, ROLE_DEFAULT;

	public String getAuthority() {
	    return name();
	  }
}
