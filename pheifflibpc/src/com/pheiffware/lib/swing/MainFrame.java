package com.pheiffware.lib.swing;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame
{
	public MainFrame(String title, JPanel mainPanel)
	{
		setTitle(title);

		// Set basic layout
		setLayout(new BorderLayout());

		// Add a new panel for the game
		add(mainPanel, BorderLayout.CENTER);

		// Pack panel into frame
		pack();

		// Make window visible
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
