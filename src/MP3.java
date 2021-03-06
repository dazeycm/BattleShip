import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import javazoom.jl.player.Player;

public class MP3 {
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
	public void play(String filename) {
		try {

			InputStream stream = MP3.class.getClassLoader()
					.getResourceAsStream(filename);

			bis = new BufferedInputStream(stream);
			player = new Player(bis);

		} catch (Exception e) {
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