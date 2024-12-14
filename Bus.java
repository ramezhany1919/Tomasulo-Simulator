package application;

public class Bus {
	private String tag;
	private Object value;
	
	
	public String getTag() {
		return tag;
	}
	
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}
	
	public void clearBus() {
		this.tag="";
		this.value=null;
	}
	
	public String toString() {
		return "DataBus{"+"tag: "+tag+" Value: "+value+"}";
	}
	
}
