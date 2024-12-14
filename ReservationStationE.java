package application;

public class ReservationStationE {
	private InstructionType op;
    private int time;
    private String tag;
    private boolean busy;
    private Object vj;
    private Object vk;
    private Object qj;
    private Object qk;
    private int address;
    private Instruction instruction;
    private float result;
    private boolean completed;
    
    public ReservationStationE(String tag) {
    	this.time=-1;
    	this.tag=tag;
    	this.busy=false;
    	this.op=null;
    	this.vj=0;
    	this.vk=0;
    	this.qj=0;
    	this.qk=0;
    	this.address=0;
    	this.instruction=null;
        this.result=-999999999;
        this.completed=false;
    	
    }
    
    
    public int getTime() {
		return time;
	}







	public void setTime(int time) {
		this.time = time;
	}







	public String getTag() {
		return tag;
	}







	public void setTag(String tag) {
		this.tag = tag;
	}







	public boolean isBusy() {
		return busy;
	}







	public void setBusy(boolean busy) {
		this.busy = busy;
	}







	public InstructionType getOp() {
		return op;
	}







	public void setOp(InstructionType op) {
		this.op = op;
	}







	public Object getVj() {
		return vj;
	}







	public void setVj(Object vj) {
		this.vj = vj;
	}







	public Object getVk() {
		return vk;
	}







	public void setVk(Object vk) {
		this.vk = vk;
	}







	public Object getQj() {
		return qj;
	}







	public void setQj(Object qj) {
		this.qj = qj;
	}







	public Object getQk() {
		return qk;
	}







	public void setQk(Object qk) {
		this.qk = qk;
	}







	public int getAddress() {
		return address;
	}







	public void setAddress(int address) {
		this.address = address;
	}







	public Instruction getInstruction() {
		return instruction;
	}







	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}







	public String toString() {
        String result="{time: "+time+" tag: "+tag+" busy: "+busy+" op: "+op+" vj: "+vj+" vk: "+vk+" qj: "+qj+" qk: "+qk +" address: "+address+" instruction: "+instruction;
        return result;
    }
    public boolean isCompleted(){
    	return this.completed;
    }
   

	public void setCompleted(boolean b) {
		this.completed=b;
		
	}


	public void setResult(float f) {
		this.result=f;
		
	}
	public float getResult(){
		return this.result;
	}
}
