package uk.co.techblue.dto;

public class ItemQuote {

	private int number;

	private String description;

	private Double price;
	
	public ItemQuote(final int number,final String description,final double price){
		this.number=number;
		this.description=description;
		this.price=price;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String toString() {
		final String endOfLine = System.getProperty("line.separator");
		final String value = "Item#=" + number + endOfLine + "Description="
				+ endOfLine + "Price=" + endOfLine;
		return value;
	}

}
