package model;

public class Notification {
    private int notificationId;
    private String title;
    private String message;
    private boolean isRead;

    public Notification() {}

    public Notification(String title, String message) {
        this.title = title;
        this.message = message;
        this.isRead = false;
    }

    public int getNotificationId() { return notificationId; }
    public void setNotificationId(int notificationId) { this.notificationId = notificationId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public boolean isRead() { return isRead; }
    public void setRead(boolean read) { isRead = read; }
}
