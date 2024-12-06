import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class Partone extends JFrame implements ActionListener, ChangeListener {

    JButton cb0_button;
    JLabel cb0_labelStudentNumber, cb0_labelFirstName, cb0_labelLastName, cb0_labelSection,
            cb0_labelTemperature;
    JTextField cb0_tfStudentNumber, cb0_tfFirstName, cb0_tfLastName, cb0_tfSection;
    JRadioButton cb0_radioButtonIn, cb0_radioButtonOut;
    JSlider cb0_temperatureSlider;
    JComboBox<String> comboBox;
    Font Poppins;

    // sql variables
    String[] cb0_sqlarr = new String[5];
    float cb0_sqltemperature;
    java.util.Date cb0_date = new java.util.Date();
    java.sql.Timestamp cb0_sqlDate = new java.sql.Timestamp(cb0_date.getTime());

    Partone() {

        try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")).deriveFont(Font.PLAIN, 18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.getContentPane().setBackground(Color.white);

        // Crafting Table 
        // Combo box
        String[] options = {"Attendance"};
        comboBox = new JComboBox<String>(options);
        comboBox.addActionListener(this);
        comboBox.setBounds(100, 10, 300, 30);
        comboBox.setFont(Poppins);
        this.add(comboBox);

        // Text fields
        cb0_tfStudentNumber = new JTextField();
        int studentNumberX = 50;
        int studentNumberY = 80;
        int studentNumberWidth = 400;
        int studentNumberHeight = 30;
        cb0_tfStudentNumber.setBounds(studentNumberX, studentNumberY, studentNumberWidth, studentNumberHeight);
        this.add(cb0_tfStudentNumber);

        cb0_tfFirstName = new JTextField();
        int FirstNameX = 50;
        int FirstNameY = 150;
        int FirstNameWidth = 400;
        int FirstNameHeight = 30;
        cb0_tfFirstName.setBounds(FirstNameX, FirstNameY, FirstNameWidth, FirstNameHeight);
        this.add(cb0_tfFirstName);

        cb0_tfLastName = new JTextField();
        cb0_tfLastName.setBounds(160, 150, 400, 30);
        int LastNameX = 50;
        int LastNameY = 220;
        int LastNameWidth = 400;
        int LastNameHeight = 30;
        cb0_tfLastName.setBounds(LastNameX, LastNameY, LastNameWidth, LastNameHeight);
        this.add(cb0_tfLastName);

        cb0_tfSection = new JTextField();
        cb0_tfSection.setBounds(160, 150, 400, 30);
        int SectionX = 50;
        int SectionY = 290;
        int SectionWidth = 400;
        int SectionHeight = 30;
        cb0_tfSection.setBounds(SectionX, SectionY, SectionWidth, SectionHeight);
        this.add(cb0_tfSection);

        // Labels
        cb0_labelStudentNumber = new JLabel("Student Number:");
        int studentNumberLabelX = studentNumberX;
        int studentNumberLabelY = studentNumberY - 25;
        int studentNumberLabelWidth = studentNumberWidth;
        int studentNumberLabelHeight = 30;
        cb0_labelStudentNumber.setBounds(studentNumberLabelX, studentNumberLabelY, studentNumberLabelWidth, studentNumberLabelHeight);
        cb0_labelStudentNumber.setHorizontalAlignment(SwingConstants.LEFT);
        cb0_labelStudentNumber.setForeground(new Color(255, 255, 255));
        cb0_labelStudentNumber.setFont(Poppins);
        this.add(cb0_labelStudentNumber);

        cb0_labelFirstName = new JLabel("First Name:");
        int FirstNameLabelX = FirstNameX;
        int FirstNameLabelY = FirstNameY - 25;
        int FirstNameLabelWidth = FirstNameWidth;
        int FirstNameLabelHeight = 30;
        cb0_labelFirstName.setBounds(FirstNameLabelX, FirstNameLabelY, FirstNameLabelWidth, FirstNameLabelHeight);
        cb0_labelFirstName.setHorizontalAlignment(SwingConstants.LEFT);
        cb0_labelFirstName.setForeground(new Color(255, 255, 255));
        cb0_labelFirstName.setFont(Poppins);
        this.add(cb0_labelFirstName);

        cb0_labelLastName = new JLabel("Last Name:");
        int LastNameLabelX = LastNameX;
        int LastNameLabelY = LastNameY - 25;
        int LastNameLabelWidth = LastNameWidth;
        int LastNameLabelHeight = 30;
        cb0_labelLastName.setBounds(LastNameLabelX, LastNameLabelY, LastNameLabelWidth, LastNameLabelHeight);
        cb0_labelLastName.setHorizontalAlignment(SwingConstants.LEFT);
        cb0_labelLastName.setForeground(new Color(255, 255, 255));
        cb0_labelLastName.setFont(Poppins);
        this.add(cb0_labelLastName);

        cb0_labelSection = new JLabel("Course & Sec.:");
        int SectionLabelX = SectionX;
        int SectionLabelY = SectionY - 25;
        int SectionLabelWidth = SectionWidth;
        int SectionLabelHeight = 30;
        cb0_labelSection.setBounds(SectionLabelX, SectionLabelY, SectionLabelWidth, SectionLabelHeight);
        cb0_labelSection.setHorizontalAlignment(SwingConstants.LEFT);
        cb0_labelSection.setForeground(new Color(255, 255, 255));
        cb0_labelSection.setFont(Poppins);
        this.add(cb0_labelSection);

        cb0_labelTemperature = new JLabel();
        cb0_labelTemperature.setForeground(new Color(255, 255, 255));
        cb0_labelTemperature.setFont(Poppins);
        cb0_labelTemperature.setBounds(200, 390, 200, 40);
        this.add(cb0_labelTemperature);

        

        // Radio buttons
        cb0_radioButtonIn = new JRadioButton("Check In");
        cb0_radioButtonIn.setForeground(new Color(255, 255, 255));
        cb0_radioButtonIn.setFont(Poppins);
        cb0_radioButtonIn.setBounds(100, 340, 120, 40);
        cb0_radioButtonIn.setFocusable(false);
        cb0_radioButtonIn.setOpaque(false);
        this.add(cb0_radioButtonIn);

        cb0_radioButtonOut = new JRadioButton("Check Out");
        cb0_radioButtonOut.setForeground(new Color(255, 255, 255));
        cb0_radioButtonOut.setFont(Poppins);
        cb0_radioButtonOut.setBounds(280, 340, 120, 40);
        cb0_radioButtonOut.setFocusable(false);
        cb0_radioButtonOut.setOpaque(false);
        this.add(cb0_radioButtonOut);

        ButtonGroup cb0_group = new ButtonGroup();
        cb0_group.add(cb0_radioButtonIn);
        cb0_group.add(cb0_radioButtonOut);

        // Slider
        cb0_temperatureSlider = new JSlider(0, 500);
        cb0_temperatureSlider.setOpaque(false);
        cb0_temperatureSlider.setPaintTicks(true);
        cb0_temperatureSlider.setMinorTickSpacing(50);
        cb0_temperatureSlider.setPaintTrack(true);
        cb0_temperatureSlider.setMajorTickSpacing(100 / 10);
        cb0_temperatureSlider.setPaintLabels(true);
        cb0_temperatureSlider.setFont(new Font("Arial", Font.PLAIN, 10));
        cb0_temperatureSlider.setBounds(50, 430, 400, 50);
        cb0_labelTemperature.setText("°C = 25.0");
        cb0_temperatureSlider.addChangeListener(this);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("0"));
        labels.put(100, new JLabel("10"));
        labels.put(250, new JLabel("25"));
        labels.put(400, new JLabel("40"));
        labels.put(500, new JLabel("50"));
        cb0_temperatureSlider.setLabelTable(labels);
        this.add(cb0_temperatureSlider);

        // Button
        cb0_button = new JButton("Submit");
        cb0_button.addActionListener(this);
        cb0_button.setFont(Poppins);
        cb0_button.setBackground(new Color(255, 233, 77));
        cb0_button.setForeground(new Color(128, 0, 0));
        cb0_button.setFocusable(false);
        cb0_button.setBounds(50, 490, 400, 50);
        this.add(cb0_button);

        this.setSize(500, 590);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("Z_School\\Y2_Sem2\\OOP, finals\\update\\update2\\images\\bg3.png");
        imageLabel.setIcon(imageIcon);
        imageLabel.setBounds(0, -10, 500, 590);
        getContentPane().add(imageLabel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == comboBox) {
            switch (comboBox.getSelectedIndex()) {
                case 0:
                    // Attendance selected
                    System.out.println(comboBox.getSelectedItem());
                    break;
            }
        } else if (e.getSource() == cb0_button) {
            cb0_sqlarr[0] = cb0_tfStudentNumber.getText();
            cb0_sqlarr[1] = cb0_tfFirstName.getText();
            cb0_sqlarr[2] = cb0_tfLastName.getText();
            cb0_sqlarr[3] = cb0_tfSection.getText();
            cb0_sqltemperature = (float) cb0_temperatureSlider.getValue() / 10;

            if (cb0_radioButtonIn.isSelected()) {
                try {
                    cb0_insertInfo(cb0_sqlarr, cb0_sqltemperature);

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            } else if (cb0_radioButtonOut.isSelected()) {
                try {
                    cb0_insertChOut(cb0_sqlarr[0]);

                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        cb0_labelTemperature.setText("°C = " + ((float) cb0_temperatureSlider.getValue() / 10));
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/group4_oop_db";
        String username = "sqluser";
        String password = "password";
        return DriverManager.getConnection(url, username, password);
    }

    public void cb0_insertInfo(String cb0_sql[], float cb0_sql_temperature) throws ClassNotFoundException {
        try {
            String SQL = "INSERT INTO attendance_table(StudentNumber, FirstName, LastName, CourseAndSection, CheckInDateTime, Temperature)" + " VALUES(?,?,?,?,?,?)";
            Connection connection = connect();
            PreparedStatement preparedstmt = connection.prepareStatement(SQL);

            int cb0_insertInfoConfirm = JOptionPane.showConfirmDialog(null, "Confirm Attendance?", "Attendance Confirmation", JOptionPane.YES_NO_OPTION);
            if (cb0_insertInfoConfirm == 0) {
                preparedstmt.setString(1, cb0_sql[0]);
                preparedstmt.setString(2, cb0_sql[1]);
                preparedstmt.setString(3, cb0_sql[2]);
                preparedstmt.setString(4, cb0_sql[3]);
                preparedstmt.setTimestamp(5, cb0_sqlDate);
                preparedstmt.setFloat(6, cb0_sql_temperature);

                preparedstmt.execute();
                connection.close();
                JOptionPane.showMessageDialog(null, "Attendance input has been successful", "", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cb0_insertChOut(String cb0_stdNumber) throws ClassNotFoundException {
        try {
            String SQL = "UPDATE attendance_table SET CheckOutDateTime = ? WHERE StudentNumber = ? AND CheckOutDateTime IS NULL";
            Connection connection = connect();
            PreparedStatement preparedstmt = connection.prepareStatement(SQL);

            int cb0_insertInfoConfirm = JOptionPane.showConfirmDialog(null, "Confirm Attendance?", "Attendance Confirmation", JOptionPane.YES_NO_OPTION);
            if (cb0_insertInfoConfirm == 0) {
                preparedstmt.setTimestamp(1, cb0_sqlDate);
                preparedstmt.setString(2, cb0_stdNumber);

                preparedstmt.executeUpdate();
                connection.close();
                JOptionPane.showMessageDialog(null, "Attendance update has been successful", "", JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Partone();
            }
        });
    }
}
