
public class Force {

	private double load; //
	private String name; //
	
	public Force(double load, String name) {
		this.load = load;
		this.name = name;
		
		
	}

	public double getLoad() {
		return load;
	}

	public void setLoad(double load) {
		this.load = load;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public void decay() {
		this.setLoad( load*0.80);
	
	}
	
	public Force clone() {
		Force clone = new Force(load, name);
		
		return clone;
	}
	
	@Override
	public String toString() {
		
		String s = new String(getName() + " has a load of " + Integer.toString( (int) load));
		
		return s;
	}
	
	
	
	
	
	
	
}
