package tuition;

/**
 This class defines the profile of a student, including name and major.
 A student profile uniquely identifies a student in the student roster.
 @author Ria Shah, Christian Bermudez
 */
public class Profile {
    private String name;
    private Major major; //5 majors and 2-character each: CS, IT, BA, EE, ME

    /**
     This constructor initializes the fields of the profile.
     @param name the name of the student.
     @param major the major of the student.
     */
    public Profile(String name, Major major) {
        this.name = name;
        this.major = major;
    }

    /**
     This constructor allows the creation of an empty profile.
     */
    public Profile() {
    }

    /**
     This method gets the name of a student.
     @return the name of a student.
     */
    public String getName() {
        return name;
    }

    /**
     This method sets the name of a student.
     @param name the name of a student.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     This method gets the major of a student.
     @return the major of an student.
     */
    public Major getMajor() {
        return major;
    }

    /**
     This method sets the major of a student.
     @param major the major of a student.
     */
    public void setMajor(Major major) {
        this.major = major;
    }

    /**
     This method checks if two student profiles are the same by comparing the name and major of both.
     @param obj the object as an instance of Profile.
     @return true if the name and major of two profiles are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
       Profile newProfile = (Profile) obj;
        if (this.getName().equals(newProfile.getName()) && this.getMajor().equals(newProfile.getMajor()))
            return true;
        else
            return false;
    }

    /**
     This method creates a textual representation of a Profile with details.
     @return the textual representation of a profile.
     */
    @Override
    public String toString() {
        String retString = name + ":" + major + ":";
        return retString;
    }
}