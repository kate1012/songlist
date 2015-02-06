package songlist;

public class Song 
{
	private final String name;
	private final String artist;
	private final String album;
	private final String year;

	public Song(String name, String artist, String album, String year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getArtist()
	{
		return artist;
	}
	
	public String getAlbum()
	{
		return album;
	}
	
	public String getYear()
	{
		return year;
	}
}
