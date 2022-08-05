package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login implements ActionListener
{
Frame f;
Button submit,click;
Label username,passcode,create,heading,bankname,subheading;
static TextField usernamet;
TextField passcodet;
TextArea about;
Connection co;
PreparedStatement pst;
ResultSet rs;
int pin;

Login()
{
f=new Frame();

submit=new Button("Submit");
click=new Button("Click Here");

username=new Label("Account No : ");
passcode=new Label("Passcode : ");
heading=new Label("        Customer Login");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");
create=new Label("Want to Create a New Account? ");

about=new TextArea("The central concept of the application is to allow the\n customer(s) to service virtually using the Internet With Out\n going to bank and allow customer to open new account, withdraw,\n deposit , close and getting balance using this banking\n service. The information pertaining to the customers stores on\n an RDBC at the server side (BANK). The Bank services the customers\n according to the customer’s intention and it updates and\n backups of each customer transaction accordingly.");

usernamet=new TextField();
passcodet=new TextField();

Font font=new Font("Arial",Font.BOLD,30);
Font font2=new Font("Arial",Font.BOLD,20);
bankname.setBackground(Color.orange);
bankname.setForeground(Color.white);
bankname.setFont(font);
subheading.setBackground(Color.blue);
subheading.setForeground(Color.white);
subheading.setFont(font2);
create.setForeground(Color.red);
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
f.setBackground(myColor1);
//cusname.setForeground(Color.white);

heading.setBackground(Color.black);

bankname.setBounds(0,30,900,80);
subheading.setBounds(0,110,900,40);
heading.setBounds(70,200,250,40);
username.setBounds(80,250,90,30);
passcode.setBounds(80,290,70,30);
usernamet.setBounds(170,250,130,30);
passcodet.setBounds(170,290,130,30);
submit.setBounds(150,340,70,40);
create.setBounds(60,400,200,50);
click.setBounds(260,400,90,40);
about.setBounds(370,200,450,200);

f.setVisible(true);
f.setSize(900,500);
f.setLayout(null);

f.add(bankname);
f.add(subheading);
f.add(heading);
f.add(username);
f.add(passcode);
f.add(usernamet);
f.add(passcodet);
f.add(submit);
f.add(click);
f.add(create);
f.add(about);

submit.addActionListener(this);
click.addActionListener(this);

try{
System.out.println("1");
Class.forName("com.mysql.cj.jdbc.Driver");
System.out.println("2");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
System.out.println("3");
}
catch(Exception e)
{
System.out.println(e);
System.out.println("Hello");
}
}

public void actionPerformed(ActionEvent a)
{
if(a.getSource()==click)
{
f.setVisible(false);
new Create();
}
else
{
try{
pst=co.prepareStatement("select pincode from customer where acno=?");
System.out.println(usernamet.getText());
pst.setString(1,usernamet.getText());
rs=pst.executeQuery();
rs.next();
pin=rs.getInt(1);
System.out.println(rs.getInt(1));


if(pin==Integer.parseInt(passcodet.getText()))
{
f.setVisible(false);
new Home();
}
else 
{
System.out.println("sorry invalid details");
}

pst.close();
rs.close();
}
catch(Exception e)
{
System.out.println(e);
}
}

}

public static void main(String[] args)
{
Login l=new Login();
}
}