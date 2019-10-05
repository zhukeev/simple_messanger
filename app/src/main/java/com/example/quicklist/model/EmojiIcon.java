package com.example.quicklist.model;

public class EmojiIcon {

    String urlToServer;
    String localPath;

    public EmojiIcon(String urlToServer, String localPath) {
        this.urlToServer = urlToServer;
        this.localPath = localPath;
    }

    public String getUrlToServer() {
        return urlToServer;
    }

    public void setUrlToServer(String urlToServer) {
        this.urlToServer = urlToServer;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
