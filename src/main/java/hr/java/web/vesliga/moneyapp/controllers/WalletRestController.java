package hr.java.web.vesliga.moneyapp.controllers;

import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/wallets", produces = "application/json")
@CrossOrigin
public class WalletRestController {

    private final WalletRepository walletRepository;

    public WalletRestController(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    @GetMapping
    public Iterable<Wallet> findAll(){
        return walletRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> findOne(@PathVariable Long id){
        Wallet wallet =  walletRepository.findById(id).get();
        if(wallet != null){
            return new ResponseEntity<>(wallet, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>((Wallet) null,HttpStatus.NOT_FOUND);
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes="application/json")
    public Wallet save(@RequestBody Wallet wallet){
        return walletRepository.save(wallet);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        walletRepository.deleteById(id);
    }
}
