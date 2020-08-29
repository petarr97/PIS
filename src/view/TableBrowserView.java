/***********************************************************************
 * Module:  TableBrowserView.java
 * Author:  Petar
 * Purpose: Defines the Class TableBrowserView
 ***********************************************************************/

package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controler.FinansijeControler;
import controler.PoslovniSistemiControler;
import controler.ReportsControler;
import controler.RezervacijeControler;
import controler.SmjestajnaJedinicaControler;
import controler.TipPlacanjControler;
import controler.ZaposleniControler;
import model.TableBrowserModel;

public class TableBrowserView extends JPanel {
	public TableBrowserModel tableBrowserModel;
	public JButton[] buttons;

	public TableBrowserView() {
		setPreferredSize(new Dimension(250, 0));
		setBackground(Color.decode("#303030"));
		Border border = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		setBorder(border);

		JPanel details = new JPanel();
		details.setOpaque(false);
		details.setLocation(new Point(0, 0));
		details.setPreferredSize(new Dimension(0, 0));
		details.setBackground(Color.decode("#303030"));
		Border border1 = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		details.setBorder(border1);

		buttons = addButtons();
		int brojac = 0;

		for (JButton btn : buttons) {

			btn.setPreferredSize(new Dimension(230, 30));
			btn.addActionListener(dodavanjeActionListenera(details)[brojac++]);
			btn.setBackground(Color.decode("#66b3ff"));
			btn.setUI(new StyledButtonUI());
			add(btn);
		}
		add(details);

	}

	public JButton[] addButtons() {
		JButton[] buttons = new JButton[7];

		buttons[0] = new JButton("Narudzbe");
		buttons[1] = new JButton("Osoblje");
		buttons[2] = new JButton("Kupci");
		buttons[3] = new JButton("Proizvodi");
		buttons[4] = new JButton("Stavke narudzbe");
		buttons[5] = new JButton("Mjesto");
		buttons[6] = new JButton("Tip Placanja");

		return buttons;
	}

	public JButton[] otvoriRadSaPoslovnimSistemima() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz svih narudzbi");
		buttons[0].addActionListener(new PoslovniSistemiControler());
		buttons[0].setActionCommand("prikaz");
		buttons[1] = new JButton("Dodavanje nove narudzbe");
		buttons[1].addActionListener(new PoslovniSistemiControler());
		buttons[1].setActionCommand("dodavanje");

		return buttons;
	}

	public JButton[] otvoriRadSaPlacanjem() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz svih tipova placanja");
		buttons[0].addActionListener(new TipPlacanjControler());
		buttons[0].setActionCommand("prikaz");
		buttons[1] = new JButton("Dodavanje novog tipa placanja");
		buttons[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeFrame dF = new DodavanjeFrame();
				dF.dodavanjeTipaPlacanja();
				dF.show();

			}
		});
		buttons[1].setActionCommand("dodavanje");

		return buttons;
	}

	public JButton[] otvoriRadSaRadnicima() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz osoblja");
		buttons[0].addActionListener(new SmjestajnaJedinicaControler());
		buttons[0].setActionCommand("prikaz");
		buttons[1] = new JButton("Dodavanje osoblja");
		buttons[1].addActionListener(new SmjestajnaJedinicaControler());
		buttons[1].setActionCommand("dodavanje");

		return buttons;
	}

	public JButton[] otvoriRadSaKupcima() {
		JButton[] buttons = new JButton[2];

		buttons[0] = new JButton("Prikaz kupaca");
		buttons[0].setActionCommand("prikaz");
		buttons[0].addActionListener(new RezervacijeControler());

		buttons[1] = new JButton("Dodavanje kupca");
		buttons[1].setActionCommand("dodavanje");
		buttons[1].addActionListener(new RezervacijeControler());

		return buttons;
	}

	public JButton[] otvoriRadSaOsobljem() {
		JButton[] buttons = new JButton[2];

		buttons[0] = new JButton("Prikaz proizvoda");
		buttons[0].setActionCommand("prikaz");
		buttons[0].addActionListener(new ZaposleniControler());

		buttons[1] = new JButton("Dodavanje zaposlenog");
		buttons[1].setActionCommand("dodavanje");
		buttons[1].addActionListener(new ZaposleniControler());

		return buttons;
	}

	public JButton[] otvoriRadSaIzvjestajima() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz svih mjesta ");
		buttons[0].setActionCommand("prikaz");
		buttons[0].addActionListener(new ReportsControler());
		buttons[1] = new JButton("Dodavanje novog mjesta");
		buttons[1].setActionCommand("dodavanje");
		buttons[1].addActionListener(new ReportsControler());

		return buttons;
	}

	public JButton[] otvoriRadSaFinansijama() {
		JButton[] buttons = new JButton[2];
		buttons[0] = new JButton("Prikaz stavki narudzbe");
		buttons[0].setActionCommand("finansije");
		buttons[0].addActionListener(new FinansijeControler());
		buttons[1] = new JButton("Dodavanje Stavki narduzbe");
		buttons[1].setActionCommand("rezervacije");
		buttons[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DodavanjeFrame df = new DodavanjeFrame();
				df.dodavanjeStavkeNarudzbe();
				df.show();
			}
		});

		return buttons;
	}

	public ActionListener[] dodavanjeActionListenera(JPanel details) {
		ActionListener[] al = new ActionListener[7];

		al[0] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaPoslovnimSistemima();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};

		al[1] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaRadnicima();
				details.removeAll();
				details.revalidate();
				details.repaint();

				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();
			}
		};
		al[2] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaKupcima();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);

				}
				details.revalidate();

			}
		};
		al[3] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaOsobljem();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 30));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};
		al[4] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaFinansijama();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};
		al[5] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaIzvjestajima();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};
		al[6] = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton[] buttons1 = otvoriRadSaPlacanjem();
				details.removeAll();
				details.revalidate();
				details.repaint();
				for (JButton btn1 : buttons1) {
					btn1.setPreferredSize(new Dimension(230, 25));
					btn1.setBackground(Color.decode("#1a8cff"));
					btn1.setUI(new StyledButtonUI());
					details.setPreferredSize(new Dimension(240, 25 * buttons1.length + 20));
					details.setOpaque(false);
					details.add(btn1);
				}
				details.revalidate();

			}
		};

		return al;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Image image;
		try {
			image = ImageIO.read(new File("./img/login.jpg"));
			Image newImage = image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_DEFAULT);
			g.drawImage(newImage, 0, 0, this); // see javadoc for more info on the parameters

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void postaviFilterZaRadnike() {
		JPanel filters = new JPanel();
		filters.setLocation(new Point(0, 300));
		filters.setPreferredSize(new Dimension(0, 80));
		filters.setBackground(Color.decode("#303030"));
		Border border1 = BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(240, 240, 240));
		filters.setLayout(new BoxLayout(filters, BoxLayout.Y_AXIS));
		filters.setBorder(border1);

		JTextField filter = new JTextField();
		filters.add(filter);
		JButton btn = new JButton();
		filters.add(btn);

		add(filters);

	}
}