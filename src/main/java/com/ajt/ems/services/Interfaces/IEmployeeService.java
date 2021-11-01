package com.ajt.ems.services.Interfaces;

import com.ajt.ems.models.ApiResponse.GenericApiResponse;
import com.ajt.ems.models.db.ems.Employees;

import java.util.List;

public interface IEmployeeService {

	public List<Employees> getAllEmployees();

	public Employees getEmployeeById(Integer employeeId);

	public GenericApiResponse saveEmployee(Employees employee);

	public GenericApiResponse updateEmployeeDetails(Integer id, Employees employee);

	public GenericApiResponse deleteEmployeeById(Integer employeeId);

}
