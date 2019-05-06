package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="/api/expenses", produces = "application/json")
@CrossOrigin
public class ExpenseRestController {


    private final ExpenseRepository expenseRepository;
    private final WalletRepository walletRepository;



    public ExpenseRestController(ExpenseRepository expenseRepository, WalletRepository walletRepository){
        this.expenseRepository = expenseRepository;
        this.walletRepository = walletRepository;
    }

    @GetMapping
    public Iterable<Expense> findAll(){
        return expenseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Expense> findOne(@PathVariable Long id){
        Expense expense =  expenseRepository.findById(id).get();
        if(expense != null){
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>((Expense) null,HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes="application/json")
    public Expense save(@Validated @RequestBody Expense expense){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }

        System.out.println(username);
        Wallet wallet = walletRepository.findByUserNameLike(username);
        expense.setWallet(wallet);
        return expenseRepository.save(expense);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody Expense newExpense, @PathVariable Long id){
        Expense expense = expenseRepository.findById(id).get();
        newExpense.setWallet(expense.getWallet());

        //expenseRepository.update(id,newExpense);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        expenseRepository.deleteById(id);
    }
}
