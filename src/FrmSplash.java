import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
public class FrmSplash extends JFrame{
	FrmSplash()
	{
		ImageIcon img;
		JLabel lbl;
		JProgressBar jpb;
		setUndecorated(true);
		img=new ImageIcon("images/Hotel-Management.jpg");
		img=new ImageIcon(img.getImage().getScaledInstance(390,270,Image.SCALE_DEFAULT));
		lbl=new JLabel(img);
		add(lbl);
		jpb=new JProgressBar(JProgressBar.HORIZONTAL,0,100);
		jpb.setForeground(Color.blue);
		jpb.setBackground(Color.CYAN);
		add(jpb,"South");
		setSize(400,300);
		setLocationRelativeTo(null);
		setVisible(true);
		for(int i=0;i<=100;i++)
		{
			jpb.setValue(i);
			try {
				Thread.sleep(50);
				}
			catch(InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		CommonMethods.createAllTables();
		try {
		dispose();
		DConnection dc=new DConnection();
		ResultSet rst=dc.executeSelectQuery("select count(*) from users");
		rst.next();
		int cnt=rst.getInt(1);
		dc.close();
		
		if(cnt==0)
			new FrmSignUp();
		else
			new FrmLogin();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		//if()
	}

	public static void main(String[] args) 
	{
		new FrmSplash();
	}

}
