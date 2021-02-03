package ml.vexlab.smartgrid.transport.dto;

public class AlarmDTO {

  private String id;
  private String originator;
  private String type;
  private String severity;
  private String status;
  private String detail;
  private String device;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOriginator() {
    return originator;
  }

  public void setOriginator(String originator) {
    this.originator = originator;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String severity) {
    this.severity = severity;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public String getDevice() {
    return device;
  }

  public void setDevice(String device) {
    this.device = device;
  }

  public static class Builder {
    private String id;
    private String originator;
    private String type;
    private String severity;
    private String status;
    private String detail;
    private String device;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder originator(String originator) {
      this.originator = originator;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder severity(String severity) {
      this.severity = severity;
      return this;
    }

    public Builder status(String status) {
      this.status = status;
      return this;
    }

    public Builder detail(String detail) {
      this.detail = detail;
      return this;
    }

    public Builder device(String device) {
      this.device = device;
      return this;
    }

    public AlarmDTO build() {
      return new AlarmDTO(this);
    }
  }

  private AlarmDTO(Builder builder) {
    this.id = builder.id;
    this.originator = builder.originator;
    this.type = builder.type;
    this.severity = builder.severity;
    this.status = builder.status;
    this.detail = builder.detail;
    this.device = builder.device;
  }
}
