package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import code.CoastGuard;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class CoastGuardPublic {

	String grid0 = "5,6;50;0,1;0,4,3,3;1,1,90;";
	String grid1 = "6,6;52;2,0;2,4,4,0,5,4;2,1,19,4,2,6,5,0,8;";
	String grid2 = "7,5;40;2,3;3,6;1,1,10,4,5,90;";
	String grid3 = "8,5;60;4,6;2,7;3,4,37,3,5,93,4,0,40;";
	String grid4 = "5,7;63;4,2;6,2,6,3;0,0,17,0,2,73,3,0,30;";
	String grid5 = "5,5;69;3,3;0,0,0,1,1,0;0,3,78,1,2,2,1,3,14,4,4,9;";
	String grid6 = "7,5;86;0,0;1,3,1,5,4,2;1,1,42,2,5,99,3,5,89;";
	String grid7 = "6,7;82;1,4;2,3;1,1,58,3,0,58,4,2,72;";
	String grid8 = "6,6;74;1,1;0,3,1,0,2,0,2,4,4,0,4,2,5,0;0,0,78,3,3,5,4,3,40;";
	String grid9 = "7,5;100;3,4;2,6,3,5;0,0,4,0,1,8,1,4,77,1,5,1,3,2,94,4,3,46;";
	String grid10 = "10,6;59;1,7;0,0,2,2,3,0,5,3;1,3,69,3,4,80,4,7,94,4,9,14,5,2,39;";

	@Test(timeout = 10000)
	public void testa0() throws Exception {
		String solution = CoastGuard.solve(grid0, "BF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testa1() throws Exception {
		String solution = CoastGuard.solve(grid1, "BF", false);
		solution = solution.replace(" ", "");
		System.out.println(solution);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testa2() throws Exception {
		String solution = CoastGuard.solve(grid2, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 10000)
	public void testa3() throws Exception {
		String solution = CoastGuard.solve(grid3, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testa4() throws Exception {
		String solution = CoastGuard.solve(grid4, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testa5() throws Exception {
		String solution = CoastGuard.solve(grid5, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 60000)
	public void testa6() throws Exception {
		String solution = CoastGuard.solve(grid6, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 60000)
	public void testa7() throws Exception {
		String solution = CoastGuard.solve(grid7, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 60000)
	public void testa8() throws Exception {
		String solution = CoastGuard.solve(grid8, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 200000)
	public void testa9() throws Exception {
		String solution = CoastGuard.solve(grid9, "BF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 10000)
	public void testb0() throws Exception {
		String solution = CoastGuard.solve(grid0, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testb1() throws Exception {
		String solution = CoastGuard.solve(grid1, "DF", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testb2() throws Exception {
		String solution = CoastGuard.solve(grid2, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 10000)
	public void testb3() throws Exception {
		String solution = CoastGuard.solve(grid3, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testb4() throws Exception {
		String solution = CoastGuard.solve(grid4, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testb5() throws Exception {
		String solution = CoastGuard.solve(grid5, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testb6() throws Exception {
		String solution = CoastGuard.solve(grid6, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 10000)
	public void testb7() throws Exception {
		String solution = CoastGuard.solve(grid7, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void testb8() throws Exception {
		String solution = CoastGuard.solve(grid8, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 60000)
	public void testb9() throws Exception {
		String solution = CoastGuard.solve(grid9, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 60000)
	public void testb10() throws Exception {
		String solution = CoastGuard.solve(grid10, "DF", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	@Test(timeout = 10000)
	public void testc0() throws Exception {
		String solution = CoastGuard.solve(grid0, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testc1() throws Exception {
		String solution = CoastGuard.solve(grid1, "UC", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testc2() throws Exception {
		String solution = CoastGuard.solve(grid2, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 10000)
	public void testc3() throws Exception {
		String solution = CoastGuard.solve(grid3, "UC", true);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testc4() throws Exception {
		String solution = CoastGuard.solve(grid4, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testc5() throws Exception {
		String solution = CoastGuard.solve(grid5, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testc6() throws Exception {
		String solution = CoastGuard.solve(grid6, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 60000)
	public void testc7() throws Exception {
		String solution = CoastGuard.solve(grid7, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 60000)
	public void testc8() throws Exception {
		String solution = CoastGuard.solve(grid8, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 60000)
	public void testc9() throws Exception {
		String solution = CoastGuard.solve(grid9, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 60000)
	public void testc10() throws Exception {
		String solution = CoastGuard.solve(grid10, "UC", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	@Test(timeout = 10000)
	public void testd0() throws Exception {
		String solution = CoastGuard.solve(grid0, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testd1() throws Exception {
		String solution = CoastGuard.solve(grid1, "ID", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testd2() throws Exception {
		String solution = CoastGuard.solve(grid2, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 40000)
	public void testd3() throws Exception {
		String solution = CoastGuard.solve(grid3, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testd4() throws Exception {
		String solution = CoastGuard.solve(grid4, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testd5() throws Exception {
		String solution = CoastGuard.solve(grid5, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testd6() throws Exception {
		String solution = CoastGuard.solve(grid6, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 10000)
	public void testd7() throws Exception {
		String solution = CoastGuard.solve(grid7, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void testd8() throws Exception {
		String solution = CoastGuard.solve(grid8, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 100000)
	public void testd9() throws Exception {
		String solution = CoastGuard.solve(grid9, "ID", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 10000)
	public void teste0() throws Exception {
		String solution = CoastGuard.solve(grid0, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void teste1() throws Exception {
		String solution = CoastGuard.solve(grid1, "GR1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void teste2() throws Exception {
		String solution = CoastGuard.solve(grid2, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 30000)
	public void teste3() throws Exception {
		String solution = CoastGuard.solve(grid3, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void teste4() throws Exception {
		String solution = CoastGuard.solve(grid4, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void teste5() throws Exception {
		String solution = CoastGuard.solve(grid5, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void teste6() throws Exception {
		String solution = CoastGuard.solve(grid6, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 10000)
	public void teste7() throws Exception {
		String solution = CoastGuard.solve(grid7, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void teste8() throws Exception {
		String solution = CoastGuard.solve(grid8, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 60000)
	public void teste9() throws Exception {
		String solution = CoastGuard.solve(grid9, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 60000)
	public void teste10() throws Exception {
		String solution = CoastGuard.solve(grid10, "GR1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid10, solution));
	}

	@Test(timeout = 10000)
	public void testf0() throws Exception {
		String solution = CoastGuard.solve(grid0, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testf1() throws Exception {
		String solution = CoastGuard.solve(grid1, "GR2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testf2() throws Exception {
		String solution = CoastGuard.solve(grid2, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 100000)
	public void testf4() throws Exception {
		String solution = CoastGuard.solve(grid4, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testf5() throws Exception {
		String solution = CoastGuard.solve(grid5, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testf6() throws Exception {
		String solution = CoastGuard.solve(grid6, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 60000)
	public void testf7() throws Exception {
		String solution = CoastGuard.solve(grid7, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void testf8() throws Exception {
		String solution = CoastGuard.solve(grid8, "GR2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 10000)
	public void testg0() throws Exception {
		String solution = CoastGuard.solve(grid0, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testg1() throws Exception {
		String solution = CoastGuard.solve(grid1, "AS1", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testg2() throws Exception {
		String solution = CoastGuard.solve(grid2, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 10000)
	public void testg3() throws Exception {
		String solution = CoastGuard.solve(grid3, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 10000)
	public void testg4() throws Exception {
		String solution = CoastGuard.solve(grid4, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testg5() throws Exception {
		String solution = CoastGuard.solve(grid5, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testg6() throws Exception {
		String solution = CoastGuard.solve(grid6, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 10000)
	public void testg7() throws Exception {
		String solution = CoastGuard.solve(grid7, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void testg8() throws Exception {
		String solution = CoastGuard.solve(grid8, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 60000)
	public void testg9() throws Exception {
		String solution = CoastGuard.solve(grid9, "AS1", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	@Test(timeout = 10000)
	public void testh0() throws Exception {
		String solution = CoastGuard.solve(grid0, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid0, solution));
	}

	@Test(timeout = 10000)
	public void testh1() throws Exception {
		String solution = CoastGuard.solve(grid1, "AS2", false);
		solution = solution.replace(" ", "");
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid1, solution));
	}

	@Test(timeout = 10000)
	public void testh2() throws Exception {
		String solution = CoastGuard.solve(grid2, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid2, solution));
	}

	@Test(timeout = 10000)
	public void testh3() throws Exception {
		String solution = CoastGuard.solve(grid3, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid3, solution));
	}

	@Test(timeout = 60000)
	public void testh4() throws Exception {
		String solution = CoastGuard.solve(grid4, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid4, solution));
	}

	@Test(timeout = 10000)
	public void testh5() throws Exception {
		String solution = CoastGuard.solve(grid5, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid5, solution));
	}

	@Test(timeout = 10000)
	public void testh6() throws Exception {
		String solution = CoastGuard.solve(grid6, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid6, solution));
	}

	@Test(timeout = 10000)
	public void testh7() throws Exception {
		String solution = CoastGuard.solve(grid7, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid7, solution));
	}

	@Test(timeout = 10000)
	public void testh8() throws Exception {
		String solution = CoastGuard.solve(grid8, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid8, solution));
	}

	@Test(timeout = 60000)
	public void testh9() throws Exception {
		String solution = CoastGuard.solve(grid9, "AS2", false);
		assertTrue("The output actions do not lead to a goal state.", applyPlan(grid9, solution));
	}

	static class Checker {

		byte a;
		byte b;
		HashMap<String, Byte> ss = new HashMap<String, Byte>();
		ArrayList<String> is = new ArrayList<String>();
		byte s;
		int r;
		int d;
		byte x00;
		byte x01;
		byte xc;
		byte cp;

		public Checker(byte m, byte n, byte x, byte x00, byte x01, ArrayList<String> st, HashMap<String, Byte> sh) {
			this.a = m;
			this.b = n;
			this.xc = x;
			this.x00 = x00;
			this.x01 = x01;
			this.is = st;
			this.ss = sh;

		}

		boolean f1(int z, int k) {
			if (!f99(x00 + z, x01 + k)) {
				mn();
				return false;
			}

			this.x00 += z;
			this.x01 += k;
			mn();
			return true;
		}

		boolean f2() {

			if (!ss.containsKey(x00 + "," + x01)) {
				mn();
				return false;
			}
			if (ss.get(x00 + "," + x01) < 0) {
				mn();
				return false;
			}
			byte ts = ss.get(x00 + "," + x01);
			byte cc = (byte) (xc - cp);
			if (cc >= ts) {
				cp += ts;
				ss.replace(x00 + "," + x01, (byte) -20);
			} else {
				cp = xc;
				int n = ts - cc;
				ss.replace(x00 + "," + x01, (byte) n);
			}
			mn();
			return true;
		}

		boolean f3() {

			if (!is.contains(x00 + "," + x01)) {
				mn();
				return false;
			}
			s += cp;
			cp = 0;
			mn();
			return true;
		}

		boolean f4() {

			if (!ss.containsKey(x00 + "," + x01)) {
				mn();
				return false;
			}
			if (ss.get(x00 + "," + x01) < 0 && ss.get(x00 + "," + x01) > -20) {

				r += 1;
				ss.replace(x00 + "," + x01, (byte) 0);
				mn();
				return true;
			}
			return false;

		}

		boolean f99(int i, int j) {

			return i >= this.b || i < 0 || j >= this.a || j < 0 ? false : true;

		}

		void mn() {
			ArrayList<String> toclean = new ArrayList<String>();
			for (String k : ss.keySet()) {
				byte v = ss.get(k);
				if (v <= (byte) -1 && v >= (byte) -20)
					v++;
				else {
					if (v == 1) {
						v = (byte) -19;
						d++;
					} else {
						if (v > (byte) 1) {
							v--;
							d++;
						}
					}
				}

				if (v == 0) {
					toclean.add(k);
				} else {
					ss.replace(k, v);
				}

			}
			for (String c : toclean) {
				ss.remove(c);
			}

		}

		void clean() {
			for (String k : ss.keySet()) {
				if (ss.get(k).equals((byte) 0))
					ss.remove(k);
			}
		}

		public boolean cool() {
			return ss.size() == 0 && cp == 0;
		}

	}

	public static boolean applyPlan(String grid, String solution) {
		boolean linkin = true;
		String[] solutionArray = solution.split(";");
		String plan = solutionArray[0];
		int blue = Integer.parseInt(solutionArray[1]);
		int doors = Integer.parseInt(solutionArray[2]);

		plan.replace(" ", "");
		plan.replace("\n", "");
		plan.replace("\r", "");
		plan.replace("\n\r", "");
		plan.replace("\t", "");

		String[] actions = plan.split(",");

		String[] gridArray = grid.split(";");
		String[] dimensions = gridArray[0].split(",");
		byte m = Byte.parseByte(dimensions[0]);
		byte n = Byte.parseByte(dimensions[1]);

		byte x = Byte.parseByte(gridArray[1]);

		String[] xx = gridArray[2].split(",");
		byte x00 = Byte.parseByte(xx[0]);
		byte x01 = Byte.parseByte(xx[1]);

		String[] st = gridArray[3].split(",");
		ArrayList<String> xyz = new ArrayList<String>();
		for (int i = 0; i < st.length - 1; i += 2) {
			xyz.add(st[i] + "," + st[i + 1]);
		}

		String[] sh = gridArray[4].split(",");
		HashMap<String, Byte> m4 = new HashMap<String, Byte>();
		for (int i = 0; i < sh.length - 1; i += 3) {
			m4.put(sh[i] + "," + sh[i + 1], Byte.parseByte(sh[i + 2]));
		}
		Checker s = new Checker(m, n, x, x00, x01, xyz, m4);
		for (int i = 0; i < actions.length; i++) {

			switch (actions[i]) {
				case "up":
					linkin = s.f1(-1, 0);
					break;
				case "down":
					linkin = s.f1(1, 0);
					break;
				case "right":
					linkin = s.f1(0, 1);
					break;
				case "left":
					linkin = s.f1(0, -1);
					break;
				case "pickup":
					linkin = s.f2();
					break;
				case "drop":
					linkin = s.f3();
					break;
				case "retrieve":
					linkin = s.f4();
					break;
				default:
					linkin = false;
					break;

			}
			if (!linkin) {
				System.out.println("action that failed " + actions[i]);
				return false;
			}
		}

		System.out.println("TEST");

		return s.cool() && s.d == blue && s.r == doors;
	}
}
