package pl.edu.agh.skyhunt.exception.security;

public class NoLoggedUserException extends Exception {

    public NoLoggedUserException() {
        super("There is no logged user");
    }
}
