import java.io.*;

public class GPA1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String text;
			File file = new File(args[0]);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			rubik cube = new rubik();
			while ((text = br.readLine()) != null) {
				if (!text.substring(0, 1).equals("#")) {
					if (text.trim().length() == 54) {
						cube.setState(text);
					}
					else {
						cube.process(text);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			File file = new File(args[0]);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		String commandIn;
		rubik cube;
		boolean debugMode;
		
		if (args.length > 0 && args[0].equals("debug")) {
			debugMode = true;
		}
		else {
			debugMode = false;
		}
		
		try {
			commandIn = br.readLine();
			if (commandIn.trim().length() != 54) {
				cube = new rubik();
			}
			else {
				cube = new rubik();
				cube.setState(commandIn);
				commandIn = br.readLine();
			}
			
			while (!commandIn.equals("OUTPUT")) {
				cube.process(commandIn);
				
				if (debugMode) {
					cube.showOrientation();
					cube.display(debugMode);
				}
				commandIn = br.readLine();
			}
			
			cube.display(debugMode);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Block failure");
			e.printStackTrace();
		}*/
		
	}

}
