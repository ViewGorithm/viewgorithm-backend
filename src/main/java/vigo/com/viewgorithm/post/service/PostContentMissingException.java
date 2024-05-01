package vigo.com.viewgorithm.post.service;

public class PostContentMissingException extends RuntimeException {
    public PostContentMissingException(String message) {
        super(message);
    }
}