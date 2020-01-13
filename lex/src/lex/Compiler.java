package lex;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.filechooser.FileNameExtensionFilter;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class Compiler {

	private JFrame frame;
	String filename;
	ArrayList<String> lexicl=new ArrayList<String>();
	ArrayList<String> Program=new ArrayList<String>();
	ArrayList<String> phrase=new ArrayList<String>();
	ArrayList<String> S=new ArrayList<String>();
	ArrayList<String> sm=new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Compiler window = new Compiler();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Compiler() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.getContentPane().setBackground(Color.BLACK);
		frame.setBounds(0, 0, 1187, 714);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
						
						JScrollBar scrollBar = new JScrollBar();
						scrollBar.setBounds(0, 0, 17, 48);
		
						JScrollPane scrollPane = new JScrollPane();

						JTextArea textArea = new JTextArea();
						textArea.setForeground(new Color(0, 0, 51));
						textArea.setFont(new Font("Monospaced", Font.BOLD, 18));
						textArea.setEditable(false);
						textArea.setBackground(new Color(153, 102, 0));
						textArea.setBounds(341, 129, 789, 487);
				        JScrollPane scroll = new JScrollPane(textArea);
				        scroll.setBounds(341, 129, 800, 485);                    
						frame.getContentPane().add(scroll);

		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 51));
		panel.setBounds(28, 53, 271, 563);
		frame.getContentPane().add(panel);
		
		
		JButton btnAnalyseSemantique = new JButton("Analyse Semantique");
		btnAnalyseSemantique.setEnabled(false);
		btnAnalyseSemantique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				semantique s=new semantique();
				sm=s.P(Program,phrase);
				int index=0;
				textArea.setText("");
				while(index<sm.size()) {
				textArea.setText(textArea.getText()+phrase.get(index)+sm.get(index)+"\n");
				index++;
				}
				
			}
		});
		btnAnalyseSemantique.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JButton btnAnalyseSyntaxique = new JButton("Analyse syntaxique");
		btnAnalyseSyntaxique.setEnabled(false);
		btnAnalyseSyntaxique.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAnalyseSyntaxique.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				syntax s =new syntax();
				S=s.P(Program);
				int index=0;
				textArea.setText("");
				while(index<S.size()&&index<phrase.size()) {
				textArea.setText(textArea.getText()+phrase.get(index)+S.get(index)+"\n");
				index++;
				}
				btnAnalyseSemantique.setEnabled(true);
			}
		});
		
		
		
		
		
		JButton btnAnalyseLexicale = new JButton("Analyse Lexicale");
		btnAnalyseLexicale.setEnabled(false);
		btnAnalyseLexicale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lexicl=lex.lexique(filename,Program,phrase);
				int index=0;
				textArea.setText("");
				while(index<lexicl.size()) {
				textArea.setText(textArea.getText()+lexicl.get(index)+"\n");
				index++;
				}
				btnAnalyseSyntaxique.setEnabled(true);
			}
		});
		btnAnalyseLexicale.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		JButton btnNewButton = new JButton("Charger un Fichier");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser Chooser=new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Compila","Compila");
				Chooser.addChoosableFileFilter(filter);
				int r=Chooser.showOpenDialog(null);
				File f= Chooser.getSelectedFile();
				filename=f.getAbsolutePath();
				
				try {
					FileReader reader = new FileReader(filename);
					BufferedReader br=new BufferedReader(reader);
					textArea.read(br,null);
					br.close();
					textArea.requestFocus();
					
				}catch(Exception e1){
					JOptionPane.showMessageDialog(null, e1);
				}
				btnAnalyseLexicale.setEnabled(true);
				btnNewButton.setEnabled(false);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		
		
		
		
		
	
		
		JLabel lblCommandes = new JLabel("Commandes :");
		lblCommandes.setFont(new Font("Tahoma", Font.BOLD, 20));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(37)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAnalyseSemantique, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAnalyseSyntaxique, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAnalyseLexicale, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblCommandes, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(43, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addComponent(lblCommandes, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAnalyseLexicale, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAnalyseSyntaxique, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnAnalyseSemantique, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(77, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		JLabel lblSortie = new JLabel("Sortie :");
		lblSortie.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSortie.setForeground(new Color(255, 204, 0));
		lblSortie.setBounds(341, 82, 222, 25);
		frame.getContentPane().add(lblSortie);
	}
}
