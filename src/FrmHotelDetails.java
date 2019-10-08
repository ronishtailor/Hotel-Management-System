import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
public class FrmHotelDetails extends JInternalFrame{
	JLabel lblHotelName,lblAddress,lblEmail,lblContact,lblNoOfRooms,lblNoOfFloors,lblStar,lblAmenities,lblGuestRating;
	JTextField txtHotelName,txtAddress,txtEmail,txtContact,txtNoOfRooms,txtNoOfFloors,txtStar,txtAmenities,txtGuestRating;
	JButton btnUpdate,btnClose;
	FrmHotelDetails(JDesktopPane jdp)
	{
		super("Hotel Details",true,true,true,true);
		lblHotelName=new JLabel("Hotel Name");
		lblAddress=new JLabel("Address");
		lblEmail=new JLabel("Email");
		lblContact=new JLabel("Contact");
		lblNoOfRooms=new JLabel("No. of Rooms");
		lblNoOfFloors=new JLabel("No. of Floors");
		lblStar=new JLabel("Star");
		lblAmenities=new JLabel("Amenities");
		lblGuestRating=new JLabel("Guest Rating");
		txtHotelName=new JTextField();
		txtAddress=new JTextField();
		txtEmail=new JTextField();
		txtContact=new JTextField();
		txtNoOfRooms=new JTextField("0");
		txtNoOfFloors=new JTextField("0");
		txtStar=new JTextField();
		txtAmenities=new JTextField();
		txtGuestRating=new JTextField("0.0");
		btnUpdate=new JButton("Update");
		try {
			DConnection dc=new DConnection();
			ResultSet rst=dc.executeSelectQuery("select * from hoteldetails");
			if(rst.next())
			{
				txtHotelName.setText(rst.getString(1));
				txtAddress.setText(rst.getString(2));
				txtEmail.setText(rst.getString(3));
				txtContact.setText(rst.getString(4));
				txtNoOfRooms.setText(rst.getString(5));
				txtNoOfFloors.setText(rst.getString(6));
				txtStar.setText(rst.getString(7));
				txtAmenities.setText(rst.getString(8));
				txtGuestRating.setText(rst.getString(9));
				dc.close();
				
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		btnUpdate.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						String s1=txtHotelName.getText();
						String s2=txtAddress.getText();
						String s3=txtEmail.getText();
						String s4=txtContact.getText();
						String s5=txtNoOfRooms.getText();
						String s6=txtNoOfFloors.getText();
						String s7=txtStar.getText();
						String s8=txtAmenities.getText();
						String s9=txtGuestRating.getText();
						DConnection dc=new DConnection();
						dc.executeOtherQuery("insert into hoteldetails values('"+s1+"','"+s2+"','"+s3+"','"+s4+"','"+s5+"','"+s6+"','"+s7+"','"+s8+"','"+s9+"')");
						dc.close();
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
		setLayout(new GridLayout(10,2));
		add(lblHotelName);add(txtHotelName);
		add(lblAddress);add(txtAddress);
		add(lblEmail);add(txtEmail);
		add(lblContact);add(txtContact);
		add(lblNoOfRooms);add(txtNoOfRooms);
		add(lblNoOfFloors);add(txtNoOfFloors);
		add(lblStar);add(txtStar);
		add(lblAmenities);add(txtAmenities);
		add(lblGuestRating);add(txtGuestRating);
		add(btnUpdate);add(btnClose);
		setVisible(true);
		setLocation(300,100);
		setSize(600,400);
	}
	

}
