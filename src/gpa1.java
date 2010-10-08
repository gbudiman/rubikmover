import java.io.*;

public class gpa1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
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
		}
		
	}

}
