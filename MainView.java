import java.util.Scanner;

public class MainView {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your factory name: ");
        String name = sc.nextLine();
        Factory factory = new Factory(name);
        printInstructions(name);

        int mainMenuChoice;

        do {
            printMainMenu();
            mainMenuChoice = sc.nextInt();

            switch(mainMenuChoice) {
                case 1: 
                    int creationMenuChoice;
                    do {
                        printCreationMenu();
                        creationMenuChoice = sc.nextInt();
                    } while (creationMenuChoice != 3);
                case 2:
                    int maintenanceMenuChoice;
                    do {
                        printMaintenanceMenu();
                        maintenanceMenuChoice = sc.nextInt();
                    } while (maintenanceMenuChoice != 5);
                case 3: 
                    break;
            }
        } while (mainMenuChoice != 3);

        sc.close();
        System.out.println("Thank you for using " + name + " factory!");
    }

    public static void printInstructions(String factoryName){
        System.out.println("Welcome to " + factoryName + ", Your very own vending machine Factory!");
        System.out.println("You have the ability to create a regular vending machine or a special vending machine.");
        System.out.println("A regular vending machine has a maximum of 8 slots and a special vending machine has no maximuim.");
        System.out.println("You can test your vending machine's features by buying items from it.");
        System.out.println("You can perform maintenance by replenishing the money in the vending machine's cash register, changing the price of an item, and restocking certain items. Additionally, you can print the transaction history from the last restock.");
    }

    public static void printMainMenu(){
        System.out.println("MAIN MENU");
        System.out.println("1. Create Vending Machine");
        System.out.println("2. Test Vending Machine");
        System.out.println("3. Exit Program");
    }

    public static void printCreationMenu(){
        System.out.println("VENDING MACHINE CREATION");
        System.out.println("1. Create Regular Vending Machine");
        System.out.println("2. Create Special Vending Machine");
        System.out.println("3. Back to Main Menu");
    }

    public static void printMaintenanceMenu(){
        System.out.println("VENDING MACHINE MAINTENANCE");
        System.out.println("1. Replenish Money");
        System.out.println("2. Change Item Price");
        System.out.println("3. Restock Items");
        System.out.println("4. Print Transaction History");
        System.out.println("5. Back to Main Menu");
    }

    public static void printAdditionalInstructions() {
        System.out.println("HOW DOES THIS WORK?");
    }

    public VendingMachine initializeAdoboVendingMachine(){
        //TODO: initialize AdoboVendingMachine
        return new RegularVendingMachine("Adobo Vending Machine");
    }
}
