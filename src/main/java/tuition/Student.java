package tuition;

public class Student {
    //private Profile sProfile = new Profile();
    private int creditHours;
    private double tuition;
    private Date payDate;
    private double amtPaid;
    private Profile profile;
    private String studentType = "";
    private final int MINIMUM_FULL_TIME_CREDIT = 12;

    /**
     This constructor initializes an object of the Student class.
     @param myProfile the student profile.
     */
    public Student(Profile myProfile) {
        final int INIT_CREDIT_HOURS = 0;
        final double INIT_AMOUNT = 0.00;
        this.setProfile(myProfile);
        this.setCreditHours(INIT_CREDIT_HOURS);
        this.setTuitionDue(INIT_AMOUNT);
        this.setAmtPaid(INIT_AMOUNT);
    }
    /**
     This method gets the profile of a student.
     @return the profile of a student.
     */
    public Profile getProfile() {
        return profile;
    }

    /**
     This method sets the profile of a student.
     @param profile the profile of a student.
     */
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    /**
     This method gets the credit hours of a student.
     @return the credit hours of a student.
     */
    public int getCreditHours() {
        return creditHours;
    }

    /**
     This method sets the credit hours of a student.
     @param creditHours the credit hours of a student.
     */
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
        setStudentType(creditHours);
    }

    /**
     This method gets the type of student.
     @return the type of student.
     */
    public String getStudentType() {
        return studentType;
    }

    /**
     This method sets the type of student.
     @param creditHours the credit hours of a student.
     */
    public void setStudentType(int creditHours) {
        if (creditHours >= MINIMUM_FULL_TIME_CREDIT)
            this.studentType = "FT";
        else
            this.studentType = "PT";
    }
    /**
     This method gets the student tuition.
     @return the student tuition.
     */
    public double getTuitionDue() {
        return tuition;
    }

    /**
     This method sets the student tuition.
     @param tuition the student tuition.
     */
    public void setTuitionDue(double tuition) {
        this.tuition = tuition;
    }

    /**
     This method gets the payment date of a student tuition.
     @return the payment date of a student tuition.
     */
    public Date getPayDate() {
        return payDate;
    }

    /**
     This method sets the payment date of a student tuition.
     @param payDate the payment date of a student tuition.
     */
    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    /**
     This method gets the amount paid for a student tuition.
     @return the amount paid for a student tuition.
     */
    public double getAmtPaid() {
        return amtPaid;
    }

    /**
     This method sets the amount paid for a student tuition.
     @param amtPaid the amount paid for a student tuition.
     */
    public void setAmtPaid(double amtPaid) {
        this.amtPaid = amtPaid;
    }

    /**
     This is a do-nothing method which calculates the tuition due and is overridden by all the subclasses.
     */
    public void tuitionDue() {
    }

    /**
     This method creates a textual representation of a student with details.
     @return the textual representation of a student.
     */
    @Override
    public String toString() {
        return profile.toString();
    }

    public void setStatus(boolean status) {
    }

    public void setOrigin(String state) {
    }
}