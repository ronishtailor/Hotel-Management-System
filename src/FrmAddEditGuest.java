import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmAddEditGuest extends JInternalFrame {
	JLabel lblGuestId,lblGuestName,lblGuestAddress,lblGuestContact,lblGuestDob,lblCity,lblState;
	JTextField jtfGuestId,jtfGuestName,jtfGuestAddress,jtfGuestContact,jtfGuestDob,jtfCity,jtfState;
	JButton btnSave,btnClose;
	String btnLabel="Save";
	FrmAddEditGuest(FrmGuest frmguest,String query)
	{
		super("Add/Edit Guest",true,true,true,true);
		lblGuestId=new JLabel("Guest Id");
		lblGuestName=new JLabel("Guest Name");
		lblGuestAddress=new JLabel("Guest Address");
		lblGuestContact=new JLabel("Guest Contact");
		lblGuestDob=new JLabel("Date Of Birth(yyyy-mm-dd)");
		lblCity=new JLabel("City");
		lblState=new JLabel("State");

		jtfGuestId=new JTextField();
		jtfGuestId.setEnabled(false);
		jtfGuestName=new JTextField();
		jtfGuestAddress=new JTextField();
		jtfGuestContact=new JTextField();
		jtfGuestDob=new JTextField();
		jtfCity=new JTextField();
		jtfState=new JTextField();
		
		if(query.equals(""))
		{
			jtfGuestId.setText(getMaxGuestId()+1+"");
			//jtfGuestName.getText();,jtfGuestAddress,jtfGuestContact,jtfGuestDob,jtfCity,jtfState
		}
		else
		{
			try {
				DConnection dc=new DConnection();
				ResultSet rst=dc.executeSelectQuery(query);
				rst.next();
				
				jtfGuestId.setText(rst.getString(1));
				jtfGuestName.setText(rst.getString(2));
				jtfGuestAddress.setText(rst.getString(3));
				jtfGuestDob.setText(rst.getString(5));
				jtfCity.setText(rst.getString(6));
				jtfState.setText(rst.getString(7));
				jtfGuestContact.setText(rst.getString(4));
				dc.close();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		btnSave=new JButton("Save");
		btnSave.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						String s1=jtfGuestId.getText();
						String s2=jtfGuestName.getText();
						String s3=jtfGuestAddress.getText();
						String s4=jtfGuestContact.getText();
						String s5=jtfGuestDob.getText();
						String s6=jtfCity.getText();
						String s7=jtfState.getText();
						DConnection dc=new DConnection();
						if(query.equals(""))
						dc.executeOtherQuery("insert into guest values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"')");
						else
						dc.executeOtherQuery("update guest set name='"+s2+"',address='"+s3+"',contact='"+s4+"',dob='"+s5+"',city='"+s6+"',state='"+s7+"' where guestid='"+s1+"' ");
						frmguest.showTable();
						dispose();
					}
				});
		btnClose=new JButton("Close");
		btnClose.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						dispose();
					}
				});
		setLayout(new GridLayout(8,2));
		add(lblGuestId);add(jtfGuestId);
		add(lblGuestName);add(jtfGuestName);
		add(lblGuestAddress);add(jtfGuestAddress);
		add(lblGuestContact);add(jtfGuestContact);
		add(lblGuestDob);add(jtfGuestDob);
		add(lblCity);add(jtfCity);
		add(lblState);add(jtfState);
		add(btnSave);add(btnClose);
		setLocation(300,100);
		setSize(400,300);
		setVisible(true);
	}
	  int getMaxGuestId()
	 {int n=0;
	 	try {
	 		DConnection dc=new DConnection();
	 		ResultSet rst=dc.executeSelectQuery("select max(guestid) from guest");
	 		rst.next();
	 		n=rst.getInt(1);
	 		dc.close();
	 		}
	 	catch(SQLException e)
	 	{
	 		e.printStackTrace();
	 	}
	 	return n;
	 }

}
