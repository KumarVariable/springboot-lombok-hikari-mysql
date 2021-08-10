package mysql.hikari.lombok.spring.row.mapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import mysql.hikari.lombok.spring.model.DepartmentManager;
import mysql.hikari.lombok.spring.model.Departments;
import mysql.hikari.lombok.spring.model.Employee;
import mysql.hikari.lombok.spring.model.EmployeeDepartment;
import mysql.hikari.lombok.spring.model.EmployeeSalaryDetails;
import mysql.hikari.lombok.spring.model.GenderType;
import mysql.hikari.lombok.spring.model.JobTitle;

/**
 * Maps returned result set to an Employee object.
 * 
 * @author metanoia
 * @version 1.0
 */
public class EmployeeRowMapper implements RowMapper<Employee> {

	private final DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

	private static final Logger LOGGER = LoggerFactory
			.getLogger(EmployeeRowMapper.class);

	@Override
	public Employee mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {

		Employee employee = new Employee();

		employee.setEmployeeId(resultSet.getInt("employee_id"));

		employee.setEmployeeFirstName(
				resultSet.getString("employee_first_name"));
		employee.setEmployeeLastName(resultSet.getString("employee_last_name"));
		employee.setEmployeeFullName(resultSet.getString("employee_full_name"));

		String gender = GenderType.getEnumName(resultSet.getString("gender"));

		employee.setEmployeeGender(gender);

		String dateOfBirth = getStringDate(
				resultSet.getDate("employee_date_of_birth"));
		String hiringDate = getStringDate(
				resultSet.getDate("employee_hiring_date"));

		employee.setEmployeeDateOfBirth(dateOfBirth);
		employee.setEmployeeHiringDate(hiringDate);

		boolean hasJobTitle = hasColumn(resultSet, "job_title");
		boolean hasDepartment = hasColumn(resultSet, "department_name");

		if (hasJobTitle) {
			employee = setEmployeeJobDetails(resultSet, employee);
		}

		if (hasDepartment) {
			employee = setEmployeeDepartmentDetails(resultSet, employee);
		}

		return employee;
	}

	private boolean hasColumn(ResultSet resultSet, String source) {

		try {
			ResultSetMetaData rsMetaData = resultSet.getMetaData();
			int columnCount = rsMetaData.getColumnCount();

			for (int x = 1; x <= columnCount; x++) {
				String target = rsMetaData.getColumnLabel(x);

				if (target.equalsIgnoreCase(source)) {
					return true;
				}

			}

		} catch (Exception ex) {
			LOGGER.error("column doesn't exist {}", source);
		}

		return false;

	}

	private Employee setEmployeeJobDetails(ResultSet resultSet,
			Employee employee) {

		Currency currency = Currency.getInstance("INR");
		String currencySymbol = currency.getSymbol();

		JobTitle jobTitle = new JobTitle();
		Departments department = new Departments();
		EmployeeDepartment empDeptt = new EmployeeDepartment();
		EmployeeSalaryDetails salaryDetails = new EmployeeSalaryDetails();

		Map<Integer, EmployeeSalaryDetails> empSalaryDetails = new HashMap<Integer, EmployeeSalaryDetails>();
		Map<Integer, EmployeeDepartment> empDepttDetails = new HashMap<Integer, EmployeeDepartment>();

		Map<Integer, JobTitle> empJobDetails = new HashMap<Integer, JobTitle>();

		try {

			department.setDepartmentId(resultSet.getString("department_id"));

			empDeptt.setDepartments(department);
			empDepttDetails.put(resultSet.getInt("employee_id"), empDeptt);

			salaryDetails.setEmployeeId(resultSet.getInt("employee_id"));

			salaryDetails.setEmployeeSalary(resultSet.getInt("current_salary"));

			salaryDetails.setSalaryInCurrency(currencySymbol.concat(" ")
					.concat(String.valueOf(salaryDetails.getEmployeeSalary())));

			String salaryFromDate = getStringDate(
					resultSet.getDate("salary_from_date"));
			String salaryToDate = getStringDate(
					resultSet.getDate("salary_to_date"));

			salaryDetails.setEmployeeSalaryFromDate(salaryFromDate);
			salaryDetails.setEmployeeSalaryToDate(salaryToDate);

			jobTitle.setEmployeeId(resultSet.getInt("employee_id"));
			jobTitle.setJobTitle(resultSet.getString("job_title"));

			String titleFromDate = getStringDate(
					resultSet.getDate("title_from_date"));
			String titleToDate = getStringDate(
					resultSet.getDate("title_to_date"));

			jobTitle.setJobTitleFromDate(titleFromDate);
			jobTitle.setJobTitleToDate(titleToDate);

			empJobDetails.put(resultSet.getInt("employee_id"), jobTitle);
			empSalaryDetails.put(resultSet.getInt("employee_id"),
					salaryDetails);

			employee.setEmpDepttDetails(empDepttDetails);
			employee.setEmpSalaryDetails(empSalaryDetails);
			employee.setEmpJobDetails(empJobDetails);

		} catch (Exception ex) {
			LOGGER.error(
					"Excepton raised setEmployeeJobDetails() - Employee RowMapper",
					ex);

		}

		return employee;
	}

	private Employee setEmployeeDepartmentDetails(ResultSet resultSet,
			Employee employee) {

		Departments department = new Departments();
		DepartmentManager deptMgr = new DepartmentManager();
		EmployeeDepartment empDept = new EmployeeDepartment();

		Map<Integer, EmployeeDepartment> empDepttDetails = new HashMap<Integer, EmployeeDepartment>();
		Map<Integer, DepartmentManager> empManagerDetails = new HashMap<Integer, DepartmentManager>();

		try {

			department.setDepartmentId(resultSet.getString("department_id"));
			department
					.setDepartmentName(resultSet.getString("department_name"));

			empDept.setDepartments(department);
			empDept.setEmployeeId(resultSet.getInt("employee_id"));

			String deptJoiningDate = getStringDate(
					resultSet.getDate("dept_joining_date"));
			String deptLeavingDate = getStringDate(
					resultSet.getDate("dept_leaving_date"));

			empDept.setDeptJoiningDate(deptJoiningDate);
			empDept.setDeptLeavingDate(deptLeavingDate);

			deptMgr.setDepartmentId(resultSet.getString("department_id"));
			deptMgr.setManagerId(resultSet.getInt("manager_id"));
			deptMgr.setManagerName(resultSet.getString("manager_name"));

			String mgrFromDate = getStringDate(
					resultSet.getDate("manager_from_date"));
			String mgrToDate = getStringDate(
					resultSet.getDate("manager_to_date"));

			deptMgr.setManagerFromDate(mgrFromDate);
			deptMgr.setManagerToDate(mgrToDate);

			empDepttDetails.put(resultSet.getInt("employee_id"), empDept);
			empManagerDetails.put(resultSet.getInt("employee_id"), deptMgr);

			employee.setEmpDepttDetails(empDepttDetails);
			employee.setEmpManagerDetails(empManagerDetails);

		} catch (Exception ex) {
			LOGGER.error(
					"Excepton raised setEmployeeDepartmentDetails() - Employee RowMapper",
					ex);
		}

		return employee;
	}

	private String getStringDate(Date date) {
		return dateFormatter.format(date);
	}

}