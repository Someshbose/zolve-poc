package pujaburman30github.io.zolvepoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pujaburman30github.io.zolvepoc.dto.Receipt;
import pujaburman30github.io.zolvepoc.model.TransactionType;
import pujaburman30github.io.zolvepoc.model.Transactions;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.repo.TransactionRepository;
import pujaburman30github.io.zolvepoc.repo.UserRespository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class WalletService {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transactions debitMoney(Receipt receipt){
        User payee = getUser(receipt.getPayee());
        if(payee.getBalance()- receipt.getAmount()>100){
            Transactions transaction = Transactions.builder().payee(receipt.getPayee()).amount(receipt.getAmount()).type(TransactionType.DEBIT).build();
            transactionRepository.save(transaction);
            payee.setBalance(payee.getBalance()-receipt.getAmount());
            userRespository.save(payee);
            return transaction;
        }
        else{
            throw new RuntimeException("Failed as minimum amount is less than Rs-100 not allowed!");
        }
    }

    @Transactional
    public Transactions creditMoney(Receipt receipt){
        User benificary =
                getUser(receipt.getBenificiary());

        Transactions transaction = Transactions.builder().payer(receipt.getBenificiary()).amount(receipt.getAmount()).type(TransactionType.CREDIT).build();
        transactionRepository.save(transaction);
        benificary.setBalance(benificary.getBalance()+receipt.getAmount());
        userRespository.save(benificary);
        return transaction;

    }

    @Transactional
    public Transactions send(Receipt receipt){
        User payee = getUser(receipt.getPayee());
        User benificiary = getUser(receipt.getBenificiary());

        if(payee.getBalance()-receipt.getAmount()>=100){
            Transactions transaction = Transactions.builder().payee(receipt.getPayee()).payer(receipt.getBenificiary()).amount(receipt.getAmount()).build();
            transactionRepository.save(transaction);
            payee.setBalance(payee.getBalance()-receipt.getAmount());
            benificiary.setBalance(benificiary.getBalance()+ receipt.getAmount());
            userRespository.save(payee);
            userRespository.save(benificiary);
            return transaction;
        }
        else{
            throw new RuntimeException("Failed as minimum amount is less than Rs-100 not allowed!");
        }
    }

    public User createUser(User user){
        userRespository.save(user);
        return user;
    }

    public double getBalance(Long id){
        return getUser(id).getBalance();
    }

    public List<Transactions> history(long id){
        return transactionRepository.findByPayerOrPayee(id,id);
    }

    private User getUser(Long id){
        return userRespository.findById(id).get();
    }
}
