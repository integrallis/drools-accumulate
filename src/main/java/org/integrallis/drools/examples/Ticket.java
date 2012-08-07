package org.integrallis.drools.examples;

public class Ticket {
	private Person driver;
	private Double amount;
	private String reason;
	private Boolean friendsOfTheSheriff;

	public Ticket(Person driver, Double amount, String reason, Boolean friendsOfTheSheriff) {
		super();
		this.driver = driver;
		this.amount = amount;
		this.reason = reason;
		this.friendsOfTheSheriff = friendsOfTheSheriff;
	}

	public Person getDriver() {
		return driver;
	}

	public Double getAmount() {
		return amount;
	}

	public String getReason() {
		return reason;
	}

	public Boolean getFriendsOfTheSheriff() {
		return friendsOfTheSheriff;
	}
}
