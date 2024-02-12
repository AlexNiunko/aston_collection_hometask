package ru.aston.collections_hometask;

public class MyListException extends Exception{
    public MyListException() {
        super();
    }

    public MyListException(String message) {
        super(message);
    }

    public MyListException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyListException(Throwable cause) {
        super(cause);
    }
}
