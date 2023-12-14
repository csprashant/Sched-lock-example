package com.inno;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import net.javacrumbs.shedlock.core.SchedulerLock;
import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedLockExampleApplication {

	private Logger log = LoggerFactory.getLogger(ShedLockExampleApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ShedLockExampleApplication.class, args);
	}
	
	/*
	 * This is locked scheduler only one will be running at a time even if there are 
	 * Multiple instance
	 */
	@Scheduled(fixedDelayString = "PT10S")
	@SchedulerLock(name = "TaskScheduler_scheduledTask", lockAtLeastForString = "PT1M", lockAtMostForString = "PT2M")
	public void sessionMonitorScheduler() {
		log.info("SessionMonitor Scheduler  Date : {}", new Date());
	}
	
	/*
	 * This is normal scheudler and will run for every instance for same time of occurrence also 
	 */
	@Scheduled(fixedDelayString = "PT5S")
	public void eventAndNotificationManagementScheduler() {
		log.info("eventAndNotificationManagement Scheduler ================>{}", new Date());
	}
	

}
