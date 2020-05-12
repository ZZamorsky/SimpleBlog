package com.tieto.javabootcamp.model.text;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("article")
public class Article extends Message {

    private String category;
    
    @OneToMany
    @JoinColumn(name = "articleid")
    private List<Comment> comments;

    public void setCategory(String category) {
		this.category = category;
	}

	public String getCategory() {
        return category;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

}
