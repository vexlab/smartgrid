package ml.vexlab.smartgrid.transport.dto;

import java.util.Date;

public class TelemetryDTO {

	private String device;
	private String key;
	private Date ts;
	private Object value;

	public TelemetryDTO() {
		super();
	}

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

	public Date getTs() {
		return ts;
	}

	public void setTs(Date ts) {
		this.ts = ts;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public static class Builder {
		private String device;
		private String key;
		private Date ts;
		private Object value;

		public Builder device(String device) {
			this.device = device;
			return this;
		}

		public Builder key(String key) {
			this.key = key;
			return this;
		}

		public Builder ts(Date ts) {
			this.ts = ts;
			return this;
		}

		public Builder value(Object value) {
			this.value = value;
			return this;
		}

		public TelemetryDTO build() {
			return new TelemetryDTO(this);
		}
	}

	private TelemetryDTO(Builder builder) {
		this.device = builder.device;
		this.key = builder.key;
		this.ts = builder.ts;
		this.value = builder.value;
	}
}
