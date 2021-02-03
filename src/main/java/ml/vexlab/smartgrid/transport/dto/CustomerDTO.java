package ml.vexlab.smartgrid.transport.dto;

import java.util.List;

public class CustomerDTO {

  private String id;
  private String title;
  private String description;
  private String country;
  private String city;
  private String state;
  private String postalCode;
  private String address;
  private String phone;
  private String email;
  private List<String> assets;
  private List<String> dashboards;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public List<String> getAssets() {
    return assets;
  }

  public void setAssets(List<String> assets) {
    this.assets = assets;
  }

  public List<String> getDashboards() {
    return dashboards;
  }

  public void setDashboards(List<String> dashboards) {
    this.dashboards = dashboards;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public CustomerDTO(
      String id,
      String title,
      String description,
      String country,
      String city,
      String state,
      String postalCode,
      String address,
      String phone,
      String email) {
    super();
    this.id = id;
    this.title = title;
    this.description = description;
    this.country = country;
    this.city = city;
    this.state = state;
    this.postalCode = postalCode;
    this.address = address;
    this.phone = phone;
    this.email = email;
  }

  public static class Builder {
    private String id;
    private String title;
    private String description;
    private String country;
    private String city;
    private String state;
    private String postalCode;
    private String address;
    private String phone;
    private String email;
    private List<String> assets;
    private List<String> dashboards;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder country(String country) {
      this.country = country;
      return this;
    }

    public Builder city(String city) {
      this.city = city;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public Builder postalCode(String postalCode) {
      this.postalCode = postalCode;
      return this;
    }

    public Builder address(String address) {
      this.address = address;
      return this;
    }

    public Builder phone(String phone) {
      this.phone = phone;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder assets(List<String> assets) {
      this.assets = assets;
      return this;
    }

    public Builder dashboards(List<String> dashboards) {
      this.dashboards = dashboards;
      return this;
    }

    public CustomerDTO build() {
      return new CustomerDTO(this);
    }
  }

  private CustomerDTO(Builder builder) {
    this.id = builder.id;
    this.title = builder.title;
    this.description = builder.description;
    this.country = builder.country;
    this.city = builder.city;
    this.state = builder.state;
    this.postalCode = builder.postalCode;
    this.address = builder.address;
    this.phone = builder.phone;
    this.email = builder.email;
    this.assets = builder.assets;
    this.dashboards = builder.dashboards;
  }
}
