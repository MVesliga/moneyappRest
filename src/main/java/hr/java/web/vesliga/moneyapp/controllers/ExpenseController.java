package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Authority;
import hr.java.web.vesliga.moneyapp.model.Expense;
import hr.java.web.vesliga.moneyapp.model.User;
import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import hr.java.web.vesliga.moneyapp.repositories.UserRepository;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
@RequestMapping("/expenses")
@SessionAttributes({"expenseTypes","wallet","sumOfExpenses"})
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute("wallet")
    public Wallet setWallet(HttpSession session){

        Wallet wallet = new Wallet();
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = "";
        if (user instanceof UserDetails) {
             username = ((UserDetails)user).getUsername();
        } else {
             username = user.toString();
        }

        try{
            wallet = walletRepository.findOneByUsername(username);
        }catch(EmptyResultDataAccessException e){
            log.info("Kreira se  novčanik.");
            wallet.setUserName(username);
            wallet.setWalletName(username + " wallet");
            wallet.setWalletType(Wallet.walletType.Gotovina);
            session.setAttribute("sumOfExpenses",0.0);
            wallet = walletRepository.save(wallet);
        }

        Double sumOfExpenses = 0.0;
        Iterable<Expense> expenses = expenseRepository.findAll();
        for(Expense e : expenses){
            if(e.getWallet().getId().equals(wallet.getId())){
                wallet.getListOfExpenses().add(e);
                sumOfExpenses -= e.getAmount();
            }
        }

        session.setAttribute("sumOfExpenses",sumOfExpenses);



        return wallet;
    }

    @GetMapping("/new")
    public String showForm(Model model){
        log.info("Pune se podaci za formu.");
        model.addAttribute("expense", new Expense());
        model.addAttribute("expenseTypes", Expense.expenseType.values());

        return "newExpense";
    }

    @GetMapping("/showExpenses")
    public String showExpenses(Wallet wallet, Model model){
        List<Expense> expensesByUser = new ArrayList();
        Iterable<Expense> allExpenses = expenseRepository.findAll();

        log.info("Punim korisnikove troškove.");
        for(Expense e : allExpenses){
            if(e.getWallet().getId().equals(walletRepository.findOne(wallet.getId()).getId())){
                expensesByUser.add(e);
            }
        }
        Double sum = expensesByUser.stream().mapToDouble(e -> e.getAmount()).sum();
        model.addAttribute("expenses", expensesByUser);
        model.addAttribute("sum",sum);

        log.info("Prikazujem korisnikove troškove.");

        return "allExpensesByUser";
    }

    @GetMapping("/resetWallet")
    public String resetWallet(SessionStatus status,Wallet wallet){
        log.info("Resetiram sesiju.");
        status.setComplete();
        expenseRepository.resetWallet(wallet.getId());
        return "redirect:/expenses/new";
    }

    @GetMapping("/deleteExpense")
    public String deleteExpense(@RequestParam(name="expenseId")Long expenseId){
        System.out.println(expenseId.toString());
        System.out.println("Dođem do tu");
        expenseRepository.delete(expenseId);

        return "redirect:/expenses/showExpenses";
    }
    @GetMapping("/editExpense")
    public String editExpense(@RequestParam(name="expenseId")Long id, Model model){
        Expense expense = expenseRepository.findOne(id);
        model.addAttribute("expense",expense);

        return "editExpense";
    }

    @PostMapping("/editExpense")
    public String processEditForm(@RequestParam(name="expenseId") Long id,Expense expense, Wallet wallet){
        System.out.println(expense.getExpenseName());
        System.out.println(expense.getType().toString());
        System.out.println(expense.getAmount().toString());
        expense.setWallet(wallet);
        expenseRepository.update(id, expense);
        return "redirect:/expenses/showExpenses";
    }
    @PostMapping("/new")
    public String processForm(Wallet wallet, @Validated Expense expense, Errors errors, Model model, HttpSession session){
        log.info("Provjeravam validnost podataka.");
        if(errors.hasErrors()){
            log.info("Trošak ima grešku. Prekida se slanje.");
            return "newExpense";
        }

        expense.setWallet(wallet);
        expenseRepository.save(expense);
        wallet.getListOfExpenses().add(expense);
        Double sumOfExpenses = -wallet.getListOfExpenses().stream().mapToDouble(e -> e.getAmount()).sum();
        model.addAttribute("sumOfExpenses", sumOfExpenses);

        log.info("Prikazujem trošak.");
        return "acceptedExpense";
    }

    @GetMapping("/newUser")
    public String registerUser(Model model){
        model.addAttribute("user", new User());

        return "userRegistration";
    }



}
