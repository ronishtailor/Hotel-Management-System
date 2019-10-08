import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
public class FrmMainFrame extends JFrame implements ActionListener {
JMenuBar mbar;
JMenu mnuFile,mnuMasters,mnuTransaction,mnuReports,mnuHelp;
JMenuItem mitAddUsers,mitHotelDetails,mitSettings,mitExit;
JMenuItem mitRoomCategory,mitRoom,mitGuest,mitStaff;
JMenuItem mitBooking,mitCheckIn,mitAddAdditionalCharges,mitBilling;
JToolBar jtb;
JDesktopPane jdp;
	FrmMainFrame(String userId,String userType)
{
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setTitle("Hotel Management System: Welcome"+userId);
	mbar=new JMenuBar();
	mnuFile=new JMenu("File");
	mnuMasters=new JMenu("Masters");
	mnuTransaction=new JMenu("Transaction");
	mnuReports=new JMenu("Reports");
	mnuHelp=new JMenu("Help");
	
	mitAddUsers=new JMenuItem("Add Users");
	mitHotelDetails=new JMenuItem("Hotel details");
	mitSettings=new JMenuItem("Settings");
	mitExit=new JMenuItem("Exit");
	
	mitRoomCategory=new JMenuItem("Room Category");
	mitRoom=new JMenuItem("Room");
	mitGuest=new JMenuItem("Guest");
	mitStaff=new JMenuItem("Staff");
	
	mitBooking=new JMenuItem("Booking");
	mitCheckIn=new JMenuItem("CheckIn");
	mitAddAdditionalCharges=new JMenuItem("Add Additional Charges");
	mitBilling=new JMenuItem("Billing");
	
	mitAddUsers.addActionListener(this);
	mitHotelDetails.addActionListener(this);
	mitSettings.addActionListener(this);
	mitExit.addActionListener(this);
	
	mitRoomCategory.addActionListener(this);
	mitRoom.addActionListener(this);
	mitGuest.addActionListener(this);
	mitStaff.addActionListener(this);
	
	mitBooking.addActionListener(this);
	mitCheckIn.addActionListener(this);
	mitAddAdditionalCharges.addActionListener(this);
	mitBilling.addActionListener(this);
	
	mnuFile.add(mitAddUsers);
	mnuFile.add(mitHotelDetails);
	mnuFile.add(mitSettings);
	mnuFile.addSeparator();
	mnuFile.add(mitExit);
	
	mnuMasters.add(mitRoomCategory);
	mnuMasters.add(mitRoom);
	mnuMasters.addSeparator();
	mnuMasters.add(mitGuest);
	mnuMasters.add(mitStaff);
	
	
	mnuTransaction.add(mitBooking);
	mnuTransaction.add(mitCheckIn);
	mnuTransaction.add(mitAddAdditionalCharges);
	mnuTransaction.addSeparator();
	mnuTransaction.add(mitBilling);
	
	jtb=new JToolBar();
	JButton btnBooking=new JButton("Booking");
	JButton btnAddCharges=new JButton("Add Charges");
	JButton btnFinalBill=new JButton("Final Bill");
	jtb.add(btnBooking);
	jtb.add(btnAddCharges);
	jtb.add(btnFinalBill);
	
	mbar.add(mnuFile);
	mbar.add(mnuMasters);
	mbar.add(mnuTransaction);
	mbar.add(mnuReports);
	mbar.add(mnuHelp);
	setJMenuBar(mbar);
	add(jtb,"North");
	jdp=new MyJDesktopPane();
	add(jdp);
    setVisible(true);
    setExtendedState(MAXIMIZED_BOTH);
}
	public static void main(String args[])
	{
		new FrmMainFrame("Ronish","Admin");
	}
	public void actionPerformed(ActionEvent ae)
	{
	String s1=ae.getActionCommand();
	if(s1.equalsIgnoreCase("Add Users"))
		jdp.add(new FrmUsers(jdp));
	else if(s1.equalsIgnoreCase("hotel details")){
		jdp.add(new FrmHotelDetails(jdp));
	}			
	else if(s1.equalsIgnoreCase("settings")){
		jdp.add(new FrmSettings(jdp));
	}			
	else if(s1.equalsIgnoreCase("exit")){
		System.exit(0);
	}
	else if(s1.equalsIgnoreCase("room category")){
		jdp.add(new FrmRoomCat(jdp));
	}
	else if(s1.equalsIgnoreCase("room")){
		jdp.add(new FrmRoom(jdp));
	}
	else if(s1.equalsIgnoreCase("guest")){
		jdp.add(new FrmGuest(jdp));
	}
	else if(s1.equalsIgnoreCase("staff")){
		jdp.add(new FrmEmployee(jdp));
	}
	else if(s1.equalsIgnoreCase("Booking")){
		jdp.add(new FrmBooking(jdp));
	}
	else if(s1.equalsIgnoreCase("CheckIn")){
		jdp.add(new FrmCheckIn(jdp));
	}
	else if(s1.equalsIgnoreCase("Add Additional Charges")){
		jdp.add(new FrmAdditional(jdp));
	}
	else if(s1.equalsIgnoreCase("Billing")){
		jdp.add(new FrmPrepareBill(jdp));
	}
	
	}
}
class MyJDesktopPane extends JDesktopPane
{
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		ImageIcon ii=new ImageIcon("Images/20.jpg");
		g.drawImage(ii.getImage(),0,0,getSize().width,getSize().height,null);
	}
}
