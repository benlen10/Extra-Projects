import javax.swing.JOptionPane;

//This program uses a basic GUI to roughly calculate college fees based on various factors

public class CollegeFeeCalculator {
    public static void main(String[] args) {
        int check = 0;
        while (check == 0) {
            JOptionPane.showMessageDialog (null, "Welcome to CollegeFeeCalculator!",
                                           "CollegeFeeCalculator", JOptionPane.INFORMATION_MESSAGE);
            
            String name = JOptionPane.showInputDialog (null, "Please enter your name, then press OK", "Name", JOptionPane.QUESTION_MESSAGE);
            
            String[] time = {"Full-Time","Part-Time"};
            
            int enrollment = JOptionPane.showOptionDialog(null, "Please select your enrollment" , "Enrollment" , JOptionPane.PLAIN_MESSAGE, JOptionPane.QUESTION_MESSAGE,null,time, null);
            
            String credits = JOptionPane.showInputDialog (null, "Please enter the no. of credit hours, then press OK", "Credit Hours", JOptionPane.QUESTION_MESSAGE);
            int hours = Integer.parseInt(credits);
            if (enrollment == 1) {
                if (8 > hours) {
                    while (hours < 8) {
                        
                        JOptionPane.showMessageDialog (null, "Please enter valid credit hours for the current enrollment", "Invalid no. of credits", JOptionPane.ERROR_MESSAGE);
                        credits = JOptionPane.showInputDialog (null, "Please enter the no. of credit houts, then press OK", JOptionPane.QUESTION_MESSAGE);
                        hours = Integer.parseInt(credits);
                    }
                }
            }
            
            String[] residency = {"In-state", "Out-of-state", "International"};
            
            
            Object res = JOptionPane.showInputDialog(null, "Please select the appropriate residency", "Residency", JOptionPane.QUESTION_MESSAGE, null, residency,null);
            
            String[] housing = {"ON-Campus", "OFF-Campus"};
            
            Object house = JOptionPane.showInputDialog(null, "Please slect your housing", "Housing", JOptionPane.QUESTION_MESSAGE,null, housing, null);
            System.out.println(house.toString());
            
            
            String[] dorms = {"Earhart", "Hillenbrand", "Owen", "Windsor"};
            Object dorm = new Object();
            if (house == "ON-Campus") 
                dorm = JOptionPane.showInputDialog(null, "Please select the residence hall", "Residence-Hall", JOptionPane.QUESTION_MESSAGE,null, dorms, null);
            
            
            int fee = 0;
            int tuition = 0;
            int housingCost = 0;
            
            if (enrollment == 0) {
                if (res == "In-state") {
                    tuition = 4996;
                }
                if (res == "Out-of-state") {
                    tuition = 14397;
                }
                if (res == "International") {
                    tuition =  15397;
                }
            }
            
            if (enrollment == 1) {
                if (res == "In-state") {
                    tuition =  350;
                }
                if (res == "Out-of-state") {
                    tuition = 950;
                }
                if (res == "International") {
                    tuition = 1020;
                }
            }
            
            if (house == "ON-Campus") {
                if (dorm == "Earhart") {
                    housingCost = 4745;
                }
                if (dorm == "Hillenbrand") {
                    housingCost = 5307;
                }
                if (dorm == "Owen") {
                    housingCost = 4130;
                }
                if (dorm == "Windsor") {
                    housingCost = 4150;
                }
            }
            
            fee = (tuition + housingCost);
            
            String enr = "";
            if  (enrollment == 1) {
                enr = "Part-time";
            }
            if  (enrollment == 0) {
                enr = "Full-time";
            }
            
            String message = "Name: " + name + "\n Credit Hours: " + hours + "\n Enrollment: " + enr + "\n Residency: " + res + "\n Tutition fee: " + tuition + "\n Housing Expense: " + housingCost + "\n Total Semester Fee: " + fee;
            JOptionPane.showMessageDialog(null, message ,  "CollegeFeeCalculator", JOptionPane.INFORMATION_MESSAGE);
            
            check = JOptionPane.showConfirmDialog(null, "Would you like to preform another fee calulation?", "Are you done?", JOptionPane.YES_NO_OPTION);

        }
    }
}