# Implementation of simple stock exchange

## Specifications:

### Models:

#### Order

Buy or sell a stock (Buy order and sell orders are separate entities). Assume quantity of order to be 1 share only.

##### Attributes:

- Stock name

- Price

- Trading party name

- Direction of order – Buy or Sell

##### There are three types of orders (sub-orders):

- Limit order: These orders stay in exchange until they get crossed with order of opposite direction i.e Buy order price is more than sell order price.

- Market order: These order won’t have a price – it will be crossed with best order of the opposite direction. Best order for a buyer is an existing sell order with least price and for a seller is an existing buy order with the highest price

- IOC Order (Immediate or cancel order): These orders when placed are immediately checked on exchange and will be cancelled if they cannot be crossed with an existing order.

#### Trade

- Whenever a trade is possible between two parties – we create a trade object.

- What constitutes a valid trade? See types of order for respective behavior

- When we create a trade object – we remove the order object

##### Attributes:

- Price (Should be average of two order prices)

- Stock name

- Buyer trading party name

- Seller trading party name

#### Quote

- There will one quote object per stock.

- Whenever order objects are added/removed – this particular object would be updated.

##### Attributes:

- Stock name

- Best buy price

- Best sell price

### Features:

- Being able to read from multiple sources simultaneously (files in our case)

#### What does each file contain

Each file is a new line separated rows (instruction) where items are space separated. There can be two types of instructions:

##### Order instruction.

* Stock name

* Price

* Trading party (You can assume that it is never space separated on its own)

* Direction of order - Buy or sell

* Type of order: Limit, Market or IOC

Example: Amazon 500 Trading-Global-Inc Buy Limit

##### Sleep instruction 
Amount in milliseconds. 

Example: Sleep 1000

###  Content example of file

Amazon 500 Trading-Global-Inc Buy Limit

Sleep 1000

IBM 100 Trading-Global-Inc Sell IOC

Sleep 500

Amazon 450 MoneyMaker-Sync Sell Market

####  Should be able to trade between the orders received whenever a trade is possible. You should print the details of trade whenever it happens.

### Example console print:

Trade: Amazon

Parties involved:

Trading-Global-Inc (500)

MoneyMaker-Sync (450)

####  Should throw relevant exceptions.

* If both trading parties in a trade are same – throw a custom exception and handle it (ensure process should not crash).

* If bad data is present in the file for an order instruction or sleep instruction

#### Whenever a quote is updated, dump the updated quote object in a file.
