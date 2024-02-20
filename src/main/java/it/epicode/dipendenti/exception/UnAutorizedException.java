package it.epicode.dipendenti.exception;

public class UnAutorizedException extends RuntimeException {
    public UnAutorizedException(String message){
        super(message);
    }
}
