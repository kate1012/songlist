package songlist;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class SongListPanel extends JPanel 
{
	//song list panel stuff
	//JPanel songListPanel = new JPanel();
	//songListPanel.setLayout(new BoxLayout(songListPanel, BoxLayout.Y_AXIS));
	//songListPanel.setBorder(BorderFactory.createLineBorder(Color.black));
	
	public SongListPanel()
	{
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createLineBorder(Color.black));
	}
	


}
