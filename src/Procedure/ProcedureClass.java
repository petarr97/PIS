package Procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.swing.JOptionPane;

import dbpackage.DBconnection;
import model.Korisnik;

public class ProcedureClass {

	public static ProcedureClass instance = null;
	public static DBconnection dbConn;
	public static Connection conn;

	public static ProcedureClass getInstance() {
		if (instance == null) {
			instance = new ProcedureClass();
			dbConn = DBconnection.getInstance();
			conn = dbConn.createConnection();
		}
		return instance;
	}

	// Genericka metoda za bilo koju proceduru
	public static <T> ResultSet procedura2(String procedura, T... lista) {

		CallableStatement stm = null;
		ResultSet rs = null;

		try {
			stm = conn.prepareCall(procedura);
			int i = 1;
			for (T object : lista) {
				stm.setObject(i++, object);
			}
			stm.execute();
			rs = stm.getResultSet();
			Korisnik.getInstance().uspjesno = true;
		} catch (SQLIntegrityConstraintViolationException error) {
			JOptionPane.showMessageDialog(null, error.toString());
			Korisnik.getInstance().uspjesno = false;
		} catch (NullPointerException error) {
			JOptionPane.showMessageDialog(null, error.toString());
			Korisnik.getInstance().uspjesno = false;
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error.toString());
			Korisnik.getInstance().uspjesno = false;
		}
		return rs;
	}

}
