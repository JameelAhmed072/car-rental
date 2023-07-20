//package org.carrental.ui;
//
//import javax.swing.*;
//
//public class LoginUI {
//    public LoginUI() {
//        //defining a frame
//        JFrame frame = new JFrame();
//
//        // defining a button
//        JButton button = new JButton("Hello World");
//        button.setBounds(100,100,150,50);  /// this is not a good approach, for this use setLayouts, which is good approach
//
//        // add button on frame
//        frame.add(button);
//
//        // basic properties
//        frame.setSize(500,500);
//        frame.setLayout(null);    //  agar yee hum nai rakeenge too by default center pee ayeega button.
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // issko use karne see jab hum cross pee click karenge too program ruk jayeega
//        frame.setLocationRelativeTo(null);  ///  iss seee frame direct center mee ayeega
//        frame.setVisible(true);
//
//    }
//}

//------------------------------------------------------------------------------------------------------------------//

//    we can extend the Login directly with JFrame which will be the parent class of this
//  use this below one when you are sure that your this class will be extended to any class in future, other directly make the JFrame Object and use it.

//package org.carrental.ui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class LoginUI extends JFrame{
//    public LoginUI() {
//
//
//
//        JButton button = new JButton("Hello World");
//        button.setBounds(100,100,150,50);  /// this is not a good approach, for this use setLayouts, which is good approach
//
//        JLabel label = new JLabel();
//        label.setBounds(100,200,150,50);
//
//        // add button on frame
//        add(button);
//        add(label);
//
//
//
//        //  now I want to print something on console when clicked on button
////        button.addActionListener((b)->{
////            System.out.println("Hello stepway");
////        });
//
//        //  Now I want to print something on screen when clicked on button
//        button.addActionListener((b)->{
//            label.setText("Hello Jameel: ");
//            label.setForeground(Color.RED);
//        });
//        // basic properties
//        setSize(500,500);
//        setLayout(null);    //  agar yee hum nai rakeenge too by default center pee ayeega.
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // issko use karne see jab hum cross pee click karenge too program ruk jayeega
//        setLocationRelativeTo(null);  ///  iss seee frame direct center mee ayeega
//        setVisible(true);
//
//    }
//}







//package org.carrental.ui;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class LoginUI extends JFrame{
//    public LoginUI() {
//
//
//        JLabel label = new JLabel();
//        label.setBounds(100,300,150,50);
//
//        JTextField textField = new JTextField();
//        textField.setBounds(100,100,200,30);
//
//        JButton button = new JButton("Hello World");
//        button.setBounds(100,200,150,50);  /// this is not a good approach, for this use setLayouts, which is good approach
//
//
//
//        // add on frame
//
//        add(label);
//        add(textField);
//        add(button);
//
//
//
//        //  now I want to print something on console when clicked on button
////        button.addActionListener((b)->{
////            System.out.println("Hello stepway");
////        });
//
//        //  Now I want to print something on screen when clicked on button
////        button.addActionListener((b)->{
////            label.setText("Hello Jameel: ");
////            label.setForeground(Color.RED);
////        });
//        button.addActionListener((b)->{
//            label.setText(textField.getText());
//            label.setForeground(Color.RED);
//        });
//
//
//
//        // basic properties
//        setSize(500,500);
//        setLayout(null);    //  agar yee hum nai rakeenge too by default center pee ayeega.
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // issko use karne see jab hum cross pee click karenge too program ruk jayeega
//        setLocationRelativeTo(null);  ///  iss seee frame direct center mee ayeega
//        setVisible(true);
//
//    }
//}


//  ------------------------------------------------------------------------------------------------------------------//


//    ===>    Now proper login page:


package org.carrental.ui;

import org.carrental.service.AuthenticationService;

import javax.swing.*;
import java.awt.*;

public class LoginUI{

    private final AuthenticationService authenticationService = new AuthenticationService();


    public LoginUI() {

        JFrame frame = new JFrame("Rental Car APP");



        JTextField usertf = new JTextField("UserName");
        usertf.setBounds(150,100, 200,30);

        JTextField passtf = new JTextField("Password");
        passtf.setBounds(150,150, 200,30);



        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(200,250,100,30);

        frame.add(usertf);
        frame.add(passtf);
        frame.add(loginBtn);


        //  check login

        loginBtn.addActionListener((event)->{
            if(authenticationService.checkLogin(usertf.getText(), passtf.getText())){
                //  agar conditon true huha too maa yee screen band kar k naya screen deekana chahta huu warna error ka msg display karunga
                frame.dispose();
                new HomeUI();
            }else{
                JOptionPane.showMessageDialog(frame,"Incorrect credential");
            }
        });



        // basic properties

        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }
}

