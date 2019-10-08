import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmAddEditUsers extends JInternalFrame {

JLabel lblUserId,lblPassword,lblRetypePass,lblUserType;
JTextField jtfUserId,jtfPassword,jtfRetypePass;
JComboBox<String> jcbUserType;
JButton btnSave,btnClose;
FrmAddEditUsers(FrmUsers frmusers,String query)
{	super("AddEditUsers",true,true,true,true);
	lblUserId=new JLabel("User Id");
	lblPassword=new JLabel("Password");
	lblRetypePass=new JLabel("Retype password");
	lblUserType=new JLabel("User Type");
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	jtfUserId=new JTextField();
	jtfPassword=new JTextField();
	jtfRetypePass=new JTextField();
	jcbUserType=new JComboBox<String>();
	jcbUserType.addItem("Admin");
	jcbUserType.addItem("Staff");
	jcbUserType.addItem("Manager");
	setLayout(new GridLayout(5,2));
	btnSave=new JButton("Save");
	if(!query.equals(""))
	{
		jtfUserId.setEditable(false);
	}
	if(!query.equals(""))
	{try {
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery(query);
		rst.next();
		jtfUserId.setText(rst.getString(1));
		jtfPassword.setText(rst.getString(2));
		jtfRetypePass.setText(rst.getString(2));
		jcbUserType.setSelectedItem(rst.getString(3));
		dc.close();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	}
	

	btnSave.addActionListener(new ActionListener()
			{
			public void actionPerformed(ActionEvent ae)
			{	String s1=jtfUserId.getText();
				String s2=jtfPassword.getText();
				String s3=jtfRetypePass.getText();
				String s4=(String)jcbUserType.getSelectedItem();
				try {
						DConnection dc=new DConnection();
						ResultSet rst=dc.executeSelectQuery("select * from users where user='"+s1+"'");
						if(!s2.equals(s3))
							{	dc.close();
								JOptionPane.showMessageDialog(FrmAddEditUsers.this,"Password and Retype password are not same","Warning",JOptionPane.ERROR_MESSAGE);
								return;
							}
						else if(query.equals("")&&rst.next())
							{dc.close();
								JOptionPane.showMessageDialog(FrmAddEditUsers.this,"user already exists","User info.",JOptionPane.INFORMATION_MESSAGE);
								return;
							}
						else
						{dc.close();
							
						if(query.equals(""))
								{
								dc.executeOtherQuery("insert into users values('"+s1+"','"+s2+"','"+s4+"')");
								}
							else
								{	
								dc.executeOtherQuery("update users set pass='"+s2+"',usertype='"+s4+"' where user='"+s1+"'");
								
								}
							frmusers.showTable();
							dispose();
						}
				}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
			});
	btnClose=new JButton("Close");
	btnClose.addActionListener(new ActionListener()
			{
		public void actionPerformed(ActionEvent ae)
		{
			dispose();
		}
			}
	);
	add(lblUserId);add(jtfUserId);
	add(lblPassword);add(jtfPassword);
	add(lblRetypePass);add(jtfRetypePass);
	add(lblUserType);add(jcbUserType);
	add(btnSave);add(btnClose);
	setSize(400,300);
	setVisible(true);
	setLocation(CommonMethods.getCenter(getSize()));
}
}
