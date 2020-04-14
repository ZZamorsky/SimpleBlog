package com.tieto.javabootcamp.model.text;

import java.time.LocalDateTime;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.tieto.javabootcamp.model.user.User;

@MappedSuperclass
public abstract class Message extends TextContainer {

	@ManyToOne
	@JoinColumn(name = "author")
	private User author;

	private LocalDateTime createdAt;

    public User getAuthor() {
        return author;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setAuthor(User author) {
		this.author = author;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
