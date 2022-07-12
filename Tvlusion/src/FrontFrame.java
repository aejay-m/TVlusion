import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;


public class FrontFrame extends JFrame {

	static private Image img_bck = new ImageIcon(FrontFrame.class.getResource("imgs/bckgrnd.png")).getImage().getScaledInstance(850,570, Image.SCALE_SMOOTH);
	
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontFrame frame = new FrontFrame();
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
	
	public FrontFrame() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
	//	setTitle("TVlusion");
		
		setUndecorated(true);		
		setBackground(Color.WHITE);
		setVisible(true);
		setBounds(100, 100, 850, 535);
		setLocationRelativeTo(null); //center tab
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pnlWhole = new JPanel();
		pnlWhole.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f)); //transparent panel 
		pnlWhole.setBounds(0, 0, 850, 537);
		
		contentPane.add(pnlWhole);
		pnlWhole.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Watch your favorite");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 35));
		lblNewLabel.setBounds(10, 10, 368, 41);
		pnlWhole.add(lblNewLabel);
		
		JLabel lbl2 = new JLabel("TV Networks");
		lbl2.setForeground(new Color(244,177,131));
		lbl2.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 35));
		lbl2.setBounds(10, 51, 212, 41);
		pnlWhole.add(lbl2);
		
		JLabel lbl3 = new JLabel("all around the world");
		lbl3.setForeground(Color.WHITE);
		lbl3.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 35));
		lbl3.setBounds(220, 51, 340, 41);
		pnlWhole.add(lbl3);
		
		JLabel lbl4 = new JLabel("We broadcast premium TV content to both households and");
		lbl4.setForeground(Color.LIGHT_GRAY);
		lbl4.setFont(new Font("Nirmala UI Semilight", Font.ITALIC, 20));
		lbl4.setBounds(10, 102, 521, 41);
		pnlWhole.add(lbl4);
		
		JLabel lbl5 = new JLabel("establishments nationwide.");
		lbl5.setForeground(Color.LIGHT_GRAY);
		lbl5.setFont(new Font("Nirmala UI Semilight", Font.ITALIC, 20));
		lbl5.setBounds(10, 134, 521, 27);
		pnlWhole.add(lbl5);
		
		JPanel pnlBtnSubs = new JPanel();
		pnlBtnSubs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {  //database connection starts here
				
				
				Form frmFillup =  new Form(); 
				frmFillup.setVisible(true);
				dispose();
			}
		});
		pnlBtnSubs.setLayout(null);
		pnlBtnSubs.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		pnlBtnSubs.setBackground(new Color(113,59,37));
		pnlBtnSubs.setBounds(660, 236, 117, 29);
		pnlWhole.add(pnlBtnSubs);
		
		JLabel lblSubs = new JLabel("Get Subsciption");
		lblSubs.setForeground(Color.WHITE);
		lblSubs.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		lblSubs.setBounds(7, 8, 109, 13);
		pnlBtnSubs.add(lblSubs);
	
		JPanel pnlView = new JPanel();
		pnlView.setLayout(null);
		pnlView.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, true));
		pnlView.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.0f));
		pnlView.setBounds(660, 292, 117, 29);
		pnlWhole.add(pnlView);
		
		JLabel lblView = new JLabel("View Plans");
		lblView.setHorizontalAlignment(SwingConstants.CENTER);
		lblView.setForeground(Color.WHITE);
		lblView.setFont(new Font("Nirmala UI", Font.PLAIN, 14));
		lblView.setBounds(27, 8, 66, 13);
		pnlView.add(lblView);
		
		JLabel lblSIGN = new JLabel("SIGN UP NOW");
		lblSIGN.setForeground(Color.WHITE);
		lblSIGN.setFont(new Font("Nirmala UI Semilight", Font.BOLD, 22));
		lblSIGN.setBounds(643, 127, 158, 41);
		pnlWhole.add(lblSIGN);
		
		JLabel lblBack = new JLabel("");
		lblBack.setIcon(new ImageIcon(img_bck));	//setting image backgrond (icon)
		lblBack.setBounds(0, 0, 850, 537);
		contentPane.add(lblBack);
		
		
		
	}
}
