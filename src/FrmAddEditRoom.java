import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class FrmAddEditRoom extends JInternalFrame{
JLabel lblRoomId,lblRoomNumber,lblRoomDescription,lblCategoryName,lblPerdayRent;
JTextField txtRoomId,txtRoomNumber,txtRoomDescription,txtPerDayRent;
JComboBox<String> jcbSelection;
JButton btnSave,btnClose;
FrmAddEditRoom(FrmRoom frmroom,String query)
{
	super("Add/Edit Room",true,true,true);
	lblRoomId=new JLabel("Room Id");
	lblRoomNumber=new JLabel("Room Number");
	lblRoomDescription=new JLabel("Room Name");
	lblCategoryName=new JLabel("Category Name");
	lblPerdayRent=new JLabel("Per Day Rent");
	
	txtRoomId=new JTextField();
	txtRoomId.setEditable(false);
	txtRoomNumber=new JTextField();
	txtRoomDescription=new JTextField();
	txtPerDayRent=new JTextField();
	jcbSelection=new JComboBox<>(CommonMethods.getAllRoomCategoryName());
	if(query.equals(""))
		txtRoomId.setText(maxRoomId()+1+"");
		else
		{
			try {
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeSelectQuery(query);
			rst.next();
			txtRoomId.setText(rst.getString(1));
			txtRoomNumber.setText(rst.getString(2));
			txtRoomDescription.setText(rst.getString(3));
		    jcbSelection.setSelectedItem(CommonMethods.getRoomCategoryName(rst.getInt(4)));
		    txtPerDayRent.setText(rst.getString(5));
		    dc.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	btnSave=new JButton("Save");
	btnSave.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					String s1=txtRoomId.getText();
					String s2=txtRoomNumber.getText();
					String s3=txtRoomDescription.getText();
					int s4=CommonMethods.getRoomCategoryId((String)jcbSelection.getSelectedItem());
					String s5=txtPerDayRent.getText();
					DConnection dc=new DConnection();
					if(query.equals(""))
					dc.executeOtherQuery("insert into room values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"')");
					else
					dc.executeOtherQuery("update room set roomnumber='"+s2+"',roomname='"+s3+"',roomcategory='"+s4+"',perdayrent='"+s5+"' where roomid='"+s1+"'");
					frmroom.showTable();
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
	setLayout(new GridLayout(6,2));
	add(lblRoomId);add(txtRoomId);
	add(lblRoomNumber);add(txtRoomNumber);
	add(lblRoomDescription);add(txtRoomDescription);
	add(lblCategoryName);add(jcbSelection);
	add(lblPerdayRent);add(txtPerDayRent);
	add(btnSave);add(btnClose);
	setLocation(300,100);
	setSize(400,300);
	setVisible(true);
}
	int maxRoomId()
	{int maxid=0;
		try {
		DConnection dc=new DConnection();
	ResultSet rst=dc.executeSelectQuery("Select max(roomid) from room");
	rst.next();
	 maxid =rst.getInt(1);
	dc.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return maxid;
	}
}
