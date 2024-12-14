package application;


public class Instruction {
    private InstructionType type;
    private int operand1Register;
    private int operand2Register;
    private int TargetRegister;
    private long immediate;
    private int address;
    private int issuedAt;
    private int startedAt;
    private int finishedAt;
    private int writeBackAt;
    private int instructionId;
    private int branchLabel;
    private String instructionLabel;

    public Instruction(int instructionId,InstructionType type, int operand1Register, int operand2Register, int TargetRegister, long immediate, int address, int branchLabel, String instructionLabel) {
        this.instructionId = instructionId;
        this.type = type;
        this.operand1Register = operand1Register;
        this.operand2Register = operand2Register;
        this.TargetRegister = TargetRegister;
        this.immediate = immediate;
        this.address = address;
        this.branchLabel = branchLabel;
        this.instructionLabel = instructionLabel;
        this.issuedAt = -1;
        this.startedAt = -1;
        this.finishedAt = -1;
        this.writeBackAt = -1;
    }
    
    
    
    

    

    public InstructionType getType() {
		return type;
	}







	public void setType(InstructionType type) {
		this.type = type;
	}







	public int getOperand1Register() {
		return operand1Register;
	}







	public void setOperand1Register(int operand1Register) {
		this.operand1Register = operand1Register;
	}







	public int getOperand2Register() {
		return operand2Register;
	}







	public void setOperand2Register(int operand2Register) {
		this.operand2Register = operand2Register;
	}







	public int getTargetRegister() {
		return TargetRegister;
	}







	public void setTargetRegister(int targetRegister) {
		TargetRegister = targetRegister;
	}







	public long getImmediate() {
		return immediate;
	}







	public void setImmediate(long immediate) {
		this.immediate = immediate;
	}







	public int getAddress() {
		return address;
	}







	public void setAddress(int address) {
		this.address = address;
	}







	public int getIssuedAt() {
		return issuedAt;
	}







	public void setIssuedAt(int issuedAt) {
		this.issuedAt = issuedAt;
	}







	public int getStartedAt() {
		return startedAt;
	}







	public void setStartedAt(int startedAt) {
		this.startedAt = startedAt;
	}







	public int getFinishedAt() {
		return finishedAt;
	}







	public void setFinishedAt(int finishedAt) {
		this.finishedAt = finishedAt;
	}







	public int getWriteBackAt() {
		return writeBackAt;
	}







	public void setWriteBackAt(int writeBackAt) {
		this.writeBackAt = writeBackAt;
	}







	public int getInstructionId() {
		return instructionId;
	}







	public void setInstructionId(int instructionId) {
		this.instructionId = instructionId;
	}







	public int getBranchLabel() {
		return branchLabel;
	}







	public void setBranchLabel(int branchLabel) {
		this.branchLabel = branchLabel;
	}







	public String getInstructionLabel() {
		return instructionLabel;
	}







	public void setInstructionLabel(String instructionLabel) {
		this.instructionLabel = instructionLabel;
	}







	@Override
    public String toString() {
        return "Instruction{" +
                "type=" + type +
                ", operand1Register= " + operand1Register +
                ", operand2Register= " + operand2Register +
                ", TargetRegister= " + TargetRegister +
                ", immediate= " + immediate +
                ", address= " + address +
                ", issuedAt= " + issuedAt +
                ", startedAt= " + startedAt +
                ", finishedAt= " + finishedAt +
                ", writeBackAt= " + writeBackAt +
                ", instructionId= " + instructionId +
                ", branchLabel= " + branchLabel  +
                ", instructionLabel= '" + instructionLabel + '\'' +
                '}';
    }
}
