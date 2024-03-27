package com.contact;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String mobile;
    
    public AddressBook() {
    	
    }
    
    public AddressBook(Long id, String firstName, String mobile) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.mobile = mobile;
	}
    
	public AddressBook(String firstName, String mobile) {
		super();
		this.firstName = firstName;
		this.mobile = mobile;
	}
    
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, mobile);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AddressBook other = (AddressBook) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(mobile, other.mobile);
	}
	
}
