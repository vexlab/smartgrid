package ml.vexlab.smartgrid.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class History extends Telemetry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id")
	private UUID id;

	@ManyToOne
	@JoinColumn(name = "device_id", nullable = true)
	private Device device;

	@Column
	private Date creationDate;

	public History() {
	}

	public History(Device device, String key, Date creationDate, Boolean bool_value, String str_value,
			Integer int_value, Double dbl_value) {
		super(key, bool_value, str_value, int_value, dbl_value);
		this.device = device;
		this.creationDate = creationDate;
	}

	public History(Device device, String key, Date creationDate, Boolean bool_value) {
		super(key, bool_value, null, null, null);
		this.device = device;
		this.creationDate = creationDate;
	}

	public History(Device device, String key, Date creationDate, String str_value) {
		super(key, null, str_value, null, null);
		this.device = device;
		this.creationDate = creationDate;
	}

	public History(Device device, String key, Date creationDate, Integer int_value) {
		super(key, null, null, int_value, null);
		this.device = device;
		this.creationDate = creationDate;
	}

	public History(Device device, String key, Date creationDate, Double dbl_value) {
		super(key, null, null, null, dbl_value);
		this.device = device;
		this.creationDate = creationDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
