package tuition;

/**
 This class is derived from NonResident class and contains operations on TriState students.
 It allows calculation of tuition due for students and also manages origin of student.
 @author Ria Shah, Christian Bermudez
 */

public class TriState extends NonResident {
    private State origin;

    /**
     This is the constructor that sets up initial TriState student object.
     */
    public TriState(Profile myProfile) {
        super(myProfile);
    }

    /**
     This method sets the origin of the student.
     @param state the state the student lives in.
     */
    @Override
    public void setOrigin(String state){
        if(state.equalsIgnoreCase("NY")){
            origin = State.NY;
        } else if (state.equalsIgnoreCase("CT")){
            origin = State.CT;
        } else if (state.equalsIgnoreCase("NJ")){
            origin = State.NJ;
        }
    }

    /**
     This method gets the origin of the student.
     @return origin of the student.
     */
    public State getOrigin(){
        return origin;
    }

    /**
     This method overrides the tuitionDue() method in its parent class and calculates tuition of an TriState student.
     */
    @Override
    public void tuitionDue() {
        final int MINIMUM_FULL_TIME_CREDIT = 12;
        final int MAXIMUM_FULL_TIME_CREDIT = 16;
        final int COST_PER_CREDIT = 966;
        final double PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE = 0.80;
        final int FULL_TIME_STUDENT_UNIVERSITY_FEE = 3268;
        final int NONRESIDENT_TUITION = 29737;
        final int NY_DISCOUNT = 4000;
        final int CT_DISCOUNT = 5000;
        double cost = 0;
        if(getCreditHours() < MINIMUM_FULL_TIME_CREDIT){
            cost = COST_PER_CREDIT * getCreditHours() + PART_TIME_STUDENT_EIGHTY_PERCENT_UNIVERSITY_FEE *
                    FULL_TIME_STUDENT_UNIVERSITY_FEE;
        } else {
            cost = NONRESIDENT_TUITION + FULL_TIME_STUDENT_UNIVERSITY_FEE;
            if (getCreditHours() > MAXIMUM_FULL_TIME_CREDIT) {
                cost += COST_PER_CREDIT * (getCreditHours() - MAXIMUM_FULL_TIME_CREDIT);
            }
            if (origin == State.NY) {
                cost -= NY_DISCOUNT;
            } else if (origin == State.CT) {
                cost -= CT_DISCOUNT;
            }
        }
        if(cost < 0) {
            cost = 0;
        }
        cost = cost - getAmtPaid();
        this.setTuitionDue(cost);
    }

    /**
     This method supports printing for the TriState student.
     @return the object in form of a String .
     */
    @Override
    public String toString() {
        return super.toString() + "(tri-state):" + origin.toString();
    }
}