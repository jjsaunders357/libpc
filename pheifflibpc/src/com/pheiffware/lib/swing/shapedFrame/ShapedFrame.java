package com.pheiffware.lib.swing.shapedFrame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

public class ShapedFrame extends JFrame implements MouseMotionListener,
		MouseListener
{
	private static final long serialVersionUID = -5239540667671727475L;
	private Point initialClickLocation;
	private boolean isDragging;

	public ShapedFrame(boolean draggable)
	{
		isDragging = false;
		if (draggable)
		{
			addMouseMotionListener(this);
			addMouseListener(this);
		}
		setUndecorated(true);
		setAlwaysOnTop(true);
		setBackground(new Color(0, 0, 0, 0));
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
	}

	private Point getEventScreenLocation(MouseEvent e)
	{
		return new Point(getLocationOnScreen().x + e.getX(),
				getLocationOnScreen().y + e.getY());
	}

	public boolean startDrag(MouseEvent e)
	{
		return true;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (isDragging)
		{
			Point dragEventLocation = getEventScreenLocation(e);
			setLocation(new Point(dragEventLocation.x - initialClickLocation.x,
					dragEventLocation.y - initialClickLocation.y));
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		isDragging = false;

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		initialClickLocation = new Point(e.getX(), e.getY());
		isDragging = startDrag(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}
}
