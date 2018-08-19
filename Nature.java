
public class Nature extends Tile {
	
	private int treeDensity; //
	
	
	public Nature(int treeDensity) {
		super();
		this.treeDensity = treeDensity;
		
	}


	@Override
	public Force strengthen(Force f) {
		return f.clone();
	}
	
	@Override
	public Force weaken(Force force) {
		Force f = force.clone(); // clones incoming force
		double f_load = f.getLoad(); // gets the load of the cloned force
		
		float reduction_factor = this.treeDensity/(float) 100; //calculates how much the cloned force will be reduced
		double f_load_reduced = Math.round(f_load - (f_load * reduction_factor)) ; // reduces the cloned force by the reduction factor
	
		
		
		if (f_load_reduced > 0 ) { //makes sure the force will never be less than 0.
		
		f.setLoad(f_load_reduced);
		}
		
		else f.setLoad(0);
		
		return f; // returns the cloned force
		
		
	}

	@Override
	public boolean canPropagate() {
	if (treeDensity <= 50) {
		return true;
	}
	else return false;
	}
	
	
	
	
	

}
