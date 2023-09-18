import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class edit_patient {
    private JLabel lblid;
    public JPanel editpatient;
    private JTextField txtid;
    private JTextField txtname;
    private JLabel lblname;
    private JTextField txtage;
    private JLabel lblage;
    private JLabel lblbloodgroup;
    private JTextField txtblood;
    private JLabel lblgender;
    private JTextField txtgender;
    private JTextField txtmobile;
    private JLabel lblmobile;
    private JLabel lblemail;
    private JTextField txtemail;
    private JLabel lbladdress;
    private JTextField txtaddress;
    private JButton btnupdate;
    private JButton btnback;
    private JButton btnsearch;
    private JTable table1;
    private JLabel lbldashboard;


    Connection conn = MySQLConnection.connect();
    PreparedStatement pst;


    public static void main(String[] args) {
        JFrame frame = new JFrame("edit_patient");
        frame.setContentPane(new edit_patient().editpatient);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }


    void TableLoad(){

        String pat_name = txtname.getText();
        try {
            pst = conn.prepareStatement("select * from patient where  Name = ?");
            pst.setString(1, pat_name);
            ResultSet rs1 = pst.executeQuery();
            table1.setModel(DbUtils.resultSetToTableModel(rs1));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public edit_patient() {
        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    String pat_name = txtname.getText();


                    pst = conn.prepareStatement("select * from patient where  Name = ?");
                    pst.setString(1, pat_name);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        String pat_id = rs.getString(1);
                        String pat_gender = rs.getString(3);
                        String pat_age = rs.getString(4);
                        String pat_bloodgroup = rs.getString(5);
                        String pat_mobile = rs.getString(6);
                        String pat_email = rs.getString(7);
                        String pat_address = rs.getString(8);

                        txtid.setText(pat_id);
                        txtage.setText(pat_age);
                        txtaddress.setText(pat_address);
                        txtemail.setText(pat_email);
                        txtmobile.setText(pat_mobile);
                        txtgender.setText(pat_gender);
                        txtblood.setText(pat_bloodgroup);

                        txtname.requestFocus();


                        TableLoad();


                    }

                    else{
                        txtid.setText("");
                        txtage.setText("");
                        txtaddress.setText("");
                        txtemail.setText("");
                        txtmobile.setText("");
                        txtgender.setText("");
                        txtblood.setText("");

                        txtname.requestFocus();


                        JOptionPane.showMessageDialog(null, "Invalid patient name!");

                    }
                }

                catch (SQLException es) {
                    es.printStackTrace();
                }
            }
        });



        btnupdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pat_id, pat_name, pat_gender, pat_age, pat_bloodgroup, pat_mobile, pat_email, pat_address;




                pat_id = txtid.getText();
                pat_name = txtname.getText();
                pat_age = txtage.getText();
                pat_gender = txtgender.getText();
                pat_bloodgroup = txtblood.getText();
                pat_mobile = txtmobile.getText();
                pat_address = txtaddress.getText();
                pat_email = txtemail.getText();

                try {
                    String sql = "update patient set ID = ?, Gender = ?, Age = ?, Blood_Group = ?, Mobile_Number = ?, Email = ?, Address = ? where Name = ?";

                    pst = conn.prepareStatement(sql);


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

                   TableLoad();


                    txtname.setText("");
                    txtage.setText("");
                    txtaddress.setText("");
                    txtemail.setText("");
                    txtid.setText("");
                    txtmobile.setText("");
                    txtgender.setText("");
                    txtblood.setText("");

                    txtname.requestFocus();

                }

                catch (SQLException es) {
                    es.printStackTrace();
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



