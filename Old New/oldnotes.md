VendingMachineFactory
- name: String
- factoryFunds: CashRegister
+ Factory(name: String) //sets name and initializes funds as 100,000
+ createVendingMachine(): boolean
+ collectFunds(m: VendingMachine): void
+ testVendingMachine(m: VendingMachine): void
+ restockVendingMachine(m: VendingMachine): void
+ setnewPrice(m: VendingMachine, d: double): void

VendingMachine {abstract}
- name: String
- slots: ArrayList<Slot>
- transactionHistory: TransactionHistory
- vendingFunds: CashRegister
+ addSlot(): void
+ addItemToSlot(i: Item): void
+ restockItem(i: Item): void
+ purchaseItem(d: double): Item
+ printSummary(): File
+ checkItemAvailability(): boolean
+ checkFundsAvailability(): boolean

SpecialVendingMachine (extends VendingMachine)
+ addComboItem(i: Item)
//TODO: methods here

TransactionHistory
- lastRestockDateTime: DateUtils
- itemsSold: HashMap<String, Integer>
- totalSales: double
//TODO: methods here
+ addTransactionToHistory(i: Item) //TODO: fix method parameters

DateUtils
+ getCurrentDate(): Date

CashRegister
- change: HashMap<Double, Double>
- earnings: HashMap<Double, Double>
+ CashRegister()
+ receiveMoney(): void
+ calculateChange(): double
+ isChangeEnough(itemPrice: double)

Slot
- itemsInSlot: ArrayList<Item>
- maxCapacity: int //set to 10 for all

Item {abstract}
- name: String
- price: double
- calorieCount: double
+ Item(name: String, price: double, cals: double)
+ setNewPrice(d: double)

ComboItem (extends Item)
- preparationMessage: String
