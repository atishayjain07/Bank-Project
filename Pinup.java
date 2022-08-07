package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Pinup implements ActionListener
{
Frame f;
Button deposite,profile,transfer,withdraw,passbook,b6,logout;
Label bankname,subheading,heading;

Label oldl,newl,conl;
TextField oldt,newt,cont;
Button changeb;
Button home;
Connection co;
PreparedStatement pst;

Pinup()
{
f=new Frame();
home=new Button("Welcome");
profile=new Button("Profile");
transfer=new Button("Transfer Amount");
deposite=new Button("Deposit Amount");
withdraw=new Button("Withdraw Amount");
passbook=new Button("Passbook");
b6=new Button("Update Pin");
logout=new Button("LogOut");
oldl=new Label("Enter Old Pin : ");
newl=new Label("Enter New Pin : ");
conl=new Label("Enter Confirm Pin : ");
oldt=new TextField();
newt=new TextField();
cont=new TextField();
changeb=new Button("Change Pin");

heading=new Label("        Update Pin");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");

Font font=new Font("Arial",Font.BOLD,30);
Font font2=new Font("Arial",Font.BOLD,20);
Font font3=new Font("Arial",Font.BOLD,17);
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
changeb.setForeground(Color.green);
oldl.setFont(font3);
newl.setFont(font3);
conl.setFont(font3);
oldl.setForeground(Color.white);
newl.setForeground(Color.white);
conl.setForeground(Color.white);

bankname.setBounds(0,30,900,80);
subheading.setBounds(0,110,900,40);
heading.setBounds(350,195,500,40);
home.setBounds(10,220,200,40);
profile.setBounds(20,260,200,40);
transfer.setBounds(30,300,200,40);
deposite.setBounds(40,340,200,40);
withdraw.setBounds(50,380,200,40);
passbook.setBounds(60,420,200,40);
b6.setBounds(70,460,200,40);
logout.setBounds(80,500,200,40);

oldl.setBounds(450,280,130,40);
newl.setBounds(450,320,130,40);
conl.setBounds(450,360,160,40);
oldt.setBounds(620,280,150,30);
newt.setBounds(620,320,150,30);
cont.setBounds(620,360,150,30);
changeb.setBounds(550,430,130,40);


f.setVisible(true);
f.setSize(900,600);
f.setLayout(null);

f.add(bankname);
f.add(subheading);
f.add(heading);
f.add(home);
f.add(profile);
f.add(transfer);
f.add(deposite);
f.add(withdraw);
f.add(passbook);
f.add(b6);
f.add(logout);
f.add(oldl);
f.add(newl);
f.add(conl);
f.add(oldt);
f.add(newt);
f.add(cont);
f.add(changeb);

profile.addActionListener(this);
transfer.addActionListener(this);
deposite.addActionListener(this);
withdraw.addActionListener(this);
passbook.addActionListener(this);
b6.addActionListener(this);
logout.addActionListener(this);
changeb.addActionListener(this);
home.addActionListener(this);

b6.setEnabled(false);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}catch(Exception e)
{
System.out.println(e);
}

}

public void actionPerformed(ActionEvent a)
{
if(a.getSource()==home)
{
f.setVisible(false);
new Home();
}

else if(a.getSource()==changeb)
{

if(Integer.parseInt(newt.getText())==Integer.parseInt(cont.getText()))
{
try{
pst=co.prepareStatement("UPDATE customer SET pincode=? WHERE acno=? and pincode=?");
pst.setInt(1,Integer.parseInt(newt.getText()));
pst.setInt(2,Integer.parseInt(Login.usernamet.getText()));
pst.setInt(3,Integer.parseInt(oldt.getText()));
pst.executeUpdate();
pst.close();
changeb.setEnabled(false);
}catch(Exception e)
{
System.out.println(e);
}
}
else{
System.out.println("No match");
}

}

else if(a.getSource()==profile)
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

else
{
f.setVisible(false);
new Login();
}

}

public static void main(String[] args)
{
//new Pinup();
}
}