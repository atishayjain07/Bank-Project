package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Deposit implements ActionListener
{
Frame f;
Button deposit,profile,transfer,withdraw,passbook,b6,logout;
Label bankname,subheading,heading;

Label name,source,amount,rupee,detail,cusname;
Button depositb;
Choice sourcec;
TextField amountt;
TextArea detailta;
Button home;
Connection co;
PreparedStatement pst;
ResultSet rs;

Deposit()
{
f=new Frame();
home=new Button("");
profile=new Button("Profile");
transfer=new Button("Transfer Amount");
deposit=new Button("Deposit Amount");
withdraw=new Button("Withdraw Amount");
passbook=new Button("Passbook");
b6=new Button("Update Pin");
logout=new Button("LogOut");

heading=new Label("        Deposit Money");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");

name=new Label("Account Holder Name : ");
source=new Label("Select Source Account No. : ");
amount=new Label("Amount : ");
rupee=new Label("Rs");
cusname=new Label("");
detail=new Label("Details : ");
depositb=new Button("Deposit Amount");
sourcec=new Choice();
amountt=new TextField();
detailta=new TextArea();


Font font=new Font("Arial",Font.BOLD,30);
Font font2=new Font("Arial",Font.BOLD,20);
Font font3=new Font("Arial",Font.BOLD,15);
bankname.setBackground(Color.orange);
bankname.setForeground(Color.white);
bankname.setFont(font);
subheading.setBackground(Color.blue);
subheading.setForeground(Color.white);
subheading.setFont(font2);
heading.setFont(font2);
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
heading.setBackground(Color.black);
logout.setForeground(Color.red);
depositb.setForeground(Color.green);

cusname.setFont(font3);
f.setBackground(myColor1);
cusname.setForeground(Color.white);
name.setFont(font3);
source.setFont(font3);

amount.setFont(font3);
rupee.setFont(font3);
detail.setFont(font3);

bankname.setBounds(0,30,900,80);
subheading.setBounds(0,110,900,40);
heading.setBounds(350,195,500,40);
home.setBounds(10,220,200,40);
profile.setBounds(20,260,200,40);
transfer.setBounds(30,300,200,40);
deposit.setBounds(40,340,200,40);
withdraw.setBounds(50,380,200,40);
passbook.setBounds(60,420,200,40);
b6.setBounds(70,460,200,40);
logout.setBounds(80,500,200,40);


name.setBounds(465,290,170,30);
cusname.setBounds(640,290,180,30);
source.setBounds(430,330,205,30);
sourcec.setBounds(635,330,200,40);
amount.setBounds(560,370,70,30);
amountt.setBounds(640,370,120,30);
rupee.setBounds(770,370,30,30);
detail.setBounds(570,435,70,30);
detailta.setBounds(640,410,200,80);
depositb.setBounds(640,510,180,40);

f.setVisible(true);
f.setSize(900,600);
f.setLayout(null);

f.add(bankname);
f.add(subheading);
f.add(heading);
f.add(home);
f.add(profile);
f.add(transfer);
f.add(deposit);
f.add(withdraw);
f.add(passbook);
f.add(b6);
f.add(logout);

f.add(name);
f.add(source);
f.add(amount);
f.add(rupee);
f.add(detail);
f.add(depositb);
f.add(cusname);
f.add(sourcec);
f.add(amountt);
f.add(detailta);

profile.addActionListener(this);
transfer.addActionListener(this);
deposit.addActionListener(this);
withdraw.addActionListener(this);
passbook.addActionListener(this);
b6.addActionListener(this);
logout.addActionListener(this);
depositb.addActionListener(this);
home.addActionListener(this);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}
catch(Exception e)
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
cusname.setText(rs.getString(1));
sourcec.add("Current Acno : "+rs.getString(8));
rs.close();
pst.close();
}catch(Exception e)
{
System.out.println(e);
}

deposit.setEnabled(false);
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

else if(a.getSource()==depositb)
{
String remark=detailta.getText();

try{
int x=Integer.parseInt(Login.usernamet.getText());
pst=co.prepareStatement("Select * from customer where acno=?");
pst.setInt(1,x);
rs=pst.executeQuery();
rs.next();
int amt=(rs.getInt(6))+(Integer.parseInt(amountt.getText()));
pst=co.prepareStatement("UPDATE customer SET amount=? WHERE acno=?");
pst.setInt(1,amt);
pst.setInt(2,x);
System.out.println(amt);
pst.executeUpdate();

rs.close();
pst.close();
depositb.setEnabled(false);
}catch(Exception e)
{
System.out.println(e);
}
}

else
{
f.setVisible(false);
new Login();
}

}

public static void main(String[] args)
{
//new Deposit();
}
}