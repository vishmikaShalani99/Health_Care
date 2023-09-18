import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Appointment {
    private JButton printButton;
    private JButton addButton;
    private JButton paymentButton;
    private JTextField txtpid;
    private JTextField txtdid;
    private JTextField txtdname;
    private JTextField txtcd;
    private JTextField txttime;
    private JTextField txtad;
    public JPanel Appointments;
    private JTextField txtid;
    private JComboBox combotype;
    private JTextField txtPatName;
    private JButton backButton;
    private JButton popupButton;
    private JButton selectButton;
    private JLabel lbldashboard;

    Connection conn = MySQLConnection.connect();
    private static final String COMMIT_ACTION = "commit";

    public Appointment() {

        JFrame fa = new JFrame();
        fa.setVisible(true);
        fa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                try {
                    Statement st;
                    st = conn.createStatement();
                    String q = "select (ifnull(MAX(ID),0) +1) as NextAppointmentNo from mmsdb.appointments";

                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        txtid.setText(String.valueOf(rs.getInt("NextAppointmentNo")));
                    }

                    txtPatName.setFocusTraversalKeysEnabled(false);
                    // Our words to complete
                    List<String> p_keywords = new ArrayList<String>(25);
//                    List<String> test =  new ArrayList<>();
                    String q1 = "SELECT Name FROM mmsdb.patient";
                    ResultSet rs1 = st.executeQuery(q1);
                    while (rs1.next()) {
                        String tt = String.valueOf(rs1.getString("Name"));
                        p_keywords.add(tt);
                    }


                    //Patient Autocomplete
                    Autocomplete p_autoComplete = new Autocomplete(txtPatName,p_keywords);
                    txtPatName.getDocument().addDocumentListener(p_autoComplete);

                    txtPatName.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
                    txtPatName.getActionMap().put(COMMIT_ACTION, p_autoComplete.new CommitAction());



                    txtdname.setFocusTraversalKeysEnabled(false);
                    // Our words to complete
                    List<String> d_keywords = new ArrayList<String>(25);
//                    List<String> test =  new ArrayList<>();
                    String q2 = "SELECT Name FROM mmsdb.doctor";
                    ResultSet rs2 = st.executeQuery(q2);
                    while (rs2.next()) {
                        String tt = String.valueOf(rs2.getString("Name"));
                        d_keywords.add(tt);
                    }

                    //Doctor Autocomplete
                    Autocomplete d_autoComplete = new Autocomplete(txtdname, d_keywords);
                    txtdname.getDocument().addDocumentListener(d_autoComplete);

                    txtdname.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT_ACTION);
                    txtdname.getActionMap().put(COMMIT_ACTION, d_autoComplete.new CommitAction());



                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                popupButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) {
                        txtcd.setText(new DatePicker(fa).setPickedDate());
                    }
                });

                selectButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent ae) { txtad.setText(new DatePicker(fa).setPickedDate()); }
                });



            }

            @Override
            public void windowClosing(WindowEvent e) {



            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });




        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String app_id,app_pat_name,app_pat_id,app_doc_name,app_doc_id,app_credate,app_appdate,app_time,app_type;



                app_id = txtid.getText();
                app_pat_name = txtPatName.getText();
                app_pat_id = txtpid.getText();
                app_doc_name = txtdname.getText();
                app_doc_id = txtdid.getText();
                app_credate = txtcd.getText();
                app_appdate = txtad.getText();
                app_time =txttime.getText();
                app_type = combotype.getSelectedItem().toString();



                try{


                   String sql = "INSERT INTO appointments( Id, Patient_Name , Patient_ID , Doctor_Name , Doctor_ID , Created_Date , Appointment_Date , Time , Type ) Values(?,?,?,?,?,?,?,?,?)";


                    PreparedStatement pst = conn.prepareStatement(sql);

                    pst.setString(1, app_id);
                    pst.setString(2, app_pat_name);
                    pst.setString(3, app_pat_id);
                    pst.setString(4, app_doc_name);
                    pst.setString(5, app_doc_id);
                    pst.setString(6, app_credate);
                    pst.setString(7, app_appdate);
                    pst.setString(8, app_time);
                    pst.setString(9, app_type);

                    pst.executeUpdate();
                  JOptionPane.showMessageDialog(null,"Record Added Successfully");

                    txtid.setText("");
                    txtPatName.setText("");
                    txtpid.setText("");
                    txtdname.setText("");
                    txtdid.setText("");
                    txtcd.setText("");
                    txtad.setText("");
                    txttime.setText("");

                    txtPatName.requestFocus();
                }





                catch (SQLException e1) {

                    e1.printStackTrace();
            }

        }
    });

        paymentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }

                        JFrame frame = new JFrame("Payment");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Payments().Payments,"Recept.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });

            }
        });


        txtPatName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                try{
                    Statement st;
                    st = conn.createStatement();

                    String query = "SELECT Id FROM mmsdb.patient where name = '"+txtPatName.getText().trim()+"'";

                    ResultSet resultSet = st.executeQuery(query);

                    while (resultSet.next()) {
                        txtpid.setText(String.valueOf(resultSet.getInt("Id")));
                    }


                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);

                }


            }
        });

        txtdname.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {

                super.focusLost(e);


                try{
                    Statement st;
                    st = conn.createStatement();

                    String query = "SELECT Id FROM mmsdb.doctor where name = '"+txtdname.getText().trim()+"'";

                    ResultSet resultSet = st.executeQuery(query);

                    while (resultSet.next()) {
                        txtdid.setText(String.valueOf(resultSet.getInt("Id")));
                    }


                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);

                }




            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                            ex.printStackTrace();
                        }

                        JFrame frame = new JFrame("Receptionist - Dashboard");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Receptionist().Recept,"Recept.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });


            }
        });


    }

}

