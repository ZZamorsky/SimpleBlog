package com.tieto.javabootcamp.Component;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.Comment;

@Component
public class AccessRights {
	
	public Boolean isAproved(Article article, User user) {
		boolean isAproved = false;
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			isAproved = true;
		else if (article.getAuthor().getName().equals(user.getUsername())) 
			isAproved = true;		
		return isAproved;		
	}

	public boolean isAproved(Comment comment, User user) {
		boolean isAproved = false;
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			isAproved = true;
		else if (comment.getAuthor().getName().equals(user.getUsername())) 
			isAproved = true;		
		return isAproved;	
	}
}
