package com.pheiffware.lib.swing.keylisten;

import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;

/**
 * When the install() method is run, all key strokes to the application go to
 * this class. It will give call the press/release methods when a key is
 * pressed/released.
 * 
 * The state is also tracked.
 * 
 * All change events happen in the swing EDT. However, the key states are
 * synchronized and can be accessed anywhere.
 * 
 * 
 * @author Steve
 */
public abstract class PheiffKeyStrokeManager implements KeyEventDispatcher
{

	// Keeps track of the up/down state of all keys (don't know how many, but it
	// is way less that 1000).
	private final boolean[] keyStates = new boolean[1000];

	public void install()
	{
		KeyboardFocusManager manager = KeyboardFocusManager
				.getCurrentKeyboardFocusManager();
		manager.addKeyEventDispatcher(this);
	}

	public boolean getKeyState(int id)
	{
		synchronized (keyStates)
		{
			return keyStates[id];
		}
	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent e)
	{
		if (e.getID() == KeyEvent.KEY_PRESSED)
		{
			synchronized (keyStates)
			{
				if (!keyStates[e.getKeyCode()])
				{
					keyStates[e.getKeyCode()] = true;
					pressed(e.getKeyCode());
				}
			}
		}
		else if (e.getID() == KeyEvent.KEY_RELEASED)
		{
			synchronized (keyStates)
			{
				if (keyStates[e.getKeyCode()])
				{
					keyStates[e.getKeyCode()] = false;
					released(e.getKeyCode());
				}
			}
		}
		return false;
	}

	public abstract void pressed(int keyCode);

	public abstract void released(int keyCode);
}
