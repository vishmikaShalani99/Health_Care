import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SearchPatient {
    public JPanel searchPatients;
    private JTextField txtPat_name;
    private JButton btnSearch;
    private JButton btnBack;
    private JButton btnLogout;
    private JTextField txtage;
    private JTextField txtblood;
    private JTextField txtsymp;
    private JTextField txtmobile;
    private JTextField txtemail;
    private JTextField txtaddress;
    private JTextField txtrecord;
    private JTextField txtId;
    private JButton btnupdate;
    private JTextField txtgender;
    private JLabel lbldashboard;

    Connection conn = MySQLConnection.connect();    //MySQLConnection connection = new MySQLConnection();
    PreparedStatement pst;
    ResultSet rs;

    private static final String COMMIT_ACTION = "commit";

    public SearchPatient() {

        JFrame fa = new JFrame();
        fa.setVisible(true);
        fa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                try {
                    Statement st;
                    st = conn.createStatement();

                    txtPat_name.setFocusTraversalKeysEnabled(false);
                    // Our words to complete
                    List<String> keywords = new ArrayList<String>(25);
//                    List<String> test =  new ArrayList<>();
                    String q1 = "SELECT Name FROM mmsdb.patient";
                    ResultSet rs1 = st.executeQuery(q1);
                    while (rs1.next()) {
                        String tt = String.valueOf(rs1.getString("Name"));
                        keywords.add(tt);
                    }



                    Autocomplete autoComplete = new Autocomplete(txtPat_name, keywords);

                    txtPat_name.getDocument().addDocumentListener(autoComplete);


                    txtPat_name.getInputMap().put(KeyStroke.getKeyStroke("TAB"),COMMIT_ACTION);
                    txtPat_name.getActionMap().put(COMMIT_ACTION, autoComplete.new CommitAction());


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

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




        //to check patients details after giving patient ID
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    pst = conn.prepareStatement("select * from patient where Name=?");
                    String Name = String.valueOf(txtPat_name.getText());
                    pst.setString(1, Name);
                    rs = pst.executeQuery();

                    if (rs.next() == false) {

                        JOptionPane.showMessageDialog(null,"Invalid Patient Name");
                        txtId.setText("");
                        txtage.setText("");
                        txtgender.setText("");
                        txtblood.setText("");
                        txtsymp.setText("");
                        txtmobile.setText("");
                        txtemail.setText("");
                        txtaddress.setText("");
                        txtrecord.setText("");

                    } else {
                        txtId.setText(rs.getString("Id"));
                        txtage.setText(rs.getString("Age"));
                        txtgender.setText(rs.getString("Gender"));
                        txtblood.setText(rs.getString("Blood_group"));
                        txtsymp.setText(rs.getString("Symptoms"));
                        txtmobile.setText(rs.getString("Mobile_Number"));
                        txtemail.setText(rs.getString("Email"));
                        txtaddress.setText(rs.getString("Address"));
                        txtrecord.setText(rs.getString("Doctor_Record"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        //After clicking back button , then open DoctorSearch form
        btnBack.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Doctor - Dashboard");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Doctor().DoctorSearch,"Doctor.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });


            }
        });


        //After changing patients' data update the details in database
        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pat_id, pat_name, pat_gender, pat_age, pat_bloodgroup, pat_mobile, pat_email, pat_address;

                pat_id = txtPat_name.getText();
                pat_name = txtId.getText();
                pat_age = txtage.getText();
                pat_gender = txtgender.getText();
                pat_bloodgroup = txtblood.getText();
                pat_mobile = txtmobile.getText();
                pat_address = txtaddress.getText();
                pat_email = txtemail.getText();

                try {

                    pst = conn.prepareStatement("update patient set ID = ?, Gender = ?, Age = ?, Blood_Group = ?, Mobile_Number = ?, Email = ?, Address = ? where Name = ?");

                    pst.setString(1, pat_id);
                    pst.setString(2, pat_gender);
                    pst.setString(3, pat_age);
                    pst.setString(4, pat_bloodgroup);
                    pst.setString(5, pat_mobile);
                    pst.setString(6, pat_email);
                    pst.setString(7, pat_address);
                    pst.setString(8,pat_name);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Updated!");

                    txtId.setText("");
                    txtage.setText("");
                    txtaddress.setText("");
                    txtemail.setText("");
                    txtPat_name.setText("");
                    txtmobile.setText("");
                    txtgender.setText("");
                    txtblood.setText("");

                    txtId.requestFocus();
                }
                catch (SQLException es) {
                    es.printStackTrace();
                }
            }
        });
}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Search Patients");
        frame.setContentPane(new SearchPatient().searchPatients);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100,100,500,500);
    }
}


