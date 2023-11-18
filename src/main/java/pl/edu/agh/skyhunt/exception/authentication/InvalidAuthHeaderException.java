package pl.edu.agh.skyhunt.exception.authentication;

public class InvalidAuthHeaderException extends Exception {

    public InvalidAuthHeaderException() {
        super("Invalid authorization header");
    }
}
