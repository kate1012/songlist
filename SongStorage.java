package songlist;
/**
 * @author Kate Sussman & Ben Green
 *
 */
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

/**
 * @author Kate Sussman & Ben Green
 *
 */
@SuppressWarnings("serial")
public class SongStorage extends ArrayList<Song> {
	
	String filename;
	
	public SongStorage(String filename){
		this.filename = filename;
		this.importFile();
	}

	
	/**Import songs from list where values are separated by *only* semicolons.
	 * 
	 */
	public void importFile() 
	{
		try 
		{
			for(String line : Files.readAllLines(Paths.get(filename), Charset.defaultCharset()))
			{
				StringTokenizer tokz = new StringTokenizer(line, "|");
				
				if(tokz.hasMoreTokens())
				{
				
					Song newsong = new Song();
					newsong.setName(tokz.nextToken());
					newsong.setArtist(tokz.nextToken());	
					newsong.setAlbum(tokz.nextToken());
					if(tokz.hasMoreTokens())
					{
						newsong.setYear(tokz.nextToken());
					}
					this.add(newsong);
				}
				
			}
		} catch (IOException e) 
		{
			System.out.println("File error!");
			e.printStackTrace();
		}
	}
	
	public void exportFile() throws IOException
	{
		FileWriter output = new FileWriter(filename);
		for(Song s : this)
		{
			output.write(s.getName() + "|" + s.getArtist() + "|" + s.getAlbum() + "|" + s.getYear() + "\n");
		}
		output.close();
	}
	
	/**Alphabetize song list
	 * 
	 */
	public void alphabetize()
	{
		Collections.sort(this, new Comparator<Song>()
				{

			@Override
			public int compare(Song arg0, Song arg1) 
			{
				return arg0.getName().compareTo(arg1.getName());
			}
			
		});
	}


	/**
	 * @param query Song to search for
	 * @return Index of song in array, or -1 if not found
	 */
	public int search(Song query) {
		for(int x = 0; x < this.size(); x++)
		{
			if(this.get(x).getName().equals(query.getName()) && this.get(x).getArtist().equals(query.getArtist()))
			{
				return x;
			}
		}
		return -1;
	}
	

}
