package util;

import java.util.ArrayList;

import entity.Publication;
/**
 * pages are 10 records most each one
 * @author Linus
 *
 */
public class PageHandler {
	/**
	 * No boundary check!
	 * @param inputPublication
	 * @param page_num
	 * @return
	 */
	public ArrayList<Publication> showPage(ArrayList<Publication> inputPublication, int page_num){
		if((inputPublication.size()==0)){
			return inputPublication;
		}
		
		int start = 10*(page_num-1);
		int end = start+9;
		ArrayList<Publication> returnedList = null;
		if(inputPublication.size()-1 > end){
			returnedList = new ArrayList<>(inputPublication.subList(start, start+9));
		}else{
			returnedList = new ArrayList<>(inputPublication.subList(start, inputPublication.size()));
		}
		return returnedList;
		
	}
	
	public static int get_Total_Page(ArrayList<Publication> inputPublication){
		int n = inputPublication.size() / 10 +( (inputPublication.size()% 10 == 0) ? 0 : 1); 
		return n;
	}
	

}
