package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable{
	
	private ArrayList<String> items;
	public Cart(){
		items = new ArrayList<>();
	}
	
	public ArrayList<String> getItems(){
		if(items.size()>0){
			return items;
		}	
		return items;
	}
	
	public void addItem(String item){
		if(!items.contains(item)){
			items.add(item);
		}
		
	}
	
	public void removeItem(int itemPosition){
		try{
			items.remove(itemPosition);
		}catch (IndexOutOfBoundsException e) {
			
		}
	}
	
	public static boolean emptyCart(Cart cart){
		if(cart == null){
			return true;
		}else if(cart.getItems().size()==0){
			return true;
		}
		return false;
	}
	
	
}
