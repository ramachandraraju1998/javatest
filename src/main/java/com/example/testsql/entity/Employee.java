package com.example.testsql.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import jakarta.persistence.Entity;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String FirstName;
    private String LastName;
    private String Email;
    private String Phone;
    private String DOJ;
    private float Salary;
    @Transient
    private String slab;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String email, String phone, String doj, float salary) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.Email = email;
        this.Phone = phone;
        this.DOJ = doj;
        this.Salary = salary;
    }

 
    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        this.FirstName = firstName;
    }


    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        this.LastName = lastName;
    }


    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }


    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        this.Phone = phone;
    }


    public String getDOJ() {
        return DOJ;
    }

    public void setDOJ(String doj) {
        this.DOJ = doj;
    }

    public String getSlab() {
    	float anual_sal= this.Salary*12;
    	String slab_dataString="";
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
        return slab_dataString;
    }

    public void setSlab(String slab) {
        this.slab = slab;
    }
   
    public float getSalary() {
        return Salary;
    }

    public void setSalary(float salary) {
        this.Salary = salary;
    }
}
