package chatting.model;


public class ChatMessage {
    private String author;
    public String getAuthor() {
        return  author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    private ChatData data;
    public ChatData getData() {
        return data;
    }
    public void setData(ChatData data) {
        this.data = data;
    }

    private String type = "text";
    public String getType() {
        return type;
    }
    public ChatMessage(){}

    public ChatMessage(String text, String author) {
        this.author = author;
        this.data = new ChatData(text);
    }
}
