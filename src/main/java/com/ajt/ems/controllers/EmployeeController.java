package com.ajt.ems.controllers;

import com.ajt.ems.exception.ApiException;
import com.ajt.ems.models.ApiResponse.GenericApiResponse;
import com.ajt.ems.models.db.ems.Employees;
import com.ajt.ems.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ems")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<?> getAllEmployee() {
		try {
			List<Employees> employees = this.employeeService.getAllEmployees();
			if (employees != null) {
				return ResponseEntity.ok(employees);
			} else return ResponseEntity.ok("No Records found");
		} catch (ApiException ex) {
			return new ResponseEntity<>(ex, ex.getApiError().type);
		} catch (Exception ex) {
			String errorMsg = "Some exception occurred while fetching employees: " + ex.getMessage();
			ApiException exception = new ApiException(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, ex);
			return new ResponseEntity<ApiException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable Integer id) {
		try {
			Employees employees = this.employeeService.getEmployeeById(id);
			if (employees != null) {
				return ResponseEntity.ok(employees);
			} else return ResponseEntity.ok("No Records found");
		} catch (ApiException ex) {
			return new ResponseEntity<>(ex, ex.getApiError().type);
		} catch (Exception ex) {
			String errorMsg = "Some exception occurred while fetching employees: " + ex.getMessage();
			ApiException exception = new ApiException(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, ex);
			return new ResponseEntity<ApiException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping(
			path = "/employees",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> saveEmployee(@RequestBody Employees employee) {
		try {
			GenericApiResponse response = this.employeeService.saveEmployee(employee);
			return new ResponseEntity(response, response.status);
		} catch (ApiException ex) {
			return new ResponseEntity<>(ex, ex.getApiError().type);
		} catch (Exception ex) {
			String errorMsg = "Some exception occurred while saving employee: " + ex.getMessage();
			ApiException exception = new ApiException(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, ex);
			return new ResponseEntity<ApiException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(
			path = "/employees/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<?> updatedEmployee(
			@PathVariable Integer id,
			@RequestBody Employees employee) {
		try {
			GenericApiResponse response = this.employeeService.updateEmployeeDetails(id, employee);
			return new ResponseEntity(response, response.status);
		} catch (ApiException ex) {
			return new ResponseEntity<>(ex, ex.getApiError().type);
		} catch (Exception ex) {
			String errorMsg = "Some exception occurred while updating employee: " + ex.getMessage();
			ApiException exception = new ApiException(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, ex);
			return new ResponseEntity<ApiException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<?> deleteByEmployeeId(@PathVariable Integer id) {
		try {
			GenericApiResponse response = this.employeeService.deleteEmployeeById(id);
			return new ResponseEntity(response, response.status);
		} catch (ApiException ex) {
			return new ResponseEntity<>(ex, ex.getApiError().type);
		} catch (Exception ex) {
			String errorMsg = "Some exception occurred while deleting employee: " + ex.getMessage();
			ApiException exception = new ApiException(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR, ex);
			return new ResponseEntity<ApiException>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
