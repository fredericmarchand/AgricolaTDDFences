package agricola;

public class Farm {
	
	private Space[][] farm;
	
    public Farm(){	
    	farm = new Space[7][11];
 
    	

			
			for (int r=0;r<7;r++){
				for (int col=0;col<11;col++){
					farm[r][col] = new Space();
					if (r%2==1&&col%2==1){
						farm[r][col].setSquare();
						if ((col==1&&r==3)||(col==1&&r==5))
							farm[r][col].setType('w');
					}
					if (r%2==0&&col%2==0)
						farm[r][col].setValid();

				}

			}
   	

    	

    }
   	public Space[][] getFarm(){
		return farm;
	}
	
    
}