package com.tecsup.petclinic.dto;

public class OwnerDTO {
	private int id;
	private String firstname;
	private String lastname;
	private String address;
	private String city;
	private String telephone;
	
	public OwnerDTO(String firstname, String lastname, String address, String city, String telephone) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.address = address;
		this.city = city;
		this.telephone = telephone;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@Override
	public String toString() {
		return "Owner [id=" + id + ", first_name=" + firstname + ", last_name=" + lastname + ", address=" + address + ", city="
				+ city + ", telephone=" + telephone + "]";
	}	
}
