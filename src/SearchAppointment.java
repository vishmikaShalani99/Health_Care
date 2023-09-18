import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SearchAppointment {
    public JPanel SearchAppointments;
    private JButton btnBack;
    private JButton btnLogout;
    private JTextField txtDocId;
    private JButton btnSearchApp;
    private JTable table1;
    private JLabel lbldashboard;

    Connection conn = MySQLConnection.connect();    //MySQLConnection connection = new MySQLConnection();
    PreparedStatement pst;

    // method for loading table to show appointments details
    void table_load() {

        try {
            String doc_id=txtDocId.getText();
            pst =conn.prepareStatement("select * from mmsdb.appointments where Doctor_Id = ? ");
            pst.setString(1,doc_id);
            ResultSet rs= pst.executeQuery();

            table1.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SearchAppointment() {

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

        //After clicking search button , then find relevant doctor IDs' appointment details.
        btnSearchApp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    String doc_id=txtDocId.getText();
                    pst =conn.prepareStatement("select * from mmsdb.appointments where Doctor_Id = ? ");
                    pst.setString(1,doc_id);
                    ResultSet rs= pst.executeQuery();
                    if (rs.next()==true)
                    {
                        table_load();
                        txtDocId.setText("");
                        txtDocId.requestFocus();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Invalid ID number");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }


        public static void main (String[]args) {
            JFrame frame = new JFrame("Appointments Search");
            frame.setContentPane(new SearchAppointment().SearchAppointments);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            frame.setBounds(100,100,1200,500);
        }
}






