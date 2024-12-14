package application;

public class FloatRegFile {
	private double value;
	private String q;
	
	
	public FloatRegFile(double value,String q) {
		this.value=value;
		this.q=q;
	}
	
	
	
	
	public double getValue() {
		return value;
	}





	public void setValue(double value) {
		this.value = value;
	}





	public String getQ() {
		return q;
	}





	public void setQ(String q) {
		this.q = q;
	}





	public String toString() {
		return "{ Value:"+value+" Q: "+q+"}";
	}
	

}
