import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Receptionist {
    JPanel Recept;
    public JButton btnnewapp;
    private JButton btnlogout;
    private JButton btnSearch;
    private JTextField txtId;
    private JButton btnSearchAll;
    private JTable tblApp;
    private JLabel lbldashboard;
    private JScrollPane tblapp;
    private JButton paymentButton;



    Connection conn = MySQLConnection.connect();
    PreparedStatement pst;
    String ID;

    public Receptionist() {

        btnnewapp.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("New Appointment");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Appointment().Appointments,"Recept.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });


            }
        });
        btnlogout.addActionListener(new ActionListener() {
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
//        paymentButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//                JFrame rf = new JFrame("Receptionist");
//                rf.setVisible(false);
//                rf.dispose();
//
//                JFrame frame = new JFrame("Appointment");
//                frame.setContentPane(new DirectPay().DirectPay);
//                frame.pack();
//                frame.setVisible(true);
//
//
//            }
//        });
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    ResultSet rs1;

                    ID = txtId.getText();

                    pst = conn.prepareStatement("select * from mmsdb.appointments where ID = ?");


                    pst.setString(1, ID);
                    rs1 = pst.executeQuery();


                    if (rs1.next() == true)
                    {
                        rs1 = pst.executeQuery();
                        tblApp.setModel(DbUtils.resultSetToTableModel(rs1));
                        txtId.setText("");
                        txtId.requestFocus();


                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid ID number!");
                        txtId.setText("");
                        txtId.requestFocus();

                    }

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }



            }
        });


        btnSearchAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    ResultSet rs1;

                    pst = conn.prepareStatement("select * from mmsdb.appointments");



                    rs1 = pst.executeQuery();


                    if (rs1.next() == true)
                    {
                        rs1 = pst.executeQuery();
                        tblApp.setModel(DbUtils.resultSetToTableModel(rs1));
                        txtId.setText("");
                        txtId.requestFocus();

                    }


                    else
                    {
                        JOptionPane.showMessageDialog(null, "No Data to Show!");
                        txtId.setText("");
                        txtId.requestFocus();

                    }



                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }


            }
        });




    }
}
