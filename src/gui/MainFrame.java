package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import languages.Translator;
import logic.Const;
import logic.ListaPytan;
import logic.Question;
import logic.ReadJson;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.Button;

public class MainFrame extends JFrame implements Translator, Const {
	ListaPytan lp;
	private JPanel contentPane;

	JTextPane txlblPytanie;
	JTextPane textPaneA;
	JTextPane textPaneB;
	JTextPane textPaneC;
	JTextPane textUWAGI;

	Question aktualnePytanie;
	int aktualnyIndeks;

	// int wszystkie = 0;
	int niepoprawne = 0;
	int poprawne = 0;

	JCheckBox chckbxPytaniaWKolejnosci;
	JCheckBox chckbxNewCheckBox;

	public MainFrame() {
		setResizable(false);
		setTitle(APP_TITLE);
		lp = ReadJson.getData();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 660, 351);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setIcon(null);
		menuBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		menuBar.add(btnNewButton_1);
		
		JMenu mnNewMenu = new JMenu("New menu");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmWczytajDane = new JMenuItem("Wczytaj dane");
		mnNewMenu.add(mntmWczytajDane);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("New menu item");
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonA = new JButton("A");
		btnNewButtonA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (aktualnePytanie.getPoprawne().equals("a")) {
					poprawne++;
					nastepnePytanie();
				} else {
					niepoprawne++;
					setAnswer(aktualnePytanie.getPoprawne());
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}
		});
		btnNewButtonA.setBounds(305, 172, 128, 37);
		contentPane.add(btnNewButtonA);

		JButton buttonB = new JButton("B");
		buttonB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aktualnePytanie.getPoprawne().equals("b")) {
					poprawne++;
					rf();
					nastepnePytanie();
				} else {
					niepoprawne++;
					setAnswer(aktualnePytanie.getPoprawne());
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}

		});
		buttonB.setBounds(305, 220, 128, 37);
		contentPane.add(buttonB);

		JButton buttonC = new JButton("C");
		buttonC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (aktualnePytanie.getPoprawne().equals("c")) {
					poprawne++;
					nastepnePytanie();
				} else {
					niepoprawne++;
					setAnswer(aktualnePytanie.getPoprawne());
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}
		});
		buttonC.setBounds(305, 268, 128, 37);
		contentPane.add(buttonC);

		JButton btnLosuj = new JButton("Nastepne");
		btnLosuj.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				nastepnePytanie();

			}
		});
		btnLosuj.setBounds(10, 18, 89, 23);
		contentPane.add(btnLosuj);

		JButton btnOdpowiedz = new JButton("Odpowiedz");
		btnOdpowiedz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setAnswer(aktualnePytanie.getPoprawne());
				textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
			}
		});
		btnOdpowiedz.setBounds(109, 18, 109, 23);
		contentPane.add(btnOdpowiedz);

		txlblPytanie = new JTextPane();
		txlblPytanie.setEditable(false);
		txlblPytanie.setText("lblPytanie");
		txlblPytanie.setBounds(10, 89, 423, 72);
		contentPane.add(txlblPytanie);

		textPaneA = new JTextPane();
		textPaneA.setBounds(10, 172, 288, 37);
		contentPane.add(textPaneA);

		textPaneB = new JTextPane();
		textPaneB.setBounds(10, 220, 288, 37);
		contentPane.add(textPaneB);

		textPaneC = new JTextPane();
		textPaneC.setBounds(10, 268, 288, 37);
		contentPane.add(textPaneC);

		textUWAGI = new JTextPane();
		textUWAGI.setForeground(Color.RED);
		textUWAGI.setBounds(10, 52, 208, 26);
		contentPane.add(textUWAGI);

		lblWszystkieOpowiedzi = new JLabel("Wszystkie opowiedzi:");
		lblWszystkieOpowiedzi.setBounds(228, 40, 129, 19);
		contentPane.add(lblWszystkieOpowiedzi);

		lblProcentPoprawnych = new JLabel("Procent poprawnych:");
		lblProcentPoprawnych.setBounds(228, 60, 129, 23);
		contentPane.add(lblProcentPoprawnych);

		lblWszystkie = new JLabel("New label");
		lblWszystkie.setBounds(366, 42, 46, 14);
		contentPane.add(lblWszystkie);

		label = new JLabel("New label");
		label.setBounds(367, 64, 46, 14);
		contentPane.add(label);

		chckbxPytaniaWKolejnosci = new JCheckBox("w kolejnosci / losowo");
		chckbxPytaniaWKolejnosci.setBounds(224, 18, 128, 23);
		contentPane.add(chckbxPytaniaWKolejnosci);

		chckbxNewCheckBox = new JCheckBox("Ustawienia zaawansowane");
		chckbxNewCheckBox.setBounds(361, 18, 74, 23);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					setBounds(100, 100, 630, 351);
				} else {
					setBounds(100, 100, 446, 351);
				}
			}
		});
		contentPane.add(chckbxNewCheckBox);
		nastepnePytanie();
	}

	int kolejkaTestow = 0;
	private JLabel lblWszystkieOpowiedzi;
	private JLabel lblProcentPoprawnych;
	private JLabel lblWszystkie;
	private JLabel label;
	private JMenuBar menuBar;

	private void nastepnePytanie() {
		if (!chckbxPytaniaWKolejnosci.isSelected()) {
			kolejkaTestow++;
			if (kolejkaTestow < lp.lp.size()) {
				aktualnePytanie = lp.lp.get(kolejkaTestow);

				txlblPytanie.setText(aktualnePytanie.getPytanie());
				textPaneA.setText(aktualnePytanie.getA());
				textPaneB.setText(aktualnePytanie.getB());
				textPaneC.setText(aktualnePytanie.getC());
				textUWAGI.setText("");

				rf();

			} else {
				kolejkaTestow = 1;
				nastepnePytanie();
			}
		} else {
			Random newRandom = new Random();
			kolejkaTestow = newRandom.nextInt(lp.lp.size());

			aktualnePytanie = lp.lp.get(kolejkaTestow);

			txlblPytanie.setText(aktualnePytanie.getPytanie());
			textPaneA.setText(aktualnePytanie.getA());
			textPaneB.setText(aktualnePytanie.getB());
			textPaneC.setText(aktualnePytanie.getC());
			textUWAGI.setText("");

			rf();
		}
		setAnswer(EMPTY);
		repaint();
	}

	private void setAnswer(String poprawne) {

		switch (poprawne) {
		case ANS_A: {
			textPaneA.setForeground(Color.GREEN);
			textPaneB.setForeground(Color.RED);
			textPaneC.setForeground(Color.RED);
		}
			break;
		case ANS_B: {
			textPaneA.setForeground(Color.RED);
			textPaneB.setForeground(Color.GREEN);
			textPaneC.setForeground(Color.RED);
		}
			break;
		case ANS_C: {
			textPaneA.setForeground(Color.RED);
			textPaneB.setForeground(Color.RED);
			textPaneC.setForeground(Color.GREEN);
		}
			break;
		default:{
			textPaneA.setForeground(Color.BLACK);
			textPaneB.setForeground(Color.BLACK);
			textPaneC.setForeground(Color.BLACK);
		}
			break;
		}

	}

	private void rf() {
		lblWszystkie.setText("" + (niepoprawne + poprawne));
		label.setText("" + ((double) poprawne / (niepoprawne + poprawne)));
		repaint();
	}
}
