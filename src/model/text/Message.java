package model.text;

import java.time.LocalDateTime;

public class Message extends TextContainer{

    private String author;
    private LocalDateTime createdAt;

    public Message(String content, String author) {
        super(content);
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
