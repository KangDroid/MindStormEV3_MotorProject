package com.kangdroid.softdroid;

public class MotorControl {
	 
 public static void main(String [] args) {
	 // Enable  ultrasonic sensor
	 Helpers.us.enable();
	 Helpers.resetGyrosensors();
	 
	 try {
		 // move straight for infinite and stop if US detects in range of 10.
		 while (true) {
		     Helpers.moveForwardInfinite(); 
		     
		     // INIT basic US Sensor 
			 float[] sample = new float[Helpers.distanceMode.sampleSize()];
		     Helpers.distanceMode.fetchSample(sample, 0);
		     
     		if (sample[0]*100 <= 20) {
     			Helpers.avoidObject();
    	 			continue;
     		}
	    }
	 }
	 catch(java.lang.Exception e) {
         System.out.println(e.toString());
	 } 
	 Helpers.us.close();
	 Helpers.closeTheMotors();
	 Helpers.unregisterGyrosensors();
 }
}
