package songlist;
/**
 * @author Kate Sussman & Ben Green
 *
 */
import java.awt.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.*;
import java.io.IOException;



@SuppressWarnings("serial")
public class SongLib extends JFrame 
{
	JTextArea songList, songDetails;
	JButton add, delete, edit, submit;
	JTextField name, artist, album, year, songToAddTb, artistToAddTb, albumToAddTb, yearToAddTb;
	JPanel mainPanel, addSongPanel, songDetailsPanel, buttonPanel, songListPanel;
	JLabel songLabel, artistLabel, albumLabel, yearLabel, songToAddLabel, artistToAddLabel, albumToAddLabel, yearToAddLabel, label, message ;
	JList<String> songlist;
	DefaultListModel<String> listModel;
	Song song1, song2, song3;
	SongStorage storageList;
	Boolean editClicked = false, listClicked = true;
	
	public SongLib()
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
		
		//labels
		songLabel = new JLabel("");
		songLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		artistLabel = new JLabel("");
		artistLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		albumLabel = new JLabel("");
		albumLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		yearLabel = new JLabel("");
		yearLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		message = new JLabel();
		
		
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
		mainPanel.add(message, BorderLayout.CENTER);
		
		addSongPanel.setVisible(false);
	
		
		//frame attributes 
		this.add(mainPanel);
	
	}
	
	public static void main(String[] args) 
	{
		SongLib sl = new SongLib();	
		sl.setDefaultCloseOperation(EXIT_ON_CLOSE);
		sl.setSize(500, 500);
		sl.setResizable(false);
		sl.setLocationRelativeTo(null);
		sl.setVisible(true);
		sl.setBackground(Color.white);
	}
	
	public void createList()
	{	
		
		//initialize new arraylist
		storageList = new SongStorage("songs.txt");

		//initialize jlist 
		listModel = new DefaultListModel<String>();
		songlist = new JList<String>(listModel);
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
					
					
					if(listClicked && (songlist.getSelectedIndex() != -1))
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
						
					}
					
				}
		});
		
		songlist.setSelectedIndex(0);
		songListPanel.add(songlist);	
	}
	
	public void repopulateList() throws IOException
	{
		listClicked = false;
		
		listModel.removeAllElements();

		for(int i =0; i<storageList.size(); i++)
		{
			listModel.addElement(storageList.get(i).getName());
		}
		
		listClicked = true;
		
		//songlist = new JList(listModel);
		storageList.exportFile();
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
				int selected = songlist.getSelectedIndex();
				listModel.remove(selected);
				storageList.remove(selected);
				try {
					repopulateList();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				songlist.setSelectedIndex(0);
				
				//clear out fields if last song is deleted
				if(storageList.size() == 0)
				{
					songLabel.setText("");
					artistLabel.setText("");
					yearLabel.setText("");
					albumLabel.setText("");
				}
			}
			if(e.getSource()==edit)
			{
				addSongPanel.setVisible(true);
				editClicked = true;
				
				//retrieve song from arraylist and populate text fields 
				selectedValue = songlist.getSelectedIndex();
				Song songToEdit = storageList.get(selectedValue);
				
				songToAddTb.setText(songToEdit.getName());
				artistToAddTb.setText(songToEdit.getArtist());
				albumToAddTb.setText(songToEdit.getAlbum());
				yearToAddTb.setText(songToEdit.getYear());
				
				
				
				
			}
			if(e.getSource() == submit)
			{
				//create new song object and set its attributes 
				Song newSong = new Song();
				Boolean duplicate = false;
				
				
				newSong.setName(songToAddTb.getText());
				newSong.setArtist(artistToAddTb.getText());
				
				if(albumToAddTb.getText().equals(""))
				{
					newSong.setAlbum("Untitled Album");
				}else{
					newSong.setAlbum(albumToAddTb.getText());
				}
				
				newSong.setYear(yearToAddTb.getText());
				
				//add new song to listmodel and arraylist
				if(!newSong.getYear().equals(""))
					try
					{
						Integer.valueOf(newSong.getYear());
					}catch (NumberFormatException e1)
					{
						message.setText("Not a valid year!");
						duplicate = true;
					}
				
				if(newSong.getName().equals("") || newSong.getArtist().equals(""))
				{
					message.setText("You must enter a song name and artist");
				}else{
					
					if (storageList.search(newSong) != -1)
					{
						duplicate = true;
						message.setText("You cannot add duplicate songs.");
					}
					
					if(!duplicate)
					{
						message.setText("");
						listModel.addElement(newSong.getName());
						storageList.add(newSong);
						storageList.alphabetize();
						try 
						{
							repopulateList();
						} catch (IOException e1) 
						{
							e1.printStackTrace();
						}
						
						int newLocation = storageList.search(newSong);
						songlist.setSelectedIndex(newLocation);
					}
				}
					
					
				
				
				if(editClicked && !duplicate)
				{
					storageList.remove(selectedValue);
					try {
						repopulateList();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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

