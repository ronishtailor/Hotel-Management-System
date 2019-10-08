import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
public class FrmUsers extends JInternalFrame {
	 JScrollPane jsp;
	 String heads[]= {"UserName","Password","UserType"};
	 JTable jtb;
	 Object data[][];
	 JButton btnAdd,btnUpdate,btnDelete,btnSearch,btnShowAll;
	 JPanel pan;
	 String condition="";
	FrmUsers(JDesktopPane jdp)
	{
	 super("User Details",true,true,true,true);
	 btnAdd=new JButton("Add");
	 btnAdd.addActionListener(new ActionListener()
			 {
		 public void actionPerformed(ActionEvent ae)
		 {
			 FrmAddEditUsers cf2=new FrmAddEditUsers(FrmUsers.this,"");
			 jdp.add(cf2);
			 jdp.setComponentZOrder(cf2,0);
			 jdp.setComponentZOrder(FrmUsers.this,1);
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
				JOptionPane.showMessageDialog(FrmUsers.this,"No Row selected","",JOptionPane.INFORMATION_MESSAGE);
			}
			 String user=(String)jtb.getValueAt(row,0);
			 FrmAddEditUsers cf2=new FrmAddEditUsers(FrmUsers.this,"select * from users where user='"+user+"'");
			 jdp.add(cf2);
			 jdp.setComponentZOrder(cf2, 0);
			 jdp.setComponentZOrder(FrmUsers.this,1);
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
		 				JOptionPane.showMessageDialog(FrmUsers.this,"No Row selected","",JOptionPane.INFORMATION_MESSAGE);
		 			}
		 			else
		 			{
		 				String user=(String)jtb.getValueAt(row,0);
		 				DConnection dc=new DConnection();
		 				dc.executeOtherQuery("delete from users where user='"+user+"'");
		 				showTable();
		 			}
		 		}
			 });
	 btnSearch=new JButton("Search");
	 btnSearch.addActionListener(new ActionListener()
			 {
		 public void actionPerformed(ActionEvent ae)
		 {
			 FrmSearchUsers fs=new FrmSearchUsers(FrmUsers.this);
			 jdp.add(fs);
			 jdp.setComponentZOrder(fs,0);
			 jdp.setComponentZOrder(FrmUsers.this,1);
		 }
			 }
			 );
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
	 setSize(600,400);
	 setLocation(300,100);
	 showTable();
	 setVisible(true);
	}
 void showTable()
{ try {
	if(jsp!=null)
	remove(jsp);
	DConnection dc=new DConnection();
	dc.connect();
	ResultSet rst=dc.executeSelectQuery("select count(*) from users"+condition);
	rst.next();
	int n=rst.getInt(1);
	dc.close();
	data=new Object[n][3];
	rst=dc.executeSelectQuery("select * from users"+condition);
	for(int i=0;rst.next();i++)
	{
		for(int j=0;j<3;j++)
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
