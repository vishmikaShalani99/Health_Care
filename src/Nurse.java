import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Nurse {
    public JButton btnAdd;
    public JPanel Nurse;
    private JButton btnSearch;
    private JButton btnEdit;
    private JButton btnDelete;
    private JTextField txtid;
    private JLabel lblPID;
    private JTable tblLoad;
    private JButton btnLogout;
    private JLabel lbldashboard;


    PreparedStatement pst;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Nurse");
        frame.setContentPane(new Nurse().Nurse);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
          frame.setVisible(true);
        frame.setBounds(100, 100, 500, 500);
    }



    Connection conn = MySQLConnection.connect();

    void table_load(){
        try{
            String pat_id = txtid.getText();
            pst = conn.prepareStatement("select * from patient where Id = ?");
            pst.setString(1, pat_id);
            ResultSet rs = pst.executeQuery();
            tblLoad.setModel(DbUtils.resultSetToTableModel(rs));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void All_Details(){
        try{
            String pat_id = txtid.getText();
            pst = conn.prepareStatement("select * from patient");
            ResultSet rs = pst.executeQuery();
            tblLoad.setModel(DbUtils.resultSetToTableModel(rs));
        }

        catch (SQLException e) {
            e.printStackTrace();
        }

    }




    //TO Register a new patient
    public Nurse() {
        btnAdd.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Nurse - Patient Registration");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new PatientRegister().Patient_Registation,"Nurse.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });


            }
        });





        //To Search patient details
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {



                try{
                    String pat_id = txtid.getText();

                    pst = conn.prepareStatement("select * from patient where Id = ?");
                    pst.setString(1, pat_id);
                    ResultSet rs = pst.executeQuery();

                    if(rs.next()==true)
                    {
                        table_load();
                        txtid.setText("");
                        txtid.requestFocus();

                    }

                    else
                    {
                        All_Details();
                    }


                }


                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });





        //To edit or update patient details
        btnEdit.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Nurse - Edit Patient Details");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new edit_patient().editpatient,"Nurse.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });




            }
        });




        //To delete patient details
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pat_id = txtid.getText();

                try{
                    pst = conn.prepareStatement("delete from patient where id = ?");

                    pst.setString(1,pat_id);
                    pst.executeUpdate();

                    All_Details();

                    JOptionPane.showMessageDialog(null, "Record Deleted!");

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }




            }
        });




        //To Logout from the system
        btnLogout.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Login");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Login().MainLogin,"Login.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });
            }
        });





    }


}



