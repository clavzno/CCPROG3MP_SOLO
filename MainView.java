import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * PROBLEMS
 * 1. Calculation for change does not work properly, returns original payment and because original payment is returned, 
 * the user is unable to receive the items they ordered.
 */

public class MainView {

    public static void main(String[] args) {
        // GUI NOTE: show title screen
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your factory name: ");
        String name = sc.nextLine();
        Factory factory = new Factory(name);
        printInstructions(name);

        /* TESTING */
        VendingMachine testRegularDrinksMachine = initializeRegularDrinksMachine();
        factory.getVendingMachines().add(testRegularDrinksMachine);

        // Adding a test transaction to testRegularDrinksMachine's Transaction History
        Item testItem1 = new Item("Coke", 100, 1.50);
        Item testItem2 = new Item("Sprite", 100, 1.50);
        ArrayList<Item> testArrayList = new ArrayList<Item>();
        testArrayList.add(testItem1);
        testArrayList.add(testItem2);
        Transaction testTransaction = new Transaction(testArrayList);
        testRegularDrinksMachine.getTransactionHistory().addTransactionToHistory(testTransaction);

        // Simulate a payment for the test transaction
        HashMap<Double, Integer> testPayment = new HashMap<>();
        testPayment.put(1.0, 1); // Pay 1.0 PHP
        testPayment.put(0.5, 1); // Pay 0.5 PHP
        testPayment.put(0.25, 2); // Pay 0.5 PHP
        System.out.println("You entered: " + testPayment);

        // Buy items from the vending machine
        int testSlotIndexToBuyFrom = 1; // Change this to the correct slot index
        int testAmountToBuy = 1; // Change this to the correct amount
        HashMap<Double, Integer> testChange = factory.buyItemFromRegularVendingMachineTask(
                (RegularVendingMachine) testRegularDrinksMachine, testSlotIndexToBuyFrom - 1, testAmountToBuy,
                testPayment);
        System.out.println("Your change is " + testChange);

        // View transaction history
        System.out.println("Transaction History: ");
        testRegularDrinksMachine.getTransactionHistory().viewTransactionHistory(testRegularDrinksMachine);

        // Rest of the main method...

        int mainMenuChoice;

        do {
            printMainMenu();
            mainMenuChoice = sc.nextInt();
            sc.nextLine();

            switch (mainMenuChoice) {
                case 1:
                    int creationMenuChoice;
                    do {
                        printCreationMenu();
                        creationMenuChoice = sc.nextInt();
                        sc.nextLine();
                        switch (creationMenuChoice) {
                            case 1:
                                int machineIndex = creationRegular(factory, sc);
                                if (machineIndex == -1) {
                                    System.out.println("Invalid Index.");
                                    break;
                                }
                                int slotsCreated = factory.getVendingMachines().get(machineIndex).getSlots().size();
                                creationRegularCreateItem(factory, sc, machineIndex, slotsCreated);
                                // GUI NOTE: Show the user the vending machine they just created with Items

                                // FOR CHECKING
                                System.out.println("");
                                printRegularVendingMachineDetails(factory, machineIndex);
                                break;
                            case 2:
                                creationSpecial();
                                break;
                            case 3:
                                break;
                        }
                    } while (creationMenuChoice != 3);
                    break;
                case 2:
                    int maintenanceMenuChoice;
                    do {
                        showAllVendingMachines(factory);
                        int chosenVendingMachineChoice = sc.nextInt();
                        sc.nextLine();
                        VendingMachine chosen = factory.selectVendingMachine(chosenVendingMachineChoice - 1); // -1
                                                                                                              // because
                                                                                                              // user
                                                                                                              // inputs
                                                                                                              // 1-9 but
                                                                                                              // arraylist
                                                                                                              // is 0-8
                        System.out.println("You chose " + chosen.getName());
                        printMaintenanceMenu();
                        maintenanceMenuChoice = sc.nextInt();
                        sc.nextLine();
                        switch (maintenanceMenuChoice) {
                            case 1: // Replenish money in different denominations
                                displayChangeInMachine(chosen);
                                System.out.println("Enter denomination to replenish: ");
                                double denomination = sc.nextDouble();
                                sc.nextLine();
                                System.out.println("Enter amount to replenish: ");
                                int amount = sc.nextInt();
                                sc.nextLine();
                                factory.replenishMoneyInVendingMachine(chosen, denomination, amount);
                                break;
                            case 2: // Collect Earnings from Machine and Add to Factory Funds
                                displayEarningsInMachine(chosen);
                                factory.collectFunds(chosen);
                                break;
                            case 3: // Change Item Price
                                displayItemsInSlots(chosen);
                                System.out.println("Enter slot number to change price: ");
                                int slotIndexToChangePrice = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter new price: ");
                                double newPrice = sc.nextDouble();
                                sc.nextLine();
                                factory.setNewItemPrice(chosen, slotIndexToChangePrice - 1, newPrice);
                                break;
                            case 4: // Restock Items
                                displayAmountItemsInSlots(chosen);
                                System.out.println("Enter slot number to restock: ");
                                int slotIndexToRestock = sc.nextInt();
                                sc.nextLine();
                                System.out.println("Enter amount to restock: ");
                                int amountToRestock = sc.nextInt();
                                sc.nextLine();
                                factory.restockItemTask(chosen, slotIndexToRestock - 1, amountToRestock);
                                break;
                            case 5: // Print Transaction History
                                factory.printTransactionHistory(chosen);
                                break;
                            case 6: // Test Vending Machine Features
                                if (chosen instanceof SpecialVendingMachine) {
                                    // TODO: Special Vending Machine buy Feature
                                    System.out.println("Special Vending Machine is not supported in this version.");
                                    break;
                                } else {
                                    displayVendingMachineCatalogue(chosen);
                                    System.out.println("Enter slot number to buy from: ");
                                    int slotIndexToBuyFrom = sc.nextInt();
                                    sc.nextLine();
                                    System.out.println("Enter amount to buy: ");
                                    int amountToBuy = sc.nextInt();
                                    sc.nextLine();
                                    // gets user payment by iterating through denominations and asking how much they
                                    // want to put
                                    HashMap<Double, Integer> payment = getPaymentFromUser(sc);
                                    System.out.println("You entered: " + payment);
                                    // buys item from vending machine
                                    HashMap<Double, Integer> change = factory.buyItemFromRegularVendingMachineTask(
                                            (RegularVendingMachine) chosen, slotIndexToBuyFrom - 1, amountToBuy,
                                            payment);
                                    System.out.println("Your change is " + change);
                                    // dispense item
                                    ArrayList<Item> items = factory.dispenseItemFromRegularVendingMachineTask(
                                            (RegularVendingMachine) chosen, slotIndexToBuyFrom - 1, amountToBuy);
                                    System.out.println("Dispensed the following items" + items);
                                    break;
                                }
                            case 7: // Exit Maintenance Menu
                                System.out.println("Returning to Main Menu...");
                                break;
                        }
                    } while (maintenanceMenuChoice != 6);
                    break;
                case 3:
                    break;
            }
        } while (mainMenuChoice != 3);

        sc.close();
        System.out.println("Thank you for using " + name + " factory!");
    }

    public static HashMap<Double, Integer> getPaymentFromUser(Scanner sc) {
        double[] denominations = { 0.25, 0.50, 1.0, 5.0, 10.0, 20.0, 50.0, 100.0, 200.0, 500.0, 1000.0 };
        HashMap<Double, Integer> payment = new HashMap<>();
        for (double denomination : denominations) {
            System.out.println("Enter amount of " + denomination + " denomination: ");
            int amount = sc.nextInt();
            sc.nextLine();
            payment.put(denomination, amount);
        }
        return payment;
    }

    public static void displayVendingMachineCatalogue(VendingMachine machine) {
        System.out.println("VENDING MACHINE CATALOGUE: ");
        for (int i = 0; i < machine.getSlots().size(); i++) {
            // Format: [i] Slot 1: [i] Item Name: (Price PHP) (Calories) (Quantity
            // Available)
            System.out.println("[" + (i + 1) + "] Slot " + (i + 1) + ":" + " Item Name: "
                    + machine.getSlots().get(i).getItemsInSlot().get(i).getName() + " ("
                    + machine.getSlots().get(i).getItemsInSlot().get(i).getPrice() + " PHP) ("
                    + machine.getSlots().get(i).getItemsInSlot().get(i).getCalories() + " calories) ("
                    + machine.getSlots().get(i).getItemsInSlot().size() + " available)");
        }
    }

    public static void displayAmountItemsInSlots(VendingMachine machine) {
        for (int i = 0; i < machine.getSlots().size(); i++) {
            System.out.println("Slot " + (i + 1) + " items: " + machine.getSlots().get(i).getItemsInSlot().size());
        }
    }

    public static void displayItemsInSlots(VendingMachine machine) {
        for (int i = 0; i < machine.getSlots().size(); i++) {
            System.out.println(
                    "Slot " + (i + 1) + " items: " + machine.getSlots().get(i).getItemsInSlot().get(i).getName());
            System.out.println(
                    "Slot " + (i + 1) + " price: " + machine.getSlots().get(i).getItemsInSlot().get(i).getPrice());
            System.out.println("");
        }
    }

    public static void displayChangeInMachine(VendingMachine machine) {
        System.out.println("CHANGE in machine: " + machine.getFunds().getChange());
    }

    public static void displayEarningsInMachine(VendingMachine machine) {
        System.out.println("EARNINGS in machine: " + machine.getFunds().getEarnings());
    }

    public static void printRegularVendingMachineDetails(Factory factory, int vendingMachineIndex) {
        System.out.println("NEW VENDING MACHINE DETAILS");
        System.out.println("Vending machine name: " + factory.getVendingMachines().get(vendingMachineIndex).getName());
        System.out.println("Vending machine change: "
                + factory.getVendingMachines().get(vendingMachineIndex).getFunds().getChange());
        System.out.println(
                "Vending machine slots: " + factory.getVendingMachines().get(vendingMachineIndex).getSlots().size());

        for (int i = 0; i < factory.getVendingMachines().get(vendingMachineIndex).getSlots().size(); i++) {
            System.out.println("Slot " + (i + 1) + " items: "
                    + factory.getVendingMachines().get(vendingMachineIndex).getSlots().get(i).getItemsInSlot());
        }
    }

    public static int creationRegular(Factory factory, Scanner sc) {
        System.out.println("Enter the name of your vending machine: ");
        String name = sc.nextLine();
        System.out.println("Enter the number of slots you want your vending machine to have: ");
        int slotAmount = sc.nextInt();
        sc.nextLine();
        boolean createRegular = factory.createRegularVendingMachine(name, slotAmount);
        if (createRegular == false) {
            System.out.println("Creation Unsuccesful.");
            return -1;
        } else {
            System.out
                    .println("You have successfully created a regular vending machine with " + slotAmount + " slots!");
        }
        // GUI NOTE: Show the user the vending machine they just created without items
        // returns the index of the vending machine that was just created
        int vendingMachineIndex = factory.getVendingMachines().size() - 1;
        return vendingMachineIndex;
    }

    public static void creationRegularCreateItem(Factory factory, Scanner sc, int currentVendingMachineIndex,
            int slotsCreated) {
        int itemAmount = slotsCreated;
        System.out.println("You can now create " + itemAmount + " items for your vending machine!");
        System.out.println("Don't worry, all slots will be full of the item you create!");
        ArrayList<Item> itemCatalogue = new ArrayList<Item>();
        for (int i = 0; i < itemAmount; i++) {
            System.out.println("Enter the name of your item: ");
            String name = sc.nextLine();
            System.out.println("Enter the calories of your item: ");
            double calories = sc.nextDouble();
            sc.nextLine();
            System.out.println("Enter the price of your item: ");
            double price = sc.nextDouble();
            sc.nextLine();
            Item newItem = factory.createItem(name, calories, price);
            itemCatalogue.add(newItem);
        }
        System.out.println("You have successfully created " + itemAmount + " items!");

        // get index of the machine that was just created
        VendingMachine currentVendingMachine = factory.getVendingMachines().get(currentVendingMachineIndex);
        // add items to vending machine
        for (int i = 0; i < itemCatalogue.size(); i++) {
            // adds to the i'th slot of the vending machine
            Slot currentSlot = currentVendingMachine.getSlots().get(i);
            currentSlot.addItem(itemCatalogue.get(i));
            currentSlot.makeAllSlotsFullOfItem(); // makes all slots full of the item upon creation
        }
    }

    public static void creationSpecial() {
        System.out.println("NOT YET IMPLEMENTED");
    }

    public static void creationSpecialCreateItem() {

    }

    public static void printInstructions(String factoryName) {
        System.out.println("Welcome to " + factoryName + ", Your very own vending machine Factory!");
        System.out.println("You have the ability to create a regular vending machine or a special vending machine.");
        System.out.println(
                "A regular vending machine has a maximum of 8 slots and a special vending machine has no maximuim.");
        System.out.println("You can test your vending machine's features by buying items from it.");
        System.out.println(
                "You can perform maintenance by replenishing the money in the vending machine's cash register, changing the price of an item, and restocking certain items. Additionally, you can print the transaction history from the last restock.");
    }

    public static void showAllVendingMachines(Factory factory) {
        System.out.println("VENDING MACHINES");
        for (int i = 0; i < factory.getVendingMachines().size(); i++) {
            System.out.println((i + 1) + ". " + factory.getVendingMachines().get(i).getName());
        }
    }

    public static void printMainMenu() {
        System.out.println("MAIN MENU");
        System.out.println("1. Create Vending Machine");
        System.out.println("2. Test Vending Machine");
        System.out.println("3. Exit Program");
    }

    public static void printCreationMenu() {
        System.out.println("VENDING MACHINE CREATION");
        System.out.println("1. Create Regular Vending Machine");
        System.out.println("2. Create Special Vending Machine");
        System.out.println("3. Back to Main Menu");
    }

    public static void printMaintenanceMenu() {
        System.out.println("VENDING MACHINE MAINTENANCE");
        System.out.println("1. Replenish Money");
        System.out.println("2. Collect Funds from Machine");
        System.out.println("3. Change Item Price");
        System.out.println("4. Restock Items");
        System.out.println("5. Print Transaction History");
        System.out.println("6. Test Vending Machine Features");
        System.out.println("7. Back to Main Menu");
    }

    public static void printAdditionalInstructions() {
        System.out.println("HOW DOES THIS WORK?");
        // TODO: add instructions later
    }

    public VendingMachine initializeAdoboVendingMachine() {
        // TODO: initialize AdoboVendingMachine
        return new RegularVendingMachine("Adobo Vending Machine");
    }

    public static VendingMachine initializeRegularDrinksMachine() {
        VendingMachine regularDrinksMachine = new RegularVendingMachine("Regular Drinks Machine");
        for (int i = 0; i < 8; i++) {
            regularDrinksMachine.addSlot(1);
        }
        for (int j = 0; j < regularDrinksMachine.getSlots().size(); j++) {
            Slot currentSlot = regularDrinksMachine.getSlots().get(j);
            currentSlot.addItem(new Item("Coke", 100 + j, 50 + j));
            currentSlot.makeAllSlotsFullOfItem();
        }
        return regularDrinksMachine;
    }

}