package com.pheiffware.lib.swing.graphics;

import com.pheiffware.lib.geometry.Vec3D;
import com.pheiffware.lib.geometry.shapes.SimpleLineSegment;
import com.pheiffware.lib.geometry.shapes.LineSegment;
import com.pheiffware.lib.geometry.shapes.Rect;
import com.pheiffware.lib.geometry.shapes.Sphere;
import com.pheiffware.lib.physics.entity.rigidBody.LineSegmentEntity;
import com.pheiffware.lib.physics.entity.rigidBody.PolygonEntity;
import com.pheiffware.lib.physics.entity.rigidBody.SphereEntity;

/**
 * A bit of a hack class, this will lookup the data type of an object and if
 * possible render it. This was done this way (rather than everything
 * implementing SwingRenderable) so that the core libraries could be made to
 * work with Java and Android.
 * 
 * @author Steve
 *
 */
public class G2DRender
{
	/**
	 * Draw an entity, based on its type
	 * 
	 * @param g2d
	 * @param object
	 * @param transform
	 */
	public static void render(G2D g2d, Object object)
	{
		// If we could declare something G2DRenderable, then great, use OOP.
		if (object instanceof G2DRenderable)
		{
			((G2DRenderable) object).render(g2d);
		}
		else if (object instanceof Vec3D)
		{
			renderO(g2d, (Vec3D) object);
		}
		else if (object instanceof Rect)
		{
			renderO(g2d, (Rect) object);
		}
		else if (object instanceof SimpleLineSegment)
		{
			renderO(g2d, (SimpleLineSegment) object);
		}
		else if (object instanceof Sphere)
		{
			renderO(g2d, (Sphere) object);
		}
		else if (object instanceof SphereEntity)
		{
			renderO(g2d, (SphereEntity) object);
		}
		else if (object instanceof LineSegmentEntity)
		{
			renderO(g2d, ((LineSegmentEntity) object).getLineSegment());
		}
		else if (object instanceof PolygonEntity)
		{
			renderO(g2d, (PolygonEntity) object);
		}
	}

	private static void renderO(G2D g2d, Vec3D point)
	{
		g2d.drawPoint(point);
	}

	private static void renderO(G2D g2d, Sphere sphere)
	{
		g2d.fillOval(sphere);
	}

	private static void renderO(G2D g2d, SimpleLineSegment lineSegment)
	{
		g2d.drawArrow(lineSegment);
	}

	private static void renderO(G2D g2d, Rect rect)
	{
		g2d.fillRect(rect);
	}

	/**
	 * Draws a circle
	 * 
	 * @param g2d
	 * @param sphereEntity
	 */
	private static void renderO(G2D g2d, SphereEntity sphereEntity)
	{
		g2d.fillOval(sphereEntity.sphere);
	}

	private static void renderO(G2D g2d, LineSegment lineSegment)
	{
		Vec3D p1 = lineSegment.p1;
		Vec3D p2 = lineSegment.p2;
		Vec3D unitNormal = lineSegment.getUnitNormal();

		g2d.drawLineSegment(lineSegment);
		Vec3D center = Vec3D.average(p1, p2);
		Vec3D normalEndPoint = Vec3D.add(center, Vec3D.scale(unitNormal, 10.0f));

		g2d.drawLine(center, normalEndPoint);

	}

	private static void renderO(G2D g2d, PolygonEntity polygon)
	{
		for (LineSegment lineSegment : polygon.getLineSegments())
		{
			g2d.drawLineSegment(lineSegment);
		}
		g2d.drawOval(polygon.getBoundingSphere().getCenter().x, polygon.getBoundingSphere().getCenter().y, polygon.getBoundingSphere().getRadius());
	}
}
