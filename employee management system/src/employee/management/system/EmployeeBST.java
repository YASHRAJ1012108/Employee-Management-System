package employee.management.system;

// Node class for the BST
 class Node {
    Employee employee;
    Node left, right;

    public Node(Employee employee) {
        this.employee = employee;
        this.left = null;
        this.right = null;
    }
}

public class EmployeeBST {

    private Node root;

    public EmployeeBST() {
        this.root = null;
    }

    // Insert a new node using Employee object
    public void insert(Employee employee) {
        root = insertRec(root, employee);
    }

    private Node insertRec(Node root, Employee employee) {
        if (root == null) {
            return new Node(employee);
        }

        if (employee.getEmployeeID() < root.employee.getEmployeeID()) {
            root.left = insertRec(root.left, employee);
        } else if (employee.getEmployeeID() > root.employee.getEmployeeID()) {
            root.right = insertRec(root.right, employee);
        }

        return root;
    }

    // Delete a node by employeeID
    public void delete(int employeeID) {
        root = deleteRec(root, employeeID);
    }

    private Node deleteRec(Node root, int employeeID) {
        if (root == null) {
            return root;
        }

        if (employeeID < root.employee.getEmployeeID()) {
            root.left = deleteRec(root.left, employeeID);
        } else if (employeeID > root.employee.getEmployeeID()) {
            root.right = deleteRec(root.right, employeeID);
        } else {
            // Node to be deleted found

            // Case 1: Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Case 2: Node with two children
            Node temp = minValueNode(root.right);
            root.employee = temp.employee;
            root.right = deleteRec(root.right, temp.employee.getEmployeeID());
        }

        return root;
    }

    private Node minValueNode(Node root) {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Search for a node by employeeID
    public Employee search(int employeeID) {
        return searchRec(root, employeeID);
    }

    private Employee searchRec(Node root, int employeeID) {
        if (root == null || root.employee.getEmployeeID() == employeeID) {
            return (root != null) ? root.employee : null;
        }

        if (employeeID < root.employee.getEmployeeID()) {
            return searchRec(root.left, employeeID);
        }

        return searchRec(root.right, employeeID);
    }

    // In-order traversal for displaying the BST
    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.println(root.employee);
            inorderRec(root.right);
        }
    }
}