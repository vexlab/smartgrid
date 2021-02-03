package ml.vexlab.smartgrid.transport.dto;

public class GenericDataDTO {

  private String id;
  private String display;
  private boolean error = false;
  private Object additionalData;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public boolean isError() {
    return error;
  }

  public void setError(boolean error) {
    this.error = error;
  }

  public Object getAdditionalData() {
    return additionalData;
  }

  public void setAdditionalData(Object additionalData) {
    this.additionalData = additionalData;
  }

  public static class Builder {
    private String id;
    private String display;
    private boolean error;
    private Object additionalData;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder display(String display) {
      this.display = display;
      return this;
    }

    public Builder error(boolean error) {
      this.error = error;
      return this;
    }

    public Builder additionalData(Object additionalData) {
      this.additionalData = additionalData;
      return this;
    }

    public GenericDataDTO build() {
      return new GenericDataDTO(this);
    }
  }

  private GenericDataDTO(Builder builder) {
    this.id = builder.id;
    this.display = builder.display;
    this.error = builder.error;
    this.additionalData = builder.additionalData;
  }
}
