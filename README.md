# CCPROG3MP_SOLO
07/20/2023

## Vending Machine Sim Features
1. Introduction and Start Menu
    1.1 Name Factory "Cannot be changed later"
    1.2 Menu Options to create Vending Machines
    1.3 Menu Options to conduct maintenance
    1.4 Save and Exit game
2. Create Regular Vending Machine
    2.1 Name Vending Machine "Cannot be changed later"\
    2.2 Add Slots "Minimum of 8"
3. Create Special Vending Machine
    3.1 Name Vending Machine "Cannot be changed later"
    3.2 Add Slots "Minimum of 8"
    3.3 Add Items and set as combo or solo item
    3.4 "Special Vending Machine Created!" 
4. Test Vending Features
    4.1 View all Vending Machines
    4.2 Test Regular Vending Machine
    4.3 Test Special Vending Machine
5. Other Maintenance Features
    5.1 Restock Vending Machine
        5.1.1 View and select Vending Machine
        5.1.2 View and Select Item to restock
        5.1.3 Input quantity to restock
        5.1.4 "Restock Successful!"
    5.2 Change Price of Item
        5.2.1 View and select Vending Machine
        5.2.2 View and Select Item to change price
        5.2.3 Input new price
        5.2.4 "Price Change Successful!"
    5.3 Collect Money and put in Factory Funds
        5.3.1 View and select Vending Machine
        5.3.2 View and Select Money to collect
        5.3.3 Input quantity to collect
        5.3.4 "Collect Successful!"
    5.4 Replenish Vending Machine Funds
        5.4.1 View and select Vending Machine
        5.4.2 View and Select Money to replenish
        5.4.3 Input quantity to replenish
        5.4.4 "Replenish Successful!"
    5.5 Print Summary of Transactions from last restock
        5.5.1 View and select Vending Machine
        5.5.2 Export to txt file.
        5.5.3 View txt file in GUI "Summary of Transactions", trigger: "OK"
        5.5.4 "Summary of Transactions saved in files!"
6. Exit Game
    6.1 Save and Exit Game
    6.2 Exit Game and discard state
    

## MCO2 Requirements
1. Regular and Special Vending Machine
2. Implementation should simulate real world objects. (stock of 10 eggs isn't just an attribute quantity ut there are 10 instances of the item)
3. Design and Implementation should exhibbit proper object-oriented concepts: inheritance, polymorphism, method overloading and overriding
4. GUI-based output simulation of the features of the factory as well as the vending machine
5. Utilize MVC Architechture

## MCO2 Deliverales
[] ZIP file with source code + proper internal documenatation
* test script follows format in appendix A
[] Signed declaration of original work (appendix A)
[] Full class diagram only with model components
[] Javadoc-generated documentation for proponent-defined classes with pertinent information

## REGULAR VENDING MACHINE
1. consists of item slots that acts as an interface fo the user to know what is available
2. each slot is mapped to an item (at least 8 slots with 10 items max)
3. availability, price, calories should be available to the user
4. machine accepts payment in different denominations from the user, dispenses item, and produces change in different denominations
5. user can skip to dispense change without picking an item

## MAINTENANCE
1. restocking specific items
2. change price of each item type
3. collecting payment/money
4. replenish money for different denominations
5. print summary of transactions for the machine (quantity of each item sold and the total amount collected in sales from previous stocking)
6. print summary of starting inventory from the last restocking

## SPECIAL VENDING MACHINE extends REGULAR VENDING MACHINE
1. prepare a special product based on items stored in the machine and choices of items for the product that the user wants
* calories is the combination of the calorie count for each chosen item
2. displays preparation messages if multiple items are chosen, if single item is chosen, no preparation message needed
3. there are solo items
* Please take note, however, that the special vending machine should have products that need to be assembled using other items, items that can be purchased individually, and items that are not meant to be sold to the user.
