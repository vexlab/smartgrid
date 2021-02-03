package ml.vexlab.smartgrid.entity;

import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import ml.vexlab.smartgrid.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Asset extends Auditable<String> {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id", updatable = true, nullable = false)
  private UUID id;

  @Column private String name;

  @Column @Lob private String description;

  @OneToMany(mappedBy = "asset")
  private Set<AssetType> type;

  @ManyToOne
  @JoinColumn(name = "customer_id", nullable = true)
  private Customer customer;

  @OneToMany(mappedBy = "asset")
  private Set<Device> devices;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<AssetType> getType() {
    return type;
  }

  public void setType(Set<AssetType> type) {
    this.type = type;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public Set<Device> getDevices() {
    return devices;
  }

  public void setDevices(Set<Device> devices) {
    this.devices = devices;
  }
}
