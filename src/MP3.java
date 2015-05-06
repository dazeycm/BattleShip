/*************************************************************************
 *  Compilation:  javac -classpath .:jl1.0.jar MP3.java         (OS X)
 *                javac -classpath .;jl1.0.jar MP3.java         (Windows)
 *  Execution:    java -classpath .:jl1.0.jar MP3 filename.mp3  (OS X / Linux)
 *                java -classpath .;jl1.0.jar MP3 filename.mp3  (Windows)
 *  
 *  Plays an MP3 file using the JLayer MP3 library.
 *
 *  Reference:  http://www.javazoom.net/javalayer/sources.html
 *
 *
 *  To execute, get the file jl1.0.jar from the website above or from
 *
 *      http://www.cs.princeton.edu/introcs/24inout/jl1.0.jar
 *
 *  and put it in your working directory with this file MP3.java.
 *
 *************************************************************************/

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class MP3 {
	private String filename;
	private Player player;
	public BufferedInputStream bis;

	// constructor that takes the name of an MP3 file
	public MP3() {

	}

	public void close() throws IOException {
		if (player != null)
			player.close();

	}

	// play the MP3 file to the sound card
	public void play() {
		try {
			InputStream stream = MP3.class.getClassLoader()
					.getResourceAsStream("music/LoveStory.mp3");

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
		String filename = "";
		MP3 mp3 = new MP3();
		mp3.play();

		// when the computation is done, stop playing it
		mp3.close();

		// play from the beginning
		mp3 = new MP3();
		mp3.play();

	}

}