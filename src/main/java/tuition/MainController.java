package tuition;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainController {
    Roster myRoster = new Roster();

    @FXML
    private Label welcomeText;

    //@FXML
    //protected void onHelloButtonClick() {
    //welcomeText.setText("Welcome to JavaFX Application!");
    //}

    @FXML
    private RadioButton add;

    @FXML
    private RadioButton remove;

    @FXML
    private ToggleGroup major;

    @FXML
    private ToggleGroup residentToggle;

    @FXML
    private RadioButton tristate;

    @FXML
    private RadioButton ny;

    @FXML
    private RadioButton ct;

    @FXML
    private ToggleGroup tristateToggle;

    @FXML
    private RadioButton international;

    @FXML
    private CheckBox studyAbroad;

    @FXML
    private RadioButton resident;

    @FXML
    private RadioButton nonresident;

    @FXML
    private TextField name;

    @FXML
    private TextField creditHours;

    @FXML
    private Button submit;

    @FXML
    private TextArea output;

    @FXML
    private RadioButton singleStudentTuition;

    @FXML
    private TextField tuitionOutput;

    @FXML
    private MenuItem about;

    @FXML
    private TextField pAmount;

    @FXML
    private DatePicker pDate;

    @FXML
    private TextField pFinAid;

    @FXML
    private ToggleGroup pMajor;

    @FXML
    private TextField pName;

    @FXML
    private Button pay;

    @FXML
    private Button set;

    @FXML
    private ToggleGroup opToggle;

    /**
     * This method initializes
     */
    @FXML
    public void initialize() {
        name.setText(null);
        resident.setSelected(false);
        nonresident.setSelected(false);
        international.setSelected(false);
        studyAbroad.setSelected(false);
        creditHours.setText(null);
        tristate.setSelected(false);
        ny.setSelected(false);
        ct.setSelected(false);
        name.setDisable(true);
        major.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(true);
        });
        if (major.getSelectedToggle()!=null)
            major.getSelectedToggle().setSelected(false);
        tristate.setDisable(true);
        ny.setDisable(true);
        ct.setDisable(true);
        international.setDisable(true);
        studyAbroad.setDisable(true);
        resident.setDisable(true);
        nonresident.setDisable(true);
        creditHours.setDisable(true);
        submit.setDisable(true);
        add.setDisable(false);
        remove.setDisable(false);
    }

    //yea
    @FXML
    public void initialize1() {
        pName.setText(null);
        pMajor.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(false);
        });
        if (pMajor.getSelectedToggle()!=null)
            pMajor.getSelectedToggle().setSelected(false);
        pAmount.setText(null);
        String a = null;
        //pDate.set(null);
        pFinAid.setText(null);
    }

    @FXML
    void disableNonRes(ActionEvent event) {
        tristate.setDisable(true);
        tristate.setSelected(false);
        tristateToggle.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(true);
        });
        tristateToggle.selectToggle(null);
        international.setDisable(true);
        international.setSelected(false);
        studyAbroad.setDisable(true);
        studyAbroad.setSelected(false);
    }

    @FXML
    void enableNonRes(ActionEvent event) {;
        tristate.setDisable(false);
        tristateToggle.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle ;
            disable.setDisable(false);
        });
        international.setDisable(false);
        studyAbroad.setDisable(true);
        ny.setDisable(true);
        ct.setDisable(true);
    }

    @FXML
    void checkTriStateSelect(ActionEvent event) {
        if (!tristate.isSelected()) {
            tristateToggle.selectToggle(null);
            ny.setSelected(false);
            ct.setSelected(false);
            ny.setDisable(true);
            ct.setDisable(true);
        }
        else {
            ny.setDisable(false);
            ct.setDisable(false);
            international.setSelected(false);
            studyAbroad.setDisable(true);
            studyAbroad.setSelected(false);
        }
    }

    @FXML
    void checkStudyAbroadSelect(ActionEvent event) {
        if (international.isDisabled())
            studyAbroad.setDisable(true);
        if (!international.isSelected()) {
            studyAbroad.setSelected(false);
            studyAbroad.setDisable(true);
        }
        else {
            studyAbroad.setDisable(false);
            tristate.setSelected(false);
            ny.setDisable(true);
            ct.setDisable(true);
            ny.setSelected(false);
            ct.setSelected(false);
        }
    }

    /**
     This method removes a student from the roster.
     */
    @FXML
    private void removeStudentButton () {
        initialize();
        tuitionOutput.setText(null);
        name.setDisable(false);
        major.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(false);
        });
        resident.setDisable(true);
        nonresident.setDisable(true);
        creditHours.setDisable(true);
        submit.setDisable(false);
    }

    @FXML
    void addStudentButton(ActionEvent event) {
        initialize();
        tuitionOutput.setText(null);
        name.setDisable(false);
        major.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(false);
        });
        resident.setDisable(false);
        nonresident.setDisable(false);
        creditHours.setDisable(false);
        submit.setDisable(false);
        //add.setDisable(false);
        //remove.setDisable(true);
    }

    @FXML
    void tuitionDueButton(ActionEvent event) {
        initialize();
        tuitionOutput.setText(null);
        name.setDisable(false);
        major.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle;
            disable.setDisable(false);
        });
        resident.setDisable(true);
        nonresident.setDisable(true);
        creditHours.setDisable(true);
        submit.setDisable(false);
    }

    @FXML
    void submitButton(ActionEvent event) {
        if (remove.isSelected()) {
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else
                removeStudent();
        }
        else if (add.isSelected()){
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else if ((RadioButton) residentToggle.getSelectedToggle() == null)
                output.setText("Enter status information.");
            else {
                if (resident.isSelected()) {
                    output.setText(addResident());
                } else if (tristate.isSelected()) {
                    if (!(ny.isSelected() || ct.isSelected())) {
                        output.setText("Select state.");
                        return;
                    }
                    output.setText(addTriState());
                } else if (international.isSelected()) {
                    output.setText(addInternational());
                } else {
                    output.setText(addNonResident());
                }
            }
        }
        else {
            tuitionOutput.setText(null);
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else
                calcStudentTuition();
        }
        opToggle.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle ;
            disable.setDisable(false);
        });
    }

    /**
     * This method displays the roster without specifying the order.
     */
    @FXML
    private void printRoster () {
        //output.setText("\n* list of students in the roster **\n" + myRoster.printRoster());
        String outputMessage = myRoster.printRoster();
        if (!outputMessage.equals("Student roster is empty!"))
            output.setText("* list of students in the roster **\n" + outputMessage);
        else
            output.setText(outputMessage);
    }

    /**
     * This method displays the roster sorted by student names.
     */
    @FXML
    private void printByName () {
        String outputMessage = myRoster.sortByStudentNames();
        if (!outputMessage.equals("Student roster is empty!"))
            output.setText("* list of students ordered by name **\n" + outputMessage);
        else
            output.setText(outputMessage);
    }

    /**
     This method displays the roster sorted by the dates students made payments.
     */
    @FXML
    private void printByPayDate () {
        String outputMessage = myRoster.sortByPayment();
        if (!outputMessage.equals("Student roster is empty!"))
            output.setText("* list of students made payments ordered by payment date **\n" + outputMessage);
        else
            output.setText(outputMessage);
    }

    /**
     This method calculates tuition dues for a singular student in the roster.
     */
    @FXML
    private void calcTuition () {
        myRoster.calcTuition();
        if (myRoster.calcTuition())
            output.setText("Calculation completed.");
        else
            output.setText("Student roster is empty!");
    }

    /**
     This method calculates tuition dues for all students in the roster.
     */
    @FXML
    private void calcStudentTuition () {
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();
        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            output.setText("'" + maj + "' is not a valid major.");
            return;
        }
        Major major = Major.valueOf(maj);
        Profile myProfile = new Profile(name1, major);
        Student myStudent = new Student(myProfile);
        double studentTuition = myRoster.calcStudentTuition(myStudent);
        if (studentTuition!=0) {
            output.setText("Student tuition calculated.");
            tuitionOutput.setText(String.valueOf(studentTuition));
        }
        else
            output.setText("Student is not in the roster.");
    }

    /**
     This method does a payment for a students in the roster.
     */
    @FXML
    private void pSetFinAid (ActionEvent event) {
        if ((pName.getText()==null) || (pName.getText()=="")){
            output.setText("Enter a valid name.");
            return;
        }
        else if ((RadioButton) pMajor.getSelectedToggle()==null) {
            output.setText("Enter a valid major.");
            return;
        }
        else if (pFinAid.getText()==""){
            output.setText("Enter valid Financial Aid Amount.");
            return;
        }
        String name1 = pName.getText();
        RadioButton selectedRadioButton = (RadioButton) pMajor.getSelectedToggle();
        String maj = selectedRadioButton.getText();
        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){

            maj = maj.toLowerCase();
            output.setText("'" + maj + "' is not a valid major.");
            return;
        }

        Major major = Major.valueOf(maj);
        Profile myProfile = new Profile(name1, major);
        Resident myResident = new Resident(myProfile);
        myResident.setProfile(myProfile);
        myResident.setFinAid(Double.parseDouble(String.valueOf(pFinAid.getText())));
        String retString = myRoster.setFinAid(myResident);
        output.setText(retString);
        initialize1();
    }

    /**
     This method sets Financial Aid for a students in the roster.
     */
    @FXML
    private void pSetPayment (ActionEvent event) {
        if ((pName.getText()==null) || (pName.getText()=="")){
            output.setText("Enter a valid name.");
            return;
        }
        else if ((RadioButton) pMajor.getSelectedToggle()==null) {
            output.setText("Enter a valid major.");
            return;
        }
        else {

            String name1 = pName.getText();
            RadioButton selectedRadioButton = (RadioButton) pMajor.getSelectedToggle();
            String maj = selectedRadioButton.getText();
            if (!maj.matches("(?i)CS|IT|EE|ME|BA")) {
                maj = maj.toLowerCase();
                output.setText("'" + maj + "' is not a valid major.");
                return;
            }
            Major major = Major.valueOf(maj);
            String paymentAmount = pAmount.getText();
            if ((paymentAmount == "") || (paymentAmount == null)){
                output.setText("Payment amount missing.");
                return;
            }
            double amtPaid = Double.parseDouble(String.valueOf(paymentAmount));
            if (amtPaid <= 0) {
                //System.out.println("Missing payment date.");
                output.setText("Invalid amount.");
                return;
            }
            if(pDate.getValue() == null) {
                output.setText("Payment date invalid.");
                return;
            }

            String payDate = pDate.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            if (!new Date(payDate).isValid()) {
                output.setText("Payment date invalid.");
                return;
            }
            Profile myProfile = new Profile(name1, major);
            Student myStudent = new Student(myProfile);
            myStudent.setProfile(myProfile);
            myStudent.setAmtPaid(amtPaid);
            myStudent.setPayDate(new Date(payDate));
            String retString = myRoster.payTuition(myStudent);
            output.setText(retString);

            pDate.setValue(null);
        }
        initialize1();
    }

    /*
            if (remove.isSelected()) {
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else
                removeStudent();
        }
        else if (add.isSelected()){
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else if ((RadioButton) residentToggle.getSelectedToggle() == null)
                output.setText("Enter status information.");
            else {
                if (resident.isSelected()) {
                    output.setText(addResident());
                } else if (tristate.isSelected()) {
                    output.setText(addTriState());
                } else if (international.isSelected()) {
                    output.setText(addInternational());
                } else {
                    output.setText(addNonResident());
                }
            }
        }
        else {
            if (name.getText()==null) {
                output.setText("Enter a valid name.");
            }
            else if ((RadioButton) major.getSelectedToggle()==null) {
                output.setText("Enter a valid major.");
            }
            else
                calcStudentTuition();
        }
        initialize();
        opToggle.getToggles().forEach(toggle -> {
            Node disable = (Node) toggle ;
            disable.setDisable(false);
        });
     */

    /**
     This method provides help on the Application.
     */
    @FXML
    public void aboutApplication () {
        output.setText("This application maintains a student roster for tuition purpose and processes the tuition.\n" +
                "The software is able to add and remove students to and from the roster, update student information, calculate\n" +
                "tuition dues and process payments.");
    }

    /**
     This method validates the input when adding a student.
     @param sCreditHours the creditHours of the student.
     @return true if add command is valid, false otherwise.
     */
    private String validateAddInput (String sCreditHours) {
        final int NEG_CREDIT_HOURS = 0;
        final int MIN_CREDIT_HOURS = 3;
        final int MAX_CREDIT_HOURS = 24;
        int creditHours;
        try {
            creditHours = Integer.parseInt(sCreditHours);
        }
        catch (NumberFormatException ex){
            //output.setText("Invalid credit hours.");
            return "Invalid credit hours.";
        }

        if (creditHours < NEG_CREDIT_HOURS) {
            //output.setText("Credit hours cannot be negative.");
            return "Credit hours cannot be negative.";
        }
        else if (creditHours >= NEG_CREDIT_HOURS && creditHours < MIN_CREDIT_HOURS) {
            //output.setText("Minimum credit hours is 3.");
            return "Minimum credit hours is 3.";
        }
        else if (creditHours > MAX_CREDIT_HOURS) {
            //output.setText("Credit hours exceed the maximum 24.");
            return "Credit hours exceed the maximum 24.";
        }
        else
            return "Credit Hours ok";
    }

    /**
     This method removes a student from the roster.
     */
    private void removeStudent () {
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();
        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            output.setText("'" + maj + "' is not a valid major.");
            return;
        }
        Major major = Major.valueOf(maj);
        Profile myProfile = new Profile(name1, major);
        Student myStudent = new Student(myProfile);
        if (myRoster.remove(myStudent))
            output.setText("Student removed from the roster.");
        else
            output.setText("Student is not in the roster.");
    }

    /**
     This method adds a resident student to the roster.
     */
    private String addResident (){
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();

        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            //output.setText("'" + maj + "' is not a valid major.");
            return ("'" + maj + "' is not a valid major.");
        }

        Major major = Major.valueOf(maj);
        String sCreditHours = creditHours.getText();

        String validateCreditHours = validateAddInput(sCreditHours);
        if (!validateCreditHours.equals("Credit Hours ok"))
            return validateCreditHours;

        int creditHours = Integer.parseInt(sCreditHours);
        Profile myProfile = new Profile(name1, major);
        Resident myResident = new Resident(myProfile);
        myResident.setCreditHours(creditHours);
        if (myRoster.add(myResident)) {
            return ("Student added.");
        }
        else
            return ("Student is already in the roster.");
    }

    /**
     This method adds a nonresident student to the roster.
     */
    private String addNonResident (){
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();;

        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            return ("'" + maj + "' is not a valid major.");
        }
        Major major = Major.valueOf(maj);
        String sCreditHours = creditHours.getText();
        String validateCreditHours = validateAddInput(sCreditHours);
        if (!validateCreditHours.equals("Credit Hours ok"))
            return validateCreditHours;

        int creditHours = Integer.parseInt(sCreditHours);
        Profile myProfile = new Profile(name1, major);
        NonResident myNonResident = new NonResident(myProfile);
        myNonResident.setCreditHours(creditHours);
        if (myRoster.add(myNonResident))
            return ("Student added.");
        else
            return ("Student is already in the roster.");
    }

    /**
     This method adds a tristate student to the roster.
     */
    private String addTriState (){
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();;

        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            return ("'" + maj + "' is not a valid major.");
        }
        Major major = Major.valueOf(maj);
        String sCreditHours = creditHours.getText();

        String validateCreditHours = validateAddInput(sCreditHours);
        if (!validateCreditHours.equals("Credit Hours ok"))
            return validateCreditHours;

        int creditHours = Integer.parseInt(sCreditHours);
        String state;
        if (ny.isSelected())
            state="NY";
        else if (ct.isSelected())
            state="CT";
        else
            state="NJ";

        try {
            State.valueOf(state);
        }
        catch (IllegalArgumentException e) {
            return ("Not part of the tri-state area.");
        }

        Profile myProfile = new Profile(name1, major);
        TriState myTriState = new TriState(myProfile);
        myTriState.setCreditHours(creditHours);
        myTriState.setOrigin(state);
        if (myRoster.add(myTriState))
            return ("Student added.");
        else
            return ("Student is already in the roster.");
    }

    /**
     This method adds an international student to the roster.
     */
    private String addInternational (){
        final int TWELVE_CREDITS = 12;
        String name1 = name.getText();
        RadioButton selectedRadioButton = (RadioButton) major.getSelectedToggle();
        String maj = selectedRadioButton.getText();;

        if(!maj.matches("(?i)CS|IT|EE|ME|BA")){
            maj = maj.toLowerCase();
            return ("'" + maj + "' is not a valid major.");
        }
        Major major = Major.valueOf(maj);
        String sCreditHours = creditHours.getText();
        String validateCreditHours = validateAddInput(sCreditHours);
        if (!validateCreditHours.equals("Credit Hours ok"))
            return validateCreditHours;

        int creditHours = Integer.parseInt(sCreditHours);
        if (creditHours < TWELVE_CREDITS) {
            return ("International students must enroll at least 12 credits.");
        }
        boolean status = false;
        if (studyAbroad.isSelected())
            status = true;
        Profile myProfile = new Profile(name1, major);
        International myInternational = new International(myProfile);
        myInternational.setCreditHours(creditHours);
        myInternational.setStatus(status);
        if (myRoster.add(myInternational))
            return ("Student added.");
        else
            return ("Student is already in the roster.");
    }
}