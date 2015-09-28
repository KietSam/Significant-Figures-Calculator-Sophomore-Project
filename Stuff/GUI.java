package Stuff;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class GUI extends JFrame {
	private JButton leftButton;
	private JButton rightButton;
	public GUI (){
		super("Significant Figures Calculator");
		setLayout(new BorderLayout());
		leftButton = new JButton("Calculator");
		add(leftButton, BorderLayout.WEST);
		TheHandler handler = new TheHandler();
		leftButton.addActionListener(handler);
		
		rightButton = new JButton("Test");
		add(rightButton, BorderLayout.EAST);
		rightButton.addActionListener(handler);
	}
	
	private class TheHandler implements ActionListener {
		public void actionPerformed (ActionEvent event){
		}
	}
}
