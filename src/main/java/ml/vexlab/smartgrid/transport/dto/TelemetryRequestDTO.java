package ml.vexlab.smartgrid.transport.dto;

import java.util.Date;

public class TelemetryRequestDTO {

  private String device;
  private String key;
  private Date from;
  private Date to;
  private Long last;

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public Date getFrom() {
    return from;
  }

  public void setFrom(Date from) {
    this.from = from;
  }

  public Date getTo() {
    return to;
  }

  public void setTo(Date to) {
    this.to = to;
  }

  public Long getLast() {
    return last;
  }

  public void setLast(Long last) {
    this.last = last;
  }
}
