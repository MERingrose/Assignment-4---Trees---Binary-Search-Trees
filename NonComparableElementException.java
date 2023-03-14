public class NonComparableElementException extends RuntimeException{

    public NonComparableElementException(String e){
        super(e);
    }

    public NonComparableElementException(){
        super();
    }
    
    public NonComparableElementException(Throwable problem){
        super(problem);
    }
    
}
