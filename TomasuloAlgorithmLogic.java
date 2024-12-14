package application;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button; // Correct import for JavaFX Button
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class TomasuloAlgorithmLogic{
	//InstructionID  
	public int instructionId=0;
	//InstructionQueue
	private ArrayList<Instruction> instructionQueue;
	//ALL_ReservationStations
	private ReservationStation addSubReservationStation;
	private ReservationStation mulDivReservationStation;
	private ReservationStation loadReservationStation;
	private ReservationStation storeReservationStation;
	//WIL_LORCAN_BE_EDITED
	private ReservationStation addsubIntReservationStation;
	//ALL_ReservationStations Sizes
	private int addSubReservationStationsize;
	private int mulDivReservationStationsize;
	private int loadReservationStationsize;
	private int storeReservationStationsize;
	
	//RegistersFiles
	private ArrayList<FloatRegFile>floatRegfile;
	private ArrayList<IntRegFile> integerRegFile;
	private int floatRegFilesize=32;
	private int intRegFilesize=32;
	
	
	
	//Data-Bus
	private Bus dataBus;
	private Bus displayBus=new Bus();
	
	public Bus getDisplayBus() {
		return displayBus;
	}








	public void setDisplayBus(Bus displayBus) {
		this.displayBus = displayBus;
	}























	//Program-Counter
	private int pc;
	
	//Clock-Cycle
	private int cycle;
	
	private String cacheStartAddress;
	private String[] cacheAddress;







	//LatencyofEachInstruction
	private int floatAddLatency;
	private int intAddLatency;
	private int floatSubLatency;
	private int intSubLatency;
	private int floatMulLatency;
	private int intMulLatency;
	private int floatDivLatency;
	private int intDivLatency;
	private int floatLoadLatency;
	private int intLoadLatency;
	private int floatStoreLatency;
	private int intStoreLatency;
	private int branchLatency;
	
	//BRANCH-STALL
	private boolean branchStall;
	
	public int getInstructionId() {
		return instructionId;
	}








	public void setInstructionId(int instructionId) {
		this.instructionId = instructionId;
	}








	public boolean isBranchStall() {
		return branchStall;
	}








	public void setBranchStall(boolean branchStall) {
		this.branchStall = branchStall;
	}








	














	//Cache
	private long cachesize;
	private long cacheBlockSize;
	
	private long numberOfBlocks;
	private double[] cache = new double[124];
	private boolean cacheMiss=true;
	
	//Memory
	private ArrayList<Byte> Memory;
	
	
	
	public double[] getCache() {
		return cache;
	}








	public void setCache(double[] cache) {
		this.cache = cache;
	}

    
    public ArrayList<Instruction> getInstructionQueue() {
		return instructionQueue;
	}








	public void setInstructionQueue(ArrayList<Instruction> instructionQueue) {
		this.instructionQueue = instructionQueue;
	}








	public ReservationStation getAddSubReservationStation() {
		return addSubReservationStation;
	}








	public void setAddSubReservationStation(ReservationStation addSubReservationStation) {
		this.addSubReservationStation = addSubReservationStation;
	}








	public ReservationStation getMulDivReservationStation() {
		return mulDivReservationStation;
	}








	public void setMulDivReservationStation(ReservationStation mulDivReservationStation) {
		this.mulDivReservationStation = mulDivReservationStation;
	}








	public ReservationStation getLoadReservationStation() {
		return loadReservationStation;
	}








	public void setLoadReservationStation(ReservationStation loadReservationStation) {
		this.loadReservationStation = loadReservationStation;
	}








	public ReservationStation getStoreReservationStation() {
		return storeReservationStation;
	}








	public void setStoreReservationStation(ReservationStation storeReservationStation) {
		this.storeReservationStation = storeReservationStation;
	}








	public ReservationStation getAddsubIntReservationStation() {
		return addsubIntReservationStation;
	}








	public void setAddsubIntReservationStation(ReservationStation addsubIntReservationStation) {
		this.addsubIntReservationStation = addsubIntReservationStation;
	}








	public ArrayList<FloatRegFile> getFloatRegfile() {
		return floatRegfile;
	}








	public void setFloatRegfile(ArrayList<FloatRegFile> floatRegfile) {
		this.floatRegfile = floatRegfile;
	}








	public ArrayList<IntRegFile> getIntegerRegFile() {
		return integerRegFile;
	}








	public void setIntegerRegFile(ArrayList<IntRegFile> integerRegFile) {
		this.integerRegFile = integerRegFile;
	}








	public int getFloatRegFilesize() {
		return floatRegFilesize;
	}








	public void setFloatRegFilesize(int floatRegFilesize) {
		this.floatRegFilesize = floatRegFilesize;
	}








	public int getIntRegFilesize() {
		return intRegFilesize;
	}








	public void setIntRegFilesize(int intRegFilesize) {
		this.intRegFilesize = intRegFilesize;
	}








	public Bus getDataBus() {
		return dataBus;
	}








	public void setDataBus(Bus dataBus) {
		this.dataBus = dataBus;
	}








	public int getPc() {
		return pc;
	}








	public void setPc(int pc) {
		this.pc = pc;
	}








	public int getFloatAddLatency() {
		return floatAddLatency;
	}








	public void setFloatAddLatency(int floatAddLatency) {
		this.floatAddLatency = floatAddLatency;
	}








	public int getIntAddLatency() {
		return intAddLatency;
	}








	public void setIntAddLatency(int intAddLatency) {
		this.intAddLatency = intAddLatency;
	}








	public int getFloatSubLatency() {
		return floatSubLatency;
	}








	public void setFloatSubLatency(int floatSubLatency) {
		this.floatSubLatency = floatSubLatency;
	}








	public int getIntSubLatency() {
		return intSubLatency;
	}








	public void setIntSubLatency(int intSubLatency) {
		this.intSubLatency = intSubLatency;
	}








	public int getFloatMulLatency() {
		return floatMulLatency;
	}








	public void setFloatMulLatency(int floatMulLatency) {
		this.floatMulLatency = floatMulLatency;
	}








	public int getIntMulLatency() {
		return intMulLatency;
	}








	public void setIntMulLatency(int intMulLatency) {
		this.intMulLatency = intMulLatency;
	}








	public int getFloatDivLatency() {
		return floatDivLatency;
	}








	public void setFloatDivLatency(int floatDivLatency) {
		this.floatDivLatency = floatDivLatency;
	}








	public int getIntDivLatency() {
		return intDivLatency;
	}








	public void setIntDivLatency(int intDivLatency) {
		this.intDivLatency = intDivLatency;
	}








	public int getFloatLoadLatency() {
		return floatLoadLatency;
	}








	public void setFloatLoadLatency(int floatLoadLatency) {
		this.floatLoadLatency = floatLoadLatency;
	}








	public int getIntLoadLatency() {
		return intLoadLatency;
	}








	public void setIntLoadLatency(int intLoadLatency) {
		this.intLoadLatency = intLoadLatency;
	}








	public int getFloatStoreLatency() {
		return floatStoreLatency;
	}








	public void setFloatStoreLatency(int floatStoreLatency) {
		this.floatStoreLatency = floatStoreLatency;
	}








	public int getIntStoreLatency() {
		return intStoreLatency;
	}








	public void setIntStoreLatency(int intStoreLatency) {
		this.intStoreLatency = intStoreLatency;
	}








	public int getBranchLatency() {
		return branchLatency;
	}








	public void setBranchLatency(int branchLatency) {
		this.branchLatency = branchLatency;
	}








	public long getCachesize() {
		return cachesize;
	}








	public void setCachesize(long cachesize) {
		this.cachesize = cachesize;
	}








	public long getCacheBlockSize() {
		return cacheBlockSize;
	}








	public void setCacheBlockSize(long cacheBlockSize) {
		this.cacheBlockSize = cacheBlockSize;
	}








	public long getNumberOfBlocks() {
		return numberOfBlocks;
	}








	public void setNumberOfBlocks(long numberOfBlocks) {
		this.numberOfBlocks = numberOfBlocks;
	}








	public ArrayList<Byte> getMemory() {
		return Memory;
	}








	public void setMemory(ArrayList<Byte> memory) {
		Memory = memory;
	}
	
	








	public int getAddSubReservationStationsize() {
		return addSubReservationStationsize;
	}








	public void setAddSubReservationStationsize(int addSubReservationStationsize) {
		this.addSubReservationStationsize = addSubReservationStationsize;
	}








	public int getMulDivReservationStationsize() {
		return mulDivReservationStationsize;
	}








	public void setMulDivReservationStationsize(int mulDivReservationStationsize) {
		this.mulDivReservationStationsize = mulDivReservationStationsize;
	}








	public int getLoadReservationStationsize() {
		return loadReservationStationsize;
	}








	public void setLoadReservationStationsize(int loadReservationStationsize) {
		this.loadReservationStationsize = loadReservationStationsize;
	}








	public int getStoreReservationStationsize() {
		return storeReservationStationsize;
	}








	public void setStoreReservationStationsize(int storeReservationStationsize) {
		this.storeReservationStationsize = storeReservationStationsize;
	}
	
	public int getCycle() {
		return this.cycle;
	}

	public void setCycle(int cycle) {
		this.cycle = cycle;
	}
	
	
	//METHODS
	//
	//
	//
	//
	public static byte[] floatToBytes(float value) {
        ByteBuffer buffer = ByteBuffer.allocate(Float.BYTES); // Float.BYTES is 4 bytes for float
        buffer.putFloat(value);
        return buffer.array();
    }

    public static float bytesToFloat(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getFloat();
    }

    public static byte[] doubleToBytes(double value) {
        ByteBuffer buffer = ByteBuffer.allocate(Double.BYTES); // Double.BYTES is 8 bytes for double
        buffer.putDouble(value);
        return buffer.array();
    }

    public static double bytesToDouble(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getDouble();
    }

    public static byte[] longToBytes(long value) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES); // Long.BYTES is 8 bytes for long
        buffer.putLong(value);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        return buffer.getLong();
    }

    public static byte[] intToBytes(int value) {
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES); // Integer.BYTES is 4 bytes for int
        buffer.putInt(value);
        return buffer.array();
    }

    public static int bytesToInt(byte[] bytes) {
    	ByteBuffer buffer=ByteBuffer.wrap(bytes);
    	return buffer.getInt();
    }
    
    public void loadMemory() {
    	for(int i=0;i<30;i++) {
    		byte[] result=intToBytes(10);
    		for(int j=0;j<result.length;j++) {
    			this.Memory.add(result[i]);
    		}
    	}
    }
	
	
	
	
	
	
	
	//TAKING-INPUTS-FROM-USER
	public void inputs() {
	    Scanner sc = new Scanner(System.in);
	    
	    System.out.println("Enter add/sub reservation size:");
	    this.setAddSubReservationStationsize(sc.nextInt());
	    
	    System.out.println("Enter mul/div reservation size:");
	    this.setMulDivReservationStationsize(sc.nextInt());
	    
	    System.out.println("Enter store reservation size:");
	    this.setStoreReservationStationsize(sc.nextInt());
	    
	    System.out.println("Enter load reservation size:");
	    this.setLoadReservationStationsize(sc.nextInt());
	    
	    System.out.println("Enter float add latency:");
	    this.setFloatAddLatency(sc.nextInt());
	    
	    System.out.println("Enter int add latency:");
	    this.setIntAddLatency(sc.nextInt());
	    
	    System.out.println("Enter float sub latency:");
	    this.setFloatSubLatency(sc.nextInt());
	    
	    System.out.println("Enter int sub latency:");
	    this.setIntSubLatency(sc.nextInt());
	    
	    System.out.println("Enter float mul latency:");
	    this.setFloatMulLatency(sc.nextInt());
	    
	    System.out.println("Enter int mul latency:");
	    this.setIntMulLatency(sc.nextInt());
	    
	    System.out.println("Enter float div latency:");
	    this.setFloatDivLatency(sc.nextInt());
	    
	    System.out.println("Enter int div latency:");
	    this.setIntDivLatency(sc.nextInt());
	    
	    System.out.println("Enter float load latency:");
	    this.setFloatLoadLatency(sc.nextInt());
	    
	    System.out.println("Enter int load latency:");
	    this.setIntLoadLatency(sc.nextInt());
	    
	    System.out.println("Enter float store latency:");
	    this.setFloatStoreLatency(sc.nextInt());
	    
	    System.out.println("Enter int store latency:");
	    this.setIntStoreLatency(sc.nextInt());
	    
	    System.out.println("Enter branch latency:");
	    this.setBranchLatency(sc.nextInt());
	    System.out.println("Enter Cache size:");
	    this.setCachesize(sc.nextInt());
	    System.out.println("Enter cache block size:");
	    this.setCacheBlockSize(sc.nextInt());
	}
	
	//LOADING-INSTRUCTIONS-FROM-TXTFILE
	
	public void loadInstructions(String filename) {
	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line;
	        int instructionId = 1; // Assuming instruction IDs start at 1 and increment for each instruction
	        while ((line = br.readLine()) != null) {
	            if (line.trim().isEmpty()) continue; // Skip empty lines
	            String[] tokens = line.split("\\s+"); // Split line into tokens
	            parseAndAddInstruction(tokens, instructionId);
	            instructionId++;
	        }
	    } catch (IOException e) {
	        System.err.println("Failed to read file: " + e.getMessage());
	    }
	}

	private void parseAndAddInstruction(String[] tokens, int instructionId) {
	    InstructionType type = null;
	    int rs = -1, rt = -1, rd = -1;
	    long immediate = 0;
	    int branchLabel = -1;
	    String instructionLabel = null;

	    int i = 0; // Start index for parsing
	    if (tokens[0].contains(":")) {
	        instructionLabel = tokens[0].substring(0, tokens[0].indexOf(':'));
	        i = 1; // Adjust to start parsing after label
	    }

	    String baseType = tokens[i].replace(".", "_").toUpperCase();
	    try {
	        type = InstructionType.valueOf(baseType);
	    } catch (IllegalArgumentException e) {
	        System.err.println("Unsupported instruction type: " + baseType);
	        return;
	    }

	    i++; // Move past the instruction type
	    switch (type) {
	        case ADD_D:
	        case SUB_D:
	        case MUL_D:
	        case DIV_D:
	            rd = parseRegister(tokens[i]);
	            rs = parseRegister(tokens[i + 1]);
	            rt = parseRegister(tokens[i + 2]);
	            break;
	        case LD:
	        case L_D:
	        case SD:
	        case S_D:
	            rt = parseRegister(tokens[i]); // Target register
	            immediate = Long.parseLong(tokens[i + 1].replaceAll(",", "")); // Immediate value
	            rs = -1; // No base register in this format
	            break;
	        case DADDI:
	        case DSUBI:
	            rd = parseRegister(tokens[i]); // Destination register
	            rs = parseRegister(tokens[i + 1]); // Source register
	            immediate = Long.parseLong(tokens[i + 2].replaceAll(",", "")); // Immediate value
	            break;
	        case BNE:
	case BEQ:
	            rs = parseRegister(tokens[i]);       // Source register 1
	            rt = parseRegister(tokens[i + 1]);   // Source register 2
	            branchLabel = Integer.parseInt(tokens[i + 2]); // Branch address
	            break;
	        default:
	            System.err.println("Unhandled instruction type: " + type);
	            return;
	    }

	    Instruction instruction = new Instruction(instructionId, type, rs, rt, rd, immediate, 0, branchLabel, instructionLabel);
	    instructionQueue.add(instruction);
	}

	private int parseRegister(String token) {
	    // Removing any trailing commas or non-numeric characters for safe parsing
	    token = token.replaceAll(",", "").replaceAll("\\D+$", ""); // Regex to remove non-digits at the end
	    if (token.startsWith("F") || token.startsWith("R")) {
	        return Integer.parseInt(token.substring(1)); // Remove the prefix and parse the number
	    } else {
	        throw new IllegalArgumentException("Invalid register identifier: " + token);
	    }
	}

	
	//LOADING-RANDOM-VALUES-FOR-REGISTERS
	

	public void loadRegisters() {
	    // Clear existing registers if you want to allow reloading
	    integerRegFile.clear();
	    floatRegfile.clear();

	    try (BufferedReader reader = new BufferedReader(new FileReader("src/registerFile.txt"))) {
	        String line;
	        int count = 0;
	        while ((line = reader.readLine()) != null && count < 64) { // Read up to 64 numbers
	            if (count < 32) {
	                // Parse and add to integer register file
	                long intValue = Long.parseLong(line.trim());
	                integerRegFile.add(new IntRegFile(intValue, "0")); // Assuming "0" is the initial tag for not being in use
	            } else {
	                // Parse and add to floating-point register file
	                double floatValue = Double.parseDouble(line.trim());
	                floatRegfile.add(new FloatRegFile(floatValue, "0")); // Assuming "0" is the initial tag for not being in use
	            }
	            count++;
	        }
	    } catch (FileNotFoundException e) {
	        System.err.println("The register file could not be found.");
	    } catch (IOException e) {
	        System.err.println("An error occurred while reading the register file.");
	    } catch (NumberFormatException e) {
	        System.err.println("Error parsing numbers from the register file. Ensure all entries are valid numbers.");
	    }
	}

	
	//INIT-THE-CLASS
	
public void initReservationStations() {
	this.addSubReservationStation=new ReservationStation("A",this.addSubReservationStationsize);
	this.mulDivReservationStation=new ReservationStation("M",this.mulDivReservationStationsize);
	this.loadReservationStation=new ReservationStation("L",this.loadReservationStationsize);
	this.storeReservationStation=new ReservationStation("S",this.storeReservationStationsize);
	this.instructionQueue=new ArrayList<>();
	this.integerRegFile=new  ArrayList<>();
	this.floatRegfile=new ArrayList<>();
	this.dataBus=new Bus();
	this.pc=0;
	this.cycle=1;
	this.cacheAddress=new String[(int)(this.getCachesize()/this.getCacheBlockSize())];
}


//PRINT-METHOD-THAT-PRINTS-EVERYTHING
public void print() {
	System.out.println("                       PROGRAM COUNTER: "+this.pc+"             ");
	System.out.println("                       Register Files: "+         "             ");
	for(int i=0;i<integerRegFile.size();i++) {
		System.out.println("R"+i+" "+integerRegFile.get(i));
	}
	for(int i=0;i<floatRegfile.size();i++) {
		System.out.println("F"+i+" "+floatRegfile.get(i));
	}
	
	System.out.println("                       Instructions "+         "             ");
	for(int i=0;i<instructionQueue.size();i++) {
		System.out.println(instructionQueue.get(i));
	}
	System.out.println("                       ADD/SUB RES "+         "             ");
	
	for(int i=0;i<addSubReservationStation.getSize();i++) {
		System.out.println(addSubReservationStation.getStations()[i].toString());
	}
System.out.println("                       MUL/DIV RES "+         "             ");
	
	for(int i=0;i<mulDivReservationStation.getSize();i++) {
		System.out.println(mulDivReservationStation.getStations()[i].toString());
	}
System.out.println("                       LOAD RES "+         "             ");
	
	for(int i=0;i<loadReservationStation.getSize();i++) {
		System.out.println(loadReservationStation.getStations()[i].toString());
	}
System.out.println("                       STORE RES "+         "             ");
	
	for(int i=0;i<addSubReservationStation.getSize();i++) {
		System.out.println(storeReservationStation.getStations()[i].toString());
	}
	System.out.println("                       CACHE "+         "             ");
	for(int i=0;i<this.cacheAddress.length;i++) {
		System.out.println(cacheAddress[i]);
	}

}


//ALL-ISSUE-METHODS ISSUE-INSTRUCTIONS-INTO-STATIONS
public String issueADDSUBInstruction(Instruction i) {
	System.out.println("SHADY "+this.branchStall);
	if (this.branchStall) {
        System.out.println("========== STALL, THERE IS A BRANCH STALL ==========");
        return "BRANCH STALL";
    }

    if (addSubReservationStation.isFull()) {
        System.out.println("========== STALL, The ADD/SUB RESERVATION STATION IS FULL ==========");
        return "STALL";
    }
	Object vj=0;
	Object vk=0;
	String qj="0";
	String qk="0";
	int time=-1;
	int issuedAt=this.cycle;
	Instruction currentInstruction=new Instruction(i.getInstructionId(),i.getType(),i.getOperand1Register(),i.getOperand2Register(),i.getTargetRegister(),i.getImmediate(),i.getAddress(),i.getBranchLabel(),i.getInstructionLabel());
	currentInstruction.setIssuedAt(issuedAt);
	InstructionType op=i.getType();
	String tag="";
	
	//Logic_Setting
	//Integer-ADD OR SUB
	if(op.equals(InstructionType.DADDI) || op.equals(InstructionType.DSUBI)) {
		if(integerRegFile.get(i.getOperand1Register()).getQ().equals("0")) {
			vj=integerRegFile.get(i.getOperand1Register()).getValue();
			System.out.println("RAMOUZA "+vj);
			System.out.println("RAMOUZA "+i.getOperand1Register());
			
		}
		else {
			qj=integerRegFile.get(i.getOperand1Register()).getQ();
		}
		
		
		
		vk=i.getImmediate();
		tag=addSubReservationStation.establishInstruction(time, true, op, vj, vk, qj, qk, i.getAddress(), currentInstruction);
		integerRegFile.get(i.getTargetRegister()).setQ(tag);
	}
	//Floating-ADD OR SUB
	if(op.equals(InstructionType.ADD_D) ||op.equals(InstructionType.ADD_S) || op.equals(InstructionType.SUB_D) || op.equals(InstructionType.SUB_S)) {
		if(floatRegfile.get(i.getOperand1Register()).getQ().equals("0")) {
			vj=floatRegfile.get(i.getOperand1Register()).getValue();
		}
		else {
			qj=floatRegfile.get(i.getOperand1Register()).getQ();
		}
		if(floatRegfile.get(i.getOperand2Register()).getQ().equals("0")) {
			vk=floatRegfile.get(i.getOperand2Register()).getValue();
		}
		else {
			qk=floatRegfile.get(i.getOperand2Register()).getQ();
		}
		//System.out.println("T3ALA HENA "+currentInstruction);
		tag=addSubReservationStation.establishInstruction(time, true, op, vj, vk, qj, qk, i.getAddress(), currentInstruction);
		
		floatRegfile.get(i.getTargetRegister()).setQ(tag);
		
		
		
	}
	if(op.equals(InstructionType.BEQ)) {
		if(integerRegFile.get(i.getOperand1Register()).getQ().equals("0")) {
			vj=integerRegFile.get(i.getOperand1Register()).getValue();
		}
		else {
			qj=integerRegFile.get(i.getOperand1Register()).getQ();
		}
		if(integerRegFile.get(i.getOperand2Register()).getQ().equals("0")) {
			vk=integerRegFile.get(i.getOperand2Register()).getValue();
		}
		else {
			qk=integerRegFile.get(i.getOperand2Register()).getQ();
		}
		this.branchStall=true;
		tag=addSubReservationStation.establishInstruction(time, true, op, vj, vk, qj, qk, i.getAddress(), currentInstruction);
		return tag;
	
	}
	if(op.equals(InstructionType.BNE)) {
		System.out.println("DAKHEL EL BNE");
		if(integerRegFile.get(i.getOperand1Register()).getQ().equals("0")) {
			vj=integerRegFile.get(i.getOperand1Register());
		}
		else {
			qj=integerRegFile.get(i.getOperand1Register()).getQ();
		}
		if(integerRegFile.get(i.getOperand2Register()).getQ().equals("0")) {
			vk=integerRegFile.get(i.getOperand2Register()).getValue();
		}
		else {
			qk=integerRegFile.get(i.getOperand2Register()).getQ();
		}
		this.branchStall=true;
		tag=addSubReservationStation.establishInstruction(time, true, op, vj, vk, qj, qk, i.getAddress(), currentInstruction);
		return tag;
	
	}
	

	return tag;
	
}

public String issueMULDIVInstruction(Instruction i) {
	if (mulDivReservationStation.isFull()) {
        System.out.println("========== STALL, The MUL/DIV RESERVATION STATION IS FULL ==========");
        return "STALL";
    }
	Object vj=0;
	Object vk=0;
	String qj="0";
	String qk="0";
	int time=-1;
	int issuedAt=this.cycle;
	Instruction currentInstruction=new Instruction(i.getInstructionId(),i.getType(),i.getOperand1Register(),i.getOperand2Register(),i.getTargetRegister(),i.getImmediate(),i.getAddress(),i.getBranchLabel(),i.getInstructionLabel());
	System.out.println("INSIDE THE MUL/DIV ISSUE");
	currentInstruction.setIssuedAt(issuedAt);
	System.out.println(currentInstruction+" HAS ISSUED");
	InstructionType op=i.getType();
	String tag="";
	if(op.equals(InstructionType.MUL_D) || op.equals(InstructionType.MUL_S) || op.equals(InstructionType.DIV_D) || op.equals(InstructionType.DIV_S)) {
		if(floatRegfile.get(i.getOperand1Register()).getQ().equals("0")) {
			vj=floatRegfile.get(i.getOperand1Register()).getValue();
		}
		else {
			qj=floatRegfile.get(i.getOperand1Register()).getQ();
		}
		if(floatRegfile.get(i.getOperand2Register()).getQ().equals("0")) {
			vk=floatRegfile.get(i.getOperand2Register()).getValue();
		}
		else {
			qk=floatRegfile.get(i.getOperand2Register()).getQ();
		}
	}
	tag=mulDivReservationStation.establishInstruction(time, true, op, vj, vk, qj, qk, i.getAddress(), currentInstruction);
	floatRegfile.get(i.getTargetRegister()).setQ(tag);
	
	return tag;
}

public String issueloadInstruction(Instruction i) {
	if (loadReservationStation.isFull()) {
        System.out.println("========== STALL, The LOAD RESERVATION STATION IS FULL ==========");
        return "STALL";
    }
	Object vj=0;
	Object vk=0;
	String qj="0";
	String qk="0";
	int time=-1;
	int issuedAt=this.cycle;
	InstructionType op=i.getType();
	Instruction currentInstruction=new Instruction(i.getInstructionId(),i.getType(),i.getOperand1Register(),i.getOperand2Register(),i.getTargetRegister(),i.getImmediate(),i.getAddress(),i.getBranchLabel(),i.getInstructionLabel());
	currentInstruction.setIssuedAt(issuedAt);
	String tag="";
	
	
		 tag=loadReservationStation.establishInstruction(time, true, i.getType(), vj, vk, qj, qk, (int)i.getImmediate(), currentInstruction);
		 
		 if(op.equals(InstructionType.L_D) ||op.equals(InstructionType.L_S) ) {
			 floatRegfile.get(i.getOperand2Register()).setQ(tag);
		 }
		 else {
			 integerRegFile.get(i.getTargetRegister()).setQ(tag);
		 }
		 String address=String.valueOf((int)i.getImmediate());
		 System.out.println( "ramez was here "+(int)i.getImmediate());
		 
	
		 this.setCacheStartAddress(address);
		 for(int j=0;j<this.cacheStartAddress.length();j++) {
			 String address2=String.valueOf(this.getCacheStartAddress());
			 String helper=String.valueOf(j);
			 System.out.println("RAMEZ ADDRESS "+address2);
			 this.cacheAddress[j]=address2+helper;
			 
		 }
		 
	
	return tag;
}

public String[] getCacheAddress() {
	return cacheAddress;
}








public void setCacheAddress(String[] cacheAddress) {
	this.cacheAddress = cacheAddress;
}








public String getCacheStartAddress() {
	return cacheStartAddress;
}








public void setCacheStartAddress(String cacheStartAddress) {
	this.cacheStartAddress = cacheStartAddress;
}








public String issueStoreInstruction(Instruction i) {
	 if (storeReservationStation.isFull()) {
         System.out.println("========== STALL, The STORE RESERVATION STATION IS FULL ==========");
         return "STALL";
     }

	
	Object vj=0;
	Object vk=0;
	String qj="0";
	String qk="0";
	int time=-1;
	int issuedAt=this.cycle;
	InstructionType op=i.getType();
	Instruction currentInstruction=new Instruction(i.getInstructionId(),i.getType(),i.getOperand1Register(),i.getOperand2Register(),i.getTargetRegister(),i.getImmediate(),i.getAddress(),i.getBranchLabel(),i.getInstructionLabel());
	currentInstruction.setIssuedAt(issuedAt);
	if(op.equals(InstructionType.SW) || op.equals(InstructionType.SD)) {
		if(integerRegFile.get(i.getOperand2Register()).getQ().equals("0")) {
			vj=integerRegFile.get(i.getOperand2Register()).getValue();
		}
		else {
			qj=integerRegFile.get(i.getOperand2Register()).getQ();
		}
	}
	if(op.equals(InstructionType.S_D) || op.equals(InstructionType.S_S)) {
		if(floatRegfile.get(i.getOperand2Register()).getQ().equals("0")) {
			vj=floatRegfile.get(i.getOperand2Register()).getValue();
		}
		else {
			qj=floatRegfile.get(i.getOperand2Register()).getQ();
		}
	}
	String tag=storeReservationStation.establishInstruction(time, true, i.getType(), vj, vk, qj, qk, i.getAddress(), currentInstruction);
	return tag;
	
}

public String issueInstruction(Instruction i) {
    InstructionType op = i.getType();
    switch (op) {
        case DADDI:
        case DSUBI:
        case ADD_D:
        case ADD_S:
        case SUB_D:
        case SUB_S:
       return issueADDSUBInstruction(i);
        case MUL_D:
        case MUL_S:
        case DIV_D:
        case DIV_S:
        return issueMULDIVInstruction(i);
        case LW: 
        case LD:  
        case L_S:
        case L_D:
        return issueloadInstruction(i);
        case SW:
        case SD:
        case S_S:
        case S_D:
        return issueStoreInstruction(i);
        case BNE:
            return issueADDSUBInstruction(i);  
        case BEQ:
            return issueADDSUBInstruction(i);
        default:
            return "";
    }
    
    
}



//METHOD-TO-GET-THE-LATENCY-OF-INSTRUCTION
public int getCycles(InstructionType op) {
    switch(op) {
        case DADDI:
        	return intAddLatency;
        case DSUBI:
            return intSubLatency;  
        case ADD_D:
            return floatAddLatency;
        case ADD_S:
            return intAddLatency;
        case SUB_D:
            return floatSubLatency;
        case SUB_S:
            return intSubLatency;
        case MUL_D:
            return floatMulLatency;
        case MUL_S:
            return intMulLatency;
        case DIV_D:
            return floatDivLatency;
        case DIV_S:
            return intDivLatency;
        case LW:
            return intLoadLatency; // If LW works with integer registers
        case LD:
            return floatLoadLatency;
        case L_S:
            return intLoadLatency; // Assuming L_S loads into scalar integer registers
        case L_D:
            return floatLoadLatency; // Assuming L_D loads into scalar floating-point registers
        case SW:
            return intStoreLatency; // If SW works with integer registers
        case SD:
            return floatStoreLatency;
        case S_S:
            return intStoreLatency; // Assuming S_S stores from scalar integer registers
        case S_D:
            return floatStoreLatency; // Assuming S_D stores from scalar floating-point registers
        case BNE:
        case BEQ:
            return branchLatency;
        default:
            throw new IllegalArgumentException("Unknown instruction type: " + op);
    }
}


//METHOD-TO-CLEAR-A-STATION
public void EmptyReservationStation(ReservationStationE station) {
	station.setBusy(false);
	station.setVj(0);
	station.setVk(0);
	station.setQj("0");
	station.setQk("0");
	station.setAddress(0);
	station.setOp(null);
	station.setInstruction(null);
	station.setTime(-1);
	
}

public void loadCache() {
	for(int i=0;i<cache.length;i++) {
		if(i>100) {
			cache[i]=(double) 103.0+i;
		}
		else {
			
		cache[i]=(double) 10.0+i;
		}
		
	}
}


//EXECUTE-METHOD-RAMEZ
public void execute(String tag) {
	for(int i=0;i<addSubReservationStation.getSize() && !addSubReservationStation.isEmpty();i++) {
	
		ReservationStationE currentStation=addSubReservationStation.getStations()[i];
		
		if(currentStation.isBusy() && currentStation.getTime()>0 && !currentStation.getTag().equals(tag)) {
			
			if(currentStation.getQj().equals("0") && currentStation.getQk().equals("0")) {
				if(currentStation.getTime()==getCycles(currentStation.getOp())) {
					System.out.println(currentStation.getInstruction()+" Started");
				}
				currentStation.setTime(currentStation.getTime()-1);
				if(currentStation.getTime()==0) {
					System.out.println(currentStation.getInstruction()+" Finished");
				}
			}
		}
		
	}
	
	for(int i=0;i<mulDivReservationStation.getSize() && !mulDivReservationStation.isEmpty();i++) {
		ReservationStationE currentStation=mulDivReservationStation.getStations()[i];
		if(currentStation.isBusy() && currentStation.getTime()>0 && !currentStation.getTag().equals(tag)) {
			if(currentStation.getQj().equals("0") && currentStation.getQk().equals("0")) {
				if(currentStation.getTime()==getCycles(currentStation.getOp())) {
					System.out.println(currentStation.getInstruction()+" Started");
				}
				currentStation.setTime(currentStation.getTime()-1);
				if(currentStation.getTime()==0) {
					System.out.println(currentStation.getInstruction()+" Finished");
				}
			}
		}
		
	}
	for(int i=0;i<loadReservationStation.getSize() && !loadReservationStation.isEmpty() ;i++) {
		
		ReservationStationE currentStation=loadReservationStation.getStations()[i];
		if(currentStation.isBusy() && currentStation.getTime()>0 && !currentStation.getTag().equals(tag)) {
			if(currentStation.getQj().equals("0") && currentStation.getQk().equals("0")) {
				if(currentStation.getTime()==getCycles(currentStation.getOp())) {
					System.out.println(currentStation.getInstruction()+" Started");
				}
				currentStation.setTime(currentStation.getTime()-1);
				if(currentStation.getTime()==0) {
					System.out.println(currentStation.getInstruction()+" Finished");
				}
			}
		}
		
	}
	for(int i=0;i<storeReservationStation.getSize() && !storeReservationStation.isEmpty();i++) {
		ReservationStationE currentStation=storeReservationStation.getStations()[i];
		if(currentStation.isBusy() && currentStation.getTime()>0 && !currentStation.getTag().equals(tag)) {
			if(currentStation.getQj().equals("0") && currentStation.getQk().equals("0")) {
				if(currentStation.getTime()==getCycles(currentStation.getOp())) {
					System.out.println(currentStation.getInstruction()+" Started");
				}
				currentStation.setTime(currentStation.getTime()-1);
				if(currentStation.getTime()==0) {
					System.out.println(currentStation.getInstruction()+" Finished");
				}
			}
		}
		
	}
	
	
	
}

//UPDATE-STATIONS
public void updateStations() {
	
	String busTag=dataBus.getTag();
	Object busValue=dataBus.getValue();
	
	//Update the Stations
	
	
	for(int i=0;i<addSubReservationStation.getSize();i++) {
		ReservationStationE currentStation=addSubReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.getQj().equals(busTag)) {
			currentStation.setVj(busValue);
			currentStation.setQj("0");
		}
		if(currentStation.getQk().equals(busTag)) {
			currentStation.setVk(busValue);
			currentStation.setQk("0");
		}
		if(currentStation.isBusy() && currentStation.getQj().equals("0") && currentStation.getQk().equals("0") && currentStation.getTime()==-1 ) {
			currentStation.setTime(getCycles(currentinstruction.getType()));
			currentinstruction.setStartedAt(this.cycle+1);
			currentinstruction.setFinishedAt(this.cycle+getCycles(currentinstruction.getType()));
			currentinstruction.setWriteBackAt(this.cycle+getCycles(currentinstruction.getType())+1);
		}
		
	}
	for(int i=0;i<mulDivReservationStation.getSize();i++) {
		ReservationStationE currentStation=mulDivReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.getQj().equals(busTag)) {
			currentStation.setVj(busValue);
			currentStation.setQj("0");
		}
		if(currentStation.getQk().equals(busTag)) {
			currentStation.setVk(busValue);
			currentStation.setQk("0");
		}
		if(currentStation.isBusy() && currentStation.getQj().equals("0") && currentStation.getQk().equals("0") && currentStation.getTime()==-1) {
			currentStation.setTime(getCycles(currentinstruction.getType()));
			currentinstruction.setStartedAt(this.cycle+1);
			currentinstruction.setFinishedAt(this.cycle+getCycles(currentinstruction.getType()));
			currentinstruction.setWriteBackAt(this.cycle+getCycles(currentinstruction.getType())+1);
		}
		
	}
	for(int i=0;i<loadReservationStation.getSize();i++) {
		ReservationStationE currentStation=loadReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.getQj().equals(busTag)) {
			currentStation.setVj(busValue);
			currentStation.setQj("0");
		}
		if(currentStation.getQk().equals(busTag)) {
			currentStation.setVk(busValue);
			currentStation.setQk("0");
		}
		if(currentStation.isBusy() && currentStation.getQj().equals("0") && currentStation.getQk().equals("0") && currentStation.getTime()==-1) {
			if(!cacheMiss) {
				currentStation.setTime(getCycles(currentinstruction.getType()));
				currentinstruction.setStartedAt(this.cycle+1);
				currentinstruction.setFinishedAt(this.cycle+getCycles(currentinstruction.getType()));
				currentinstruction.setWriteBackAt(this.cycle+getCycles(currentinstruction.getType())+1);
				cacheMiss=true;
			}
			else {
				currentStation.setTime(getCycles(currentinstruction.getType())+2);
				currentinstruction.setStartedAt(this.cycle+1);
				currentinstruction.setFinishedAt(this.cycle+getCycles(currentinstruction.getType())+2);
				currentinstruction.setWriteBackAt(this.cycle+getCycles(currentinstruction.getType())+1+2);
				cacheMiss=false;
			}
			
		}
		
	}
	for(int i=0;i<storeReservationStation.getSize();i++) {
		ReservationStationE currentStation=storeReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.getQj().equals(busTag)) {
			currentStation.setVj(busValue);
			currentStation.setQj("0");
		}
		if(currentStation.getQk().equals(busTag)) {
			currentStation.setVk(busValue);
			currentStation.setQk("0");
		}
		if(currentStation.isBusy() && currentStation.getQj().equals("0") && currentStation.getQk().equals("0") && currentStation.getTime()==-1) {
			currentStation.setTime(getCycles(currentinstruction.getType()));
			currentinstruction.setStartedAt(this.cycle+1);
			currentinstruction.setFinishedAt(this.cycle+getCycles(currentinstruction.getType()));
			currentinstruction.setWriteBackAt(this.cycle+getCycles(currentinstruction.getType())+1);
		}
		
	}
	
	
	//Update the Reg-Files
	for(int i=0;i<floatRegfile.size();i++) {
		 FloatRegFile currentRegfile=floatRegfile.get(i);
		 if(currentRegfile.getQ().equals(busTag)) {
			 currentRegfile.setQ("0");
			 currentRegfile.setValue(((Number) busValue).doubleValue());
		 }
	}
	for(int i=0;i<integerRegFile.size();i++) {
		 IntRegFile currentRegfile=integerRegFile.get(i);
		 if(currentRegfile.getQ().equals(busTag)) {
			 currentRegfile.setQ("0");
			 currentRegfile.setValue((long)busValue);
		 }
	}
	
}
//Helpermethod for me to the WB (JUST PRINTS ONLY NO LOGIC)
public void print2(Instruction i) {
	System.out.println(i+" Writes on the bus");
}
//WRITE-BACK-METHOD
/*

public void WriteBack() {
	ArrayList<String> instructionsTowrite=new ArrayList<>();
	for(int i=0;i<addSubReservationStation.getSize();i++) {
		ReservationStationE currentStation=addSubReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.isBusy()  &&this.cycle>=currentinstruction.getWriteBackAt()) {
			instructionsTowrite.add(currentStation.getTag());
		}
	}
	for(int i=0;i<mulDivReservationStation.getSize();i++) {
		ReservationStationE currentStation=mulDivReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.isBusy()  &&this.cycle>=currentinstruction.getWriteBackAt()) {
			instructionsTowrite.add(currentStation.getTag());
		}
	}
	
	for(int i=0;i<loadReservationStation.getSize();i++) {
		ReservationStationE currentStation=loadReservationStation.getStations()[i];
		Instruction currentinstruction=currentStation.getInstruction();
		if(currentStation.isBusy()  &&this.cycle>=currentinstruction.getWriteBackAt()) {
			instructionsTowrite.add(currentStation.getTag());
		}
	}
	if(instructionsTowrite.isEmpty()) {
		System.out.println("NO INSTRUCTIONS FINISHED");
	}
	else {
		for(int i=0;i<instructionsTowrite.size();i++) {
		String currentTag=instructionsTowrite.get(i);
		for(int j=0;i<addSubReservationStation.getSize();i++) {
			ReservationStationE currentStation=addSubReservationStation.getStations()[i];
			Instruction currentinstruction=currentStation.getInstruction();
			if(currentStation.getQj().equals(currentTag) || currentStation.getQk().equals(currentTag)) {
				addDataonBus(currentinstruction,currentStation);	
			}
		}
		for(int j=0;i<mulDivReservationStation.getSize();i++) {
			ReservationStationE currentStation=mulDivReservationStation.getStations()[i];
			Instruction currentinstruction=currentStation.getInstruction();
			if(currentStation.getQj().equals(currentTag) || currentStation.getQk().equals(currentTag)) {
				addDataonBus(currentinstruction,currentStation);	
			}
		}
		for(int j=0;i<loadReservationStation.getSize();i++) {
			ReservationStationE currentStation=loadReservationStation.getStations()[i];
			Instruction currentinstruction=currentStation.getInstruction();
			if(currentStation.getQj().equals(currentTag) || currentStation.getQk().equals(currentTag)) {
				addDataonBus(currentinstruction,currentStation);	
			}
		}
		
		
			
		}
	}
	
	
}
*/


public void WriteBack() {
	

    for (int i = 0; i < storeReservationStation.getSize(); i++) {
        ReservationStationE currentStation = storeReservationStation.getStations()[i];
        Instruction currentinstruction = currentStation.getInstruction();
        if (currentStation.isBusy() && this.cycle >= currentinstruction.getWriteBackAt() && currentinstruction.getWriteBackAt() != -1) {
            cache[currentStation.getAddress()] = (double) currentStation.getVj();
            EmptyReservationStation(currentStation);
        }
    }

    for (int i = 0; i < addSubReservationStation.getSize(); i++) {
        ReservationStationE currentStation = addSubReservationStation.getStations()[i];
        Instruction currentinstruction = currentStation.getInstruction();
        if (currentStation.isBusy() && this.cycle >= currentinstruction.getWriteBackAt() && currentinstruction.getWriteBackAt() != -1) {
            if (currentStation.getOp().equals(InstructionType.BEQ)) {
                branchStall = false;
                if ((long) currentStation.getVj() == (long)currentStation.getVk()) {
                    System.out.println(currentinstruction + " Branch is taken");
                    pc = currentinstruction.getBranchLabel();
                } else {
                    System.out.println(currentinstruction + " Branch is not taken");
                    this.pc++;
                }
                EmptyReservationStation(currentStation);
                continue;
            }
            if (currentStation.getOp().equals(InstructionType.BNE)) {
                branchStall = false;
                if ((long) currentStation.getVj() != (long)currentStation.getVk()) {
                    System.out.println(currentinstruction + " Branch is taken");
                    pc = currentinstruction.getBranchLabel();
                } else {
                    System.out.println(currentinstruction + " Branch is not taken");
                    this.pc++;
                }
                EmptyReservationStation(currentStation);
                continue;
            }
            switch (currentStation.getOp()) {
                case ADD_D:
                case ADD_S:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    double addResult = (double) currentStation.getVj() + (double) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(addResult);
                    EmptyReservationStation(currentStation);
                    return;
                case SUB_S:
                case SUB_D:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    double subResult = (double) currentStation.getVj() - (double) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(subResult);
                    EmptyReservationStation(currentStation);
                    return;
                case DADDI:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    long daddResult = (long) currentStation.getVj() + (long) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(daddResult);
                    EmptyReservationStation(currentStation);
                    return;
                case DSUBI:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    long dsubResult = (long) currentStation.getVj() - (long) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(dsubResult);
                    EmptyReservationStation(currentStation);
                    return;
               
            }
        }
    }

    for (int i = 0; i < mulDivReservationStation.getSize(); i++) {
    	ReservationStationE currentStation = mulDivReservationStation.getStations()[i];
        Instruction currentinstruction = currentStation.getInstruction();
        if (currentStation.isBusy() && this.cycle >= currentinstruction.getWriteBackAt() && currentinstruction.getWriteBackAt() != -1) {
            switch (currentStation.getOp()) {
                case MUL_D:
                case MUL_S:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    double mulResult = (double) currentStation.getVj() * (double) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(mulResult);
                    EmptyReservationStation(currentStation);
                    return;
                case DIV_S:
                case DIV_D:
                    System.out.println(currentinstruction + " writes on the common data bus");
                    double divResult =  (double) currentStation.getVj() / (double) currentStation.getVk();
                    dataBus.setTag(currentStation.getTag());
                    dataBus.setValue(divResult);
                    EmptyReservationStation(currentStation);
                    return;
            }
        }
    }

    for (int i = 0; i < loadReservationStation.getSize(); i++) {
        ReservationStationE currentStation = loadReservationStation.getStations()[i];
        Instruction currentinstruction = currentStation.getInstruction();
        if (currentStation.isBusy() && this.cycle >= currentinstruction.getWriteBackAt() && currentinstruction.getWriteBackAt() != -1) {
            System.out.println(currentinstruction + " writes on the common data bus");
            double loadResult = cache[currentStation.getAddress()];
            dataBus.setTag(currentStation.getTag());
            dataBus.setValue(loadResult);
            EmptyReservationStation(currentStation);
            return;
        }
    }
}













public void addDataonBus(Instruction i,ReservationStationE station) {
	print2(i);
	InstructionType op=i.getType();
	switch(op) {
	case DADDI:
	long resultaddi=(long) station.getVj()+i.getImmediate();
	dataBus.setTag(station.getTag());
	dataBus.setValue(resultaddi);
	EmptyReservationStation(station);
	break;
	case ADD_D:
	case ADD_S:
		long resultadd_d = ((Number) station.getVj()).longValue() + ((Number) station.getVk()).longValue();
		dataBus.setTag(station.getTag());
		dataBus.setValue(resultadd_d);
		EmptyReservationStation(station);
		break;
	case DSUBI:
		long resultsubi=(long) station.getVj()-i.getImmediate();
		dataBus.setTag(station.getTag());
		dataBus.setValue(resultsubi);
		EmptyReservationStation(station);
		break;
	case SUB_D:
	case SUB_S:
		long resultsub_d = ((Number) station.getVj()).longValue() - ((Number) station.getVk()).longValue();
		dataBus.setTag(station.getTag());
		dataBus.setValue(resultsub_d);
		EmptyReservationStation(station);
		break;
	
	case MUL_S:
	case MUL_D:
	double resultmult_d=(double) station.getVj() * (double) station.getVk();
	dataBus.setTag(station.getTag());
	dataBus.setValue(resultmult_d);
	EmptyReservationStation(station);
	break;
	
	case DIV_S:
	case DIV_D:
	double resultdiv_d=(double) station.getVj() / (double) station.getVk();
	dataBus.setTag(station.getTag());
	dataBus.setValue(resultdiv_d);
	EmptyReservationStation(station);
	break;
	
	case LW:
	case LD:
	case L_D:
	case L_S:
		double loadresult=cache[station.getAddress()];
		dataBus.setTag(station.getTag());
		dataBus.setValue(loadresult);
		EmptyReservationStation(station);
		break;
}
}


public boolean AllStationsEmpty() {
	return this.addSubReservationStation.isEmpty() && this.mulDivReservationStation.isEmpty() && this.loadReservationStation.isEmpty() && this.storeReservationStation.isEmpty();
}

public void tomasulo() {
	//System.out.println(instructionQueue);
	if(cycle >= 3) {
	if ((((!AllStationsEmpty()) || cycle == 1 || pc < instructionQueue.size())) ) {
		System.out.println("                       CYCLE "+this.cycle+"             ");
		if(pc <instructionQueue.size()) {
			
		String tag=issueInstruction(instructionQueue.get(pc));
		execute(tag);
		WriteBack();
		updateStations();
		setCycle(getCycle()+1);
		
	
	
		if (this.branchStall || tag.equals("STALL") || tag.equals("BRANCH STALL")) {
            this.print();
            this.displayBus.setTag(this.dataBus.getTag());
            this.displayBus.setValue(this.dataBus.getValue());
            this.dataBus.clearBus();
            return;
        }
        
		this.setPc(this.getPc()+1);
		
		}
		else {
			execute("");
			WriteBack();
			updateStations();
			setCycle(getCycle()+1);
			
			
		}
		
		
		this.print();
		  this.displayBus.setTag(this.dataBus.getTag());
          this.displayBus.setValue(this.dataBus.getValue());
		this.dataBus.clearBus();
	}
	}
	else {
		this.setCycle(this.getCycle()+1);
	}
	
}


	public static void main(String[] args) {
    	TomasuloAlgorithmLogic tomasulo=new TomasuloAlgorithmLogic();
    	tomasulo.inputs();
    	tomasulo.initReservationStations();
    	tomasulo.loadInstructions("src/instructions.txt");
    	tomasulo.loadRegisters();
    	tomasulo.loadCache();
    	tomasulo.tomasulo();
    	//tomasulo.print();
    	
    }
    
}