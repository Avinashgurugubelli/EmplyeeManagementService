package com.ajt.ems.services;

import com.ajt.ems.exception.ApiException;
import com.ajt.ems.models.ApiResponse.GenericApiResponse;
import com.ajt.ems.models.db.ems.Employees;
import com.ajt.ems.repositories.EmployeeRepository;
import com.ajt.ems.services.Interfaces.IEmployeeService;
import com.ajt.ems.utils.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employees> getAllEmployees() {
		return this.employeeRepository.findAll();
	}

	@Override
	public Employees getEmployeeById(Integer employeeId) {
		Optional<Employees> employee = this.employeeRepository.findById(employeeId);
		if (!employee.isPresent()) {
			throw new ApiException("No record found with employee id: " + employeeId.toString(), HttpStatus.NOT_FOUND, null);
		}
		return employee.get();
	}

	@Override
	public GenericApiResponse saveEmployee(Employees employee) {
		try {
			Employees saveEmployee = this.employeeRepository.save(employee);
			return new GenericApiResponse("Employee successfully saved", saveEmployee, HttpStatus.CREATED);
		} catch (Exception ex) {
			throw new ApiException("Some exception occurred while saving employee: " + ex.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}

	@Override
	public GenericApiResponse updateEmployeeDetails(Integer id, Employees employee) {
		try {
			Employees employeeToUpdate = this.getEmployeeById(id);
			employeeToUpdate.setFirstName(employee.getFirstName());
			employeeToUpdate.setLastName(employee.getLastName());
			employeeToUpdate.setEmail(employee.getEmail());
			employeeToUpdate.setManagerId(employee.getManagerId());
			employeeToUpdate.setPhoneNumber(employee.getPhoneNumber());
			employeeToUpdate.setHireDate(employee.getHireDate());
			employeeToUpdate.setSalary(employee.getSalary());
			Employees updatedEmployee = this.employeeRepository.save(employeeToUpdate);
			return new GenericApiResponse("Employee successfully updated", updatedEmployee, HttpStatus.CREATED);
		} catch (ConstraintViolationException ex) {
			throw new ApiException("ConstraintViolation exception occurred while updating employee details: " + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
		} catch (DataIntegrityViolationException ex) {
			throw new ApiException("DataIntegrityViolation exception occurred while updating employee details: " + ex.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
		} catch (Exception ex) {
			throw new ApiException("Some exception occurred while updating employee details: " + ex.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}

	@Override
	public GenericApiResponse deleteEmployeeById(Integer employeeId) {
		try {
			this.employeeRepository.deleteById(employeeId);
			return new GenericApiResponse("Employee successfully deleted", null, HttpStatus.OK);
		} catch (EmptyResultDataAccessException ex) {
			throw new ApiException("employee with id " + employeeId.toString() + " not exists in DB: " + ex.getMessage(), HttpStatus.NOT_FOUND, null);

		} catch (Exception ex) {
			throw new ApiException("Some exception occurred while Deleting employee: " + ex.getMessage(), HttpStatus.NOT_FOUND, null);
		}
	}
}
