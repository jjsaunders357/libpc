package com.pheiffware.lib.swing.graphics;

/**
 * If something implements this class then G2DRender will be able to render it.
 * 
 * @author Steve
 *
 */
public interface G2DRenderable
{
	void render(G2D g2d);
}
