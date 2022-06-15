package tuition;

/**
 This class is derived from Student class and contains operations on Resident students.
 It allows calculation of tuition due for students and also manages financial aid of students.
 @author Ria Shah, Christian Bermudez
 */
public class Resident extends Student{

    private double finAid=0;

    /**
     This is the constructor that sets up initial NJ Resident student object.
     */
    public Resident(Profile myProfile) {
        super(myProfile);
    }

    /**
     This method gets the financial aid of the student.
     @return finAid of the student.
     */
    public double getFinAid(){
        return finAid;
    }

    /**
     This method sets the financial aid of the student.
     */
    public void setFinAid(double toSet){
        finAid = toSet;
    }

    /**
     This method overrides the tuitionDue() method in its parent class and calculates tuition of a Resident student.
     */
    @Override
    public void tuitionDue() {
        final int MINIMUM_FULL_TIME_CREDIT = 12;
        final int MAXIMUM_FULL_TIME_CREDIT = 16;
        final double PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE = 0.80;
        final int RESIDENT_COST_PER_CREDIT = 404;
        final int RESIDENT_TUITION = 12536;
        final int RESIDENT_UNIVERSITY_FEE = 3268;
        double cost = 0;
        if(getCreditHours() < MINIMUM_FULL_TIME_CREDIT){
            cost = RESIDENT_COST_PER_CREDIT * getCreditHours() + PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE *
                    RESIDENT_UNIVERSITY_FEE;
        } else {
            cost = RESIDENT_TUITION + RESIDENT_UNIVERSITY_FEE;
            if(getCreditHours() > MAXIMUM_FULL_TIME_CREDIT){
                cost += RESIDENT_COST_PER_CREDIT * (getCreditHours() - MAXIMUM_FULL_TIME_CREDIT);
            }
        }
        cost = cost - getAmtPaid();
        this.setTuitionDue(cost);
    }

    /**
     This method supports printing for the Resident student.
     @return the object in form of a String .
     */
    @Override
    public String toString() {
        String strPayDate;
        if (getPayDate() == null)
            strPayDate = "--/--/--";
        else
            strPayDate = String.valueOf(getPayDate());

        double tempFinAid = getFinAid();
        if (tempFinAid>0) {
            return super.toString() + getCreditHours() + " credit hours:" + "tuition due:" + String.format("%.02f", getTuitionDue()) + ":total payment:"
                    + String.format("%.02f", getAmtPaid()) + ":last payment date:" + strPayDate + ":resident:financial aid $" + getFinAid();
        }
        else {
            return super.toString() + getCreditHours() + " credit hours:" + "tuition due:" + String.format("%.02f", getTuitionDue()) + ":total payment:"
                    + String.format("%.02f", getAmtPaid()) + ":last payment date:" + strPayDate + ":resident";
        }
    }
}