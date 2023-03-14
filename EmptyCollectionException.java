public class EmptyCollectionException extends RuntimeException{

    public EmptyCollectionException() {
    }

    public EmptyCollectionException(String collection) {
        super("the " + collection + "is empty.");
    }
    
}
