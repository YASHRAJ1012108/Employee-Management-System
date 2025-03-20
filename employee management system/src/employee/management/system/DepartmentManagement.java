
package employee.management.system;
import java.sql.*;
import java.util.*;


public class DepartmentManagement {

    private Connection connection;
    HashMap<Integer, Department> dp = new HashMap<>();
    public DepartmentManagement(Connection connection) {
        this.connection = connection;
    }

    public void addDepartment(Department department) {
        String sql = "INSERT INTO departments (departmentid, name, location) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, department.getDepartmentID());
            pstmt.setString(2, department.getName());
            pstmt.setString(3, department.getLocation());
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department added successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error adding department: " + e.getMessage());
        }
    }

    public void updateDepartment(int departmentID, String name, String location) {
        String sql = "UPDATE departments SET name = ?, location = ? WHERE departmentid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, location);
            pstmt.setInt(3, departmentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department updated successfully.");
            } else {
                System.out.println("Department not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating department: " + e.getMessage());
        }
    }

    public void deleteDepartment(int departmentID) {
        String sql = "DELETE FROM departments WHERE departmentid = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, departmentID);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Department deleted successfully.");
            } else {
                System.out.println("Department not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting department: " + e.getMessage());
        }
    }

    public void getAllDepartments() {
        String sql = "SELECT * FROM departments";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int departmentID = rs.getInt("departmentid");
                String name = rs.getString("name");
                String location = rs.getString("location");
                Department department = new Department(departmentID, name, location);
                System.out.println(department);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving departments: " + e.getMessage());
        }
    }
}
