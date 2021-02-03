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
import javax.validation.constraints.Size;
import ml.vexlab.smartgrid.audit.Auditable;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Device extends Auditable<String> {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id")
  private UUID id;

  @Column private String name;

  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  @Column
  @Size(max = 20)
  private String accessToken;

  @Column @Lob private String description;

  @OneToMany(mappedBy = "device")
  private Set<DeviceType> type;

  @OneToMany(mappedBy = "device")
  private Set<Alarm> alarms;

  @OneToMany(mappedBy = "device")
  private Set<History> histories;

  @ManyToOne
  @JoinColumn(name = "asset_id", nullable = false)
  private Asset asset;

  public Device() {}

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

  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Set<DeviceType> getType() {
    return type;
  }

  public void setType(Set<DeviceType> type) {
    this.type = type;
  }

  public Set<Alarm> getAlarms() {
    return alarms;
  }

  public void setAlarms(Set<Alarm> alarms) {
    this.alarms = alarms;
  }

  public Set<History> getHistories() {
    return histories;
  }

  public void setHistories(Set<History> histories) {
    this.histories = histories;
  }

  public Asset getAsset() {
    return asset;
  }

  public void setAsset(Asset asset) {
    this.asset = asset;
  }
}
