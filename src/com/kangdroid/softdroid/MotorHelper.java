package com.kangdroid.softdroid;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class MotorHelper {
	
	 //Register PORT first or program will throw NPE.
	 static EV3LargeRegulatedMotor LeftMotor = new EV3LargeRegulatedMotor(MotorPort.B);
	 static EV3LargeRegulatedMotor RightMotor = new EV3LargeRegulatedMotor(MotorPort.C);
	 
	 //Local Variables
	 static int right;
	 static int left;
	 static int maxlimit;

	 public static void moveForwardInfinite() {
		 //Reset Motor just in case
		 LeftMotor.resetTachoCount();
		 RightMotor.resetTachoCount();
		 //getTachoCount -- get current number of Tacho.
		 // Set motor speed to 720 value.
		 LeftMotor.setSpeed(100);
		 RightMotor.setSpeed(100);
		 // Now Let's move for 2 second.(2000ms)
		 LeftMotor.forward();
		 RightMotor.forward();
	 }
	 
	 public static void stopTheMotors() {
		 // Stop motor (stop smoothly) : For now, do not stop motors since both motors are not stopping at same time
		 LeftMotor.stop(true);
		 RightMotor.stop(true);
		 // Close motor
		 //LeftMotor.close();
		 //RightMotor.close();
	 }
	 
	 public static void closeTheMotors() {
		 // Close motor
		 LeftMotor.close();
		 RightMotor.close();
	 }
	 
	 public static void moveForwardandStop() {
		 //Reset Motor just in case
		 LeftMotor.resetTachoCount();
		 RightMotor.resetTachoCount();
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
		 // Reset Degree counter
		 LeftMotor.resetTachoCount();
		 // Get Tacho count of current motor and add 380 to turn 90
		 right = LeftMotor.getTachoCount() + 380;
		 //Set speed to 100 for left motor and ROTATE
		 LeftMotor.setSpeed(100);
		 LeftMotor.rotate(right);
		 //LeftMotor.close();
	 }
	 public static void turnLeft() {
		 // Reset Degree counter
		 RightMotor.resetTachoCount();
		 // Get Tacho count of current motor and add 380 to turn 90
		 left = RightMotor.getTachoCount() + 380;
		 //Set speed to 100 for left motor and ROTATE
		 RightMotor.setSpeed(100);
		 RightMotor.rotate(left);
		 //RightMotor.close();
	 }
	
}
