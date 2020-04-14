package com.tieto.javabootcamp.model.text;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("comment")
public class Comment extends Message {

    private Long articleID;
  
    public Long getArticleID() {
	  	return articleID;
    }

	public void setArticleID(Long articleID) {
		this.articleID = articleID;
	}
    
}
