package com.example;

public class Customer {
    private long id;
    private String first_name, last_name;
    
    public Customer() {
    	
    }

    public Customer(long id, String firstName, String lastName) {
        this.id = id;
        this.first_name = firstName;
        this.last_name = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, first_name, last_name);
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

    // getters & setters omitted for brevity
}