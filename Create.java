package bank;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.lang.Math;

public class Create implements ActionListener,ItemListener
{
Frame f;
Label heading,bankname,subheading,accreated1,accreated2,accreated3,accreated4;
Connection co;
PreparedStatement pst;
ResultSet rs;
String st;
int acno=0;

Label name,contact,address,email,type,amount;
TextField namet,contactt,addresst,emailt,amountt;
Choice typec;
Button back,create;

Create()
{
f=new Frame();
accreated1=new Label("");
accreated2=new Label("");
accreated3=new Label("");
accreated4=new Label("");
name=new Label("Name : ");
contact=new Label("Contact No : ");
address=new Label("Address : ");
email=new Label("Email : ");
type=new Label("Account Type : ");
amount=new Label("Enter Amount : ");
namet=new TextField();
contactt=new TextField();
addresst=new TextField();
emailt=new TextField();
amountt=new TextField();
typec=new Choice();
back=new Button("← Back");
create=new Button("Create Account");

heading=new Label("        Create Account");
bankname=new Label("      Atishay Bank");
subheading=new Label("        Manage Your Money Online");

Font font=new Font("Arial",Font.BOLD,30);
Font font2=new Font("Arial",Font.BOLD,20);
Font font3=new Font("Arial",Font.BOLD,15);
bankname.setBackground(Color.orange);
bankname.setForeground(Color.white);
bankname.setFont(font);
subheading.setBackground(Color.blue);
subheading.setForeground(Color.white);
subheading.setFont(font2);
Color myColor1 = new Color(123, 111, 222);
heading.setForeground(myColor1);
accreated1.setForeground(Color.white);
accreated2.setForeground(Color.white);
accreated3.setForeground(Color.white);
f.setBackground(myColor1);
//cusname.setForeground(Color.white);

accreated4.setForeground(Color.red);
heading.setBackground(Color.black);
heading.setFont(font2);
name.setFont(font3);
contact.setFont(font3);
address.setFont(font3);
email.setFont(font3);
type.setFont(font3);
amount.setFont(font3);
back.setForeground(Color.pink);
create.setForeground(Color.green);

bankname.setBounds(0,30,900,80);
subheading.setBounds(0,110,900,40);
heading.setBounds(70,200,250,40);

name.setBounds(70,270,80,40);	namet.setBounds(200,270,180,30);
contact.setBounds(70,320,100,40);contactt.setBounds(200,320,180,30);
type.setBounds(70,370,130,40);	typec.setBounds(200,370,180,30);
address.setBounds(500,270,80,40);addresst.setBounds(620,270,180,30);
email.setBounds(500,320,80,40); emailt.setBounds(620,320,180,30);
amount.setBounds(500,370,120,40); amountt.setBounds(620,370,180,30);
back.setBounds(300,430,130,40);
create.setBounds(450,430,130,40);

accreated1.setBounds(270,270,500,30);
accreated2.setBounds(270,300,500,30);
accreated3.setBounds(270,330,500,30);
accreated4.setBounds(270,360,500,30);

f.setVisible(true);
f.setSize(900,500);
f.setLayout(null);

f.add(bankname);
f.add(subheading);
f.add(heading);
f.add(name);	
f.add(namet);
f.add(contact);
f.add(contactt);
f.add(type);	
f.add(typec);
f.add(address);
f.add(addresst);
f.add(email);
f.add(emailt);
f.add(amount);
f.add(amountt);
f.add(back);
f.add(create);
f.add(accreated1);
f.add(accreated2);
f.add(accreated3);
f.add(accreated4);
typec.add("");
typec.add("Current");
typec.add("Saving");

back.addActionListener(this);
create.addActionListener(this);
typec.addItemListener(this);

try{
Class.forName("com.mysql.cj.jdbc.Driver");
co=DriverManager.getConnection("jdbc:mysql://localhost:8889/Bankdb","root","root");
}
catch(Exception e)
{
System.out.print(e);
}

}

public void itemStateChanged(ItemEvent e)
{
st=typec.getSelectedItem();
}

public void actionPerformed(ActionEvent a)
{
if(a.getSource()==back)
{
f.setVisible(false);
Login l=new Login();
}
else
{
try
{
pst=co.prepareStatement("select * from customer");
rs=pst.executeQuery();
while(rs.next())
{
acno++;
}
acno++;

int min=1000;
int max=9999;
int pin=(int)(Math.random()*(max-min+1)+min);
pst=co.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?)");
pst.setString(1,namet.getText());
pst.setInt(2,Integer.parseInt(contactt.getText()));
pst.setString(3,st);
pst.setString(4,addresst.getText());
pst.setString(5,emailt.getText());
pst.setInt(6,Integer.parseInt(amountt.getText()));
pst.setString(7,"Active");
pst.setInt(8,acno);
pst.setInt(9,pin);
pst.executeUpdate();
pst.close();
rs.close();

name.setVisible(false);
contact.setVisible(false);
address.setVisible(false);
email.setVisible(false);
type.setVisible(false);
amount.setVisible(false);
namet.setVisible(false);
contactt.setVisible(false);
addresst.setVisible(false);
emailt.setVisible(false);
amountt.setVisible(false);
typec.setVisible(false);

accreated1.setText("* YOUR ACCOUNT CREATED SUCCESSFULLY *");
accreated2.setText("* YOU ACCOUNT NO. IS "+acno+" *");
accreated3.setText("* YOU PINCODE IS "+pin+" REMEMBER IT (YOU CAN CHANGE IT LATER) *");
accreated4.setText("* CLICK BACK BUTTON FOR LOGIN *");

create.setEnabled(false); 

}catch(Exception ee)
{
System.out.print(ee);
}
}

}

public static void main(String[] args)
{
//new Create();
}
}