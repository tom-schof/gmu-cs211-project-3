
public class Highway extends Tile {

	private int carDensity; //
	 
	
	public Highway(int carDensity) {
		super();
		this.carDensity = carDensity;
		
		
	}


	@Override
	public Force strengthen(Force f) {
		int load_increment = (carDensity * CARS_WASTE);
		double f_load = f.getLoad();
		double f_load_increment = f_load + load_increment;
		f.setLoad(f_load_increment);
		return f;
	}


	@Override
	public boolean canPropagate() {
		
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
