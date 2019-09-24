package az.pashabank.ls.msstudent.exception;

public class ClientException extends RuntimeException {

    public ClientException() {
        super("exception.student.client-fail- Error with client integration");
    }
}
