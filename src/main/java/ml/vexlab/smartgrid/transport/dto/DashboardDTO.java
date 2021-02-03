package ml.vexlab.smartgrid.transport.dto;

public class DashboardDTO {

	private String id;
	private String value;
	private String title;
	private String customer;

	public DashboardDTO() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public static class Builder {
		private String id;
		private String value;
		private String title;
		private String customer;

		public Builder id(String id) {
			this.id = id;
			return this;
		}

		public Builder value(String value) {
			this.value = value;
			return this;
		}

		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Builder customer(String customer) {
			this.customer = customer;
			return this;
		}

		public DashboardDTO build() {
			return new DashboardDTO(this);
		}
	}

	private DashboardDTO(Builder builder) {
		this.id = builder.id;
		this.value = builder.value;
		this.title = builder.title;
		this.customer = builder.customer;
	}
}
