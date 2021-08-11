package mysql.hikari.lombok.spring.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import lombok.extern.slf4j.Slf4j;
import mysql.hikari.lombok.spring.config.SqlQueryProperties;
import mysql.hikari.lombok.spring.dao.EmployeeDAO;
import mysql.hikari.lombok.spring.model.Employee;
import mysql.hikari.lombok.spring.row.mapper.EmployeeRowMapper;

/**
 * Persistence API implementation to perform database operations.
 * 
 * @author metanoia
 * @version 1.0
 * @param <T>
 * 
 */
@Slf4j
@Repository
public class EmployeeDAOImpl implements EmployeeDAO<Employee> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SqlQueryProperties queryProperties;

	@Override
	public String getDepartmentId(int employeeId) {
		String departmentId = "";
		String FIND_DEPTT_NUMBER = queryProperties.getDepartmentId();

		log.info("Find Department Id By Employee Id {}. Query => {} ",
				employeeId, FIND_DEPTT_NUMBER);

		departmentId = jdbcTemplate.queryForObject(FIND_DEPTT_NUMBER,
				String.class, new Object[]{employeeId});

		return departmentId;
	}

	@Override
	public List<Employee> getAllEmployees() {
		String FIND_ALL_EMPLOYEES = queryProperties.getAllEmployees();

		log.info(" EmployeeDAO.getAllEmployees() Query => {} ",
				FIND_ALL_EMPLOYEES);

		List<Employee> empList = jdbcTemplate.query(FIND_ALL_EMPLOYEES,
				new EmployeeRowMapper());

		return empList;
	}

	@Override
	public Employee getEmpJobSalary(int employeeId) {
		String FIND_EMP_SALARY = queryProperties.getJobDetails();

		log.info(
				"EmployeeDAO.getEmpJobSalary() For Employee Id = {} . Query => {} ",
				employeeId, FIND_EMP_SALARY);

		Employee empSalary = jdbcTemplate.queryForObject(FIND_EMP_SALARY,
				new EmployeeRowMapper(), new Object[]{employeeId});

		return empSalary;
	}

	@Override
	public Employee getEmpDepartment(int employeeId, String departmentId) {
		String FIND_EMP_MANAGER = queryProperties.getDepartmentDetails();

		log.info(
				" EmployeeDAO.getEmpDepartment() For Employee Id {} and Depatment Number {}. Query => {} ",
				employeeId, departmentId, FIND_EMP_MANAGER);

		Employee empMgr = jdbcTemplate.queryForObject(FIND_EMP_MANAGER,
				new EmployeeRowMapper(),
				new Object[]{departmentId, employeeId});

		return empMgr;
	}

	@Override
	public Employee getEmployeeDetails(int employeeId) {

		Employee employee = getEmpJobSalary(employeeId);
		String departmentId = getDepartmentId(employeeId);

		if (ObjectUtils.isEmpty(departmentId)) {
			String msgformat = String.format(
					"Department Number not found for employee %d ", employeeId);
			log.error(msgformat);
			return new Employee();
		}

		Employee depttDetails = getEmpDepartment(employeeId, departmentId);

		employee.setEmpDepttDetails(depttDetails.getEmpDepttDetails());
		employee.setEmpManagerDetails(depttDetails.getEmpManagerDetails());

		log.info(" EmployeeDAO.getEmployeeDetails() for Employee Id {} ",
				employeeId);

		return employee;
	}

}
