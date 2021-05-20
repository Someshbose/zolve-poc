package pujaburman30github.io.zolvepoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pujaburman30github.io.zolvepoc.dto.Receipt;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.service.WalletService;


@RestController
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping("/debit")
    public ResponseEntity debitMoney(@RequestBody Receipt receipt){
        return ResponseEntity.ok(service.debitMoney(receipt));
    }

    @PostMapping("/credit")
    public ResponseEntity creditMoney(@RequestBody Receipt receipt){
        return ResponseEntity.ok(service.creditMoney(receipt));
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@RequestBody User user){
        User savedUser = service.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/history/{user-id}")
    public ResponseEntity getTransactionHistory(@PathVariable("user-id") Long userId){
        return ResponseEntity.ok(service.history(userId));
    }

    @PostMapping("/send")
    public ResponseEntity sendMoney(@RequestBody Receipt receipt){
        return ResponseEntity.ok(service.send(receipt));
    }

    @GetMapping("/balance/{user-id}")
    public double getBalance(@PathVariable("user-id") Long userId){
        return service.getBalance(userId);
    }
}
