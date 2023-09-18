import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.*;

public class Payments {
    private JTextField txtid;
    private JTextField txtpatid;
    private JTextField txtdate;
    private JTextField txthosfee;
    private JTextField txtappid;
    private JButton btndashboard;
    private JButton btnprint;
    private JButton btnadd;
    private JTextField txtdocid;
    private JTextField txtdocfee;
    private JTextField txttot;
    public JPanel Payments;
    private JComboBox combopaymethod;
    private JTextField txtdocname;
    private JTextField txtpatname;
    private JLabel lbldashboard;


    Connection conn = MySQLConnection.connect();
    PreparedStatement pst;


    public Payments() {

        JFrame fa = new JFrame();
        fa.setVisible(true);
        fa.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

                try {

                    Statement st;
                    st = conn.createStatement();
                    String q = "select ap.Id,ap.Doctor_Id,doc.Name as DoctorName,doc.Fee as DoctorFee, ap.Patient_Id, pat.Name, (select (ifnull(MAX(Id),0) +1) from payments) as NextPaymentNo\n" +
                            "from appointments ap\n" +
                            "inner join doctor doc on ap.Doctor_Id = doc.Id\n" +
                            "inner join patient pat on pat.Id = ap.Patient_Id\n" +
                            "ORDER by ap.Id desc\n" +
                            "limit 1\n";

                    ResultSet rs = st.executeQuery(q);
                    while (rs.next()) {
                        txtid.setText(String.valueOf(rs.getInt("NextPaymentNo")));
                        txtappid.setText(String.valueOf(rs.getInt("Id")));
                        txtdocid.setText(String.valueOf(rs.getInt("Doctor_Id")));
                        txtdocname.setText(String.valueOf(rs.getString("DoctorName")));
                        txtpatid.setText(String.valueOf(rs.getInt("Patient_Id")));
                        txtpatname.setText(String.valueOf(rs.getString("Name")));
                        txtdocfee.setText(String.valueOf(rs.getDouble("DoctorFee")));
                        txthosfee.setText("2500.00");
                        txtdate.setText(java.time.LocalDateTime.now().toString());
                        txttot.setText(String.valueOf(Double.valueOf(txthosfee.getText()) + Double.valueOf(txtdocfee.getText())));
                    }


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


        btndashboard.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Receptionist");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new Receptionist().Recept,"Recept.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });



            }
        });
        btnadd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pay_id,app_id,pat_id,doc_id,pay_method,pay_date,doc_fee,hospital_fee,pay_total;

                pay_id = txtid.getText();
                app_id = txtappid.getText();
                pat_id = txtpatid.getText();
                doc_id = txtdocid.getText();
                pay_method = combopaymethod.getSelectedItem().toString();
                pay_date = txtdate.getText();
                doc_fee = txtdocfee.getText();
                hospital_fee =txthosfee.getText();
                pay_total = txttot.getText();

                try {

                    String sql = "INSERT INTO payments(Id,Appointment_Id,Patient_Id,Doctor_Id,Payment_Method,Payment_Date,Doctor_Fee,Hospital_Fee,Total) Values(?,?,?,?,?,?,?,?,?)";

                    pst = conn.prepareStatement(sql);

                    pst.setString(1,pay_id);
                    pst.setString(2,app_id);
                    pst.setString(3,pat_id);
                    pst.setString(4,doc_id);
                    pst.setString(5,pay_method);
                    pst.setString(6,pay_date);
                    pst.setString(7,doc_fee);
                    pst.setString(8,hospital_fee);
                    pst.setString(9,pay_total);



                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Record Added!");


                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                                ex.printStackTrace();
                            }

                            JFrame frame = new JFrame("Receptionist");
                            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                            frame.add(new BackgroundImagePane(new Receptionist().Recept,"Recept.jpg"));
                            frame.pack();
                            frame.setLocationRelativeTo(null);
                            frame.setVisible(true);
                        }
                    });




                }
                catch (SQLException e1) {
                    e1.printStackTrace();
                }


            }
        });
    }
}
