package model.text;

import java.time.LocalDateTime;

public class Article extends Message{

    public static final String NO_COMMENTS = "No Comments so far.";

    private String category;
    private String[] comments;

    public Article(String content, String author, String category) {
        super(content, author);
        this.category = category;
        this.comments = new String[] { NO_COMMENTS };
    }

    public String getCategory() {
        return category;
    }

    public String[] getComments() {
        return comments;
    }

    public void setComments(String[] comments) {
        this.comments = comments;
    }

}
