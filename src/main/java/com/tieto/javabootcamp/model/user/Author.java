package com.tieto.javabootcamp.model.user;

public class Author extends RegisteredUser {

    private int countOfArticles = 0;

    public Author(String nickname, String name, String surname, String email, String password) {
        super(nickname, name, surname, email, password);
    }

    public int getCountOfArticles() {
        return countOfArticles;
    }

    public void setCountOfArticles(int countOfArticles) {
        this.countOfArticles = countOfArticles;
    }
}
