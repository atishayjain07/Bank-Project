package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Home implements ActionListener
{
Frame f;
Button deposite,profile,transfer,withdraw,passbook,b6,logout;
Label bankname,subheading,heading;
TextArea about;
Connection co;
ResultSet rs;
PreparedStatement pst;
Button home;

Home()
{
f=new Frame();
home=new Button("");
profile=new Button("Profile");
transfer=new Button("Transfer Amount");
deposite=new Button("Deposit Amount");
withdraw=new Button("Withdraw Amount");
passbook=new Button("Passbook");
b6=new Button("Update Pin");
logout=new Button("LogOut");


heading=new Label("");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");

about=new TextArea("The central concept of the application is to allow the\ncustomer(s) to service virtually using the Internet With Out\ngoing to bank and allow customer to open new account, withdraw,\ndeposit , close and getting balance using this banking\nservice. The information pertaining to the customers stores on\nan RDBC at the server side (BANK). The Bank services the customers\naccording to the customer’s intention and it updates and\nbackups of each customer transaction accordingly.");

Font font=new Font("Arial",Font.BOLD,30);
Font font2=new Font("Arial",Font.BOLD,20);
bankname.setBackground(Color.orange);
bankname.setForeground(Color.white);
bankname.setFont(font);
subheading.setBackground(Color.blue);
subheading.setForeground(Color.white);
subheading.setFont(font2);
heading.setBackground(Color.black);
heading.setFont(font2);
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
logout.setForeground(Color.red);
f.setBackground(myColor1);
//cusname.setForeground(Color.white);


bankname.setBounds(0,30,900,80);
subheading.setBounds(0,110,900,40);
heading.setBounds(350,195,500,40);
about.setBounds(370,270,450,140);
home.setBounds(10,220,200,40);
profile.setBounds(20,260,200,40);
transfer.setBounds(30,300,200,40);
deposite.setBounds(40,340,200,40);
withdraw.setBounds(50,380,200,40);
passbook.setBounds(60,420,200,40);
b6.setBounds(70,460,200,40);
logout.setBounds(80,500,200,40);


f.setVisible(true);
f.setSize(900,600);
f.setLayout(null);

f.add(bankname);
f.add(subheading);
f.add(about);
f.add(heading);
f.add(home);
f.add(profile);
f.add(transfer);
f.add(deposite);
f.add(withdraw);
f.add(passbook);
f.add(b6);
f.add(logout);

profile.addActionListener(this);
transfer.addActionListener(this);
deposite.addActionListener(this);
withdraw.addActionListener(this);
passbook.addActionListener(this);
b6.addActionListener(this);
logout.addActionListener(this);
home.addActionListener(this);

home.setEnabled(false);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}catch(Exception e)
{
System.out.println(e);
}

heading.setText("        Welcome, Atishay");
home.setLabel("Welcome, Atishay");
try{
pst=co.prepareStatement("Select name,status from customer Where acno=?");
pst.setInt(1,Integer.parseInt(Login.usernamet.getText()));
rs=pst.executeQuery();
rs.next();
heading.setText("        Welcome, "+rs.getString(1));
//name=rs.getString(1);
home.setLabel("Welcome, "+rs.getString(1));
if(rs.getString(2).equals("Deactive"))
{
transfer.setEnabled(false);
withdraw.setEnabled(false);
deposite.setEnabled(false);
passbook.setEnabled(false);
b6.setEnabled(false);
}
rs.close();
pst.close();
}catch(Exception e)
{
System.out.println(e);
}


}

public void actionPerformed(ActionEvent a)
{
if(a.getSource()==profile)
{
f.setVisible(false);
new Profile();
}

else if(a.getSource()==transfer)
{
f.setVisible(false);
new Transfer();
}

else if(a.getSource()==deposite)
{
f.setVisible(false);
new Deposit();
}

else if(a.getSource()==withdraw)
{
f.setVisible(false);
new Withdraw();
}

else if(a.getSource()==passbook)
{
f.setVisible(false);
new Passbook();
}

else if(a.getSource()==b6)
{
f.setVisible(false);
new Pinup();
}

else
{
f.setVisible(false);
new Login();
}

}

public static void main(String[] args)
{
new Home();
}
}