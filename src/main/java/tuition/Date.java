package tuition;

import java.util.Calendar;
import java.util.StringTokenizer;

/**
 This class defines the Date abstract data type with year, month, and day.
 @author Ria Shah, Christian Bermudez
 */

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * This is a parameterized constructor that takes a string in a form of "mm/dd/yyyy" and creates a Date object.
     * @param date the date of the payment.
     */
    public Date(String date) {
        StringTokenizer st = new StringTokenizer(date,"-");
        year = Integer.parseInt(st.nextToken());
        month = Integer.parseInt(st.nextToken());
        day = Integer.parseInt(st.nextToken());
    }

    /**
     This constructor creates an object with today’s date.
     */
    public Date() {
        Calendar cal = Calendar.getInstance();
        month = cal.get(Calendar.MONTH) + 1;
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);
    }

    /**
     This method gets the day of the Date object.
     @return day of the Date object.
     */
    public int getDay() {
        return day;
    }

    /**
     This method gets the month of the Date object.
     @return month of the Date object.
     */
    public int getMonth() {
        return month;
    }

    /**
     This method gets the year of the Date object.
     @return year of the Date object.
     */
    public int getYear() {
        return year;
    }

    /**
     This method overrides the compareTo method and compares dates of 2 albums.
     @param date the date of the payment.
     @return 1 if passed in date is greater than date of the current object, else return 0.
     */
    @Override
    public int compareTo(Date date) {
        final int INVALID = -1;
        if (date.getYear() > this.year) {
            return 1;
        }
        if (date.getYear() < this.year) {
            return INVALID;
        }
        if (date.getYear() == this.year) {
            if (date.getMonth() > this.month) {
                return 1;
            }
            if (date.getMonth() < this.month) {
                return INVALID;
            }
            if (date.getMonth() == this.month) {
                if (date.getDay() > this.day) {
                    return 1;
                }
                if (date.getDay() < this.day) {
                    return INVALID;
                }
                if (date.getDay() == this.day) {
                    return 0;
                }
            }
        }
        throw new IllegalArgumentException("Non comparable objects");
    }

    /**
     This method returns today's date.
     @return today's date.
     */
    public static Date today() {
        return new Date();
    }

    /**
     This method checks if the month of the current Date object is valid.
     @param monthToCheck the month of the Date object.
     @return true if month is valid, false otherwise.
     */
    private boolean isValidMonth(int monthToCheck) {
        final int CANNOT_BE_A_THIRTEEN_MONTHS = 13;
        return monthToCheck > 0 && monthToCheck <  CANNOT_BE_A_THIRTEEN_MONTHS;
    }

    /**
     This method checks if a year is a leap year.
     @param yearToCheck the year of the Date object.
     @return true if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear(int yearToCheck) {
        final int QUADRENNIAL = 4;
        final int CENTENNIAL = 100;
        final int QUATERCENTENNIAL = 400;
        if (yearToCheck % QUADRENNIAL == 0) {
            if (yearToCheck % CENTENNIAL == 0) {
                if (year % QUATERCENTENNIAL == 0) {
                    return true; //divisible by 400
                }
                return false; //divisible by 100
            }
            return true; // divisible by 4
        }
        return false; // not divisible by 4
    }

    /**
     This method checks if the day of the Date object is valid.
     @param dayToCheck the day of the Date object.
     @return true if the day is valid, false otherwise.
     */
    private boolean isValidDay(int dayToCheck) {
        final int JANUARY = 1;
        final int FEBRUARY = 2;
        final int MARCH = 3;
        final int APRIL = 4;
        final int MAY = 5;
        final int JUNE = 6;
        final int JULY = 7;
        final int AUGUST = 8;
        final int SEPTEMBER = 9;
        final int OCTOBER = 10;
        final int NOVEMBER = 11;
        final int DECEMBER = 12;
        final int MONTHS_WITH_THIRTY_ONE_DAYS = 31;
        final int NO_MONTHS_HAVE_THIRTY_TWO_DAYS = 32;
        final int FEBRUARY_LEAP_YEAR = 29;
        if (!isValidMonth(month)) {
            return false;
        }
        if (month == JANUARY || month == MARCH || month == MAY || month == JULY || month == SEPTEMBER || month == NOVEMBER || month == DECEMBER) {
            return dayToCheck > 0 && dayToCheck < NO_MONTHS_HAVE_THIRTY_TWO_DAYS;
        }//months with 31 days

        if (month == APRIL || month == JUNE || month == AUGUST || month == OCTOBER) {
            return dayToCheck > 0 && dayToCheck < MONTHS_WITH_THIRTY_ONE_DAYS;
        }//months with 30 days

        if (month == FEBRUARY) { // if february
            if (dayToCheck > 0 && dayToCheck < FEBRUARY_LEAP_YEAR) {
                return true;
            } else if (isLeapYear(year)) {
                return dayToCheck == FEBRUARY_LEAP_YEAR;
            }
            return false;
        }
        throw new IllegalArgumentException("Somehow the month was wrong");
    }

    /**
     This method checks if a date is valid.
     @return true if the date is valid, false otherwise.
     */
    public boolean isValid() {
        final int YEARS_OF_TUITION_PAYMENT = 2021;
        Calendar cal = Calendar.getInstance();
        if (year > cal.get(Calendar.YEAR)) { //If a future year
            return false;
        }
        if (year == cal.get(Calendar.YEAR)) { // if a future month
            if (month > cal.get(Calendar.MONTH)+1) {
                return false;
            } else if (month == cal.get(Calendar.MONTH)+1) {
                if (day > cal.get(Calendar.DAY_OF_MONTH)) { // if a future day
                    return false;
                }
            }
        }
        return isValidDay(day) && isValidMonth(month) && year >= YEARS_OF_TUITION_PAYMENT;
    }

    /**
     This method creates the textual representation of a Date object in the form of "mm/dd/yyyy".
     @return the textual representation of a Date object in the form of "mm/dd/yyyy".
     */
    public String toString() {
        return Integer.toString(month)+ "/" + Integer.toString(day) + "/" + Integer.toString(year);
    }

    /**
     This method checks if two dates are the same.
     @param obj the object as an instance of Date.
     @return true if the 2 dates are equal, false otherwise.
     */
    @Override
    public boolean equals (Object obj) {
        if (obj instanceof Date) {
            return this.compareTo((Date) obj) == 0;
        }
        else {
            return false;
        }
    }

    /**
     Testbed main to perform various validations on the date.
     @param args an array of command-line arguments.
     */
    public static void main (String[]args) {
        //test case #1, a date with the year before 2021 should be invalid.
        Date date = new Date("11/1/1979");
        boolean result = date.isValid();
        System.out.print("Test case #1: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #2, a date with a valid day but invalid year.
        date = new Date("02/29/2022");
        result = date.isValid();
        System.out.print("Test case #2: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #3, a date with a valid day and year.
        date = new Date("03/26/2021");
        result = date.isValid();
        System.out.print("Test case #3: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #4, a date with a valid day and year.
        date = new Date("07/13/2021");
        result = date.isValid();
        System.out.print("Test case #4: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #5, a date with an invalid month.
        date = new Date("13/1/2021");
        result = date.isValid();
        System.out.print("Test case #5: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #6, a date with an invalid day in the month.
        date = new Date("12/32/2021");
        result = date.isValid();
        System.out.print("Test case #6: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #7, a date with an invalid month.
        date = new Date("00/20/2021");
        result = date.isValid();
        System.out.print("Test case #7: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #8, a date with an invalid day in a valid month.
        date = new Date("06/31/2021");
        result = date.isValid();
        System.out.print("Test case #8: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");

        //test case #9, a date with an invalid year.
        date = new Date("06/28/2025");
        result = date.isValid();
        System.out.print("Test case #9: ");
        if (result)
            System.out.println("Pass.");
        else
            System.out.println("Fail.");
    }
}