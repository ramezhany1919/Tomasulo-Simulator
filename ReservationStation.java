package application;

public class ReservationStation {
	private ReservationStationE[] stations;
    private int size;
    private String suffix;
    public ReservationStation(String suffix,int size ) {
  	  this.size=size;
  	  this.suffix=suffix;
  	  this.stations=new ReservationStationE[size];
  	  for(int i=0;i<size;i++) {
  		  this.stations[i]=new ReservationStationE(suffix+(i+1));
  	  }
  	  
    }
    
    
    public ReservationStationE[] getStations() {
		return stations;
	}


	public void setStations(ReservationStationE[] stations) {
		this.stations = stations;
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public String getSuffix() {
		return suffix;
	}


	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public boolean isEmpty() {
  	  for(int i=0;i<size;i++) {
  		  if(stations[i].isBusy()) {
  			  return false;
  		  }
  	  }
  	  return true;
    }
    
    
    public boolean isFull() {
  	  for(int i=0;i<size;i++) {
  		  if(!stations[i].isBusy()) {
  			  return false;
  		  }
  	  }
  	  return true;
    }
    
    
    
    
  


	public String establishInstruction (int time, boolean busy, InstructionType op, Object vj, Object vk, String qj, String qk, int address, Instruction instruction) {
        for (int i = 0; i < size; i++) {
            if (!stations[i].isBusy()) {
                stations[i].setTime(time);
                stations[i].setBusy(busy);
                stations[i].setOp(op);
                stations[i].setVj(vj);
                stations[i].setVk(vk);
                stations[i].setQj(qj);
                stations[i].setQk(qk);
                stations[i].setAddress(address);
                stations[i].setInstruction(instruction);
                return stations[i].getTag();
            }
        }

        return "";
    }
}
