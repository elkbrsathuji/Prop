class Pixel{
	private int x;
	private int y;
	private float val;
	public Pixel(){
		this.x = null;
		this.y = null;
		val = -1;
	}
	public Pixel(int x, int y, float val) {
		this.x = x;
		this.y = y;
		this.val = val;
	}
	public int getX(){ return this.x;}
	public int getY(){ return this.y;}
	public float getX(){ return this.val;}
	public void setX(int n_x){this.x = n_x;}
	public void setY(int n_y){this.y = n_y;}
	public void setVal(int n_val){this.val = n_val;}
}