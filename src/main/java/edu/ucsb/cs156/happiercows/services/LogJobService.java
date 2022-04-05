package edu.ucsb.cs156.happiercows.services;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import edu.ucsb.cs156.happiercows.entities.Commons;
import edu.ucsb.cs156.happiercows.entities.UserCommons;
import edu.ucsb.cs156.happiercows.repositories.CommonsRepository;
import edu.ucsb.cs156.happiercows.repositories.UserCommonsRepository;

import java.time.Duration;
import java.util.Optional;
import java.util.Random;

@Slf4j
@Service
public class LogJobService {
    @Autowired 
    private UserCommonsRepository userCommonsRepository;

    @Autowired 
    private CommonsRepository commonsRepository;

    @Async
    @SneakyThrows
    public void performLog() {
        log.info("Log job started, sleeping for 10 seconds");
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        log.info("Log job ended");
    }

    @Async
    @SneakyThrows
    public void performMilkCows() {
        log.info("Log milking job started");
        Iterable<Commons> allCommons = commonsRepository.findAll();
        for(Commons c : allCommons){
            int todaysDate = -1; //change to todays date in format of endDate
            if(c.getStartDate() < todaysDate && c.getEndDate() > todaysDate){
                Iterable<UserCommons> UserCommonsList = userCommonsRepository.findByCommonsId(c.getId());
                for(UserCommons uc : UserCommonsList){
                    log.info("total welath=" + uc.getTotalWealth() + (uc.getCowCount() * uc.getAverageCowHealth() * c.getMilkPrice()));
                    uc.setTotalWealth(uc.getTotalWealth() + (uc.getCowCount() * uc.getAverageCowHealth() * c.getMilkPrice()));
                    userCommonsRepository.save(uc);
                }
            }
        }
        log.info("Log milking job ended");
    }

    @Async
    @SneakyThrows
    public void performUpdateHealth() {
        log.info("Log health job started");
        Iterable<Commons> allCommons = commonsRepository.findAll();
        for(Commons c : allCommons){
            int todaysDate = -1; //change to todays date in format of endDate
            if(c.getStartDate() < todaysDate && c.getEndDate() > todaysDate){
                int totalCows = 0;
                Iterable<UserCommons> UserCommonsList = userCommonsRepository.findByCommonsId(c.getId());
                for(UserCommons uc : UserCommonsList){
                    totalCows += uc.getCowCount();
                }
                for(UserCommons uc : UserCommonsList){
                    uc.setAverageCowHealth(((c.getUserCount()*c.getCowsPerUserThereshold()) - totalCows) * .01);
                    userCommonsRepository.save(uc);
                }
            }
        }
        log.info("Log health job ended");
    }

    // @Async
    // @SneakyThrows
    // public void performKillCows() {
    //     log.info("Log kill job started");
    //     Iterable<Commons> allCommons = commonsRepository.findAll();
    //     for(Commons c : allCommons){
    //         int todaysDate = -1; //change to todays date in format of endDate
    //         if(c.getStartDate() < todaysDate && c.getEndDate() > todaysDate){
    //             Iterable<UserCommons> UserCommonsList = userCommonsRepository.findByCommonsId(c.getId());
    //             for(UserCommons uc : UserCommonsList){
    //                 Random r = new Random();
    //                 int rand = r.nextInt(100) + 1;
    //                 log.info("random int = " + rand);
    //                 if(uc.getAverageCowHealth() <= .2 && rand == 1){
    //                     uc.setCowCount(uc.getCowCount()-1);
    //                     userCommonsRepository.save(uc);
    //                 }
    //             }
    //         }
    //     }
    //     log.info("Log kill job ended");
    // }

    // @Async
    // @SneakyThrows
    // public void performGenerateReport() {
    //     log.info("Log report job started");
    //     Iterable<Commons> allCommons = commonsRepository.findAll();
    //     for(Commons c : allCommons){
    //         int todaysDate = -1; //change to todays date in format of endDate
    //         if(c.getStartDate() < todaysDate && c.getEndDate() > todaysDate){
    //             int totalCows = 0;
    //             int totalHealth = 0;
    //             Iterable<UserCommons> UserCommonsList = userCommonsRepository.findByCommonsId(c.getId);
    //             for(UserCommons uc : UserCommonsList){
    //                 totalCows += uc.getCowCount();
    //                 totalHealth += uc.getCowCount() * uc.getAverageHealth();
    //                 //add each user report to user report table
    //                 uc.getUserId(); //get user by id and get first and last name
    //                 uc.getCowCount();
    //                 uc.getAverageHealth();
    //                 uc.getTotalWealth();
    //                 //game day need start date
    //                 //profit
    //             }
    //             int totalAverageHealth = totalHealth/totalCows;
    //             totalAverageHealth;
    //             totalCows;
    //             todaysDate;
    //             //add commons info to commons report table
    //         }
    //     }
    //     log.info("Log report job ended");
    // }
}
