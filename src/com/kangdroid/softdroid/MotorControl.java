package com.kangdroid.softdroid;

import lejos.hardware.Button;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class MotorControl {
	 //Register PORT first or program will throw NPE.
	 static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	 static SampleProvider distanceMode = us.getDistanceMode();
	 
 public static void main(String [] args) {
	 // Enable  ultrasonic sensor
	 us.enable();
	 Helpers.resetGyrosensors();
	 
	 try {
		 // move straight for infinite and stop if US detects in range of 10.
		 while (true) {
		     // INIT basic US Sensor 
			 float[] sample = new float[distanceMode.sampleSize()];
		     distanceMode.fetchSample(sample, 0);
		     System.out.println("Angle is " + sample[0]); 
		     
			 if (Button.ESCAPE.isDown() != true) {
				 Helpers.moveForwardInfinite(); 
			 } else {
				 System.exit(0);
			 }
	     
	     if (Button.ESCAPE.isDown() != true) { 	 
	     		if (sample[0]*100 <= 20) {
	     			Helpers.avoidObject();
	    	 			continue;
	     		}
	     }
	    }
	 }
	 catch(java.lang.Exception e) {
         System.out.println(e.toString());
         Delay.msDelay(3000);
	 } 
	 us.close();
	 Helpers.closeTheMotors();
	 Helpers.unregisterGyrosensors();
 }
}
