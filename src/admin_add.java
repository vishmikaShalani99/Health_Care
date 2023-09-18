import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.*;

public class admin_add {
    private JLabel lblid;
    public JTextField txtid;
    private JLabel Name;
    private JTextField txtname;
    private JLabel lblgender;
    private JComboBox cmbxgender;
    private JLabel lblspecial;
    private JTextField txtspecial;
    private JTextField txtfee;
    private JLabel lblfee;
    private JLabel lblmobile;
    private JTextField txtmobile;
    private JLabel lblemail;
    private JTextField txtemail;
    private JLabel lbladdress;
    private JTextField txtaddress;
    private JTextField txtusername;
    private JLabel lblpassword;
    private JTextField txtpassword;
    private JButton btnsave;
    private JButton btnback;
    public JPanel adminadd;
    public JComboBox cmbxtype;
    private JLabel lbltype;
    private JTextField txtduty;
    private JLabel lblduty;
    private JLabel lbldashboard;
    private JButton btnedit;


    Connection conn = MySQLConnection.connect();
    PreparedStatement pst;

    String ID, usertype, tablename;
    Statement st;
    String q, query1;
    ResultSet rs;



    public void Increment(){
        try {

            if (usertype == "Nurse") {
                txtfee.setEnabled(false);
                txtspecial.setEnabled(false);
                txtduty.setEnabled(true);

                st = conn.createStatement();
                q = "select (ifnull(MAX(Id),0) +1) as NextNo from mmsdb.nurse";

                rs = st.executeQuery(q);
                while (rs.next()) {
                    txtid.setText(String.valueOf(rs.getInt("NextNo")));
                }
            }

            else if (usertype == "Doctor") {
                txtduty.setEnabled(false);
                txtfee.setEnabled(true);
                txtspecial.setEnabled(true);

                st = conn.createStatement();
                q = "select (ifnull(MAX(Id),0) +1) as NextNo from mmsdb.doctor";

                rs = st.executeQuery(q);
                while (rs.next()) {
                    txtid.setText(String.valueOf(rs.getInt("NextNo")));
                }
            }

            else {
                txtfee.setEnabled(false);
                txtspecial.setEnabled(false);
                txtduty.setEnabled(true);

                st = conn.createStatement();
                q = "select (ifnull(MAX(Id),0) +1) as NextNo from mmsdb.receptionist";

                rs = st.executeQuery(q);
                while (rs.next()) {
                    txtid.setText(String.valueOf(rs.getInt("NextNo")));
                }

            }
        }

        catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


    public admin_add() {

        JFrame fa = new JFrame();
        fa.setVisible(true);

        fa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                usertype = cmbxtype.getSelectedItem().toString();
                Increment();
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




        cmbxtype.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                cmbxtype.setSelectedItem(toString());
                usertype = cmbxtype.getSelectedItem().toString();

                    if (usertype == "Receptionist") {
                        Increment();
                    } else if (usertype == "Doctor") {
                        Increment();
                    } else if (usertype == "Nurse") {
                        Increment();
                    }



            }

        });



        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String name, gender,fee, specialization, mobile, email, address, duty, username, password;


                name = txtname.getText();
                gender = cmbxgender.getSelectedItem().toString();
                specialization = txtspecial.getText();
                fee = txtfee.getText();
                duty = txtduty.getText();
                mobile = txtmobile.getText();
                email = txtemail.getText();
                address = txtaddress.getText();
                username = txtusername.getText();
                password = txtpassword.getText();

                try {
                    if (usertype == "Nurse" || usertype == "Receptionist") {
                        if (usertype == "Nurse") {
                            tablename = "nurse";
                        } else {
                            tablename = "receptionist";
                        }

                        String sql = "INSERT INTO $name(Name, Gender, Duty_Hours, Mobile_Number, Email, Address, Username, Password) values(?,?,?,?,?,?,?,?)";

                        pst = conn.prepareStatement(sql);

                        String query1 = sql.replace("$name", tablename);
                        pst = conn.prepareStatement(query1);

                        pst.setString(1, name);
                        pst.setString(2, gender);
                        pst.setString(3, duty);
                        pst.setString(4, mobile);
                        pst.setString(5, address);
                        pst.setString(6, email);
                        pst.setString(7, username);
                        pst.setString(8, password);

                        pst.executeUpdate();

                    } else {

                        String sql = "INSERT INTO $name(Name, Gender, Specialization, Fee, Mobile_Number, Email, Address, Username, Password) values(?,?,?,?,?,?,?,?,?)";

                        pst = conn.prepareStatement(sql);

                        tablename = "doctor";
                        String query1 = sql.replace("$name", tablename);
                        pst = conn.prepareStatement(query1);

                        pst.setString(1, name);
                        pst.setString(2, gender);
                        pst.setString(3, specialization);
                        pst.setString(4, fee);
                        pst.setString(5, mobile);
                        pst.setString(6, address);
                        pst.setString(7, email);
                        pst.setString(8, username);
                        pst.setString(9, password);

                        pst.executeUpdate();

                    }


                    JOptionPane.showMessageDialog(null, "Record Added!");


                    txtname.setText("");
                    cmbxgender.setSelectedItem("");
                    txtduty.setText("");
                    txtspecial.setText("");
                    txtfee.setText("");
                    txtmobile.setText("");
                    txtemail.setText("");
                    txtaddress.setText("");
                    txtusername.setText("");
                    txtpassword.setText("");

                    txtname.requestFocus();

                    Increment();

                }

                catch (SQLException e1) {
                    e1.printStackTrace();
                }




            }
        });


        btnback.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Administrator");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Administrator().Admin,"Admin.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });

            }
        });


        txtid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{

                    if (usertype == "Nurse" || usertype == "Receptionist") {
                        txtfee.setEnabled(false);
                        txtspecial.setEnabled(false);
                        txtduty.setEnabled(true);

                        if (usertype == "Nurse") {
                            tablename = "nurse";
                        } else {
                            tablename = "receptionist";
                        }

                        ID = txtname.getText();


                        String query = "select * from $name where  ID = ?";
                        String query1 = query.replace("$name", tablename);
                        pst = conn.prepareStatement(query1);


                    pst.setString(1, ID);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String name = rs.getString(2);
                        String gender = rs.getString(3);
                        String duty = rs.getString(4);
                        String mobile = rs.getString(5);
                        String email = rs.getString(6);
                        String address = rs.getString(7);
                        String username = rs.getString(8);
                        String password = rs.getString(9);

                        txtid.setText("");
                        txtname.setText("");
                        cmbxgender.setSelectedItem("");
                        txtduty.setText("");
                        txtspecial.setText("");
                        txtfee.setText("");
                        txtmobile.setText("");
                        txtemail.setText("");
                        txtaddress.setText("");
                        txtusername.setText("");
                        txtpassword.setText("");

                        txtname.requestFocus();


                    }

                    else{
                        txtid.setText("");
                        txtname.setText("");
                        cmbxgender.setSelectedItem("");
                        txtduty.setText("");
                        txtspecial.setText("");
                        txtfee.setText("");
                        txtmobile.setText("");
                        txtemail.setText("");
                        txtaddress.setText("");
                        txtusername.setText("");
                        txtpassword.setText("");

                        txtname.requestFocus();


                        JOptionPane.showMessageDialog(null, "Invalid patient name!");

                    }
                }

            }

                catch (SQLException e2) {
                    e2.printStackTrace();
                }
            }

        });
    }
}
