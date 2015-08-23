class LengthDec implements WeightFunc{
	public static float WeightFunc(Pixel y_i, Pixel x, float epsilon, int z){
		double dist_2 = (double) Math.pow(y_i.getX()-x.getX(),2)+Math.pow(y_i.getY()-x.getY());
		float ans = (float) 1/(Math.pow(double(Math.sqrt(dist_2),(double)z)+epsilon)
		return ans;
	}
}