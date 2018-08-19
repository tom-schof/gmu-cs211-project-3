public class IndustrialBuilding extends Building {

	private double emissions; //
	private int EMISSIONS_CONSTANT = 10; //
	
	public IndustrialBuilding(int people, int cars, int height, double emissions) {
		super(people, cars, height);
		this.emissions =  emissions;
		
	}
	
	public Force strengthen(Force force) {
		super.strengthen(force);
		double load_increment = (emissions * EMISSIONS_CONSTANT);
		double force_load = force.getLoad();
		double force_load_increment = force_load + load_increment;
		force.setLoad(force_load_increment);
		return force;
		
	}
	@Override
	public boolean canPropagate() {
		
		
		return true;
		
		
	}
	
	
	
	
	
}
