import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmRoomCatSearch extends JInternalFrame {
JLabel lblSearchBy,lblValue;
JTextField txtValue;
JComboBox<String> jcbSearchBy;
JButton btnSearch,btnClose;
FrmRoomCatSearch(FrmRoomCat frmroomcat)
{
	super("Room category search",true,true,true,true);
	lblSearchBy=new JLabel("Search By");
	lblValue=new JLabel("Value");
	jcbSearchBy=new JComboBox<String>();
	jcbSearchBy.addItem("categoryid");
	jcbSearchBy.addItem("categoryname");
	btnSearch=new JButton("Search");
	btnSearch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
				   String Field=(String)jcbSearchBy.getSelectedItem();
				   String Value=txtValue.getText();
				   DConnection dc=new DConnection();
				   frmroomcat.condition=" where "+Field+" = '"+Value+"'";
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
			});
	txtValue=new JTextField();
	setLayout(new GridLayout(3,2));
	add(lblSearchBy);add(jcbSearchBy);
	add(lblValue);add(txtValue);
	add(btnSearch);add(btnClose);
	setSize(400,200);
	setLocation(300,100);
	setVisible(true);
}
}
