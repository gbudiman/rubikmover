public class rubik {
	public String cube[][] = new String[6][9];
	public int top;
	public int left;
	public int front;
	public int right;
	public int back;
	public int down;
	public int[] topCells;
	public int[] leftCells;
	public int[] frontCells;
	public int[] rightCells;
	public int[] backCells;
	public int[] downCells;
	
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
			String temp2 = cube[face][1];
			cube[face][0] = cube[face][6];
			cube[face][6] = cube[face][8];
			cube[face][8] = cube[face][2];
			cube[face][2] = temp;
			
			cube[face][1] = cube[face][3];
			cube[face][3] = cube[face][7];
			cube[face][7] = cube[face][5];
			cube[face][5] = temp2;
		}
		else {
			String temp = cube[face][0];
			String temp2 = cube[face][1];
			cube[face][0] = cube[face][2];
			cube[face][2] = cube[face][8];
			cube[face][8] = cube[face][6];
			cube[face][6] = temp;
			
			cube[face][1] = cube[face][5];
			cube[face][5] = cube[face][7];
			cube[face][7] = cube[face][3];
			cube[face][3] = temp2;
		}
	}
	
	public void setState(String iState) {
		int stringPointer = 0;
		for (int face = 0; face < 6; face++) {
			for (int i = 0; i < 9; i++) {
				cube[face][i] = iState.substring(stringPointer, stringPointer+1);
				stringPointer++;
			}
		}
		//this.display(false);
	}
	
	public int[] cArray(int i0, int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
		int[] newValue = {i0, i1, i2, i3, i4, i5, i6, i7, i8};
		return newValue;
	}
	
	public void setCells() {
		switch (front) {
		case 1:
			switch (top) {
			case 0: topCells = cArray(2,5,8,1,4,7,0,3,6);
					frontCells = cArray(0,1,2,3,4,5,6,7,8);
					leftCells = cArray(0,1,2,3,4,5,6,7,8);
					rightCells = cArray(0,1,2,3,4,5,6,7,8);
					backCells = cArray(8,7,6,5,4,3,2,1,0);
					downCells = cArray(6,3,0,7,4,1,8,5,4); break;
			} 
			break;
		}
	}
	
	public int returnCell(int face, int perspectiveIndex) {
		switch (face) {
		case 0: return topCells[perspectiveIndex]; 
		case 1: return frontCells[perspectiveIndex];
		case 2: return rightCells[perspectiveIndex];
		case 3: return backCells[perspectiveIndex];
		case 4: return leftCells[perspectiveIndex];
		case 5: return downCells[perspectiveIndex];
		}
		
		return -1;
	}
	
	public void rollObs(int f1, int f2, int f3, int f4, boolean clockwise, int c1, int c2, int c3) {
		// Clockwise means rolls occur f1->f2->f3->f4->f1
		if (!clockwise) {
			String t1 = cube[f1][returnCell(f1, c1)];
			String t2 = cube[f1][returnCell(f1, c2)];
			String t3 = cube[f1][returnCell(f1, c3)];
			
			cube[f1][returnCell(f1, c1)] = cube[f2][returnCell(f2, c1)];
			cube[f1][returnCell(f1, c2)] = cube[f2][returnCell(f2, c2)];
			cube[f1][returnCell(f1, c3)] = cube[f2][returnCell(f2, c3)];
			
			cube[f2][returnCell(f2, c1)] = cube[f3][returnCell(f3, c1)];
			cube[f2][returnCell(f2, c2)] = cube[f3][returnCell(f3, c2)];
			cube[f2][returnCell(f2, c3)] = cube[f3][returnCell(f3, c3)];
			
			cube[f3][returnCell(f3, c1)] = cube[f4][returnCell(f4, c1)];
			cube[f3][returnCell(f3, c2)] = cube[f4][returnCell(f4, c2)];
			cube[f3][returnCell(f3, c3)] = cube[f4][returnCell(f4, c3)];
			
			cube[f4][returnCell(f4, c1)] = t1;
			cube[f4][returnCell(f4, c2)] = t2;
			cube[f4][returnCell(f4, c3)] = t3;
		}
		else {
			String t1 = cube[f1][returnCell(f1, c1)];
			String t2 = cube[f1][returnCell(f1, c2)];
			String t3 = cube[f1][returnCell(f1, c3)];
			
			cube[f1][returnCell(f1, c1)] = cube[f4][returnCell(f4, c1)];
			cube[f1][returnCell(f1, c2)] = cube[f4][returnCell(f4, c2)];
			cube[f1][returnCell(f1, c3)] = cube[f4][returnCell(f4, c3)];
			
			cube[f4][returnCell(f4, c1)] = cube[f3][returnCell(f3, c1)];
			cube[f4][returnCell(f4, c2)] = cube[f3][returnCell(f3, c1)];
			cube[f4][returnCell(f4, c3)] = cube[f3][returnCell(f3, c1)];
			
			cube[f3][returnCell(f3, c1)] = cube[f2][returnCell(f2, c1)];
			cube[f3][returnCell(f3, c2)] = cube[f2][returnCell(f2, c2)];
			cube[f3][returnCell(f3, c3)] = cube[f2][returnCell(f2, c3)];
			
			cube[f2][returnCell(f2, c1)] = t1;
			cube[f2][returnCell(f2, c2)] = t2;
			cube[f2][returnCell(f2, c3)] = t3;
		}
	}
	
	public void roll(int f1, int c11, int c12, int c13, 
			int f2, int c21, int c22, int c23, 
			int f3, int c31, int c32, int c33, 
			int f4, int c41, int c42, int c43, boolean clockwise) {
		String t1 = cube[f1][c11];
		String t2 = cube[f1][c12];
		String t3 = cube[f1][c13];
		
		if (!clockwise) {
			cube[f1][c11] = cube[f2][c21];
			cube[f1][c12] = cube[f2][c22];
			cube[f1][c13] = cube[f2][c23];
			
			cube[f2][c21] = cube[f3][c31];
			cube[f2][c22] = cube[f3][c32];
			cube[f2][c23] = cube[f3][c33];
			
			cube[f3][c31] = cube[f4][c41];
			cube[f3][c32] = cube[f4][c42];
			cube[f3][c33] = cube[f4][c43];
			
			cube[f4][c41] = t1;
			cube[f4][c42] = t2;
			cube[f4][c43] = t3;
		}
		else {
			cube[f1][c11] = cube[f4][c41];
			cube[f1][c12] = cube[f4][c42];
			cube[f1][c13] = cube[f4][c43];
			
			cube[f4][c41] = cube[f3][c31];
			cube[f4][c42] = cube[f3][c32];
			cube[f4][c43] = cube[f3][c33];
			
			cube[f3][c31] = cube[f2][c21];
			cube[f3][c32] = cube[f2][c22];
			cube[f3][c33] = cube[f2][c23];
			
			cube[f2][c21] = t1;
			cube[f2][c22] = t2;
			cube[f2][c23] = t3;
		}
	}
	
	public void process(String command) {
		/*
		 * Modify orientation only
		 */
		// Affected: top, back, down, front;
		if (command.equals("X")) {
			//System.out.println("Rotate along X axis CW");
			int temp = top;
			top = front;
			front = down;
			down = back;
			back = temp;
		}
		else if (command.equals("X'")) {
			//System.out.println("Rotate along X axis CCW");
			int temp = top;
			top = back;
			back = down;
			down = front;
			front = temp;
		}
		// Affected: front, left, right, back;
		else if (command.equals("Y")) {
			//System.out.println("Rotate along Y axis CW");
			int temp = front;
			front = right;
			right = back;
			back = left;
			left = temp;
		}
		else if (command.equals("Y'")) {
			//System.out.println("Rotate along Y axis CCW");
			int temp = front;
			front = left;
			left = back;
			back = right;
			right = temp;
		}
		// Affected: top, left, right, down
		else if (command.equals("Z")) {
			//System.out.println("Rotate along Z axis CW");
			int temp = top;
			top = left;
			left = down;
			down = right;
			right = temp;
		}
		else if (command.equals("Z'")) {
			//System.out.println("Rotate along Z axis CCW");
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
			//System.out.println("Turn top face CW");
			switch (front) {
			case 0: 
				switch (top) {
				case 1: this.roll(front,0,3,6,left,0,3,6,back,0,3,6,right,8,5,2,true); break;
				case 2: this.roll(front,6,7,8,left,0,3,6,back,2,1,0,right,8,5,2,true); break;
				case 3: this.roll(front,8,5,2,left,0,3,6,back,8,5,2,right,8,5,2,true); break;
				case 4: this.roll(front,2,1,0,left,0,3,6,back,6,7,8,right,8,5,2,true); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,true); break;
				case 2: this.roll(front,8,5,2,left,6,7,8,back,0,3,6,right,2,1,0,true); break;
				case 4: this.roll(front,0,3,6,left,6,7,8,back,8,5,2,right,2,1,0,true); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,true); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,true); break;
				case 1: this.roll(front,0,3,6,left,0,3,6,back,8,5,2,right,0,3,6,true); break;
				case 3: this.roll(front,8,5,2,left,8,5,2,back,0,3,6,right,8,5,2,true); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,true); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,true); break;
				case 2: this.roll(front,0,3,6,left,2,1,0,back,8,5,2,right,6,7,8,true); break;
				case 4: this.roll(front,8,5,2,left,2,1,0,back,0,3,6,right,6,7,8,true); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,true); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,true); break;
				case 1: this.roll(front,8,5,2,left,0,3,6,back,0,3,6,right,0,3,6,true); break;
				case 3: this.roll(front,0,3,6,left,8,5,2,back,8,5,2,right,8,5,2,true); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,true); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(front,0,3,6,left,8,5,2,back,0,3,6,right,0,3,6,true); break;
				case 2: this.roll(front,2,1,0,left,8,5,2,back,6,7,8,right,0,3,6,true); break;
				case 3: this.roll(front,8,5,2,left,8,5,2,back,8,5,2,right,0,3,6,true); break;
				case 4: this.roll(front,6,7,8,left,8,5,2,back,2,1,0,right,0,3,6,true); break;
				}
			}
			this.transpose(top, true);
		}
		else if (command.equals("U'")) {
			//System.out.println("Turn top face CCW");
			switch (front) {
			case 0: 
				switch (top) {
				case 1: this.roll(front,0,3,6,left,0,3,6,back,0,3,6,right,8,5,2,false); break;
				case 2: this.roll(front,6,7,8,left,0,3,6,back,2,1,0,right,8,5,2,false); break;
				case 3: this.roll(front,8,5,2,left,0,3,6,back,8,5,2,right,8,5,2,false); break;
				case 4: this.roll(front,2,1,0,left,0,3,6,back,6,7,8,right,8,5,2,false); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,false); break;
				case 2: this.roll(front,8,5,2,left,6,7,8,back,0,3,6,right,2,1,0,false); break;
				case 4: this.roll(front,0,3,6,left,6,7,8,back,8,5,2,right,2,1,0,false); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,false); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,false); break;
				case 1: this.roll(front,0,3,6,left,0,3,6,back,8,5,2,right,0,3,6,false); break;
				case 3: this.roll(front,8,5,2,left,8,5,2,back,0,3,6,right,8,5,2,false); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,false); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,false); break;
				case 2: this.roll(front,0,3,6,left,2,1,0,back,8,5,2,right,6,7,8,false); break;
				case 4: this.roll(front,8,5,2,left,2,1,0,back,0,3,6,right,6,7,8,false); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,false); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(front,2,1,0,left,2,1,0,back,2,1,0,right,2,1,0,false); break;
				case 1: this.roll(front,8,5,2,left,0,3,6,back,0,3,6,right,0,3,6,false); break;
				case 3: this.roll(front,0,3,6,left,8,5,2,back,8,5,2,right,8,5,2,false); break;
				case 5: this.roll(front,6,7,8,left,6,7,8,back,6,7,8,right,6,7,8,false); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(front,0,3,6,left,8,5,2,back,0,3,6,right,0,3,6,false); break;
				case 2: this.roll(front,2,1,0,left,8,5,2,back,6,7,8,right,0,3,6,false); break;
				case 3: this.roll(front,8,5,2,left,8,5,2,back,8,5,2,right,0,3,6,false); break;
				case 4: this.roll(front,6,7,8,left,8,5,2,back,2,1,0,right,0,3,6,false); break;
				}
			}
			this.transpose(top, false);
		}
		else if (command.equals("D")) {
			// This sequence is written backwards. The argument for clockwise roll should be reversed
			//System.out.println("Turn bottom face CW");
			switch (front) {
			case 0: 
				switch(top) {
				case 1: this.roll(front,2,5,8,left,2,5,8,back,2,5,8,right,6,3,0,false); break;
				case 2: this.roll(front,0,1,2,left,2,5,8,back,8,7,6,right,6,3,0,false); break;
				case 3: this.roll(front,6,3,0,left,2,5,8,back,6,3,0,right,6,3,0,false); break;
				case 4: this.roll(front,8,7,6,left,2,5,8,back,0,1,2,right,6,3,0,false); break;
				} break;
			case 1:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,false); break;
				case 2: this.roll(front,6,3,0,left,0,1,2,back,2,5,8,right,8,7,6,false); break;
				case 4: this.roll(front,2,5,8,left,0,1,2,back,6,3,0,right,8,7,6,false); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,false); break;
				} break;
			case 2:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,false); break;
				case 1: this.roll(front,2,5,8,left,2,5,8,back,6,3,0,right,2,5,8,false); break;
				case 3: this.roll(front,2,5,8,left,2,5,8,back,2,5,8,right,6,3,0,false); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,false); break;
				} break;
			case 3: 
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,false); break;
				case 2: this.roll(front,2,5,8,left,8,7,6,back,6,3,0,right,0,1,2,false); break;
				case 4: this.roll(front,6,3,0,left,8,7,6,back,2,5,8,right,0,1,2,false); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,false); break;
				} break;
			case 4:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,false); break;
				case 1: this.roll(front,6,3,0,left,2,5,8,back,2,5,8,right,2,5,8,false); break;
				case 3: this.roll(front,2,5,8,left,6,3,0,back,6,3,0,right,6,3,0,false); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,false); break;
				} break;
			case 5:
				switch(top) {
				case 1: this.roll(front,2,5,8,left,6,3,0,back,2,5,8,right,2,5,8,false); break;
				case 2: this.roll(front,8,7,6,left,6,3,0,back,0,1,2,right,2,5,8,false); break;
				case 3: this.roll(front,6,3,0,left,6,3,0,back,6,3,0,right,2,5,8,false); break;
				case 4: this.roll(front,0,1,2,left,6,3,0,back,8,7,6,right,2,5,8,false); break;
				}
			}
			this.transpose(down, true);
		}
		else if (command.equals("D'")) {
			// This sequence is written backwards. The argument for clockwise roll should be reversed
			//System.out.println("Turn bottom face CCW");
			switch (front) {
			case 0: 
				switch(top) {
				case 1: this.roll(front,2,5,8,left,2,5,8,back,2,5,8,right,6,3,0,true); break;
				case 2: this.roll(front,0,1,2,left,2,5,8,back,8,7,6,right,6,3,0,true); break;
				case 3: this.roll(front,6,3,0,left,2,5,8,back,6,3,0,right,6,3,0,true); break;
				case 4: this.roll(front,8,7,6,left,2,5,8,back,0,1,2,right,6,3,0,true); break;
				} break;
			case 1:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,true); break;
				case 2: this.roll(front,6,3,0,left,0,1,2,back,2,5,8,right,8,7,6,true); break;
				case 4: this.roll(front,2,5,8,left,0,1,2,back,6,3,0,right,8,7,6,true); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,true); break;
				} break;
			case 2:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,true); break;
				case 1: this.roll(front,2,5,8,left,2,5,8,back,6,3,0,right,2,5,8,true); break;
				case 3: this.roll(front,2,5,8,left,2,5,8,back,2,5,8,right,6,3,0,true); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,true); break;
				} break;
			case 3: 
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,true); break;
				case 2: this.roll(front,2,5,8,left,8,7,6,back,6,3,0,right,0,1,2,true); break;
				case 4: this.roll(front,6,3,0,left,8,7,6,back,2,5,8,right,0,1,2,true); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,true); break;
				} break;
			case 4:
				switch(top) {
				case 0: this.roll(front,8,7,6,left,8,7,6,back,8,7,6,right,8,7,6,true); break;
				case 1: this.roll(front,6,3,0,left,2,5,8,back,2,5,8,right,2,5,8,true); break;
				case 3: this.roll(front,2,5,8,left,6,3,0,back,6,3,0,right,6,3,0,true); break;
				case 5: this.roll(front,0,1,2,left,0,1,2,back,0,1,2,right,0,1,2,true); break;
				} break;
			case 5:
				switch(top) {
				case 1: this.roll(front,2,5,8,left,6,3,0,back,2,5,8,right,2,5,8,true); break;
				case 2: this.roll(front,8,7,6,left,6,3,0,back,0,1,2,right,2,5,8,true); break;
				case 3: this.roll(front,6,3,0,left,6,3,0,back,6,3,0,right,2,5,8,true); break;
				case 4: this.roll(front,0,1,2,left,6,3,0,back,8,7,6,right,2,5,8,true); break;
				}
			}
			this.transpose(down, false);
		}
		else if (command.equals("F")) {
			//System.out.println("Turn front face CW");
			switch (front) {
			case 0: this.roll(top,2,1,0,right,2,1,0,down,2,1,0,left,2,1,0,true); break;
			case 1:
				switch (top) {
				case 0: this.roll(top,0,3,6,right,0,3,6,down,0,3,6,left,8,5,2,true); break;
				case 2: this.roll(top,0,3,6,right,0,3,6,down,8,5,2,left,0,3,6,true); break;
				case 4: this.roll(top,8,5,2,right,0,3,6,down,0,3,6,left,0,3,6,true); break;
				case 5: this.roll(top,0,3,6,right,8,5,2,down,0,3,6,left,0,3,6,true); break;
				} break;
			case 2: 
				switch (top) {
				case 0: this.roll(top,6,7,8,right,0,3,6,down,2,1,0,left,8,5,2,true); break;
				case 1: this.roll(top,8,5,2,right,6,7,8,down,0,3,6,left,2,1,0,true); break;
				case 3: this.roll(top,0,3,6,right,2,1,0,down,8,5,2,left,6,7,8,true); break;
				case 5: this.roll(top,2,1,0,right,8,5,2,down,6,7,8,left,0,3,6,true); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,8,5,2,right,0,3,6,down,8,5,2,left,8,5,2,true); break;
				case 2: this.roll(top,8,5,2,right,8,5,2,down,0,3,6,left,8,5,2,true); break;
				case 4: this.roll(top,0,3,6,right,8,5,2,down,8,5,2,left,8,5,2,true); break;
				case 5: this.roll(top,8,5,2,right,8,5,2,down,8,5,2,left,0,3,6,true); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,2,1,0,right,0,3,6,down,6,7,8,left,8,5,2,true); break;
				case 1: this.roll(top,0,3,6,right,6,7,8,down,8,5,2,left,2,1,0,true); break;
				case 3: this.roll(top,8,5,2,right,2,1,0,down,0,3,6,left,6,7,8,true); break;
				case 5: this.roll(top,6,7,8,right,8,5,2,down,2,1,0,left,0,3,6,true); break;
				} break;
			case 5: this.roll(top,6,7,8,right,6,7,8,down,6,7,8,left,6,7,8,true); break;
			}
			
			this.transpose(front, true);
		}
		else if (command.equals("F'")) {
			//System.out.println("Turn front face CCW");
			switch (front) {
			case 0: this.roll(top,2,1,0,right,2,1,0,down,2,1,0,left,2,1,0,false); break;
			case 1:
				switch (top) {
				case 0: this.roll(top,0,3,6,right,0,3,6,down,0,3,6,left,8,5,2,false); break;
				case 2: this.roll(top,0,3,6,right,0,3,6,down,8,5,2,left,0,3,6,false); break;
				case 4: this.roll(top,8,5,2,right,0,3,6,down,0,3,6,left,0,3,6,false); break;
				case 5: this.roll(top,0,3,6,right,8,5,2,down,0,3,6,left,0,3,6,false); break;
				} break;
			case 2: 
				switch (top) {
				case 0: this.roll(top,6,7,8,right,0,3,6,down,2,1,0,left,8,5,2,false); break;
				case 1: this.roll(top,8,5,2,right,6,7,8,down,0,3,6,left,2,1,0,false); break;
				case 3: this.roll(top,0,3,6,right,2,1,0,down,8,5,2,left,6,7,8,false); break;
				case 5: this.roll(top,2,1,0,right,8,5,2,down,6,7,8,left,0,3,6,false); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,8,5,2,right,0,3,6,down,8,5,2,left,8,5,2,false); break;
				case 2: this.roll(top,8,5,2,right,8,5,2,down,0,3,6,left,8,5,2,false); break;
				case 4: this.roll(top,0,3,6,right,8,5,2,down,8,5,2,left,8,5,2,false); break;
				case 5: this.roll(top,8,5,2,right,8,5,2,down,8,5,2,left,0,3,6,false); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,2,1,0,right,0,3,6,down,6,7,8,left,8,5,2,false); break;
				case 1: this.roll(top,0,3,6,right,6,7,8,down,8,5,2,left,2,1,0,false); break;
				case 3: this.roll(top,8,5,2,right,2,1,0,down,0,3,6,left,6,7,8,false); break;
				case 5: this.roll(top,6,7,8,right,8,5,2,down,2,1,0,left,0,3,6,false); break;
				} break;
			case 5: this.roll(top,6,7,8,right,6,7,8,down,6,7,8,left,6,7,8,false); break;
			}
			
			this.transpose(front, false);
		}
		else if (command.equals("B")) {
			//System.out.println("Turn back face CW");
			switch (front) {
			case 0: this.roll(top,6,7,8,left,6,7,8,down,6,7,8,right,6,7,8,true); break;
			case 1: 
				switch (top) {
				case 0: this.roll(top,8,5,2,left,0,3,6,down,8,5,2,right,8,5,2,true); break;
				case 2: this.roll(top,8,5,2,left,8,5,2,down,0,3,6,right,8,5,2,true); break;
				case 4: this.roll(top,0,3,6,left,8,5,2,down,8,5,2,right,8,5,2,true); break;
				case 5: this.roll(top,8,5,2,left,0,3,6,down,8,5,2,right,8,5,2,true); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,2,1,0,left,0,3,6,down,6,7,8,right,8,5,2,true); break;
				case 1: this.roll(top,0,3,6,left,6,7,8,down,8,5,2,right,2,1,0,true); break;
				case 3: this.roll(top,8,5,2,left,2,1,0,down,0,3,6,right,6,7,8,true); break;
				case 5: this.roll(top,6,7,8,left,8,5,2,down,2,1,0,right,0,3,6,true); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,0,3,6,left,0,3,6,down,0,3,6,right,8,5,2,true); break;
				case 2: this.roll(top,0,3,6,left,0,3,6,down,8,5,2,right,0,3,6,true); break;
				case 4: this.roll(top,8,5,2,left,0,3,6,down,0,3,6,right,0,3,6,true); break;
				case 5: this.roll(top,0,3,6,left,8,5,2,down,0,3,6,right,0,3,6,true); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,6,7,8,left,0,3,6,down,2,1,0,right,8,5,2,true); break;
				case 1: this.roll(top,8,5,2,left,6,7,8,down,0,3,6,right,2,1,0,true); break;
				case 3: this.roll(top,0,3,6,left,2,1,0,down,8,5,2,right,6,7,8,true); break;
				case 5: this.roll(top,2,1,0,left,8,5,2,down,6,7,8,right,0,3,6,true); break;
				} break;
			case 5: this.roll(top,2,1,0,left,2,1,0,down,2,1,0,right,2,1,0,true); break;
			}
			this.transpose(back, true);
		}
		else if (command.equals("B'")) {
			//System.out.println("Turn back face CCW");
			switch (front) {
			case 0: this.roll(top,6,7,8,left,6,7,8,down,6,7,8,right,6,7,8,false); break;
			case 1: 
				switch (top) {
				case 0: this.roll(top,8,5,2,left,0,3,6,down,8,5,2,right,8,5,2,false); break;
				case 2: this.roll(top,8,5,2,left,8,5,2,down,0,3,6,right,8,5,2,false); break;
				case 4: this.roll(top,0,3,6,left,8,5,2,down,8,5,2,right,8,5,2,false); break;
				case 5: this.roll(top,8,5,2,left,0,3,6,down,8,5,2,right,8,5,2,false); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,2,1,0,left,0,3,6,down,6,7,8,right,8,5,2,false); break;
				case 1: this.roll(top,0,3,6,left,6,7,8,down,8,5,2,right,2,1,0,false); break;
				case 3: this.roll(top,8,5,2,left,2,1,0,down,0,3,6,right,6,7,8,false); break;
				case 5: this.roll(top,6,7,8,left,8,5,2,down,2,1,0,right,0,3,6,false); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,0,3,6,left,0,3,6,down,0,3,6,right,8,5,2,false); break;
				case 2: this.roll(top,0,3,6,left,0,3,6,down,8,5,2,right,0,3,6,false); break;
				case 4: this.roll(top,8,5,2,left,0,3,6,down,0,3,6,right,0,3,6,false); break;
				case 5: this.roll(top,0,3,6,left,8,5,2,down,0,3,6,right,0,3,6,false); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,6,7,8,left,0,3,6,down,2,1,0,right,8,5,2,false); break;
				case 1: this.roll(top,8,5,2,left,6,7,8,down,0,3,6,right,2,1,0,false); break;
				case 3: this.roll(top,0,3,6,left,2,1,0,down,8,5,2,right,6,7,8,false); break;
				case 5: this.roll(top,2,1,0,left,8,5,2,down,6,7,8,right,0,3,6,false); break;
				} break;
			case 5: this.roll(top,2,1,0,left,2,1,0,down,2,1,0,right,2,1,0,false); break;
			}
			this.transpose(back, false);
		}
		else if (command.equals("R")) {
			//System.out.println("Turn right face CW");
			switch (front) {
			case 0:
				switch (top) {
				case 1: this.roll(top,0,3,6,back,6,7,8,down,8,5,2,front,2,1,0,true); break;
				case 2: this.roll(top,0,3,6,back,0,3,6,down,8,5,2,front,0,3,6,true); break;
				case 3: this.roll(top,0,3,6,back,2,1,0,down,8,5,2,front,6,7,8,true); break;
				case 4: this.roll(top,0,3,6,back,8,5,2,down,8,5,2,front,8,5,2,true); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(top,6,7,8,back,0,3,6,down,2,1,0,front,8,5,2,true); break;
				case 2: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,true); break;
				case 4: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,true); break;
				case 5: this.roll(top,6,7,8,back,8,5,2,down,2,1,0,front,0,3,6,true); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,8,5,2,back,0,3,6,down,8,5,2,front,8,5,2,true); break;
				case 1: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,true); break;
				case 3: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,true); break;
				case 5: this.roll(top,0,3,6,back,8,5,2,down,0,3,6,front,0,3,6,true); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,2,1,0,back,0,3,6,down,6,7,8,front,8,5,2,true); break;
				case 2: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,true); break;
				case 4: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,true); break;
				case 5: this.roll(top,2,1,0,back,8,5,2,down,6,7,8,front,0,3,6,true); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,0,3,6,back,0,3,6,down,0,3,6,front,8,5,2,true); break;
				case 1: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,true); break;
				case 3: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,true); break;
				case 5: this.roll(top,8,5,2,back,8,5,2,down,8,5,2,front,0,3,6,true); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(top,8,5,2,back,6,7,8,down,0,3,6,front,2,1,0,true); break;
				case 2: this.roll(top,8,5,2,back,8,5,2,down,0,3,6,front,8,5,2,true); break;
				case 3: this.roll(top,8,5,2,back,2,1,0,down,0,3,6,front,6,7,8,true); break;
				case 4: this.roll(top,8,5,2,back,0,3,6,down,0,3,6,front,0,3,6,true); break;
				} break;
			}
			this.transpose(right, true);
		}
		else if (command.equals("R'")) {
			//System.out.println("Turn right face CCW");
			switch (front) {
			case 0:
				switch (top) {
				case 1: this.roll(top,0,3,6,back,6,7,8,down,8,5,2,front,2,1,0,false); break;
				case 2: this.roll(top,0,3,6,back,0,3,6,down,8,5,2,front,0,3,6,false); break;
				case 3: this.roll(top,0,3,6,back,2,1,0,down,8,5,2,front,6,7,8,false); break;
				case 4: this.roll(top,0,3,6,back,8,5,2,down,8,5,2,front,8,5,2,false); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(top,6,7,8,back,0,3,6,down,2,1,0,front,8,5,2,false); break;
				case 2: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,false); break;
				case 4: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,false); break;
				case 5: this.roll(top,6,7,8,back,8,5,2,down,2,1,0,front,0,3,6,false); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,8,5,2,back,0,3,6,down,8,5,2,front,8,5,2,false); break;
				case 1: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,false); break;
				case 3: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,false); break;
				case 5: this.roll(top,0,3,6,back,8,5,2,down,0,3,6,front,0,3,6,false); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,2,1,0,back,0,3,6,down,6,7,8,front,8,5,2,false); break;
				case 2: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,false); break;
				case 4: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,false); break;
				case 5: this.roll(top,2,1,0,back,8,5,2,down,6,7,8,front,0,3,6,false); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,0,3,6,back,0,3,6,down,0,3,6,front,8,5,2,false); break;
				case 1: this.roll(top,6,7,8,back,6,7,8,down,6,7,8,front,6,7,8,false); break;
				case 3: this.roll(top,2,1,0,back,2,1,0,down,2,1,0,front,2,1,0,false); break;
				case 5: this.roll(top,8,5,2,back,8,5,2,down,8,5,2,front,0,3,6,false); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(top,8,5,2,back,6,7,8,down,0,3,6,front,2,1,0,false); break;
				case 2: this.roll(top,8,5,2,back,8,5,2,down,0,3,6,front,8,5,2,false); break;
				case 3: this.roll(top,8,5,2,back,2,1,0,down,0,3,6,front,6,7,8,false); break;
				case 4: this.roll(top,8,5,2,back,0,3,6,down,0,3,6,front,0,3,6,false); break;
				} break;
			}
			this.transpose(right, false);
		}
		else if (command.equals("L")) {
			// This action is also written backwards.
			//System.out.println("Turn left face CW");
			switch (front) {
			case 0:
				switch (top) {
				case 1: this.roll(top,2,5,8,back,0,1,2,down,6,3,0,front,8,7,6,false); break;
				case 2: this.roll(top,2,5,8,back,2,5,8,down,6,3,0,front,2,5,8,false); break;
				case 3: this.roll(top,2,5,8,back,8,7,6,down,6,3,0,front,0,1,2,false); break;
				case 4: this.roll(top,2,5,8,back,6,3,0,down,6,3,0,front,6,3,0,false); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(top,0,1,2,back,2,5,8,down,8,7,6,front,6,3,0,false); break;
				case 2: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,false); break;
				case 4: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,false); break;
				case 5: this.roll(top,0,1,2,back,6,3,0,down,8,7,6,front,2,5,8,false); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,6,3,0,back,2,5,8,down,6,3,0,front,6,3,0,false); break;
				case 1: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,false); break;
				case 3: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,false); break;
				case 5: this.roll(top,2,5,8,back,6,3,0,down,2,5,8,front,2,5,8,false); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,8,7,6,back,2,5,8,down,0,1,2,front,6,3,0,false); break;
				case 2: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,false); break;
				case 4: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,false); break;
				case 5: this.roll(top,8,7,6,back,6,3,0,down,0,1,2,front,2,5,8,false); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,2,5,8,back,2,5,8,down,2,5,8,front,6,3,0,false); break;
				case 1: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,false); break;
				case 3: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,false); break;
				case 5: this.roll(top,6,3,0,back,6,3,0,down,6,3,0,front,2,5,8,false); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(top,6,3,0,back,0,1,2,down,2,5,8,front,8,7,6,false); break;
				case 2: this.roll(top,6,3,0,back,6,3,0,down,2,5,8,front,6,3,0,false); break;
				case 3: this.roll(top,6,3,0,back,8,7,6,down,2,5,8,front,0,1,2,false); break;
				case 4: this.roll(top,6,3,0,back,2,5,8,down,2,5,8,front,2,5,8,false); break;
				} break;
			}
			this.transpose(left, true);
		}
		else if (command.equals("L'")) {
			//System.out.println("Turn left face CCW");
			switch (front) {
			case 0:
				switch (top) {
				case 1: this.roll(top,2,5,8,back,0,1,2,down,6,3,0,front,8,7,6,true); break;
				case 2: this.roll(top,2,5,8,back,2,5,8,down,6,3,0,front,2,5,8,true); break;
				case 3: this.roll(top,2,5,8,back,8,7,6,down,6,3,0,front,0,1,2,true); break;
				case 4: this.roll(top,2,5,8,back,6,3,0,down,6,3,0,front,6,3,0,true); break;
				} break;
			case 1:
				switch (top) {
				case 0: this.roll(top,0,1,2,back,2,5,8,down,8,7,6,front,6,3,0,true); break;
				case 2: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,true); break;
				case 4: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,true); break;
				case 5: this.roll(top,0,1,2,back,6,3,0,down,8,7,6,front,2,5,8,true); break;
				} break;
			case 2:
				switch (top) {
				case 0: this.roll(top,6,3,0,back,2,5,8,down,6,3,0,front,6,3,0,true); break;
				case 1: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,true); break;
				case 3: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,true); break;
				case 5: this.roll(top,2,5,8,back,6,3,0,down,2,5,8,front,2,5,8,true); break;
				} break;
			case 3:
				switch (top) {
				case 0: this.roll(top,8,7,6,back,2,5,8,down,0,1,2,front,6,3,0,true); break;
				case 2: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,true); break;
				case 4: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,true); break;
				case 5: this.roll(top,8,7,6,back,6,3,0,down,0,1,2,front,2,5,8,true); break;
				} break;
			case 4:
				switch (top) {
				case 0: this.roll(top,2,5,8,back,2,5,8,down,2,5,8,front,6,3,0,true); break;
				case 1: this.roll(top,0,1,2,back,0,1,2,down,0,1,2,front,0,1,2,true); break;
				case 3: this.roll(top,8,7,6,back,8,7,6,down,8,7,6,front,8,7,6,true); break;
				case 5: this.roll(top,6,3,0,back,6,3,0,down,6,3,0,front,2,5,8,true); break;
				} break;
			case 5:
				switch (top) {
				case 1: this.roll(top,6,3,0,back,0,1,2,down,2,5,8,front,8,7,6,true); break;
				case 2: this.roll(top,6,3,0,back,6,3,0,down,2,5,8,front,6,3,0,true); break;
				case 3: this.roll(top,6,3,0,back,8,7,6,down,2,5,8,front,0,1,2,true); break;
				case 4: this.roll(top,6,3,0,back,2,5,8,down,2,5,8,front,2,5,8,true); break;
				} break;
			}
			this.transpose(left, false);
		}
		
		else if (command.equals("RESET")) {
			this.reset();
		}
		else if (command.equals("OUTPUT")) {
			this.display(false);
		}
		/*
		 * Unrecognized command
		 */
		else {
			System.out.println("Unrecognized command");
		}
	}
}
