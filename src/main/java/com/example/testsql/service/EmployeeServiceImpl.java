package com.example.testsql.service;


import com.example.testsql.entity.Employee;
import com.example.testsql.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
//        for(int i=0;i<allEmployees.size();i++) {
//        	float anual_sal = allEmployees.get(i).getSalary()*12;
//        	System.out.println("annnnn"+anual_sal);
//        	allEmployees.get(i).setSlab("");
//        	if(anual_sal<=250000) {
//        		allEmployees.get(i).setSlab("No Tax");
//        	}
//        	
//        	if(anual_sal>250000 && anual_sal<=500000) {
//        		allEmployees.get(i).setSlab("5% Tax");
//        	}
//        	
//        	if(anual_sal>500000  && anual_sal<=1000000) {
//        		allEmployees.get(i).setSlab("10% Tax");
//        	}
//        	
//        	if(anual_sal>1000000) {
//        		allEmployees.get(i).setSlab("20% Tax");
//        	}
//        }
        return allEmployees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        return null;
    }


}
