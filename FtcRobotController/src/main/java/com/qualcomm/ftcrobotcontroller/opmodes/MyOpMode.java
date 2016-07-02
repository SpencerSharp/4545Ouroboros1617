package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.EventLoopManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.math.*;
import android.util.Log;
import com.qualcomm.robotcore.exception.RobotCoreException;

/**
 * @author      Spencer Sharp <address @ example.com>
 * @author      Steven Lo
 * @version     1.6                 (current version number of program)
 * @since       1.2          (the version of the package this class was first added to)
 */
public class MyOpMode
{
    //IMU name
    AdafruitIMU imu;

    //Motors
    /**
     * DcMotor variable for our front left motor
     */
    DcMotor motorFL;
    /**
     * DcMotor variable for our back left motor
     */
    DcMotor motorBL;
    /**
     * DcMotor variable for our front right motor
     */
    DcMotor motorFR;
    DcMotor motorBR;
    DcMotor motorLiftL;
    DcMotor motorLiftR;
    DcMotor motorManipL;
    DcMotor motorManipR;

    //Servos
    Servo servoBasketL;
    Servo servoBasketR;
    Servo servoHookL;
    Servo servoHookR;

    public void initializeMotors()
    {
        //Wheel motors
        motorFL = hardwareMap.dcMotor.get("motorFL");
        motorBL = hardwareMap.dcMotor.get("motorBL");
        motorFR = hardwareMap.dcMotor.get("motorFR");
        motorBR = hardwareMap.dcMotor.get("motorBR");
        //Lift motors
        motorLiftL = hardwareMap.dcMotor.get("motorLiftL");
        motorLiftR = hardwareMap.dcMotor.get("motorLiftR");
        //Manipulator motors
        motorManipL = hardwareMap.dcMotor.get("motorManipL");
        motorManipR = hardwareMap.dcMotor.get("motorManipR");
    }

    public void initializeServos()
    {
        //Turntable servos
        servoBasketL = hardwareMap.servo.get("servoBasketL");
        servoBasketR = hardwareMap.servo.get("servoBasketR");
        //Hook servos
        servoHookL = hardwareMap.servo.get("servoHookL");
        servoHookR = hardwareMap.servo.get("servoHookR");
    }

    public void initializeIMU()
    {
        try
        {
            imu = new AdafruitIMU(hardwareMap, "bno055"
                    , (byte)(AdafruitIMU.BNO055_ADDRESS_A * 2)//By convention the FTC SDK always does 8-bit I2C bus
                    , (byte)AdafruitIMU.OPERATION_MODE_IMU);
        } catch (RobotCoreException e){
            Log.i("FtcRobotController", "Exception: " + e.getMessage());
        }
    }

    public void startIMU()
    {
        imu.startIMU();
    }

    @Override
    public void init()
    {
        initializeMotors();
        initializeServos();
        initializeIMU();
    }

    @Override
    public void init_loop()
    {

    }

    @Override
    public void start()
    {
        startIMU();
    }

    //Parent loop
    @Override
    public void loop()
    {

    }

    @Override
    public void stop()
    {

    }
}