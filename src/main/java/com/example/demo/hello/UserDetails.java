package com.example.demo.hello;

public class UserDetails {

	private String firstName;
	private String lastName;
	private String cityName;
	
	
	
	@Override
	public String toString() {
		return "UserDetails [firstName=" + firstName + ", lastName=" + lastName + ", cityName=" + cityName + "]";
	}
	public UserDetails(String firstName, String lastName, String cityName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.cityName = cityName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
}
