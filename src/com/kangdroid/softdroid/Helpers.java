package com.kangdroid.softdroid;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Helpers {
	
	 //Register PORT first or program will throw NPE.
	 static EV3LargeRegulatedMotor LeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	 static EV3LargeRegulatedMotor RightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	 static EV3GyroSensor gyro = new EV3GyroSensor(SensorPort.S1);
	 static SampleProvider gyroSamples = gyro.getAngleMode();
	 static EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
	 //static SampleProvider distanceMode = us.getDistanceMode();

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

	 public static void turnRight() {
		 LeftMotor.setSpeed(100);
		 LeftMotor.forward();
	 }
	 
	 public static void turnLeft() {
		 RightMotor.setSpeed(100);
		 RightMotor.forward();
	 }
	 
	 /* Functions for Back-Driving System. This won't be used until I simulate all-Best algorithm. */
	 
	 // Heading Left
	 public static void turnBackwardLeft() {
		 LeftMotor.setSpeed(100);
		 LeftMotor.backward();
	 }
	 
	 // Heading Right
	 public static void turnBackwardRight() {
		 RightMotor.setSpeed(100);
		 RightMotor.backward();
	 }
	 
	 // Going back
	 public static void goBackward() {
		 LeftMotor.setSpeed(100);
		 RightMotor.setSpeed(100);
		 LeftMotor.backward();
		 RightMotor.backward();
	 }
	 
	  public static void avoidObject() {
		  stopTheMotors();
		  //resetGyrosensors();
			while (true) {
				float [] testing = new float[gyroSamples.sampleSize()];
				gyroSamples.fetchSample(testing, 0);
				 if (testing[0] >= -90f) {
					 turnRight();
				 } else {
					 LeftMotor.stop(true);
					 //resetGyrosensors();
					 break;
				 }
			}
			moveForwardInfinite();
			Delay.msDelay(4000);
			stopTheMotors();
			while (true) {
				float [] testing = new float[gyroSamples.sampleSize()];
				gyroSamples.fetchSample(testing, 0);
				 if (testing[0] <= -0f) {
					 Helpers.turnLeft();
				 } else {
					 RightMotor.stop(true);
					 //resetGyrosensors();
					 break;
				 }
			}
			moveForwardInfinite();
			Delay.msDelay(4000);
			stopTheMotors();
			while (true) {
				float [] testing = new float[gyroSamples.sampleSize()];
				gyroSamples.fetchSample(testing, 0);
				 if (testing[0] <= 90f) {
					 Helpers.turnLeft();
				 } else {
					 RightMotor.stop(true);
					 //resetGyrosensors();
					 break;
				 }
			}
			moveForwardInfinite();
			Delay.msDelay(4000);
			stopTheMotors();
			while (true) {
				float [] testing = new float[gyroSamples.sampleSize()];
				gyroSamples.fetchSample(testing, 0);
				 if (testing[0] >= -0f) {
					 Helpers.turnRight();
				 } else {
					 LeftMotor.stop(true);
					 //resetGyrosensors();
					 break;
				 }
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
