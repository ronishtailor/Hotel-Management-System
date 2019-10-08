import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmLogin extends JFrame {
 JLabel lblUser,lblPass;
 JTextField jtfUser;
 JPasswordField jpfPass;
 JCheckBox jcb;
 JButton jtbLogin,jtbClose;
 FrmLogin()
  {
	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  setLayout(new GridLayout(4,2,20,20));
	  lblUser=new JLabel("User");
	  lblPass=new JLabel("Password");
	  jtfUser=new JTextField();
	  jpfPass=new JPasswordField();
	  jpfPass.setEchoChar('*');
	  jtbLogin=new JButton("Login");
	  jtbLogin.addActionListener(new ActionListener()
	  {
		  public void actionPerformed(ActionEvent ae)
		  { DConnection dc=new DConnection();
			String s1=CommonMethods.textValidation(jtfUser.getText()); 
			String s2=CommonMethods.textValidation(new String(jpfPass.getPassword()));
			ResultSet rst=dc.executeSelectQuery("select * from users where user='"+s1+"' and pass='"+s2+"'");
			try {
			if(rst.next())
			{
				String userType=rst.getString(3);
				dc.close();
				dispose();
				new FrmMainFrame(s1,userType);
			}
			else
			{
				dc.close();
				JOptionPane.showMessageDialog(FrmLogin.this,"Invalid Username or Password");
			}
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			}
		  }
  
	  });

	  jtbClose=new JButton("Close");
	  jtbClose.addActionListener(new ActionListener()
			  {
		  public void actionPerformed(ActionEvent ae)
		  {
			  dispose();
		  }
		  
			  });
	  jcb=new JCheckBox("Show Password");
	  jcb.addItemListener(new ItemListener()
			  {
		        public void itemStateChanged(ItemEvent ie)
		        {
		        	if(jcb.isSelected())
		        	{
		        		jpfPass.setEchoChar((char)0);
		        		
		        	}
		        	else
		        	{jpfPass.setEchoChar('*');
	        		
		        	}
		        }
			  });
	 add(lblUser);add(jtfUser);
	 add(lblPass);add(jpfPass);
	 add(jtbLogin);add(jtbClose);
	 add(jcb);
	 setTitle("Login");
	 setSize(400,300);
	 setLocationRelativeTo(null);
	 setVisible(true);
	  
  }
 public static void main(String args[])
 {
	 new FrmLogin();
 }
}
