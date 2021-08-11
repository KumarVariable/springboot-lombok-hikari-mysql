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

		int dummyEmpId = 10009;

		log.info("Find All Employees - limit to 5");

		findAllEmployees(employeeService);

		log.info("Find salary details for employee {} ", dummyEmpId);

		findEmployeeSalary(employeeService, dummyEmpId);

		log.info("Find department details for employee {} ", dummyEmpId);

		findEmployeeManager(employeeService, dummyEmpId);

		log.info("Find complete details for Employee {} ", dummyEmpId);

		findEmployeeDetails(employeeService, dummyEmpId);

	}

	private void findAllEmployees(EmployeeService employeeService) {
		try {

			List<Employee> employeeList = employeeService.findAllEmployees();

			for (Employee emp : employeeList) {
				log.info("Employees => {} ", emp.toString());
			}
		} catch (Exception ex) {
			log.error("Exception Caught at {} --> {} ",
					"SpringBootLombokMain.findAllEmployees()", ex.toString());
		}

	}

	private void findEmployeeSalary(EmployeeService employeeService,
			int employeeId) {
		try {
			Employee employee = employeeService.findEmployeeSalary(employeeId);
			log.info("Employee Salary Details of employee id = {} --->  {}",
					employeeId, employee.toString());
		} catch (Exception ex) {
			log.error("Exception Caught at {} --> {} ",
					"SpringBootLombokMain.findEmployeeSalary()", ex.toString());
		}

	}

	private void findEmployeeManager(EmployeeService employeeService,
			int employeeId) {

		try {
			Employee employee = employeeService.findEmployeeManager(employeeId);

			log.info("Employee Manager Details of employee id = {} ---> {} ",
					employeeId, employee.toString());
		} catch (Exception ex) {
			log.error("Exception Caught at {} --> {} ",
					"SpringBootLombokMain.findEmployeeManager()",
					ex.toString());
		}
	}

	private void findEmployeeDetails(EmployeeService employeeService,
			int employeeId) {

		try {
			Employee employee = employeeService.findEmployeeDetails(employeeId);

			log.info("Employee Manager Details of employee id = {} ---> {} ",
					employeeId, employee.toString());
		} catch (Exception ex) {
			log.error("Exception Caught at {} --> {} ",
					"SpringBootLombokMain.findEmployeeDetails()",
					ex.toString());
		}
	}

}
