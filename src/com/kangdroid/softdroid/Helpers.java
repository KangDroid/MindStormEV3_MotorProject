package com.kangdroid.softdroid;

import lejos.hardware.Button;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Helpers {
	
	 //Register PORT first or program will throw NPE.
	 static EV3LargeRegulatedMotor LeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	 static EV3LargeRegulatedMotor RightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	 static EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S1);
	 static SampleProvider gyroSamples = gyro.getAngleMode();
	 
	 
	 //Local Variables
	 static int right;
	 static int left;
	 static int maxlimit;

	 public static void moveForwardInfinite() {
		 LeftMotor.setSpeed(100);
		 RightMotor.setSpeed(100);
		 LeftMotor.forward();
		 RightMotor.forward();
	 }
	 
	 public static void stopTheMotors() {
		 // Stop motor (stop smoothly) : For now, do not stop motors since both motors are not stopping at same time
		 LeftMotor.stop(true);
		 RightMotor.stop(true);
	 }
	 
	 public static void closeTheMotors() {
		 // Close motor
		 if (LeftMotor != null) {
			 LeftMotor.close();
		 }
		 if (RightMotor != null) {
			 RightMotor.close();
		 }
	 }
	 
	 public static void moveForwardandStop() {
		 //Reset Motor just in case
		 //LeftMotor.resetTachoCount();
		 //RightMotor.resetTachoCount();
		 //getTachoCount -- get current number of Tacho.
		 // Set motor speed to 720 value.
		 LeftMotor.setSpeed(100);
		 RightMotor.setSpeed(100);
		 // Now Let's move for 2 second.(2000ms)
		 LeftMotor.forward();
		 RightMotor.forward();
		 Delay.msDelay(2000);
		 // Stop motor (stop smoothly) : For now, do not stop motors since both motors are not stopping at same time
		 LeftMotor.stop(true);
		 RightMotor.stop(true);
		 // Close motor
		 LeftMotor.close();
		 RightMotor.close();
		// Delay.msDelay(2000);
	 }
	 public static void turnRight() {
		 LeftMotor.setSpeed(100);
		 LeftMotor.forward();
	 }
	 
	 public static void turnLeft() {
		 RightMotor.setSpeed(100);
		 RightMotor.forward();
	 }
	 
	  public static void avoidObject() {
		 if (Button.ESCAPE.isDown() != true) {
			 resetGyrosensors();
				stopTheMotors();
				while (true) {
					float [] testing = new float[gyroSamples.sampleSize()];
					gyroSamples.fetchSample(testing, 0);
					 if (testing[0] >= -87f) {
						 turnRight();
					 } else {
						 LeftMotor.stop(true);
						 resetGyrosensors();
						 break;
					 }
				}
				moveForwardInfinite();
				Delay.msDelay(4000);
				stopTheMotors();
				while (true) {
					float [] testing = new float[gyroSamples.sampleSize()];
					gyroSamples.fetchSample(testing, 0);
					 if (testing[0] <= 87f) {
						 Helpers.turnLeft();
					 } else {
						 RightMotor.stop(true);
						 resetGyrosensors();
						 break;
					 }
				}
				moveForwardInfinite();
				Delay.msDelay(4000);
				stopTheMotors();
				while (true) {
					float [] testing = new float[gyroSamples.sampleSize()];
					gyroSamples.fetchSample(testing, 0);
					 if (testing[0] <= 87f) {
						 Helpers.turnLeft();
					 } else {
						 RightMotor.stop(true);
						 resetGyrosensors();
						 break;
					 }
				}
				moveForwardInfinite();
				Delay.msDelay(4000);
				stopTheMotors();
				while (true) {
					float [] testing = new float[gyroSamples.sampleSize()];
					gyroSamples.fetchSample(testing, 0);
					 if (testing[0] >= -87f) {
						 Helpers.turnRight();
					 } else {
						 LeftMotor.stop(true);
						 resetGyrosensors();
						 break;
					 }
				}
		 } else {
			 System.exit(0);
		 }
	 }
	 
	 /* This is the gyro sensor part. This one includes registering/unregistering of gyro sensor and getting angle as well. */
	 
	 public static void resetGyrosensors() {
		 gyro.reset();
	 }
	 
	 public static void unregisterGyrosensors() {
		 gyro.close();
	 }
}
