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

## MENU
1. Create a vending machine
2. Test a vending machine
	2.a. test vending features
	2.b. maintenance features
3. Exit

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
5. print summary of transactions for the machine (quantity of each item sold and the total amount collected in sales from previous stocking
6. print summary of starting inventory from the last restocking

## SPECIAL VENDING MACHINE extends REGULAR VENDING MACHINE
1. prepare a special product based on items stored in the machine and choices of items for the product that the user wants
* calories is the combination of the calorie count for each chosen item
2. displays preparation messages if multiple items are chosen, if single item is chosen, no preparation message needed
3. there are solo items
* Please take note, however, that the special vending machine should have products that need to be assembled using other items, items that can be purchased individually, and items that are not meant to be sold to the user.

/********************************/

Vending Machine {abstract}
- name: String
- moneyInventory: Money

Regular Vending Machine extends Vending Machine
- vendingSlots: Item[10] // each holds an instance of the item

Special Vending Machine extends Vending Machine
- vendingSlots: Item[] 

Item {abstract}
- name: String
- calories: double
- price: Double

SoloItem extends Item
