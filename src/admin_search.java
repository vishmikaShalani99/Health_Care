import net.proteanit.sql.DbUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;

public class admin_search extends MySQLConnection {
    private JButton btnsearch;
    private JLabel lblid;
    private JTextField txtid;
    private JScrollPane tblload;
    private JTable table1;
    private JLabel lbltype;
    private JComboBox cmbxtype;
    private JButton btnall;
    public JPanel adminsearch;
    private JButton btndelete;
    private JButton btnback;
    private JLabel lbldashboard;
    private JButton btnedit;


    Connection conn = MySQLConnection.connect();
    PreparedStatement pst, pst1;

    String ID, usertype;

    public admin_search() {
        btnall.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    ResultSet rs;

                    usertype = cmbxtype.getSelectedItem().toString();

                    switch(usertype){

                        case "Receptionist":
                            pst = conn.prepareStatement("select * from receptionist");
                            break;

                        case "Doctor":
                            pst = conn.prepareStatement("select * from doctor");
                            break;

                        case "Nurse":
                            pst = conn.prepareStatement("select * from nurse");
                            break;

                        case "Patient":
                            pst = conn.prepareStatement("select * from patient");
                            break;

                        case "Payment":
                            pst = conn.prepareStatement("select * from payments");
                            break;

                        case "Appointment":
                            pst = conn.prepareStatement("select * from appointments");
                            break;
                    }

                    rs = pst.executeQuery();


                    if (rs.next() == true)
                    {
                        rs = pst.executeQuery();
                        table1.setModel(DbUtils.resultSetToTableModel(rs));
                        txtid.setText("");
                        txtid.requestFocus();

                    }


                    else
                    {
                        JOptionPane.showMessageDialog(null, "No Data to Show!");
                        txtid.setText("");
                        txtid.requestFocus();

                    }



                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });




        btnsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try{
                    ResultSet rs;

                    usertype = cmbxtype.getSelectedItem().toString();
                    ID = txtid.getText();

                    switch(usertype){

                        case "Receptionist":
                            pst = conn.prepareStatement("select * from receptionist where Id = ?");
                            break;

                        case "Doctor":
                            pst = conn.prepareStatement("select * from doctor where Id = ?");
                            break;

                        case "Nurse":
                            pst = conn.prepareStatement("select * from nurse where Id = ?");
                            break;

                        case "Patient":
                            pst = conn.prepareStatement("select * from patient where Id = ?");
                            break;

                        case "Payment":
                            pst = conn.prepareStatement("select * from payments where Id = ?");
                            break;

                        case "Appointment":
                            pst = conn.prepareStatement("select * from appointments where ID = ?");
                            break;
                    }

                    pst.setString(1, ID);
                    rs = pst.executeQuery();


                    if (rs.next() == true)
                    {
                        rs = pst.executeQuery();
                        table1.setModel(DbUtils.resultSetToTableModel(rs));
                        txtid.setText("");
                        txtid.requestFocus();


                    }

                    else
                    {
                        JOptionPane.showMessageDialog(null, "Invalid ID number!");
                        txtid.setText("");
                        txtid.requestFocus();

                    }

                }

                catch (SQLException e1)
                {
                    e1.printStackTrace();
                }

            }
        });


        btndelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pat_id = txtid.getText();

                try{

                ResultSet rs;

                usertype = cmbxtype.getSelectedItem().toString();
                ID = txtid.getText();

                switch(usertype){

                    case "Receptionist":
                        pst1 =conn.prepareStatement("select * from receptionist where Id = ?");
                        pst = conn.prepareStatement("delete from receptionist where Id = ?");
                        break;

                    case "Doctor":
                        pst1 = conn.prepareStatement("select * from doctor where Id = ?");
                        pst = conn.prepareStatement("delete from doctor where Id = ?");
                        break;

                    case "Nurse":
                        pst1 = conn.prepareStatement("select * from nurse where Id = ?");
                        pst = conn.prepareStatement("delete from nurse where Id = ?");
                        break;

                    case "Patient":
                        pst1 = conn.prepareStatement("select * from patient where Id = ?");
                        pst = conn.prepareStatement("delete from patient where Id = ?");
                        break;

                    case "Payment":
                        pst1 = conn.prepareStatement("select * from payments where Id = ?");
                        pst = conn.prepareStatement("delete from payments where Id = ?");
                        break;

                    case "Appointment":
                        pst1 = conn.prepareStatement("select * from appointments where ID = ?");
                        pst = conn.prepareStatement("delete from appointments where ID = ?");
                        break;
                }


                pst1.setString(1, ID);
                pst.setString(1, ID);
                rs = pst1.executeQuery();


                if (rs.next() == true)
                {
                    pst.executeUpdate();
                    table1.setModel(DbUtils.resultSetToTableModel(rs));
                    txtid.setText("");
                    txtid.requestFocus();


                }

                else
                {
                    JOptionPane.showMessageDialog(null, "Invalid ID number!");
                    txtid.setText("");
                    txtid.requestFocus();

                }

            }

                catch (SQLException e1)
            {
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

    }
}
