package OnlineStore;

import OnlineStore.exceptions.EmptyCartException;
import OnlineStore.exceptions.ValidCategory;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SystemManager systemManager = new SystemManager();
        printMenu(systemManager);
    }

    static Scanner s = new Scanner(System.in);

    private static boolean ifCategoryExist(String category){
        for (Product.eCategories c : Product.eCategories.values()) {
            if (c.name().equals(category)) {
                return true;
            }
        }
        return false;
    }

    private static Product.eCategories ifCategoryProductValid(){
        Product.eCategories category = null;
        boolean validCategory = false;
        do{
            System.out.println("Enter product category (Kids, Electricity, Office, Cloths): ");
            String categoryStr = s.next();
            s.nextLine();
            try{
                if (!(ifCategoryExist(categoryStr))){
                    throw new ValidCategory(categoryStr);
                }else{
                    category = Product.eCategories.valueOf(categoryStr);
                    validCategory = true;
                }
            }catch (ValidCategory e){
                System.out.println(e.getMessage());
            }
        }while(!validCategory);
    return category;
    }

    private static String chooseSellerByNumber(SystemManager systemManager) {
        boolean validInput = false;
        String username = "";
        int number;

        while (!validInput) {
            try {
                System.out.println("Enter the number of the seller: ");
                number = s.nextInt();
                s.nextLine();
                username = systemManager.chooseSellerByNumber(number);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a valid number.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return username;
    }

    private static String chooseBuyerByNumber(SystemManager systemManager) {
        boolean validInput = false;
        String username = "";
        int number;

        while (!validInput) {
            try {
                System.out.println("Enter the number of the buyer: ");
                number = s.nextInt();
                s.nextLine();
                username = systemManager.chooseBuyerByNumber(number);
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a valid number.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return username;
    }

    private static String chooseProductByNumber(SystemManager systemManager, String sellerUserName) {
        boolean validInput = false;
        String productName= "";
        int productNumber;

        while (!validInput) {
            try{
                System.out.println("╔═════════════════════════════════════╗");
                System.out.println("      Products of " + sellerUserName + ":");
                System.out.println("╚═════════════════════════════════════╝");
                displayProductSeller(systemManager,sellerUserName);
                System.out.println("Enter the number of the product: ");
                productNumber = s.nextInt();
                s.nextLine();
                productName = systemManager.chooseProductByNumber(productNumber, sellerUserName);
                validInput = true;

            } catch (InputMismatchException e) {
                System.out.println("Invalid input type. Please enter a valid number.");
                s.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return productName;
    }

    private static void showSellers(SystemManager systemManager) {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("               Sellers:                 ");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println(systemManager.showSellers());
    }

    private static void showBuyers(SystemManager systemManager) {
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("               Buyers:                 ");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println(systemManager.showBuyers());
    }

    private static void displayProductSeller(SystemManager systemManager, String userNameSeller) {
        System.out.println(systemManager.showProductsSeller(userNameSeller));
    }

    private static void addSeller(SystemManager systemManager) {
        String sellerUserName = "";
        boolean isNameExist = true;
        boolean isTaken;

        System.out.println("Enter username of seller: ");
        do{
            sellerUserName= s.nextLine();
            isTaken = systemManager.ifNameIsExistInSellers(sellerUserName);
            if (isTaken) {
                System.out.println("This name is taken. You need to enter new username of seller: ");
            }else{
                isNameExist = false;
            }
        }while(isNameExist);

        System.out.println("Enter password of seller: ");
        String sellerPassword = s.nextLine();
        systemManager.addSeller(sellerUserName, sellerPassword);
    }

    private static void addBuyer(SystemManager systemManager) {
        String buyerUserName;
        boolean isNameExist = true;
        boolean isTaken;
        boolean numOk = true;
        int buildingNumber = 0;
        System.out.println("Enter username of buyer: ");
        do{
            buyerUserName= s.nextLine();
            isTaken = systemManager.ifNameIsExistInBuyers(buyerUserName);
            if (isTaken) {
                System.out.println("This name is taken. You need to enter new username of buyer: ");
            }else{
                isNameExist = false;
            }
        }while(isNameExist);

        System.out.println("Enter password of buyer: ");
        String buyerPassword = s.nextLine();
        System.out.println("Enter country: ");
        String country = s.nextLine();
        System.out.println("Enter city: ");
        String city = s.nextLine();
        System.out.println("Enter street: ");
        String street = s.nextLine();
        do{
            try{
                System.out.println("Enter building number: ");
                buildingNumber = s.nextInt();
                s.nextLine();
                systemManager.CheckPositiveNumbers(buildingNumber);
                numOk = false;
            }catch (InputMismatchException e){
                s.nextLine();
                System.out.println("Building number can not be string.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while(numOk);

        Address address = new Address(country, city, street, buildingNumber);
        systemManager.addBuyer(buyerUserName, buyerPassword,address);
    }

    private static boolean ValidationPrice(SystemManager systemManager,String price, boolean invalidPrice){
        try{
            double priceProduct = Double.parseDouble(price);
            systemManager.CheckPrice(priceProduct);
            invalidPrice = false;
        }catch (NumberFormatException e){
            System.out.println("invalid price type.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return invalidPrice;
    }

    private static boolean validBoolean(String message) {
        boolean isValid = false;
        boolean input = false;

        while (!isValid) {
            System.out.println(message);
            try {
                input = s.nextBoolean();
                s.nextLine();
                isValid = true;
            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("Invalid input. Please enter true or false.");
            }catch (Exception e) {
            s.nextLine();
            System.out.println(e.getMessage());
            }
        }
        return input;
    }

    private static void addProductToSeller(SystemManager systemManager) {
        String productName;
        String price;

        boolean doSpecialPackaging;
        boolean invalidPrice = true;
        double productPriceNew = 0;
        double specialPackagePrice = 0;

        String message = "Does the product have special packaging? (true/false): ";
        if (!systemManager.ifThereIsSeller()){
            System.out.println("There are no sellers yet.");
        }else{
            showSellers(systemManager);
            String sellerUserName = chooseSellerByNumber(systemManager);

            System.out.println("Enter product name: ");
            productName = s.nextLine();
            do{
                System.out.println("Enter product price: ");
                price = s.nextLine();
                invalidPrice = ValidationPrice(systemManager, price, invalidPrice);
                if(!invalidPrice){
                    productPriceNew = Double.parseDouble(price);
                }

            }while(invalidPrice);

            Product.eCategories category = ifCategoryProductValid();

            doSpecialPackaging = validBoolean(message);

            invalidPrice = true;

            if(doSpecialPackaging){
                do{
                    System.out.println("Enter the price of the special package: ");
                    price = s.nextLine();
                    invalidPrice = ValidationPrice(systemManager, price, invalidPrice);
                    if(!invalidPrice){
                        specialPackagePrice = Double.parseDouble(price);
                    }

                }while(invalidPrice);
            }

            invalidPrice = true;
            while(!systemManager.addProductToSeller(sellerUserName, productName, productPriceNew, category, specialPackagePrice, doSpecialPackaging)){
                do{
                    System.out.println("You have this product with the same price. Enter new product: ");
                    productName = s.nextLine();

                    System.out.println("Enter product price: ");
                    price = s.nextLine();
                    invalidPrice = ValidationPrice(systemManager, price, invalidPrice);
                    if(!invalidPrice){
                        productPriceNew = Double.parseDouble(price);
                    }

                }while(invalidPrice);
            }
            System.out.println("Product " + productName + " added to seller " + sellerUserName + " successfully.");
        }
    }

    private static void addProductToBuyer(SystemManager systemManager) {
        if (!systemManager.ifThereIsBuyer()){
            System.out.println("There are no buyers yet.");
        }else{
            showBuyers(systemManager);
            String buyerUsername = chooseBuyerByNumber(systemManager);
            if (systemManager.ifThereIsSeller()){
                showSellers(systemManager);
                String sellerUserToBuy = chooseSellerByNumber(systemManager);
                if (!systemManager.ifThereIsProductsSeller(sellerUserToBuy)){
                    System.out.println("This seller has no products.");
                }else{
                    String productToBuy = chooseProductByNumber(systemManager, sellerUserToBuy);
                    while (!systemManager.addProductToBuyer(buyerUsername, sellerUserToBuy, productToBuy)) {
                        System.out.println("Failed to add product " + productToBuy + " to buyer " + buyerUsername + ".");
                        System.out.println("Enter new product name: ");
                        productToBuy = s.nextLine();
                    }
                    System.out.println("Product " + productToBuy + " added to buyer " + buyerUsername + " successfully.");
                }
            }else {
                System.out.println("There are no sellers yet.");
            }
        }
    }

    private static void paymentForBuyer(SystemManager systemManager){
        boolean invalidCart = true;
        String buyerUserForPayment = "";
        if (!systemManager.ifThereIsBuyer()) {
            System.out.println("There are no buyers yet.");
        }else {
            try {
                showBuyers(systemManager);
                buyerUserForPayment = chooseBuyerByNumber(systemManager);
                if (!systemManager.ifThereIsProductsInCart(buyerUserForPayment)) {
                    invalidCart = false;
                    throw new EmptyCartException(buyerUserForPayment);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            if (invalidCart) {
                double totalPrice = systemManager.paymentForBuyer(buyerUserForPayment);
                System.out.println("Total Price: " + totalPrice);
                System.out.println("Payment successful. The cart has been cleared and the order has been saved.");
            }
        }
    }


    private static void displayBuyers(SystemManager systemManager) {
        if (!systemManager.ifThereIsBuyer()) {
            System.out.println("There are no buyers to display.");
        } else {
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("             Buyers Details         ");
            System.out.println("╚═════════════════════════════════════╝");
            Buyer[] buyers = systemManager.getBuyers();
            Arrays.sort(buyers);
            int buyerNumber = 1;
            for (int i = 0; i < buyers.length; i++) {
                System.out.print(buyerNumber + ") " + buyers[i]);
                buyerNumber++;
            }
        }
    }

    public static void displaySellers(SystemManager systemManager) {
        if (!systemManager.ifThereIsSeller()){
            System.out.println("There are no sellers to display.");
        }else{
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("           Sellers Details          ");
            System.out.println("╚═════════════════════════════════════╝");
            Seller[] sellers = systemManager.getSellers();
            Arrays.sort(sellers);
            int sellerNumber = 1;
            for (int i = 0; i < sellers.length; i++) {
                System.out.println(sellerNumber + ") " + sellers[i]);
                sellerNumber++;
            }
        }
    }

    public static void displayProductsByCategory(SystemManager systemManager) {
        Product.eCategories category = ifCategoryProductValid();
        System.out.println("╔═════════════════════════════════════╗");
        System.out.println("     Products of " + category + ":");
        System.out.println("╚═════════════════════════════════════╝");
        System.out.println(systemManager.getProductsByCategory(category));
    }

    private static void createNewCartFromHistory(SystemManager systemManager) {
        String buyerUserName ;
        boolean answer;

        String message1 = "You have products in your current cart. Do you want to replace them from one of your history orders? (true/false)";
        String message2 = "Do you want to take one of your history orders? (true/false)";

        if (!systemManager.ifThereIsBuyer()) {
            System.out.println("There are no buyers yet.");
        }else{
            showBuyers(systemManager);
            buyerUserName = chooseBuyerByNumber(systemManager);
            if (!systemManager.ifThereIsHistoryOrders(buyerUserName)) {
                System.out.println("You don't have history orders.");
            }else{
                try {
                    if (!systemManager.ifThereIsProductsInCart(buyerUserName)) {
                        throw new EmptyCartException(buyerUserName);
                    }
                    answer = validBoolean(message1);

                    if (answer) {
                        createCart(systemManager, buyerUserName);
                    }
                } catch (EmptyCartException e) {
                    System.out.println(e.getMessage());
                    answer = validBoolean(message2);
                    if (answer){
                        createCart(systemManager, buyerUserName);
                    }
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private static void createCart(SystemManager systemManager, String buyerUserName) {
        boolean validInput = false;
        int historyNumber;
        do{
            try{
                System.out.println(systemManager.ShowOrderHistory(buyerUserName));
                System.out.println("Enter the number of the order history to use: ");
                historyNumber = s.nextInt();
                s.nextLine();
                systemManager.creatCart(historyNumber,buyerUserName);
                validInput = true;
            }catch (InputMismatchException e){
                System.out.println("Invalid input type. Please enter a valid number.");
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }while (!validInput);

        System.out.println("Your wish cart created successfully.");
    }

    private static void validNumbersInMenu(int choice) throws IllegalArgumentException{
        if (choice < 0 || choice > 9){
            throw new IllegalArgumentException();
        }
    }

    private static void printMenu(SystemManager systemManager) {
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("╔═════════════════════════════════════╗");
            System.out.println("║                 Menu                ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║ 0 - Exit                            ║");
            System.out.println("║ 1 - Add seller                      ║");
            System.out.println("║ 2 - Add buyer                       ║");
            System.out.println("║ 3 - Add product to a seller         ║");
            System.out.println("║ 4 - Add product to a buyer          ║");
            System.out.println("║ 5 - Order payment for a buyer       ║");
            System.out.println("║ 6 - View the details of all buyers  ║");
            System.out.println("║ 7 - View the details of all sellers ║");
            System.out.println("║ 8 - View all products from category ║");
            System.out.println("║ 9 - Create new cart from history    ║");
            System.out.println("╚═════════════════════════════════════╝");
            System.out.print("Enter your choice: ");
            int choice;

            try {
                choice = s.nextInt();
                s.nextLine();
                validNumbersInMenu(choice);
                switch (choice) {
                    case 0:
                        continueProgram = false;
                        s.close();
                        break;
                    case 1:
                        addSeller(systemManager);
                        break;
                    case 2:
                        addBuyer(systemManager);
                        break;
                    case 3:
                        addProductToSeller(systemManager);
                        break;
                    case 4:
                        addProductToBuyer(systemManager);
                        break;
                    case 5:
                        paymentForBuyer(systemManager);
                        break;
                    case 6:
                        displayBuyers(systemManager);
                        break;
                    case 7:
                        displaySellers(systemManager);
                        break;
                    case 8:
                        displayProductsByCategory(systemManager);
                        break;
                    case 9:
                        createNewCartFromHistory(systemManager);
                        break;
                }
            } catch (InputMismatchException e) {
                s.nextLine();
                System.out.println("Wrong input. Please enter a number between 0 - 9.");
            } catch (IllegalArgumentException e) {
                System.out.println("You must enter valid number between 0 - 9.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Goodbye!");
    }
}