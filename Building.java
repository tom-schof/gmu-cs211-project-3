
public class Building extends Tile {

	private int people; //
	private int cars; //
	private int height; //
	
	
	public Building(int people, int cars, int height)	{
		super();
	 
		this.people = people;
		this.cars = cars;
		this.height = height;
	
		
	}
	
	public int getPeople() {
		return people;
	}

	public void setPeople(int people) {
		this.people = people;
	}

	public int getCars() {
		return cars;
	}

	public void setCars(int cars) {
		this.cars = cars;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	
	@Override
	public Force strengthen(Force f) {
		double load_increment = (people * PEOPLE_WASTE) + (cars * CARS_WASTE);
		double f_load = f.getLoad();
		double f_load_increment = f_load + load_increment;
		f.setLoad(( Math.round( f_load_increment)));
		return f;
	}

	

	@Override
	public boolean canPropagate() {
	if (height < 100) {
		return true;
	}
	else return false;
	}
	

	
	

}

