package pujaburman30github.io.zolvepoc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pujaburman30github.io.zolvepoc.dao.ReceiptDto;
import pujaburman30github.io.zolvepoc.model.TransactionType;
import pujaburman30github.io.zolvepoc.model.Transactions;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.repo.TransactionRepository;
import pujaburman30github.io.zolvepoc.repo.UserRespository;
import pujaburman30github.io.zolvepoc.util.InSuffiecientFundException;
import pujaburman30github.io.zolvepoc.util.UserCreationException;
import pujaburman30github.io.zolvepoc.util.UserNotFoundException;

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
    public Transactions debitMoney(ReceiptDto receipt) throws InSuffiecientFundException,UserNotFoundException{
        User payee = getUser(receipt.getPayer());
        if(payee.getBalance()- receipt.getAmount()>100){
            Transactions transaction = Transactions.builder().payee(receipt.getPayer()).amount(receipt.getAmount()).type(TransactionType.DEBIT).build();
            transactionRepository.save(transaction);
            payee.setBalance(payee.getBalance()-receipt.getAmount());
            userRespository.save(payee);
            return transaction;
        }
        else{
            throw new InSuffiecientFundException("Failed as minimum amount is less than Rs-100 not allowed!");
        }
    }

    @Transactional
    public Transactions creditMoney(ReceiptDto receipt)throws UserNotFoundException{
        User benificary =
                getUser(receipt.getBenificiary());

        Transactions transaction = Transactions.builder().payer(receipt.getBenificiary()).amount(receipt.getAmount()).type(TransactionType.CREDIT).build();
        transactionRepository.save(transaction);
        benificary.setBalance(benificary.getBalance()+receipt.getAmount());
        userRespository.save(benificary);
        return transaction;

    }

    @Transactional
    public Transactions send(ReceiptDto receipt){
        User payee = getUser(receipt.getPayer());
        User benificiary = getUser(receipt.getBenificiary());

        if(payee.getBalance()-receipt.getAmount()>=100){
            Transactions transaction = Transactions.builder().payee(receipt.getPayer()).payer(receipt.getBenificiary()).amount(receipt.getAmount()).type(TransactionType.DEBIT).build();
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
        if(user.getBalance()<100){
            throw new UserCreationException("Minimum account balance to create account is Rs. 100");
        }
        userRespository.save(user);
        return user;
    }

    public double getBalance(Long id) throws UserNotFoundException{
        return getUser(id).getBalance();
    }

    public List<Transactions> history(long id){
        return transactionRepository.findByPayerOrPayee(id,id);
    }

    private User getUser(Long id){
        Optional<User> user = userRespository.findById(id);
        if (user.isPresent()){
            return user.get();
        }else{
            throw new UserNotFoundException("User with id- {"+id +"}you are looking for doesn't exist!");
        }

    }
}
