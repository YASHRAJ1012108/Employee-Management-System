package employee.management.system;

import java.sql.*;
import java.util.*;

public class EmployeeManagement {
          EmployeeLinkedList employeeList=new EmployeeLinkedList();
          EmployeeBST employeeBST=new EmployeeBST();
          ArrayList<Employee> al=new ArrayList<>();
        
    public void AddEmployee(Employee employeeData) {
        String query = "INSERT INTO Employees (employeeID, name, phone, email, address, jobTitle, departmentid, salary,managerID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employeeData.getEmployeeID());
            pstmt.setString(2, employeeData.getName());
            pstmt.setString(3, employeeData.getPhone());
            pstmt.setString(4, employeeData.getEmail());
            pstmt.setString(5, employeeData.getAddress());
            pstmt.setString(6, employeeData.getJobTitle());
           
            pstmt.setInt(7, employeeData.getDepartmenid());
            pstmt.setDouble(8, employeeData.getSalary());
            pstmt.setInt(9, employeeData.getManagerID());
            int r=pstmt.executeUpdate();
            if(r>0){
                System.out.println("data inserted successfully");
            }
            else{
                System.out.println("data not inserted");
            }


            // Add to custom linked list
            
            al.addLast(employeeData);
            employeeBST.insert(employeeData);
            employeeList.addFirst(employeeData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateEmployee(int employeeID, String updatedData) {
        String query = "UPDATE Employees SET " + updatedData + " WHERE employeeID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, employeeID);
          int r=  pstmt.executeUpdate();
          if(r>0){
            System.out.println("data updated successfully");
          }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void DeleteEmployee(int employeeIDD) {
        
        String query = "DELETE FROM employees WHERE employeeID = ?";

        try (Connection conn = DBConnection.getConnection();
        
        PreparedStatement cstmt = conn.prepareStatement(query)) {

            cstmt.setInt(1, employeeIDD);
            int d=cstmt.executeUpdate();
            if(d>0){
                System.out.println("Data deleted successfully");
            }
            else{
                System.out.println("Data not deleted");
            }
            

            // Remove from custom linked list
           // employeeList.delete(employeeIDD);
          //  al.remove(employeeIDD);
            employeeBST.delete(employeeIDD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Employee GetEmployeeDetailsByID(int employeeID) {
        return employeeBST.search(employeeID);
    }   

    public Employee GetEmployeeDetails(int employeeID) {
        Employee employee = employeeList.find(employeeID);
        if (employee != null) {
            return employee;
        } else {
            System.out.println("Employee not found in the system.");
            return null;
        }
    }

    public void PrintAllEmployees() {
        String query = "SELECT * FROM Employees";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("employeeID");
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String jobTitle = rs.getString("jobTitle");
                int departmentid = rs.getInt("departmentid");

                double salary=rs.getDouble("salary");
                int managerID = rs.getInt("managerID");

                System.out.println("ID: " + id + ", Name: " + name + ", Phone: " + phone + ", Email: " + email +
                        ", Address: " + address + ", Job Title: " + jobTitle + ", Department: " + departmentid +", salary: " + salary+ ", Manager ID: " + managerID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        //  public void PrintAllEmployeesUsingLinklist(){
        //     for(Employee data:employeeList){
        //         System.out.println(data);
        //     }
        //  }
    

    public void sort(){
        al.sort(Comparator.comparing(Employee::getEmployeeID));
        for(Employee data:al){
            System.out.println(data);
        }
    }

    
    }

