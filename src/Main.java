import java.awt.EventQueue;
import java.util.Locale;
import java.util.ResourceBundle;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) throws Exception {

		// new ReadFile();
//		// new ReadJson();
//		
//		ResourceBundle bundle1 = ResourceBundle.getBundle("TestBundle");
//		displayValues(bundle1);
//
//		Locale defaultLocale = Locale.getDefault();
//		ResourceBundle bundle2 = ResourceBundle.getBundle("TestBundle", defaultLocale);
//		displayValues(bundle2);
//
//		Locale swedishLocale = new Locale("sv", "SE");
//		ResourceBundle bundle3 = ResourceBundle.getBundle("TestBundle", swedishLocale);
//		displayValues(bundle3);
//
//		Locale nonexistentLocale = new Locale("xx", "XX");
//		ResourceBundle bundle4 = ResourceBundle.getBundle("TestBundle", nonexistentLocale);
//		displayValues(bundle4);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private static void displayValues(ResourceBundle bundle2) {
		// TODO Auto-generated method stub
		
	}

}
