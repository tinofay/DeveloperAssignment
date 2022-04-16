package com.assignment.tino

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RandomRectangleGUI{
	private JFrame frame;
	private JButton colorButton;
	private JButton sizeButton;
	
	private final RandomRectDrawPanel rectDrawPanel = new RandomRectDrawPanel();
	
	public static void main (String[] args){
		RandomRectangleGUI gui = new RandomRectangleGUI();
		gui.createJFrame();
	}

	public void createJFrame(){
		frame = new JFrame("TinosPlayFrame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		colorButton = new JButton("Click me for a random colour");
		sizeButton = new JButton("Click me for a random size");

		colorButton.addActionListener(new RandomColorListener());
		sizeButton.addActionListener(new SizeListener());

		frame.getContentPane().add(BorderLayout.PAGE_START, colorButton);
		frame.getContentPane().add(BorderLayout.PAGE_END, sizeButton);
		frame.getContentPane().add(BorderLayout.CENTER, drawPanel);
		frame.setSize(500,500);
		frame.setVisible(true);
	}



	class RandomRectDrawPanel extends JPanel{
		
		private Color backgroundColor;
		private int height = 50;
		private int width = 80;
		private int xCartesianPosition = 50;
		private int yCartesianPosition = 50;

		public void paintComponent (Graphics g){
			super.paintComponent(g);
			g.setColor(backgroundColor);
			g.fillRect(xCartesianPosition,yCartesianPosition,width,height);
		}
		
		public void randomColor(){
			int r = (int)(Math.random()*255);
			int gr = (int)(Math.random()*255);
			int b = (int)(Math.random()*255);
			backgroundColor = new Color(r,gr,b);
		}
		
		public void randomSize(){
			int displace = 5;
			height = (int)(Math.random()*getHeight());
			width = (int)(Math.random()*getWidth());

			int temp;
			if ((yCartesianPosition + height) > getHeight()){  // this to keep all of the height of the rectangle inside the draw panel
				temp = getHeight() - (yCartesianPosition + height);
				height = height + temp - displace;  // temp is a negative number
			}
			if (height < 5) height = 5;//minimum height

			if ((xCartesianPosition + width) > getWidth()){  // this to keep all of the width of the rectangle inside the draw panel
				temp = getWidth() - (xCartesianPosition + width);
				width = width + temp - displace;  // temp is a negative number
			}
			if (width < 5) width = 5; //minimum width
		}				
	}

	class RandomColorListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			rectDrawPanel.randomColor();
			rectDrawPanel.repaint();
		}
	}

	class SizeListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			rectDrawPanel.randomSize();
			rectDrawPanel.repaint();
		}
	}


}
