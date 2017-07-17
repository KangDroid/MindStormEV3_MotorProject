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
	 
	 //Local Variables
	 static int right;
	 static int left;
	 static int maxlimit;
	 
 public static void main(String [] args) {
	 // Enable  ultrasonic sensor
	 us.enable();
	 
	 try {
		 // move straight for infinite and stop if US detects in range of 10.
		 while (true) {
			 if (Button.ESCAPE.isDown() != true) {
				 MotorHelper.moveForwardInfinite(); 
			 } else {
				 System.exit(0);
			 }
	     // INIT basic US Sensor 
	     float[] sample = new float[distanceMode.sampleSize()];
	     distanceMode.fetchSample(sample, 0);
	     
	     if (Button.ESCAPE.isDown() != true) { 	 
	     		if (sample[0]*100 <= 10) {
	     			avoidObject();
	    	 			continue;
	     		}
	     }
	    }
	 }
	 catch(java.lang.Exception e) {
         System.out.println(e.toString());
	 }
	 
	 // Disable ultrasonic sensor & Disable Motor
     us.close();
     MotorHelper.closeTheMotors();
 }
 
 public static void avoidObject() {
	 if (Button.ESCAPE.isDown() != true) {
			MotorHelper.stopTheMotors();
			MotorHelper.turnRight();
			MotorHelper.moveForwardInfinite();
			Delay.msDelay(4000);
			MotorHelper.stopTheMotors();
			MotorHelper.turnLeft();
			MotorHelper.moveForwardInfinite();
			Delay.msDelay(4000);
			MotorHelper.stopTheMotors();
			MotorHelper.turnLeft();
			MotorHelper.moveForwardInfinite();
			Delay.msDelay(4000);
			MotorHelper.stopTheMotors();
			MotorHelper.turnRight();
	 } else {
		 System.exit(0);
	 }
 }
 
}
