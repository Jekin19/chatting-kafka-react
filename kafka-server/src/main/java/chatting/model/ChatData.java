package chatting.model;

public class ChatData {
    private String text;
    public String getText() {
        return  text;
    }
    public void setText(String text) {
        this.text = text;
    }

    private String emoji;
    public String getEmoji() {
        return emoji;
    }
    public void setEmoji(String emoji) {
        this.emoji = emoji;
    }

    private String url;
    public String getUrl() {
        return  url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    private String fileName;
    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public  ChatData() {}
    public ChatData(String text) {
        this.text = text;
    }
}
