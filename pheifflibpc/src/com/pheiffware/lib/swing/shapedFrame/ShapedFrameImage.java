package com.pheiffware.lib.swing.shapedFrame;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class ShapedFrameImage extends ShapedFrame implements ActionListener
{
	private static final long serialVersionUID = 2203049567111268044L;
	private Image image;

	public ShapedFrameImage(boolean draggable, String imageName)
	{
		super(draggable);
		image = new ImageIcon(getClass().getResource(imageName)).getImage();
		setPreferredSize(new Dimension(image.getWidth(null),
				image.getHeight(null)));
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		Timer timer = new Timer(0, this);
		timer.setRepeats(true);
		timer.setDelay(10);
		timer.start();

	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image, 0, 0, image.getWidth(null), image.getHeight(null),
				null);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

	}

}
