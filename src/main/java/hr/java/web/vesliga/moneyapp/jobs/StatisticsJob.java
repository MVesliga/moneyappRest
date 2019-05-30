package hr.java.web.vesliga.moneyapp.jobs;

import hr.java.web.vesliga.moneyapp.model.Wallet;
import hr.java.web.vesliga.moneyapp.repositories.ExpenseRepository;
import hr.java.web.vesliga.moneyapp.repositories.WalletRepository;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class StatisticsJob extends QuartzJobBean {

    @Autowired
    ExpenseRepository expenseRepository;
    @Autowired
    WalletRepository walletRepository;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        List<?> lista = expenseRepository.listByExpenseType();

        System.out.format("%15s%15s%15s%15s\n", "", "SUM", "MIN", "MAX");
        for(int i = 0; i < lista.size(); i++){
            Object[] row = (Object[]) lista.get(i);
            System.out.format("%15s%15s%15s%15s\n", row[0], row[1], row[2], row[3]);
        }
        System.out.println("----------------------------------------------------------------");
    }
}
