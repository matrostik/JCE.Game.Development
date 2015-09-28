import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class Sound {
	
	/*
	 * Fire sound
	 */
	public static void playFire()
	{
		playSound(new File("../blaster.wav"));
	}
	
	/*
	 * Explose sound
	 */
	public static void playExplose()
	{
		playSound(new File("../explosion-01.wav"));
	}

	/*
	 * Fly sound
	 */
	public static void playFly()
	{
		playSound(new File("../fly1.wav"));
	}
	
	/*
	 * Fly sound
	 */
	public static void playStartGame()
	{
		playSound(new File("../ready4action.wav"));
	}
	
	/*
	 * Game over sound
	 */
	public static void playGameOver()
	{
		try 
		{
			Thread.sleep(2000);
		} 
		catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playSound(new File("../game_over.wav"));
	}
	
	private static void playSound(File audio)
	{
		try 
		{ 
		    AudioInputStream stream;
		    AudioFormat format;
		    DataLine.Info info;
		    Clip clip;

		    stream = AudioSystem.getAudioInputStream(audio);
		    format = stream.getFormat();
		    info = new DataLine.Info(Clip.class, format);
		    clip = (Clip) AudioSystem.getLine(info);
		    clip.open(stream);
		    clip.start();
		}
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
}
