class Image{
	private float [][] colMap
	private int rows;
	private int cols;
	public Image(){
		this.colMap = null;
		this.rows = 0;
		this.cols = 0;
	}
	public Image(float [] [] img){
		this.colMap = img;
		this.rows = img.length();
		this.cols = img[0].length();
	}
	
	public int getRows(){ return this.rows;}
	public int getCols(){ return this.cols;}
	public float [][] getColMap(){ return this.colMap;}
	public void setIndex(int i, int j, float val){
		this.colMap[i][j] = val;
	}
	
	
}