package agricola;

import java.util.ArrayList;

public class Player {
	
	@SuppressWarnings("unused")
	private boolean human, hasChild;
	public int food, reed, wood, clay, stone, grain, vege, family, rooms, activefam, sheep, cattle, boar, field, empty, pasture, stable, score;
	private char roomtype;
	private boolean hasOven;
	public int fenceCount;
	public ArrayList<Fence> fences;
	private int pastureAreaCount;
	private int maxAnimalCount;
//	private farm
	
	public Player(){
		food=0;
		reed=0;
		wood=0;
		clay=0;
		stone=0;
		grain=0;
		vege=0;
		family=2;
		activefam=2;
		rooms=2;
		roomtype='w';
		sheep=0;
		cattle=0;
		boar=0;
		field=0;
		empty=13;
		pasture=0;
		stable=0;
		setPastureAreaCount(0);
		score=0;
		fenceCount = 0;
		maxAnimalCount = 1;
		hasOven=false;
		fences = new ArrayList<Fence>();
	}
	
	public ArrayList<Fence> getFences() {
		return fences;
	}
	
	public int getScore(){
		return score;
	}
	
	public void calcScore(){
		if (field<2)
			score--;
		else
			score=score+field-1;
			
		score+=pasture;
		
		score=score+(grain/2);
		
		if (vege<1)
			score--;
		else
			score+=vege;
			
		if (sheep<1)
			score--;
		else 
			score=score+(sheep/2);
			
		if (boar<1)
			score--;
		else 
			score=score+(boar/2);
			
		if (cattle<1)
			score--;
		else 
			score+=cattle;
			
		score-=empty;
		score+=pastureAreaCount;
		
		score+=stable;
		
		if (roomtype=='c')
			score+=rooms;
		
		if (roomtype=='s')
			score=score+(rooms*2);
			
		score=score+(family*3);
		
	}
	
	public void addScore(int n){
		score+=n;
	}
	
	public boolean hasOven(){
		return hasOven;
	}
	public void setOven(){
		hasOven=true;
	}
	
	public void addField(){
		field+=1;
	}
	
	public void decVege(){
		vege--;
	}
	public void decGrain(){
		grain--;
	}
	
	public int getField(){
		return field;
	}
	
	public void decEmpty(int n){
		empty-=n;
	}
	
	public int getPasture(){
		return pasture;
	}
	
	public void addPasture(int n){
		pasture+=n;
	}
	
	public int getStable(){
		return stable;
	}
	
	public void addStable(int n){
		stable+=n;
	}
	
	public int getEmpty(){
		return empty;
	}
	
	
	public void addSheep(int n){
		sheep+=n;
	}
	
	public int getSheep(){
		return sheep;
	}
	
	public void addBoar(int n){
		boar+=n;
	}
	
	public int getBoar(){
		return boar;
	}
	
	public void addCattle(int n){
		cattle+=n;
	}
	
	public int getCattle(){
		return cattle;
	}
	
	public boolean hasChild(){
		return hasChild;
	}
	
	public void setChild(){
		hasChild=!hasChild;
	}
	
	public int getRoomType(){
		return roomtype;
	}
	
	public void setRoomType(char r){
		roomtype=r;
	}
	
	public int getFood(){
		return food;
	}
	
	public int getReed(){
		return reed;
	}
	
	public int getWood(){
		return wood;
	}
	
	public int getClay(){
		return clay;
	}
	
	public int getStone(){
		return stone;
	}
	public int getGrain(){
		return grain;
	}
	public int getVege(){
		return vege;
	}
	public int getFamily(){
		return family;
	}
	public int getRooms(){
		return rooms;
	}
	
	public int getActiveFamily(){
		return activefam;
	}
	
	public int getFenceCount() {
		return fenceCount;
	}

	public void useFam(){
		activefam--;
	}
	
	public void resetFam(){
		activefam=family;
	}
	
	public void addFood(int n){
		food=food+n;
	}
	
	public void addReed(int n){
		reed=reed+n;
	}
	
	public void addWood(int n){
		wood=wood+n;
	}
	
	public void addClay(int n){
		clay=clay+n;
	}
	
	public void addStone(int n){
		stone=stone+n;
	}
	public void addGrain(){
		grain++;
	}
	public void addVege(){
		vege++;
	}
	public void addFamily(){
		family++;
		hasChild=true;
	}
	public void addRooms(int n){
		rooms=rooms+n;
	}
	
	public void addFence(int row, int col) {
		fenceCount++;
		fences.add(new Fence(row, col));
	}

	/**
	 * @return the pastureAreaCount
	 */
	public int getPastureAreaCount() {
		return pastureAreaCount;
	}

	/**
	 * @param pastureAreaCount the pastureAreaCount to set
	 */
	public void setPastureAreaCount(int pastureAreaCount) {
		this.pastureAreaCount = pastureAreaCount;
	}

	/**
	 * @return the maxAnimalCount
	 */
	public int getMaxAnimalCount() {
		return maxAnimalCount;
	}

	/**
	 * @param maxAnimalCount the maxAnimalCount to set
	 */
	public void setMaxAnimalCount(int maxAnimalCount) {
		this.maxAnimalCount = maxAnimalCount;
	}
	
	public int getTotalAnimals() {
		return (cattle + boar + sheep);
	}
	
	
	
}