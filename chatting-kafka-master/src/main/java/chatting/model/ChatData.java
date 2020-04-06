package chatting.model;

public class ChatData {
    private String text;
    public String getText() {
        return  text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public  ChatData() {}

    public ChatData(String text) {
        this.text = text;
    }
}
