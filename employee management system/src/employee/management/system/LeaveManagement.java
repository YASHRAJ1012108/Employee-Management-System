package employee.management.system;


import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class LeaveManagement {

    // Request Leave
    public void requestLeave() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        System.out.print("Enter Leave Type (e.g., Sick, Vacation): ");
        String leaveType = scanner.nextLine();

        System.out.print("Enter Start Date (YYYY-MM-DD): ");
        String startDateInput = scanner.nextLine();
        Date startDate = Date.valueOf(startDateInput);

        System.out.print("Enter End Date (YYYY-MM-DD): ");
        String endDateInput = scanner.nextLine();
        Date endDate = Date.valueOf(endDateInput);

        String insertLeaveQuery = "INSERT INTO leaves (employeeID, leaveType, startDate, endDate) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertLeaveQuery)) {

            preparedStatement.setInt(1, employeeID);
            preparedStatement.setString(2, leaveType);
            preparedStatement.setDate(3, startDate);
            preparedStatement.setDate(4, endDate);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Leave request submitted successfully.");
            } else {
                System.out.println("Failed to submit leave request.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to approve leave
    public void approveLeave() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Leave Request ID to approve: ");
        int requestID = scanner.nextInt();

        String getLeaveDetailsQuery = "SELECT employeeID, startDate, endDate FROM leaves WHERE requestID = ?";
        String updateLeaveBalanceQuery = "UPDATE employees SET LeaveBalances = LeaveBalances - ? WHERE employeeID = ?";
        String updateLeaveStatusQuery = "UPDATE leaves SET status = 'Approved' WHERE requestID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement getLeaveStatement = connection.prepareStatement(getLeaveDetailsQuery);
             PreparedStatement updateBalanceStmt = connection.prepareStatement(updateLeaveBalanceQuery);
             PreparedStatement updateStatusStmt = connection.prepareStatement(updateLeaveStatusQuery)) {

            getLeaveStatement.setInt(1, requestID);
            ResultSet resultSet = getLeaveStatement.executeQuery();

            if (resultSet.next()) {
                int employeeID = resultSet.getInt("employeeID");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");

                long differenceInMillis = endDate.getTime() - startDate.getTime();
                int leaveDays = (int) (differenceInMillis / (1000 * 60 * 60 * 24)) + 1;

                updateBalanceStmt.setInt(1, leaveDays);
                updateBalanceStmt.setInt(2, employeeID);
                updateBalanceStmt.executeUpdate();

                updateStatusStmt.setInt(1, requestID);
                updateStatusStmt.executeUpdate();

                System.out.println("Leave approved successfully.");
            } else {
                System.out.println("Leave request not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get leave balance
    public void getLeaveBalance() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();

        String query = "SELECT LeaveBalances FROM employees WHERE employeeID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int leaveBalance = resultSet.getInt("LeaveBalances");
                System.out.println("Leave balance: " + leaveBalance);
            } else {
                System.out.println("Employee not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get leave history
    public void getLeaveHistory() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee ID: ");
        int employeeID = scanner.nextInt();

        String query = "SELECT * FROM leaves WHERE employeeID = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, employeeID);
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Leave history for Employee ID: " + employeeID);
            while (resultSet.next()) {
                int requestID = resultSet.getInt("requestID");
                String leaveType = resultSet.getString("leaveType");
                Date startDate = resultSet.getDate("startDate");
                Date endDate = resultSet.getDate("endDate");
                String status = resultSet.getString("status");

                System.out.println("Request ID: " + requestID + ", Leave Type: " + leaveType +
                                   ", Start Date: " + startDate + ", End Date: " + endDate +
                                   ", Status: " + status);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
  
}}
