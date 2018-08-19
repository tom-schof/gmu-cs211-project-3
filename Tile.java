
public abstract class Tile implements Modifiable {

	private int row; //
	private int column; //
	private String measurement; //
	int PEOPLE_WASTE = 2;
	int CARS_WASTE =5;

	public abstract boolean canPropagate();

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int col) {
		this.column = col;

	}

	public String getMeasurement() {
		return measurement;
	}

	public void setMeasurement(double measurement) {
		this.measurement = ((int) Math.round(measurement)) + "";

	}

	public String toString() {

		String s = new String("row: " + Integer.toString(getRow()) + " col: " + Integer.toString(getColumn()) );

		return s;
	}
	@Override
	public Force weaken(Force force) {
		Force f = force.clone();
		
		f.decay();

		return f;

	}
	
	

}
