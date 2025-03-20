package employee.management.system;



class Node {
    Employee data;
    Node next;

    public Node(Employee data) {
        this.data = data;
        this.next = null;
    }
}


public class EmployeeLinkedList {
    private Node head;

    
    public void addFirst(Employee employee) {
        Node newNode = new Node(employee);
        if (head == null) {
            head = newNode;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    
    public boolean delete(int employeeID) {
        if (head == null) return false;

     
        if (head.data.employeeID == employeeID) {
            head = head.next;
            return true;
        }

        Node current = head;
        Node previous = null;

        while (current != null && current.data.employeeID != employeeID) {
            previous = current;
            current = current.next;
        }

     
        if (current == null) return false;

      
        previous.next = current.next;
        return true;
    }

    public void printAllEmployees() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

  
    public Employee find(int employeeID) {
        Node temp = head;
        while (temp != null) {
            if (temp.data.employeeID == employeeID) {
                return temp.data;
            }
            temp = temp.next;
        }
        return null;
    }
}
