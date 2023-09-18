import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class PatientRegister {
    public JPanel Patient_Registation;
    private JLabel lblPatientID;
    private JLabel lblGender;
    private JLabel lblBloodGroup;
    public  JTextField txtID;
    private JLabel lblPatientName;
    public JTextField txtAge;
    private JLabel lblMobile;
    public JComboBox cmbxBlood;
    public JTextField txtMobile;
    private JLabel lblAge;
    public JTextField txtName;
    private JLabel lblEmail;
    public JTextField txtEmail;
    private JLabel lblAddress;
    public JTextField txtAddress;
    private JButton btnSave;
    public JComboBox cmbxGender;
    private JButton btnBack;
    private JLabel lbldashboard;

    public static void main(String[] args) {
        JFrame frame = new JFrame("PatientRegister");
        frame.setContentPane(new PatientRegister().Patient_Registation);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100, 100, 500, 500);
    }

    PreparedStatement pst;

    Connection conn = MySQLConnection.connect();



    //To auto increment the patient ID
    void Increment_ID(){

        try {
            Statement st;
            st = conn.createStatement();
            String q = "select (ifnull(MAX(Id),0) +1) as NextpatientNo from mmsdb.patient";

            ResultSet rs = st.executeQuery(q);
            while (rs.next()) {
                txtID.setText(String.valueOf(rs.getInt("NextpatientNo")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }




    public PatientRegister() {

        JFrame fa = new JFrame();
        fa.setVisible(true);

        fa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                Increment_ID();

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



        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pat_name, pat_gender, pat_age, pat_bloodgroup, pat_mobile, pat_email, pat_address;


                pat_name = txtName.getText();
                pat_age = txtAge.getText();
                pat_gender = cmbxGender.getSelectedItem().toString();
                pat_bloodgroup = cmbxBlood.getSelectedItem().toString();
                pat_mobile = txtMobile.getText();
                pat_address = txtAddress.getText();
                pat_email = txtEmail.getText();

                try {
                    String sql = "INSERT INTO patient(Name, Gender, Age, Blood_Group, Mobile_Number, Email, Address) values(?,?,?,?,?,?,?)";

                    pst = conn.prepareStatement(sql);


                    pst.setString(2, pat_gender);
                    pst.setString(3, pat_age);
                    pst.setString(4, pat_bloodgroup);
                    pst.setString(5, pat_mobile);
                    pst.setString(6, pat_email);
                    pst.setString(7, pat_address);

                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added!");

                    Increment_ID();

                    txtName.setText("");
                    txtAge.setText("");
                    txtAddress.setText("");
                    txtEmail.setText("");
                    //txtID.setText("");
                    txtMobile.setText("");
                    cmbxGender.setSelectedItem("");
                    cmbxBlood.setSelectedItem("");

                    txtName.requestFocus();



                } catch (SQLException e1) {
                    e1.printStackTrace();
                }



            }
        });



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

                        JFrame frame = new JFrame("Nurse - Dashboard");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Nurse().Nurse,"Nurse.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });

            }
        });
    }



    }







