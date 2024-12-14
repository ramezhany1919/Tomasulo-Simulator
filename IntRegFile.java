package application;

public class IntRegFile {
	
	private long value;
	private String q;
	
	
	public IntRegFile(long value,String q) {
		this.value=value;
		this.q=q;
	}
	
	public long getValue() {
		return value;
	}
	




	public void setValue(long value) {
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
