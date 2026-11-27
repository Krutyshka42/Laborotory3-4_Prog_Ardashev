package addedexceptions;

public class FruitRejectedException extends Exception {
    public FruitRejectedException(String message) {
        super(message);
    }

    @Override
    public String getMessage(){
        return "Не получилось положить фрукт. "+super.getMessage();
    }
}
