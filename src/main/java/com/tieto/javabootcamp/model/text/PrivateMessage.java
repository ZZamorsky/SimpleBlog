package com.tieto.javabootcamp.model.text;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.tieto.javabootcamp.model.user.User;

@Entity
@DiscriminatorValue("private_message")
public class PrivateMessage extends Message {

	@ManyToOne
	@JoinColumn(name = "recipient")
    private User recipient;

    public User getRecipient() {
        return recipient;
    }

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

    
}
