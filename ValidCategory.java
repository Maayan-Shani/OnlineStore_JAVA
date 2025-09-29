package OnlineStore.exceptions;

public class ValidCategory extends Exception{
    public ValidCategory(String category){
        super("The category : " + category + " invalid. The categories are: Kids, Electricity, Office, Cloths. Please try again.");
    }
}
