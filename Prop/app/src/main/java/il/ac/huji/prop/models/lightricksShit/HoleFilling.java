import android.media.Image;

import java.util.ArrayList;





public class HoleFilling {



    /**

     * @param args

     */

    int rows;
    int cols;
    float [][] img;
    ArrayList <Pixel> boundary;
    ArrayList <Pixel> hole;
    ArrayList stHole;
    ArrayList endHole;

    public static void main(String[] args) {

    }

    private void findHole(){
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if( img[i][j] < 0 ){
                    hole.add(new Pixel(i,j,-1));
                    continue;
                }
                if (img[i+1][j] < 0){
                    findBoundary(i,j,true,true);
                    i=rows;
                    j=cols;
                    continue;
                }
                if (img[i][j+1] < 0){
                    findBoundary(i,j,true,false);
                    i=rows;
                    j=cols;
                    continue;
                }
                if (img[i-1][j] < 0){
                    findBoundary(i,j,false,true);
                    i=rows;
                    j=cols;
                    continue;
                }
                if (img[i][j-1] < 0){
                    findBoundary(i,j,false,false);
                    i=rows;
                    j=cols;
                    continue;
                }
            }
        }
    }
//clockwise search for boundary
//pay attention to image limits.
    private void findBoundary(int k,int l, boolean forward, boolean upper){
        int i = k;//rows
        int j = l;//cols
        boolean inLimits = true;
        do{
            if (j+1 < cols && img[i][j+1] < 0){
                if (i-1 < 0 ){
                    inLimits = false;
                    j += 1;
                }
                else if (img[i-1][j+1] < 0){
                    i -= 1;
                }
                else{
                    i -= 1;
                    j += 1;
                }
            }
            else if(i+1 < rows && img[i+1][j] < 0){
                if (j+1 > cols){
                    inLimits = false;
                    i = i+1;
                }
                else if(img[i+1][j+1] < 0){
                    j -= 1;
                }
                else{
                    i += 1;
                    j += 1;
                }
            }
            else if(j-1 >= 0 && img[i][j-1] < 0){
                if (i+1 > rows){
                    inLimits = true;
                    j -= 1;
                }
                else if(img[i+1][j-1] < 0 ){
                    i += 1;
                }
                else{
                    i += 1;
                    j -= 1;
                }
            }
            else if(i-1 >= 0 && img[i-1][j] < 0){
                if (j-1 < 0){
                    inLimits = false;
                    i -= 1;
                }
                else if (img[i-1][j-1] < 0){
                    j -= 1;
                }
                else{
                    i -= 1;
                    j -= 1;
                }
            }
            if (inLimits){
                boundary.add(new Pixel(i,j,img[i][j]));
            }
            else{
                inLimits = true;
            }
        }while(i != k && j !=l);
    }

    private void goodApproximation(){

    }

    private void colorBoundary(Image img, ArrayList<Pixel> bound){
		float [][] blackBound = img.getColMap();
		for (Pixel p:bound){
			blackBound[p.getY()][p.getX()] = 1;
		}
		//TODO print blackBound
	}	
	/* 
	private void FindBoundary(int i, int j, float [] [] img){
		ArrayList<Pixel> boundary = new ArrayList<~>;
		map = img.getColMap();
		rows = img.getRows();
		cols = img.getCols();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j< cols; j++){
				if (i < rows - 1 && map[i+1][j] < 0){
					boundary.add(new Pixel(i,j,map[i][j]))
				}
				else if (i > 0 && map[i-1][j] < 0){
					boundary.add(new Pixel(i,j,map[i][j]))
				}
				else if (j < cols -1 && map[i][j+1] < 0){
					boundary.add(new Pixel(i,j,map[i][j]))
				}
				else if(j > 0 && map[i][j-1] < 0 ){
					boundary.add(new Pixel(i,j,map[i][j]))
				}
				
			}
		}
	} */
	
	private void SetVal(Pixel y_i, Pixel x, WeightFunc w){
		
	}
	
}

	