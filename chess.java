import java.util.*;
import java.lang.Math;

class chess {
	public static String arr[][] = { { "BR1", "BK1", "BB1", "BQn", "BLn", "BB2", "BK2", "BR2" },
			{ "BP1", "BP2", "BP3", "BP4", "BP5", "BP6", "BP7", "BP8" },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "   ", "   ", "   ", "   ", "   ", "   ", "   ", "   " },
			{ "WP1", "WP2", "WP3", "WP4", "WP5", "WP6", "WP7", "WP8" },
			{ "WR1", "WK1", "WB1", "WQn", "WLn", "WB2", "WK2", "WR2" } };
	public static int pawn_first_step[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int pawn_first_step1[] = { 0, 0, 0, 0, 0, 0, 0, 0 };
	public static int chance = 0;
	public static int caseline_W = 0;
	public static int caseline_B = 0;
	public static int count_queen_w = 1;
	public static int count_bishop_w = 2;
	public static int count_knight_w = 2;
	public static int count_rook_w = 2;
	public static int count_queen_b = 1;
	public static int count_bishop_b = 2;
	public static int count_knight_b = 2;
	public static int count_rook_b = 2;
	public static int check = 0;
	public static int check1 = 0;
	public static int read = 0;
	public static int illegal_move_W = 0;
	public static int illegal_move_B = 0;
	public static String p1 = "";
	public static String p2 = "";

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print(
				"Kon'nichiwa(Hello)\nWelcome to the game of chess....\nEnter :\n1.Basic Instructions for the game\n2.Start Game\nInput: ");
		int select = sc.nextInt();
		if (select == 1)
			details();
		System.out.print("\nEnter Name of 1st Player : ");
		sc.nextLine();
		p1 = sc.nextLine();
		System.out.print("Enter Name of 2nd Player : ");
		p2 = sc.nextLine();
		System.out.println("\n" + p1 + " is alocated White army and " + p2
				+ " has been alocated Black Army \nHereby Game of chess starts:\n");
		while (true) {
			check = 0;
			read = 0;
			if ((chance % 2) == 0) {
				System.out.println(p1 + "'s Turn to go:\n");
				System.out.println("Invalid moves left : " + (3 - illegal_move_W));
				check_W(0);
				check = check_W(1);
				display();
				if (check >= 2) {
					System.out.println("Save your King before it gets killed!!!");
					king("WLn");
					caseline_W = 1;
				} else
					player1();
				victory();
				System.out.println("----------------------------------------------------------------------");
			} else {
				System.out.println(p2 + "'s Turn to go:\n");
				System.out.println("Invalid moves left : " + (3 - illegal_move_B));
				check_B(0);
				check = check_B(1);
				display();
				if (check >= 2) {
					System.out.println("Save your King before it gets killed!!!");
					king("BLn");
					caseline_B = 1;
				} else
					player2();
				victory();
				System.out.println("----------------------------------------------------------------------");
			}
			chance++;
		}
	}

	public static void display() {
		System.out.println("      |--------------------    X    ------------------|");
		System.out.println("         a     b     c     d     e     f     g     h  ");
		System.out.println("---    ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println(" |  1 | " + arr[0][0] + " | " + arr[0][1] + " | " + arr[0][2] + " | " + arr[0][3] + " | "
				+ arr[0][4] + " | " + arr[0][5] + " | " + arr[0][6] + " | " + arr[0][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println(" |  2 | " + arr[1][0] + " | " + arr[1][1] + " | " + arr[1][2] + " | " + arr[1][3] + " | "
				+ arr[1][4] + " | " + arr[1][5] + " | " + arr[1][6] + " | " + arr[1][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println(" |  3 | " + arr[2][0] + " | " + arr[2][1] + " | " + arr[2][2] + " | " + arr[2][3] + " | "
				+ arr[2][4] + " | " + arr[2][5] + " | " + arr[2][6] + " | " + arr[2][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println("    4 | " + arr[3][0] + " | " + arr[3][1] + " | " + arr[3][2] + " | " + arr[3][3] + " | "
				+ arr[3][4] + " | " + arr[3][5] + " | " + arr[3][6] + " | " + arr[3][7] + " |");
		System.out.println(" Y     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println("    5 | " + arr[4][0] + " | " + arr[4][1] + " | " + arr[4][2] + " | " + arr[4][3] + " | "
				+ arr[4][4] + " | " + arr[4][5] + " | " + arr[4][6] + " | " + arr[4][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println(" |  6 | " + arr[5][0] + " | " + arr[5][1] + " | " + arr[5][2] + " | " + arr[5][3] + " | "
				+ arr[5][4] + " | " + arr[5][5] + " | " + arr[5][6] + " | " + arr[5][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----'");
		System.out.println(" |  7 | " + arr[6][0] + " | " + arr[6][1] + " | " + arr[6][2] + " | " + arr[6][3] + " | "
				+ arr[6][4] + " | " + arr[6][5] + " | " + arr[6][6] + " | " + arr[6][7] + " |");
		System.out.println(" |     ----- ----- ----- ----- ----- ----- ----- -----");
		System.out.println(" |  8 | " + arr[7][0] + " | " + arr[7][1] + " | " + arr[7][2] + " | " + arr[7][3] + " | "
				+ arr[7][4] + " | " + arr[7][5] + " | " + arr[7][6] + " | " + arr[7][7] + " |");
		System.out.println("---    ----- ----- ----- ----- ----- ----- ----- -----");
	}

	public static void player1() {
		victory();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a cookie :");
		String Char = sc.nextLine();
		if ((!Char.substring(0, 1).equals("W")) || (Char.length() != 3)) {
			System.out.println("Invalid input");
			player1();
		} else {
			switch (Char.substring(1, 2)) {
				case "P":
					pawn_p1(Char);
					break;
				case "R":
					rook(Char);
					caseline_W = 1;
					break;
				case "K":
					knight(Char);
					break;
				case "B":
					bishop(Char);
					break;
				case "Q":
					queen(Char);
					break;
				case "L":
					king(Char);
					caseline_W = 1;
					break;
				default:
					System.out.println("There may be a mistake in writing input, try again.....!!!!");
					player1();
			}
		}
	}

	public static void player2() {
		victory();
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a cookie :");
		String Char = sc.nextLine();
		if (!Char.substring(0, 1).equals("B") || Char.length() != 3) {
			System.out.println("Invalid input");
			player2();
		} else {
			switch (Char.substring(1, 2)) {
				case "P":
					pawn_p2(Char);
					break;
				case "R":
					rook(Char);
					caseline_B = 1;
					break;
				case "K":
					knight(Char);
					break;
				case "B":
					bishop(Char);
					break;
				case "Q":
					queen(Char);
					break;
				case "L":
					king(Char);
					caseline_B = 1;
					break;
				default:
					System.out.println("There may be a mistake in writing input, try again.....!!!!");
					player2();
			}
		}
	}

	public static void pawn_p1(String str) {
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		int no = Integer.parseInt(str.substring(2));
		int kill = 2;
		String cokie = "   ";
		int door = 2, CNT = 0;
		if ((pos[1] != 0) && !arr[pos[0] - 1][pos[1] - 1].equals("   ")
				&& arr[pos[0] - 1][pos[1] - 1].substring(0, 1).equals("B")
				&& ((pos[1] != 7) && !arr[pos[0] - 1][pos[1] + 1].equals("   ")
						&& (arr[pos[0] - 1][pos[1] + 1].substring(0, 1).equals("B")))) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] - 1][pos[1] - 1] + "\n2.To kill "
					+ arr[pos[0] - 1][pos[1] + 1] + "\n3.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] - 1][pos[1] - 1];
				arr[pos[0] - 1][pos[1] - 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			} else if (kill == 2) {
				cokie = arr[pos[0] - 1][pos[1] + 1];
				arr[pos[0] - 1][pos[1] + 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			} else if (kill == 3) {
				arr[pos[0] - 1][pos[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
			door = kill;
			kill = 1;
		} else if ((pos[1] != 0) && !arr[pos[0] - 1][pos[1] - 1].equals("   ")
				&& arr[pos[0] - 1][pos[1] - 1].substring(0, 1).equals("B")) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] - 1][pos[1] - 1] + "\n2.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] - 1][pos[1] - 1];
				arr[pos[0] - 1][pos[1] - 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		} else if ((pos[1] != 7) && !arr[pos[0] - 1][pos[1] + 1].equals("   ")
				&& (arr[pos[0] - 1][pos[1] + 1].substring(0, 1).equals("B"))) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] - 1][pos[1] + 1] + "\n2.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] - 1][pos[1] + 1];
				arr[pos[0] - 1][pos[1] + 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		}
		if (pawn_first_step[no - 1] == 0 && kill == 2) {
			System.out.printf("Enter:\n1 for moving 1 step forward\n2 for moving 2 steps forward\nInput:	");
			int steps = sc.nextInt();
			if (steps == 2) {
				if (!arr[pos[0] - 2][pos[1]].equals("   ")) {
					System.out.println("Place already occupied, try some other move:");
					illegal_move_W++;
					player1();
				} else {
					arr[pos[0] - 2][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
					pawn_first_step[no - 1] = 1;
				}
				CNT++;
			} else if (steps == 1) {
				if (!arr[pos[0] - 1][pos[1]].equals("   ")) {
					System.out.println("Place already occupied, try some other move:");
					illegal_move_W++;
					player1();
				} else {
					arr[pos[0] - 1][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
					pawn_first_step[no - 1] = 1;
				}
				CNT++;
			} else {
				System.out.println("Enter valid move:");
				illegal_move_W++;
				pawn_p1(str);
			}
		} else if (pawn_first_step[no - 1] == 1 && kill == 2) {
			if (!arr[pos[0] - 1][pos[1]].equals("   ")) {
				System.out.println("Place already occupied, try some other move:");
				illegal_move_W++;
				player1();
			} else {
				arr[pos[0] - 1][pos[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		} else if (kill != 1 && kill != 2) {
			System.out.println("Kill or not?? You Kept me in confusion...!!!\nTry again: ");
			illegal_move_W++;
			player1();
		}
		int pos1[] = search(str);
		back_move_pawn(pos, cokie, kill, str, door, CNT);
		if (pos1[0] == 0)
			pawn_swap(pos1, str);
	}

	public static void pawn_p2(String str) {
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		String cokie = "   ";
		int no = Integer.parseInt(str.substring(2));
		int kill = 2, CNT = 0;
		int door = 2;
		if ((pos[1] != 7) && !arr[pos[0] + 1][pos[1] + 1].equals("   ")
				&& arr[pos[0] + 1][pos[1] + 1].substring(0, 1).equals("W")
				&& ((pos[1] != 0) && !arr[pos[0] + 1][pos[1] - 1].equals("   ")
						&& (arr[pos[0] + 1][pos[1] - 1].substring(0, 1).equals("W")))) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] + 1][pos[1] + 1] + "\n2.To kill "
					+ arr[pos[0] + 1][pos[1] - 1] + "\n3.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] + 1][pos[1] + 1];
				arr[pos[0] + 1][pos[1] + 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			} else if (kill == 2) {
				cokie = arr[pos[0] + 1][pos[1] - 1];
				arr[pos[0] + 1][pos[1] - 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			} else if (kill == 3) {
				arr[pos[0] + 1][pos[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
			door = kill;
			kill = 1;
		} else if ((pos[1] != 7) && !arr[pos[0] + 1][pos[1] + 1].equals("   ")
				&& arr[pos[0] + 1][pos[1] + 1].substring(0, 1).equals("W")) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] + 1][pos[1] + 1] + "\n2.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] + 1][pos[1] + 1];
				arr[pos[0] + 1][pos[1] + 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		} else if ((pos[1] != 0) && !arr[pos[0] + 1][pos[1] - 1].equals("   ")
				&& (arr[pos[0] + 1][pos[1] - 1].substring(0, 1).equals("W"))) {
			System.out.print("Enter :\n1.To kill " + arr[pos[0] + 1][pos[1] - 1] + "\n2.Move forward\nInput: ");
			kill = sc.nextInt();
			if (kill == 1) {
				cokie = arr[pos[0] + 1][pos[1] - 1];
				arr[pos[0] + 1][pos[1] - 1] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		}
		if (pawn_first_step1[no - 1] == 0 && kill == 2) {
			System.out.printf("Enter:\n1 for moving 1 step forward\n2 to move 2 steps forward\nInput:	");
			int steps = sc.nextInt();
			if (steps == 2) {
				if (!arr[pos[0] + 2][pos[1]].equals("   ")) {
					System.out.println("Place already occupied, try some other move:");
					illegal_move_B++;
					player2();
				} else {
					arr[pos[0] + 2][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
					pawn_first_step1[no - 1] = 1;
				}
				CNT++;
			} else if (steps == 1) {
				if (!arr[pos[0] + 1][pos[1]].equals("   ")) {
					System.out.println("Place already occupied, try some other move:");
					illegal_move_B++;
					player2();
				} else {
					arr[pos[0] + 1][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
					pawn_first_step1[no - 1] = 1;
				}
			} else {
				System.out.println("Enter valid move:");
				illegal_move_B++;
				pawn_p2(str);
			}
		} else if (pawn_first_step1[no - 1] == 1 && kill == 2) {
			if (!arr[pos[0] + 1][pos[1]].equals("   ")) {
				System.out.println("Place already occupied, try some other move:");
				illegal_move_B++;
				player2();
			} else {
				arr[pos[0] + 1][pos[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		} else if (kill != 1 && kill != 2) {
			if (!arr[pos[0] + 1][pos[1]].equals("   ")) {
				System.out.println("Place already occupied, try some other move:");
				illegal_move_B++;
				player2();
			} else {
				arr[pos[0] + 1][pos[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
			}
		}
		int pos1[] = search(str);
		back_move_pawn(pos, cokie, kill, str, door, CNT);
		if (pos1[0] == 7)
			pawn_swap(pos1, str);
	}

	public static void king(String str) {
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		String ele = "   ";
		System.out.print(
				"Enter your move: \n1. left\n2. right\n3. up\n4. down\n5. upleft\n6. upright\n7. downleft\n8. downright\nInput:	");
		int move = sc.nextInt();
		switch (move) {
			case 1:
				if ((pos[1] == 0) || (arr[pos[0]][pos[1] - 1].substring(0, 1)).equals(str.substring(0, 1))) // "left"
				{
					System.out.println("Try some other way out : ");
					illegal_move_W++;
					player1();
				} else {
					ele = arr[pos[0]][pos[1] - 1];
					arr[pos[0]][pos[1] - 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 2:
				if ((pos[1] == 7) || (arr[pos[0]][pos[1] + 1].substring(0, 1)).equals(str.substring(0, 1))) // "right"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0]][pos[1] + 1];
					arr[pos[0]][pos[1] + 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 3:
				if ((pos[0] == 0) || (arr[pos[0] - 1][pos[1]].substring(0, 1)).equals(str.substring(0, 1))) // "up"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] - 1][pos[1]];
					arr[pos[0] - 1][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 4:
				if ((pos[0] == 7) || (arr[pos[0] + 1][pos[1]].substring(0, 1)).equals(str.substring(0, 1))) // "down"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] + 1][pos[1]];
					arr[pos[0] + 1][pos[1]] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 5:
				if ((pos[1] == 0) || (pos[0] == 0)
						|| (arr[pos[0] - 1][pos[1] - 1].substring(0, 1)).equals(str.substring(0, 1))) // "upleft"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] - 1][pos[1] - 1];
					arr[pos[0] - 1][pos[1] - 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 6:
				if ((pos[0] == 0) || (pos[1] == 7)
						|| (arr[pos[0] - 1][pos[1] + 1].substring(0, 1)).equals(str.substring(0, 1))) // "upright"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] - 1][pos[1] + 1];
					arr[pos[0] - 1][pos[1] + 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 7:
				if ((pos[0] == 7) || (pos[1] == 0)
						|| (arr[pos[0] + 1][pos[1] - 1].substring(0, 1)).equals(str.substring(0, 1))) // "downleft"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] + 1][pos[1] - 1];
					arr[pos[0] + 1][pos[1] - 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 8:
				if ((pos[0] == 7) || (pos[1] == 7)
						|| (arr[pos[0] + 1][pos[1] + 1].substring(0, 1)).equals(str.substring(0, 1))) // "downright"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					ele = arr[pos[0] + 1][pos[1] + 1];
					arr[pos[0] + 1][pos[1] + 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			default:
				System.out.println("Enter valid input:");
				if (str.substring(0, 1).equals("W")) {
					illegal_move_W++;
					player1();
				} else {
					illegal_move_B++;
					player2();
				}
		}
		back_move_king(ele, move, pos, str);
	}

	public static void knight(String str) {
		Scanner sc = new Scanner(System.in);
		int init[] = new int[2];
		int pos[] = search(str);
		String ele = "   ";
		System.out.print(
				"Enter your move:\n1. up-up-left\n2. up-up-right\n3. down-down-left\n4. down-down-right\n5. left-left-up\n6. left-left-down\n7. right-right-up\n8. right-right-down\nInput:	");
		int move = sc.nextInt();
		switch (move) {
			case 1:
				if ((pos[0] < 2) || (pos[1] == 0)
						|| (arr[pos[0] - 2][pos[1] - 1].substring(0, 1)).equals(str.substring(0, 1))) // "upupleft"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] - 2;
					init[1] = pos[1] - 1;
					ele = arr[init[0]][init[1]];
					arr[pos[0] - 2][pos[1] - 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 2:
				if ((pos[0] < 2) || (pos[1] == 7)
						|| (arr[pos[0] - 2][pos[1] + 1].substring(0, 1)).equals(str.substring(0, 1))) // "upupright"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] - 2;
					init[1] = pos[1] + 1;
					ele = arr[init[0]][init[1]];
					arr[pos[0] - 2][pos[1] + 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 3:
				if ((pos[0] > 5) || (pos[1] == 0)
						|| (arr[pos[0] + 2][pos[1] - 1].substring(0, 1)).equals(str.substring(0, 1))) // "downdownleft"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] + 2;
					init[1] = pos[1] - 1;
					ele = arr[init[0]][init[1]];
					arr[pos[0] + 2][pos[1] - 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 4:
				if ((pos[0] > 5) || (pos[1] == 7)
						|| (arr[pos[0] + 2][pos[1] + 1].substring(0, 1)).equals(str.substring(0, 1))) // "downdownright"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] + 2;
					init[1] = pos[1] + 1;
					ele = arr[init[0]][init[1]];
					arr[pos[0] + 2][pos[1] + 1] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 5:
				if ((pos[0] == 0) || (pos[1] > 5)
						|| (arr[pos[0] - 1][pos[1] - 2].substring(0, 1)).equals(str.substring(0, 1))) // "leftleftup"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] - 1;
					init[1] = pos[1] - 2;
					ele = arr[init[0]][init[1]];
					arr[pos[0] - 1][pos[1] - 2] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 6:
				if ((pos[0] == 7) || (pos[1] > 5)
						|| (arr[pos[0] + 1][pos[1] - 2].substring(0, 1)).equals(str.substring(0, 1))) // "leftleftdown"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] - 2;
					init[1] = pos[1] + 1;
					ele = arr[init[0]][init[1]];
					arr[pos[0] + 1][pos[1] - 2] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 7:
				if ((pos[0] == 0) || (pos[1] > 5)
						|| (arr[pos[0] - 1][pos[1] + 2].substring(0, 1)).equals(str.substring(0, 1))) // "rightrightup"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] - 1;
					init[1] = pos[1] + 2;
					ele = arr[init[0]][init[1]];
					arr[pos[0] - 1][pos[1] + 2] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			case 8:
				if ((pos[0] == 7) || (pos[1] > 5)
						|| (arr[pos[0] + 1][pos[1] + 2].substring(0, 1)).equals(str.substring(0, 1))) // "rightrightdown"
				{
					System.out.println("Try some other way out : ");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else {
					init[0] = pos[0] + 1;
					init[1] = pos[1] + 2;
					ele = arr[init[0]][init[1]];
					arr[pos[0] + 1][pos[1] + 2] = str;
					arr[pos[0]][pos[1]] = "   ";
				}
				break;
			default:
				System.out.println("Enter valid input:");
				if (str.substring(0, 1).equals("W")) {
					illegal_move_W++;
					player1();
				} else {
					illegal_move_B++;
					player2();
				}
		}
		read++;
		if (read == 1)
			back_move_knight(ele, init, pos, str);
	}

	public static void rook(String str) {
		String inp[] = new String[2];
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		System.out.print("Enter the destination coordinates:\n1.Y coordinate :	");
		inp[0] = sc.nextLine();
		System.out.print("2.X coordinate :	");
		inp[1] = sc.nextLine();
		if ((Integer.parseInt(inp[0]) > 0 && Integer.parseInt(inp[0]) > 8)) {
			System.out.println("Enter valid Input : ");
			rook(str);
		} else if (pos[0] == -1) {
			System.out.println("Sorry, I could'nt find it.....!!");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		} else {
			int end[] = pos_convert(inp, str);
			move_for_rook(pos, end, str);
		}
	}

	public static void bishop(String str) {
		String inp[] = new String[2];
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		System.out.print("Enter the destination coordinates:\n1.Y coordinate :	");
		inp[0] = sc.nextLine();
		System.out.print("2.X coordinate :	");
		inp[1] = sc.nextLine();
		if ((Integer.parseInt(inp[0]) > 0 && Integer.parseInt(inp[0]) > 8)) {
			System.out.println("Enter valid coordinates : ");
			bishop(str);
		} else if (pos[0] == -1) {
			System.out.println("It seems Bishop ran out of the board.....!!");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		} else {
			int end[] = pos_convert(inp, str);
			move_for_bishop(pos, end, str);
		}
	}

	public static void queen(String str) {
		String inp[] = new String[2];
		Scanner sc = new Scanner(System.in);
		int pos[] = search(str);
		System.out.print("Enter the destination coordinates:\n1.Y coordinate :	");
		inp[0] = sc.nextLine();
		System.out.print("2.X coordinate :	");
		inp[1] = sc.nextLine();
		try {
			if (Integer.parseInt(inp[0]) > 0 && Integer.parseInt(inp[0]) > 8) {
				System.out.println("Enter valid coordinates : ");
				queen(str);
			} else if (pos[0] == -1) {
				System.out.println("Oops, Cookie is out of Inventory.....!!");
				if (str.substring(0, 1).equals("W")) {
					illegal_move_W++;
					player1();
				} else {
					illegal_move_B++;
					player2();
				}
			} else {
				int end[] = pos_convert(inp, str);
				if ((pos[0] == end[0]) || (pos[1] == end[1]))
					move_for_rook(pos, end, str);
				else
					move_for_bishop(pos, end, str);
			}
		} catch (Exception e) {
			System.out.println("Enter valid coordinates : ");
			queen(str);
		}
	}

	public static void move_for_rook(int pos[], int end[], String str) {
		int intrupt = 0;
		String ele = "   ";
		int caseline_count = 0;
		if ((pos[0] != end[0] && pos[1] != end[1]) || (pos[0] == end[0] && pos[1] == end[1])
				|| (arr[end[0]][end[1]].substring(0, 1).equals(str.substring(0, 1)))) {
			if ((arr[end[0]][end[1]].substring(0, 1).equals(str.substring(0, 1))
					&& arr[end[0]][end[1]].substring(1).equals("Ln"))) {
				if ((arr[end[0]][end[1]].substring(0, 1).equals("W") && caseline_W == 1)
						|| arr[end[0]][end[1]].substring(0, 1).equals("B") && caseline_B == 1) {
					System.out.println("Caseline rules violated....\n");
					if (str.substring(0, 1).equals("W")) {
						illegal_move_W++;
						player1();
					} else {
						illegal_move_B++;
						player2();
					}
				} else if (check == 0) {
					if (pos[0] == end[0]) {
						if (pos[1] < end[1]) {
							for (int i = pos[1] + 1; i < end[1]; i++)
								if (!arr[pos[0]][i].equals("   ")) {
									intrupt = 1;
									break;
								}
						} else if (pos[1] > end[1]) {
							for (int i = end[1] + 1; i < pos[1]; i++)
								if (!arr[pos[0]][i].equals("   ")) {
									intrupt = 1;
									break;
								}
						}
					} else if (pos[1] == end[1]) {
						if (pos[0] < end[0]) {
							for (int i = pos[0] + 1; i < end[0]; i++)
								if (!arr[i][pos[1]].equals("   ")) {
									intrupt = 1;
									break;
								}
						} else if (pos[0] > end[0]) {
							for (int i = end[0] + 1; i < pos[0]; i++)
								if (!arr[i][pos[1]].equals("   ")) {
									intrupt = 1;
									break;
								}
						}
					}
					if (intrupt == 1) {
						System.out.println("Obtracles found on the way, make sure to clear them..!!!");
						if (str.substring(0, 1).equals("W")) {
							illegal_move_W++;
							player1();
						} else {
							illegal_move_B++;
							player2();
						}
					} else {
						if (str.substring(0, 1).equals("W")) {
							if (pos[1] - end[1] > 0) {
								arr[end[0]][end[1] + 1] = str;
								arr[pos[0]][pos[1] - 1] = "WLn";
							} else {
								arr[end[0]][end[1] - 1] = str;
								arr[pos[0]][pos[1] + 2] = "WLn";
							}
						} else {
							if (pos[1] - end[1] > 0) {
								arr[end[0]][end[1] + 1] = str;
								arr[pos[0]][pos[1] - 1] = "BLn";
							} else {
								arr[end[0]][end[1] - 1] = str;
								arr[pos[0]][pos[1] + 2] = "BLn";
							}
						}
						arr[end[0]][end[1]] = "   ";
						arr[pos[0]][pos[1]] = "   ";
						caseline_count = 1;
						System.out.println("\nCaseline Succesfull...!!!\n");
					}
				}
			} else {
				System.out.println("Move not possible");
				if (str.substring(0, 1).equals("W")) {
					illegal_move_W++;
					player1();
				} else {
					illegal_move_B++;
					player2();
				}
			}
		} else if (pos[0] == end[0]) {
			if (pos[1] < end[1]) {
				for (int i = pos[1] + 1; i < end[1]; i++)
					if (!arr[pos[0]][i].equals("   ")) {
						intrupt = 1;
						break;
					}
			} else if (pos[1] > end[1]) {
				for (int i = end[1] + 1; i < pos[1]; i++)
					if (!arr[pos[0]][i].equals("   ")) {
						intrupt = 1;
						break;
					}
			}
		} else if (pos[1] == end[1]) {
			if (pos[0] < end[0]) {
				for (int i = pos[0] + 1; i < end[0]; i++)
					if (!arr[i][pos[1]].equals("   ")) {
						intrupt = 1;
						break;
					}
			} else if (pos[0] > end[0]) {
				for (int i = end[0] + 1; i < pos[0]; i++)
					if (!arr[i][pos[1]].equals("   ")) {
						intrupt = 1;
						break;
					}
			}
		}
		if (intrupt == 1 && caseline_count == 0) {
			System.out.println("Obtracles found on the way, make sure to clear them..!!!");
			if (str.substring(0, 1).equals("W")) {
				player1();
				illegal_move_W++;
			} else {
				player2();
				illegal_move_B++;
			}
		} else if (intrupt == 0 && caseline_count == 0) {
			ele = arr[end[0]][end[1]];
			arr[end[0]][end[1]] = str;
			arr[pos[0]][pos[1]] = "   ";
		}
		back_move_rook_bsp_qn(pos, end, ele, str);
	}

	public static void move_for_bishop(int pos[], int end[], String str) {
		int intrupt = 0;
		String ele = "   ";
		if (Math.abs(end[0] - pos[0]) != Math.abs(end[1] - pos[1]) || (pos[0] == end[0] && pos[1] == end[1])
				|| (arr[end[0]][end[1]].substring(0, 1).equals(str.substring(0, 1)))) {
			System.out.println("Move not possible");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		} else {
			if ((end[0] - pos[0]) == (end[1] - pos[1])) {
				if (end[0] > pos[0]) {
					for (int i = 1; i < end[0] - pos[0]; i++)
						if (!arr[pos[0] + i][pos[1] + i].equals("   ")) {
							intrupt = 1;
							break;
						}
				} else if (end[0] < pos[0]) {
					for (int i = 1; i < pos[0] - end[0]; i++)
						if (!arr[pos[0] - i][pos[1] - i].equals("   ")) {
							intrupt = 1;
							break;
						}
				}
			} else if ((end[0] - pos[0]) == -1 * (end[1] - pos[1])) {
				if (end[0] > pos[0]) {
					for (int i = 1; i < end[0] - pos[0]; i++)
						if (!arr[pos[0] + i][pos[1] - i].equals("   ")) {
							intrupt = 1;
							break;
						}
				} else if (end[0] < pos[0]) {
					for (int i = 1; i < pos[0] - end[0]; i++)
						if (!arr[pos[0] - i][pos[1] + i].equals("   ")) {
							intrupt = 1;
							break;
						}
				}
			}
			if (intrupt == 1) {
				System.out.println("Obtracles found on the way, make sure to clear them..!!!");
				if (str.substring(0, 1).equals("W")) {
					player1();
					illegal_move_W++;
				} else {
					player2();
					illegal_move_B++;
				}
			} else {
				ele = arr[end[0]][end[1]];
				arr[end[0]][end[1]] = str;
				arr[pos[0]][pos[1]] = "   ";
				back_move_rook_bsp_qn(pos, end, ele, str);
			}
		}
	}

	public static int check_W(int count) {
		if (count == 0)
			System.out.println();
		int num_chk_cnt = 0;
		int pos[] = search("WLn");
		if (((pos[1] != 0) && arr[pos[0] - 1][pos[1] - 1].substring(0, 2).equals("BP"))
				|| ((pos[1] != 7) && arr[pos[0] - 1][pos[1] + 1].substring(0, 2).equals("BP"))) {
			if (count == 0)
				System.out.println("WARNING: Check via Pawn");
			else if (count == 1)
				num_chk_cnt++;
		}
		int intrupt = chk_diagonal_W(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Diagonal");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		intrupt = chk_perpendicular_W(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Perpendicular");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		intrupt = chk_knight_W(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Knight");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		if (count == 0)
			System.out.println();
		return num_chk_cnt;
	}

	public static int chk_diagonal_W(int pos[]) {
		int intrupt = 0;
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7 || pos[1] + i > 7)
				break;
			else {
				if (!arr[pos[0] + i][pos[1] + i].equals("   ")) {
					if (arr[pos[0] + i][pos[1] + i].substring(0, 2).equals("BB")
							|| arr[pos[0] + i][pos[1] + i].substring(0, 2).equals("BQ"))
						intrupt += 1;
					break;
				}
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0 || pos[1] - i < 0)
				break;
			else {
				if (!arr[pos[0] - i][pos[1] - i].equals("   ")) {
					if (arr[pos[0] - i][pos[1] - i].substring(0, 2).equals("BB")
							|| arr[pos[0] - i][pos[1] - i].substring(0, 2).equals("BQ"))
						intrupt += 1;
					break;
				}
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7 || pos[1] - i < 0)
				break;
			else if (!arr[pos[0] + i][pos[1] - i].equals("   ")) {
				if (arr[pos[0] + i][pos[1] - i].substring(0, 2).equals("BB")
						|| arr[pos[0] + i][pos[1] - i].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0 || pos[1] + i > 7)
				break;
			else if (!arr[pos[0] - i][pos[1] + i].equals("   ")) {
				if (arr[pos[0] - i][pos[1] + i].substring(0, 2).equals("BB")
						|| arr[pos[0] - i][pos[1] + i].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		return intrupt;
	}

	public static int chk_perpendicular_W(int pos[]) {
		int intrupt = 0;
		for (int i = 1; i < 8; i++) {
			if (pos[1] + i > 7)
				break;
			else if (!arr[pos[0]][pos[1] + i].equals("   ")) {
				if (arr[pos[0]][pos[1] + i].substring(0, 2).equals("BR")
						|| arr[pos[0]][pos[1] + i].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[1] - i < 0)
				break;
			else if (!arr[pos[0]][pos[1] - i].equals("   ")) {
				if (arr[pos[0]][pos[1] - i].substring(0, 2).equals("BR")
						|| arr[pos[0]][pos[1] - i].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7)
				break;
			else if (!arr[pos[0] + i][pos[1]].equals("   ")) {
				if (arr[pos[0] + i][pos[1]].substring(0, 2).equals("BR")
						|| arr[pos[0] + i][pos[1]].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0)
				break;
			if (!arr[pos[0] - i][pos[1]].equals("   ")) {
				if (arr[pos[0] - i][pos[1]].substring(0, 2).equals("BR") || arr[pos[0]][i].substring(0, 2).equals("BQ"))
					intrupt += 1;
				break;
			}
		}
		return intrupt;
	}

	public static int chk_knight_W(int pos[]) {
		int intrupt = 0;
		if ((pos[0] >= 2) && (pos[1] != 0)) // upupleft
		{
			if (arr[pos[0] - 2][pos[1] - 1].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] >= 2) && (pos[1] != 7)) // upupright
		{
			if (arr[pos[0] - 2][pos[1] + 1].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] < 6) && (pos[1] != 0)) // downdownleft
		{
			if (arr[pos[0] + 2][pos[1] - 1].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] < 6) && (pos[1] != 7)) // downdownright
		{
			if (arr[pos[0] + 2][pos[1] + 1].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] != 0) && (pos[1] >= 2)) // leftleftup
		{
			if (arr[pos[0] - 1][pos[1] - 2].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] != 7) && (pos[1] >= 2)) // leftleftdown
		{
			if (arr[pos[0] + 1][pos[1] - 2].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] != 0) && (pos[1] < 6)) // rightrightup
		{
			if (arr[pos[0] - 1][pos[1] + 2].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		if ((pos[0] != 7) && (pos[1] < 6)) // rightrightdown
		{
			if (arr[pos[0] + 1][pos[1] + 2].substring(0, 2).equals("BK"))
				intrupt += 1;
		}
		return intrupt;
	}

	public static int check_B(int count) {
		if (count == 0)
			System.out.println();
		int num_chk_cnt = 0;
		int pos[] = search("BLn");
		if (((pos[1] != 0) && arr[pos[0] + 1][pos[1] - 1].substring(0, 2).equals("WP"))
				|| ((pos[1] != 7) && arr[pos[0] + 1][pos[1] + 1].substring(0, 2).equals("WP"))) {
			if (count == 0)
				System.out.println("WARNING: Check via Pawn");
			else if (count == 1)
				num_chk_cnt++;
		}
		int intrupt = chk_diagonal_B(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Diagonal");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		intrupt = chk_perpendicular_B(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Perpendicular");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		intrupt = chk_knight_B(pos);
		if (intrupt >= 1 && count == 0)
			System.out.println("WARNING: Check via Knight");
		else if (intrupt >= 1 && count == 1)
			num_chk_cnt += intrupt;
		if (count == 0)
			System.out.println();
		return num_chk_cnt;
	}

	public static int chk_diagonal_B(int pos[]) {
		int intrupt = 0;
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7 || pos[1] + i > 7)
				break;
			else {
				if (!arr[pos[0] + i][pos[1] + i].equals("   ")) {
					if (arr[pos[0] + i][pos[1] + i].substring(0, 2).equals("WB")
							|| arr[pos[0] + i][pos[1] + i].substring(0, 2).equals("WQ"))
						intrupt = 1;
					break;
				}
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0 || pos[1] - i < 0)
				break;
			else {
				if (!arr[pos[0] - i][pos[1] - i].equals("   ")) {
					if (arr[pos[0] - i][pos[1] - i].substring(0, 2).equals("WB")
							|| arr[pos[0] - i][pos[1] - i].substring(0, 2).equals("WQ"))
						intrupt = 1;
					break;
				}
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7 || pos[1] - i < 0)
				break;
			else if (!arr[pos[0] + i][pos[1] - i].equals("   ")) {
				if (arr[pos[0] + i][pos[1] - i].substring(0, 2).equals("WB")
						|| arr[pos[0] + i][pos[1] - i].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0 || pos[1] + i > 7)
				break;
			else if (!arr[pos[0] - i][pos[1] + i].equals("   ")) {
				if (arr[pos[0] - i][pos[1] + i].substring(0, 2).equals("WB")
						|| arr[pos[0] - i][pos[1] + i].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		return intrupt;
	}

	public static int chk_perpendicular_B(int pos[]) {
		int intrupt = 0;
		for (int i = 1; i < 8; i++) {
			if (pos[1] + i > 7)
				break;
			else if (!arr[pos[0]][pos[1] + i].equals("   ")) {
				if (arr[pos[0]][pos[1] + i].substring(0, 2).equals("WR")
						|| arr[pos[0]][pos[1] + i].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[1] - i < 0)
				break;
			else if (!arr[pos[0]][pos[1] - i].equals("   ")) {
				if (arr[pos[0]][pos[1] - i].substring(0, 2).equals("WR")
						|| arr[pos[0]][pos[1] - i].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] + i > 7)
				break;
			else if (!arr[pos[0] + i][pos[1]].equals("   ")) {
				if (arr[pos[0] + i][pos[1]].substring(0, 2).equals("WR")
						|| arr[pos[0] + i][pos[1]].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		for (int i = 1; i < 8; i++) {
			if (pos[0] - i < 0)
				break;
			if (!arr[pos[0] - i][pos[1]].equals("   ")) {
				if (arr[pos[0] - i][pos[1]].substring(0, 2).equals("WR")
						|| arr[pos[0] - i][pos[1]].substring(0, 2).equals("WQ"))
					intrupt = 1;
				break;
			}
		}
		return intrupt;
	}

	public static int chk_knight_B(int pos[]) {
		int intrupt = 0;
		if ((pos[0] >= 2) && (pos[1] != 0)) // upupleft
		{
			if (arr[pos[0] - 2][pos[1] - 1].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] >= 2) && (pos[1] != 7)) // upupright
		{
			if (arr[pos[0] - 2][pos[1] + 1].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] < 6) && (pos[1] != 0)) // downdownleft
		{
			if (arr[pos[0] + 2][pos[1] - 1].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] < 6) && (pos[1] != 7)) // downdownright
		{
			if (arr[pos[0] + 2][pos[1] + 1].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] != 0) && (pos[1] >= 2)) // leftleftup
		{
			if (arr[pos[0] - 1][pos[1] - 2].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] != 7) && (pos[1] >= 2)) // leftleftdown
		{
			if (arr[pos[0] + 1][pos[1] - 2].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] != 0) && (pos[1] < 6)) // rightrightup
		{
			if (arr[pos[0] - 1][pos[1] + 2].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		if ((pos[0] != 7) && (pos[1] < 6)) // rightrightdown
		{
			if (arr[pos[0] + 1][pos[1] + 2].substring(0, 2).equals("WK"))
				intrupt = 1;
		}
		return intrupt;
	}

	public static void pawn_swap(int pos[], String str) {
		Scanner sc = new Scanner(System.in);
		System.out.print(
				"\nCongrats!!!\nYou can swap the pawn with of of the cookie's mentioned below:\n1.Queen\n2.Bishop\n3.Knight\n4.Rook\nInput:	");
		int num = sc.nextInt();
		switch (num) {
			case 1:
				if (str.substring(0, 1).equals("W")) {
					if (count_queen_w == 1) {
						int pos1[] = search("WQn");
						arr[pos[0]][pos[1]] = "WQ2";
						arr[pos1[0]][pos1[1]] = "WQ1";
					} else if (count_queen_w >= 2) {
						String cookie = "WQ" + Integer.toString(count_queen_w + 1);
						arr[pos[0]][pos[1]] = cookie;
					}
					count_queen_w++;
				} else {
					if (count_queen_b == 1) {
						int pos1[] = search("BQn");
						arr[pos[0]][pos[1]] = "BQ2";
						arr[pos1[0]][pos1[1]] = "BQ1";
					} else if (count_queen_b >= 2) {
						String cookie = "BQ" + Integer.toString(count_queen_b + 1);
						arr[pos[0]][pos[1]] = cookie;
					}
					count_queen_b++;
				}
				break;
			case 2:
				if (str.substring(0, 1).equals("W")) {
					count_bishop_w++;
					String cookie = "WB" + Integer.toString(count_bishop_w);
					arr[pos[0]][pos[1]] = cookie;
				} else {
					count_bishop_b++;
					String cookie = "BB" + Integer.toString(count_bishop_b);
					arr[pos[0]][pos[1]] = cookie;
				}
				break;
			case 3:
				if (str.substring(0, 1).equals("W")) {
					count_knight_w++;
					String cookie = "BK" + Integer.toString(count_knight_w);
					arr[pos[0]][pos[1]] = cookie;
				} else {
					count_knight_b++;
					String cookie = "BK" + Integer.toString(count_knight_b);
					arr[pos[0]][pos[1]] = cookie;
				}
				break;
			case 4:
				if (str.substring(0, 1).equals("W")) {
					count_rook_w++;
					String cookie = "BR" + Integer.toString(count_rook_w);
					arr[pos[0]][pos[1]] = cookie;
				} else {
					count_rook_b++;
					String cookie = "BR" + Integer.toString(count_rook_b);
					arr[pos[0]][pos[1]] = cookie;
				}
				break;
			default:
				System.out.println("Plss Don't confuse me...!!!\nTry input again");
				if (str.substring(0, 1).equals("W")) {
					illegal_move_W++;
					player1();
				} else {
					illegal_move_B++;
					player2();
				}
				pawn_swap(pos, str);
		}
	}

	public static void back_move_knight(String ele, int init[], int pos[], String str) {
		if (str.substring(0, 1).equals("W"))
			check1 = check_W(1);
		else
			check1 = check_B(1);
		if (check1 >= 1) {
			arr[pos[0]][pos[1]] = str;
			arr[init[0]][init[1]] = ele;
			read = 0;
			System.out.println("Check not resolved!!! Also you made an invalid move:");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		}
	}

	public static void back_move_king(String ele, int move, int pos[], String str) {
		if (str.substring(0, 1).equals("W"))
			check1 = check_W(1);
		else
			check1 = check_B(1);
		if (check1 >= 1) {
			arr[pos[0]][pos[1]] = str;
			switch (move) {
				case 1:
					arr[pos[0]][pos[1] - 1] = ele;
					break;
				case 2:
					arr[pos[0]][pos[1] + 1] = ele;
					break;
				case 3:
					arr[pos[0] - 1][pos[1]] = ele;
					break;
				case 4:
					arr[pos[0] + 1][pos[1]] = ele;
					break;
				case 5:
					arr[pos[0] - 1][pos[1] - 1] = ele;
					break;
				case 6:
					arr[pos[0] - 1][pos[1] + 1] = ele;
					break;
				case 7:
					arr[pos[0] + 1][pos[1] - 1] = ele;
					break;
				case 8:
					arr[pos[0] + 1][pos[1] + 1] = ele;
					break;
			}
			System.out.println("Check not resolved!!! Also you made an invalid move:");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		}
	}

	public static void back_move_rook_bsp_qn(int pos[], int end[], String ele, String str) {
		if (str.substring(0, 1).equals("W"))
			check1 = check_W(1);
		else
			check1 = check_B(1);
		if (check1 >= 1) {
			arr[pos[0]][pos[1]] = str;
			arr[end[0]][end[1]] = ele;
			System.out.println("Check not resolved!!! Also you made an invalid move:");
			if (str.substring(0, 1).equals("W")) {
				illegal_move_W++;
				player1();
			} else {
				illegal_move_B++;
				player2();
			}
		}
	}

	public static void back_move_pawn(int pos[], String cokie, int kill, String str, int door, int CNT) {
		if (str.substring(0, 1).equals("W"))
			check1 = check_W(1);
		else
			check1 = check_B(1);
		if (check1 >= 1) {
			int curr[] = search(str);
			if (kill == 1 && (door != 3)) {
				arr[curr[0]][curr[1]] = cokie;
				arr[pos[0]][pos[1]] = str;
			} else if (kill == 2 || (kill == 1 && (door == 3))) {
				arr[curr[0]][curr[1]] = "   ";
				arr[pos[0]][pos[1]] = str;
			}
			System.out.println("Check not resolved!!! Also you made an invalid move:");
			int index = Integer.parseInt(str.substring(2)) - 1;
			if (str.substring(0, 1).equals("W")) {
				if (CNT == 1)
					pawn_first_step[index] = 0;
				illegal_move_W++;
				player1();
			} else {
				if (CNT == 1)
					pawn_first_step1[index] = 0;
				illegal_move_B++;
				player2();
			}
		}
	}

	public static int[] search(String str) {
		int pos[] = { -1, -1 };
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (arr[i][j].equals(str)) {
					pos[0] = i;
					pos[1] = j;
				}
		return pos;
	}

	public static int[] pos_convert(String pos[], String str) {
		int ans[] = { -1, -1 };
		ans[0] = Integer.parseInt(pos[0]) - 1;
		if (ans[0] < 0 || ans[0] > 7) {
			System.out.println("Invalid First co-ordinate:\nEnter number in range '1' to '8' as input of Y coordinate");
			if (str.substring(0, 1).equals("W")) {
				player1();
				illegal_move_W++;
			} else {
				player2();
				illegal_move_B++;
			}
		}
		switch (pos[1]) {
			case "a":
				ans[1] = 0;
				break;
			case "b":
				ans[1] = 1;
				break;
			case "c":
				ans[1] = 2;
				break;
			case "d":
				ans[1] = 3;
				break;
			case "e":
				ans[1] = 4;
				break;
			case "f":
				ans[1] = 5;
				break;
			case "g":
				ans[1] = 6;
				break;
			case "h":
				ans[1] = 7;
				break;
			default:
				System.out.println(
						"Invalid Second co-ordinate:\nEnter alphabet in range 'a' to 'h' as input of X coordinate");
				if (str.substring(0, 1).equals("W")) {
					player1();
					illegal_move_W++;
				} else {
					player2();
					illegal_move_B++;
				}
		}
		return ans;
	}

	public static void victory() {
		Scanner sc = new Scanner(System.in);
		int pos[] = search("WLn");
		int pos1[] = search("BLn");
		if (pos[0] == -1 || pos1[0] == -1 || illegal_move_B == 3 || illegal_move_W == 3) {
			System.out.println("\n----------------------------------------------------------------------");
			if (pos[0] == -1 || illegal_move_W == 3)
				System.out.println("\t\tCongragulations " + p2 + " Team.....!!!");
			else if (pos1[0] == -1 || illegal_move_B == 3)
				System.out.println("\t\tCongragulations " + p1 + "'s Team.....!!!");
			System.out.println("----------------------------------------------------------------------");
			int white = chance % 2 == 0 ? chance / 2 : chance / 2 + 1;
			System.out.println(
					"\nTotal chances taken in the game are " + chance + ". \nTotal steps taken by White team are "
							+ white + ". \nTotal steps taken by Black team are " + (chance / 2) + ".");
			System.out.print("\nHope You Enjoyed...!!! \nThank You for playing \nEnter '1' to Exit\nInput: ");
			int yorn = sc.nextInt();
			if (yorn != 2864)
				System.exit(0);
		}
	}

	public static void details() {
		System.out.println("This is a multiplayer chess game in terminal.");
		System.out.println("Lets see how this goes.......\n\n");
		System.out.println("Some Basic instructions :");
		System.out.println("1. Every Cookie's name is an alpha-numeric combination of length 3");
		System.out.println("2. First letter dentotes weather the cookie is from black army (B) or white army (W)");
		System.out.println("3. Second letter denotes kind of character in this war.");
		System.out.println("4. Basic cookie names are as follows:");
		System.out.println("	R-Rook   (Hathi)");
		System.out.println("	K-Knight (Ghoda)");
		System.out.println("	B-Bishop (Vazir)");
		System.out.println("	Ln-Lion  (King, Raja)");
		System.out.println("	Qn-Queen (Rani)");
		System.out.println("	P-Pawn   (Sipahi)");
		System.out
				.println("5. Third is a numeric character used to distinguish characters if they are of same type\n\n");
		System.out.println("Some Pre-cautions:");
		System.out.println(
				"1. Caslene will be performed by selecting rook, Selecting king in that case will be of no benifits.");
		System.out.println("2. X and Y axis are shown on the ends of board, so remember not to make mistakes.");
		System.out.println("3. Three invalid/illegal moves may lead to your opponents victory so mind your steps.\n\n");
		System.out.println("Input Format:");
		System.out.println(
				"-> In the Field 'Enter a cokie:' The input must be in the exact same case as in the board.\n for example: WP3 is correct input whereas inputs like wp3 or Wp3 ar any other from mentioned may result to invalid input.");
		System.out.println("\nAll the best players :)\n");
	}
}