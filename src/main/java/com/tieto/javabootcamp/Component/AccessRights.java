package com.tieto.javabootcamp.Component;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import com.tieto.javabootcamp.model.text.Article;
import com.tieto.javabootcamp.model.text.Comment;

@Component
public class AccessRights {
	
	public Boolean isApproved(Article article, User user) {
		boolean isApproved = false;
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			isApproved = true;
		else if (article.getAuthor().getName().equals(user.getUsername())) 
			isApproved = true;
		return isApproved;
	}

	public boolean isApproved(Comment comment, User user) {
		boolean isApproved = false;
		if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN")))
			isApproved = true;
		else if (comment.getAuthor().getName().equals(user.getUsername())) 
			isApproved = true;
		return isApproved;
	}
}
