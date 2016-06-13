package sipy;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JCheckBox;

public class Gui extends JFrame
{
	ListaPytan lp;
	private JPanel contentPane;

	JTextPane txlblPytanie;

	JTextPane textPaneA;

	JTextPane textPaneB;

	JTextPane textPaneC;
	JTextPane textUWAGI;

	Pytanie aktualnePytanie;
	int aktualnyIndeks;

	//int wszystkie = 0;
	int niepoprawne = 0;
	int poprawne = 0;

	JCheckBox chckbxPytaniaWKolejnosci;

	public Gui()
	{
		setTitle("Bakszoraizer");
		lp = ReadJson.getData();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 348);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButtonA = new JButton("A");
		btnNewButtonA.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (aktualnePytanie.getPoprawne().equals("a"))
				{
					poprawne++;
					nastepnePytanie();
				} else
				{
					niepoprawne++;
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}
		});
		btnNewButtonA.setBounds(305, 165, 128, 37);
		contentPane.add(btnNewButtonA);

		JButton buttonB = new JButton("B");
		buttonB.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (aktualnePytanie.getPoprawne().equals("b"))
				{
					poprawne++;
					rf();
					nastepnePytanie();
				} else
				{
					niepoprawne++;
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}

		});
		buttonB.setBounds(305, 213, 128, 37);
		contentPane.add(buttonB);

		JButton buttonC = new JButton("C");
		buttonC.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				if (aktualnePytanie.getPoprawne().equals("c"))
				{
					poprawne++;
					nastepnePytanie();
				} else
				{
					niepoprawne++;
					textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
				}
				rf();
			}
		});
		buttonC.setBounds(305, 261, 128, 37);
		contentPane.add(buttonC);

		JButton btnLosuj = new JButton("Nastepne");
		btnLosuj.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{

				nastepnePytanie();

			}
		});
		btnLosuj.setBounds(10, 11, 89, 23);
		contentPane.add(btnLosuj);

		JButton btnOdpowiedz = new JButton("Odpowiedz");
		btnOdpowiedz.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				textUWAGI.setText("Poprawna odpowiedz to: " + aktualnePytanie.getPoprawne());
			}
		});
		btnOdpowiedz.setBounds(109, 11, 109, 23);
		contentPane.add(btnOdpowiedz);

		txlblPytanie = new JTextPane();
		txlblPytanie.setEditable(false);
		txlblPytanie.setText("lblPytanie");
		txlblPytanie.setBounds(10, 82, 423, 72);
		contentPane.add(txlblPytanie);

		textPaneA = new JTextPane();
		textPaneA.setBounds(10, 165, 288, 37);
		contentPane.add(textPaneA);

		textPaneB = new JTextPane();
		textPaneB.setBounds(10, 213, 288, 37);
		contentPane.add(textPaneB);

		textPaneC = new JTextPane();
		textPaneC.setBounds(10, 261, 288, 37);
		contentPane.add(textPaneC);

		textUWAGI = new JTextPane();
		textUWAGI.setForeground(Color.RED);
		textUWAGI.setBounds(10, 45, 208, 26);
		contentPane.add(textUWAGI);

		lblWszystkieOpowiedzi = new JLabel("Wszystkie opowiedzi:");
		lblWszystkieOpowiedzi.setBounds(228, 33, 129, 19);
		contentPane.add(lblWszystkieOpowiedzi);

		lblProcentPoprawnych = new JLabel("Procent poprawnych:");
		lblProcentPoprawnych.setBounds(228, 53, 129, 23);
		contentPane.add(lblProcentPoprawnych);

		lblWszystkie = new JLabel("New label");
		lblWszystkie.setBounds(366, 35, 46, 14);
		contentPane.add(lblWszystkie);

		label = new JLabel("New label");
		label.setBounds(367, 57, 46, 14);
		contentPane.add(label);

		chckbxPytaniaWKolejnosci = new JCheckBox("w kolejnosci / losowo");
		chckbxPytaniaWKolejnosci.setBounds(224, 11, 209, 23);
		contentPane.add(chckbxPytaniaWKolejnosci);
		nastepnePytanie();
	}

	int kolejkaTestow = 0;
	private JLabel lblWszystkieOpowiedzi;
	private JLabel lblProcentPoprawnych;
	private JLabel lblWszystkie;
	private JLabel label;

	private void nastepnePytanie()
	{
		if (!chckbxPytaniaWKolejnosci.isSelected())
		{
			kolejkaTestow++;
			if (kolejkaTestow < lp.lp.size())
			{
				aktualnePytanie = lp.lp.get(kolejkaTestow);

				txlblPytanie.setText(aktualnePytanie.getPytanie());
				textPaneA.setText(aktualnePytanie.getA());
				textPaneB.setText(aktualnePytanie.getB());
				textPaneC.setText(aktualnePytanie.getC());
				textUWAGI.setText("");

				rf();

			} else
			{
				kolejkaTestow = 1;
				nastepnePytanie();
			}
		} else
		{
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
		repaint();
	}

	private void rf()
	{
		lblWszystkie.setText("" + (niepoprawne + poprawne));
		label.setText("" + ((double) poprawne / (niepoprawne + poprawne)));
		repaint();
	}
}
