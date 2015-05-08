package com.util;

public class DictEnum {
	   public enum FTPHomeType {
		   DAM("1"), Publisher("2"), Channel("3");
		   private final String value;
		   FTPHomeType(String value) {
	            this.value = value;
	        }
	        public String getValue() {
	            return value;
	        }
	    }
	   //资产状态
	   public enum assetsStatus{
		   Draft(0),Delivery(1),DeliveryCompleted(2),WaitingProcess(3),Processing(4),
		   ProcessCompleted(5),WaitingPrice(6),PriceCompleted(7),WaitingPuton(8),
		   Puton(9),PutonSuccess(1),PutonFailure(11),WaitingRePuton(12),
		   RePuton(13),WaitingPriceUpdate(14),PriceUpdate(15),PriceUpdateSuccess(16),PriceUpdateFailure(17),
		   WaitingMetadataUpdate(18),MetadataUpdate(19),MetadataUpdateSuccess(20),
		   MetadataUpdateFailure(21),WaitingEBookUpdate(22),EBookUpdate(23),EBookUpdateSuccess(24),
		   EBookUpdateFailure(25),ShelvesIn(26),Shelves(27),UnderShelves(28),Under(29),Delete(99);
		   private final int value;
		   assetsStatus(int value){
			   this.value= value;
		   }
		   
		   public int getValue(){
			   return value;
		   }
	   }
}
