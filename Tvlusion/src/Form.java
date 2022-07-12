import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JRadioButton;
import java.awt.Color;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.FlowLayout;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
import java.text.SimpleDateFormat;


public class Form extends JFrame {

	static private Image img_remote = new ImageIcon(FrontFrame.class.getResource("imgs/remote.jpg")).getImage().getScaledInstance(174,610, Image.SCALE_SMOOTH); 


	static Connection objConn;			
	static PreparedStatement objPst;
	static ResultSet objRs = null;
	static Statement objSQLQuery; 
	static String objSql;
	
	private JPanel contentPane;
	static private JTextField txtFullname,txtMobile,txtHouseInc,txtEmail,txtLandline,txtOccupation,txtGrossinc,txtInstallAdd,txtBillAdd;
	private JLabel lblGet,lblStarted,lblForm,lblRemote,lblFNAME,lblMobile,lblLandline,lblOwnership,lblStatus,lblEmployBrd,lblAbout,lblEmployment,lblNote,lblNumbox;
	private JPanel pnlView;
	private JLabel pnlBack;
	private JPanel pnlPlan;
	private JRadioButton rdbtnOwned,rdbtnMortage,rdbtnSingle,rdbtnMarried,rdbtnSelfemployed,rdbtnProfession,rdbtnGovernment,rdbtnRetired,rdbtnPrivate; //about you
	private JRadioButton rdbtnPrepaid,rdbtnSD,rdbtnHD,rdbtnNA; // prepaid options
	private JRadioButton rdbtnPostPaid,rdbtn290,rdbtn420,rdbtn520,rdbtn720,rdbtn1050,rdbtn1350,rdbtn1650,rdbtn1950,rdbtnNA2; // plan options
	private JRadioButton rdbtnHDL,rdbtnFlex,rdbtnNA3; // plan types
	private JRadioButton rdbtn1,rdbtn2,rdbtn3,rdbtn4,rdbtnNA5; // no. of box
	static private ButtonGroup btnStatus,btnOwnership,btnEmployment,btnPlanOption,btnPlanTypes,btnBoxes,btnSubscription,btnPackageOpt,btnBillOpt;
	private JLabel lblPrepaidOpt,lblBrdPrepaid,lblBillOpt;
	private JLabel lblEmail,lblBirth,lblHouseinc,lblOccupation,lblOcc,lblAddr,lblGross,lblBillAdd;
	private JRadioButton rdbtnEmail,rdbtnCour,rdbtnNA6;// bill options 	
	private	JButton btnConfirm, btnTable;
	
	static JDateChooser dateChooser1;
	static String jDate;	// or ilalagay sa obj pst
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form frame = new Form();
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
	public Form() {
		
		setResizable(false);
	//	setTitle("TVlusion");
		
		setUndecorated(true);		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1033, 607);
		setLocationRelativeTo(null); //center tab
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 1032, 609);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 txtFullname.setText("");
				 txtMobile.setText("");
				 txtHouseInc.setText("Household monthly income");
				 txtEmail.setText("");
				 txtLandline.setText("");
				 txtOccupation.setText("");
				 txtGrossinc.setText("");
				 txtInstallAdd.setText("");
				 txtBillAdd.setText("");
				 btnStatus.clearSelection(); 
				 btnOwnership.clearSelection();
				 btnEmployment.clearSelection();
				 btnPlanOption.clearSelection();
				 btnPlanTypes.clearSelection();
				 btnBoxes.clearSelection();
				 btnSubscription.clearSelection();
				 btnPackageOpt.clearSelection();
				 btnBillOpt.clearSelection();
				 dateChooser1.setCalendar(null);
			}
		});
		btnClear.setForeground(Color.WHITE);
		btnClear.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		btnClear.setBorderPainted(false);
		btnClear.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.DARK_GRAY, null));
		btnClear.setBackground(new Color(113, 59, 37));
		btnClear.setBounds(21, 489, 133, 27);
		panel.add(btnClear);
		
		lblForm = new JLabel("S U B S C R I P T I O N    F O R M");
		lblForm.setForeground(new Color(113,59,37));
		lblForm.setFont(new Font("Nirmala UI", Font.BOLD, 20));
		lblForm.setBounds(469, 10, 314, 33);
		panel.add(lblForm);
		
		lblFNAME = new JLabel("Full name :");
		lblFNAME.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblFNAME.setBounds(202, 64, 63, 27);
		panel.add(lblFNAME);
		
		lblMobile = new JLabel("Mobile no :");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblMobile.setBounds(202, 100, 63, 27);
		panel.add(lblMobile);
		
		lblLandline = new JLabel("Landline no :");
		lblLandline.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLandline.setBounds(202, 137, 69, 27);
		panel.add(lblLandline);
		
		lblEmail = new JLabel("Email : ");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(481, 64, 53, 27);
		panel.add(lblEmail);
		
		lblBirth = new JLabel("Birthdate : ");
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBirth.setBounds(480, 100, 63, 27);
		panel.add(lblBirth);
		
		lblHouseinc = new JLabel("Income : ");
		lblHouseinc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblHouseinc.setBounds(481, 137, 63, 27);
		panel.add(lblHouseinc);
		
		lblOccupation = new JLabel("Occupation");
		lblOccupation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOccupation.setBounds(758, 64, 69, 27);
		panel.add(lblOccupation);
		
		lblOcc = new JLabel("Installation");
		lblOcc.setHorizontalAlignment(SwingConstants.LEFT);
		lblOcc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOcc.setBounds(758, 100, 63, 27);
		panel.add(lblOcc);
		
		lblAddr = new JLabel("Address :");
		lblAddr.setHorizontalAlignment(SwingConstants.LEFT);
		lblAddr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAddr.setBounds(758, 126, 63, 27);
		panel.add(lblAddr);
		
		lblAbout = new JLabel("About you :");
		lblAbout.setForeground(Color.GRAY);
		lblAbout.setFont(new Font("Nirmala UI", Font.BOLD | Font.ITALIC, 14));
		lblAbout.setBounds(202, 170, 88, 26);
		panel.add(lblAbout);
		
		txtFullname = new JTextField();
		txtFullname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFullname.setColumns(10);
		txtFullname.setBounds(275, 65, 194, 26);
		panel.add(txtFullname);
		
		txtMobile = new JTextField();
		txtMobile.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMobile.setColumns(10);
		txtMobile.setBounds(276, 101, 194, 26);
		panel.add(txtMobile);
		
		txtLandline = new JTextField();
		txtLandline.setHorizontalAlignment(SwingConstants.LEFT);
		txtLandline.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtLandline.setColumns(10);
		txtLandline.setBounds(276, 137, 194, 26);
		panel.add(txtLandline);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtEmail.setColumns(10);
		txtEmail.setBounds(544, 65, 194, 26);
		panel.add(txtEmail);
		
		txtHouseInc = new JTextField();
		txtHouseInc.addFocusListener(new FocusAdapter() {
		
			@Override
			public void focusGained(FocusEvent e) {
				
				if(txtHouseInc.getText().equals("Household monthly income"))
				{
					txtHouseInc.setText("");
				}
				else
				{
					txtHouseInc.selectAll();
				}
			}
		@Override
			public void focusLost(FocusEvent e) 
			{
			
				if(txtHouseInc.getText().equals(""))
				{
					txtHouseInc.setText("Household monthly income");
				}
					
			}
		});
		
		txtHouseInc.setHorizontalAlignment(SwingConstants.CENTER);
		txtHouseInc.setForeground(Color.DARK_GRAY);
		txtHouseInc.setText("Household monthly income");
		txtHouseInc.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtHouseInc.setColumns(10);
		txtHouseInc.setBounds(544, 137, 195, 26);
		panel.add(txtHouseInc);
		
		txtOccupation = new JTextField();
		txtOccupation.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtOccupation.setColumns(10);
		txtOccupation.setBounds(830, 65, 173, 26);
		panel.add(txtOccupation);
		
		txtInstallAdd = new JTextField();
		txtInstallAdd.setHorizontalAlignment(SwingConstants.LEFT);
		txtInstallAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtInstallAdd.setColumns(10);
		txtInstallAdd.setBounds(830, 101, 173, 62);
		panel.add(txtInstallAdd);
		
		rdbtnSingle = new JRadioButton("Single");
		rdbtnSingle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnSingle.setBackground(Color.WHITE);
		rdbtnSingle.setBounds(312, 203, 55, 21);
		panel.add(rdbtnSingle);
		
		rdbtnMarried = new JRadioButton("Married");
		rdbtnMarried.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnMarried.setBackground(Color.WHITE);
		rdbtnMarried.setBounds(383, 203, 63, 21);
		panel.add(rdbtnMarried);
		
	    lblStatus = new JLabel(" Marital Status:");
	    lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    lblStatus.setBounds(202, 201, 259, 26);
	    lblStatus.setBorder(new LineBorder(Color.LIGHT_GRAY));
	    panel.add(lblStatus);
		
		rdbtnOwned = new JRadioButton("Owned");
		rdbtnOwned.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnOwned.setBackground(Color.WHITE);
		rdbtnOwned.setBounds(312, 239, 69, 21);
		panel.add(rdbtnOwned);
		
		rdbtnMortage = new JRadioButton("Mortaged");
		rdbtnMortage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnMortage.setBackground(Color.WHITE);
		rdbtnMortage.setBounds(383, 239, 76, 21);
		panel.add(rdbtnMortage);
		
		lblOwnership = new JLabel(" Home Ownership:");
		lblOwnership.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblOwnership.setBounds(202, 237, 259, 26);
		lblOwnership.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.add(lblOwnership);
		
		rdbtnSelfemployed = new JRadioButton("Self-Employed");
		rdbtnSelfemployed.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnSelfemployed.setBackground(Color.WHITE);
		rdbtnSelfemployed.setBounds(487, 242, 103, 21);
		panel.add(rdbtnSelfemployed);
		
		rdbtnProfession = new JRadioButton("Profession");
		rdbtnProfession.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnProfession.setBackground(Color.WHITE);
		rdbtnProfession.setBounds(592, 242, 80, 21);
		panel.add(rdbtnProfession);
		rdbtnPrivate = new JRadioButton("Private");
		rdbtnPrivate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPrivate.setBackground(Color.WHITE);
		rdbtnPrivate.setBounds(672, 242, 63, 21);
		panel.add(rdbtnPrivate);
		
		rdbtnGovernment = new JRadioButton("Government");
		rdbtnGovernment.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnGovernment.setBackground(Color.WHITE);
		rdbtnGovernment.setBounds(487, 269, 103, 21);
		panel.add(rdbtnGovernment);
		
		rdbtnRetired = new JRadioButton("Retired");
		rdbtnRetired.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnRetired.setBackground(Color.WHITE);
		rdbtnRetired.setBounds(592, 269, 70, 21);
		panel.add(rdbtnRetired);
		
		btnStatus = new ButtonGroup();					// button group1 for Marital Status
		rdbtnSingle.setActionCommand(rdbtnSingle.getText());
		rdbtnMarried.setActionCommand(rdbtnMarried.getText());
		btnStatus.add(rdbtnSingle);
		btnStatus.add(rdbtnMarried);
		
		
		btnOwnership = new ButtonGroup();			// button group2 for Home Ownership
		rdbtnOwned.setActionCommand(rdbtnOwned.getText());
		rdbtnMortage.setActionCommand(rdbtnMortage.getText());
		btnOwnership.add(rdbtnOwned);
		btnOwnership.add(rdbtnMortage);
	
		
		btnEmployment = new ButtonGroup();			// button group3 for Employment
		rdbtnSelfemployed.setActionCommand(rdbtnSelfemployed.getText());
		rdbtnProfession.setActionCommand(rdbtnProfession.getText());
		rdbtnPrivate.setActionCommand(rdbtnPrivate.getText());
		rdbtnGovernment.setActionCommand(rdbtnGovernment.getText());
		rdbtnRetired.setActionCommand(rdbtnRetired.getText());
		btnEmployment.add(rdbtnSelfemployed);
		btnEmployment.add(rdbtnProfession);
		btnEmployment.add(rdbtnPrivate);
		btnEmployment.add(rdbtnGovernment);
		btnEmployment.add(rdbtnRetired);
		
		lblGet = new JLabel("GET");
		lblGet.setFont(new Font("Nirmala UI", Font.BOLD, 30));
		lblGet.setBounds(59, 195, 76, 33);
		panel.add(lblGet);
		lblGet.setForeground(Color.WHITE);
		
		lblStarted = new JLabel("STARTED");
		lblStarted.setForeground(Color.WHITE);
		lblStarted.setFont(new Font("Nirmala UI", Font.BOLD, 30));
		lblStarted.setBounds(22, 238, 132, 33);
		panel.add(lblStarted);
		
		pnlView = new JPanel();
		pnlView.setLayout(null);
		pnlView.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		pnlView.setBackground(new Color(0, 0, 0, 0));
		pnlView.setBounds(22, 569, 132, 29);
		panel.add(pnlView);
		
		pnlBack = new JLabel("Back");
		pnlBack.setHorizontalAlignment(SwingConstants.CENTER);
		pnlBack.setForeground(Color.WHITE);
		pnlBack.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		pnlBack.setBounds(33, 7, 66, 13);
		pnlView.add(pnlBack);
		
		lblEmployment = new JLabel("Employment :");
		lblEmployment.setForeground(Color.BLACK);
		lblEmployment.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblEmployment.setBounds(491, 212, 76, 21);
		panel.add(lblEmployment);
		
		lblEmployBrd = new JLabel("");
		lblEmployBrd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmployBrd.setBounds(482, 201, 259, 97);
		panel.add(lblEmployBrd);
		lblEmployBrd.setBorder(new LineBorder(Color.LIGHT_GRAY));
		
		btnPlanOption = new ButtonGroup(); // button group 4 for Plan option
	    btnPlanTypes = new ButtonGroup(); // button group 5 for Plan types
		btnBoxes = new ButtonGroup();  //button group 6 for number of boxex
		
		rdbtnPrepaid = new JRadioButton("Prepaid");
		rdbtnPrepaid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPrepaid.setBackground(Color.WHITE);
		rdbtnPrepaid.setBounds(848, 203, 69, 21);
		panel.add(rdbtnPrepaid);
		
		rdbtnPostPaid = new JRadioButton("Postpaid");
		rdbtnPostPaid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnPostPaid.setBackground(Color.WHITE);
		rdbtnPostPaid.setBounds(920, 203, 69, 21);
		panel.add(rdbtnPostPaid);
		
		JLabel lblSubType = new JLabel(" Subscription :");
		lblSubType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSubType.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblSubType.setBounds(758, 201, 245, 26);
		panel.add(lblSubType);
		
		lblPrepaidOpt = new JLabel("Package option (for prepaid)");
		lblPrepaidOpt.setBounds(764, 242, 154, 21);
		panel.add(lblPrepaidOpt);
		lblPrepaidOpt.setForeground(Color.BLACK);
		lblPrepaidOpt.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		
		rdbtnSD = new JRadioButton("SD");
		rdbtnSD.setBounds(788, 269, 47, 21);
		panel.add(rdbtnSD);
		rdbtnSD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnSD.setBackground(Color.WHITE);
		
		rdbtnHD = new JRadioButton("HD");
		rdbtnHD.setBounds(853, 269, 47, 21);
		panel.add(rdbtnHD);
		rdbtnHD.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnHD.setBackground(Color.WHITE);
		
		rdbtnNA = new JRadioButton("N/A");
		rdbtnNA.setBounds(915, 269, 47, 21);
		panel.add(rdbtnNA);
		rdbtnNA.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNA.setBackground(Color.WHITE);
		
		lblBrdPrepaid = new JLabel("");
		lblBrdPrepaid.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBrdPrepaid.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lblBrdPrepaid.setBounds(758, 239, 245, 59);
		panel.add(lblBrdPrepaid);
		
		btnSubscription = new ButtonGroup(); // button group 6 for Subscription type
		
		rdbtnPrepaid.setActionCommand(rdbtnPrepaid.getText());
		rdbtnPostPaid.setActionCommand(rdbtnPostPaid.getText());
		btnSubscription.add(rdbtnPrepaid);
		btnSubscription.add(rdbtnPostPaid);
		
		btnPackageOpt = new ButtonGroup();  // button group 7 for Package Option
		rdbtnSD.setActionCommand(rdbtnSD.getText());
		rdbtnHD.setActionCommand(rdbtnHD.getText());
		rdbtnNA.setActionCommand(rdbtnNA.getText());
		btnPackageOpt.add(rdbtnSD);
		btnPackageOpt.add(rdbtnHD);
		btnPackageOpt.add(rdbtnNA);
		
		pnlPlan = new JPanel();
		pnlPlan.setBackground(Color.WHITE);
		pnlPlan.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(113, 59, 37), new Color(160, 160, 160)), "    CHOOSE A PLAN   ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(113, 59, 37)));
		pnlPlan.setBounds(202, 323, 801, 157);
		panel.add(pnlPlan);
		pnlPlan.setLayout(null);
		
		JLabel lblPlanOpt = new JLabel("Plan options :  ");
		lblPlanOpt.setForeground(Color.BLACK);
		lblPlanOpt.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblPlanOpt.setBounds(20, 29, 86, 21);
		pnlPlan.add(lblPlanOpt);
		
		JLabel lblPlantypes = new JLabel("Plan types :");
		lblPlantypes.setForeground(Color.BLACK);
		lblPlantypes.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblPlantypes.setBounds(291, 29, 69, 21);
		pnlPlan.add(lblPlantypes);
		
		rdbtn290 = new JRadioButton("290 HD");	// PLAN OPTIONS 
		rdbtn290.setToolTipText("");
		rdbtn290.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn290.setBackground(Color.WHITE);
		rdbtn290.setBounds(20, 56, 65, 21);
		pnlPlan.add(rdbtn290);
		
		rdbtn720 = new JRadioButton("720 HD");
		rdbtn720.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn720.setBackground(Color.WHITE);
		rdbtn720.setBounds(108, 56, 69, 21);
		pnlPlan.add(rdbtn720);
		
		rdbtn1650 = new JRadioButton("1650 HD");
		rdbtn1650.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn1650.setBackground(Color.WHITE);
		rdbtn1650.setBounds(196, 56, 86, 21);
		pnlPlan.add(rdbtn1650);
		
		rdbtn420 = new JRadioButton("420 HD");
		rdbtn420.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn420.setBackground(Color.WHITE);
		rdbtn420.setBounds(20, 79, 65, 21);
		pnlPlan.add(rdbtn420);
		
		rdbtn1050 = new JRadioButton("1050 HD");
		rdbtn1050.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn1050.setBackground(Color.WHITE);
		rdbtn1050.setBounds(108, 79, 76, 21);
		pnlPlan.add(rdbtn1050);
		
		rdbtn1950 = new JRadioButton("1950 HD");
		rdbtn1950.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn1950.setBackground(Color.WHITE);
		rdbtn1950.setBounds(196, 79, 76, 21);
		pnlPlan.add(rdbtn1950);
		
		rdbtn520 = new JRadioButton("520 HD");
		rdbtn520.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn520.setBackground(Color.WHITE);
		rdbtn520.setBounds(20, 102, 65, 21);
		pnlPlan.add(rdbtn520);
		
		rdbtn1350 = new JRadioButton("1350 HD");
		rdbtn1350.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn1350.setBackground(Color.WHITE);
		rdbtn1350.setBounds(108, 102, 76, 21);
		pnlPlan.add(rdbtn1350);
		
		rdbtnNA2 = new JRadioButton("N/A");
		rdbtnNA2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNA2.setBackground(Color.WHITE);
		rdbtnNA2.setBounds(196, 102, 86, 21);
		pnlPlan.add(rdbtnNA2);
		
		rdbtnHDL = new JRadioButton("HD Lite");
		rdbtnHDL.setToolTipText("");
		rdbtnHDL.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnHDL.setBackground(Color.WHITE);
		rdbtnHDL.setBounds(288, 56, 65, 21);
		pnlPlan.add(rdbtnHDL);
		
		rdbtnFlex = new JRadioButton("Flexi (Cash)");
		rdbtnFlex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnFlex.setBackground(Color.WHITE);
		rdbtnFlex.setBounds(288, 79, 92, 21);
		pnlPlan.add(rdbtnFlex);
		
		rdbtnNA3 = new JRadioButton("N/A");
		rdbtnNA3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNA3.setBackground(Color.WHITE);
		rdbtnNA3.setBounds(288, 102, 65, 21);
		pnlPlan.add(rdbtnNA3);
		
		lblNumbox = new JLabel("No. of box :");
		lblNumbox.setForeground(Color.BLACK);
		lblNumbox.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblNumbox.setBounds(384, 29, 69, 21);
		pnlPlan.add(lblNumbox);
		
		rdbtn1 = new JRadioButton("1");	//radio button no. of box 1-4
		rdbtn1.setToolTipText("");
		rdbtn1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn1.setBackground(Color.WHITE);
		rdbtn1.setBounds(382, 56, 41, 21);
		pnlPlan.add(rdbtn1);
		
		rdbtn2 = new JRadioButton("2");
		rdbtn2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn2.setBackground(Color.WHITE);
		rdbtn2.setBounds(382, 79, 41, 21);
		pnlPlan.add(rdbtn2);
		
		rdbtn3 = new JRadioButton("3");
		rdbtn3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn3.setBackground(Color.WHITE);
		rdbtn3.setBounds(382, 102, 41, 21);
		pnlPlan.add(rdbtn3);
		
		rdbtn4 = new JRadioButton("4");
		rdbtn4.setToolTipText("");
		rdbtn4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn4.setBackground(Color.WHITE);
		rdbtn4.setBounds(425, 56, 41, 21);
		pnlPlan.add(rdbtn4);
		
		rdbtnNA5 = new JRadioButton("N/A");
		rdbtnNA5.setToolTipText("");
		rdbtnNA5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNA5.setBackground(Color.WHITE);
		rdbtnNA5.setBounds(425, 80, 53, 21);
		pnlPlan.add(rdbtnNA5);
		
		rdbtn290.setActionCommand(rdbtn290.getText());
		rdbtn420.setActionCommand(rdbtn420.getText());
		rdbtn520.setActionCommand(rdbtn520.getText());
		rdbtn720.setActionCommand(rdbtn720.getText());
		rdbtn1050.setActionCommand(rdbtn1050.getText());
		rdbtn1350.setActionCommand(rdbtn1350.getText());
		rdbtn1650.setActionCommand(rdbtn1650.getText());
		rdbtn1950.setActionCommand(rdbtn1950.getText());
		rdbtnNA2.setActionCommand(rdbtnNA2.getText());
		btnPlanOption.add(rdbtn290);
		btnPlanOption.add(rdbtn420);
		btnPlanOption.add(rdbtn520);
		btnPlanOption.add(rdbtn720);
		btnPlanOption.add(rdbtn1050);
		btnPlanOption.add(rdbtn1350);
		btnPlanOption.add(rdbtn1650);
		btnPlanOption.add(rdbtn1950);
		btnPlanOption.add(rdbtnNA2);
		
		rdbtnHDL.setActionCommand(rdbtnHDL.getText());
		rdbtnFlex.setActionCommand(rdbtnFlex.getText());
		rdbtnNA3.setActionCommand(rdbtnNA3.getText());
		btnPlanTypes.add(rdbtnHDL);
		btnPlanTypes.add(rdbtnFlex);
		btnPlanTypes.add(rdbtnNA3);
		
		rdbtn1.setActionCommand(rdbtn1.getText());
		rdbtn2.setActionCommand(rdbtn2.getText());
		rdbtn3.setActionCommand(rdbtn3.getText());
		rdbtn4.setActionCommand(rdbtn4.getText());
		rdbtnNA5.setActionCommand(rdbtnNA5.getText());
		btnBoxes.add(rdbtn1);
		btnBoxes.add(rdbtn2);
		btnBoxes.add(rdbtn3);
		btnBoxes.add(rdbtn4);
		btnBoxes.add(rdbtnNA5);
		
		lblBillOpt = new JLabel("Billing Option : ");
		lblBillOpt.setForeground(Color.BLACK);
		lblBillOpt.setFont(new Font("Nirmala UI", Font.BOLD, 12));
		lblBillOpt.setBounds(502, 29, 92, 21);
		pnlPlan.add(lblBillOpt);
		
		rdbtnEmail = new JRadioButton("Email");
		rdbtnEmail.setToolTipText("");
		rdbtnEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnEmail.setBackground(Color.WHITE);
		rdbtnEmail.setBounds(645, 56, 76, 21);
		pnlPlan.add(rdbtnEmail);
		
		rdbtnCour = new JRadioButton("Courier");
		rdbtnCour.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnCour.setBackground(Color.WHITE);
		rdbtnCour.setBounds(645, 79, 69, 21);
		pnlPlan.add(rdbtnCour);
		
		rdbtnNA6 = new JRadioButton("N/A");
		rdbtnNA6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtnNA6.setBackground(Color.WHITE);
		rdbtnNA6.setBounds(645, 102, 65, 21);
		pnlPlan.add(rdbtnNA6);
		
	    btnBillOpt = new ButtonGroup(); //button group 8 for bill options
	    rdbtnEmail.setActionCommand(rdbtnEmail.getText());
	    rdbtnCour.setActionCommand(rdbtnCour.getText());
	    rdbtnNA6.setActionCommand(rdbtnNA6.getText());
		btnBillOpt.add(rdbtnEmail);
		btnBillOpt.add(rdbtnCour);
		btnBillOpt.add(rdbtnNA6);
		
		JLabel lblBillingStatementSent = new JLabel("Billing statement sent to :");
		lblBillingStatementSent.setForeground(Color.BLACK);
		lblBillingStatementSent.setFont(new Font("Nirmala UI", Font.PLAIN, 12));
		lblBillingStatementSent.setBounds(502, 56, 137, 21);
		pnlPlan.add(lblBillingStatementSent);
		
		lblGross = new JLabel("Gros annual income : ");
		lblGross.setHorizontalAlignment(SwingConstants.LEFT);
		lblGross.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGross.setBounds(202, 271, 122, 27);
		panel.add(lblGross);
		
		txtGrossinc = new JTextField();
		txtGrossinc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtGrossinc.setColumns(10);
		txtGrossinc.setBounds(322, 271, 139, 27);
		panel.add(txtGrossinc);
		
		lblBillAdd = new JLabel("Billing Address : ");
		lblBillAdd.setHorizontalAlignment(SwingConstants.LEFT);
		lblBillAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblBillAdd.setBounds(202, 489, 88, 27);
		panel.add(lblBillAdd);
			
		txtBillAdd = new JTextField();
		txtBillAdd.setBounds(294, 490, 709, 26);
		panel.add(txtBillAdd);
		txtBillAdd.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtBillAdd.setColumns(10);
		
		lblNote = new JLabel("please select N/A if not applicable");
		lblNote.setForeground(Color.RED);
		lblNote.setFont(new Font("Nirmala UI", Font.ITALIC, 11));
		lblNote.setBounds(202, 585, 178, 13);
		panel.add(lblNote);
		
		btnConfirm = new JButton("Confirm");//save to database
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				SaveToDatabase();		
			}
		});
		btnConfirm.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.DARK_GRAY, null));
		btnConfirm.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		btnConfirm.setBackground(new Color (113,59,37));
		btnConfirm.setBorderPainted(false);
		btnConfirm.setForeground(Color.WHITE);
		btnConfirm.setBounds(870, 569, 133, 21);
		panel.add(btnConfirm);
		
		btnTable = new JButton("View Table"); //view table from database
		btnTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Records viewTable = new Records(); // admin
				viewTable.setVisible(true);
				dispose();
			}
		});
		
		btnTable.setForeground(Color.WHITE);
		btnTable.setFont(new Font("Nirmala UI", Font.PLAIN, 13));
		btnTable.setBorderPainted(false);
		btnTable.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, Color.DARK_GRAY, null));
		btnTable.setBackground(new Color(113, 59, 37));
		btnTable.setBounds(22, 532, 133, 27);
		panel.add(btnTable);
		
		lblRemote = new JLabel("");
		lblRemote.setBounds(0, 0, 173, 611);
		panel.add(lblRemote);
		lblRemote.setBorder(null);
		lblRemote.setIcon(new ImageIcon(img_remote));
		
		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(544, 100, 198, 27);
		panel.add(dateChooser1);
		
		con();
		
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
	
	static void SaveToDatabase() { // add button
		
		Connection objConn = con();

		try {	
		

		String objSql = "INSERT INTO customer "
				+ "(Full_Name,Mobile_no,Landline_no,Email,Birthdate,Household_inc,Occupation,Installation_add,Marital_status,Home_own,Annual_inc,Sub_type,Employment,Prepaid_Pck,Postpaid_PTyp,Postpaid_POpt,Postpaid_NBox,postpaid_BOpt,postpaid_BAdd)"
				+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
		
		objPst = objConn.prepareStatement(objSql); // prepare statement is used to to perform the sql statement
		
		//objPst.setString(4, strGender); //gender
		//objPst.setInt(1, Integer.parseInt(txtID.getText()));
	
		objPst.setString(1, txtFullname.getText());
		objPst.setString(2, txtMobile.getText()); 	//int
		objPst.setString(3, txtLandline.getText());	//int				
		objPst.setString(4, txtEmail.getText());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //date format
		jDate = sdf.format(dateChooser1.getDate());
		objPst.setString(5, jDate);
		//objPst.setString(6, txtHouseInc.getText());
		objPst.setInt(6, Integer.parseInt(txtHouseInc.getText()));
		objPst.setString(7, txtOccupation.getText());
		objPst.setString(8, txtInstallAdd.getText());
		objPst.setString(9, btnStatus.getSelection().getActionCommand());
		objPst.setString(10, btnOwnership.getSelection().getActionCommand());
		//objPst.setString(11, txtGrossinc .getText()); //int
		objPst.setString(11, txtGrossinc.getText());
		objPst.setString(12, btnSubscription.getSelection().getActionCommand()); //getactioncommand is for button groups
		objPst.setString(13, btnEmployment.getSelection().getActionCommand());
		objPst.setString(14, btnPackageOpt.getSelection().getActionCommand());
		objPst.setString(15, btnPlanTypes.getSelection().getActionCommand());
		objPst.setString(16, btnPlanOption.getSelection().getActionCommand());
		objPst.setString(17, btnBoxes.getSelection().getActionCommand());
		objPst.setString(18, btnBillOpt.getSelection().getActionCommand());
		objPst.setString(19, txtBillAdd .getText());
		
		objPst.executeUpdate();
	
		JOptionPane.showMessageDialog(null,"Saved sucessfully");
		
		}	catch(Exception objE) {
			JOptionPane.showMessageDialog(null,"ERROR Saving failed " +objE);
			System.out.printf("ERROR Saving failed " +objE);
		}
	}
}

/* Border border = BorderFactory.createLineBorder(Color.GRAY, 1); //border
lblStatus.setBorder(border);
lblOwnership.setBorder(border);
lblEmployBrd.setBorder(border); */
