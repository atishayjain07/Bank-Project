package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Profile implements ActionListener
{
Frame f;
Button deposite,profile,transfer,withdraw,passbook,b6,logout;
Label bankname,subheading,heading;
static Label name,namet,acno,acnot,address,addresst,contact,contactt,email,emailt,type,typet,status,statust;
Connection co;
ResultSet rs;
PreparedStatement pst;
Button actdeact,edit,check;
Button home;

Profile()
{
f=new Frame();

name=new Label("Account Holder Name : ");
namet=new Label("");
actdeact=new Button("");
acno=new Label("Account number : ");
acnot=new Label("");
address=new Label("Residential Address : ");
addresst=new Label("");
contact=new Label("Registered Mobile Number : ");
contactt=new Label("");
email=new Label("Email : ");
emailt=new Label("");
type=new Label("Account Type : ");
typet=new Label("");
status=new Label("Account Status : ");
statust=new Label("");

home=new Button("");
edit=new Button("Edit Details");
check=new Button("Check Balance");
profile=new Button("Profile");
transfer=new Button("Transfer Amount");
deposite=new Button("Deposit Amount");
withdraw=new Button("Withdraw Amount");
passbook=new Button("Passbook");
b6=new Button("Update Pin");
logout=new Button("LogOut");

heading=new Label("        Account Details");
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
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
logout.setForeground(Color.red);
f.setBackground(myColor1);
name.setForeground(Color.white);
acno.setForeground(Color.white);
address.setForeground(Color.white);
contact.setForeground(Color.white);
email.setForeground(Color.white);
type.setForeground(Color.white);
status.setForeground(Color.white);
actdeact.setForeground(Color.red);

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
edit.setBounds(750,240,100,40);

name.setBounds(400,270,150,40);namet.setBounds(600,270,100,40);
acno.setBounds(400,320,150,40);acnot.setBounds(600,320,100,40);
address.setBounds(400,370,150,40);addresst.setBounds(600,370,250,40);
contact.setBounds(400,420,190,40);contactt.setBounds(600,420,100,40);
email.setBounds(400,470,70,40);emailt.setBounds(600,470,200,40);
type.setBounds(400,520,100,40);typet.setBounds(600,520,100,40);
status.setBounds(400,570,130,40);statust.setBounds(600,570,70,40);
actdeact.setBounds(690,570,70,40);

f.setVisible(true);
f.setSize(900,650);
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
f.add(edit);
f.add(name);
f.add(namet);
f.add(acno);
f.add(acnot);
f.add(address);
f.add(addresst);
f.add(contact);
f.add(contactt);
f.add(email);
f.add(emailt);
f.add(type);
f.add(typet);
f.add(status);
f.add(statust);
f.add(actdeact);

edit.addActionListener(this);
profile.addActionListener(this);
transfer.addActionListener(this);
deposite.addActionListener(this);
withdraw.addActionListener(this);
passbook.addActionListener(this);
b6.addActionListener(this);
actdeact.addActionListener(this);
logout.addActionListener(this);
home.addActionListener(this);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}catch(Exception e)
{
System.out.println(e);
}
home.setLabel("Welcome, Atishay");

try{
pst=co.prepareStatement("Select * from customer Where acno=?");
pst.setInt(1,Integer.parseInt(Login.usernamet.getText()));
rs=pst.executeQuery();
rs.next();
home.setLabel("Welcome, "+rs.getString(1));
namet.setText(rs.getString(1));
acnot.setText(Integer.toString(rs.getInt(8)));
addresst.setText(rs.getString(4));
contactt.setText(Integer.toString(rs.getInt(2)));
emailt.setText(rs.getString(5));
typet.setText(rs.getString(3));
statust.setText(rs.getString(7));
if(statust.getText().equals("Active"))
{
System.out.println(statust.getText());
actdeact.setLabel("Deactive");
}
else{
System.out.println(statust.getText());
actdeact.setLabel("Active");
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

profile.setEnabled(false);
}

public void actionPerformed(ActionEvent a)
{
if(a.getSource()==edit)
{
f.setVisible(false);
new Edit();
}

else if(a.getSource()==home)
{
f.setVisible(false);
new Home();
}

else if(a.getSource()==actdeact)
{
try{
int x=Integer.parseInt(Login.usernamet.getText());
pst=co.prepareStatement("UPDATE customer SET status=? WHERE acno=?");
if(statust.getText().equals("Active"))
{
pst.setString(1,"Deactive");
statust.setText("Deactive");
transfer.setEnabled(false);
withdraw.setEnabled(false);
deposite.setEnabled(false);
passbook.setEnabled(false);
b6.setEnabled(false);
actdeact.setLabel("Active");
}
else{
pst.setString(1,"Active");
statust.setText("Active");
transfer.setEnabled(true);
withdraw.setEnabled(true);
deposite.setEnabled(true);
passbook.setEnabled(true);
b6.setEnabled(true);
actdeact.setLabel("Deactive");
}
pst.setInt(2,x);
pst.executeUpdate();
pst.close();
//actdeact.setEnabled(false);
}catch(Exception e)
{
System.out.println(e);
}

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
//new Profile();
}
}