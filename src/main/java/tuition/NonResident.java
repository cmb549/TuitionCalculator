package tuition;

/**
 This class is derived from Student class and contains operations on NonResident students.
 It allows calculation of tuition due for students.
 @author Ria Shah, Christian Bermudez
 */
public class NonResident extends Student {

    /**
     This is the constructor that sets up initial NonResident student object.
     */
    public NonResident(Profile myProfile) {
        super(myProfile);
    }

    /**
     This method overrides the tuitionDue() method in its parent class and calculates tuition of a NonResident student.
     */
    @Override
    public void tuitionDue() {
        final int MINIMUM_FULL_TIME_CREDIT = 12;
        final int MAXIMUM_FULL_TIME_CREDIT = 16;
        final int COST_PER_CREDIT = 966;
        final double PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE = 0.80;
        final int FULL_TIME_STUDENT_UNIVERSITY_FEE = 3268;
        final int NONRESIDENT_TUITION = 29737;
        double cost = 0;
        if(getCreditHours() < MINIMUM_FULL_TIME_CREDIT){
            cost = COST_PER_CREDIT * getCreditHours() + PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE *
                    FULL_TIME_STUDENT_UNIVERSITY_FEE;
        } else {
            cost = NONRESIDENT_TUITION + FULL_TIME_STUDENT_UNIVERSITY_FEE;
            if(getCreditHours() > MAXIMUM_FULL_TIME_CREDIT){
                cost += COST_PER_CREDIT * (getCreditHours() - MAXIMUM_FULL_TIME_CREDIT);
            }
        }
        cost = cost - getAmtPaid();
        this.setTuitionDue(cost);
    }

    /**
     This method supports printing for the NonResident student.
     @return the object in form of a String .
     */
    @Override
    public String toString() {
        String strPayDate;
        if (getPayDate() == null)
            strPayDate = "--/--/--";
        else
            strPayDate = String.valueOf(getPayDate());
        return super.toString() + getCreditHours() + " credit hours:" + "tuition due:" + String.format("%.02f",getTuitionDue()) + ":total payment:"
                + String.format("%.02f",getAmtPaid()) + ":last payment date:" + strPayDate + ":non-resident";
    }
}