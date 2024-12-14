package application;

import java.util.ArrayList;

public class InstructionHandler {
	 static int floatStoreLatency;
     static int intStoreLatency;
     static int floatLoadLatency;
     static int intLoadLatency;
     static int floatAddLatency;
     static int intAddLatency;
     static int floatDivLatency;
     static int intDivLatency;
     static int floatMulLatency;
     static int intMulLatency;
     static int floatSubLatency;
     static int intSubLatency;
     static int branchLatency;
     ArrayList<Instruction> instructions = new ArrayList<>();
	public static int getFloatStoreLatency() {
		return floatStoreLatency;
	}

	public static void setFloatStoreLatency(int floatStoreLatency) {
		InstructionHandler.floatStoreLatency = floatStoreLatency;
	}

	public static int getIntStoreLatency() {
		return intStoreLatency;
	}

	public static void setIntStoreLatency(int intStoreLatency) {
		InstructionHandler.intStoreLatency = intStoreLatency;
	}

	public static int getFloatLoadLatency() {
		return floatLoadLatency;
	}

	public static void setFloatLoadLatency(int floatLoadLatency) {
		InstructionHandler.floatLoadLatency = floatLoadLatency;
	}

	public static int getIntLoadLatency() {
		return intLoadLatency;
	}

	public static void setIntLoadLatency(int intLoadLatency) {
		InstructionHandler.intLoadLatency = intLoadLatency;
	}

	public static int getFloatAddLatency() {
		return floatAddLatency;
	}

	public static void setFloatAddLatency(int floatAddLatency) {
		InstructionHandler.floatAddLatency = floatAddLatency;
	}

	public static int getIntAddLatency() {
		return intAddLatency;
	}

	public static void setIntAddLatency(int intAddLatency) {
		InstructionHandler.intAddLatency = intAddLatency;
	}

	public static int getFloatDivLatency() {
		return floatDivLatency;
	}

	public static void setFloatDivLatency(int floatDivLatency) {
		InstructionHandler.floatDivLatency = floatDivLatency;
	}

	public static int getIntDivLatency() {
		return intDivLatency;
	}

	public static void setIntDivLatency(int intDivLatency) {
		InstructionHandler.intDivLatency = intDivLatency;
	}

	public static int getFloatMulLatency() {
		return floatMulLatency;
	}

	public static void setFloatMulLatency(int floatMulLatency) {
		InstructionHandler.floatMulLatency = floatMulLatency;
	}

	public static int getIntMulLatency() {
		return intMulLatency;
	}

	public static void setIntMulLatency(int intMulLatency) {
		InstructionHandler.intMulLatency = intMulLatency;
	}

	public static int getFloatSubLatency() {
		return floatSubLatency;
	}

	public static void setFloatSubLatency(int floatSubLatency) {
		InstructionHandler.floatSubLatency = floatSubLatency;
	}

	public static int getIntSubLatency() {
		return intSubLatency;
	}

	public static void setIntSubLatency(int intSubLatency) {
		InstructionHandler.intSubLatency = intSubLatency;
	}

	public static int getBranchLatency() {
		return branchLatency;
	}

	public static void setBranchLatency(int branchLatency) {
		InstructionHandler.branchLatency = branchLatency;
	}



	
	
	public InstructionHandler() {
        this.instructions = new ArrayList<>();
    }
	/*
	
	 public void decodeInstruction(String instruction) {
		 System.out.println("decode method");
		 System.out.println(" current instructions"+instruction);
		 System.out.println("ending printing ");
		 
	        String[] lines = instruction.split("\n"); // Split the input into individual lines
	        for (String s : lines) {
	        	Instruction i1=new Instruction();
	            int length = s.length();
	            int counter = 0;
	            String current = "";

	            // Loading type (operation)
	            while (counter < length && s.charAt(counter) != ' ') {
	                current += s.charAt(counter);
	                counter++;
	            }
	            System.out.println("Type: " + current);
	            i1.setType(current);
	            current = "";
	        
	            // Skip space after type
	            while (counter < length && (s.charAt(counter) == ' ' || s.charAt(counter) == ',')) {
	                counter++;
	            }
	        
	            // Loading target
	            while (counter < length && s.charAt(counter) != ',' && s.charAt(counter) != ' ') {
	                current += s.charAt(counter);
	                counter++;
	            }
	            System.out.println("Target: " + current);
	            i1.setTarget(current);
	         
	            current = "";
	        
	            // Skip comma and any spaces to the next part
	            while (counter < length && (s.charAt(counter) == ' ' || s.charAt(counter) == ',')) {
	                counter++;
	            }
	        
	            // Loading operand1 (if exists)
	            while (counter < length && s.charAt(counter) != ',' && s.charAt(counter) != ' ') {
	                current += s.charAt(counter);
	                counter++;
	            }
	            if (!current.isEmpty()) {
	                System.out.println("Operand1: " + current);
	                i1.setOperand1(current);
	                current = "";
	            }
	        
	            // Skip comma and spaces to the next operand
	            while (counter < length && (s.charAt(counter) == ' ' || s.charAt(counter) == ',')) {
	                counter++;
	            }
	        
	            // Loading operand2, if any
	            while (counter < length && s.charAt(counter) != ' ' && s.charAt(counter) != ',') {
	                current += s.charAt(counter);
	                counter++;
	            }
	            i1.setOperand2(current);
	           
	                System.out.println("Operand2: " + current);
	            
	            System.out.println(); // Print a newline for readability between instructions
	            instructions.add(i1);
	        }
	        
	        
	        System.out.println("Decoded and added: " + instruction);
	        //ShowList();
	    }
	 
	 
	 
	 
	 /*
	 public void loadCycles() {
		 System.out.println("loadcycles method");
		    for (Instruction instruction : instructions) {
		        String type = instruction.getType();
		        switch (type) {
		            case "DADDI":
		            case "DSUBI":
		                instruction.setNumberOfCycles(intAddLatency);  // Assuming same latency for integer add and sub
		                break;
		            case "ADD.D":
		            case "SUB.D":
		            	System.out.println("in the add condition");
		                instruction.setNumberOfCycles(floatAddLatency);  // Assuming same latency for floating point add and sub
		                break;
		            case "ADD.S":
		            case "SUB.S":
		                instruction.setNumberOfCycles(intAddLatency);  // Assuming same latency for simple precision
		                break;
		            case "MUL.D":
		                instruction.setNumberOfCycles(floatMulLatency);
		                break;
		            case "MUL.S":
		                instruction.setNumberOfCycles(intMulLatency);  // Assuming same latency for integer and single precision multiply
		                break;
		            case "DIV.D":
		                instruction.setNumberOfCycles(floatDivLatency);
		                break;
		            case "DIV.S":
		                instruction.setNumberOfCycles(intDivLatency);  // Assuming same latency for integer and single precision divide
		                break;
		            case "LW":
		            case "LD":
		            case "L.S":
		            case "L.D":
		                instruction.setNumberOfCycles(intLoadLatency);  // Load instructions
		                break;
		            case "SW":
		            case "SD":
		            case "S.S":
		            case "S.D":
		                instruction.setNumberOfCycles(intStoreLatency);  // Store instructions
		                break;
		            case "BNE":
		            case "BEQ":
		                instruction.setNumberOfCycles(branchLatency);  // Branch instructions
		                break;
		            default:
		                System.out.println("Unknown instruction type: " + type);
		                instruction.setNumberOfCycles(1);  // Default cycle count if type is unknown
		        }
		    }
		    ShowList();
		}
*/

	 
	 public void ShowList() {
		 System.out.println("this is the show list method");
		 System.out.println("the current size of instructions is "+this.instructions.size());
		 for(int i=0;i<instructions.size();i++) {
			 System.out.println(instructions.get(i));
		 }
	 }
	    }
	 
	 


