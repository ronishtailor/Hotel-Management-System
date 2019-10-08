import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class FrmRoom extends JInternalFrame{
String heads[]= {"RoomId","RoomNumber","RoomName","RoomCategory","PerDayRent"};
Object data[][];
JScrollPane jsp;
JTable jtb;
JPanel pan;
JButton btnAdd,btnUpdate,btnDelete,btnSearch,btnShowAll;
String condition="";
FrmRoom(JDesktopPane jdp)
{super("Room Details",true,true,true,true);
	btnAdd=new JButton("Add");
	btnAdd.addActionListener(new ActionListener()
			{public void actionPerformed(ActionEvent ae)
			{
				FrmAddEditRoom cf2=new FrmAddEditRoom(FrmRoom.this,"");
				jdp.add(cf2);
				jdp.setComponentZOrder(cf2,0);
				jdp.setComponentZOrder(FrmRoom.this,1);
			}});
	btnUpdate=new JButton("Update");
	btnUpdate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					int row=jtb.getSelectedRow();
					if(row==-1)
					{
						JOptionPane.showMessageDialog(FrmRoom.this,"No Row Selected","Information",JOptionPane.ERROR_MESSAGE);
					return;
					}
					else
					{
						int roomId=Integer.parseInt((String)jtb.getValueAt(row,0));
					    FrmAddEditRoom cf2=new FrmAddEditRoom(FrmRoom.this,"select * from room where roomid='"+roomId+"'");
					    jdp.add(cf2);
					    jdp.setComponentZOrder(cf2, 0);
					    jdp.setComponentZOrder(FrmRoom.this,1);
					}
				}
			});
	btnDelete=new JButton("Delete");
	btnDelete.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					int row=jtb.getSelectedRow();
					if(row==-1)
					{
						JOptionPane.showMessageDialog(FrmRoom.this,"No Row Selected","Information",JOptionPane.ERROR_MESSAGE);
					return;
					}
					else
					{
					int id=Integer.parseInt((String)jtb.getValueAt(row,0));
					DConnection dc=new DConnection();
					dc.executeOtherQuery("delete from room where roomid='"+id+"'");
					showTable();
					}
				}
			});
	
	
	btnSearch=new JButton("Search");
	btnSearch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					FrmSearchRoom cf=new FrmSearchRoom(FrmRoom.this);
					jdp.add(cf);
					jdp.setComponentZOrder(cf,0);
					jdp.setComponentZOrder(FrmRoom.this,1);
				}
			});
	
	btnShowAll=new JButton("Show All");
	btnShowAll.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			condition="";
			showTable();
		}
	});
	pan=new JPanel();
	pan.setLayout(new GridLayout(1,5));
	pan.add(btnAdd);
	pan.add(btnUpdate);
	pan.add(btnDelete);
	pan.add(btnSearch);
	pan.add(btnShowAll);
	add(pan,"South");
	showTable();
	setSize(600,400);
	setLocation(300,100);
	setVisible(true);
	
}
void showTable()
{if(jsp!=null)
{
	remove(jsp);
}
	try {
	DConnection dc=new DConnection();
	ResultSet rst=dc.executeSelectQuery("select count(*) from room"+condition);
	rst.next();
	int n=rst.getInt(1);
	dc.close();
	data=new Object[n][5];
	rst=dc.executeSelectQuery("select * from room"+condition);
	for(int i=0;rst.next();i++)
	{
		for(int j=0;j<5;j++)
		{
			data[i][j]=rst.getString(j+1);
		}
	}
	jtb=new JTable(data,heads);
	jsp=new JScrollPane(jtb);
	add(jsp);
	revalidate();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
}

}
