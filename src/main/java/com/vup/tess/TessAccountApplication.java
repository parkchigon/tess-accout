package com.vup.tess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.vup.tess.proc.worker.TargetMonthAccountStatProc;

@SpringBootApplication
@EnableScheduling
public class TessAccountApplication {
	
	@Autowired
	private TargetMonthAccountStatProc targetMonthAccountStatProc;
	
	public static void main(String[] args) {
		SpringApplication.run(TessAccountApplication.class, args);
	}

	@Scheduled(cron = "${schedule-job.account-month.delay}")
	public void startAccount() throws InterruptedException{
		targetMonthAccountStatProc.doService(); 
	}
	
}

