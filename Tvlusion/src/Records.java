import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Records extends JFrame {

	static Connection objConn;			
	static PreparedStatement objPst;
	static ResultSet objRs = null;
	static Statement objSQLQuery; 
	static String objSql;
	static DefaultTableModel tblModel;
	
	private JPanel contentPane;
	private static JTable table;

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Records frame = new Records();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Records() {
		
		setUndecorated(true);	
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1363, 648);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1349, 638);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 1349, 575);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
			 "Full_Name", "Mobile_no", "Landline_no", "Email", "Birthdate", "Occupation ", "Houshold_inc", "Installation_add", "Marital_status", "Home_own", "Annual_inc", "Sub_type","Employment", "Prepaid_Pck", "Postpaid_PTyp", "Postpaid_POpt", "Postpaid_NBox", "Postpaid_BOpt", "Postpaid_BAdd"
			}
		));
		
		scrollPane.setViewportView(table);
		
		JButton btnShow = new JButton("Load Records");
		btnShow.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		btnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowRecords();
			}
		});
		btnShow.setBackground(Color.WHITE);
		btnShow.setBounds(1195, 595, 144, 33);
		panel.add(btnShow);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontFrame frame = new FrontFrame(); // admin
				dispose();
			}
		});
		btnBack.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		btnBack.setBackground(Color.WHITE);
		btnBack.setBounds(20, 595, 144, 33);
		panel.add(btnBack);
	}
	
static Connection con() {
		
		try {
						
			String strDriver = "com.mysql.cj.jdbc.Driver";
			String strUrl = "jdbc:mysql://localhost:3306/dbproj";
			Class.forName(strDriver);
			System.out.println("connected to the database successfully");
			
			return DriverManager.getConnection(strUrl, "admin1","Admpw2021"); 
			
		}	catch(Exception objE) {
			System.out.println("Connection failed" + objE);
		}			
			return null;			
	}
	
	static void ShowRecords() {
		
		Connection objConn = con();
		tblModel = new DefaultTableModel();
		
		tblModel.addColumn("Full_Name");
		tblModel.addColumn("Mobile_no");
		tblModel.addColumn("Landline_no");
		tblModel.addColumn("Email");
		tblModel.addColumn("Birthdate");
		tblModel.addColumn("Occupation");
		tblModel.addColumn("Household_inc");	
		tblModel.addColumn("Installation_add");
		tblModel.addColumn("Marital_status");
		tblModel.addColumn("Home_own");
		tblModel.addColumn("Annual_inc");
		tblModel.addColumn("Sub_type");
		tblModel.addColumn("Employment");
		tblModel.addColumn("Prepaid_Pck");
		tblModel.addColumn("Postpaid_PTyp");
		tblModel.addColumn("Postpaid_POpt");
		tblModel.addColumn("Postpaid_NBox");
		tblModel.addColumn("Postpaid_BOpt");
		tblModel.addColumn("Postpaid_BAdd");
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);	
		
		try {	

	    objSql = "SELECT * FROM customer";
		objSQLQuery = objConn.createStatement(); 
		objRs = objSQLQuery.executeQuery(objSql); // result set is used to show results of an sql query
		
			while(objRs.next())
			{
				tblModel.addRow(new Object[]
				{
					//objRs.getString("IDNumber"),	
					objRs.getString("Full_Name"),
					objRs.getString("Mobile_no"),
					objRs.getString("Landline_no"),
					objRs.getString("Email"),
					objRs.getString("Birthdate"),
					objRs.getString("Occupation"),
					objRs.getString("Household_inc"),
					objRs.getString("Installation_add"),
					objRs.getString("Marital_status"),
					objRs.getString("Home_own"),
					objRs.getString("Annual_inc"),
					objRs.getString("Sub_type"),
					objRs.getString("Employment"),
					objRs.getString("Prepaid_Pck"),
					objRs.getString("Postpaid_PTyp"),
					objRs.getString("Postpaid_POpt"),
					objRs.getString("Postpaid_NBox"),
					objRs.getString("Postpaid_BOpt"),
					objRs.getString("Postpaid_BAdd"),	
							
				});
			}
			
			objRs.close();
			objSQLQuery.close();
			objConn.close();
			
			table.setModel(tblModel);
			table.setAutoResizeMode(0);
			//table.getColumnModel().getColumn(0).setResizable(false);
			table.getColumnModel().getColumn(0).setPreferredWidth(90);
			table.getColumnModel().getColumn(1).setPreferredWidth(160);
			table.getColumnModel().getColumn(2).setPreferredWidth(90);
			table.getColumnModel().getColumn(3).setPreferredWidth(90);
			table.getColumnModel().getColumn(4).setPreferredWidth(150);
			table.getColumnModel().getColumn(5).setPreferredWidth(90);
			table.getColumnModel().getColumn(6).setPreferredWidth(90);
			table.getColumnModel().getColumn(7).setPreferredWidth(90);
			table.getColumnModel().getColumn(8).setPreferredWidth(170);
			table.getColumnModel().getColumn(9).setPreferredWidth(80);
			table.getColumnModel().getColumn(10).setPreferredWidth(80);
			table.getColumnModel().getColumn(11).setPreferredWidth(80);
			table.getColumnModel().getColumn(12).setPreferredWidth(90);
			table.getColumnModel().getColumn(13).setPreferredWidth(80);
			table.getColumnModel().getColumn(14).setPreferredWidth(80);
			table.getColumnModel().getColumn(15).setPreferredWidth(80);
			table.getColumnModel().getColumn(16).setPreferredWidth(80);
			table.getColumnModel().getColumn(17).setPreferredWidth(79);
			table.getColumnModel().getColumn(18).setPreferredWidth(80);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);	
			
		} catch(Exception objE) {
		 System.out.println("Failed to load data" + objE);
		}
	}
}


