package vigo.com.viewgorithm.post.error;

public class PostContentMissingException extends RuntimeException {
    public PostContentMissingException(String message) {
        super(message);
    }
}