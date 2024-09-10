package com.isi.monothique.exception;

public class NameConflictException extends RuntimeException{
    public NameConflictException(String message){
        super(message);
    }
}
