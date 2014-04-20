package ru.project.phonebook.PhoneBookGwt.client;

public class PhoneBookSet {
	
	public PhoneBookSet() {
		
	}

	private String name;
	private String phoneNumber;
	private String eMail;
	
	public PhoneBookSet( String name, String phoneNumber, String eMail) {
		this.name = name ;
		this.phoneNumber = phoneNumber;
		this.eMail = eMail ;
	}
	
	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getEMail() {
		return eMail;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

}
