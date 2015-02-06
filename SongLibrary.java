package songlist;


import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;

public class SongLibrary extends JFrame 
{
	JTextArea songList, songDetails;
	JButton add, delete, edit, submit;
	JTextField name, artist, album, year, songToAddTb, artistToAddTb, albumToAddTb, yearToAddTb;
	JPanel mainPanel, addSongPanel, songDetailsPanel, buttonPanel, songListPanel;
	JLabel songLabel, artistLabel, albumLabel, yearLabel, songToAddLabel, artistToAddLabel, albumToAddLabel, yearToAddLabel;
	JList songlist;
	DefaultListModel listModel;
	Song song1, song2, song3;
	
	public SongLibrary()
	{
		//ListenForButton lForButton = new ListenForButton(this);
		ListenForButton lForButton = new ListenForButton();
		
		//main panel stuff
		mainPanel = new JPanel(new BorderLayout());
		mainPanel.setLayout(new BorderLayout());
		
		//button panel stuff
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		buttonPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
	
		//song list panel stuff
		songListPanel = new JPanel();
		songListPanel.setLayout(new BoxLayout(songListPanel, BoxLayout.Y_AXIS));
		songListPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		//songListPanel.setPreferredSize(new Dimension(200, 200));
	
		
		//song details panel stuff
		songDetailsPanel = new JPanel();
		songDetailsPanel.setLayout(new BoxLayout(songDetailsPanel, BoxLayout.Y_AXIS));
		songDetailsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		songDetailsPanel.setPreferredSize(new Dimension(100, 100));
		
		//add song panel stuff
		addSongPanel = new JPanel();
		addSongPanel.setLayout(new BoxLayout(addSongPanel, BoxLayout.Y_AXIS));
		//addSongPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		//song1 = new Song("I'll See You At The Ring Of Fire", "Brave Bird", "You're Not Quite Ready", "2014");
		//song2 = new Song("Song 2 name", "Song 2 artist", "song 2 album", "song 2 year");
		//song3 = new Song("Song 3 name", "song 3 artist", "song 3 album", "song 3 year");
		song1 = new Song();
		song2 = new Song();
		song3 = new Song();
		song1.setName("Name");
		song1.setArtist("Artist");
		song1.setAlbum("Album");
		song1.setYear("Year");
		song2.setName("Name");
		song2.setArtist("Artist");
		song2.setAlbum("Album");
		song2.setYear("Year");
		song3.setName("Name");
		song3.setArtist("Artist");
		song3.setAlbum("Album");
		song3.setYear("Year");
		
		//labels
		songLabel = new JLabel("song name");
		songLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		artistLabel = new JLabel("artist name");
		artistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		albumLabel = new JLabel("album name");
		albumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		yearLabel = new JLabel("year");
		yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		songToAddLabel = new JLabel("Song Name:");
		artistToAddLabel = new JLabel("Artist Name:");
		albumToAddLabel = new JLabel("Album Name:");
		yearToAddLabel = new JLabel("Year:");
		
		//textboxes
		songToAddTb = new JTextField();
		artistToAddTb = new JTextField();
		albumToAddTb = new JTextField();
		yearToAddTb = new JTextField();
		
		//buttons
		add = new JButton("Add");
		add.addActionListener(lForButton);
		delete = new JButton("Delete");
		delete.addActionListener(lForButton);
		edit = new JButton("Edit");
		edit.addActionListener(lForButton);
		submit = new JButton("Submit");
		submit.addActionListener(lForButton);
		
		//add things to button panel
		buttonPanel.add(add);
		buttonPanel.add(delete);
		buttonPanel.add(edit);
		
		//add things to add song panel
		addSongPanel.add(songToAddLabel);
		addSongPanel.add(songToAddTb);
		addSongPanel.add(artistToAddLabel);
		addSongPanel.add(artistToAddTb);
		addSongPanel.add(albumToAddLabel);
		addSongPanel.add(albumToAddTb);
		addSongPanel.add(yearToAddLabel);
		addSongPanel.add(yearToAddTb);
		addSongPanel.add(submit);
		
		createList();
		//songListPanel.add(songlist);

		//add things to main panel
		mainPanel.add(songDetailsPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.EAST);
		mainPanel.add(songListPanel, BorderLayout.WEST);
		mainPanel.add(addSongPanel, BorderLayout.SOUTH);
		
		addSongPanel.setVisible(false);
		
		//frame attributes 
		this.add(mainPanel);
	
	}
	
	public static void main(String[] args) 
	{
		SongLibrary sl = new SongLibrary();	
		sl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		sl.setSize(500, 500);
		sl.setResizable(false);
		sl.setLocationRelativeTo(null);
		sl.setVisible(true);
		sl.setBackground(Color.white);
	}
	
	public void createList()
	{	
		Song[] songs = {song1, song2, song3};
			
		listModel = new DefaultListModel();
		listModel.addElement("new element");
		
		songlist = new JList(listModel);
		songlist.setLayoutOrientation(JList.VERTICAL);
		//songlist.setLayout(new BoxLayout(songlist, BoxLayout.Y_AXIS));
		songlist.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent ev)
			{
				
				if(!ev.getValueIsAdjusting())
				{
					
					JList source = (JList) ev.getSource();
					//int selectedValue = source.getSelectedIndex();
					//Song selectedSong = (Song) source.getModel().getElementAt(selectedValue);
					Song s = (Song) source.getSelectedValue();
					//String S = (String) dlm.getElementAt(subList.getSelectedValue().toString());
					//Song ss = (Song) listModel.getElementAt(songlist.getSelectedIndex());
					//Song s = (Song) listModel.elementAt(songlist.getSelectedIndex());
					//String selected = source.getSelectedValue().toString();
					
					songLabel.setText(s.getName());
					//artistLabel.setText(selectedSong.getArtist());
					//albumLabel.setText(s.getAlbum());
					songDetailsPanel.add(songLabel);
				}
			}
		});
		
		
		songlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		songlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		songlist.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(songlist);
		//listScroller.setLayout(new BoxLayout(listScroller, BoxLayout.Y_AXIS));
		listScroller.setPreferredSize(new Dimension(250, 80));
		//listScroller.setBackground(Color.OPAQUE);
		
		
		for(int i =0; i<songs.length; i++)
		{
			listModel.addElement(songs[i].getName());
		}
		
		songListPanel.add(songlist);	
	}
	
	class ListenForButton implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add)
			{
				addSongPanel.setVisible(true);
				System.out.println("add");
			}
			if(e.getSource()==delete)
			{
				int selectedIndex = songlist.getSelectedIndex();
				Object selectedValue = songlist.getSelectedValue();
				//listModel.removeElementAt(selectedIndex);
				//listModel.remove(selectedIndex);
				//listModel.remove(1);
			}
			if(e.getSource()==edit)
			{
				
			}
			if(e.getSource() == submit)
			{
				
				Song song = new Song();
				song.setName(songToAddTb.getText());
				song.setArtist(artistToAddTb.getText());
				song.setAlbum(albumToAddTb.getText());
				song.setYear(yearToAddTb.getText());
				
				listModel.addElement(song.getName());
			}
		}
	}
}

/*class ListenForButton implements ActionListener
{
	SongLibrary sl;
	
	public ListenForButton(SongLibrary sl)
	{
		this.sl = sl;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==sl.add)
		{
			sl.mainPanel.add(sl.addSongPanel, BorderLayout.SOUTH);
		}
		if(e.getSource()==sl.delete)
		{
			
		}
		if(e.getSource()==sl.edit)
		{
			
		}
	}
}*/
