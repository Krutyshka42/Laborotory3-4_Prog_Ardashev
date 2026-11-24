package addedexceptions;

public class FruitRejectedException extends RuntimeException {
    public FruitRejectedException(String message) {
        super(message);
    }
}
