package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Passbook implements ActionListener
{
Frame f;
Button deposite,profile,transfer,withdraw,passbook,b6,logout;
Label bankname,subheading,heading;
Connection co;
ResultSet rs;
PreparedStatement pst;
Button home;
Label acno,date,year,credit,debit,balance,remark;
Label l[];

Passbook()
{
l=new Label[100];
f=new Frame();
home=new Button("");
profile=new Button("Profile");
transfer=new Button("Transfer Amount");
deposite=new Button("Deposit Amount");
withdraw=new Button("Withdraw Amount");
passbook=new Button("Passbook");
b6=new Button("Update Pin");
logout=new Button("LogOut");
acno=new Label("      Acc. No.");
date=new Label("         Date");
year=new Label("        Year");
credit=new Label("        Credit");
debit=new Label("         Debit");
balance=new Label("Closing Balance");
remark=new Label("      Remark");

heading=new Label("        Passbook");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");

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
Color Color2 = new Color(192, 192, 192);
acno.setBackground(Color2);
date.setBackground(Color2);
year.setBackground(Color2);
credit.setBackground(Color2);
debit.setBackground(Color2);
balance.setBackground(Color2);
remark.setBackground(Color2);
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
logout.setForeground(Color.red);
f.setBackground(myColor1);
//cusname.setForeground(Color.white);

bankname.setBounds(0,30,1100,80);
subheading.setBounds(0,110,1100,40);
heading.setBounds(350,195,700,40);
home.setBounds(10,220,200,40);
profile.setBounds(20,260,200,40);
transfer.setBounds(30,300,200,40);
deposite.setBounds(40,340,200,40);
withdraw.setBounds(50,380,200,40);
passbook.setBounds(60,420,200,40);
b6.setBounds(70,460,200,40);
logout.setBounds(80,500,200,40);

acno.setBounds(350,260,100,40);
date.setBounds(450,260,100,40);
year.setBounds(550,260,100,40);
credit.setBounds(650,260,100,40);
debit.setBounds(750,260,100,40);
balance.setBounds(850,260,100,40);
remark.setBounds(950,260,100,40);

f.setVisible(true);
f.setSize(1100,600);
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
f.add(acno);
f.add(date);
f.add(year);
f.add(credit);
f.add(debit);
f.add(balance);
f.add(remark);

profile.addActionListener(this);
transfer.addActionListener(this);
deposite.addActionListener(this);
withdraw.addActionListener(this);
passbook.addActionListener(this);
b6.addActionListener(this);
logout.addActionListener(this);
home.addActionListener(this);

passbook.setEnabled(false);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}catch(Exception e)
{
System.out.println(e);
}
home.setLabel("Welcome, Atishay");
try{
pst=co.prepareStatement("Select name,status from customer Where acno=?");
pst.setInt(1,Integer.parseInt(Login.usernamet.getText()));
rs=pst.executeQuery();
rs.next();
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
if(a.getSource()==home)
{
f.setVisible(false);
new Home();
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
//new Passbook();
}
}