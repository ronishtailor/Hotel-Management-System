import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmGuest extends JInternalFrame{
String heads[]= {"GuestId","Name","Address","Contact","DOB","City","State"};
JTable jtb;
Object data[][];
JPanel pan;
JButton btnAdd,btnUpdate,btnDelete,btnSearch,btnShowAll;
JScrollPane jsp;
String condition="";
FrmGuest(JDesktopPane jdp)
{
	super("Guests",true,true,true,true);
	btnAdd=new JButton("Add");
	btnAdd.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					FrmAddEditGuest cf2=new FrmAddEditGuest(FrmGuest.this,"");
					jdp.add(cf2);
					jdp.setComponentZOrder(cf2,0);
					jdp.setComponentZOrder(FrmGuest.this,1);
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
						JOptionPane.showMessageDialog(FrmGuest.this,"No Row Selected","Warning",JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					int gid=Integer.parseInt((String)jtb.getValueAt(row,0));
					FrmAddEditGuest cf2=new FrmAddEditGuest(FrmGuest.this,"select * from guest where guestid="+gid);
					jdp.add(cf2);
					jdp.setComponentZOrder(cf2, 0);
					jdp.setComponentZOrder(FrmGuest.this,1);
				}
			});
	btnDelete=new JButton("Delete");
	btnDelete.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			int row=jtb.getSelectedRow();
			if(row==-1) {
				JOptionPane.showMessageDialog(FrmGuest.this, "No record selected");
				return;
			}
			int GuestId=Integer.parseInt((String)jtb.getValueAt(row, 0));
			DConnection dc=new DConnection();
			dc.executeOtherQuery("delete from guest where guestid="+GuestId);
			showTable();
			dc.close();
		}
	});
	btnSearch=new JButton("Search");
	btnSearch.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent ae) {
			FrmGuestSearch cf2=new FrmGuestSearch(FrmGuest.this);
			jdp.add(cf2);
			jdp.setComponentZOrder(cf2, 0);
			jdp.setComponentZOrder(FrmGuest.this, 1);
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
	setLocation(300,100);
	setSize(600,400);
	setVisible(true);
}
 void showTable()
{
		try {
			if(jsp!=null)
			remove(jsp);	
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select count(*) from guest"+condition);
        rst.next();
		int n=rst.getInt(1);
		dc.close();
		data=new Object[n][7];	
		rst=dc.executeSelectQuery("select * from guest"+condition);
		for(int i=0;rst.next();i++)
		{
			for(int j=0;j<7;j++)
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
