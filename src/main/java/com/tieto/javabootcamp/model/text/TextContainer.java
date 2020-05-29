package com.tieto.javabootcamp.model.text;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.tieto.javabootcamp.model.user.User;

/**
 * This is JavaDoc comment added by Pavel Mlcuch
 */
/*
 * id bigserial
 * content text
 * author text
 * createdAt datetime
 * articleID biginteger
 * recipient text
 */
@Entity
@Table(name="text_container")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="text_container_type", discriminatorType=DiscriminatorType.STRING, length=20)
public abstract class TextContainer {

	@SequenceGenerator(name = "text_container_seq", allocationSize = 1, sequenceName = "...")
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "text_container_seq")
	private Long id;
	
	@Column(name="text_content", length = 65536)
	private String content;
	
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

    public String getContent() {
        return content;
    }

	public void setContent(String content) {
		this.content = content;
	}
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
