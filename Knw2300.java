/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knw2300;
import rxtxrobot.*;
/**
 *
 * @author Ashley
 */
public class Knw2300 {
    
    public static void main(String[] args) {        
        RXTXRobot r = new ArduinoNano();
        r.setPort("/dev/tty.wch ch341 USB=>RS232 fd120");
        r.connect(); 
        runBothMotors(r);
        //ping(r);
        //servo(r);
        //shaftEncoder(r);
        r.close(); 
    }
    
    public static void motor(RXTXRobot r) {
        // Turn for 3 meters
        r.runMotor(RXTXRobot.MOTOR1, 50, 30000);
    }
    
    public static void ping(RXTXRobot r) {
        // >15cm & <15cm
        final int PING_PIN = 4;
        for (int x=0; x < 10; ++x) 
	{ 
            //Read the ping sensor value, which is connected to pin 12 
            System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
            r.sleep(300); 
	}
    }
    
    public static void servo(RXTXRobot r) {
        // Angle specified and then back
        r.attachServo(RXTXRobot.SERVO1, 7);
        r.sleep(1000);
        r.moveServo(RXTXRobot.SERVO1, 135);
        r.sleep(1000);
        r.moveServo(RXTXRobot.SERVO1, 90);
    }
    
    public static void shaftEncoder(RXTXRobot r) {
        // Turn for 500 ticks
        System.out.println(r.getEncodedMotorPosition(RXTXRobot.MOTOR1));
        r.runEncodedMotor(RXTXRobot.MOTOR1, 125, 500);
        System.out.println(r.getEncodedMotorPosition(RXTXRobot.MOTOR1));
    }
    
    public static void runBothMotors(RXTXRobot r) {
        r.runEncodedMotor(RXTXRobot.MOTOR1, -250, 4581, RXTXRobot.MOTOR2, 250, 4581); // Run both motors forward for 3m 
	r.sleep(5000); // Pause execution for 5 seconds, but the motors keep running. 
	r.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors 
    }
    
    public static void runBothMotors(RXTXRobot r, int x){
    	//run motor for x meters
        r.runEncodedMotor(RXTXRobot.MOTOR1, -250, 1527*x, RXTXRobot.MOTOR2, 250, 1527*x); // Run both motors forward for 3m 
	r.sleep(5000); // Pause execution for 5 seconds, but the motors keep running. 
	r.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0); // Stop both motors 
    }
    
    public static void bumpSensor(RXTXRobot r) {
        // Motor until bump sensor is tapped
        r.refreshAnalogPins();
        System.out.println(r.getAnalogPin(0));
        //r.getAnalogPin(1);
        //r.getAnalogPin(2);
        //r.getAnalogPin(3);
    }
    
    public static void runBothMotorsBump(RXTXRobot r) {
        // Motor until bump sensor is tapped
        r.refreshAnalogPins();
        int bump0 = r.getAnalogPin(0).getValue();
        int bump1 = r.getAnalogPin(1).getValue();
        int bump2 = r.getAnalogPin(2).getValue();
        int bump3 = r.getAnalogPin(3).getValue();
        while(true){
        	r.runMotor(RXTXRobot.MOTOR1, 500, RXTXRobot.MOTOR2, 500, 0); // Run both motors forward indefinitely 
        	if(bump0!=0||bump1!=0||bump2!=0||bump3!=0){
        		r.runMotor(RXTXRobot.MOTOR1,0,RXTXRobot.MOTOR2,0,0);
        	}
        	r.sleep(50);
        }
} 