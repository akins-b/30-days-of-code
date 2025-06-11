package com.__days_of_code.social.media.dto.request;

public class QueryPostComment {
    int page = 1;
    int size = 25;
    long postId;

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
    public long getPostId() {
        return postId;
    }
    public void setPostId(long postId) {
        this.postId = postId;
    }
}
