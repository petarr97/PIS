package controler;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingUtilities;

import Procedure.ProcedureClass;
import model.Korisnik;
import model.TableModel;
import state.WorkingOnTableState;
import view.ApplicationView;
import view.TableView;

public class TipPlacanjControler implements ActionListener {
	ApplicationView view = null;
	TableView centerView = null;
	boolean zabrana = true;
	TableModel tableModel = null;

	@Override
	public void actionPerformed(ActionEvent e) {

		Korisnik.getInstance().setTrenutnaTabela("smjestajneJednice");
		view = (ApplicationView) SwingUtilities.getWindowAncestor((Component) e.getSource());

		centerView = view.getCenterView();
		centerView.removeAll();
		centerView.repaint();
		view.setState(new WorkingOnTableState(view));

		if (e.getActionCommand().equals("prikaz")) {

			ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
			String[] columnNames = null;

			ResultSet rs = ProcedureClass.getInstance().procedura2("{call UCITAJ_TIP_PLACANJA}");

			columnNames = new String[2];
			columnNames[0] = "#ID";
			columnNames[1] = "Naziv";

			int br_redova = 0;

			try {

				int i = 0;
				while (rs.next()) {
					ArrayList<String> pomocna = new ArrayList<String>();
					pomocna.add(rs.getString(1));
					pomocna.add(rs.getString(2));
					data.add(pomocna);
					// pomocna.clear();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			centerView.removeAll();
			centerView.createTable();
			createModel(data, columnNames);
			zabrana = false;

		}
	}

	public void createModel(ArrayList<ArrayList<String>> data, String[] columns) {

		String[][] data1 = new String[data.size()][data.get(0).size()];
		for (int i = 0; i < data.size(); i++) {
			int brojac_stringova = 0;
			for (String string : data.get(i)) {
				data1[i][brojac_stringova++] = string;
			}
		}
		centerView.newModel = new TableModel(data1, columns);
		centerView.update();
	}

}