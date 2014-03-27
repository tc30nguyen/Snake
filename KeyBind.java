import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.KeyStroke;
import javax.swing.JPanel;

public class KeyBind
{

	private JPanel panel;
	public Action enterAction, upAction, leftAction, downAction, rightAction;
	private char dir;

	public KeyBind(JPanel panel)
	{
		dir = 0;
		this.panel = panel;
		setKeys();
	}

	public char getDir()
	{
		return dir;
	}

	private void setKeys()
	{
		panel.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "doEnterAction");
		panel.getInputMap().put(KeyStroke.getKeyStroke("W"), "doUpAction");
		panel.getInputMap().put(KeyStroke.getKeyStroke("A"), "doLeftAction");
		panel.getInputMap().put(KeyStroke.getKeyStroke("S"), "doDownAction");
		panel.getInputMap().put(KeyStroke.getKeyStroke("D"), "doRightAction");
	
		enterAction = new EnterAction();
		upAction = new UpAction();
		leftAction = new LeftAction();
		downAction = new DownAction();
		rightAction = new RightAction();
		
		panel.getActionMap().put("doEnterAction", enterAction);
		panel.getActionMap().put("doUpAction", upAction);
		panel.getActionMap().put("doLeftAction", leftAction);
		panel.getActionMap().put("doDownAction", downAction);
		panel.getActionMap().put("doRightAction", rightAction);
	}

	private class UpAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent tf)
		{
			if(dir != 'D')
				dir = 'U';
		}
	}
	private class LeftAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent tf)
		{
			if(dir != 'R')
				dir = 'L';
		}
	}

	private class DownAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent tf)
		{
			if(dir != 'U')
				dir = 'D';
		}
	}

	private class RightAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent tf)
		{
			if(dir != 'L')
				dir = 'R';
		}
	}

	private class EnterAction extends AbstractAction
	{
		public void actionPerformed(ActionEvent e)
		{
			
		}
	}
}
