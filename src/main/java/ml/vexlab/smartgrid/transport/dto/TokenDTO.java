package ml.vexlab.smartgrid.transport.dto;

public class TokenDTO {

  private String token;

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public TokenDTO() {}

  public TokenDTO(String token) {
    super();
    this.token = token;
  }
}
