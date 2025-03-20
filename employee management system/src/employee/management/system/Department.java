package employee.management.system;



public class Department {
     int departmentID;
     String name;
     String location;

    public Department(int departmentID, String name, String location) {
        this.departmentID = departmentID;
        this.name = name;
        this.location = location;
    }

    public int getDepartmentID() {
        return departmentID;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Department [departmentID=" + departmentID + ", name=" + name + ", location=" + location + "]";
    }

    

}
