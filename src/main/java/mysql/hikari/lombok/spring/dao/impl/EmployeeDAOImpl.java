package mysql.hikari.lombok.spring.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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
@Repository
public class EmployeeDAOImpl implements EmployeeDAO<Employee> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeDAOImpl.class);

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	SqlQueryProperties queryProperties;

	@Override
	public String getDepartmentId(int employeeId) {
		String FIND_DEPTT_NUMBER = queryProperties.getDepartmentId();

		return jdbcTemplate.queryForObject(FIND_DEPTT_NUMBER, String.class,
				new Object[]{employeeId});
	}

	@Override
	public List<Employee> getAllEmployees() {
		String FIND_ALL_EMPLOYEES = queryProperties.getAllEmployees();

		List<Employee> empList = jdbcTemplate.query(FIND_ALL_EMPLOYEES,
				new EmployeeRowMapper());

		return empList;
	}

	@Override
	public Employee getEmpJobSalary(int employeeId) {
		String FIND_EMP_SALARY = queryProperties.getJobDetails();

		Employee empSalary = jdbcTemplate.queryForObject(FIND_EMP_SALARY,
				new EmployeeRowMapper(), new Object[]{employeeId});

		return empSalary;
	}

	@Override
	public Employee getEmpDepartment(int employeeId, String departmentId) {
		String FIND_EMP_MANAGER = queryProperties.getDepartmentDetails();

		Employee empMgr = jdbcTemplate.queryForObject(FIND_EMP_MANAGER,
				new EmployeeRowMapper(),
				new Object[]{departmentId, employeeId});

		return empMgr;
	}

	@Override
	public Employee getEmployeeDetails(int employeeId) {

		Employee employee = getEmpJobSalary(employeeId);
		String departmentId = getDepartmentId(employeeId);

		Employee depttDetails = getEmpDepartment(employeeId, departmentId);

		employee.setEmpDepttDetails(depttDetails.getEmpDepttDetails());
		employee.setEmpManagerDetails(depttDetails.getEmpManagerDetails());

		return employee;
	}

}
