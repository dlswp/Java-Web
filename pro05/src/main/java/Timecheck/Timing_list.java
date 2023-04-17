package Timecheck;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Timing_list {

	public static void main(String[] args) {
		List<Integer> arrayList = new ArrayList<>();
		List<Integer> linkedList = new LinkedList<>();

		long duration = timeOperations(arrayList);
		
		System.out.println("측정시간 : " + duration);
	}
	
	public static long timeOperations(List<Integer> list) {
		
		long start = System.currentTimeMillis();
		
		for(int i = 0; i<100000; i++) {
			list.add(0,i);
		}
		
		return System.currentTimeMillis() - start;
		
	}
	

}
