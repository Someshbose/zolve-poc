package pujaburman30github.io.zolvepoc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import pujaburman30github.io.zolvepoc.api.*;
import pujaburman30github.io.zolvepoc.dao.ReceiptDto;
import pujaburman30github.io.zolvepoc.dao.TransactionDto;
import pujaburman30github.io.zolvepoc.dao.UserDto;
import pujaburman30github.io.zolvepoc.model.Transactions;
import pujaburman30github.io.zolvepoc.model.User;
import pujaburman30github.io.zolvepoc.service.WalletService;

import javax.print.attribute.standard.Destination;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class NewWalletController implements AddApi, BalanceApi, CreditApi, DebitApi, HistoryApi, SendApi {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private WalletService service;

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        User user = service.createUser(getUserFromDto(userDto));
        return ResponseEntity.ok(getUserDtoFromEntity(user));
    }

    @Override
    public ResponseEntity showBalanceById(Long userId) {
        return ResponseEntity.ok(service.getBalance(userId));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TransactionDto> creditAmount(ReceiptDto receiptDto) {
        Transactions credit = service.creditMoney(receiptDto);
        return ResponseEntity.ok(getTransactionDtoFromEntity(credit, receiptDto.getBenificiary()));
    }

    @Override
    public ResponseEntity<TransactionDto> debitAmount(ReceiptDto receiptDto) {
        Transactions debit = service.debitMoney(receiptDto);
        return ResponseEntity.ok(getTransactionDtoFromEntity(debit,receiptDto.getPayer()));
    }

    @Override
    public ResponseEntity<List<TransactionDto>> userHistory(Long userId) {
        List<Transactions> list = service.history(userId);
        List<TransactionDto> dtoList = list.stream().map(item -> getTransactionDtoFromEntity(item,userId)).collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @Override
    public ResponseEntity<TransactionDto> sendAmount(ReceiptDto receiptDto) {
        Transactions send = service.send(receiptDto);
        return ResponseEntity.ok(getTransactionDtoFromEntity(send,receiptDto.getPayer()));
    }

    private User getUserFromDto(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return user;
    }

    private UserDto getUserDtoFromEntity(User user){
        UserDto userDto= modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private TransactionDto getTransactionDtoFromEntity(Transactions transactions,Long userId){

        modelMapper.typeMap(Transactions.class,TransactionDto.class).addMappings(mapper->
        {
            mapper.map(src-> src.getPayee().getId(), TransactionDto::setPayee);
            mapper.map(src-> src.getPayer().getId(), TransactionDto::setPayer);
            mapper.map(src-> src.getTransaction_date(),TransactionDto::setTimeAt);
            mapper.map(src-> src.getTypes()==null?null:src.getTypes().stream().map(e->{System.out.println(e.getType());
                return e.getType().toString();}).findFirst() ,TransactionDto::setTransactionType);
        });

        TransactionDto transactionDto = modelMapper.map(transactions,TransactionDto.class);
        return transactionDto;
    }
}
