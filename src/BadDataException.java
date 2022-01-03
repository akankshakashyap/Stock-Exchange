public class BadDataException extends Exception {
    BadDataException(String error) {
        super(error);
    }

    BadDataException() {
        super();
    }

}
