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
        r.setPort("/dev/tty.wch ch341 USB=>RS232 fa130");
        r.connect(); 
        motor(r);
        ping(r);
        servo(r);
        shaftEncoder(r);
        r.close(); 
    }
    
    public static void motor(RXTXRobot r) {
        r.runMotor(RXTXRobot.MOTOR1, 50, 30000);
    }
    
    public static void ping(RXTXRobot r) {
        final int PING_PIN = 4;
        for (int x=0; x < 10; ++x) 
	{ 
            //Read the ping sensor value, which is connected to pin 12 
            System.out.println("Response: " + r.getPing(PING_PIN) + " cm"); 
            r.sleep(300); 
	}
    }
    
    public static void servo(RXTXRobot r) {
        r.attachServo(RXTXRobot.SERVO1, 7);
        r.sleep(1000);
        r.moveServo(RXTXRobot.SERVO1, 135);
        r.sleep(1000);
        r.moveServo(RXTXRobot.SERVO1, 90);
    }
    
    public static void shaftEncoder(RXTXRobot r) {
        System.out.println(r.getEncodedMotorPosition(RXTXRobot.MOTOR1));
        r.runEncodedMotor(RXTXRobot.MOTOR1, 125, 500);
        System.out.println(r.getEncodedMotorPosition(RXTXRobot.MOTOR1));
    }
}
