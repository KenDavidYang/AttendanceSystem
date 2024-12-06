import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;


public class Parttwo extends JFrame implements ActionListener, ChangeListener {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JButton cb0_button;
    JLabel cb0_labelTeacherNumber, cb0_labelFirstName, cb0_labelLastName, cb0_labelSection,
            cb0_labelTemperature, blank1, blank2, blank3, blank4, blank5, blank6, blank7, blank8,
            blank9, blank10, blank11;
    JTextField cb0_tfTeacherNumber, cb0_tfFirstName, cb0_tfLastName;
    JPanel panel1, panel2, panel3;
    JPanel cb0_subPanel1, cb0_subPanel2, cb0_subPanel3;
    JPanel cb1_subPanel1;
    JRadioButton cb0_radioButtonIn, cb0_radioButtonOut;
    JSlider cb0_temperatureSlider;
    JTable cb1_table, cb2_table;
    JComboBox<String> comboBox;
    Font Poppins;

    // sql variables
    String[] cb0_sqlarr = new String[5];
    float cb0_sqltemperature;
    java.util.Date cb0_date = new java.util.Date();
    java.sql.Timestamp cb0_sqlDate = new java.sql.Timestamp(cb0_date.getTime());

    public Parttwo() {

try {
            Poppins = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")).deriveFont(Font.PLAIN, 18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Regular.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(Color.white);

        // // // Crafting Table
        // // combo box
        String[] options = {"Attendance", "Student Table", "Teacher Table"};
        comboBox = new JComboBox<>(options);
        comboBox.addActionListener(this);
        comboBox.setFont(Poppins);
        comboBox.setPreferredSize(new Dimension(300, 30));

        // // buttons
        cb0_button = new JButton("Submit");
        cb0_button.addActionListener(this);
        cb0_button.setFont(Poppins);
        cb0_button.setBackground(new Color(255, 233, 77));
        cb0_button.setForeground(new Color(128, 0, 0));
        cb0_button.setFocusable(false);
        cb0_button.setPreferredSize(new Dimension(100, 30));

        // // labels
        cb0_labelTeacherNumber = new JLabel("Teacher Number", JLabel.CENTER);
        cb0_labelTeacherNumber.setFont(Poppins);
        cb0_labelFirstName = new JLabel("First Name", JLabel.CENTER);
        cb0_labelFirstName.setFont(Poppins);
        cb0_labelLastName = new JLabel("Last Name", JLabel.CENTER);
        cb0_labelLastName.setFont(Poppins);
        cb0_labelTemperature = new JLabel();
        cb0_labelTemperature.setHorizontalAlignment(SwingConstants.CENTER);
        cb0_labelTemperature.setFont(Poppins);

        cb0_labelTemperature.setFont(Poppins);

        // // text fields
        cb0_tfTeacherNumber = new JTextField();
        cb0_tfFirstName = new JTextField();
        cb0_tfLastName = new JTextField();

        cb0_tfTeacherNumber.setPreferredSize(new Dimension(350, 30));
        cb0_tfFirstName.setPreferredSize(new Dimension(350, 30));
        cb0_tfLastName.setPreferredSize(new Dimension(350, 30));

        // // radio button
        cb0_radioButtonIn = new JRadioButton("Check In");
        //cb0_radioButtonIn.setForeground(new Color(255, 255, 255));
        cb0_radioButtonIn.setFont(Poppins);
        cb0_radioButtonIn.setHorizontalAlignment(SwingConstants.CENTER);
        cb0_radioButtonIn.setFocusable(false);
        cb0_radioButtonIn.setOpaque(false);

        cb0_radioButtonOut = new JRadioButton("Check Out");
        //cb0_radioButtonOut.setForeground(new Color(255, 255, 255));
        cb0_radioButtonOut.setFont(Poppins);
        cb0_radioButtonOut.setHorizontalAlignment(SwingConstants.CENTER);
        cb0_radioButtonOut.setFocusable(false);
        cb0_radioButtonOut.setOpaque(false);

        ButtonGroup cb0_group = new ButtonGroup();
        cb0_group.add(cb0_radioButtonIn);
        cb0_group.add(cb0_radioButtonOut);

        cb0_radioButtonIn.setFont(Poppins);
        cb0_radioButtonOut.setFont(Poppins);

        cb0_radioButtonIn.setFocusable(false);
        cb0_radioButtonOut.setFocusable(false);

        // // Slider
        cb0_temperatureSlider = new JSlider(0, 500);
        cb0_temperatureSlider.setOpaque(false);
        cb0_temperatureSlider.setPaintTicks(true);
        cb0_temperatureSlider.setMinorTickSpacing(50);

        cb0_temperatureSlider.setPaintTrack(true);
        cb0_temperatureSlider.setMajorTickSpacing(100 / 10);

        cb0_temperatureSlider.setPaintLabels(true);
        cb0_temperatureSlider.setFont(Poppins);
        cb0_temperatureSlider.setPreferredSize(new Dimension(300, 50));
        cb0_labelTemperature.setText("\u00B0C = 25.0");
        cb0_temperatureSlider.addChangeListener(this);

        Hashtable<Integer, JLabel> labels = new Hashtable<>();
        labels.put(0, new JLabel("0"));
        labels.put(100, new JLabel("10"));
        labels.put(250, new JLabel("25"));
        labels.put(400, new JLabel("40"));
        labels.put(500, new JLabel("50"));
        cb0_temperatureSlider.setLabelTable(labels);

        // // panels

        // cb0
        cb0_subPanel1 = new JPanel();
        cb0_subPanel2 = new JPanel();
        cb0_subPanel3 = new JPanel();

        cb0_subPanel1.setPreferredSize(new Dimension(115, 250));
        cb0_subPanel2.setPreferredSize(new Dimension(385, 250));
        cb0_subPanel3.setPreferredSize(new Dimension(500, 250));

        cb0_subPanel1.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));
        cb0_subPanel2.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 30));
        cb0_subPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 10));

        // cb1
        cb1_subPanel1 = new JPanel();

        cb1_subPanel1.setPreferredSize(new Dimension(500, 500));

        cb1_subPanel1.setBackground(Color.cyan);

        cb1_subPanel1.setLayout(new FlowLayout());

        blank1 = new JLabel();
        blank2 = new JLabel();
        blank3 = new JLabel();
        blank4 = new JLabel();
        blank5 = new JLabel();
        blank6 = new JLabel();
        blank7 = new JLabel();
        blank8 = new JLabel();
        blank9 = new JLabel();
        blank10 = new JLabel();
        blank11 = new JLabel();

        // // // Panels
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));

        panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        panel3 = new JPanel();
        panel3.setBackground(Color.green);
        panel3.setLayout(new BorderLayout());

        this.setSize(1000, 500);
        panel1.add(comboBox);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);
        this.add(panel3, BorderLayout.EAST);

        this.getContentPane().setBackground(Color.white);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // combobox selection
        if (e.getSource() == comboBox) {
            switch (comboBox.getSelectedIndex()) {
                case 0:
                    panel2.removeAll();
                    panel3.removeAll();
                
                    System.out.println(comboBox.getSelectedItem());
                
                    // Set the preferred size of panel2
                    panel2.setPreferredSize(new Dimension(1000, 400));
                
                    // Set the layout manager for panel2 as BorderLayout
                    panel2.setLayout(new BorderLayout());
                
                    // Create sub-panels for each section
                    cb0_subPanel1 = new JPanel();
                    cb0_subPanel2 = new JPanel();
                    cb0_subPanel3 = new JPanel();
                
                    // Set the layout manager for cb0_subPanel1 as GridLayout with 3 rows and 1 column
                    cb0_subPanel1.setLayout(new GridLayout(7, 1));
                
                    // Set the layout manager for cb0_subPanel2 as GridLayout with 3 rows and 1 column
                    cb0_subPanel2.setLayout(new GridLayout(7, 1));
                
                    // Set the layout manager for cb0_subPanel3 as FlowLayout with centered alignment
                    cb0_subPanel3.setLayout(new GridLayout(7, 1));
                
                    // Set the preferred sizes of the sub-panels
                    cb0_subPanel1.setPreferredSize(new Dimension(170, 200));
                    cb0_subPanel2.setPreferredSize(new Dimension(330, 200));
                    cb0_subPanel3.setPreferredSize(new Dimension(500, 200));
                
                    // Add components to cb0_subPanel1
                    cb0_subPanel1.add(blank1);
                    cb0_subPanel1.add(cb0_labelTeacherNumber);
                    cb0_subPanel1.add(blank2);
                    cb0_subPanel1.add(cb0_labelFirstName);
                    cb0_subPanel1.add(blank3);
                    cb0_subPanel1.add(cb0_labelLastName);
                    cb0_subPanel1.add(blank4);
                
                    // Add components to cb0_subPanel2
                    cb0_subPanel2.add(blank5);
                    cb0_subPanel2.add(cb0_tfTeacherNumber);
                    cb0_subPanel2.add(blank6);
                    cb0_subPanel2.add(cb0_tfFirstName);
                    cb0_subPanel2.add(blank7);
                    cb0_subPanel2.add(cb0_tfLastName);
                    cb0_subPanel2.add(blank8);
                
                    // Add components to cb0_subPanel3
                    cb0_subPanel3.add(blank9);
                    cb0_subPanel3.add(cb0_labelTemperature);
                    cb0_subPanel3.add(cb0_temperatureSlider);
                    cb0_subPanel3.add(cb0_radioButtonIn);
                    cb0_subPanel3.add(cb0_radioButtonOut);
                    cb0_subPanel3.add(cb0_button);
                
                    // Add sub-panels to panel2
                    panel2.add(cb0_subPanel1, BorderLayout.WEST);
                    panel2.add(cb0_subPanel2, BorderLayout.CENTER);
                    panel2.add(cb0_subPanel3, BorderLayout.EAST);

                    // Add sub-panels to panel2
                    panel2.add(cb0_subPanel1, BorderLayout.WEST);
                    panel2.add(cb0_subPanel2, BorderLayout.CENTER);
                    panel2.add(cb0_subPanel3, BorderLayout.EAST);
                
                    this.repaint();
                    this.revalidate();
                    break;
                case 1:
                    panel2.removeAll();
                    panel3.removeAll();

                    student_table();
                    System.out.println(comboBox.getSelectedItem());
                    panel2.add(cb1_subPanel1);
                    JScrollPane studentScrollPane = new JScrollPane(cb1_table);
                    studentScrollPane.setPreferredSize(new Dimension(984, 400)); // Adjust the size as needed
                    panel3.add(studentScrollPane);
    
                    this.repaint();
                    this.revalidate();
                    break;
                case 2:
                    panel2.removeAll();
                    panel3.removeAll();

                    teacher_table();
                    System.out.println(comboBox.getSelectedItem());
                    JScrollPane teacherScrollPane = new JScrollPane(cb2_table);
                    teacherScrollPane.setPreferredSize(new Dimension(984, 400)); // Adjust the size as needed
                    panel3.add(teacherScrollPane);
    
                    this.repaint();
                    this.revalidate();
                    break;
            }
        }
    
        // buttons
        else if (e.getSource() == cb0_button) {
            cb0_sqlarr[0] = cb0_tfTeacherNumber.getText();
            cb0_sqlarr[1] = cb0_tfFirstName.getText();
            cb0_sqlarr[2] = cb0_tfLastName.getText();
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
        cb0_labelTemperature.setText("\u00B0C = " + ((float) cb0_temperatureSlider.getValue()/10));
    }

    public void student_table() {
        String[] cb1_columns = {"Session ID", "Student No.", "First Name", "Last Name", "Year & Section", "DateTime In", "DateTime Out", "Temperature"};
        Object[][] cb1_rows = new Object[50][10];
        int cb1_i = 0;

        cb1_table = new JTable(cb1_rows, cb1_columns);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM attendance_table");
            while (resultSet.next()) {
                cb1_rows[cb1_i][0] = resultSet.getInt(1);
                cb1_rows[cb1_i][1] = resultSet.getString(2);
                cb1_rows[cb1_i][2] = resultSet.getString(3);
                cb1_rows[cb1_i][3] = resultSet.getString(4);
                cb1_rows[cb1_i][4] = resultSet.getString(5);
                cb1_rows[cb1_i][5] = resultSet.getString(6);
                cb1_rows[cb1_i][6] = resultSet.getString(7);
                cb1_rows[cb1_i][7] = resultSet.getFloat(8);
                cb1_i++;
                cb1_table = new JTable(cb1_rows, cb1_columns);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void teacher_table() {
        String[] cb2_columns = {"Session ID", "Teacher No.", "First Name", "Last Name", "DateTime In", "DateTime Out", "Temperature"};
        Object[][] cb2_rows = new Object[50][10];
        int cb2_i = 0;

        cb2_table = new JTable(cb2_rows, cb2_columns);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = connect();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM teacher_attendance_table");
            while (resultSet.next()) {
                cb2_rows[cb2_i][0] = resultSet.getInt(1);
                cb2_rows[cb2_i][1] = resultSet.getString(2);
                cb2_rows[cb2_i][2] = resultSet.getString(3);
                cb2_rows[cb2_i][3] = resultSet.getString(4);
                cb2_rows[cb2_i][4] = resultSet.getString(5);
                cb2_rows[cb2_i][5] = resultSet.getString(6);
                cb2_rows[cb2_i][6] = resultSet.getFloat(7);
                cb2_i++;
                cb2_table = new JTable(cb2_rows, cb2_columns);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
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
            String SQL = "INSERT INTO teacher_attendance_table(TeacherNumber, FirstName, LastName, CheckInDateTime, Temperature)" + " VALUES(?,?,?,?,?)";
            Connection connection = connect();
            PreparedStatement preparedstmt = connection.prepareStatement(SQL);

            int cb0_insertInfoConfirm = JOptionPane.showConfirmDialog(null, "Confirm Attendance?", "Attendance Confirmation", JOptionPane.YES_NO_OPTION);
            if (cb0_insertInfoConfirm == 0) {
                preparedstmt.setString(1, cb0_sql[0]);
                preparedstmt.setString(2, cb0_sql[1]);
                preparedstmt.setString(3, cb0_sql[2]);
                preparedstmt.setTimestamp(4, cb0_sqlDate);
                preparedstmt.setFloat(5, cb0_sql_temperature);

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
            String SQL = "UPDATE teacher_attendance_table SET CheckOutDateTime = ? WHERE TeacherNumber = ? AND CheckOutDateTime IS NULL";
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
       
}