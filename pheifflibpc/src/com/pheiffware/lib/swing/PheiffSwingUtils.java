package com.pheiffware.lib.swing;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class PheiffSwingUtils
{
	/**
	 * Adds a listener for a given key stroke to a component
	 * 
	 * @param component
	 * @param keycode
	 *            example: KeyEvent.VK_ESCAPE
	 * @param focusRequirements
	 *            JComponent.WHEN_FOCUSED,
	 *            JComponent.WHEN_IN_FOCUSED_WINDOW,
	 *            JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
	 * @param action
	 */
	public static void addKeyListenerToComponent(JComponent component, int keycode, int focusRequirements, AbstractAction action)
	{
		InputMap inputMap = component.getInputMap(focusRequirements);
		ActionMap actionMap = component.getActionMap();

		inputMap.put(KeyStroke.getKeyStroke(keycode, 0), keycode);
		actionMap.put(keycode, action);
	}
}
