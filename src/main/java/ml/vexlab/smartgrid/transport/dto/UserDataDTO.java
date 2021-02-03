package ml.vexlab.smartgrid.transport.dto;

import java.util.Date;
import java.util.List;

import ml.vexlab.smartgrid.enums.Role;

public class UserDataDTO {

	private String username;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private Date lastLogin;
	private List<Role> roles;

	public UserDataDTO() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public static class Builder {
		private String username;
		private String firstname;
		private String lastname;
		private String email;
		private String password;
		private Date lastLogin;
		private List<Role> roles;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder firstname(String firstname) {
			this.firstname = firstname;
			return this;
		}

		public Builder lastname(String lastname) {
			this.lastname = lastname;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder password(String password) {
			this.password = password;
			return this;
		}

		public Builder lastLogin(Date lastLogin) {
			this.lastLogin = lastLogin;
			return this;
		}

		public Builder roles(List<Role> roles) {
			this.roles = roles;
			return this;
		}

		public UserDataDTO build() {
			return new UserDataDTO(this);
		}
	}

	private UserDataDTO(Builder builder) {
		this.username = builder.username;
		this.firstname = builder.firstname;
		this.lastname = builder.lastname;
		this.email = builder.email;
		this.password = builder.password;
		this.lastLogin = builder.lastLogin;
		this.roles = builder.roles;
	}
}
