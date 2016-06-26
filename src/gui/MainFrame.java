package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import languages.Translator;
import logic.Const;
import logic.ListaPytan;
import logic.Question;
import logic.ReadJson;

import javax.swing.JLabel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JList;

public class MainFrame extends JFrame implements Translator, Const {
	ListaPytan lp;
	private JPanel contentPane;

	JTextPane txlblPytanie;
	JTextPane textPaneA;
	JTextPane textPaneB;
	JTextPane textPaneC;
	JTextPane textPaneD;

	Question aktualnePytanie;
	int aktualnyIndeks;

	// int wszystkie = 0;
	int niepoprawne = 0;
	int poprawne = 0;
	JCheckBox chckbxNewCheckBox;
	JList list;
	JRadioButton rdbtnNewRadioButton_1;

	public MainFrame() {
		setResizable(false);
		setTitle(APP_TITLE);
		model = new DefaultListModel<Question>();
		lp = ReadJson.getData();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 445, 397);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JButton btnNewButton = new JButton("Nastepne");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nextQuestion();
			}
		});
		btnNewButton.setIcon(null);
		menuBar.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Odpowiedz");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setAnswer(aktualnePytanie.getPoprawne());
			}
		});
		menuBar.add(btnNewButton_1);

		chckbxNewCheckBox = new JCheckBox("Podgl\u0105d pyta\u0144");
		menuBar.add(chckbxNewCheckBox);
		chckbxNewCheckBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (chckbxNewCheckBox.isSelected()) {
					setBounds(100, 100, 742, 397);
				} else {
					setBounds(100, 100, 445, 397);
				}
			}
		});
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
				}
				rf();
			}
		});
		btnNewButtonA.setBounds(367, 85, 66, 37);
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
				}
				rf();
			}

		});
		buttonB.setBounds(367, 133, 66, 37);
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

				}
				rf();
			}
		});
		buttonC.setBounds(367, 181, 66, 37);
		contentPane.add(buttonC);

		txlblPytanie = new JTextPane();
		txlblPytanie.setEditable(false);
		txlblPytanie.setText("lblPytanie");
		txlblPytanie.setBounds(10, 7, 423, 72);
		contentPane.add(txlblPytanie);

		textPaneA = new JTextPane();
		textPaneA.setBounds(10, 85, 347, 37);
		contentPane.add(textPaneA);

		textPaneB = new JTextPane();
		textPaneB.setBounds(10, 133, 347, 37);
		contentPane.add(textPaneB);

		textPaneC = new JTextPane();
		textPaneC.setBounds(10, 181, 347, 37);
		contentPane.add(textPaneC);

		textPaneD = new JTextPane();
		textPaneD.setBounds(10, 229, 347, 37);
		contentPane.add(textPaneD);

		JButton buttonD = new JButton("D");
		buttonD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (aktualnePytanie.getPoprawne().equals("d")) {
					poprawne++;
					nastepnePytanie();
				} else {
					niepoprawne++;
					setAnswer(aktualnePytanie.getPoprawne());
				}
				rf();
			}
		});
		buttonD.setBounds(367, 229, 66, 37);
		contentPane.add(buttonD);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(443, 7, 293, 335);
		contentPane.add(scrollPane);

		list = new JList(model);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList list = (JList) e.getSource();

				aktualnePytanie = lp.lp.get(list.getSelectedIndex());

				txlblPytanie.setText(aktualnePytanie.getPytanie());
				textPaneA.setText(aktualnePytanie.getA());
				textPaneB.setText(aktualnePytanie.getB());
				textPaneC.setText(aktualnePytanie.getC());
				textPaneD.setText(aktualnePytanie.getD());
				rf();
				setAnswer(EMPTY);
			}
		});
		// list.addMouseListener(new MouseAdapter() {
		// public void mouseClicked(MouseEvent evt) {
		// JList list = (JList) evt.getSource();
		//
		// aktualnePytanie = lp.lp.get(list.getSelectedIndex());
		//
		// txlblPytanie.setText(aktualnePytanie.getPytanie());
		// textPaneA.setText(aktualnePytanie.getA());
		// textPaneB.setText(aktualnePytanie.getB());
		// textPaneC.setText(aktualnePytanie.getC());
		//
		// rf();
		//
		// }
		// });
		scrollPane.setViewportView(list);

		lblWszystkieOpowiedzi = new JLabel("Wszystkie opowiedzi:");
		lblWszystkieOpowiedzi.setBounds(10, 277, 135, 14);
		contentPane.add(lblWszystkieOpowiedzi);

		lblProcentPoprawnych = new JLabel("Procent poprawnych:");
		lblProcentPoprawnych.setBounds(10, 302, 135, 14);
		contentPane.add(lblProcentPoprawnych);

		lblWszystkie = new JLabel("New label");
		lblWszystkie.setBounds(155, 277, 46, 14);
		contentPane.add(lblWszystkie);

		label = new JLabel("New label");
		label.setBounds(155, 302, 46, 14);
		contentPane.add(label);

		rdbtnNewRadioButton_1 = new JRadioButton("W kolejno\u015Bci");
		rdbtnNewRadioButton_1.setBounds(299, 298, 114, 23);
		rdbtnNewRadioButton_1.setSelected(true);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Losowo");
		rdbtnNewRadioButton.setBounds(299, 273, 82, 23);
		nastepnePytanie();

		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnNewRadioButton_1);
		bg.add(rdbtnNewRadioButton);

		contentPane.add(rdbtnNewRadioButton_1);
		contentPane.add(rdbtnNewRadioButton);
		repaintList();
	}

	int kolejkaTestow = -1;
	private JLabel lblWszystkieOpowiedzi;
	private JLabel lblProcentPoprawnych;
	private JLabel lblWszystkie;
	private JLabel label;
	private JMenuBar menuBar;

	DefaultListModel<Question> model;

	private void nastepnePytanie() {
		nextQuestion();
	}

	private void nextQuestion() {
		if (rdbtnNewRadioButton_1.isSelected()) {
			kolejkaTestow++;
			if (kolejkaTestow < lp.lp.size()) {
				aktualnePytanie = lp.lp.get(kolejkaTestow);

				txlblPytanie.setText(aktualnePytanie.getPytanie());
				textPaneA.setText(aktualnePytanie.getA());
				textPaneB.setText(aktualnePytanie.getB());
				textPaneC.setText(aktualnePytanie.getC());
				textPaneD.setText(aktualnePytanie.getD());

				rf();

			} else {
				kolejkaTestow = 0;
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
			textPaneD.setText(aktualnePytanie.getD());

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
			textPaneD.setForeground(Color.RED);
		}
			break;
		case ANS_B: {
			textPaneA.setForeground(Color.RED);
			textPaneB.setForeground(Color.GREEN);
			textPaneC.setForeground(Color.RED);
			textPaneD.setForeground(Color.RED);
		}
			break;
		case ANS_C: {
			textPaneA.setForeground(Color.RED);
			textPaneB.setForeground(Color.RED);
			textPaneC.setForeground(Color.GREEN);
			textPaneD.setForeground(Color.RED);
		}
			break;
		case ANS_D: {
			textPaneA.setForeground(Color.RED);
			textPaneB.setForeground(Color.RED);
			textPaneC.setForeground(Color.RED);
			textPaneD.setForeground(Color.GREEN);
		}
			break;
		default: {
			textPaneA.setForeground(Color.BLACK);
			textPaneB.setForeground(Color.BLACK);
			textPaneC.setForeground(Color.BLACK);
			textPaneD.setForeground(Color.BLACK);
		}
			break;
		}

	}

	private void repaintList() {

		model.clear();

		for (Question p : lp.lp) {
			model.addElement(p);
		}
	}

	private void rf() {
		lblWszystkie.setText("" + (niepoprawne + poprawne));
		label.setText("" + ((double) poprawne / (niepoprawne + poprawne)));
		repaint();
	}
}
