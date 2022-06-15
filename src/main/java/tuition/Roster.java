package tuition;

public class Roster {
    private Student[] roster;
    private int size; //keep track of the number of students in the roster
    private final int NOT_FOUND = -1;

    /**
     * This constructor sets a growable array list data structure to an initial capacity of 4.
     */
    public Roster() {
        roster = new Student[4];
        size = 0;
    }

    /**
     * This method searches a student in the roster.
     * @param student the student being managed.
     * @return the index of roster if student is found, -1 otherwise.
     */
    private int find(Student student) {
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) {//if position is null then skip
                continue;
            }
            if (roster[i].getProfile().equals(student.getProfile())) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    /**
     * This method allows the array list data structure with an initial capacity of 4 to automatically
     * increase whenever it is full.
     * The array list does not decrease in capacity.
     */
    private void grow() {
        Student[] studentsToKeep = new Student[roster.length + 4]; //create new array of +4 length
        for (int i = 0; i < roster.length; i++) {
            studentsToKeep[i] = roster[i];
        }
        roster = studentsToKeep; //changes pointer
    } //increase the capacity of the array list by 4

    /**
     * This method adds a student to the roster.
     * @param student the student being managed.
     * @return true if student is added, false otherwise.
     */
    public boolean add(Student student) {
        if (find(student) != NOT_FOUND) {
            return false;
        }
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) {//empty position
                roster[i] = student;//save at that position
                return true;
            }
        }
        grow();
        return add(student);
    }

    /**
     * This method deletes a student from the roster.
     * @param student the student being managed.
     * @return true if student is removed, false otherwise.
     */
    public boolean remove(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return false;
        }
        roster[index] = null;
        for (int i = index; i < roster.length-1; i++) {
            if (roster[i+1] != null) {
                roster[i] = roster[i+1];
                roster[i+1] = null;
            }
        }
        return true;
    }

    /**
     * This method displays the roster without specifying the order.
     */
    public String printRoster() {
        int numberStudents = 0;
        //String RosterString = "* list of students in the roster **\n";
        String RosterString = "";
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) {//if position is null then skip
                continue;
            }
            RosterString += roster[i].toString() + "\n";
            numberStudents++;
        }
        RosterString += "* end of roster **";
        if (numberStudents == 0)
            return "Student roster is empty!";
        else
            return RosterString;
    }

    /**
     * This method displays the roster without specifying the order.
     */
    private String printRosterByPayment() {
        int numberStudents = 0;
        //String RosterString = "* list of students in the roster **\n";
        String RosterString = "";
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] == null) {//if position is null then skip
                continue;
            }
            if (roster[i].getPayDate() == null) {//if position is null then skip
                continue;
            }
            RosterString += roster[i].toString() + "\n";
            numberStudents++;
        }
        RosterString += "* end of roster **";
        if (numberStudents == 0)
            return "Student roster is empty!";
        else
            return RosterString;
    }


    /**
     * This method displays the roster sorted by student names.
     */
    public String sortByStudentNames() {
        //int numberStudents = 0;
        String RosterString = "* list of students in the roster **\n";
        int n = roster.length;
        for (int i = 0; i < n; i++) {
            if ((roster[i] == null)) {
                continue;
            }
            for (int j = i + 1; j < n; j++) {
                if ((roster[j] == null)) {
                    continue;
                }
                if ((roster[i] == null)) {
                    break;
                }

                if (name1GTname2(i, j)) {
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp;
                }
                RosterString += roster[i].toString() + "\n";
                //numberStudents++;
            }
        }
        return printRoster();
    }

    /**
     This method displays the roster sorted by the dates students made payments.
     */
    public String sortByPayment() {
        int n = roster.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((roster[j] == null)) {
                    continue;
                }
                if ((roster[i] == null)) {
                    break;
                }

                if(roster[i].getPayDate() == null){
                    continue;
                }
                if(roster[j].getPayDate() == null){
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp; //swap
                    continue;
                }


                if (roster[i].getPayDate().compareTo(roster[j].getPayDate()) < 0) {
                    Student temp = roster[i];
                    roster[i] = roster[j];
                    roster[j] = temp; //swap
                }
            }
        }
        return printRosterByPayment();
    }

    /**
     This method compares the names of two students and picks the greater alphabetical letter.
     @param index1 the first index.
     @param index2 the second index.
     @return true if the name1 is greater than name2, false otherwise.
     */
    private boolean name1GTname2(int index1, int index2) {
        String name1 = roster[index1].getProfile().getName();
        String name2 = roster[index2].getProfile().getName();

        int result = name1.substring(0, 1).toLowerCase().compareTo(name2.substring(0, 1).toLowerCase());
        if (result > 0) {
            return true;
        } else if (result < 0) {
            return false;
        } else if (result == 0) {
            int i = 1;
            while (result == 0) {
                if (i + 1 > name1.length()) {
                    return true;
                }
                if (i + 1 > name2.length()) {
                    return false;
                }
                result = name1.substring(i, i + 1).toLowerCase().compareTo(name2.substring(i, i + 1).toLowerCase());
                i++;
            }
            if (result > 0) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     This method calculates tuition dues for all students in the roster.
     @return true if tuition is paid, false otherwise.
     */
    public boolean calcTuition() {
        // if (roster[i] instanceof Resident)
        int studentCnt = 0;
        for (int i = 0; i < roster.length; i++) {
            if (roster[i] != null) {
                roster[i].tuitionDue();
                studentCnt++;
            }
        }
        if (studentCnt > 0)
            return true;
        else
            return false;
    }

    public double calcStudentTuition(Student student) {
        int index = find(student);
        if (index == NOT_FOUND) {
            return 0;
        }
        //roster[index] = null;
        roster[index].tuitionDue();
        return roster[index].getTuitionDue();
    }

    /**
     This method allows a student to pay tuition.
     @param student the student.
     @return a message that the payment was applied, an error message otherwise.
     */
    public String payTuition(Student student) {
        int index = find(student);
        if (index == NOT_FOUND)
            return "Student is not in the roster.";
        double amtPaid = student.getAmtPaid();
        Date payDate = student.getPayDate();
        double tuitionDue = roster[index].getTuitionDue();
        if(tuitionDue < amtPaid){
            return "Amount is greater than amount due.";
        }
        double newTuitionDue = tuitionDue - amtPaid;
        double alreadyPaidAmt = roster[index].getAmtPaid();
        double newAmtPaid = alreadyPaidAmt + amtPaid;

        if (roster[index].getTuitionDue() >= amtPaid) {
            roster[index].setAmtPaid(newAmtPaid);
            roster[index].setTuitionDue(newTuitionDue);
            roster[index].setPayDate(payDate);
            return "Payment applied.";
        }
        else
            return "Cannot overpay Tuition.";
    }

    /**
     This method sets the study abroad status for an international student.
     @param student the student.
     @return a message that the international status is set.
     */
    public String setStudyAbroad (Student student){
        final int CREDIT_HOURS = 12;
        final double AMT_PAID = 0.0;
        int index = find(student);
        if (index == NOT_FOUND)
            return "Couldn't find the international student.";
            //return false;
        if (!(roster[index] instanceof International))
            return "Not an international student.";

        International myInternational = (International) student;
        boolean status = myInternational.getStatus();
        International currentInternational = (International) roster[index];
        currentInternational.setStatus(status);
        if (roster[index].getCreditHours() > CREDIT_HOURS)
            currentInternational.setCreditHours(CREDIT_HOURS);
        currentInternational.setAmtPaid(AMT_PAID);
        currentInternational.setPayDate(null);
        roster[index] = currentInternational;
        calcTuition();
        return "Tuition updated.";
    }

    /**
     This method sets the financial aid amount for a resident student.
     @param student the student.
     @return a message that the financial aid is awarded.
     */
    public String setFinAid (Student student) {
        int index = find(student);
        if (index == NOT_FOUND)
            return "Student is not in the roster.";
        if (!(roster[index] instanceof Resident))
            return "Not a resident student.";
        Resident myResident = (Resident) student;
        double finAid = myResident.getFinAid();
        double currentFinAid = (((Resident) roster[index]).getFinAid());
        if (currentFinAid > 0)
            return "Awarded once already.";
        if(roster[index].getStudentType().equalsIgnoreCase("PT"))
            return "Parttime student doesn't qualify for the award.";
        Resident currentResident = (Resident) roster[index];
        currentResident.setFinAid(finAid);
        roster[index] = currentResident;
        return "Financial Aid awarded.";
    }
}