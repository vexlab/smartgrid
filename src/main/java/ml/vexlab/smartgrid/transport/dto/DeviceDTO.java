package ml.vexlab.smartgrid.transport.dto;

import java.util.List;

public class DeviceDTO {
	private String id;
	private String name;
	private String accessToken;
	private String description;
	private List<String> type;
	private List<String> alarm;
	private String asset;

	public DeviceDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<String> getType() {
		return type;
	}

	public void setType(List<String> type) {
		this.type = type;
	}

	public List<String> getAlarm() {
		return alarm;
	}

	public void setAlarm(List<String> alarm) {
		this.alarm = alarm;
	}

	public String getAsset() {
		return asset;
	}

	public void setAsset(String asset) {
		this.asset = asset;
	}

	public static class Builder {
		private String id;
		private String name;
		private String accessToken;
		private String description;
		private List<String> type;
		private List<String> alarm;
		private String asset;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder accessToken(String accessToken) {
			this.accessToken = accessToken;
			return this;
		}

		public Builder description(String description) {
			this.description = description;
			return this;
		}

		public Builder type(List<String> type) {
			this.type = type;
			return this;
		}

		public Builder alarm(List<String> alarm) {
			this.alarm = alarm;
			return this;
		}

		public Builder asset(String asset) {
			this.asset = asset;
			return this;
		}

		public DeviceDTO build() {
			return new DeviceDTO(this);
		}
	}

	private DeviceDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.accessToken = builder.accessToken;
		this.description = builder.description;
		this.type = builder.type;
		this.alarm = builder.alarm;
		this.asset = builder.asset;
	}
}
