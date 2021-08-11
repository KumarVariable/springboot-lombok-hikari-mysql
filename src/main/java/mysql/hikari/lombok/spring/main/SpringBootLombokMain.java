package mysql.hikari.lombok.spring.main;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;
import mysql.hikari.lombok.spring.model.Employee;
import mysql.hikari.lombok.spring.service.EmployeeService;

/**
 * Main Class to bootstrap a Spring application as a stand-alone Spring
 * application. It creates an appropriate {@link ApplicationContext} instance,
 * and load beans. If implemented using specified class this main class will
 * start a Servlet container and will host our application in that servlet
 * container
 * 
 * @author metanoia
 * @version 1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"mysql.hikari.lombok.spring"})
@ConfigurationPropertiesScan("mysql.hikari.lombok.spring.config")
public class SpringBootLombokMain implements CommandLineRunner {

	@Autowired
	ApplicationContext appContext;

	@Autowired
	EmployeeService employeeService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLombokMain.class, args);
	}

	/**
	 * Callback used to run the bean.
	 * 
	 * @param args
	 *            - incoming main method arguments
	 * @throws Exception
	 *             - on error
	 */
	@Override
	public void run(String... args) throws Exception {
		log.info(" << Total Beans configured at Spring startup = {} ",
				appContext.getBeanDefinitionCount());

		Map<String, DataSource> dataSource = appContext
				.getBeansOfType(DataSource.class);

		dataSource.forEach((k, v) -> {
			log.info(k + " - " + v);
		});

		log.info("Uncomment code to Find All Employees - limit to 5");

		findAllEmployees(employeeService);

		log.info(
				"Uncomment below code to find salary details by employee id. ");

		findEmployeeSalary(employeeService, 10009);

		log.info(
				"Uncomment below code to find department details by employee id.");

		findEmployeeManager(employeeService, 10009);

		log.info(
				"Uncomment below code to find complete details of an Employee.");

		findEmployeeDetails(employeeService, 10009);

	}

	@SuppressWarnings("unused")
	private void findAllEmployees(EmployeeService employeeService) {
		List<Employee> employeeList = employeeService.findAllEmployees();

		for (Employee emp : employeeList) {
			log.info("Employee details => {} ", emp.toString());
		}

	}

	@SuppressWarnings(value = {"unused"})
	private void findEmployeeSalary(EmployeeService employeeService,
			int employeeId) {
		Employee employee = employeeService.findEmployeeSalary(employeeId);
		log.info("Employee Salary Details of employee id = {} --->  {}",
				employeeId, employee.toString());

	}

	@SuppressWarnings(value = {"unused"})
	private void findEmployeeManager(EmployeeService employeeService,
			int employeeId) {

		Employee employee = employeeService.findEmployeeManager(employeeId);

		log.info("Employee Manager Details of employee id = {} ---> {} ",
				employeeId, employee.toString());
	}

	private void findEmployeeDetails(EmployeeService employeeService,
			int employeeId) {

		Employee employee = employeeService.findEmployeeDetails(employeeId);

		log.info("Employee Manager Details of employee id = {} ---> {} ",
				employeeId, employee.toString());
	}

}
