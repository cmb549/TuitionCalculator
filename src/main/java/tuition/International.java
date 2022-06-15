package tuition;

/**
 This class is derived from NonResident class and contains operations on International students.
 It allows calculation of tuition due for students and also manages study abroad status.
 @author Ria Shah, Christian Bermudez
 */

public class International extends NonResident {
    /**
     This is the constructor that sets up initial International student object.
     @param myProfile profile of the student, which contains name and major.
     */
    public International(Profile myProfile) {
        super(myProfile);
    }

    boolean isStudyAbroad;
    /**
     This method gets the study abroad status of a student.
     @return the status of a student; true if study abroad, false otherwise
     */
    public boolean getStatus(){
        return isStudyAbroad;
    }

    /**
     This method sets the study abroad status of a student.
     */
    @Override
    public void setStatus(boolean status){
        isStudyAbroad = status;
    }

    /**
     This method overrides the tuitionDue() method in its parent class and calculates tuition of an international student.
     */
    @Override
    public void tuitionDue() {
        final int MAXIMUM_FULL_TIME_CREDIT = 16;
        final int COST_PER_CREDIT = 966;
        final int FULL_TIME_STUDENT_UNIVERSITY_FEE = 3268;
        final int NONRESIDENT_TUITION = 29737;
        final int ADDITIONAL_FEE = 2650;
        double cost = 0;
        if(isStudyAbroad){ // study abroad ==> max = 12 hours
            cost = FULL_TIME_STUDENT_UNIVERSITY_FEE + ADDITIONAL_FEE;
        } else { //normal international
            cost = NONRESIDENT_TUITION + FULL_TIME_STUDENT_UNIVERSITY_FEE + ADDITIONAL_FEE; //nonresident fees and tuition
            if(getCreditHours() > MAXIMUM_FULL_TIME_CREDIT){
                cost += COST_PER_CREDIT * (getCreditHours() - MAXIMUM_FULL_TIME_CREDIT); //additional fee for over 16 hours
            }
        }
        cost = cost - getAmtPaid();
        this.setTuitionDue(cost);
    }

    /**
     This method supports printing for the international student.
     @return the object in form of a String .
     */
    @Override
    public String toString() {
        if (getStatus())
            return super.toString() + ":international:" + "study abroad";
        else
            return super.toString() + ":international";
    }
}