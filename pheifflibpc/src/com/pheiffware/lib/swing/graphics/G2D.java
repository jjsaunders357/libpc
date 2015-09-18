package com.pheiffware.lib.swing.graphics;

import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints.Key;
import java.awt.Stroke;
import java.util.Map;

import com.pheiffware.lib.geometry.Vec3D;
import com.pheiffware.lib.geometry.shapes.LineSegment;
import com.pheiffware.lib.geometry.shapes.Rect;
import com.pheiffware.lib.geometry.shapes.Sphere;

public class G2D
{
	// When showing a directional arrow, how many pixels is it long?
	private final double arrowLength = 15;
	// When showing a directional arrow, how many pixels are the arrow lines
	// facing backward?
	private final double arrowPointLength = 5;

	// What angle are the arrow tip lines?
	private final double arrowPointAngle = 180 * 155 / Math.PI;

	private final Graphics2D g2d;
	public final Transform2D transform;

	public G2D(Graphics2D g2d, Transform2D transform)
	{
		this.g2d = g2d;
		this.transform = transform;
	}

	public final void drawLine(LineSegment lineSegment)
	{
		drawLine(lineSegment.p1, lineSegment.p2);
	}

	public final void drawLine(Vec3D p1, Vec3D p2)
	{
		drawLine(p1.x, p1.y, p2.x, p2.y);
	}

	public final void drawLine(double x1, double y1, double x2, double y2)
	{
		g2d.drawLine(transform.transX(x1), transform.transY(y1),
				transform.transX(x2), transform.transY(y2));
	}

	public void drawArrow(LineSegment lineSegment)
	{
		drawArrow(lineSegment.p1, lineSegment.p2);
	}

	public void drawArrowAboluteLength(Vec3D p1, double angleInRadians)
	{
		Vec3D p2 = new Vec3D(p1.x + Math.cos(angleInRadians) * arrowLength
				/ transform.scaleRenderableToWindowX, p1.y
				+ Math.sin(angleInRadians) * arrowLength
				/ transform.scaleRenderableToWindowY, p1.z);
		drawArrow(p1, p2);
	}

	public final void drawArrow(Vec3D p1, Vec3D p2)
	{
		drawLine(p1, p2);
		Vec3D dir = Vec3D.normalize(Vec3D.sub(p2, p1));

		Vec3D endPoint = transform.transformPoint(p2);
		Vec3D arrow1 = new Vec3D(dir);
		arrow1.rotate2D(-arrowPointAngle);
		arrow1.scaleBy(arrowPointLength);
		arrow1.addTo(endPoint);

		Vec3D arrow2 = new Vec3D(dir);
		arrow2.rotate2D(arrowPointAngle);
		arrow2.scaleBy(arrowPointLength);
		arrow2.addTo(endPoint);
		drawLineAbsolute(endPoint, arrow1);
		drawLineAbsolute(endPoint, arrow2);
	}

	public final void fillRect(Rect rect)
	{
		g2d.fillRect(transform.transX(rect.x1), transform.transY(rect.y1),
				transform.transWidth(rect.width),
				transform.transHeight(rect.height));
	}

	public final void fillOval(Sphere sphere)
	{
		fillOval(sphere.center.x, sphere.center.y, sphere.radius);
	}

	public final void drawOval(Sphere sphere)
	{
		drawOval(sphere.center.x, sphere.center.y, sphere.radius);
	}

	public final void fillOval(double x, double y, double radius)
	{
		g2d.fillOval(transform.transX(x - radius),
				transform.transY(y - radius), transform.transWidth(radius * 2),
				transform.transHeight(radius * 2));
	}

	public final void drawOval(double x, double y, double radius)
	{
		g2d.drawOval(transform.transX(x - radius),
				transform.transY(y - radius), transform.transWidth(radius * 2),
				transform.transHeight(radius * 2));
	}

	public void fillRectAbsolute(int x, int y, int width, int height)
	{
		g2d.fillRect(x, y, width, height);
	}

	public final void drawLineAbsolute(Vec3D p1, Vec3D p2)
	{
		g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
	}

	public final void drawLineAbsolute(double x1, double y1, double x2,
			double y2)
	{
		g2d.drawLine((int) x1, (int) y1, (int) x2, (int) y2);
	}

	/************************************ Delegated *************************************/
	public void setBackground(Color color)
	{
		g2d.setBackground(color);
	}

	public void setColor(Color arg0)
	{
		g2d.setColor(arg0);
	}

	public void setComposite(Composite comp)
	{
		g2d.setComposite(comp);
	}

	public void setFont(Font arg0)
	{
		g2d.setFont(arg0);
	}

	public void setPaint(Paint paint)
	{
		g2d.setPaint(paint);
	}

	public void setPaintMode()
	{
		g2d.setPaintMode();
	}

	public void setRenderingHint(Key hintKey, Object hintValue)
	{
		g2d.setRenderingHint(hintKey, hintValue);
	}

	public void setRenderingHints(Map<?, ?> hints)
	{
		g2d.setRenderingHints(hints);
	}

	public void setStroke(Stroke s)
	{
		g2d.setStroke(s);
	}

}
