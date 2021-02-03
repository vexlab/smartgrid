package ml.vexlab.smartgrid.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import ml.vexlab.smartgrid.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Customer extends Auditable<String> {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = true, nullable = false)
  private UUID id;

  @Column private String title;

  @Column @Lob private String description;

  @Column private String country;

  @Column private String city;

  @Column private String state;

  @Column private String postalCode;

  @Column private String address;

  @Column private String phone;

  @Column private String email;

  @OneToMany(mappedBy = "customer")
  private Set<Asset> assets;

  @OneToMany(mappedBy = "customer")
  private Set<Dashboard> dashboards;

  public Customer() {}

  public Customer(
      UUID id,
      String title,
      String description,
      String country,
      String city,
      String state,
      String postalCode,
      String address,
      String phone,
      String email,
      Set<Asset> assets,
      Set<Dashboard> dashboards) {
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
    this.assets = assets;
    this.dashboards = dashboards;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Set<Asset> getAssets() {
    return assets;
  }

  public void setAssets(Set<Asset> assets) {
    this.assets = assets;
  }

  public Set<Dashboard> getDashboards() {
    return dashboards;
  }

  public void setDashboards(Set<Dashboard> dashboards) {
    this.dashboards = dashboards;
  }
}
