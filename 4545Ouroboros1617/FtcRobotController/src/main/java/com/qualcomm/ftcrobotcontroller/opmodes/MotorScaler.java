package com.qualcomm.ftcrobotcontroller.opmodes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * This class is designed to fix the deficiencies between the Modern Robotics platform and
 * the AndyMark 40 Motor in the RUN_WITHOUT_ENCODERS mode. Normally, the relationship between the value sent to the motor
 * and the power the motor is run at is logarithmic. So if you send it a value of 0.25 for example,
 * the motor will run at about 80% power. When calling scaleSimple or scaleExact like this:
 * motorL.setPower(MotorScaler.scaleExact(gamepad1.right_stick_y));
 * at a right stick y value of 0.2, the motor would be run at 20% power.
 * The scaleControlled methods are intended to increase motor accuracy at lower powers.
 * This is most useful when driving a robot during the Driver Controlled period where accurate
 * controls are needed with a joystick, e.x. an arm that picks up things is being used.
 *
 * @author SpencerSharp
 * @version 1.0, Spring 2016
 */

public abstract class MotorScaler
{
    private MotorScaler()
    {

    }

    /**
     * Returns a double that represents the "properly scaled" value from controller input to AndyMark 40 Motor.
     * Essentially, makes the % of total mA for the motor sent the same as the % amount the
     * joystick is pushed. For example, 0.25 sent would return a value that, when sent to the motor,
     * runs the motor at 25% of its mA capacity.
     * <p>
     * This method is optimized to have minimal runtime, but is less accurate as a result.
     * Use when lots of method runs per second (Setting motor power in teleop)
     *
     * @param  inpFromController  a double representing the value inputted from the controller
     * @return a double representing the value sent to the motor in order to obtain the mA value
     * @see    MotorScaler.scaleExact(double inpFromController)
     */
    public double scaleSimple(double inpFromController)
    {
        double valToMotor;                              //Value to return as the value that should be send to the motor
        double[] sendValues = {                         //Array representing all values that can be sent, in increments of 0.05
                0.0,                                    //For example, index 0 is accessed if inpFromController < 0.05
                0.01,                                   //And index 1 is accessed if inpFromController >= 0.05 and inpFromController < 0.10
                0.02,
                0.03,
                0.04,
                0.05,
                0.06,
                0.07,
                0.075,
                0.08,
                0.09,
                0.10,
                0.113,
                0.126,
                0.14,
                0.15,
                0.19,
                0.225,
                1.0};
        int index = ((int)inpFromController*100)/5; //Index to be accessed from valToMotor array, returns the value to send
        valToMotor=sendValues[index];               //Sets valToMotor to the correct value to return
        if(inpFromController<0)                     //If the controller value is negative
            valToMotor*=-1;                         //Flip the value to return
        return valToMotor;
    }

    /**
     * Returns a double that represents the "properly scaled" value from controller input to AndyMark 40 Motor.
     * Essentially, makes the % of total mA for the motor sent the same as the % amount the
     * joystick is pushed. For example, 0.25 sent would return a value that, when sent to the motor,
     * runs the motor at 25% of its mA capacity.
     * <p>
     * Use this method for standard movement, or autonomous powers. This method is slower
     * in runtime, but is more precise. Use if your input value needs to exactly match
     * match the output. (Autonomous one time set)
     *
     * @param  inpFromController  a double representing the value inputted from the controller
     * @return a double representing the value sent to the motor in order to obtain the mA value
     * @see    MotorScaler.scaleSimple(double inpFromController)
     */
    public double scaleExact(double inpFromController)
    {
        double valToMotor = 0.0;


        return valToMotor;
    }

    /**
     * Returns a double that represents the "exponentially scaled" value from controller input to
     * the AndyMark 40 Motor. Essentially, makes the relationship between value from the controller
     * and power the motor is run at into an exponential one. This makes it much easier to control at low speeds.
     * The relationship isn't exactly exponential, but intended to resemble one.
     * <p>
     * This method should be used to control the robot when precise movement is needed. Also use this vs.
     * scaleControlledExact when multiple method calls per second are being made. (Ex. TeleOp driving)
     *
     * @param  inpFromController  a double representing the value inputted from the controller
     * @return a double representing the value sent to the motor in order to obtain the mA value
     * @see    MotorScaler.scaleControlledExact(double inpFromController)
     */
    public double scaleControlledSimple(double inpFromController)
    {
        double valToMotor = 0.0;



        return valToMotor;
    }

    /**
     * Returns a double that represents the "exponentially scaled" value from controller input to
     * the AndyMark 40 Motor. Essentially, makes the relationship between value from the controller
     * and power the motor is run at into an exponential one. This makes it much easier to control at low speeds.
     * The relationship isn't exactly exponential, but intended to resemble one.
     * <p>
     * This method should be used to control the robot when precise movement is needed. Also use this vs.
     * scaleControlledSimple when one or less method calls per second are needed, or extreme precision.
     *
     * @param  inpFromController  a double representing the value inputted from the controller
     * @return a double representing the value sent to the motor in order to obtain the mA value
     * @see    MotorScaler.scaleControlledSimple(double inpFromController)
     */
    public double scaleControlledExact(double inpFromController)
    {
        double valToMotor = 0.0;



        return valToMotor;
    }
}