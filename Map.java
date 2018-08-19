import java.util.Arrays;
import java.util.logging.*;

public class Map {
	private static final Logger LOGGER = Logger.getLogger(Map.class.getName());

	private Tile[][] map; //

	private int freeRow; //

	private int freeColumn; //

	public Map(int width, int height) {

		this.map = new Tile[height][width];
		this.freeRow = 0;
		this.freeColumn = 0;

	}

	public boolean addTile(Tile tile) {
		tile.setMeasurement(0);

		boolean logic = false;

		// 1. if it's already full, don't add tile and return false
		if (this.freeRow == -1 && this.freeColumn == -1) {
			logic = false;
		}

		// 2. Otherwise add tile, update FreeRow and FreeColumn attributes
		else {

			this.map[freeRow][freeColumn] = tile;
			tile.setRow(freeRow);
			tile.setColumn(freeColumn);

			// - Now update
			// -Condition 1: there is no more space in the row
			if (this.freeColumn == this.map[0].length - 1) {
				// -Condition 1a: there are no more rows, map is full
				if (this.freeRow == this.map.length - 1) {
					this.freeRow = -1;
					this.freeColumn = -1;

					logic = true;

				}
				// -Condition 1b: there is another row. Move to it and reset column index
				else {
					this.freeRow++;
					this.freeColumn = 0;
					logic = true;

				}
				// -Condition 2: there is space left in the row
			} else {
				this.freeColumn++;
				logic = true;

			}

		}

		return logic;

	}

	public Tile getTile(int row, int col) {
		return map[row][col];

	}

	public Tile[] getNeighbors(Tile tile, Direction direction) {
		int ctr = 0;
		int Neighbors_ctr = 0;
		Tile[] Neighbors_Temp = new Tile[3];
		Tile[] Neighbors = null;
		// 1. get the location of the tile in the map;
		int x = tile.getRow();
		int y = tile.getColumn();

		// 2. check the case for north
		if (direction == Direction.NORTH) {

			// 2a check row+1 and column-1, check row+1 and column, check row+1 and column
			// +1
			// 2aa. if all three are true, make a new tile array and add three
			// 2ab. if two are true, make a new tile array and add two
			// 2ac. if one is true, make a new tile array and add it
			// 2ad. if none are true, tile array will be null

			for (int i = -1; i <= 1; i++) {
				try {
					Neighbors_Temp[i + 1] = (map[x - 1][y + i]);
				} catch (NullPointerException e) {
					ctr++;
				} catch (ArrayIndexOutOfBoundsException e) {
					ctr++;
				}
			}
				Neighbors = new Tile[3 - ctr];
				for (int j = 0; j < 3; j++)
					if (Neighbors_Temp[j] != null) {
						Neighbors[Neighbors_ctr] = Neighbors_Temp[j];
						Neighbors_ctr++;
					}
			

		}

		// 3. check the case for South
		else if (direction == Direction.SOUTH) {
			// 3a check row-1 and column-1, check row-1 and column, check row-1 and column
			// +1
			// 3aa. if all three are true, make a new tile array and add three
			// 3ab. if two are true, make a new tile array and add two
			// 3ac. if one is true, make a new tile array and add it
			// 3ad. if none are true, tile array will be null

			for (int i = -1; i <= 1; i++) {
				try {
					Neighbors_Temp[i + 1] = (map[x + 1][y + i]);
					
					
				} catch (NullPointerException e) {
					ctr++;
				} catch (ArrayIndexOutOfBoundsException e) {
					ctr++;
					
					
				}
			}
			Neighbors = new Tile[3 - ctr];
			for (int j = 0; j < 3; j++)
				if (Neighbors_Temp[j] != null) {
					
					Neighbors[Neighbors_ctr] = Neighbors_Temp[j];
					Neighbors_ctr++;
				}

		}
		// 4. check the case for East
		else if (direction == Direction.EAST) {
			// 4a check row+1 and column+1, check row and column+1, check row-1 and column
			// +1
			// 4aa. if all three are true, make a new tile array and add three
			// 4ab. if two are true, make a new tile array and add two
			// 4ac. if one is true, make a new tile array and add it
			// 4ad. if none are true, tile array will be null

			for (int i = -1; i <= 1; i++) {
				try {
					Neighbors_Temp[i + 1] = (map[x + i][y + 1]);

				} catch (NullPointerException e) {
					ctr++;
				} catch (ArrayIndexOutOfBoundsException e) {
					ctr++;
					
				}
			}
			Neighbors = new Tile[3 - ctr];
			for (int j = 0; j < 3; j++)
				if (Neighbors_Temp[j] != null) {
					Neighbors[Neighbors_ctr] = Neighbors_Temp[j];
					Neighbors_ctr++;
				}

		} else if (direction == Direction.WEST) {
			// 5. check the case for West
			// 5a check row+1 and column-1, check row and column-1, check row-1 and column
			// -1
			// 5aa. if all three are true, make a new tile array and add three
			// 5ab. if two are true, make a new tile array and add two
			// 5ac. if one is true, make a new tile array and add it
			// 5ad. if none are true, tile array will be null
			for (int i = -1; i <= 1; i++) {
				try {
					Neighbors_Temp[i + 1] = (map[x + i][y - 1]);
				} catch (NullPointerException e) {
					ctr++;
				} catch (ArrayIndexOutOfBoundsException e) {
					ctr++;
				}
			}
			Neighbors = new Tile[3 - ctr];
			for (int j = 0; j < 3; j++)
				if (Neighbors_Temp[j] != null) {
					Neighbors[Neighbors_ctr] = Neighbors_Temp[j];
					Neighbors_ctr++;
				}
		}
		return Neighbors;
	}

	public void propagate(Force force, int row, int column, Direction direction) {

		Tile tile = getTile(row, column);

		Force weaken = tile.weaken(force);
		Force strengthen = tile.strengthen(force);
		tile.setMeasurement((strengthen.getLoad() + weaken.getLoad()) / 2);
		Tile[] neighbors = getNeighbors(tile, direction);
		force.setLoad((strengthen.getLoad() + weaken.getLoad()) / 2);

		// recursion below
		for (int i = 0; i < neighbors.length; i++)
			propagate(force, neighbors[i].getRow(), neighbors[i].getColumn(), direction);
	}

	@Override
	public String toString() {

		// loop through dimensions of map
		// concatenate
		//
		String s = new String();
		String s_temp = new String();
	
	


		for (int i = 0; i < this.map.length; i++) {
			for (int j = 0; j < this.map[0].length; j++) {
				if (j == this.map[0].length -1) {
					LOGGER.info(s_temp);
					
					s_temp = "class " + map[i][j].getClass().getName() + " " + map[i][j].getMeasurement() + "                           ";
					s_temp = s_temp.substring(0, 32);
					s = s  + s_temp + "\n" ;
				}else {
					s_temp = "class " + map[i][j].getClass().getName() + " " + map[i][j].getMeasurement() + "                           ";
					s_temp = s_temp.substring(0, 32);
					s = s  + s_temp ;
				}
			}
		}

		return s;

	}

}
