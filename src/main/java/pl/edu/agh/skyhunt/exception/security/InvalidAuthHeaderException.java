package pl.edu.agh.skyhunt.exception.security;

public class InvalidAuthHeaderException extends Exception {

    public InvalidAuthHeaderException() {
        super("Invalid authorization header");
    }
}
