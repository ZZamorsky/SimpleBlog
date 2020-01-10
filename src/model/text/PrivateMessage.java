package model.text;

public class PrivateMessage extends Message {

    private String recipient;

    public PrivateMessage(String content, String author, String recipient) {
        super(content, author);
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

}
