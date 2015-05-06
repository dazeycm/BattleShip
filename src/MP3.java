import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.player.Player;

/**
 * 
 * @author Craig Dazey and Nora Husani
 * @course CSE 211
 * @professor Dr. Kiper
 * @date 05/07/15
 * @description MP3 is a class for handling audio for a battleship game
 */
public class MP3 {
	private String filename;
	private Player player;
	public BufferedInputStream bis;

	// default constructor
	public MP3() {
	}

	/**
	 * Preconditions: none Postconditions: closes the player Side Effects: calls
	 * .close()
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		if (player != null)
			player.close();
	}

	// play the MP3 file to the sound card
	/**
	 * Preconditions: filename is not null Postconditions: audio file is played
	 * Side Effects: new BufferedInputStream and Player are created
	 * 
	 * @param filename
	 *            a string representing the audio file to play
	 */
	public void play(String filename) {
		try {

			InputStream stream = MP3.class.getClassLoader()
					.getResourceAsStream(filename);

			// FileInputStream fis = new FileInputStream(filename);

			bis = new BufferedInputStream(stream);
			// stream.close();
			player = new Player(bis);
			// bis.close();

		} catch (Exception e) {
			// System.out.println("Problem playing file " + filename);
			System.out.println(e);
		}

		// run in new thread to play in background
		new Thread() {
			public void run() {
				try {
					player.play();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();

	}

	// test client
	public static void main(String[] args) throws IOException {

	}

}