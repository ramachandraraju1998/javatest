package com.example.testsql.controller;


import com.example.testsql.entity.Employee;
import com.example.testsql.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    @GetMapping("/employee")
    public Map<String, List<Map<String,Object>>> getAllEmployees() {
    	List<Employee> data = employeeService.fetchAllEmployees();
        List<Map<String, Object>> recList = new ArrayList<>();
    	for(int i=0;i<data.size();i++) {
            Map<String, Object> map1 = new HashMap<>();
        	float anual_sal= data.get(i).getSalary()*12;
        	String slab_dataString="";
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            String doj = data.get(i).getDOJ();
            LocalDate doj_date = LocalDate.parse(doj, formatter);
            int currentYear = Year.now().getValue();
            LocalDate finance_start_year = LocalDate.of(currentYear-1, 4, 1); //2022 4 1
            
        	// calculation if doj after financial year starts
            if (!doj_date.isBefore(finance_start_year)) {
              int daysInCurrentYear = (int) ChronoUnit.DAYS.between(Year.of(currentYear).atDay(1), Year.of(currentYear).atDay(365));
              float day_sal= anual_sal/daysInCurrentYear;
              LocalDate finance_end_date = LocalDate.of(currentYear, 3, 31);
              int daysworked = (int)ChronoUnit.DAYS.between(doj_date, finance_end_date);
              anual_sal = daysworked*day_sal;
            }
 
        	
        	if(anual_sal<=250000) {
        		slab_dataString="No Tax";
        	}
        	
        	if(anual_sal>250000 && anual_sal<=500000) {
        		slab_dataString="5% Tax";
        	}
        	
        	if(anual_sal>500000  && anual_sal<=1000000) {
        		slab_dataString="10% Tax";
        	}
       
        	if(anual_sal>1000000) {
        		slab_dataString="20% Tax";
        	}
        
        	map1.put("FirstName",data.get(i).getFirstName());
        	map1.put("LastName",data.get(i).getLastName());
        	map1.put("Email",data.get(i).getEmail());
        	map1.put("Phone",data.get(i).getPhone());
        	map1.put("DOJ",data.get(i).getDOJ());
        	map1.put("Salary",data.get(i).getSalary());
        	map1.put("slab",slab_dataString);
        	recList.add(map1);
    	}
    	Map<String, List<Map<String,Object>>> response = new HashMap<>();
    	response.put("data",recList);
        return response;
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

}
