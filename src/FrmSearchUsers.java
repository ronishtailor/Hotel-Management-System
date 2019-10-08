import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmSearchUsers extends JInternalFrame {
JLabel lblSearchBy,lblField;
JTextField txtField;
JComboBox<String> jcbSearchBy;
JButton btnSearch,btnClose;
FrmSearchUsers(FrmUsers frmusers)
{	super("Search",true,true,true,true);
	lblSearchBy=new JLabel("Search By");
	lblField=new JLabel("Field");
	txtField=new JTextField();
	jcbSearchBy=new JComboBox<String>();
	jcbSearchBy.addItem("User");
	jcbSearchBy.addItem("Pass");
	jcbSearchBy.addItem("User type");
	btnSearch=new JButton("Search");
	btnSearch.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					String item=(String)jcbSearchBy.getSelectedItem();
					String value=txtField.getText();
					DConnection dc=new DConnection();
					frmusers.condition=" where "+item+"= '"+value+"'";
					frmusers.showTable();
					dc.close();
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
	setLayout(new GridLayout(3,2));
	add(lblSearchBy);add(jcbSearchBy);
	add(lblField);add(txtField);
	add(btnSearch);add(btnClose);
	setLocation(300,100);
	setSize(400,200);
	setVisible(true);
}
}
