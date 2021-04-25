package project.rindus.springBackend.exception;

public class CallFailedException extends Exception{
    Exception exception;
    public CallFailedException(Exception exception) {
        super ("Call failed to JsonPlaceHolder");
        this.exception = exception;
    }
}
