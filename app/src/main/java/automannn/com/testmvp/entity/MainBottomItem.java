package automannn.com.testmvp.entity;

public class MainBottomItem extends SerializableObject{
    private String iconUrl;
    private String text;

    public MainBottomItem() {
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
