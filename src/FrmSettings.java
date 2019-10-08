import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class FrmSettings extends JInternalFrame {
JTextField txt;
FrmSettings(JDesktopPane jdp)
{
	super("Settings",true,true,true,true);
	txt=new JTextField("Will do later");
	add(txt);
	setLocation(300,100);
	setSize(600,400);
	setVisible(true);
}
}
