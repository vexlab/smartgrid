package ml.vexlab.smartgrid.transport.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import ml.vexlab.smartgrid.enums.Role;

public class UserResponseDTO {

  private UUID id;
  private String firstname;
  private String lastname;
  private String username;
  private String email;
  private Date lastLogin;
  List<Role> roles;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
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

  public UserResponseDTO() {}

  public UserResponseDTO(
      UUID id,
      String firstname,
      String lastname,
      String username,
      String email,
      Date lastLogin,
      List<Role> roles) {
    super();
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.username = username;
    this.email = email;
    this.lastLogin = lastLogin;
    this.roles = roles;
  }

  public static class Builder {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private Date lastLogin;
    private List<Role> roles;

    public Builder id(UUID id) {
      this.id = id;
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

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
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

    public UserResponseDTO build() {
      return new UserResponseDTO(this);
    }
  }

  private UserResponseDTO(Builder builder) {
    this.id = builder.id;
    this.firstname = builder.firstname;
    this.lastname = builder.lastname;
    this.username = builder.username;
    this.email = builder.email;
    this.lastLogin = builder.lastLogin;
    this.roles = builder.roles;
  }
}
