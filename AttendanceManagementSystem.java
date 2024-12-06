import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AttendanceManagementSystem {
    private JFrame frame;
    private JButton studentButton;
    private JButton facultyButton;
    Font Poppins;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AttendanceManagementSystem window = new AttendanceManagementSystem();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AttendanceManagementSystem() {
        initialize();
    }

    private void initialize() {

        try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")).deriveFont(Font.BOLD, 30);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }
        
        frame = new JFrame("Attendance Management System");
        frame.getContentPane().setBackground(new Color(255, 255, 255));
        frame.setBounds(100, 100, 1000, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);  
        frame.setLocationRelativeTo(null);

        studentButton = new JButton("Student");
        studentButton.setBounds(542,116,400,100);  
        studentButton.setBackground(new Color(255, 233, 77));
        studentButton.setForeground(new Color(128, 0, 0));
        studentButton.setFocusPainted(false);
        studentButton.setFont(Poppins);

        facultyButton = new JButton("Faculty");
        facultyButton.setBounds(542,246,400,100);  
        facultyButton.setBackground(new Color(141, 26, 26));
        facultyButton.setForeground(new Color(255, 223, 0));
        facultyButton.setFocusPainted(false);
        facultyButton.setFont(Poppins);

        frame.getContentPane().add(studentButton);
        frame.getContentPane().add(facultyButton);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./images/bg.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, -10, 1000, 490);
        frame.getContentPane().add(imageLabel);

        frame.setVisible(true);

        studentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginForm("Student");
            }
        });

        facultyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showLoginForm("Faculty");
            }
        });
    }

    private void showLoginForm(String userType) {
        LoginForm loginForm = new LoginForm(userType);
        loginForm.setVisible(true);
    }


}

class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;
    private String userType;
    Font Poppins;

    public LoginForm(String userType) {

        try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")).deriveFont(Font.BOLD, 30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        this.userType = userType;
        setTitle(userType + " Login");
        getContentPane().setBackground(new Color(254, 250, 224));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 500);
        setLocationRelativeTo(null);

        usernameField = new JTextField();
        int usernameX = 542;
        int usernameY = 40;
        int usernameWidth = 400;
        int usernameHeight = 40;
        usernameField.setBounds(usernameX, usernameY, usernameWidth, usernameHeight);

        JLabel usernameLabel = new JLabel("Username:");
        int usernameLabelX = usernameX;
        int usernameLabelY = usernameY - 30;
        int usernameLabelWidth = usernameWidth;
        int usernameLabelHeight = 30;
        usernameLabel.setBounds(usernameLabelX, usernameLabelY, usernameLabelWidth, usernameLabelHeight);
        usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        usernameLabel.setFont(Poppins);
        usernameLabel.setForeground(new Color(255, 255, 255));

        passwordField = new JPasswordField();
        int passwordX = 542;
        int passwordY = 140;
        int passwordWidth = 400;
        int passwordHeight = 40;
        passwordField.setBounds(passwordX, passwordY, passwordWidth, passwordHeight);

        JLabel passwordLabel = new JLabel("Password:");
        int passwordLabelX = passwordX;
        int passwordLabelY = passwordY - 30;
        int passwordLabelWidth = passwordWidth;
        int passwordLabelHeight = 30;
        passwordLabel.setBounds(passwordLabelX, passwordLabelY, passwordLabelWidth, passwordLabelHeight);
        passwordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passwordLabel.setFont(Poppins);
        passwordLabel.setForeground(new Color(255, 255, 255));


        loginButton = new JButton("Login");
        loginButton.setBounds(542,210,400,100);  
        loginButton.setBackground(new Color(255, 233, 77));
        loginButton.setForeground(new Color(128, 0, 0));
        loginButton.setFocusPainted(false);
        loginButton.setFont(Poppins);

        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(542,330,400,100);  
        signUpButton.setBackground(new Color(141, 26, 26));
        signUpButton.setForeground(new Color(255, 223, 0));
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(Poppins);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./images/bg.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, -10, 1000, 490);
        getContentPane().add(imageLabel);

        JLabel blank = new JLabel();

        add(loginButton);
        add(signUpButton);
        add(usernameField);
        add(passwordField);
        add(usernameLabel);
        add(passwordLabel);
        add(imageLabel);
        add(blank);
        

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                try {
                    login(username, password);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showSignUpForm();
            }
        });
    }

    private void login(String username, String password) throws ClassNotFoundException {
        String tableName;
        if (userType.equals("Student")) {
            tableName = "student_information";
        } else {
            tableName = "teacher_information";
        }

        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String url = "jdbc:mysql://localhost:3306/group4_oop_db";
        String usernameDB = "sqluser";
        String passwordDB = "password";
        String query = "SELECT * FROM " + tableName + " WHERE username = ? AND password = ?";

        try (Connection connection = DriverManager.getConnection(url, usernameDB, passwordDB);
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                if (tableName == "student_information") {
                    new Partone();
                }
                else if (tableName == "teacher_information") {
                    new Parttwo();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred during login.");
        }
    }

    private void showSignUpForm() {
        if (userType.equals("Student")) {
            StudentSignUpForm studentSignUpForm = new StudentSignUpForm();
            studentSignUpForm.setVisible(true);
        } else {
            TeacherSignUpForm teacherSignUpForm = new TeacherSignUpForm();
            teacherSignUpForm.setVisible(true);
        }
    }
}

class SignUpForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JRadioButton studentRadioButton;
    private JRadioButton teacherRadioButton;
    private JButton signUpButton;

    public SignUpForm() {
        setTitle("Sign Up Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLayout(new GridLayout(4, 1));

        // Initialize radio buttons
        studentRadioButton = new JRadioButton("Student");
        teacherRadioButton = new JRadioButton("Teacher");

        // Group the radio buttons
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(studentRadioButton);
        buttonGroup.add(teacherRadioButton);

        // Initialize sign up button
        signUpButton = new JButton("Sign Up");

        // Create a panel for radio buttons
        JPanel radioPanel = new JPanel();
        radioPanel.setLayout(new FlowLayout());
        radioPanel.add(studentRadioButton);
        radioPanel.add(teacherRadioButton);

        // Add the radio panel and sign up button to the frame
        add(radioPanel);
        add(signUpButton);

        // Add action listener for the sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (studentRadioButton.isSelected()) {
                    StudentSignUpForm studentSignUpForm = new StudentSignUpForm();
                    studentSignUpForm.setVisible(true);
                } else if (teacherRadioButton.isSelected()) {
                    TeacherSignUpForm teacherSignUpForm = new TeacherSignUpForm();
                    teacherSignUpForm.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(SignUpForm.this, "Please select a role.");
                }
            }
        });
    }
}

class StudentSignUpForm extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField userNameField;
    private JTextField passWordField;
	private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField sectionField;
    private JTextField studentNumberField;
    private JTextField birthdayField;
    private JTextField emergencyContactField;
    private JTextField emergencyContactNumberField;
    private JTextField subjectsEnrolledField;
    private JButton signUpButton;
    Font Poppins;
	
    public StudentSignUpForm() {

        try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")).deriveFont(Font.BOLD, 20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        setTitle("Student Sign Up Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 550);
        getContentPane().setBackground(new Color(254, 250, 224));
        setResizable(false);
        setLocationRelativeTo(null);

        // Initialize form fields
        userNameField = new JTextField();
        int userNameX = 63;
        int userNameY = 40;
        int userNameWidth = 400;
        int userNameHeight = 40;
        userNameField.setBounds(userNameX, userNameY, userNameWidth, userNameHeight);

        passWordField = new JTextField();
        int passWordX = 63;
        int passWordY = 120;
        int passWordWidth = 400;
        int passWordHeight = 40;
        passWordField.setBounds(passWordX, passWordY, passWordWidth, passWordHeight);

        firstNameField = new JTextField();
        int firstNameX = 63;
        int firstNameY = 200;
        int firstNameWidth = 400;
        int firstNameHeight = 40;
        firstNameField.setBounds(firstNameX, firstNameY, firstNameWidth, firstNameHeight);

        middleNameField = new JTextField();
        int middleNameX = 63;
        int middleNameY = 280;
        int middleNameWidth = 400;
        int middleNameHeigth = 40;
        middleNameField.setBounds(middleNameX, middleNameY, middleNameWidth, middleNameHeigth);

        lastNameField = new JTextField();
        int lastNameX = 63;
        int lastNameY = 360;
        int lastNameWidth = 400;
        int lastNameHeigth = 40;
        lastNameField.setBounds(lastNameX, lastNameY, lastNameWidth, lastNameHeigth);

        sectionField = new JTextField();
        int sectionX = 63;
        int sectionY = 440;
        int sectionWidth = 400;
        int sectionHeigth = 40;
        sectionField.setBounds(sectionX, sectionY, sectionWidth, sectionHeigth);

        studentNumberField = new JTextField();
        int studentNumberX = 521;
        int studentNumberY = 40;
        int studentNumberWidth = 400;
        int studentNumberHeigth = 40;
        studentNumberField.setBounds(studentNumberX, studentNumberY, studentNumberWidth, studentNumberHeigth);

        birthdayField = new JTextField();
        int birthdayX = 521;
        int birthdayY = 120;
        int birthdayWidth = 400;
        int birthdayHeigth = 40;
        birthdayField.setBounds(birthdayX, birthdayY, birthdayWidth, birthdayHeigth);

        emergencyContactField = new JTextField();
        int emergencyContactX = 521;
        int emergencyContactY = 200;
        int emergencyContactWidth = 400;
        int emergencyContactHeigth = 40;
        emergencyContactField.setBounds(emergencyContactX, emergencyContactY, emergencyContactWidth, emergencyContactHeigth);

        emergencyContactNumberField = new JTextField();
        int emergencyContactNumberX = 521;
        int emergencyContactNumberY = 280;
        int emergencyContactNumberWidth = 400;
        int emergencyContactNumberHeigth = 40;
        emergencyContactNumberField.setBounds(emergencyContactNumberX, emergencyContactNumberY, emergencyContactNumberWidth, emergencyContactNumberHeigth);

        subjectsEnrolledField = new JTextField();
        int subjectsEnrolledX = 521;
        int subjectsEnrolledY = 360;
        int subjectsEnrolledWidth = 400;
        int subjectsEnrolledHeigth = 40;
        subjectsEnrolledField.setBounds(subjectsEnrolledX, subjectsEnrolledY, subjectsEnrolledWidth, subjectsEnrolledHeigth);

        // Initialize combo box for subjects enrolled
        
        // Initialize sign up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(521,420,400,60);  
        signUpButton.setBackground(new Color(255, 233, 77));
        signUpButton.setForeground(new Color(128, 0, 0));
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(Poppins);

        // Add form fields and button to the frame
        JLabel userNameLabel = new JLabel("Username:");
        int userNameLabelX = userNameX;
        int userNameLabelY = userNameY - 25;
        int userNameLabelWidth = userNameWidth;
        int userNameLabelHeight = 30;
        userNameLabel.setBounds(userNameLabelX, userNameLabelY, userNameLabelWidth, userNameLabelHeight);
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setFont(Poppins);
        userNameLabel.setForeground(new Color(255, 255, 255));
        
        JLabel passWordLabel = new JLabel("Password:");
        int passWordLabelX = passWordX;
        int passWordLabelY = passWordY - 25;
        int passWordLabelWidth = passWordWidth;
        int passWordLabelHeight = 30;
        passWordLabel.setBounds(passWordLabelX, passWordLabelY, passWordLabelWidth, passWordLabelHeight);
        passWordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passWordLabel.setFont(Poppins);
        passWordLabel.setForeground(new Color(255, 255, 255));
        
        JLabel firstNameLabel = new JLabel("First Name:");
        int firstNameLabelX = firstNameX;
        int firstNameLabelY = firstNameY - 25;
        int firstNameLabelWidth = firstNameWidth;
        int firstNameLabelHeight = 30;
        firstNameLabel.setBounds(firstNameLabelX, firstNameLabelY, firstNameLabelWidth, firstNameLabelHeight);
        firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabel.setFont(Poppins);
        firstNameLabel.setForeground(new Color(255, 255, 255));

        JLabel middleNameLabel = new JLabel("Middle Name:");
        int middleNameLabelX = middleNameX;
        int middleNameLabelY = middleNameY - 25;
        int middleNameLabelWidth = middleNameWidth;
        int middleNameLabelHeight = 30;
        middleNameLabel.setBounds(middleNameLabelX, middleNameLabelY, middleNameLabelWidth, middleNameLabelHeight);
        middleNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        middleNameLabel.setFont(Poppins);
        middleNameLabel.setForeground(new Color(255, 255, 255));

        JLabel lastNameLabel = new JLabel("Last Name:");
        int lastNameLabelX = lastNameX;
        int lastNameLabelY = lastNameY - 25;
        int lastNameLabelWidth = lastNameWidth;
        int lastNameLabelHeight = 30;
        lastNameLabel.setBounds(lastNameLabelX, lastNameLabelY, lastNameLabelWidth, lastNameLabelHeight);
        lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabel.setFont(Poppins);
        lastNameLabel.setForeground(new Color(255, 255, 255));

        JLabel sectionLabel = new JLabel("Section:");
        int sectionLabelX = sectionX;
        int sectionLabelY = sectionY - 25;
        int sectionLabelWidth = sectionWidth;
        int sectionLabelHeight = 30;
        sectionLabel.setBounds(sectionLabelX, sectionLabelY, sectionLabelWidth, sectionLabelHeight);
        sectionLabel.setHorizontalAlignment(SwingConstants.LEFT);
        sectionLabel.setFont(Poppins);
        sectionLabel.setForeground(new Color(255, 255, 255));

        JLabel studentNumberLabel = new JLabel("Student Number:");
        int studentNumberLabelX = studentNumberX;
        int studentNumberLabelY = studentNumberY - 25;
        int studentNumberLabelWidth = studentNumberWidth;
        int studentNumberLabelHeight = 30;
        studentNumberLabel.setBounds(studentNumberLabelX, studentNumberLabelY, studentNumberLabelWidth, studentNumberLabelHeight);
        studentNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        studentNumberLabel.setFont(Poppins);
        studentNumberLabel.setForeground(new Color(255, 255, 255));
        
        JLabel birthdayLabel = new JLabel("Birthday:");
        int birthdayLabelX = birthdayX;
        int birthdayLabelY = birthdayY - 25;
        int birthdayLabelWidth = birthdayWidth;
        int birthdayLabelHeight = 30;
        birthdayLabel.setBounds(birthdayLabelX, birthdayLabelY, birthdayLabelWidth, birthdayLabelHeight);
        birthdayLabel.setHorizontalAlignment(SwingConstants.LEFT);
        birthdayLabel.setFont(Poppins);
        birthdayLabel.setForeground(new Color(255, 255, 255));
        
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:");
        int emergencyContactLabelX = emergencyContactX;
        int emergencyContactLabelY = emergencyContactY - 25;
        int emergencyContactLabelWidth = emergencyContactWidth;
        int emergencyContactLabelHeight = 30;
        emergencyContactLabel.setBounds(emergencyContactLabelX, emergencyContactLabelY, emergencyContactLabelWidth, emergencyContactLabelHeight);
        emergencyContactLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emergencyContactLabel.setFont(Poppins);
        emergencyContactLabel.setForeground(new Color(255, 255, 255));

        JLabel emergencyContactNumberLabel = new JLabel("Emergency Contact Number:");
        int emergencyContactNumberLabelX = emergencyContactNumberX;
        int emergencyContactNumberLabelY = emergencyContactNumberY - 25;
        int emergencyContactNumberLabelWidth = emergencyContactNumberWidth;
        int emergencyContactNumberLabelHeight = 30;
        emergencyContactNumberLabel.setBounds(emergencyContactNumberLabelX, emergencyContactNumberLabelY, emergencyContactNumberLabelWidth, emergencyContactNumberLabelHeight);
        emergencyContactNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emergencyContactNumberLabel.setFont(Poppins);
        emergencyContactNumberLabel.setForeground(new Color(255, 255, 255));

        JLabel subjectsEnrolledLabel = new JLabel("Subject Enrolled:");
        int subjectsEnrolledLabelX = subjectsEnrolledX;
        int subjectsEnrolledLabelY = subjectsEnrolledY - 25;
        int subjectsEnrolledLabelWidth = subjectsEnrolledWidth;
        int subjectsEnrolledLabelHeight = 30;
        subjectsEnrolledLabel.setBounds(subjectsEnrolledLabelX, subjectsEnrolledLabelY, subjectsEnrolledLabelWidth, subjectsEnrolledLabelHeight);
        subjectsEnrolledLabel.setHorizontalAlignment(SwingConstants.LEFT);
        subjectsEnrolledLabel.setFont(Poppins);
        subjectsEnrolledLabel.setForeground(new Color(255, 255, 255));


        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./images/bg1.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, -10, 1000, 550);
        getContentPane().add(imageLabel);

        add(userNameField);
        add(passWordField);
        add(firstNameField);
        add(middleNameField);
        add(lastNameField);
        add(sectionField);
        add(studentNumberField);
        add(birthdayField);
        add(emergencyContactField);
        add(emergencyContactNumberField);
        add(subjectsEnrolledField);
        add(userNameLabel);
        add(passWordLabel);
        add(firstNameLabel);
        add(middleNameLabel);
        add(lastNameLabel);
        add(sectionLabel);
        add(studentNumberLabel);
        add(birthdayLabel);
        add(emergencyContactLabel);
        add(emergencyContactNumberLabel);
        add(subjectsEnrolledLabel);
        add(signUpButton);
        add(imageLabel);
        add(new JLabel());

        // Add action listener for the sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    signUp();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    private void signUp() throws ClassNotFoundException {
    	String userName = userNameField.getText();
        String passWord = passWordField.getText();
    	String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        String section = sectionField.getText();
        String studentNumber = studentNumberField.getText();
        String birthday = birthdayField.getText();
        String emergencyContact = emergencyContactField.getText();
        String emergencyContactNumber = emergencyContactNumberField.getText();
        String subjectsEnrolled = subjectsEnrolledField.getText();

        // Database connection details
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String url = "jdbc:mysql://localhost:3306/group4_oop_db";
        String username = "sqluser";
        String password = "password";

        // SQL query to insert student sign-up data
        String query = "INSERT INTO student_information (username, passWord, first_name, middle_name, last_name, section, student_number, birthday, emergency_contact, emergency_contact_number, subjects_enrolled) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameter values for the SQL query
        	statement.setString(1, userName);
            statement.setString(2, passWord);
            statement.setString(3, firstName);
            statement.setString(4, middleName);
            statement.setString(5, lastName);
            statement.setString(6, section);
            statement.setString(7, studentNumber);
            statement.setString(8, birthday);
            statement.setString(9, emergencyContact);
            statement.setString(10, emergencyContactNumber);
            statement.setString(11, subjectsEnrolled);

            // Execute the SQL query
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(StudentSignUpForm.this, "Student sign-up successful!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(StudentSignUpForm.this, "Failed to sign up student.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(StudentSignUpForm.this, "An error occurred while signing up student.");
        }
    }

    private void clearFields() {
    	userNameField.setText("");
        passWordField.setText("");
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        sectionField.setText("");
        studentNumberField.setText("");
        birthdayField.setText("");
        emergencyContactField.setText("");
        emergencyContactNumberField.setText("");
        subjectsEnrolledField.setText("");
    }
}
   

class TeacherSignUpForm extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField userNameField;
    private JTextField passWordField;
	private JTextField firstNameField;
    private JTextField middleNameField;
    private JTextField lastNameField;
    private JTextField employeeNumberField;
    private JTextField birthdayField;
    private JTextField emergencyContactField;
    private JTextField emergencyContactNumberField;
    private JTextField handledSubjectsField;
    private JButton signUpButton;
    Font Poppins;

    public TeacherSignUpForm() {

        try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")).deriveFont(Font.BOLD, 20f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./images/Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        setTitle("Teacher Sign Up Form");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 550);
        getContentPane().setBackground(new Color(254, 250, 224));
        setResizable(false);
        setLocationRelativeTo(null);

        // Initialize form fields
        userNameField = new JTextField();
        int userNameX = 63;
        int userNameY = 40;
        int userNameWidth = 400;
        int userNameHeight = 40;
        userNameField.setBounds(userNameX, userNameY, userNameWidth, userNameHeight);

        passWordField = new JTextField();
        int passWordX = 63;
        int passWordY = 120;
        int passWordWidth = 400;
        int passWordHeight = 40;
        passWordField.setBounds(passWordX, passWordY, passWordWidth, passWordHeight);

        firstNameField = new JTextField();
        int firstNameX = 63;
        int firstNameY = 200;
        int firstNameWidth = 400;
        int firstNameHeight = 40;
        firstNameField.setBounds(firstNameX, firstNameY, firstNameWidth, firstNameHeight);

        middleNameField = new JTextField();
        int middleNameX = 63;
        int middleNameY = 280;
        int middleNameWidth = 400;
        int middleNameHeigth = 40;
        middleNameField.setBounds(middleNameX, middleNameY, middleNameWidth, middleNameHeigth);

        lastNameField = new JTextField();
        int lastNameX = 63;
        int lastNameY = 360;
        int lastNameWidth = 400;
        int lastNameHeigth = 40;
        lastNameField.setBounds(lastNameX, lastNameY, lastNameWidth, lastNameHeigth);

        employeeNumberField = new JTextField();
        int employeeNumberX = 521;
        int employeeNumberY = 40;
        int employeeNumberWidth = 400;
        int employeeNumberHeigth = 40;
        employeeNumberField.setBounds(employeeNumberX, employeeNumberY, employeeNumberWidth, employeeNumberHeigth);

        birthdayField = new JTextField();
        int birthdayX = 521;
        int birthdayY = 120;
        int birthdayWidth = 400;
        int birthdayHeigth = 40;
        birthdayField.setBounds(birthdayX, birthdayY, birthdayWidth, birthdayHeigth);

        emergencyContactField = new JTextField();
        int emergencyContactX = 521;
        int emergencyContactY = 200;
        int emergencyContactWidth = 400;
        int emergencyContactHeigth = 40;
        emergencyContactField.setBounds(emergencyContactX, emergencyContactY, emergencyContactWidth, emergencyContactHeigth);

        emergencyContactNumberField = new JTextField();
        int emergencyContactNumberX = 521;
        int emergencyContactNumberY = 280;
        int emergencyContactNumberWidth = 400;
        int emergencyContactNumberHeigth = 40;
        emergencyContactNumberField.setBounds(emergencyContactNumberX, emergencyContactNumberY, emergencyContactNumberWidth, emergencyContactNumberHeigth);

        handledSubjectsField = new JTextField();
        int handledSubjectsX = 521;
        int handledSubjectsY = 360;
        int handledSubjectsWidth = 400;
        int handledSubjectsHeigth = 40;
        handledSubjectsField.setBounds(handledSubjectsX, handledSubjectsY, handledSubjectsWidth, handledSubjectsHeigth);

        // Initialize combo box for subjects enrolled
        
        // Initialize sign up button
        signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(63,420,858,60);  
        signUpButton.setBackground(new Color(255, 233, 77));
        signUpButton.setForeground(new Color(128, 0, 0));
        signUpButton.setFocusPainted(false);
        signUpButton.setFont(Poppins);

        // Add form fields and button to the frame
        JLabel userNameLabel = new JLabel("Username:");
        int userNameLabelX = userNameX;
        int userNameLabelY = userNameY - 30;
        int userNameLabelWidth = userNameWidth;
        int userNameLabelHeight = 30;
        userNameLabel.setBounds(userNameLabelX, userNameLabelY, userNameLabelWidth, userNameLabelHeight);
        userNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        userNameLabel.setFont(Poppins);
        userNameLabel.setForeground(new Color(255, 255, 255));
        
        JLabel passWordLabel = new JLabel("Password:");
        int passWordLabelX = passWordX;
        int passWordLabelY = passWordY - 30;
        int passWordLabelWidth = passWordWidth;
        int passWordLabelHeight = 30;
        passWordLabel.setBounds(passWordLabelX, passWordLabelY, passWordLabelWidth, passWordLabelHeight);
        passWordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        passWordLabel.setFont(Poppins);
        passWordLabel.setForeground(new Color(255, 255, 255));
        
        JLabel firstNameLabel = new JLabel("First Name:");
        int firstNameLabelX = firstNameX;
        int firstNameLabelY = firstNameY - 30;
        int firstNameLabelWidth = firstNameWidth;
        int firstNameLabelHeight = 30;
        firstNameLabel.setBounds(firstNameLabelX, firstNameLabelY, firstNameLabelWidth, firstNameLabelHeight);
        firstNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        firstNameLabel.setFont(Poppins);
        firstNameLabel.setForeground(new Color(255, 255, 255));

        JLabel middleNameLabel = new JLabel("Middle Name:");
        int middleNameLabelX = middleNameX;
        int middleNameLabelY = middleNameY - 30;
        int middleNameLabelWidth = middleNameWidth;
        int middleNameLabelHeight = 30;
        middleNameLabel.setBounds(middleNameLabelX, middleNameLabelY, middleNameLabelWidth, middleNameLabelHeight);
        middleNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        middleNameLabel.setFont(Poppins);
        middleNameLabel.setForeground(new Color(255, 255, 255));

        JLabel lastNameLabel = new JLabel("Last Name:");
        int lastNameLabelX = lastNameX;
        int lastNameLabelY = lastNameY - 30;
        int lastNameLabelWidth = lastNameWidth;
        int lastNameLabelHeight = 30;
        lastNameLabel.setBounds(lastNameLabelX, lastNameLabelY, lastNameLabelWidth, lastNameLabelHeight);
        lastNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lastNameLabel.setFont(Poppins);
        lastNameLabel.setForeground(new Color(255, 255, 255));

        JLabel employeeNumberLabel = new JLabel("Employee Number:");
        int employeeNumberLabelX = employeeNumberX;
        int employeeNumberLabelY = employeeNumberY - 30;
        int employeeNumberLabelWidth = employeeNumberWidth;
        int employeeNumberLabelHeight = 30;
        employeeNumberLabel.setBounds(employeeNumberLabelX, employeeNumberLabelY, employeeNumberLabelWidth, employeeNumberLabelHeight);
        employeeNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        employeeNumberLabel.setFont(Poppins);
        employeeNumberLabel.setForeground(new Color(255, 255, 255));
        
        JLabel birthdayLabel = new JLabel("Birthday:");
        int birthdayLabelX = birthdayX;
        int birthdayLabelY = birthdayY - 30;
        int birthdayLabelWidth = birthdayWidth;
        int birthdayLabelHeight = 30;
        birthdayLabel.setBounds(birthdayLabelX, birthdayLabelY, birthdayLabelWidth, birthdayLabelHeight);
        birthdayLabel.setHorizontalAlignment(SwingConstants.LEFT);
        birthdayLabel.setFont(Poppins);
        birthdayLabel.setForeground(new Color(255, 255, 255));
        
        JLabel emergencyContactLabel = new JLabel("Emergency Contact:");
        int emergencyContactLabelX = emergencyContactX;
        int emergencyContactLabelY = emergencyContactY - 30;
        int emergencyContactLabelWidth = emergencyContactWidth;
        int emergencyContactLabelHeight = 30;
        emergencyContactLabel.setBounds(emergencyContactLabelX, emergencyContactLabelY, emergencyContactLabelWidth, emergencyContactLabelHeight);
        emergencyContactLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emergencyContactLabel.setFont(Poppins);
        emergencyContactLabel.setForeground(new Color(255, 255, 255));

        JLabel emergencyContactNumberLabel = new JLabel("Emergency Contact Number:");
        int emergencyContactNumberLabelX = emergencyContactNumberX;
        int emergencyContactNumberLabelY = emergencyContactNumberY - 30;
        int emergencyContactNumberLabelWidth = emergencyContactNumberWidth;
        int emergencyContactNumberLabelHeight = 30;
        emergencyContactNumberLabel.setBounds(emergencyContactNumberLabelX, emergencyContactNumberLabelY, emergencyContactNumberLabelWidth, emergencyContactNumberLabelHeight);
        emergencyContactNumberLabel.setHorizontalAlignment(SwingConstants.LEFT);
        emergencyContactNumberLabel.setFont(Poppins);
        emergencyContactNumberLabel.setForeground(new Color(255, 255, 255));
        
        JLabel handledSubjectsLabel = new JLabel("Subject Enrolled:");
        int handledSubjectsLabelX = handledSubjectsX;
        int handledSubjectsLabelY = handledSubjectsY - 30;
        int handledSubjectsLabelWidth = handledSubjectsWidth;
        int handledSubjectsLabelHeight = 30;
        handledSubjectsLabel.setBounds(handledSubjectsLabelX, handledSubjectsLabelY, handledSubjectsLabelWidth, handledSubjectsLabelHeight);
        handledSubjectsLabel.setHorizontalAlignment(SwingConstants.LEFT);
        handledSubjectsLabel.setFont(Poppins);
        handledSubjectsLabel.setForeground(new Color(255, 255, 255));

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./images/bg1.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, -10, 1000, 550);
        getContentPane().add(imageLabel);

        add(userNameField);
        add(passWordField);
        add(firstNameField);
        add(middleNameField);
        add(lastNameField);
        add(employeeNumberField);
        add(birthdayField);
        add(emergencyContactField);
        add(emergencyContactNumberField);
        add(handledSubjectsField);
        add(userNameLabel);
        add(passWordLabel);
        add(firstNameLabel);
        add(middleNameLabel);
        add(lastNameLabel);
        add(employeeNumberLabel);
        add(birthdayLabel);
        add(emergencyContactLabel);
        add(emergencyContactNumberLabel);
        add(handledSubjectsLabel);
        add(signUpButton);
        add(imageLabel);
        add(new JLabel());

        // Add action listener for the sign up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    signUp();
                } catch (ClassNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        });
    }

    private void signUp() throws ClassNotFoundException {
    	String userName = userNameField.getText();
        String passWord = passWordField.getText();
    	String firstName = firstNameField.getText();
        String middleName = middleNameField.getText();
        String lastName = lastNameField.getText();
        String employeeNumber = employeeNumberField.getText();
        String birthday = birthdayField.getText();
        String emergencyContact = emergencyContactField.getText();
        String emergencyContactNumber = emergencyContactNumberField.getText();
        String handledSubjects = handledSubjectsField.getText();

        // Database connection details
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String url = "jdbc:mysql://localhost:3306/group4_oop_db";
        String username = "sqluser";
        String password = "password";

        // SQL query to insert teacher sign-up data
        String query = "INSERT INTO teacher_information (username, password, first_name, middle_name, last_name, employee_number, birthday, emergency_contact, emergency_contact_number, handled_subjects) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(query)) {

            // Set the parameter values for the SQL query
        	statement.setString(1, userName);
            statement.setString(2, passWord);
            statement.setString(3, firstName);
            statement.setString(4, middleName);
            statement.setString(5, lastName);
            statement.setString(6, employeeNumber);
            statement.setString(7, birthday);
            statement.setString(8, emergencyContact);
            statement.setString(9, emergencyContactNumber);
            statement.setString(10, handledSubjects);

            // Execute the SQL query
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(TeacherSignUpForm.this, "Teacher sign-up successful!");
                clearFields();
            } else {
                JOptionPane.showMessageDialog(TeacherSignUpForm.this, "Failed to sign up teacher.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(TeacherSignUpForm.this, "An error occurred while signing up teacher.");
        }
    }

    private void clearFields() {
    	userNameField.setText("");
        passWordField.setText("");
        firstNameField.setText("");
        middleNameField.setText("");
        lastNameField.setText("");
        employeeNumberField.setText("");
        birthdayField.setText("");
        emergencyContactField.setText("");
        emergencyContactNumberField.setText("");
        handledSubjectsField.setText("");
    }
}