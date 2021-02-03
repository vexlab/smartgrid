package ml.vexlab.smartgrid.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import ml.vexlab.smartgrid.audit.Auditable;

@Entity
public class Dashboard extends Auditable<String> {
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "id", updatable = true, nullable = false)
	private UUID id;

	@Column
	@Lob
	private String value;

	@Column
	private String title;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = true)
	private Customer customer;

	public Dashboard() {

	}

	public Dashboard(UUID id, String value, String title, Customer customer) {
		super();
		this.id = id;
		this.value = value;
		this.title = title;
		this.customer = customer;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
