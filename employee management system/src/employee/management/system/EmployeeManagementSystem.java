package employee.management.system;
import java.util.*;
import java.sql.*;

public class EmployeeManagementSystem {
 public static void main(String[] args) throws ClassNotFoundException {

    Class.forName("com.mysql.cj.jdbc.Driver");
    System.out.println("Driver loaded successfully");
    Connection conn = DBConnection.getConnection();
    
        EmployeeManagement emManagement = new EmployeeManagement();
        DepartmentManagement departmentManagement=new DepartmentManagement(conn);
        LeaveManagement leaveManagement=new LeaveManagement();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("/Main Menu:");
           System.out.println("1.Employee Management");
           System.out.println("2.Department Management");
           System.out.println("3.Leave Management");
           System.out.println("Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                while(true){
                    System.out.println("1. Add Employee");
                    System.out.println("2. Update Employee");
                    System.out.println("3. Delete Employee");
                    System.out.println("4. Get Employee Details by their id using BST");
                    System.out.println("5. Print All Employees using Linklist");
                    System.out.println("6. Sort Employee by their EmployeeId");
                    System.out.println("7. Exit");
                    System.out.println("Enter your choice");    
                    // Add Employee
                    int cchoice = scanner.nextInt();

                    switch (cchoice) {

                  case 1:  System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Phone: ");
                    String phone = scanner.nextLine();

                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();

                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();

                    System.out.print("Enter Job Title: ");
                    String jobTitle = scanner.nextLine();

                    System.out.print("Enter Departmentid: ");
                    int departmentid = scanner.nextInt();


                    System.out.println("Enter salary of employee ");
                    double salary=scanner.nextDouble();

                    System.out.print("Enter Manager ID: ");
                    int managerID = scanner.nextInt();

                    Employee newEmployee = new Employee(id, name, phone, email, address, jobTitle, departmentid,salary, managerID);
                    emManagement.AddEmployee(newEmployee);

                    break;

                case 2:
                    // Update Employee
                    System.out.print("Enter Employee ID to Update: ");
                    int updateID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    System.out.print("Enter Update Query (e.g., name='New Name'): ");
                    String updateData = scanner.nextLine();

                    emManagement.UpdateEmployee(updateID, updateData);
                    break;

                case 3:
                    // Delete Employee
                    System.out.print("Enter Employee ID to Delete: ");
                    int deleteID = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    emManagement.DeleteEmployee(deleteID);
                    break;

                case 4:
                    // Get Employee Details
                    System.out.print("Enter Employee ID to Retrieve Details: ");
                    int searchID = scanner.nextInt();

                    Employee employee = emManagement.GetEmployeeDetailsByID(searchID);
                    if (employee != null) {
                        System.out.println(employee);
                    } else {
                        System.out.println("Employee not found.");
                    }
                    break;


                case 5:
                    // Print All Employees
                    emManagement.PrintAllEmployees();
                    break;

                    case 6:
                    System.out.println("Sorted Employee Details");
                    emManagement.sort();
                    break;

                    case 7:
                    System.out.println("Exiting Employee Management System.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");

                }
            }
            
                    case 2:
                    while (true) {
                        System.out.println("\nDepartment Management System");
                        System.out.println("1. Add Department");
                        System.out.println("2. Update Department");
                        System.out.println("3. Delete Department");
                        System.out.println("4. Get All Departments");
                        System.out.println("5. Exit");
                        System.out.print("Enter your choice: ");
                        int choicee = scanner.nextInt();
                        scanner.nextLine();  // Consume newline
        
                        switch (choicee) {
                            case 1:
                                System.out.print("Enter Department ID: ");
                                int departmentID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
        
                                System.out.print("Enter Department Name: ");
                                String name = scanner.nextLine();
        
                                System.out.print("Enter Department Location: ");
                                String location = scanner.nextLine();
        
                                Department department = new Department(departmentID, name, location);
                                departmentManagement.addDepartment(department);
                                break;
        
                            case 2:
                                System.out.print("Enter Department ID to Update: ");
                                int updateID = scanner.nextInt();
                                scanner.nextLine();  // Consume newline
        
                                System.out.print("Enter New Department Name: ");
                                String newName = scanner.nextLine();
        
                                System.out.print("Enter New Department Location: ");
                                String newLocation = scanner.nextLine();
        
                                departmentManagement.updateDepartment(updateID, newName, newLocation);
                                break;
        
                            case 3:
                                System.out.print("Enter Department ID to Delete: ");
                                int deleteID = scanner.nextInt();
                                departmentManagement.deleteDepartment(deleteID);
                                break;
        
                            case 4:
                                departmentManagement.getAllDepartments();
                                break;
        
                            case 5:
                                System.out.println("Exiting Department Management System.");
                                scanner.close();
                                System.exit(0);
        
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }

                    case 3:
                    while (true) {
                        System.out.println("\nLeave Management System");
                        System.out.println("1. Request Leave");
                        System.out.println("2. Approve Leave");
                        System.out.println("3. Get Leave Balance");
                        System.out.println("4. Get Leave History");
                        System.out.println("5. Exit");
                        System.out.print("Choose an option: ");
                        int choiceee = scanner.nextInt();
            
                        switch (choiceee) {
                            case 1:
                                leaveManagement.requestLeave();
                                break;
                            case 2:
                                leaveManagement.approveLeave();
                                break;
                            case 3:
                                leaveManagement.getLeaveBalance();
                                break;
                            case 4:
                                leaveManagement.getLeaveHistory();
                                break;
                            case 5:
                                System.out.println("Exiting...");
                                System.exit(0);
                            default:
                                System.out.println("Invalid choice. Please try again.");
                        }
                    }


                case 4:
                    // Exit
                    scanner.close();
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
        
       
    }

