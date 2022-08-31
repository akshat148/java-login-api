import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.sql.*; 

class CreateLoginForm extends JFrame implements ActionListener
{
JButton b1;
JPanel p1;
JLabel userLabel,PassLabel;
final JTextField t1,t2;

public static void main(String[]arg)
{
try
{
CreateLoginForm form=new CreateLoginForm();
form.setSize(300,100);
form.setVisible(true);
}
catch(Exception e)
{
JOptionPane.showMessageDialog(null,e.getMessage());
} 
}

CreateLoginForm()
{
userLabel=new JLabel();
userLabel.setText("Email-ID");
t1= new JTextField(15);
PassLabel= new JLabel();
PassLabel.setText("Password");
t2= new JPasswordField(15);
b1= new JButton("SUBMIT");
p1=new JPanel(new GridLayout(3,1));
p1.add(userLabel);
p1.add(t1);
p1.add(PassLabel);
p1.add(t2);
p1.add(b1);
add(p1,BorderLayout.CENTER);
b1.addActionListener(this);
setTitle("Login Form");
}
public void actionPerformed(ActionEvent ae)
{
String userValue=t1.getText();
String passValue=t2.getText();
try {
Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/reg","root","9721199379@Ad");
 PreparedStatement st = (PreparedStatement) connection.prepareStatement("Select email, pass from emp where email=? and pass=?");
st.setString(1, userValue);
st.setString(2, passValue);
ResultSet rs = st.executeQuery();
 if (rs.next()) {
dispose();
NewPage page=new NewPage();
page.setVisible(true);
JLabel wel_label=new JLabel("welcome:"+userValue);
page.getContentPane().add(wel_label);
}

else
{
JOptionPane.showMessageDialog(b1, "Incorrect Email-id and Password"); 
}
}
catch (SQLException sqlException) 
{
sqlException.printStackTrace();
}
}
}

