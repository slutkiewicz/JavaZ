package domain;


import java.util.stream.Stream;

public  class Category {
	private Stream<String> categories = Stream.of("GPU", "CPU", "HDD","RAM");
	
	public boolean checkCategories(String s) {
		return categories.anyMatch(e-> e.equals(s));
	}
		

}
