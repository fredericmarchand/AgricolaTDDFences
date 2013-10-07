package agricola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class AgricolaController extends JFrame implements MouseListener,
		ActionListener {

	// private Player player,player1,player2,player3,player4,player5;
	private Player[] players;
	public FarmView view;
	private JButton[][] b;
	public Space[][][] farm;
	public JButton b_room, b_start, b_grain, b_field, b_stable, b_day, b_wood,
			b_clay, b_reed, b_food, b_sheep, b_sow, b_fences, b_improve,
			b_stone, b_growth, b_renov, b_boar, b_vege, b_cattle, b_stone2,
			b_growth2, b_fieldsow, b_renov2, b3_clay, b3_2wood, b3_resource,
			b3_resource2, b4_wood, b4_2clay, b4_2wood, b4_food, b4_resource,
			b4_3resource, b5_4wood, b5_3clay, b5_roomfood, b5_reed, b5_animals,
			b5_resource;

	private JRadioButtonMenuItem mplayer1, mplayer2, mplayer3, mplayer4,
			mplayer5;
	private int[] sold;
	private float[] total;
	@SuppressWarnings("unused")
	private Space seat_text;
	private int game, num_players;
	public int turn, wood, clay, reed, food, sheep, stone, stone2, boar,
			cattle, clay_3p, wood2_3p, wood_4p, wood2_4p, clay2_4p, food_4p,
			wood4_5p, clay3_5p, food_5p, reed_5p;
	private int cur_player, start_player, view_player;
	public boolean wField, wRoom, wStableRoom, wStable, wRoom2, wSow,
			wFieldSow, wFences, wSheep, wBoar, wCattle, wField2;
	public boolean debug_mode;
	
	JButton fence_done;

	public AgricolaController(int numPlayers) {
		num_players = numPlayers;
		debug_mode = true;
		players = new Player[5];
		farm = new Space[5][7][11];

		// player = new Player();
		view = new FarmView();

		players[0] = new Player();
		for (int r = 0; r < 7; r++) {
			for (int col = 0; col < 11; col++) {
				farm[0][r][col] = new Space();
				if (r % 2 == 1 && col % 2 == 1) {
					farm[0][r][col].setSquare();
					if ((col == 1 && r == 3) || (col == 1 && r == 5))
						farm[0][r][col].setType('w');
				}
				if (r % 2 == 0 && col % 2 == 0)
					farm[0][r][col].setValid();

			}

		}

		if (num_players > 1) {
			players[1] = new Player();
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[1][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[1][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[1][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[1][r][col].setValid();

				}

			}

		}
		if (num_players > 2) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[2][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[2][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[2][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[2][r][col].setValid();

				}

			}
			players[2] = new Player();
		}
		if (num_players > 3) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[3][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[3][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[3][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[3][r][col].setValid();

				}

			}
			players[3] = new Player();
		}
		if (num_players > 4) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[4][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[4][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[4][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[4][r][col].setValid();

				}

			}
			players[4] = new Player();
		}

		cur_player = 0;
		start_player = 0;
		view_player = 0;
		b = view.getButtons();
		game = 1;
		wood = 3;
		clay = 1;
		reed = 1;
		food = 1;
		sheep = 1;
		stone = 1;
		stone2 = 1;
		boar = 1;
		cattle = 1;
		turn = 1;

		if (num_players == 3) {
			clay_3p = 1;
			wood2_3p = 1;

			view.get3Actions()[0].setVisible(true);
			view.get3Actions()[1].setVisible(true);
			view.get3Actions()[2].setVisible(true);
			view.get3Actions()[3].setVisible(true);

			b3_clay = view.get3Actions()[0];
			b3_clay.addActionListener(this);

			b3_2wood = view.get3Actions()[1];
			b3_2wood.addActionListener(this);

			b3_resource = view.get3Actions()[2];
			b3_resource.addActionListener(this);

			b3_resource2 = view.get3Actions()[3];
			b3_resource2.addActionListener(this);

			view.get3Texts()[0].setVisible(true);
			view.get3Texts()[1].setVisible(true);

		}

		else if (num_players == 4) {
			wood_4p = 1;
			wood2_4p = 2;
			clay2_4p = 2;
			food_4p = 1;

			view.get4Actions()[0].setVisible(true);
			view.get4Actions()[1].setVisible(true);
			view.get4Actions()[2].setVisible(true);
			view.get4Actions()[3].setVisible(true);
			view.get4Actions()[4].setVisible(true);
			view.get4Actions()[5].setVisible(true);

			b4_wood = view.get4Actions()[0];
			b4_wood.addActionListener(this);

			b4_2clay = view.get4Actions()[1];
			b4_2clay.addActionListener(this);

			b4_2wood = view.get4Actions()[2];
			b4_2wood.addActionListener(this);

			b4_food = view.get4Actions()[3];
			b4_food.addActionListener(this);

			b4_resource = view.get4Actions()[4];
			b4_resource.addActionListener(this);

			b4_3resource = view.get4Actions()[5];
			b4_3resource.addActionListener(this);

			view.get4Texts()[0].setVisible(true);
			view.get4Texts()[1].setVisible(true);
			view.get4Texts()[2].setVisible(true);
			view.get4Texts()[3].setVisible(true);
		} else if (num_players == 5) {
			wood4_5p = 5;
			clay3_5p = 3;
			food_5p = 1;
			reed_5p = 1;

			view.get5Actions()[0].setVisible(true);
			view.get5Actions()[1].setVisible(true);
			view.get5Actions()[2].setVisible(true);
			view.get5Actions()[3].setVisible(true);
			view.get5Actions()[4].setVisible(true);
			view.get5Actions()[5].setVisible(true);

			b5_4wood = view.get5Actions()[0];
			b5_4wood.addActionListener(this);

			b5_3clay = view.get5Actions()[1];
			b5_3clay.addActionListener(this);

			b5_roomfood = view.get5Actions()[2];
			b5_roomfood.addActionListener(this);

			b5_reed = view.get5Actions()[3];
			b5_reed.addActionListener(this);

			b5_animals = view.get5Actions()[4];
			b5_animals.addActionListener(this);

			b5_resource = view.get5Actions()[5];
			b5_resource.addActionListener(this);

			view.get5Texts()[0].setVisible(true);
			view.get5Texts()[1].setVisible(true);
			view.get5Texts()[2].setVisible(true);
			view.get5Texts()[3].setVisible(true);

		}

		b_room = view.getActions()[0];
		b_room.addActionListener(this);

		b_start = view.getActions()[1];
		b_start.addActionListener(this);

		b_grain = view.getActions()[2];
		b_grain.addActionListener(this);

		b_field = view.getActions()[3];
		b_field.addActionListener(this);

		b_stable = view.getActions()[4];
		b_stable.addActionListener(this);

		b_day = view.getActions()[5];
		b_day.addActionListener(this);

		b_wood = view.getActions()[6];
		b_wood.addActionListener(this);

		b_clay = view.getActions()[7];
		b_clay.addActionListener(this);

		b_reed = view.getActions()[8];
		b_reed.addActionListener(this);

		b_food = view.getActions()[9];
		b_food.addActionListener(this);

		b_sheep = view.getActions()[10];
		b_sheep.addActionListener(this);

		b_sow = view.getActions()[11];
		b_sow.setVisible(false);
		b_sow.addActionListener(this);

		b_fences = view.getActions()[12];
		b_fences.setVisible(false);
		b_fences.addActionListener(this);

		b_improve = view.getActions()[13];
		b_improve.setVisible(false);
		b_improve.addActionListener(this);

		// b_growth, b_renov, b_boar, b_vege, b_cattle, b_stone2, b_growth2,
		// b_fieldsow, b_renov2;

		b_stone = view.getActions()[14];
		b_stone.setVisible(false);
		b_stone.addActionListener(this);

		b_growth = view.getActions()[15];
		b_growth.setVisible(false);
		b_growth.addActionListener(this);

		b_renov = view.getActions()[16];
		b_renov.setVisible(false);
		b_renov.addActionListener(this);

		b_boar = view.getActions()[17];
		b_boar.setVisible(false);
		b_boar.addActionListener(this);

		b_vege = view.getActions()[18];
		b_vege.setVisible(false);
		b_vege.addActionListener(this);

		b_cattle = view.getActions()[19];
		b_cattle.setVisible(false);
		b_cattle.addActionListener(this);

		b_stone2 = view.getActions()[20];
		b_stone2.setVisible(false);
		b_stone2.addActionListener(this);

		b_growth2 = view.getActions()[21];
		b_growth2.setVisible(false);
		b_growth2.addActionListener(this);

		b_fieldsow = view.getActions()[22];
		b_fieldsow.setVisible(false);
		b_fieldsow.addActionListener(this);

		b_renov2 = view.getActions()[23];
		b_renov2.setVisible(false);
		b_renov2.addActionListener(this);

		mplayer1 = view.getPlayer1();
		mplayer1.addActionListener(this);

		mplayer2 = view.getPlayer2();
		mplayer2.addActionListener(this);

		mplayer3 = view.getPlayer3();
		mplayer3.addActionListener(this);

		mplayer4 = view.getPlayer4();
		mplayer4.addActionListener(this);

		mplayer5 = view.getPlayer5();
		mplayer5.addActionListener(this);

		view.setVisible(true);
		view.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int r = 0; r < 7; r++) {
			for (int col = 0; col < 11; col++) {
				if (b[r][col].isEnabled()) {
					b[r][col].addActionListener(this);
					b[r][col].addMouseListener(this);
				}
			}
		}

		total = new float[5];
		sold = new int[5];
		for (int count = 0; count <= 4; count++) {
			total[count] = 0;
			sold[count] = 0;

		}

	}
	
	public AgricolaController() {
		num_players = 0;

		players = new Player[5];
		farm = new Space[5][7][11];

		debug_mode = false;
		
		// player = new Player();
		view = new FarmView();

		Object[] possibilities = { "Human", "Computer - Normal",
				"Computer - Hard" };
		String s = (String) JOptionPane.showInputDialog(null,
				"Please Select Player 1", "Setup Game",
				JOptionPane.PLAIN_MESSAGE, null, possibilities, "Human");
		if (s.equals("Human"))
			num_players++;

		Object[] possibilities2 = { "Human", "Computer - Normal",
				"Computer - Hard", "No More Players" };

		s = (String) JOptionPane.showInputDialog(null,
				"Please Select Player 2", "Setup Game",
				JOptionPane.PLAIN_MESSAGE, null, possibilities2, "Human");
		if (!s.equals("No More Players")) {
			if (s.equals("Human"))
				num_players++;

			s = (String) JOptionPane.showInputDialog(null,
					"Please Select Player 3", "Setup Game",
					JOptionPane.PLAIN_MESSAGE, null, possibilities2, "Human");
			if (!s.equals("No More Players")) {
				if (s.equals("Human"))
					num_players++;
				s = (String) JOptionPane.showInputDialog(null,
						"Please Select Player 4", "Setup Game",
						JOptionPane.PLAIN_MESSAGE, null, possibilities2,
						"Human");
				if (!s.equals("No More Players")) {
					if (s.equals("Human"))
						num_players++;
					s = (String) JOptionPane.showInputDialog(null,
							"Please Select Player 5", "Setup Game",
							JOptionPane.PLAIN_MESSAGE, null, possibilities2,
							"Human");
					if (!s.equals("No More Players")) {
						if (s.equals("Human"))
							num_players++;

					}
				}
			}
		}

		players[0] = new Player();
		for (int r = 0; r < 7; r++) {
			for (int col = 0; col < 11; col++) {
				farm[0][r][col] = new Space();
				if (r % 2 == 1 && col % 2 == 1) {
					farm[0][r][col].setSquare();
					if ((col == 1 && r == 3) || (col == 1 && r == 5))
						farm[0][r][col].setType('w');
				}
				if (r % 2 == 0 && col % 2 == 0)
					farm[0][r][col].setValid();

			}

		}

		if (num_players > 1) {
			players[1] = new Player();
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[1][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[1][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[1][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[1][r][col].setValid();

				}

			}

		}
		if (num_players > 2) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[2][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[2][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[2][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[2][r][col].setValid();

				}

			}
			players[2] = new Player();
		}
		if (num_players > 3) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[3][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[3][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[3][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[3][r][col].setValid();

				}

			}
			players[3] = new Player();
		}
		if (num_players > 4) {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					farm[4][r][col] = new Space();
					if (r % 2 == 1 && col % 2 == 1) {
						farm[4][r][col].setSquare();
						if ((col == 1 && r == 3) || (col == 1 && r == 5))
							farm[4][r][col].setType('w');
					}
					if (r % 2 == 0 && col % 2 == 0)
						farm[4][r][col].setValid();

				}

			}
			players[4] = new Player();
		}

		cur_player = 0;
		start_player = 0;
		view_player = 0;
		b = view.getButtons();
		game = 1;
		wood = 3;
		clay = 1;
		reed = 1;
		food = 1;
		sheep = 1;
		stone = 1;
		stone2 = 1;
		boar = 1;
		cattle = 1;
		turn = 1;

		if (num_players == 3) {
			clay_3p = 1;
			wood2_3p = 1;

			view.get3Actions()[0].setVisible(true);
			view.get3Actions()[1].setVisible(true);
			view.get3Actions()[2].setVisible(true);
			view.get3Actions()[3].setVisible(true);

			b3_clay = view.get3Actions()[0];
			b3_clay.addActionListener(this);

			b3_2wood = view.get3Actions()[1];
			b3_2wood.addActionListener(this);

			b3_resource = view.get3Actions()[2];
			b3_resource.addActionListener(this);

			b3_resource2 = view.get3Actions()[3];
			b3_resource2.addActionListener(this);

			view.get3Texts()[0].setVisible(true);
			view.get3Texts()[1].setVisible(true);

		}

		else if (num_players == 4) {
			wood_4p = 1;
			wood2_4p = 2;
			clay2_4p = 2;
			food_4p = 1;

			view.get4Actions()[0].setVisible(true);
			view.get4Actions()[1].setVisible(true);
			view.get4Actions()[2].setVisible(true);
			view.get4Actions()[3].setVisible(true);
			view.get4Actions()[4].setVisible(true);
			view.get4Actions()[5].setVisible(true);

			b4_wood = view.get4Actions()[0];
			b4_wood.addActionListener(this);

			b4_2clay = view.get4Actions()[1];
			b4_2clay.addActionListener(this);

			b4_2wood = view.get4Actions()[2];
			b4_2wood.addActionListener(this);

			b4_food = view.get4Actions()[3];
			b4_food.addActionListener(this);

			b4_resource = view.get4Actions()[4];
			b4_resource.addActionListener(this);

			b4_3resource = view.get4Actions()[5];
			b4_3resource.addActionListener(this);

			view.get4Texts()[0].setVisible(true);
			view.get4Texts()[1].setVisible(true);
			view.get4Texts()[2].setVisible(true);
			view.get4Texts()[3].setVisible(true);
		} else if (num_players == 5) {
			wood4_5p = 5;
			clay3_5p = 3;
			food_5p = 1;
			reed_5p = 1;

			view.get5Actions()[0].setVisible(true);
			view.get5Actions()[1].setVisible(true);
			view.get5Actions()[2].setVisible(true);
			view.get5Actions()[3].setVisible(true);
			view.get5Actions()[4].setVisible(true);
			view.get5Actions()[5].setVisible(true);

			b5_4wood = view.get5Actions()[0];
			b5_4wood.addActionListener(this);

			b5_3clay = view.get5Actions()[1];
			b5_3clay.addActionListener(this);

			b5_roomfood = view.get5Actions()[2];
			b5_roomfood.addActionListener(this);

			b5_reed = view.get5Actions()[3];
			b5_reed.addActionListener(this);

			b5_animals = view.get5Actions()[4];
			b5_animals.addActionListener(this);

			b5_resource = view.get5Actions()[5];
			b5_resource.addActionListener(this);

			view.get5Texts()[0].setVisible(true);
			view.get5Texts()[1].setVisible(true);
			view.get5Texts()[2].setVisible(true);
			view.get5Texts()[3].setVisible(true);

		}

		b_room = view.getActions()[0];
		b_room.addActionListener(this);

		b_start = view.getActions()[1];
		b_start.addActionListener(this);

		b_grain = view.getActions()[2];
		b_grain.addActionListener(this);

		b_field = view.getActions()[3];
		b_field.addActionListener(this);

		b_stable = view.getActions()[4];
		b_stable.addActionListener(this);

		b_day = view.getActions()[5];
		b_day.addActionListener(this);

		b_wood = view.getActions()[6];
		b_wood.addActionListener(this);

		b_clay = view.getActions()[7];
		b_clay.addActionListener(this);

		b_reed = view.getActions()[8];
		b_reed.addActionListener(this);

		b_food = view.getActions()[9];
		b_food.addActionListener(this);

		b_sheep = view.getActions()[10];
		b_sheep.addActionListener(this);

		b_sow = view.getActions()[11];
		b_sow.setVisible(false);
		b_sow.addActionListener(this);

		b_fences = view.getActions()[12];
		b_fences.setVisible(false);
		b_fences.addActionListener(this);

		b_improve = view.getActions()[13];
		b_improve.setVisible(false);
		b_improve.addActionListener(this);

		// b_growth, b_renov, b_boar, b_vege, b_cattle, b_stone2, b_growth2,
		// b_fieldsow, b_renov2;

		b_stone = view.getActions()[14];
		b_stone.setVisible(false);
		b_stone.addActionListener(this);

		b_growth = view.getActions()[15];
		b_growth.setVisible(false);
		b_growth.addActionListener(this);

		b_renov = view.getActions()[16];
		b_renov.setVisible(false);
		b_renov.addActionListener(this);

		b_boar = view.getActions()[17];
		b_boar.setVisible(false);
		b_boar.addActionListener(this);

		b_vege = view.getActions()[18];
		b_vege.setVisible(false);
		b_vege.addActionListener(this);

		b_cattle = view.getActions()[19];
		b_cattle.setVisible(false);
		b_cattle.addActionListener(this);

		b_stone2 = view.getActions()[20];
		b_stone2.setVisible(false);
		b_stone2.addActionListener(this);

		b_growth2 = view.getActions()[21];
		b_growth2.setVisible(false);
		b_growth2.addActionListener(this);

		b_fieldsow = view.getActions()[22];
		b_fieldsow.setVisible(false);
		b_fieldsow.addActionListener(this);

		b_renov2 = view.getActions()[23];
		b_renov2.setVisible(false);
		b_renov2.addActionListener(this);

		mplayer1 = view.getPlayer1();
		mplayer1.addActionListener(this);

		mplayer2 = view.getPlayer2();
		mplayer2.addActionListener(this);

		mplayer3 = view.getPlayer3();
		mplayer3.addActionListener(this);

		mplayer4 = view.getPlayer4();
		mplayer4.addActionListener(this);

		mplayer5 = view.getPlayer5();
		mplayer5.addActionListener(this);

		view.setVisible(true);
		view.setDefaultCloseOperation(EXIT_ON_CLOSE);

		for (int r = 0; r < 7; r++) {
			for (int col = 0; col < 11; col++) {
				if (b[r][col].isEnabled()) {
					b[r][col].addActionListener(this);
					b[r][col].addMouseListener(this);
				}
			}
		}

		total = new float[5];
		sold = new int[5];
		for (int count = 0; count <= 4; count++) {
			total[count] = 0;
			sold[count] = 0;

		}

	}

	public void mouseEntered(MouseEvent e) {
		// seat_text=seating[((JButton)e.getSource()).location().y/17][((JButton)e.getSource()).location().x/18];
		// view.getTexts()[4].setText(Character.toString(seat_text.getSection()));
		// view.getTexts()[5].setText(Character.toString(seat_text.getRow()));
		// view.getTexts()[6].setText(Character.toString(seat_text.getNumber()));
		// view.getTexts()[7].setText(seat_text.getPricing(seat_text.getSection()-49));
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof JButton) {

			
			if (e.getActionCommand()
					.equals("Build rooms [5 wood/clay/stone and 2 reed] and/or build stables [2 wood]")) {

				Object roomorstable[] = { "Build Room", "Build Stable" };

				int selectedValue = JOptionPane.showOptionDialog(null,
						"Take Resource", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, roomorstable, roomorstable[0]);

				if (selectedValue == 0) {

					if (players[cur_player].getReed() < 2)
						JOptionPane.showMessageDialog(null, "Not enough reed",
								"insufficient resources",
								JOptionPane.ERROR_MESSAGE);

					else if (players[cur_player].getRoomType() == 'w') {
						if (players[cur_player].getWood() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough wood",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						else
							wRoom = true;

					}

					else if (players[cur_player].getRoomType() == 'c') {
						if (players[cur_player].getClay() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough clay",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						else
							wRoom = true;

					}

					else {
						if (players[cur_player].getStone() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough stone",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						else
							wRoom = true;

					}

				}

				else {
					if (players[cur_player].getWood() < 2)
						JOptionPane.showMessageDialog(null, "Not enough wood",
								"insufficient resources",
								JOptionPane.ERROR_MESSAGE);
					else
						wStable = true;
				}

			}

			else if (e.getActionCommand().equals(
					"Starting Player and take 1 Food")) {
				b_start.setVisible(false);
				players[cur_player].addFood(1);
				players[cur_player].useFam();
				start_player = cur_player;

				update(false);

			}

			else if (e.getActionCommand().equals("Take 1 Grain")) {
				b_grain.setVisible(false);
				players[cur_player].addGrain();
				players[cur_player].useFam();

				update(false);
			}

			else if (e.getActionCommand().equals("Plow 1 Field")) {
				wField = true;

			}

			else if (e.getActionCommand().equals(
					"Build 1 Stable [1 wood] and/or Bake Bread")) {
				Object stabbread[] = { "Build Stable", "Bake Bread" };

				int selectedValue = JOptionPane.showOptionDialog(null,
						"Choose Action", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, stabbread, stabbread[0]);

				if (selectedValue == 0)
					if (players[cur_player].getWood() > 0)
						wStableRoom = true;
					else {
						players[cur_player].useFam();
						update(false);
					}

			} else if (e.getActionCommand().equals(
					"Take 1 food and 1 wood, clay, reed, or stone")) {
				b_day.setVisible(false);
				players[cur_player].addFood(food);
				players[cur_player].useFam();

				Object[] possibleValues = { "Wood", "Clay", "Reed" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Take Resource", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, possibleValues, possibleValues[0]);

				if (selectedValue == 0) {
					players[cur_player].addWood(1);
				} else if (selectedValue == 1) {
					players[cur_player].addClay(1);
				} else
					players[cur_player].addReed(1);

				update(false);

			} else if (e.getActionCommand().equals("Take wood (+3 per round)")) {
				b_wood.setVisible(false);
				players[cur_player].addWood(wood);
				wood = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals("Take clay (+1 per round)")) {
				b_clay.setVisible(false);
				players[cur_player].addClay(clay);
				clay = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals("Take reed (+1 per round)")) {
				b_reed.setVisible(false);
				players[cur_player].addReed(reed);
				reed = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals("Take food (+1 per round)")) {
				b_food.setVisible(false);
				players[cur_player].addFood(food);
				food = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals("Take sheep (+1 per round)")) {
				
				b_sheep.setVisible(false);
				players[cur_player].addSheep(sheep);
				sheep = 0;
				players[cur_player].useFam();
				update(false);
				if (players[cur_player].getTotalAnimals() > players[cur_player].getMaxAnimalCount())
					consume();

				update(false);
			} else if (e.getActionCommand().equals("Sow and/or Bake bread")) {
				int selectedValue = 0;
				boolean tempcontinue;
				Object[] possibleValues = { "Sow Grain", "Sow Vegetable",
						"Bake Bread", "Done Action" };
				while (selectedValue != 3) {

					selectedValue = JOptionPane.showOptionDialog(null,
							"Choose Action", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, possibleValues,
							possibleValues[0]);

					tempcontinue = true;
					if (selectedValue == 0) {
						if (players[cur_player].getGrain() > 0) {

							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'f'
											&& farm[cur_player][r][col]
													.getStack() == 0
											&& tempcontinue) {
										farm[cur_player][r][col].setStack(3);
										farm[cur_player][r][col].setType('g');
										players[cur_player].decGrain();
										tempcontinue = false;
									}
								}
							}
						}

					} else if (selectedValue == 1) {
						if (players[cur_player].getVege() > 0) {

							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'f'
											&& farm[cur_player][r][col]
													.getStack() == 0
											&& tempcontinue) {
										farm[cur_player][r][col].setStack(2);
										farm[cur_player][r][col].setType('v');
										players[cur_player].decVege();
										tempcontinue = false;
									}
								}
							}
						}
					} else if (selectedValue == 2) {

					}

					else {

					}
				}
				players[cur_player].useFam();
				b_sow.setVisible(false);
				update(false);

			}
			else if (e.getActionCommand().equals(
					"Build Fences [1 wood per fence] (must enclose pasture)")) {
				int retFence = buildFencesAction();
				switch (retFence) {
				case 0:
					players[cur_player].useFam();
					JOptionPane
					.showMessageDialog(
							null,
							"Click on the valid (light blue) spots on the farm to build fences",
							"Build fences", JOptionPane.INFORMATION_MESSAGE);
					break;
				case 1:
					JOptionPane
					.showMessageDialog(
							null,
							"You do not have enough wood to build fences",
							"No wood", JOptionPane.ERROR_MESSAGE);
					break;
				case 2:
					JOptionPane
					.showMessageDialog(
							null,
							"You already have the maximum amount of fences allowed",
							"Max fences", JOptionPane.ERROR_MESSAGE);
					break;
				}

			} else if (e.getActionCommand().equals("Buy 1 Major Improvement")) {
			} else if (e.getActionCommand().equals(
					"Take 1 stone (+1 per round)")) {
				b_stone.setVisible(false);
				players[cur_player].addStone(stone);
				stone = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals(
					"Family Growth (after round)")) {
				if (players[cur_player].getRooms() == players[cur_player]
						.getFamily())
					JOptionPane
							.showMessageDialog(
									null,
									"You must have an unoccupied room to grow your family",
									"No room", JOptionPane.ERROR_MESSAGE);
				else {

					players[cur_player].addFamily();
					players[cur_player].useFam();

					update(false);
				}

			} else if (e
					.getActionCommand()
					.equals("Renovate [1 reed plus 1 clay/stone per room] also 1 Major Improvement")) {
				if (players[cur_player].getReed() > 0) {

					if (farm[cur_player][3][1].getType() == 'w') {
						if (players[cur_player].getClay() < players[cur_player]
								.getRooms()) {
							JOptionPane.showMessageDialog(null,
									"Not enough Clay",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						} else {
							players[cur_player].useFam();
							b_renov.setVisible(false);
							players[cur_player].setRoomType('c');
							players[cur_player].addClay(-(players[cur_player]
									.getRooms()));
							players[cur_player].addReed(-1);
							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'w')
										farm[cur_player][r][col].setType('c');
								}
							}
						}
					} else if (farm[cur_player][3][1].getType() == 'c') {
						if (players[cur_player].getStone() < players[cur_player]
								.getRooms()) {
							JOptionPane.showMessageDialog(null,
									"Not enough Stone",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						} else {
							players[cur_player].setRoomType('s');
							players[cur_player].useFam();
							b_renov.setVisible(false);

							players[cur_player].addStone(-(players[cur_player]
									.getRooms()));
							players[cur_player].addReed(-1);
							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'c')
										farm[cur_player][r][col].setType('s');
								}

							}
						}
					}
					b_renov.setVisible(false);
					update(false);
				} else
					JOptionPane
							.showMessageDialog(null, "Not enough reed",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
			} else if (e.getActionCommand().equals(
					"Take wild boar (+1 per turn)")) {
				
				b_boar.setVisible(false);
				players[cur_player].addBoar(boar);
				boar = 0;
				players[cur_player].useFam();
				update(false);
				if (players[cur_player].getTotalAnimals() > players[cur_player].getMaxAnimalCount())
					consume();

				update(false);
			} else if (e.getActionCommand().equals("Take 1 vegetable")) {
				b_vege.setVisible(false);
				players[cur_player].addVege();
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals("Take cattle (+1 per turn)")) {
				b_cattle.setVisible(false);
				players[cur_player].addCattle(cattle);
				cattle = 0;
				players[cur_player].useFam();
				update(false);
				if (players[cur_player].getTotalAnimals() > players[cur_player].getMaxAnimalCount())
					consume();

				update(false);

			} else if (e.getActionCommand().equals("Take stone(+1 per turn)")) {
				b_stone2.setVisible(false);
				players[cur_player].addStone(stone);
				stone2 = 0;
				players[cur_player].useFam();

				update(false);
			} else if (e.getActionCommand().equals(
					"Family Growth (even without space in your home)")) {
				b_growth2.setVisible(false);
				players[cur_player].addFamily();
				players[cur_player].useFam();

				update(false);

			} else if (e.getActionCommand().equals("Plow 1 Field and/or Sow")) {
				Object plowchoice[] = { "Plow(Select First)", "Sow Grain",
						"Sow Vegetable", "Done Action" };
				boolean tempcontinue;
				int selectedValue = 0;
				while (selectedValue != 3) {

					selectedValue = JOptionPane.showOptionDialog(null,
							"Choose Action", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, plowchoice,
							plowchoice[0]);

					if (selectedValue == 0) {
						wField2 = true;
						break;
					}

					else if (selectedValue == 1) {
						tempcontinue = true;
						if (players[cur_player].getGrain() > 0) {

							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'f'
											&& farm[cur_player][r][col]
													.getStack() == 0
											&& tempcontinue) {
										farm[cur_player][r][col].setStack(3);
										farm[cur_player][r][col].setType('g');
										players[cur_player].decGrain();
										tempcontinue = false;
									}
								}
							}
						}

					} else if (selectedValue == 2) {
						tempcontinue = true;
						if (players[cur_player].getVege() > 0) {

							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'f'
											&& farm[cur_player][r][col]
													.getStack() == 0
											&& tempcontinue) {
										farm[cur_player][r][col].setStack(2);
										farm[cur_player][r][col].setType('v');
										players[cur_player].decVege();
										tempcontinue = false;
									}
								}
							}
						}
					}

				}

			} else if (e
					.getActionCommand()
					.equals("Renovate (1 reed plus 1 clay/stone per room) also Fences [1 wood]")) {
				if (players[cur_player].getReed() > 0) {

					if (farm[cur_player][3][1].getType() == 'w') {
						if (players[cur_player].getClay() < players[cur_player]
								.getRooms()) {
							JOptionPane.showMessageDialog(null,
									"Not enough Clay",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						} else {
							players[cur_player].useFam();
							b_renov.setVisible(false);
							players[cur_player].setRoomType('c');
							players[cur_player].addClay(-(players[cur_player]
									.getRooms()));
							players[cur_player].addReed(-1);
							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'w')
										farm[cur_player][r][col].setType('c');
								}
							}
						}
					} else if (farm[cur_player][3][1].getType() == 'c') {
						if (players[cur_player].getStone() < players[cur_player]
								.getRooms()) {
							JOptionPane.showMessageDialog(null,
									"Not enough Stone",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);
						} else {
							players[cur_player].useFam();
							b_renov.setVisible(false);
							players[cur_player].setRoomType('s');
							players[cur_player].addStone(-(players[cur_player]
									.getRooms()));
							players[cur_player].addReed(-1);
							for (int r = 0; r < 7; r++) {
								for (int col = 0; col < 11; col++) {

									if (farm[cur_player][r][col].getType() == 'c')
										farm[cur_player][r][col].setType('s');
								}

							}
						}
					}
					b_renov2.setVisible(false);
					update(false);
				} else
					JOptionPane
							.showMessageDialog(null, "Not enough reed",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);

			}

			// 3 player only commands

			else if (e.getActionCommand().equals("(3p) Clay (+1 per round)")) {
				// clay_3p.setVisible(false);
				b3_clay.setVisible(false);
				players[cur_player].addClay(clay_3p);
				clay_3p = 0;
				players[cur_player].useFam();

				update(false);

			} else if (e.getActionCommand().equals("(3p) Wood (+2 per round)")) {
				b3_2wood.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addWood(wood2_3p);
				wood2_3p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(3p) 1 Building Resource")) {
				b3_resource.setVisible(false);
				players[cur_player].useFam();

				Object[] possibleValues4 = { "Wood", "Clay", "Reed", "Stone" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Take Resource", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, possibleValues4, possibleValues4[0]);

				if (selectedValue == 0) {
					players[cur_player].addWood(1);
				} else if (selectedValue == 2) {
					players[cur_player].addClay(1);
				} else if (selectedValue == 3)
					players[cur_player].addReed(1);
				else
					players[cur_player].addStone(1);

				update(false);

			}

			else if (e.getActionCommand().equals(
					"(3p) 2 Different Building Resources")) {
				b3_resource2.setVisible(false);
				players[cur_player].useFam();

				Object[] possibleValues4 = { "Wood", "Clay", "Reed", "Stone" };
				Object[] possibleValuesStone = { "Wood", "Clay", "Reed" };
				Object[] possibleValuesClay = { "Wood", "Reed", "Stone" };
				Object[] possibleValuesReed = { "Wood", "Clay", "Stone" };
				Object[] possibleValuesWood = { "Clay", "Reed", "Stone" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Take Resource", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, possibleValues4, possibleValues4[0]);

				if (selectedValue == 0) {
					players[cur_player].addWood(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesWood, possibleValuesWood[0]);
					if (selectedValue == 0) {
						players[cur_player].addClay(1);
					} else if (selectedValue == 1)
						players[cur_player].addReed(1);
					else
						players[cur_player].addStone(1);

				} else if (selectedValue == 1) {
					players[cur_player].addClay(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesClay, possibleValuesClay[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addReed(1);
					else
						players[cur_player].addStone(1);

				} else if (selectedValue == 2) {

					players[cur_player].addReed(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesReed, possibleValuesReed[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addClay(1);
					else
						players[cur_player].addStone(1);

				} else {

					players[cur_player].addStone(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesStone, possibleValuesStone[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addClay(1);
					else
						players[cur_player].addReed(1);

				}

				update(false);

			}

			else if (e.getActionCommand().equals("(4p) Wood (+1 per round)")) {
				b4_wood.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addWood(wood_4p);
				wood_4p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(4p) Clay (+2 per round)")) {
				b4_2clay.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addClay(clay2_4p);
				clay2_4p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(4p) Wood (+2 per round)")) {
				b4_2wood.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addWood(wood2_4p);
				wood2_4p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(4p) Food (+1 per round)")) {
				b4_food.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addFood(food_4p);
				food_4p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(4p) 1 reed, stone & food")) {
				b4_3resource.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addReed(1);
				players[cur_player].addStone(1);
				players[cur_player].addFood(1);
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals(
					"(4p) 2 Different Building Resources")) {
				b4_resource.setVisible(false);
				players[cur_player].useFam();

				Object[] possibleValues4 = { "Wood", "Clay", "Reed", "Stone" };
				Object[] possibleValuesStone = { "Wood", "Clay", "Reed" };
				Object[] possibleValuesClay = { "Wood", "Reed", "Stone" };
				Object[] possibleValuesReed = { "Wood", "Clay", "Stone" };
				Object[] possibleValuesWood = { "Clay", "Reed", "Stone" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Take Resource", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, possibleValues4, possibleValues4[0]);

				if (selectedValue == 0) {
					players[cur_player].addWood(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesWood, possibleValuesWood[0]);
					if (selectedValue == 0) {
						players[cur_player].addClay(1);
					} else if (selectedValue == 1)
						players[cur_player].addReed(1);
					else
						players[cur_player].addStone(1);

				} else if (selectedValue == 1) {
					players[cur_player].addClay(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesClay, possibleValuesClay[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addReed(1);
					else
						players[cur_player].addStone(1);

				} else if (selectedValue == 2) {

					players[cur_player].addReed(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesReed, possibleValuesReed[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addClay(1);
					else
						players[cur_player].addStone(1);

				} else {

					players[cur_player].addStone(1);

					selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null,
							possibleValuesStone, possibleValuesStone[0]);
					if (selectedValue == 0) {
						players[cur_player].addWood(1);
					} else if (selectedValue == 1)
						players[cur_player].addClay(1);
					else
						players[cur_player].addReed(1);

				}

				update(false);

			}

			// ***********************
			// 5 FAMILY MEMBER ACTIONS
			// ***********************

			else if (e.getActionCommand().equals("(5p) Wood (+4 per round)")) {
				b5_4wood.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addWood(wood4_5p);
				wood4_5p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals("(5p) Clay (+3 per round)")) {
				b5_3clay.setVisible(false);
				// wood2_3p.setVisible(false);
				players[cur_player].addClay(clay3_5p);
				clay3_5p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals(
					"(5p) Build 1 room or take Food (+1 per round)")) {
				// b5_roomfood.setVisible(false);

				Object[] possibleValuesRoomFood = { "Build 1 Room", "Take Food" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Action", "Choose one", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null,
						possibleValuesRoomFood, possibleValuesRoomFood);
				if (selectedValue == 0) {
					if (players[cur_player].getReed() < 2)
						JOptionPane.showMessageDialog(null, "Not enough reed",
								"insufficient resources",
								JOptionPane.ERROR_MESSAGE);

					else if (players[cur_player].getRoomType() == 'w') {
						if (players[cur_player].getWood() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough wood",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);

					}

					else if (players[cur_player].getRoomType() == 'c') {
						if (players[cur_player].getClay() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough clay",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);

					}

					else {
						if (players[cur_player].getStone() < 5)
							JOptionPane.showMessageDialog(null,
									"Not enough stone",
									"insufficient resources",
									JOptionPane.ERROR_MESSAGE);

					}

					// players[cur_player].useFam();
					//
					// update(false)

				}

				else {
					b5_roomfood.setVisible(false);
					players[cur_player].addFood(food_5p);
					food_5p = 0;
					players[cur_player].useFam();

					update(false);
				}

			}

			else if (e.getActionCommand().equals(
					"(5p) Take Reed (+1 per round) & 1 stone + 1 wood")) {

				b5_reed.setVisible(false);

				players[cur_player].addReed(reed_5p);
				players[cur_player].addStone(1);
				players[cur_player].addWood(1);
				reed_5p = 0;
				players[cur_player].useFam();

				update(false);

			}

			else if (e.getActionCommand().equals(
					"(5p) Take Animal - sheep gives 1 food, cattle costs 1")) {
				Object[] possibleValuesAnimals = { "Sheep+Food", "Boar",
						"Cattle-Food" };
				int selectedValue = JOptionPane.showOptionDialog(null,
						"Choose Animal", "Choose one",
						JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
						null, possibleValuesAnimals, possibleValuesAnimals);

				if (selectedValue == 0) {
					b5_animals.setVisible(false);

					players[cur_player].useFam();
					players[cur_player].addSheep(1);
					players[cur_player].addFood(1);

					update(false);
				} else if (selectedValue == 1) {
					b5_animals.setVisible(false);

					players[cur_player].useFam();
					players[cur_player].addBoar(1);

					update(false);
				} else {
					if (players[cur_player].getFood() < 1)
						JOptionPane.showMessageDialog(null, "Not enough Food",
								"Need 1 Food", JOptionPane.ERROR_MESSAGE);
					else {
						b5_animals.setVisible(false);

						players[cur_player].useFam();
						players[cur_player].addCattle(1);
						players[cur_player].addFood(-1);

						update(false);
					}
				}

			}

			else if (e.getActionCommand().equals(
					"(5p) 2 Different Building Resources or Family Growth")) {

				Object[] possibleChoice2 = { "Resources", "Growth" };
				int selectedValue2 = JOptionPane.showOptionDialog(null,
						"Choose", "Choose one", JOptionPane.DEFAULT_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, possibleChoice2,
						possibleChoice2[0]);

				if (selectedValue2 == 0) {
					b5_resource.setVisible(false);
					players[cur_player].useFam();

					update(false);

					Object[] possibleValues4 = { "Wood", "Clay", "Reed",
							"Stone" };
					Object[] possibleValuesStone = { "Wood", "Clay", "Reed" };
					Object[] possibleValuesClay = { "Wood", "Reed", "Stone" };
					Object[] possibleValuesReed = { "Wood", "Clay", "Stone" };
					Object[] possibleValuesWood = { "Clay", "Reed", "Stone" };
					int selectedValue = JOptionPane.showOptionDialog(null,
							"Take Resource", "Choose one",
							JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, possibleValues4,
							possibleValues4[0]);

					if (selectedValue == 0) {
						players[cur_player].addWood(1);

						selectedValue = JOptionPane.showOptionDialog(null,
								"Take Resource", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null,
								possibleValuesWood, possibleValuesWood[0]);
						if (selectedValue == 0) {
							players[cur_player].addClay(1);
						} else if (selectedValue == 1)
							players[cur_player].addReed(1);
						else
							players[cur_player].addStone(1);

					} else if (selectedValue == 1) {
						players[cur_player].addClay(1);

						selectedValue = JOptionPane.showOptionDialog(null,
								"Take Resource", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null,
								possibleValuesClay, possibleValuesClay[0]);
						if (selectedValue == 0) {
							players[cur_player].addWood(1);
						} else if (selectedValue == 1)
							players[cur_player].addReed(1);
						else
							players[cur_player].addStone(1);

					} else if (selectedValue == 2) {

						players[cur_player].addReed(1);

						selectedValue = JOptionPane.showOptionDialog(null,
								"Take Resource", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null,
								possibleValuesReed, possibleValuesReed[0]);
						if (selectedValue == 0) {
							players[cur_player].addWood(1);
						} else if (selectedValue == 1)
							players[cur_player].addClay(1);
						else
							players[cur_player].addStone(1);

					} else {

						players[cur_player].addStone(1);

						selectedValue = JOptionPane.showOptionDialog(null,
								"Take Resource", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null,
								possibleValuesStone, possibleValuesStone[0]);
						if (selectedValue == 0) {
							players[cur_player].addWood(1);
						} else if (selectedValue == 1)
							players[cur_player].addClay(1);
						else
							players[cur_player].addReed(1);

					}
				}

				else {
					if (game < 5)
						JOptionPane
								.showMessageDialog(
										null,
										"Growth can only be taken from round 5 onwards",
										"Too Early", JOptionPane.ERROR_MESSAGE);

					else if (players[cur_player].getRooms() == players[cur_player]
							.getFamily())
						JOptionPane
								.showMessageDialog(
										null,
										"You must have an unoccupied room to grow your family",
										"No room", JOptionPane.ERROR_MESSAGE);
					else {

						players[cur_player].addFamily();
						players[cur_player].useFam();

						update(false);
					}
				}
			}

			// **************************************
			// FOLLOW UP BUTTONS TO CLICK ON THE FARM
			// **************************************

			else {
				if (wField) {
					if (farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
							.getSource()).location().x / 118 * 2 + 1].getType() == 'e'
							&& farm[cur_player][((JButton) e.getSource())
									.location().y / 110 * 2 + 1][((JButton) e
									.getSource()).location().x / 118 * 2 + 1]
									.isSquare()) {

						b_field.setVisible(false);
						players[cur_player].useFam();
						players[cur_player].decEmpty(1);

						// b_field.setVisible(false);
						players[cur_player].addField();
						// players[cur_player].useFam();
						// System.out.println((((JButton)e.getSource()).location().y/110)*2+1
						// + " " +
						// (((JButton)e.getSource()).location().x/118)*2+1);
						farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
								.getSource()).location().x / 118 * 2 + 1]
								.setType('f');
						update(false);

					} else {
						wField = false;
					}

				}
				
				if (wFences) {
					handleBuildFarmFence((JButton)e.getSource());
				}

				if (wRoom) {
					if (farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
							.getSource()).location().x / 118 * 2 + 1].getType() == 'e'
							&& farm[cur_player][((JButton) e.getSource())
									.location().y / 110 * 2 + 1][((JButton) e
									.getSource()).location().x / 118 * 2 + 1]
									.isSquare()) {
						players[cur_player].addRooms(1);
						players[cur_player].decEmpty(1);
						farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
								.getSource()).location().x / 118 * 2 + 1]
								.setType(farm[cur_player][3][1].getType());

						players[cur_player].addReed(-2);
						if (farm[cur_player][3][1].getType() == 'w')
							players[cur_player].addWood(-5);
						else if (farm[cur_player][3][1].getType() == 'c')
							players[cur_player].addClay(-5);
						else
							players[cur_player].addStone(-5);

						Object roomdone[] = { "Build Room", "Build Stable",
								"Done Action" };

						int selectedValue = JOptionPane.showOptionDialog(null,
								"Choose Action", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, roomdone,
								roomdone[0]);

						if (selectedValue == 0) {

							if (players[cur_player].getReed() < 2) {
								players[cur_player].useFam();
								update(false);
								JOptionPane.showMessageDialog(null,
										"Not enough reed",
										"insufficient resources",
										JOptionPane.ERROR_MESSAGE);
							}

							else if (players[cur_player].getRoomType() == 'w') {
								if (players[cur_player].getWood() < 5) {

									JOptionPane.showMessageDialog(null,
											"Not enough wood",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
									players[cur_player].useFam();
									update(false);
								}

							}

							else if (players[cur_player].getRoomType() == 'c') {
								if (players[cur_player].getClay() < 5) {

									JOptionPane.showMessageDialog(null,
											"Not enough clay",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
									players[cur_player].useFam();
									update(false);
								}

							}

							else {
								if (players[cur_player].getStone() < 5) {
									players[cur_player].useFam();
									update(false);
									JOptionPane.showMessageDialog(null,
											"Not enough stone",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
								}

							}

						}

						else if (selectedValue == 1) {
							if (players[cur_player].getWood() < 2) {
								players[cur_player].useFam();

								update(false);
								JOptionPane.showMessageDialog(null,
										"Not enough wood",
										"insufficient resources",
										JOptionPane.ERROR_MESSAGE);
							} else {
								wRoom = false;
								wStable = true;
							}
						} else {
							players[cur_player].useFam();
							update(false);
						}

					}

				}

				if (wStable) {
					if (farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
							.getSource()).location().x / 118 * 2 + 1].getType() == 'e'
							&& farm[cur_player][((JButton) e.getSource())
									.location().y / 110 * 2 + 1][((JButton) e
									.getSource()).location().x / 118 * 2 + 1]
									.isSquare()) {
						players[cur_player].addStable(1);
						players[cur_player].decEmpty(1);
						players[cur_player].addWood(-2);
						farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
								.getSource()).location().x / 118 * 2 + 1]
								.setType('m');
						Object stabdone[] = { "Build Room", "Build Stable",
								"Done Action" };

						int selectedValue = JOptionPane.showOptionDialog(null,
								"Choose Action", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, stabdone,
								stabdone[0]);

						if (selectedValue == 0) {

							if (players[cur_player].getReed() < 2) {
								players[cur_player].useFam();
								update(false);
								JOptionPane.showMessageDialog(null,
										"Not enough reed",
										"insufficient resources",
										JOptionPane.ERROR_MESSAGE);
							}

							else if (players[cur_player].getRoomType() == 'w') {
								if (players[cur_player].getWood() < 5) {

									JOptionPane.showMessageDialog(null,
											"Not enough wood",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
									players[cur_player].useFam();
									update(false);
								} else {

									wRoom = true;
									wStable = false;
								}

							}

							else if (players[cur_player].getRoomType() == 'c') {
								if (players[cur_player].getClay() < 5) {

									JOptionPane.showMessageDialog(null,
											"Not enough clay",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
									players[cur_player].useFam();
									update(false);
								} else {

									wRoom = true;
									wStable = false;
								}

							}

							else {
								if (players[cur_player].getStone() < 5) {
									players[cur_player].useFam();
									update(false);
									JOptionPane.showMessageDialog(null,
											"Not enough stone",
											"insufficient resources",
											JOptionPane.ERROR_MESSAGE);
								} else {

									wRoom = true;
									wStable = false;
								}

							}

						}

						else if (selectedValue == 1) {
							if (players[cur_player].getWood() < 2) {
								players[cur_player].useFam();
								update(false);
								JOptionPane.showMessageDialog(null,
										"Not enough wood",
										"insufficient resources",
										JOptionPane.ERROR_MESSAGE);
							} else {
								wRoom = false;
								wStable = true;
							}
						} else {

							players[cur_player].useFam();
							update(false);
						}

					}
				}

				if (wStableRoom) {
					b_stable.setVisible(false);

					if (farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
							.getSource()).location().x / 118 * 2 + 1].getType() == 'e'
							&& farm[cur_player][((JButton) e.getSource())
									.location().y / 110 * 2 + 1][((JButton) e
									.getSource()).location().x / 118 * 2 + 1]
									.isSquare()) {
						players[cur_player].addStable(1);
						players[cur_player].decEmpty(1);
						players[cur_player].useFam();
						players[cur_player].addWood(-1);

						Object breadstab[] = { "Bake Bread", "Done Action" };

						int selectedValue = JOptionPane.showOptionDialog(null,
								"Choose Action", "Choose one",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, breadstab,
								breadstab[0]);

						if (selectedValue == 0) {

							update(false);
						} else {
							update(false);
						}
					}
				}

				if (wField2) {
					if (farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
							.getSource()).location().x / 118 * 2 + 1].getType() == 'e'
							&& farm[cur_player][((JButton) e.getSource())
									.location().y / 110 * 2 + 1][((JButton) e
									.getSource()).location().x / 118 * 2 + 1]
									.isSquare()) {

						b_fieldsow.setVisible(false);
						players[cur_player].useFam();
						players[cur_player].decEmpty(1);

						// b_field.setVisible(false);
						players[cur_player].addField();
						// players[cur_player].useFam();
						// System.out.println((((JButton)e.getSource()).location().y/110)*2+1
						// + " " +
						// (((JButton)e.getSource()).location().x/118)*2+1);
						farm[cur_player][((JButton) e.getSource()).location().y / 110 * 2 + 1][((JButton) e
								.getSource()).location().x / 118 * 2 + 1]
								.setType('f');

						Object plowchoice[] = { "Sow Grain", "Sow Vegetable",
								"Done Action" };
						boolean tempcontinue;
						int selectedValue = 0;
						while (selectedValue != 2) {

							selectedValue = JOptionPane.showOptionDialog(null,
									"Choose Action", "Choose one",
									JOptionPane.DEFAULT_OPTION,
									JOptionPane.PLAIN_MESSAGE, null,
									plowchoice, plowchoice[0]);

							if (selectedValue == 0) {
								tempcontinue = true;
								if (players[cur_player].getGrain() > 0) {

									for (int r = 0; r < 7; r++) {
										for (int col = 0; col < 11; col++) {

											if (farm[cur_player][r][col]
													.getType() == 'f'
													&& farm[cur_player][r][col]
															.getStack() == 0
													&& tempcontinue) {
												farm[cur_player][r][col]
														.setStack(3);
												farm[cur_player][r][col]
														.setType('g');
												players[cur_player].decGrain();
												tempcontinue = false;
											}
										}
									}
								}

							} else if (selectedValue == 1) {
								tempcontinue = true;
								if (players[cur_player].getVege() > 0) {

									for (int r = 0; r < 7; r++) {
										for (int col = 0; col < 11; col++) {

											if (farm[cur_player][r][col]
													.getType() == 'f'
													&& farm[cur_player][r][col]
															.getStack() == 0
													&& tempcontinue) {
												farm[cur_player][r][col]
														.setStack(2);
												farm[cur_player][r][col]
														.setType('v');
												players[cur_player].decVege();
												tempcontinue = false;
											}
										}
									}
								}
							}

						}
						update(false);
					} else {
						wField2 = false;
					}

				}

			}

		}
		// JOptionPane.showMessageDialog(this, "Command: " +
		// e.getActionCommand());
		// if (e.getActionCommand().equals("food"));
		// System.out.print("sup");

		/*
		 * if (e.getSource() instanceof JButton){ if
		 * (e.getActionCommand().equals("Administrator")){ LoginDialogBox
		 * login=new LoginDialogBox(this); login.setVisible(true); if
		 * (login.isAdmin()){ SeatInfoDialogBox info=new SeatInfoDialogBox
		 * (this,sold,total); info.setVisible(true); } }
		 * 
		 * else { if (e.getActionCommand().equals("Purchase")){ String
		 * seatspurchased = ""; int temp=0; for (int r=0;r<27;r++){ for (int
		 * col=0;col<35;col++){ if (b[r][col].getBackground()==Color.black){
		 * seatspurchased = seatspurchased +
		 * Character.toString(seating[r][col].getSection()) +
		 * Character.toString(seating[r][col].getRow()) +
		 * Character.toString(seating[r][col].getNumber()) + " "; temp++; } } }
		 * if (temp==0) JOptionPane.showMessageDialog(null,
		 * "Please choose at least one seat to purchase them.",
		 * "No Seats Chosen", JOptionPane.INFORMATION_MESSAGE); else {
		 * PurchaseDialogBox p = new PurchaseDialogBox(this);
		 * p.setVisible(true); if (p.getPurchase()){ if (game==5)
		 * System.out.print("Receipt:\nSeats Purchased for all games: "+
		 * seatspurchased + "\n" + p + "\n\nTotal Cost: $" +
		 * view.getTexts()[3].getText()+ "\n"); else
		 * System.out.print("Receipt:\nSeats Purchased for game "+ game +": " +
		 * seatspurchased + "\n" + p + "\n\nTotal Cost: $" +
		 * view.getTexts()[3].getText()+ "\n"); total[game-1] = total[game-1] +
		 * Float.valueOf(view.getTexts()[3].getText()); sold[game-1] =
		 * sold[game-1] + temp; update(true); } }
		 * 
		 * } else { JButton selection=(JButton)e.getSource(); Seat
		 * seat=seating[(
		 * (JButton)e.getSource()).location().y/17][((JButton)e.getSource
		 * ()).location().x/18]; float
		 * cost=Float.valueOf(seat.getPricing(seat.getSection()-49)); if
		 * (game==5) cost=(cost*4)*0.9f; if
		 * (selection.getBackground()==Color.black){
		 * changeButton(seat,selection);
		 * view.getTexts()[0].setText(String.format("%.2f",
		 * Float.valueOf(view.getTexts()[0].getText()) - cost)); } else {
		 * selection.setBackground(Color.black);
		 * view.getTexts()[0].setText(String.format("%.2f",
		 * Float.valueOf(view.getTexts()[0].getText()) + cost));
		 * 
		 * } view.getTexts()[1].setText(String.format("%.2f",
		 * Float.valueOf(view.getTexts()[0].getText()) * 0.08));
		 * view.getTexts()[2].setText(String.format("%.2f",
		 * Float.valueOf(view.getTexts()[0].getText()) * 0.05));
		 * view.getTexts()[3].setText(String.format("%.2f",
		 * Float.valueOf(view.getTexts()[0].getText()) +
		 * Float.valueOf(view.getTexts()[1].getText()) +
		 * Float.valueOf(view.getTexts()[2].getText())));
		 * 
		 * }
		 * 
		 * } } else { if (e.getActionCommand().equals("Game 1")){ game=1;
		 * update(false); } if (e.getActionCommand().equals("Game 2")){ game=2;
		 * update(false); } if (e.getActionCommand().equals("Game 3")){ game=3;
		 * update(false); } if (e.getActionCommand().equals("Game 4")){ game=4;
		 * update(false); } if (e.getActionCommand().equals("All Games")){
		 * game=5; update(false); }
		 * 
		 * }
		 */

	}

	public void changeButton(Space s, JButton button) {
		/*
		 * boolean sold = false;
		 * 
		 * if (game == 5){ for (int a=0; a<4; a++){ if (s.getBought()[a]) sold =
		 * true;
		 * 
		 * } } if (sold){ button.setBackground(Color.white);
		 * button.setEnabled(false); } else if (s.getBought()[game-1]){
		 * button.setBackground(Color.white); button.setEnabled(false); } else {
		 * button.setEnabled(true); if (s.getSection()=='1')
		 * button.setBackground(Color.red); if (s.getSection()=='2')
		 * button.setBackground(Color.green); if (s.getSection()=='3')
		 * button.setBackground(Color.blue); if (s.getSection()=='4')
		 * button.setBackground(Color.yellow); }
		 */
	}

	// ******************************************
	// After every action, update is called to change players and process new
	// round changes if everyone is out of family members
	// ******************************************
	public void update(boolean newround) {

		wField = false;
		wRoom = false;
		wRoom2 = false;
		wSow = false;
		wFieldSow = false;
		wFences = false;
		wSheep = false;
		wBoar = false;
		wCattle = false;

		createPastures();
		int sum = 0;
		for (int i = 0; i < num_players; i++)
			sum += players[i].getActiveFamily();

		if (sum == 0) {

			if (turn == 4 || turn == 7 || turn == 9 || turn == 11 || turn == 13
					|| turn == 14) {
				for (int p = 0; p < num_players; p++) {
					for (int r = 0; r < 7; r++) {
						for (int col = 0; col < 11; col++) {

							if (farm[p][r][col].getType() == 'g'
									&& farm[p][r][col].getStack() > 1) {
								farm[p][r][col].decStack();
								if (farm[p][r][col].getStack() == 0)
									farm[p][r][col].setType('f');
								players[p].addGrain();
							}
							if (farm[p][r][col].getType() == 'v'
									&& farm[p][r][col].getStack() > 1) {
								farm[p][r][col].decStack();
								if (farm[p][r][col].getStack() == 0)
									farm[p][r][col].setType('f');
								players[p].addVege();
							}
						}
					}
					int selectedValue = 0;
					Object harvestopt[] = { "Convert Grain",
							"Convert Vegetable", "Convert Sheep",
							"Convert Boar", "Convert Cattle", "Done" };

					while (selectedValue != 5) {

						selectedValue = JOptionPane.showOptionDialog(null,
								"Choose actions before Harvest", "Harvest",
								JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, harvestopt,
								harvestopt[0]);

						if (selectedValue == 0 && players[p].getGrain() > 0) {
							players[p].decGrain();
							players[p].addFood(1);
						} else if (selectedValue == 1
								&& players[p].getVege() > 0) {
							if (players[p].hasOven()) {
								players[p].decVege();
								players[p].addFood(3);

							} else {

								players[p].decVege();
								players[p].addFood(1);
							}

						}

						else if (selectedValue == 2
								&& players[p].getSheep() > 0) {
							if (players[p].hasOven()) {
								players[p].addSheep(-1);
								players[p].addFood(2);

							} else {

								players[p].addSheep(-1);
								players[p].addFood(1);
							}

						}

						else if (selectedValue == 3 && players[p].getBoar() > 0) {
							if (players[p].hasOven()) {
								players[p].addBoar(-1);
								players[p].addFood(3);

							} else {

								players[p].addBoar(-1);
								players[p].addFood(1);
							}

						}

						else if (selectedValue == 4
								&& players[p].getCattle() > 0) {
							if (players[p].hasOven()) {
								players[p].addCattle(-1);
								players[p].addFood(4);

							} else {

								players[p].addCattle(-1);
								players[p].addFood(2);
							}

						}

					}
					if (players[p].hasChild()) {
						players[p].addFood(1);
					}
					if (players[p].getFood() < players[p].getFamily() * 2) {
						players[p].addFood(-(players[p].getFood()));
						players[p]
								.addScore(((players[p].getFamily() * 2) - players[p]
										.getFood()) * 3);

					} else
						players[p].addFood(-(players[p].getFamily() * 2));

					if (players[p].getSheep() > 2)
						players[p].addSheep(1);
					if (players[p].getBoar() > 2)
						players[p].addBoar(1);
					if (players[p].getCattle() > 2)
						players[p].addCattle(1);

					if (turn == 14) {
						players[p].calcScore();

					}

				}

				if (turn == 14) {
					if (num_players == 1) {

						JOptionPane.showMessageDialog(null, "Final Score: "
								+ players[0].getScore());
					}

					if (num_players == 2) {

						JOptionPane.showMessageDialog(
								null,
								"Final Scores\nPlayer1: "
										+ players[0].getScore()
										+ "\nPlayer 2: "
										+ players[1].getScore());
					}
					if (num_players == 3) {

						JOptionPane.showMessageDialog(
								null,
								"Final Scores\nPlayer1: "
										+ players[0].getScore()
										+ "\nPlayer 2: "
										+ players[1].getScore()
										+ "\nPlayer 3: "
										+ players[2].getScore());
					}
					if (num_players == 4) {

						JOptionPane.showMessageDialog(
								null,
								"Final Scores\nPlayer1: "
										+ players[0].getScore()
										+ "\nPlayer 2: "
										+ players[1].getScore()
										+ "\nPlayer 3: "
										+ players[2].getScore()
										+ "\nPlayer 4: "
										+ players[3].getScore());
					}
					if (num_players == 5) {

						JOptionPane.showMessageDialog(
								null,
								"Final Scores\nPlayer1: "
										+ players[0].getScore()
										+ "\nPlayer 2: "
										+ players[1].getScore()
										+ "\nPlayer 3: "
										+ players[2].getScore()
										+ "\nPlayer 4: "
										+ players[3].getScore()
										+ "\nPlayer 5: "
										+ players[4].getScore());
					}

				}

			}

			if (turn > 4)
				stone++;

			if (turn > 7)
				boar++;

			if (turn > 9)
				cattle++;

			if (turn > 10)
				stone2++;

			turn++;
			wood += 3;
			clay++;
			reed++;
			food++;
			sheep++;
			clay_3p++;
			wood2_3p += 2;
			wood_4p++;
			wood2_4p += 2;
			clay2_4p += 2;
			food_4p++;
			wood4_5p += 4;
			clay3_5p += 3;
			food_5p++;
			reed_5p++;

			for (int i = 0; i < num_players; i++) {
				players[i].resetFam();
				if (players[i].hasChild())
					players[i].setChild();
			}

			cur_player = start_player;
			if (num_players == 3) {
				b3_2wood.setVisible(true);
				b3_clay.setVisible(true);
				b3_resource.setVisible(true);
				b3_resource2.setVisible(true);
			} else if (num_players == 4) {
				b4_2clay.setVisible(true);
				b4_2wood.setVisible(true);
				b4_3resource.setVisible(true);
				b4_resource.setVisible(true);
				b4_food.setVisible(true);
				b4_wood.setVisible(true);

			} else if (num_players == 5) {
				b5_3clay.setVisible(true);
				b5_4wood.setVisible(true);
				b5_resource.setVisible(true);
				b5_roomfood.setVisible(true);
				b5_animals.setVisible(true);
				b5_reed.setVisible(true);
			}

			b_room.setVisible(true);
			b_start.setVisible(true);
			b_grain.setVisible(true);
			b_field.setVisible(true);
			b_stable.setVisible(true);
			b_day.setVisible(true);
			b_wood.setVisible(true);
			b_clay.setVisible(true);
			b_reed.setVisible(true);
			b_food.setVisible(true);
			b_sheep.setVisible(true);
			if (turn > 1)
				b_sow.setVisible(true);
			if (turn > 2)
				b_fences.setVisible(true);
			if (turn > 3)
				b_improve.setVisible(true);
			if (turn > 4) {
				view.getGameTexts()[6].setVisible(true);
				b_stone.setVisible(true);
			}
			if (turn > 5)
				b_growth.setVisible(true);
			if (turn > 6)
				b_renov.setVisible(true);
			if (turn > 7) {
				view.getGameTexts()[7].setVisible(true);
				b_boar.setVisible(true);
			}
			if (turn > 8)
				b_vege.setVisible(true);
			if (turn > 9) {
				view.getGameTexts()[8].setVisible(true);
				b_cattle.setVisible(true);
			}
			if (turn > 10) {
				view.getGameTexts()[9].setVisible(true);
				b_stone2.setVisible(true);
			}
			if (turn > 11)
				b_growth2.setVisible(true);

			if (turn > 12)
				b_fieldsow.setVisible(true);

			if (turn > 13)
				b_renov2.setVisible(true);
		}

		// skips players with no remaining family members
		else {

			cur_player++;
			if (cur_player == num_players)
				cur_player = 0;
			while (players[cur_player].getActiveFamily() <= 0) {
				cur_player++;
				if (cur_player == num_players)
					cur_player = 0;
			}
		}

		view_player = cur_player;

		view.getPlayerTexts()[0].setText(Integer.toString(players[cur_player]
				.getFood()));
		view.getPlayerTexts()[1].setText(Integer.toString(players[cur_player]
				.getReed()));
		view.getPlayerTexts()[2].setText(Integer.toString(players[cur_player]
				.getWood()));
		view.getPlayerTexts()[3].setText(Integer.toString(players[cur_player]
				.getClay()));
		view.getPlayerTexts()[4].setText(Integer.toString(players[cur_player]
				.getStone()));
		view.getPlayerTexts()[5].setText(Integer.toString(players[cur_player]
				.getGrain()));
		view.getPlayerTexts()[6].setText(Integer.toString(players[cur_player]
				.getVege()));
		view.getPlayerTexts()[7].setText(Integer.toString(players[cur_player]
				.getFamily()));
		view.getPlayerTexts()[8].setText(Integer.toString(players[cur_player]
				.getActiveFamily()));
		view.getPlayerTexts()[9].setText(Integer.toString(players[cur_player]
				.getSheep()));
		view.getPlayerTexts()[10].setText(Integer.toString(players[cur_player]
				.getBoar()));
		view.getPlayerTexts()[11].setText(Integer.toString(players[cur_player]
				.getCattle()));

		view.getGameTexts()[0].setText(Integer.toString(turn));
		view.getGameTexts()[1].setText(Integer.toString(wood));
		view.getGameTexts()[2].setText(Integer.toString(clay));
		view.getGameTexts()[3].setText(Integer.toString(reed));
		view.getGameTexts()[4].setText(Integer.toString(food));
		view.getGameTexts()[5].setText(Integer.toString(sheep));
		view.getGameTexts()[6].setText(Integer.toString(stone));
		view.getGameTexts()[7].setText(Integer.toString(boar));
		view.getGameTexts()[8].setText(Integer.toString(cattle));
		view.getGameTexts()[9].setText(Integer.toString(stone2));
		view.getGameTexts()[10].setText(Integer.toString(cur_player + 1));
		view.getGameTexts()[11].setText(Integer.toString(start_player + 1));

		view.get3Texts()[0].setText(Integer.toString(wood2_3p));
		view.get3Texts()[1].setText(Integer.toString(clay_3p));

		view.get4Texts()[0].setText(Integer.toString(wood_4p));
		view.get4Texts()[1].setText(Integer.toString(wood2_4p));
		view.get4Texts()[2].setText(Integer.toString(clay2_4p));
		view.get4Texts()[3].setText(Integer.toString(food_4p));

		view.get5Texts()[0].setText(Integer.toString(reed_5p));
		view.get5Texts()[1].setText(Integer.toString(food_5p));
		view.get5Texts()[2].setText(Integer.toString(clay3_5p));
		view.get5Texts()[3].setText(Integer.toString(wood4_5p));
		updateFarm(false);

		/*
		 * for (int r=0;r<27;r++){ for (int col=0;col<35;col++){ if (bought){ if
		 * ((b[r][col].getBackground()==Color.black) && (game==5)){ for (int
		 * count=0;count<4;count++) seating[r][col].getBought()[count]=true; }
		 * else { if (b[r][col].getBackground()==Color.black)
		 * seating[r][col].getBought()[game-1]=true; } }
		 * changeButton(seating[r][col],b[r][col]);
		 * 
		 * } }
		 */
	}
	
	public void consume() {
		int selectedValue = 0;
		Object harvestopt[] = { "Convert Sheep",
				"Convert Boar", "Convert Cattle" };

		while ((players[cur_player].getMaxAnimalCount() < (players[cur_player].getTotalAnimals()))) {

			selectedValue = JOptionPane.showOptionDialog(null,
					"You do not have enough room for all these animals", "Consume",
					JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, harvestopt,
					harvestopt[0]);

			if (selectedValue == 0 && players[cur_player].getSheep() > 0) {
				if (players[cur_player].hasOven()) {
					players[cur_player].addSheep(-1);
					players[cur_player].addFood(2);
				} 
				else {
					players[cur_player].addSheep(-1);
					players[cur_player].addFood(1);
				}

			}
			else if (selectedValue == 1 && players[cur_player].getBoar() > 0) {
				if (players[cur_player].hasOven()) {
					players[cur_player].addBoar(-1);
					players[cur_player].addFood(3);
				} 
				else {
					players[cur_player].addBoar(-1);
					players[cur_player].addFood(1);
				}
			}
			else if (selectedValue == 2 && players[cur_player].getCattle() > 0) {
				if (players[cur_player].hasOven()) {
					players[cur_player].addCattle(-1);
					players[cur_player].addFood(4);
				} 
				else {
					players[cur_player].addCattle(-1);
					players[cur_player].addFood(2);
				}
			}
		}
	}

	// **************************
	// When any visual changes on the displayed farm need to be done
	// **************************
	public void updateFarm(boolean alter) {
		/*
		 * System.out.println(" player 1: " +farm[0][1][1].getType());
		 * System.out.println(" player 2: " +farm[1][1][1].getType());
		 * System.out.println(" player 3: " +farm[2][1][1].getType());
		 * System.out.println(" player 4: " +farm[3][1][1].getType());
		 */
		if (alter) {

			b[3][3].setBackground(Color.black);
		}

		else {
			for (int r = 0; r < 7; r++) {
				for (int col = 0; col < 11; col++) {
					// e= empty, w=wood room, p=pasture, c=clay room, s=stone
					// room, f=field, g=grain field, v=vege field, x=fence

					if (farm[view_player][r][col].getType() == 'e'
							&& farm[view_player][r][col].isSquare())
						b[r][col].setBackground(Color.white);
					else if (farm[view_player][r][col].getType() == 'w')
						b[r][col].setBackground(Color.red);
					else if (farm[view_player][r][col].getType() == 'c')
						b[r][col].setBackground(Color.orange);
					else if (farm[view_player][r][col].getType() == 's')
						b[r][col].setBackground(Color.gray);
					else if (farm[view_player][r][col].getType() == 'm') {
						b[r][col].setBackground(new Color(135, 206, 250));
					}
					else if (farm[view_player][r][col].getType() == 'p') {
						b[r][col].setBackground(Color.pink);
					} 
					else if (farm[view_player][r][col].getType() == 'g') {
						if (farm[view_player][r][col].getStack() == 1)
							b[r][col].setBackground(new Color(238, 232, 170));
						else if (farm[view_player][r][col].getStack() == 2)
							b[r][col].setBackground(Color.yellow);
						else
							b[r][col].setBackground(new Color(255, 215, 0));
					} 
					else if (farm[view_player][r][col].getType() == 'v') {
						if (farm[view_player][r][col].getStack() == 2)
							b[r][col].setBackground(Color.green);
						else
							b[r][col].setBackground(new Color(152, 251, 152));
					} 
					else if (farm[view_player][r][col].getType() == 'f')
						b[r][col].setBackground(new Color(139, 69, 19));
					else if (farm[view_player][r][col].getType() == 'x')
						b[r][col].setBackground(new Color(205, 133, 63));

				}

			}
		}

	}

	public Player getPlayer(int i) {
		return players[i];
	}

	public int buildFencesAction() {
		if (players[cur_player].getWood() <= 0) {
			return 1;
		} else if (players[cur_player].getFenceCount() >= 15) {
			return 2;
		}
		wFences = true;
		return 0;
	}
	
	/* Returns whether there is a fence at a given location on the farm */
	public boolean hasFence(int row, int col) {
		if (view.getFarm()[row][col].getType() == 'x')
			return true;
		return false;
	}
	
	public boolean validFencePosition(int row, int col) {
		if (players[cur_player].getFenceCount() == 0) {
			return true;
		}
		else if (hasFence(row, col)) {
			return false;
		}
		else {
			if ((row!=0 && col!=0 && hasFence(row-1, col-1))  ||
				(row!=0 && col!=10 && hasFence(row-1, col+1)) ||
				(row!=6 && col!=0 && hasFence(row+1, col-1))  ||
				(row!=6 && col!=10 && hasFence(row+1, col+1)) ||
				(col-2>0 && hasFence(row, col-2))  ||
				(col+2<10 && hasFence(row, col+2)) ||
				(row-2>0 && hasFence(row-2, col))  ||
				(row+2<6 && hasFence(row+2, col))) {
				return true;
			}
		}
		
		return false;
	}
	
	public void createPastures() {
		int pastureCount = 0;
		int animalCount = 0;
		int checker = 0x0000;
		for (int row = 1; row <= 5; row+=2) {
			for (int col = 1; col <= 9; col+=2 ) {
				if ( farm[view_player][row][col].getType() == 'w' ||
					 farm[view_player][row][col].getType() == 'c' ||
					 farm[view_player][row][col].getType() == 's' ||
					 farm[view_player][row][col].getType() == 'f') continue;
				for (Fence fence: players[cur_player].getFences()) {
					if (fence.getX() > row && fence.getY() == col)
						checker |= 0x0001;
					if (fence.getX() < row && fence.getY() == col) 
						checker |= 0x0010;
					if (fence.getX() == row && fence.getY() < col) 
						checker |= 0x0100;
					if (fence.getX() == row && fence.getY() > col)
						checker |= 0x1000;
				}
				if (checker == 0x1111) {
					pastureCount++;
					animalCount+=2;
					farm[view_player][row][col].setType('p');
					updateFarm(false);
					wFences = true;
				}
				checker = 0x0000;
			}
		}
		players[cur_player].setPastureAreaCount(pastureCount);
		players[cur_player].setMaxAnimalCount(++animalCount);
	}
	
	public void handleBuildFarmFence(JButton button) {
		boolean correctClick = false;
		if (players[cur_player].getWood() <= 0) {
			update(false);
			return; 
		} else if (players[cur_player].getFenceCount() >= 15) {
			update(false);
			return; 
		}
		for (int row = 1; row < 7; row+=2) {
			for (int col = 0; col <= 10; col+=2) {
				if (button == view.getButtons()[row][col] && validFencePosition(row, col)) {
					correctClick = true;
					button.setBackground(Color.pink);
					view.getFarm()[row][col].setType('x');
					players[cur_player].addWood(-1);
					players[cur_player].addFence(row, col);
					break;
				}
			}
			if (correctClick) break;
		}
		if (!correctClick) {
			for (int row = 0; row <= 6; row+=2) {
				for (int col = 1; col < 11; col+=2) {
					if (button == view.getButtons()[row][col] && validFencePosition(row, col)) {
						correctClick = true;
						button.setBackground(Color.pink);
						view.getFarm()[row][col].setType('x');
						players[cur_player].addWood(-1);
						players[cur_player].addFence(row, col);
						break;
					}
				}
				if (correctClick) break;
			}
		}
		update(false);
		if (!debug_mode) {
			Object[] possibleValues = { "Yes", "No" };
			int selectedValue = JOptionPane.showOptionDialog(null,
					"More fences?", "Choose one",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
					null, possibleValues, possibleValues[0]);
			if (selectedValue == 0) {
				wFences = true;
			}
		}
		else {
			wFences = true;
		}
	}

}