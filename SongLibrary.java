package songlist;


import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SongLibrary extends JFrame 
{
	JTextArea songList, songDetails;
	JButton add, delete, edit, submit;
	JTextField name, artist, album, year, songToAddTb, artistToAddTb, albumToAddTb, yearToAddTb;
	JPanel mainPanel, addSongPanel, songDetailsPanel, buttonPanel, songListPanel;
	JLabel songLabel, artistLabel, albumLabel, yearLabel, songToAddLabel, artistToAddLabel, albumToAddLabel, yearToAddLabel, label;
	JList songlist;
	DefaultListModel listModel;
	Song song1, song2, song3;
	ArrayList<Song> storageList;
	Boolean editClicked = false, listClicked = true;
	
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
		song1.setName("Name1");
		song1.setArtist("Artist1");
		song1.setAlbum("Album1");
		song1.setYear("Year1");
		song2.setName("Name2");
		song2.setArtist("Artist2");
		song2.setAlbum("Album2");
		song2.setYear("Year2");
		song3.setName("Name3");
		song3.setArtist("Artist3");
		song3.setAlbum("Album3");
		song3.setYear("Year3");
		
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
		yearToAddTb.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyPressed(KeyEvent e) {

		            int key = e.getKeyCode();

		            /* Restrict input to only integers */
		            if (key < 96 && key > 105) {}
		            
		            else
		            	e.setKeyChar(e.getKeyChar());
		        };
		    });
		
		//buttons
		add = new JButton("Add");
		add.addActionListener(lForButton);
		//add.setBackground(Color.blue);
		//add.setBorder(BorderFactory.createLineBorder(Color.red));
		delete = new JButton("Delete");
		delete.addActionListener(lForButton);
		delete.setEnabled(false);
		edit = new JButton("Edit");
		edit.addActionListener(lForButton);
		edit.setEnabled(false);
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
		
		//add labels to songDetailsPanel
				songDetailsPanel.add(songLabel);
				songDetailsPanel.add(artistLabel);
				songDetailsPanel.add(albumLabel);
				songDetailsPanel.add(yearLabel);
		
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
		//int selectedValue;
		
		//initialize new arraylist
		storageList = new ArrayList<Song>();
		storageList.add(song1);
		storageList.add(song2);
		storageList.add(song3);
		
		//Collections.sort(storageList.toString());

		//initialize jlist 
		listModel = new DefaultListModel();
		songlist = new JList(listModel);
		songlist.setSelectedIndex(0);
		songlist.setLayoutOrientation(JList.VERTICAL);
		songlist.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		songlist.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		songlist.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(songlist);
		listScroller.setPreferredSize(new Dimension(250, 80));

		//add arraylist elements to jlist
		for(int i =0; i<storageList.size(); i++)
		{
			listModel.addElement(storageList.get(i).getName());
		}
		
		songlist.addListSelectionListener(new ListSelectionListener(){
			public void valueChanged(ListSelectionEvent ev)
			{
				
				//if(!ev.getValueIsAdjusting())
				//{
					
					
					if(listClicked)
					{
						//enable delete and edit buttons
						delete.setEnabled(true);
						edit.setEnabled(true);
			
						int selectedValue = songlist.getSelectedIndex();
						Song selectedSong = (Song) storageList.get(selectedValue);
						songLabel.setText(selectedSong.getName());
						artistLabel.setText(selectedSong.getArtist());
						albumLabel.setText(selectedSong.getAlbum());
						yearLabel.setText(selectedSong.getYear());
						//yearLabel.setText(selectedSong.toString());
		
						
					}
					
				}
			//}
		});
		
		
		
		
		//add songlist to songlistpanel
		songListPanel.add(songlist);	
	}
	
	public void repopulateList()
	{
		listClicked = false;
		
		listModel.removeAllElements();

		for(int i =0; i<storageList.size(); i++)
		{
			listModel.addElement(storageList.get(i).getName());
		}
		
		listClicked = true;
		
		//songlist = new JList(listModel);
	}
	
	public void removeElements(int index)
	{
		storageList.remove(index);
		listModel.remove(index);
	}
	
	class ListenForButton implements ActionListener
	{
		int selectedValue;
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==add)
			{
				addSongPanel.setVisible(true);
			}
			if(e.getSource()==delete)
			{
				int selectedIndex = songlist.getSelectedIndex();
				Object selectedValue = songlist.getSelectedValue();
			}
			if(e.getSource()==edit)
			{
				addSongPanel.setVisible(true);
				editClicked = true;
				
				//retreive song from arraylist and populate text fields 
				selectedValue = songlist.getSelectedIndex();
				Song songToEdit = (Song) storageList.get(selectedValue);
				songToAddTb.setText(songToEdit.getName());
				artistToAddTb.setText(songToEdit.getArtist());
				albumToAddTb.setText(songToEdit.getAlbum());
				yearToAddTb.setText(songToEdit.getYear());
				
				//removeElements(selectedValue);
				
				
				
			}
			if(e.getSource() == submit)
			{
				//create new song object and set its attributes 
				Song newSong = new Song();
				newSong.setName(songToAddTb.getText());
				newSong.setArtist(artistToAddTb.getText());
				newSong.setAlbum(albumToAddTb.getText());
				newSong.setYear(yearToAddTb.getText());
				
				//add new song to listmodel and arraylist
				
				if(!(songToAddTb.getText().equals("") || artistToAddTb.getText().equals("")))
				{
					listModel.addElement(newSong.getName());
					storageList.add(newSong);
				}
					
				
				
				if(editClicked)
				{
					storageList.remove(selectedValue);
					repopulateList();
					//listModel.removeAllElements();
					//storageList.remove(selectedValue);
					//listModel.remove(selectedValue);
				}
				
				
				addSongPanel.setVisible(false);
				
				editClicked = false;
				
				//repopulateList();
			}
		}
	}
}

