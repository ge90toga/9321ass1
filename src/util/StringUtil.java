package util;

import java.util.ArrayList;
import java.util.Iterator;

import javax.print.event.PrintJobAttributeListener;

public class StringUtil {

	public static boolean isNull(String str){
		if(str == null || str.length() == 0){
			return true;
		}
		return false;
	}
	
	public static boolean matches(String check, String key){
		if(check.toLowerCase().contains(key.toLowerCase().trim())){
			return true;
		}
		return false;
	}
	
	public static <E> void printList(ArrayList<E> list){
		for(E e: list){
			if(e != null){
				System.out.println(e);
			}
			
		}
	}
	
	public static <E> void printTop10List(ArrayList<E> list){
		System.out.println("contains: "+list.size());
		int counter = 0;
		for(E e: list){
			if(e != null){
				System.out.println(e);
				counter++;
			}
			if(counter == 10){
				break;
			}
			
		}
	}
	
	
	public static <E> ArrayList<E> removeNull(ArrayList<E> list){
		for (Iterator<E> iterator = list.iterator(); iterator.hasNext();) {
		    E e = iterator.next();
		    if (e == null) {
		        // Remove the current element from the iterator and the list.
		        iterator.remove();
		    }
		}
		return list;
	}
	
	
	
}
