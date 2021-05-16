package pujaburman30github.io.zolvepoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pujaburman30github.io.zolvepoc.dto.Receipt;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.service.WalletService;

import java.net.http.HttpResponse;

@RestController
public class WalletController {

    @Autowired
    private WalletService service;

    @PostMapping("/debit")
    public ResponseEntity debitMoney(Receipt receipt){
        return ResponseEntity.ok(service.debitMoney(receipt));
    }

    @PostMapping("/credit")
    public ResponseEntity creditMoney(Receipt receipt){
        return ResponseEntity.ok(service.creditMoney(receipt));
    }

    @PostMapping("/add")
    public ResponseEntity createUser(User user){
        User savedUser = service.createUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/balace/{user-id}")
    public double getBalance(@PathVariable long userId){
        return service.getBalance(userId);
    }
}
