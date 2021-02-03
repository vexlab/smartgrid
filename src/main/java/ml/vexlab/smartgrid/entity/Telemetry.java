package ml.vexlab.smartgrid.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Telemetry {

  @Column protected String key;

  @Column protected Boolean bool_value;

  @Column protected String str_value;

  @Column protected Integer int_value;

  @Column protected Double dbl_value;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Boolean getBool_value() {
    return bool_value;
  }

  public void setBool_value(Boolean bool_value) {
    this.bool_value = bool_value;
  }

  public String getStr_value() {
    return str_value;
  }

  public void setStr_value(String str_value) {
    this.str_value = str_value;
  }

  public Integer getInt_value() {
    return int_value;
  }

  public void setInt_value(Integer int_value) {
    this.int_value = int_value;
  }

  public Double getDbl_value() {
    return dbl_value;
  }

  public void setDbl_value(Double dbl_value) {
    this.dbl_value = dbl_value;
  }

  public Telemetry() {}

  public Telemetry(
      String key, Boolean bool_value, String str_value, Integer int_value, Double dbl_value) {
    super();
    this.key = key;
    this.bool_value = bool_value;
    this.str_value = str_value;
    this.int_value = int_value;
    this.dbl_value = dbl_value;
  }
}
