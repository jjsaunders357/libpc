package com.pheiffware.lib.swing.graphics;

import java.awt.geom.Point2D;

import com.pheiffware.lib.geometry.Vec3D;

/**
 * Due to swing limitations during rendering, this is used to translate all
 * SwingRenderables to perform standard transformations. Doesn't handle
 * rotations, but can deal with drawing items like circles with scaled radii.
 * 
 * This is used to translate from SwingRenderable coordinates to window
 * coordinates and back.
 * 
 * @author Steve
 *
 */
public class Transform2D
{
	public int windowWidth;
	public int windowHeight;
	public double viewX;
	public double viewY;
	public double viewWidth;
	public double viewHeight;

	public double scaleRenderableToWindowX;
	public double scaleRenderableToWindowY;
	public double translateRenderableToWindowX;
	public double translateRenderableToWindowY;

	public Transform2D(int windowWidth, int windowHeight, double viewX,
			double viewY, double viewWidth, double viewHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.viewX = viewX;
		this.viewY = viewY;
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		calcTransform();
	}

	public final void setWindowDims(int windowWidth, int windowHeight)
	{
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		calcTransform();
	}

	public final void setView(double viewX, double viewY, double viewWidth,
			double viewHeight)
	{
		this.viewX = viewX;
		this.viewY = viewY;
		this.viewWidth = viewWidth;
		this.viewHeight = viewHeight;
		calcTransform();
	}

	public final Vec3D windowToRenderable(Point2D.Double windowPoint)
	{
		return new Vec3D(windowPoint.x / scaleRenderableToWindowX
				- translateRenderableToWindowX, windowPoint.y
				/ scaleRenderableToWindowY - translateRenderableToWindowY, 0);
	}

	public final int transX(double x)
	{
		return (int) ((x + translateRenderableToWindowX) * scaleRenderableToWindowX);
	}

	public final int transY(double y)
	{
		return (int) ((y + translateRenderableToWindowY) * scaleRenderableToWindowY);
	}

	public final int transWidth(double width)
	{
		return (int) (width * scaleRenderableToWindowX);
	}

	public final int transHeight(double height)
	{
		return (int) (height * scaleRenderableToWindowY);
	}

	private final void calcTransform()
	{
		scaleRenderableToWindowX = windowWidth / viewWidth;
		scaleRenderableToWindowY = windowHeight / viewHeight;
		translateRenderableToWindowX = -viewX;
		translateRenderableToWindowY = -viewY;
	}

	public Vec3D transformPoint(Vec3D v1)
	{
		return new Vec3D(transX(v1.x), transY(v1.y), v1.z);
	}
}
