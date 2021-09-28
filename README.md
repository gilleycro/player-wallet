# INSTRUCTIONS
* Server is running on localhost:8080
* API with description can be found on http://localhost:8080/swagger-ui/
* Database used is H2
* If system does not have mvn installed mvnw can be used instead (.cmd for windows)

## Database
Solution used H2 database which obviously is not made for production. If something else (like mysql/postgres)
was used than setup of project would be different and most constraints would be on DB instead in code. 
Downside of this approach is that anything can be inserted into database directly with SQL, but it speeds up development process.

## Model
Possibly Player and Account tables could be merged into one for this example, but in production these data would
probably be separated so they were also in this solution. 

## Tests
Only small amount of tests was written, production code would have bigger coverage.

## HOW TO USE
Test data will be loaded when project is started. For transaction to be valid there must be valid player id that will be
used for updating the balance.

Go to http://localhost:8080/swagger-ui/ <br>
open player-controller  <br>
api for payment is /api/player/process-payment/{id} where id is playerId

### request that will work
```javascript
{
    "amount": 100,
    "transactionId": 1000,
    "transactionType": "DEBIT"
}
```

### request that will throw "InsufficientFundsException"
```javascript
{
    "amount": 100000,
    "transactionId": 1001,
    "transactionType": "DEBIT"
}
```

### request that will throw "TransactionNotUniqueException"
```javascript
{
    "amount": 100000,
    "transactionId": 1,
    "transactionType": "DEBIT"
}
```



