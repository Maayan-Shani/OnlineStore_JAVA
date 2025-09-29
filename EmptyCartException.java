package OnlineStore.exceptions;

public class EmptyCartException extends Exception{
    public EmptyCartException(String buyerName){
        super("The cart of buyer: " + buyerName + " is empty.");
    }
}
