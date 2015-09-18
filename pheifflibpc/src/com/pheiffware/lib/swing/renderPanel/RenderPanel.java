package com.pheiffware.lib.swing.renderPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;

import com.pheiffware.lib.swing.graphics.G2D;
import com.pheiffware.lib.swing.graphics.Transform2D;

/**
 * A panel which causes repaint to happen over and over.
 * 
 * @author Steve
 */
@SuppressWarnings("serial")
public abstract class RenderPanel extends JPanel implements ComponentListener

{
	// Triggers repaint to happen over and over
	private final ScheduledExecutorService renderExecutor;

	// The 1st time paint happens, renderInit is called. After that, render is
	// called.
	private boolean firstRender = false;

	// Period at which swing is triggered to repaint
	private double renderPeriod;

	private Transform2D transform;

	/**
	 * 
	 * @param width
	 * @param height
	 * @param renderPeriod
	 *            How often swing is told to repaint. The actually timing of
	 *            painting, depends on how swing manages calling the paint
	 *            method.
	 */
	public RenderPanel(int width, int height, double renderPeriod)
	{
		transform = new Transform2D(width, height, 0, 0, 1, 1);
		setPreferredSize(new Dimension(width, height));
		this.renderPeriod = renderPeriod;
		addComponentListener(this);
		renderExecutor = Executors.newSingleThreadScheduledExecutor();
	}

	public void startRender()
	{
		renderExecutor.scheduleAtFixedRate(new Runnable()
		{
			public void run()
			{
				repaint();
			}
		}, 0, (long) (renderPeriod * 1000000.0), TimeUnit.MICROSECONDS);
	}

	/**
	 * Sets up the view transform
	 * 
	 * @param width
	 * @param height
	 * @param renderPeriod
	 * @param viewX
	 * @param viewY
	 * @param viewWidth
	 * @param viewHeight
	 */
	public RenderPanel(int width, int height, double renderPeriod,
			double viewX, double viewY, double viewWidth, double viewHeight)
	{
		transform = new Transform2D(width, height, 0, 0, 1, 1);
		setPreferredSize(new Dimension(width, height));
		this.renderPeriod = renderPeriod;
		addComponentListener(this);
		renderExecutor = Executors.newSingleThreadScheduledExecutor();
		setView(viewX, viewY, viewWidth, viewHeight);
	}

	public void setView(double viewX, double viewY, double viewWidth,
			double viewHeight)
	{
		transform.setView(viewX, viewY, viewWidth, viewHeight);
		repaint();
	}

	public void renderInit(G2D g2d)
	{
		g2d.setColor(new Color(0, 0, 0));
		g2d.fillRectAbsolute(0, 0, getWidth(), getHeight());
	}

	public abstract void render(G2D g2d);

	@Override
	public void componentResized(ComponentEvent e)
	{
		transform.setWindowDims(getWidth(), getHeight());
		repaint();
	}

	@Override
	public void paint(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		if (firstRender)
		{
			renderInit(new G2D(g2d, transform));
			firstRender = false;
		}
		else
		{
			render(new G2D(g2d, transform));
		}
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
	}

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
	}
}
