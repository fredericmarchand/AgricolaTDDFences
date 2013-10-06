package agricola;

public class Space {
	private boolean square;
	@SuppressWarnings("unused")
	private boolean valid;
	private char type;
	private int stack;
	
	public Space(){
		type='e';
		square=false;
		valid=true;
		stack=0;
		//e= empty, w=wood room, p=pasture, c=clay room, s=stone room, f=field, x=fence
		
	}
	
	public void setType(char t){
		type=t;
	}
	
	public void setStack(int i){
		stack=i;
	}
	
	public void decStack(){
		stack--;
	}
	
	public int getStack(){
		return stack;
	}
	
	public void setSquare(){
		square=true;
	}
	
	public void setValid(){
		valid=false;
	}
	
	public boolean isSquare(){
		return square;
	}
	public boolean isValid(){
		return square;
	}
	
	
	public char getType(){
		return type;
	}
	

		
}