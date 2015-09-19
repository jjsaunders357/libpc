package com.pheiffware.lib.swing.renderPanel.graphicDebug2D;

import java.awt.Color;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.pheiffware.lib.swing.graphics.G2D;
import com.pheiffware.lib.swing.graphics.G2DRender;
import com.pheiffware.lib.swing.renderPanel.RenderPanel;

public class GraphicDebugPanel2D extends RenderPanel
{
	private static final long serialVersionUID = 5990924595581860103L;
	private final Object renderLock = new Object();
	private final List<Object> renderItems = new LinkedList<>();
	private final List<Color> renderColors = new LinkedList<>();

	public GraphicDebugPanel2D(int width, int height, double renderPeriod)
	{
		super(width, height, renderPeriod);
	}

	public GraphicDebugPanel2D(int width, int height, double renderPeriod, double viewX, double viewY, double viewWidth, double viewHeight)
	{
		super(width, height, renderPeriod, viewX, viewY, viewWidth, viewHeight);
	}

	/**
	 * Resets set of objects being rendered all at once.
	 * 
	 * @param objects
	 */
	public void setRenderables(Object[] objects, Color[] colors)
	{
		synchronized (renderLock)
		{
			renderItems.clear();
			for (int i = 0; i < objects.length; i++)
			{
				renderItems.add(objects[i]);
				renderColors.add(colors[i]);
			}
		}
	}

	/**
	 * Adds an object to render in thread-safe manner.
	 * 
	 * @param object
	 */
	public void addRenderable(Object object, Color color)
	{
		synchronized (renderLock)
		{
			renderItems.add(object);
			renderColors.add(color);
		}
	}

	public void clearRenderables()
	{
		synchronized (renderLock)
		{
			renderItems.clear();
			renderColors.clear();
		}
	}

	@Override
	public void render(G2D g2d)
	{
		g2d.fillRectAbsolute(0, 0, getWidth(), getHeight());
		g2d.setColor(new Color(255, 0, 0));
		renderDebugObjects(g2d);
	}

	protected void renderDebugObjects(G2D g2d)
	{
		synchronized (renderLock)
		{
			Iterator<Color> colorIter = renderColors.iterator();
			for (Object object : renderItems)
			{
				g2d.setColor(colorIter.next());
				G2DRender.render(g2d, object);
			}
		}
	}
}
