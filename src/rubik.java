public class rubik {
	public String cube[][] = new String[6][9];
	public int top;
	public int left;
	public int front;
	public int right;
	public int back;
	public int down;
	
	public rubik() {
		for (int cell = 0; cell < 9; cell++) {
			cube[0][cell] = "Y";
			cube[1][cell] = "R";
			cube[2][cell] = "G";
			cube[3][cell] = "O";
			cube[4][cell] = "B";
			cube[5][cell] = "W";
		}
		top = 0;
		front = 1;
		right = 2;
		back = 3;
		left = 4;
		down = 5;
	}
	
	public void reset() {
		for (int cell = 0; cell < 9; cell++) {
			cube[0][cell] = "Y";
			cube[1][cell] = "R";
			cube[2][cell] = "G";
			cube[3][cell] = "O";
			cube[4][cell] = "B";
			cube[5][cell] = "W";
		}
		top = 0;
		front = 1;
		right = 2;
		back = 3;
		left = 4;
		down = 5;
	}
	
	public void display(boolean debugMode) {
		String dump = null;
		for (int face = 0; face < 6; face++) {
			for (int cell = 0; cell < 9; cell++) {
				if (cell == 4 && debugMode) {
					dump = dump + "[" + cube[face][cell] + "]";
				}
				else {
					dump = dump + cube[face][cell];
				}
			}
			if (debugMode) {
				dump = dump + " ";
			}
		}
		
		System.out.println(dump.substring(4));
	}
	
	public void showOrientation() {
		System.out.println("Cube orientation ============");
		System.out.println("Top: " + cube[top][4]);
		System.out.println("Front: " + cube[front][4]);
		System.out.println("Right: " + cube[right][4]);
		System.out.println("Back: " + cube[back][4]);
		System.out.println("Left: " + cube[left][4]);
		System.out.println("Down: " + cube[down][4]);
		System.out.println("/Cube orientation ===========");
	}
	
	public void transpose(int face, boolean clockwise) {
		if (clockwise) {
			String temp = cube[face][0];
			cube[face][0] = cube[face][6];
			cube[face][6] = cube[face][8];
			cube[face][8] = cube[face][2];
			cube[face][2] = temp;
		}
		else {
			String temp = cube[face][0];
			cube[face][0] = cube[face][2];
			cube[face][2] = cube[face][8];
			cube[face][8] = cube[face][6];
			cube[face][6] = temp;
		}
	}
	
	public void roll(int f1, int f2, int f3, int f4, boolean clockwise, int c1, int c2, int c3) {
		// Clockwise means rolls occur f1->f2->f3->f4->f1
		if (!clockwise) {
			String t1 = cube[f1][c1];
			String t2 = cube[f1][c2];
			String t3 = cube[f1][c3];
			
			cube[f1][c1] = cube[f2][c1];
			cube[f1][c2] = cube[f2][c2];
			cube[f1][c3] = cube[f2][c3];
			
			cube[f2][c1] = cube[f3][c1];
			cube[f2][c2] = cube[f3][c2];
			cube[f2][c3] = cube[f3][c3];
			
			cube[f3][c1] = cube[f4][c1];
			cube[f3][c2] = cube[f4][c2];
			cube[f3][c3] = cube[f4][c3];
			
			cube[f4][c1] = t1;
			cube[f4][c2] = t2;
			cube[f4][c3] = t3;
		}
		else {
			String t1 = cube[f1][c1];
			String t2 = cube[f1][c2];
			String t3 = cube[f1][c3];
			
			cube[f1][c1] = cube[f4][c1];
			cube[f1][c2] = cube[f4][c2];
			cube[f1][c3] = cube[f4][c3];
			
			cube[f4][c1] = cube[f3][c1];
			cube[f4][c2] = cube[f3][c2];
			cube[f4][c3] = cube[f3][c3];
			
			cube[f3][c1] = cube[f2][c1];
			cube[f3][c2] = cube[f2][c2];
			cube[f3][c3] = cube[f2][c3];
			
			cube[f2][c1] = t1;
			cube[f2][c2] = t2;
			cube[f2][c3] = t3;
		}
	}
	
	public void process(String command) {
		/*
		 * Modify orientation only
		 */
		// Affected: top, back, down, front;
		if (command.equals("X")) {
			System.out.println("Rotate along X axis CW");
			int temp = top;
			top = front;
			front = down;
			down = back;
			back = temp;
		}
		else if (command.equals("X'")) {
			System.out.println("Rotate along X axis CCW");
			int temp = top;
			top = back;
			back = down;
			down = front;
			front = temp;
		}
		// Affected: front, left, right, back;
		else if (command.equals("Y")) {
			System.out.println("Rotate along Y axis CW");
			int temp = front;
			front = right;
			right = back;
			back = left;
			left = temp;
		}
		else if (command.equals("Y'")) {
			System.out.println("Rotate along Y axis CCW");
			int temp = front;
			front = left;
			left = back;
			back = right;
			right = temp;
		}
		// Affected: top, left, right, down
		else if (command.equals("Z")) {
			System.out.println("Rotate along Z axis CW");
			int temp = top;
			top = left;
			left = down;
			down = right;
			right = temp;
		}
		else if (command.equals("Z'")) {
			System.out.println("Rotate along Z axis CCW");
			int temp = top;
			top = right;
			right = down;
			down = left;
			left = temp;
		}
		
		/*
		 * Modify cube
		 */
		else if (command.equals("U")) {
			System.out.println("Turn top face CW");
		}
		else if (command.equals("U'")) {
			System.out.println("Turn top face CCW");
		}
		else if (command.equals("D")) {
			System.out.println("Turn bottom face CW");
		}
		else if (command.equals("D'")) {
			System.out.println("Turn bottom face CCW");
		}
		else if (command.equals("F")) {
			System.out.println("Turn front face CW");
			this.roll(top, right, down, left, true, 0, 3, 6);
			this.transpose(front, true);
		}
		else if (command.equals("F'")) {
			System.out.println("Turn front face CCW");
			this.roll(top, right, down, left, false, 0, 3, 6);
			this.transpose(front, false);
		}
		else if (command.equals("B")) {
			System.out.println("Turn back face CW");
		}
		else if (command.equals("B'")) {
			System.out.println("Turn back face CCW");
		}
		else if (command.equals("R")) {
			System.out.println("Turn right face CW");
		}
		else if (command.equals("R'")) {
			System.out.println("Turn right face CCW");
		}
		else if (command.equals("L")) {
			System.out.println("Turn left face CW");
		}
		else if (command.equals("L'")) {
			System.out.println("Turn left face CCW");
		}
		
		else if (command.equals("RESET")) {
			this.reset();
		}
		/*
		 * Unrecognized command
		 */
		else {
			System.out.println("Unrecognized command");
		}
	}
}
