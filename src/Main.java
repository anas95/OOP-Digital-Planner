import java.awt.EventQueue;

/**
 * This is main class where 
 * it will called class StartPage to begin.
 * EventQueue class is used to ensure the program have
 * safe-threading to avoid program crashes in certain situations.
 * @author ANAS
 */
public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new StartPage();
			}
		});
	}
}
