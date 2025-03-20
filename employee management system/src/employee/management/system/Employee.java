package employee.management.system;

import java.util.Date;

public class Employee {
    int employeeID;
    String name;
    String phone;
    String email;
    String address;
    String jobTitle;
    int departmentid;
     Date startDate;
     double salary;
     int managerID;


     Employee(int employeeID, String name, String phone, String email, String address, 
                    String jobTitle, int departmentid, double salary, int managerID) {
        this.employeeID = employeeID;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.jobTitle = jobTitle;
        this.departmentid = departmentid;
       
        this.salary = salary;
        this.managerID = managerID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDepartment(int departmentid) {
        this.departmentid = departmentid;
    }

   
    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    
    @Override
    public String toString() {
        return "Employee [employeeID=" + employeeID + ", name=" + name + ", phone=" + phone + ", email=" + email
                + ", address=" + address + ", jobTitle=" + jobTitle + ", department=" + departmentid +  ", salary=" + salary + ", managerID=" + managerID + "]";
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public int getDepartmenid() {
        return departmentid;
    }

   
    public double getSalary() {
        return salary;
    }

    public int getManagerID() {
        return managerID;
    }
}



 
