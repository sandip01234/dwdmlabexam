package org.example;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class StudentForm extends JFrame implements ActionListener {

    JTextField t1,t2;
    JPasswordField p1,p2;

    JRadioButton r1,r2;

    JCheckBox c1,c2;

    JComboBox cb;

    JButton b1,b2;

    JTextArea ta;

    StudentForm() {

        setLayout(null);

        JLabel l1=new JLabel("ID");
        JLabel l2=new JLabel("User");
        JLabel l3=new JLabel("Pass");
        JLabel l4=new JLabel("Repass");

        t1=new JTextField();
        t2=new JTextField();

        p1=new JPasswordField();
        p2=new JPasswordField();

        r1=new JRadioButton("Male");
        r2=new JRadioButton("Female");

        ButtonGroup bg=new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        c1=new JCheckBox("Java");
        c2=new JCheckBox("PHP");

        cb=new JComboBox(
                new String[]{"Nepal","India","China"}
        );

        b1=new JButton("Submit");
        b2=new JButton("Reset");

        ta=new JTextArea();

        l1.setBounds(30,30,80,30);
        t1.setBounds(120,30,120,30);

        l2.setBounds(30,70,80,30);
        t2.setBounds(120,70,120,30);

        l3.setBounds(30,110,80,30);
        p1.setBounds(120,110,120,30);

        l4.setBounds(30,150,80,30);
        p2.setBounds(120,150,120,30);

        r1.setBounds(120,190,70,30);
        r2.setBounds(200,190,80,30);

        c1.setBounds(120,230,70,30);
        c2.setBounds(200,230,70,30);

        cb.setBounds(120,270,120,30);

        b1.setBounds(40,320,100,30);
        b2.setBounds(170,320,100,30);

        ta.setBounds(30,370,280,100);

        add(l1); add(t1);
        add(l2); add(t2);
        add(l3); add(p1);
        add(l4); add(p2);

        add(r1); add(r2);

        add(c1); add(c2);

        add(cb);

        add(b1); add(b2);

        add(ta);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(350,550);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1) {

            String id=t1.getText();
            String user=t2.getText();

            String pass=p1.getText();
            String repass=p2.getText();

            // Validation
            if(id.equals("") || user.equals("")
                    || pass.equals("") || repass.equals("")) {

                JOptionPane.showMessageDialog(this,
                        "Empty Field");
            }

            else if(!pass.equals(repass)) {

                JOptionPane.showMessageDialog(this,
                        "Password Not Matched");
            }

            else {

                String gender =
                        r1.isSelected() ? "Male" : "Female";

                String course =
                        (c1.isSelected() ? "Java " : "")
                                +
                                (c2.isSelected() ? "PHP" : "");

                try {

                    Class.forName(
                            "com.mysql.cj.jdbc.Driver"
                    );

                    Connection con =
                            DriverManager.getConnection(
                                    "jdbc:mysql://localhost:3306/advancedjava",
                                    "root",
                                    "sandip@123"
                            );

                    PreparedStatement ps =
                            con.prepareStatement(
                                    "insert into student values(?,?,?,?,?,?)"
                            );

                    ps.setInt(1,Integer.parseInt(id));

                    ps.setString(2,user);

                    ps.setString(3,pass);

                    ps.setString(4,gender);

                    ps.setString(5,course);

                    ps.setString(6,
                            cb.getSelectedItem().toString());

                    ps.executeUpdate();

                    JOptionPane.showMessageDialog(this,
                            "Inserted");

                    ResultSet rs =
                            con.createStatement().executeQuery(
                                    "select * from student"
                            );

                    ta.setText("");

                    while(rs.next()) {

                        ta.append(
                                rs.getInt(1)+" "
                                        +rs.getString(2)+" "
                                        +rs.getString(3)+"\n"
                        );
                    }

                    con.close();

                } catch(Exception ex) {

                    System.out.println(ex);
                }
            }
        }

        else if(e.getSource()==b2) {

            t1.setText("");
            t2.setText("");

            p1.setText("");
            p2.setText("");

            ta.setText("");
        }
    }

    public static void main(String[] args) {

        new StudentForm();
    }
}