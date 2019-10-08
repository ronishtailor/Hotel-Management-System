import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmAddEditRoomCat extends JInternalFrame {
JLabel lblCatId,lblCatName,lblTv,lblFridge,lblGeyser,lblAc;
JTextField txtCatId,txtCatName;
JComboBox<String> jcbTv,jcbFridge,jcbGeyser,jcbAc;
JButton btnSave,btnClose;
	FrmAddEditRoomCat(FrmRoomCat frmroomcat,String query)
{ 	super("Add Edit Room Category",true,true,true,true);
	lblCatId=new JLabel("Category id");
	lblCatName=new JLabel("Category name");
	lblTv=new JLabel("Tv");
	lblFridge=new JLabel("Fridge");
	lblGeyser=new JLabel("Geyser");
	lblAc=new JLabel("Ac");
	txtCatId=new JTextField();
	txtCatName=new JTextField();
	jcbTv=new JComboBox<String>();
	jcbTv.addItem("Yes");
	jcbTv.addItem("No");
	jcbFridge=new JComboBox<String>();
	jcbFridge.addItem("Yes");
	jcbFridge.addItem("No");
	jcbGeyser=new JComboBox<String>();
	jcbGeyser.addItem("Yes");
	jcbGeyser.addItem("No");
	jcbAc=new JComboBox<String>();
	jcbAc.addItem("Yes");
	jcbAc.addItem("No");
	btnSave=new JButton("Save");
	if(query.equals(""))
	{
		txtCatId.setText(maxid()+1+"");
	}
	else
	{
		try {
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeSelectQuery(query);
			rst.next();
			txtCatId.setText(rst.getString(1));
			txtCatName.setText(rst.getString(2));
			jcbTv.setSelectedItem(rst.getString(3));
			jcbFridge.setSelectedItem(rst.getString(4));
			jcbGeyser.setSelectedItem(rst.getString(5));
			jcbAc.setSelectedItem(rst.getString(6));
		dc.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	DConnection dc=new DConnection();
	btnSave.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					String s1=txtCatId.getText();
					String s2=txtCatName.getText();
					String s3=(String)jcbTv.getSelectedItem();
					String s4=(String)jcbFridge.getSelectedItem();
					String s5=(String)jcbGeyser.getSelectedItem();
					String s6=(String)jcbAc.getSelectedItem();
					if(query.equals(""))
					dc.executeOtherQuery("insert into roomcat values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"')");
					else
					dc.executeOtherQuery("update roomcat set categoryname='"+s2+"',tv='"+s3+"',fridge='"+s4+"',geyser='"+s5+"',ac='"+s6+"' where categoryid="+s1);
					frmroomcat.showTable();
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
			}
	);
	setLayout(new GridLayout(7,2));
	add(lblCatId);add(txtCatId);
	add(lblCatName);add(txtCatName);
	add(lblTv);add(jcbTv);
	add(lblFridge);add(jcbFridge);
	add(lblGeyser);add(jcbGeyser);
	add(lblAc);add(jcbAc);
	add(btnSave);add(btnClose);
	setSize(500,300);
	setLocation(300,100);
	setVisible(true);
}
	int maxid()
	{int max=0;
		try {
			DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select max(categoryid) from roomcat");
		rst.next();
		max=rst.getInt(1);
		dc.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return max;
	}
}
