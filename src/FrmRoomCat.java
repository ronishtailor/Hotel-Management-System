import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class FrmRoomCat extends JInternalFrame {
JButton btnAdd,btnUpdate,btnDelete,btnSearch,btnShowAll;
JPanel pan;
JTable jtb;
Object data[][];
JScrollPane jsp;
String heads[]= {"Category Id","Category Name","Tv","Fridge","Geyser","Ac"};
String condition="";
FrmRoomCat(JDesktopPane jdp)
{
	super("Room Categories",true,true,true,true);
	btnAdd=new JButton("Add");
	btnAdd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
				FrmAddEditRoomCat cf2=new FrmAddEditRoomCat(FrmRoomCat.this,"");
				jdp.add(cf2);
				jdp.setComponentZOrder(cf2,0);
				jdp.setComponentZOrder(FrmRoomCat.this,1);
				}
			});
	btnUpdate=new JButton("Update");
	btnUpdate.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
				int row=jtb.getSelectedRow();
				if(row==-1)
				{
					JOptionPane.showMessageDialog(FrmRoomCat.this,"No row selected","",JOptionPane.ERROR_MESSAGE);
				return;
				}
				int catid=Integer.parseInt((String)jtb.getValueAt(row,0));
				FrmAddEditRoomCat cf2=new FrmAddEditRoomCat(FrmRoomCat.this,"select * from roomcat where categoryid='"+catid+"'");
				jdp.add(cf2);
				jdp.setComponentZOrder(cf2,0);
				jdp.setComponentZOrder(FrmRoomCat.this,1);
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
						JOptionPane.showMessageDialog(FrmRoomCat.this,"No row selected","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					else
					{DConnection dc=new DConnection();
					String user=(String)jtb.getValueAt(row,0);
					dc.executeOtherQuery("delete from roomcat where categoryid='"+user+"'");
					showTable();
					}
				}
			});
	btnSearch=new JButton("Search");
	btnSearch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					FrmRoomCatSearch cf2=new FrmRoomCatSearch(FrmRoomCat.this);
					jdp.add(cf2);
					jdp.setComponentZOrder(cf2,0);
					jdp.setComponentZOrder(FrmRoomCat.this,1);
					
				}
			});
	btnShowAll=new JButton("Show All");
	btnShowAll.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
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
	setVisible(true);
	setLocation(300,100);
	setSize(600,400);
	
}
void showTable()
{
	try {
		if(jsp!=null)
		{
			remove(jsp);
		}
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select count(*) from roomcat"+condition);
		rst.next();
		int n=rst.getInt(1);
		data=new Object[n][6];
		dc.close();
		rst=dc.executeSelectQuery("select * from roomcat"+condition);
		for(int i=0;rst.next();i++)
		{
			for(int j=0;j<6;j++)
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
