import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class FrmSignUp extends JFrame{
	JLabel lblUser,lblPass,lblRetype;
	JTextField jtfUser;
	JPasswordField jpfPass,jpfRetype;
	JCheckBox jcb;
	JButton jtbCreate,jtbClose;
	FrmSignUp()
	{	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(new GridLayout(5,2,20,20));
		lblUser=new JLabel("User Name");
		lblPass=new JLabel("Password");
		lblRetype=new JLabel("Retype Password");
		jtfUser=new JTextField();
		jpfPass=new JPasswordField();
		jpfPass.setEchoChar('*');
		jpfRetype=new JPasswordField();
		jpfRetype.setEchoChar('*');
		jtbCreate=new JButton("Create");
		jtbCreate.addActionListener(new ActionListener()
				{ public void actionPerformed(ActionEvent ae)
			{
					String s1=jtfUser.getText();
					String s2=new String(jpfPass.getPassword());
					String s3=new String (jpfRetype.getPassword());
					if(!s2.equals(s3))
					{
						JOptionPane.showMessageDialog(FrmSignUp.this,"Password and retype Password does not match");
					}
					DConnection dc=new DConnection();
					int cnt=dc.executeOtherQuery("insert into users values('"+s1+"','"+s2+"','Admin')");
					if(cnt==1)
					{
						JOptionPane.showMessageDialog(FrmSignUp.this,"Account Created Successfully");
					}
					dispose();
					new FrmLogin();
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
			        		jpfRetype.setEchoChar((char)0);
			        	}
			        	else
			        	{jpfPass.setEchoChar('*');
		        		jpfRetype.setEchoChar('*');
			        	}
			         }
				});
		add(lblUser); add(jtfUser);
		add(lblPass); add(jpfPass);
		add(lblRetype);add(jpfRetype);
		add(jtbCreate);add(jtbClose);
		add(jcb);
		setTitle("Sign Up");
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	public static void main(String args[])
	{
		new FrmSignUp();
	}

}
