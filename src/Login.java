import javax.swing.*;
import java.awt.*;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.JFrame;


public class Login extends JPanel{
    public JPanel MainLogin;
    public JButton btnOK;
    private JButton btnCancel;
    private JComboBox combousertype;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JLabel lbltype;
    private JLabel lbldashboard;


    MySQLConnection connection = new MySQLConnection();
    Connection conn = MySQLConnection.connect();

    public Login()  {
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {



                    Statement st;
                    st = conn.createStatement();

                    String username, password, usertype;

                    usertype = combousertype.getSelectedItem().toString();
                    username = txtUsername.getText();
                    password = String.valueOf(txtPassword.getPassword());

                    String query = "";

                    switch(usertype){

                       case "Administrator":
                             query = "SELECT * FROM mmsdb.administrator WHERE username = '" + username + "' AND password='" + password + "'";
                            break;

                        case "Doctor":
                             query = "SELECT * FROM mmsdb.doctor WHERE username = '" + username + "' AND password='" + password + "'";
                            break;

                        case "Receptionist":
                              query = "SELECT * FROM mmsdb.receptionist WHERE username = '" + username + "' AND password='" + password + "'";
                            break;

                        case "Nurse":
                              query = "SELECT * FROM mmsdb.nurse WHERE username = '" + username + "' AND password='" + password + "'";
                            break;
                    }


                    ResultSet resultSet = st.executeQuery(query);
                    if (resultSet.next()) {

                        switch (usertype){

                            case "Nurse":
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

                                break;


                            case "Receptionist":

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

                                break;

                            case "Administrator":

                                JFrame Qframe = new JFrame();
                                Qframe.add(new Login().MainLogin);
                                Qframe.setVisible(false);
                                Qframe.dispose();

                                EventQueue.invokeLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                                        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                                            ex.printStackTrace();
                                        }

                                        JFrame frame = new JFrame("Administrator - Dashboard");
                                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                        frame.add(new BackgroundImagePane(new Administrator().Admin,"Admin.jpg"));
                                        frame.pack();
                                        frame.setLocationRelativeTo(null);
                                        frame.setVisible(true);
                                    }
                                });

                                break;


                            case "Doctor":
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

                                break;

                        }

                    }
                    else JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed", JOptionPane.INFORMATION_MESSAGE);
                    txtUsername.setText("");
                    txtUsername.requestFocus();

                    txtPassword.setText("");
                    txtPassword.requestFocus();

                }
                catch (Exception ex) {
                    throw new RuntimeException(ex);

                }
            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnCancel.addActionListener((event) -> System.exit(0));
            }
        });
    }

}
