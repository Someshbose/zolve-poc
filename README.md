#Zolve POC

/add (POST call)
```shell
url -localhost:8080/add
payload - {
    "id": 1,
    "firstName" :"Somesh",
    "lastName" : "Bose",
    "contact" : "9090",
    "balance" : 100
}
```
/balance/{user-id} GET call
```shell
url-localhost:8080/balance/1
```

/debit (or) /credit (POST call)
```shell
url- localhost:8080/debit
payload- {
    "payee":1, (optional for credit)
    "benificiary":1, (optional for debit)
    "amount":10
}
```

database url-
```shell
http://localhost:8080/h2-console/login.jsp
```