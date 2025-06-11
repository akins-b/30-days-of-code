package com.__days_of_code.social.media.dto.request;

public class QueryPosts {
    private int page = 1;
    private int size = 25;

    // Getters and Setters
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
