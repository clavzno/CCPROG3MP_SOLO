import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static final String ANSI_OFF = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args) {
        int firstChoice;
        int secondChoice;
        int thirdChoice;
        int buyOption;
        Scanner scan = new Scanner(System.in);
        Factory factory = new Factory();
        Misc misc = new Misc();

        /* MADE ONLY FOR DEMO */
        ArrayList<Item> premadeItems = initalizeAdoboVendingMachineItems();
        factory.createRegularVendingMachine("Adobo Vending Machine", premadeItems);
        /* END PREMADE ADOBO VENDING */

        do {
            misc.clearScreen();
            System.out.println(ANSI_GREEN + "WELCOME TO THE VENDING MACHINE FACTORY" + ANSI_OFF);
            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
            System.out.print("[1] CREATE A NEW VENDING MACHINE\n[2] TEST VENDING MACHINES\n[3] EXIT\n");
            System.out.println("Enter choice here: ");
            firstChoice = scan.nextInt();

            switch (firstChoice) {
                case 1:
                    System.out.println("WHAT KIND OF MACHINE WOULD YOU LIKE TO CREATE?");
                    System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                    System.out.println("[1] REGULAR\n[2] SPECIAL\n[3] EXIT");
                    secondChoice = scan.nextInt();
                    scan.nextLine();
                    switch (secondChoice) {
                        case 1:
                            System.out.println("ENTERING REGULAR MACHINE CREATION");
                            misc.pauseScreen2Sec();
                            misc.clearScreen();

                            String[] itemNames = new String[8];
                            double[] itemPrices = new double[8];
                            int[] itemCalorieCounts = new int[8];
                            ArrayList<Item> itemList = new ArrayList<Item>();

                            System.out.println("Enter the vending machine name:");
                            String vendingMachineName = scan.nextLine();

                            for (int i = 0; i < 8; i++) {
                                System.out.println("Enter Item " + (i + 1) + " name:");
                                itemNames[i] = scan.nextLine();
                                System.out.println("Enter Item " + (i + 1) + " price:");
                                itemPrices[i] = scan.nextDouble();
                                scan.nextLine();
                                System.out.println("Enter Item " + (i + 1) + " calorie count:");
                                itemCalorieCounts[i] = scan.nextInt();
                                scan.nextLine();
                                Item item = new Item(itemNames[i], itemPrices[i], itemCalorieCounts[i]);
                                itemList.add(item);
                            }

                            factory.createRegularVendingMachine(vendingMachineName, itemList);
                            System.out.println("VENDING MACHINE " + vendingMachineName + " CREATED");
                            misc.pauseScreen2Sec();
                            break;
                        case 2:
                            System.out.println("ERROR: SPECIAL MACHINE CREATION NOT YET IMPLEMENTED");
                            misc.pauseScreen2Sec();
                            break;
                        case 3: // exit
                            break;
                        default:
                            System.out.println("ERROR: INVALID CHOICE");
                            misc.pauseScreen2Sec();
                            break;
                    }
                    break;
                case 2:
                    System.out.println("WHAT TESTING MODE WOULD YOU LIKE TO ENTER?");
                    System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                    System.out.println("[1] VENDING FEATURES\n[2] MAINTENANCE FEATURES\n[3] EXIT");
                    secondChoice = scan.nextInt();
                    switch (secondChoice) {
                        case 1:
                            System.out.println("ENTERING VENDING MACHINE TESTING MODE");
                            misc.pauseScreen2Sec();
                            misc.clearScreen();
                            System.out.println("WHAT KIND OF VENDING MACHINE WOULD YOU LIKE TO TEST?");
                            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                            System.out.println("[1] REGULAR\n[2] SPECIAL\n[3] EXIT");
                            thirdChoice = scan.nextInt();
                            switch (thirdChoice) {
                                case 1:
                                    System.out.println("REGULAR VENDING MACHINES");
                                    misc.pauseScreen2Sec();
                                    ArrayList<RegularVendingMachine> vendingMachines = factory.getVendingMachines();
                                    int machineIndex = 1;
                                    for (RegularVendingMachine vendingMachine : vendingMachines) {
                                        System.out.println(machineIndex + ") " + vendingMachine.getName());
                                        machineIndex++;
                                    }
                                    System.out.println("Enter the index of the vending machine you want to test:");
                                    int vendingMachineIndex = scan.nextInt();
                                    scan.nextLine();
                                    RegularVendingMachine chosenVendingMachine = vendingMachines
                                            .get(vendingMachineIndex - 1);
                                    System.out
                                            .println("VENDING MACHINE " + chosenVendingMachine.getName() + " SELECTED");
                                    misc.pauseScreen2Sec();
                                    System.out.println("WHAT WOULD YOU LIKE TO DO?");
                                    System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                                    System.out.println("[1] PURCHASE ITEM\n[2] EXIT");
                                    buyOption = scan.nextInt();

                                    switch (buyOption) {
                                        case 1:
                                            System.out.println("WHAT ITEM WOULD YOU LIKE TO PURCHASE?");
                                            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);

                                            // display all items
                                            int itemIndexForThis = 0;
                                            for (Item item : chosenVendingMachine.getAllItems()) {
                                                System.out.println(itemIndexForThis + ") " + item.getName());
                                                System.out.println("Price: " + item.getPrice());
                                                System.out.println("Calorie Count: " + item.getCalorieCount());
                                                itemIndexForThis++;
                                            }

                                            // select item
                                            System.out.println("Enter the index of the item you want to purchase:");
                                            int itemIndex = scan.nextInt();
                                            scan.nextLine();
                                            Item chosenItem = chosenVendingMachine.getAllItems().get(itemIndex - 1);

                                            // chosen item
                                            System.out.println("You have chosen " + chosenItem.getName());
                                            System.out.println("Price: " + chosenItem.getPrice());
                                            System.out.println("Calorie Count: " + chosenItem.getCalorieCount());

                                            // insert money
                                            System.out.println("INSERT DENOMINATION:");
                                            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                                            System.out.println(
                                                    "[1] 1 PESO\n[2] 5 PESOS\n[3] 10 PESOS\n[4] 20 PESOS\n[5] 50 PESOS\n[6] 100 PESOS\n[7] 500 PESOS\n[8] 1000 PESOS\n[9] CANCEL");
                                            int moneyChoice = scan.nextInt();
                                            scan.nextLine();

                                            switch (moneyChoice) {
                                                case 1:
                                                    // Insert 1 Peso
                                                    chosenVendingMachine.restockChange(1.0, 1);
                                                    break;
                                                case 2:
                                                    // Insert 5 Pesos
                                                    chosenVendingMachine.restockChange(5.0, 1);
                                                    break;
                                                case 3:
                                                    // Insert 10 Pesos
                                                    chosenVendingMachine.restockChange(10.0, 1);
                                                    break;
                                                case 4:
                                                    // Insert 20 Pesos
                                                    chosenVendingMachine.restockChange(20.0, 1);
                                                    break;
                                                case 5:
                                                    // Insert 50 Pesos
                                                    chosenVendingMachine.restockChange(50.0, 1);
                                                    break;
                                                case 6:
                                                    // Insert 100 Pesos
                                                    chosenVendingMachine.restockChange(100.0, 1);
                                                    break;
                                                case 7:
                                                    // Insert 500 Pesos
                                                    chosenVendingMachine.restockChange(500.0, 1);
                                                    break;
                                                case 8:
                                                    // Insert 1000 Pesos
                                                    chosenVendingMachine.restockChange(1000.0, 1);
                                                    break;
                                                case 9:
                                                    // Dispense change
                                                    System.out.println("Transaction cancelled. Returning change...");
                                                    break;
                                                default:
                                                    System.out.println("Invalid choice. Please try again.");
                                                    break;
                                            } // end moneyChoice switch case
                                    } // end buyOption switch case
                                    break;
                                case 2: // display special vending machines
                                    System.out.println("ERROR: SPECIAL MACHINE TESTING NOT YET IMPLEMENTED");
                                    misc.pauseScreen2Sec();
                                    break;
                                case 3: // exit
                                    break;
                                default:
                                    System.out.println("ERROR: INVALID CHOICE");
                                    misc.pauseScreen2Sec();
                                    break;
                            } // end thirdChoice switch case

                            break;
                        case 2:
                            System.out.println("ENTERING MAINTENANCE TESTING MODE");
                            misc.pauseScreen2Sec();
                            System.out.println("WHAT KIND OF VENDING MACHINE WOULD YOU LIKE TO DO MAINTENANCE ON?");
                            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                            System.out.println("[1] REGULAR\n[2] SPECIAL\n[3] EXIT");
                            int fourthChoice = scan.nextInt();
                            switch (fourthChoice) {
                                case 1:
                                    // display vending machines and choose index
                                    ArrayList<RegularVendingMachine> vendingMachines = factory.getVendingMachines();
                                    int machineIndex = 1;
                                    for (RegularVendingMachine vendingMachine : vendingMachines) {
                                        System.out.println(machineIndex + ") " + vendingMachine.getName());
                                        machineIndex++;
                                    }
                                    System.out.println("Enter the index of the vending machine you want to test:");
                                    int vendingMachineIndex = scan.nextInt();
                                    scan.nextLine();
                                    RegularVendingMachine chosenVendingMachine = vendingMachines
                                            .get(vendingMachineIndex - 1);
                                    System.out
                                            .println("VENDING MACHINE " + chosenVendingMachine.getName() + " SELECTED");

                                    // display maintenance options
                                    System.out.println("WHAT WOULD YOU LIKE TO DO?");
                                    System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                                    System.out.println(
                                            "[1] RESTOCK\n[2] PRINT SUMMARY OF TRANSACTIONS\n[3] COLLECT MONEY\n[4] EXIT");
                                    int fifthChoice = scan.nextInt();
                                    Maintenance maintenance = new Maintenance(chosenVendingMachine);

                                    switch (fifthChoice) {
                                        case 1: // restock
                                            // display items to restock
                                            System.out.println("WHAT ITEM WOULD YOU LIKE TO RESTOCK?");
                                            System.out.println(ANSI_BLUE + "PLEASE CHOOSE AN OPTION" + ANSI_OFF);
                                            for (Item item : chosenVendingMachine.getAllItems()) {
                                                System.out.println(item.getName());
                                                System.out.println("Price: " + item.getPrice());
                                                System.out.println("Calorie Count: " + item.getCalorieCount());
                                            }
                                            // select item
                                            System.out.println("Enter the index of the item you want to restock:");
                                            int itemIndex = scan.nextInt();
                                            scan.nextLine();
                                            Item chosenItem = chosenVendingMachine.getAllItems().get(itemIndex - 1);
                                            System.out.println("You have chosen " + chosenItem.getName());
                                            // ask for quantity to restock
                                            System.out.println("Enter the quantity you want to restock:");
                                            int quantity = scan.nextInt();
                                            scan.nextLine();
                                            maintenance.restockItem(chosenItem, quantity);
                                            System.out.println("Item " + chosenItem.getName() + " restocked!");
                                            break;
                                        case 2: // print summary of transactions
                                            ArrayList<String> transactions = chosenVendingMachine.getTransactions();
                                            for (String transaction : transactions) {
                                                System.out.println("TRANSACTIONS");
                                                System.out.println(transaction);
                                            }
                                            break;
                                        case 3: // collect money
                                            maintenance.collectMoney();
                                            System.out.println("MONEY COLLECTED, VENDING MACHINE CASH IS NOW EMPTY");
                                            break;
                                        case 4: // exit
                                            break;
                                        default:
                                            System.out.println("ERROR: INVALID CHOICE");
                                            misc.pauseScreen2Sec();
                                            break;
                                    }

                                case 2: // display special vending machines
                                    System.out.println("ERROR: SPECIAL MACHINE MAINTENANCE NOT YET IMPLEMENTED");
                                    misc.pauseScreen2Sec();
                                    break;
                                case 3: // exit
                                    break;
                                default:
                                    System.out.println("ERROR: INVALID CHOICE");
                                    misc.pauseScreen2Sec();
                                    break;
                            }
                    }
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again.\n" + ANSI_OFF);
                    misc.pauseScreen2Sec();
                    break;
            }
        } while (firstChoice != 3);

        scan.close();
        System.out.println(ANSI_GREEN + "THANK YOU FOR VISITING THE VENDING MACHINE FACTORY!" + ANSI_OFF);
    }

    public static ArrayList<Item> initalizeAdoboVendingMachineItems() {
        String[] itemNames = new String[8];
        double[] itemPrices = new double[8];
        double[] itemCalorieCounts = new double[8];
        ArrayList<Item> PREMADEitemList = new ArrayList<Item>();

        itemNames[0] = "Chicken Breast";
        itemNames[1] = "Chicken Drumsticks";
        itemNames[2] = "Pork shoulder";
        itemNames[3] = "Pork belly";
        itemNames[4] = "Mix of Chicken";
        itemNames[5] = "Mix of Pork";
        itemNames[6] = "Cup of White Rice";
        itemNames[7] = "Adobo Sauce";

        itemPrices[0] = 1.5;
        itemPrices[1] = 1.2;
        itemPrices[2] = 1.3;
        itemPrices[3] = 1.4;
        itemPrices[4] = 1.6;
        itemPrices[5] = 1.7;
        itemPrices[6] = 1.8;
        itemPrices[7] = 0.5;

        itemCalorieCounts[0] = 231;
        itemCalorieCounts[1] = 289;
        itemCalorieCounts[2] = 390;
        itemCalorieCounts[3] = 777;
        itemCalorieCounts[4] = 260;
        itemCalorieCounts[5] = 583.5;
        itemCalorieCounts[6] = 204;
        itemCalorieCounts[7] = 153.3;

        for (int i = 0; i < 8; i++) {
            Item item = new Item(itemNames[i], itemPrices[i], itemCalorieCounts[i]);
            PREMADEitemList.add(item);
        }

        return PREMADEitemList;
    }
}