
public class BodyOfWater extends Tile {

	@Override
	public Force strengthen(Force f) {
	
		
		return f.clone();
	}

	@Override
	public boolean canPropagate() {

		return true;
	}


	
}
