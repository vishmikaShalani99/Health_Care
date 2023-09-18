import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Doctor  {
    JPanel DoctorSearch;
    private JButton btnAppointments;
    private JButton btnPatient;
    private JButton btnLogout;
    private JLabel lbldashboard;

    public Doctor() {


        //to check specific Patient details, to go searchPatient form
        btnPatient.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Doctor - Search Patient");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new SearchPatient().searchPatients,"Doctor.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });



            }
        });

        //when clicking back button then go to login form again
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
        btnAppointments.addActionListener(new ActionListener() {
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

                        JFrame frame = new JFrame("Doctor - Search Appointments");
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.add(new BackgroundImagePane(new SearchAppointment().SearchAppointments,"Doctor.jpg"));
                        frame.pack();
                        frame.setLocationRelativeTo(null);
                        frame.setVisible(true);
                    }
                });


            }
        });
    }

    public static void main(String[] args) {
        JFrame frame= new JFrame("Doctor");
        frame.setContentPane(new Doctor().DoctorSearch);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(100,100,500,500);
    }
}
