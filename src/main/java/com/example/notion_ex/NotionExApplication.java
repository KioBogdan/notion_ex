package com.example.notion_ex;

import com.example.notion_ex.model.*;
import com.example.notion_ex.repository.*;
import com.example.notion_ex.service.FinancialActivityService;
import com.example.notion_ex.service.ToDoActivityService;
import com.example.notion_ex.service.impl.ToDoActivityImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan(basePackages = {"com.example.notion_ex"})
public class NotionExApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotionExApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepo userRepo, ToDoActivityRepo toDoActivityRepo, ReadActivityRepo readBooksActivityRepo,
						   FinancialActivityRepo financialActivityRepo, ProjectActivityRepo projectTasksActivityRepo) {
		return args -> {
			User user = new User();
			user.setFirstname("Gigel");
			user.setLastname("Petrica");
			userRepo.save(user);

			User user1 = new User();
			user1.setFirstname("Costel");
			user1.setLastname("Petrica");
			userRepo.save(user1);

			User admin = new User();
			admin.setFirstname("Costelica");
			admin.setLastname("Cercel");
			userRepo.save(admin);

			User clientRef = new User();
			clientRef.setFirstname("Bogdan");
			clientRef.setLastname("Chiorean");
			clientRef.setEmail("bogdan_chiorean57@yahoo.com");
			clientRef.setPassword("1234");
			clientRef.setUserRole(UserRole.CLIENT);
			userRepo.save(clientRef);

			/*User client = new User();
			client.setName("Petrica");
			userRepo.save(client);*/

			/*User user2 = new Client();
			user1.setName("Costel");
			clientRepo.save(user1);*/

			ToDoActivity activity = new ToDoActivity();
			activity.setTaskName("Must implement Java functions");
			toDoActivityRepo.save(activity);

			ToDoActivity activity1 = new ToDoActivity();
			activity1.setId(2L);
			activity1.setTaskName("This homework is due");
			toDoActivityRepo.save(activity1);

			FinancialActivity act = new FinancialActivity();
			act.setExpense("Groceries");
			act.setCategory("Must");
			financialActivityRepo.save(act);

			FinancialActivity act1 = new FinancialActivity();
			act1.setExpense("Financial Course");
			act1.setCategory("Must");
			act1.setAmount(1000);
			//act1.setUser(user1);
			financialActivityRepo.save(act1);

			FinancialActivity act2 = new FinancialActivity();
			act2.setExpense("Weekend fun");
			act2.setCategory("Probably");
			act1.setAmount(250);
			financialActivityRepo.save(act2);

			System.out.println(financialActivityRepo.findByExpense(("Groceries")).get());

			ProjectActivity prAct = new ProjectActivity();
			prAct.setProjectName("Dealing with Java bugs");
			projectTasksActivityRepo.save(prAct);

			ProjectActivity prAct1 = new ProjectActivity();
			prAct1.setProjectName("Assurance");
			projectTasksActivityRepo.save(prAct1);

			ReadActivity readAct = new ReadActivity();
			readAct.setType("Book");
			readBooksActivityRepo.save(readAct);


		};
	}
}
