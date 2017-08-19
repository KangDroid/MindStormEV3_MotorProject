package com.kangdroid.softdroid;

import lejos.robotics.RangeFinderAdapter;
import lejos.utility.Delay;

public class MotorControl {
	static RangeFinderAdapter sonar;
 public static void main(String [] args) {
	 /* New Algorithm sketch: 
	  * 1. Move forward(Infinite)
	  * 2. Stop if object or obstacle detected
	  * 3. go back a while, maybe 3000ms.
	  * 4. turn left till US Detection_range: 30 >>>
	  * 5. gogogogo
	  * 6. loop.  */
	 
	 sonar = new RangeFinderAdapter(Helpers.us);
	 
	 // Enable  ultrasonic sensor
	 Helpers.us.enable();
	 Helpers.resetGyrosensors();
	 
	 try {
		 while (true) {
		 while (findRange() > 10) {
			 Helpers.moveForwardInfinite();
		 }
		 Helpers.stopTheMotors();
		 
		 Helpers.goBackward();
		 Delay.msDelay(700);
		 Helpers.stopTheMotors();
		 
		 while (findRange() < 40) {
			 System.out.println("Going Left:" + findRange());
			 Helpers.turnBackwardLeft();
			 //Delay.msDelay(4000);
		 }
		 Helpers.stopTheMotors();
		 }
	 }
	 catch(java.lang.Exception e) {
         System.out.println(e.toString());
	 } 
	 Helpers.us.close();
	 Helpers.closeTheMotors();
	 Helpers.unregisterGyrosensors();
	 
 } 
 
 public static float findRange() {
 	return sonar.getRange();
 }
 
 // Due to experiment, Back up main one.
/* public void backup() {
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
 } */
 
}
