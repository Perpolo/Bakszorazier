package sipy;

import javax.swing.JOptionPane;

public class Custom
{
	public static void showInfo(String message)
	{
		JOptionPane.showMessageDialog(null, message,"",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public static void showError(String message)
	{
		JOptionPane.showMessageDialog(null, message, "",
				JOptionPane.ERROR_MESSAGE);
	}
}
