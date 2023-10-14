package com.example.moretech5back;

import com.example.moretech5back.model.Branch;
import com.example.moretech5back.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
@Slf4j
public class Moretech5backApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        try {
            log.info(InetAddress.getLocalHost().toString());
        } catch (UnknownHostException e) {
            log.info("unknown_host");
        }
        SpringApplication.run(Moretech5backApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(BranchService branchService) {
        return args -> {
            for (int i = 0; i < 10; i++) {
                double off = 100;
                Branch branch = Branch.builder()
                        .name("Branch" + i)
                        .address("Street "  + (i % 3) + " House " + ThreadLocalRandom.current().nextLong(1, 11))
                        .load(ThreadLocalRandom.current().nextLong(1, 11))
                        .x(ThreadLocalRandom.current().nextDouble(1, off + 1))
                        .y(ThreadLocalRandom.current().nextDouble(1, off + 1)).build();

                branchService.save(
                        branch.getName(),
                        branch.getAddress(),
                        branch.getLoad(),
                        branch.getX(),
                        branch.getY());
            }
        };
    }
}
