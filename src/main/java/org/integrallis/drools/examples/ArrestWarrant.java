package org.integrallis.drools.examples;

public class ArrestWarrant {
    private Person person;
    private String reason;
    private Double amount;
    
	public ArrestWarrant(Person person, String reason, Double amount) {
		this.person = person;
		this.reason = reason;
		this.amount = amount;
	}

	public Person getPerson() {
		return person;
	}

	public String getReason() {
		return reason;
	}

	public Double getAmount() {
		return amount;
	}
	
	public String toString() {
		return "Arrest Warrant issued for " + person.getName() + " for " + reason + " because they racked up " + amount;
	}
}
