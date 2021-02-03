package ml.vexlab.smartgrid.transport.dto;

import java.util.List;

public class AssetDTO {

	private String id;
	private String name;
	private String description;
	private List<String> type;
	private String customer;
	private List<String> devices;

	public AssetDTO() {
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

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public List<String> getDevices() {
		return devices;
	}

	public void setDevices(List<String> devices) {
		this.devices = devices;
	}

	public AssetDTO(String id, String name, String description, List<String> type, String customer,
			List<String> devices) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.type = type;
		this.customer = customer;
		this.devices = devices;
	}

	public static class Builder {
		private String id;
		private String name;
		private String description;
		private List<String> type;
		private String customer;
		private List<String> devices;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder name(String name) {
			this.name = name;
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

		public Builder customer(String customer) {
			this.customer = customer;
			return this;
		}

		public Builder devices(List<String> devices) {
			this.devices = devices;
			return this;
		}

		public AssetDTO build() {
			return new AssetDTO(this);
		}
	}

	private AssetDTO(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.type = builder.type;
		this.customer = builder.customer;
		this.devices = builder.devices;
	}
}
