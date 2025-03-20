package employee.management.system;

import java.util.Date;

public class LeaveRequestNode {
    int requestID;
    int employeeID;
    String leaveType;
    Date startDate;
    Date endDate;
    String status;      //"Pending", "Approved", "Rejected"
    LeaveRequestNode next;

    public LeaveRequestNode(int requestID, int employeeID, String leaveType, Date startDate,Date endDate, String status) {
        this.requestID = requestID;
        this.employeeID = employeeID;
        this.leaveType = leaveType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }


    public int getRequestID() {
        return requestID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public Date getStartDate() {
         return startDate; }
   

    public Date getEndDate() {
         return endDate; }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "LeaveRequestNode [requestID=" + requestID + ", employeeID=" + employeeID + ", leaveType=" + leaveType
                + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status + "]";
    }

    
}
