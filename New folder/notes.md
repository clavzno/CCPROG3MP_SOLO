### REQUIRED FEATURES
1. Create Regular Vending Machine
2. Create Special Vending Machine
3. Test Vending Features
    3.1. Choose Item and Amount
    3.2. Accept Cash
    3.3. Calculate Change
    3.4. If change enough, dispense item from slot
    3.5. If change not enough, return money
    3.6. If item not available, return money
    3.7. When dispensing item, add transaction to history
4. Restock Vending Machine
    4.1 View all slots and amount in slots
    4.2 Choose slot to restock
    4.3 Get amount to restock
    4.4 Restock slot
5. Set new price for item
    5.1 View all slots and price of items in slots
    5.2 Choose slot to set new price
    5.3 Set new price
6. Print Summary of Transactions
    6.1 Select vending machine
    6.2 View summary and print to file
7. Collect Earnings from Vending Machine
    7.1 Choose Vending Machine
    7.2 Transfer earnings to Factory funds

/**************************************************************/
# NOTE: MOST UPDATED IS IN LUCID CHART

# Item
- name: String
- calories: double
- price: double
+ Item(name: String, calories: double, price: double)
+ setPrice(price: double): void

    # ComboItem extends Item
    - processMessage: String //TODO

# Slot
- itemsInSlot: ArrayList<Item> //each has an instance of the same item
- maxCapacity: int (10) //for ease of use, use slot.add() instead of Item[10]
+ Slot()
+ dispenseItem(amount: int): ArrayList<Item> //TODO
+ addItem(item: Item): boolean

# VendingMachine {abstract}
- name: String
- slots: ArrayList<Slots>
- funds: CashRegister
- history: TransactionHistory
+ VendingMachine(name: String, slotAmount: int)

+ addSlot(amount: int): boolean
+ addItemToSlot(item: Item, slotIndex: int): boolean
+ areSlotsFull() boolean

+ addTransactionToHistory()

	## RegularVendingMachine
	- maxSlots: int (8) //at least 8 slots

    ## SpecialVendingMachine
    - preparation message: String
    + addComboItem(item: Item): boolean

# CashRegister
- change: HashMap<Double, Integer>
- earnings: HashMap<Double, Integer> //earnings cannot be used as change
+ CashRegister()
- initializeChange()

+ receiveMoney(HashMap<Double, Integer>): void
+ calculateChange(itemPrice: double): double
- isChangeEnough(itemPrice: double): boolean
+ dispenseMoney(amount: double): HashMap<Double, Integer>

# TransactionHistory
- transactions: ArrayList<Transaction>
- lastRestockDateTime: Date
- totalSalesEarnings: double
+ TransactionHistory()
+ addTransactionToHistory(item: Item, amount: int, totalPrice: double): void
+ overwriteLastRestockDateTime(dateTime: Date): void
+ exportToFile(): void


# Transaction
- itemSold: Item
- amountSold: int
- totalPrice: double
- transactionDateTime: Date
+ getItem(): Item
+ getAmount(): int
+ getTotalPrice(): 

# Factory
- name: String
- factoryFunds: CashRegister
+ Factory(name: String)

+ collectFunds(vm: VendingMachine): void

+ createRegularVendingMachine(name: String, slotAmount: int)
+ createSpecialVendingMachine(name: String, slotAmount: int)

+ addItemToMachine(item: Item): boolean
+ buyItem(vm: VendingMachine, index: int, amount: int): Item
+ buyItem(vm: VendingMachine, index: int, amount: int): boolean
+ setNewItemPrice(vm: VendingMachine, index: int, amount: int): boolean