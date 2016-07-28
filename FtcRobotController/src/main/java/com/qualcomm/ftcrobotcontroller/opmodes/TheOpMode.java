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
public class TheOpMode extends OpMode
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
    DcMotor motorManip;

    //Servos
    Servo servoBasketL;
    Servo servoBasketR;
    Servo servoDumpL;
    Servo servoDumpR;
    Servo servoHookL;
    Servo servoHookR;

    //Motor values
    double motorFLSpeed;
    double motorBLSpeed;
    double motorFRSpeed;
    double motorBRSpeed;
    double motorLiftLSpeed;
    double motorLiftRSpeed;
    double motorManipSpeed;

    //Servo positions
    double servoBasketLPosition;
    double servoBasketRPosition;
    double servoDumpLPosition;
    double servoDumpRPosition;
    double servoHookLPosition;
    double servoHookRPosition;

    /**
     * Initializes motors.
     * <p>
     * Sets up all the motors
     */
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
        motorManip = hardwareMap.dcMotor.get("motorManip");
    }

    /**
     * Initializes servos.
     * <p>
     * Sets up all the servos
     */
    public void initializeServos()
    {
        //Basket servos
        servoBasketL = hardwareMap.servo.get("servoBasketL");
        servoBasketR = hardwareMap.servo.get("servoBasketR");
        //Dump servos
        servoDumpL = hardwareMap.servo.get("servoDumpL");
        servoDumpR = hardwareMap.servo.get("servoDumpR");
        //Hook servos
        servoHookL = hardwareMap.servo.get("servoHookL");
        servoHookR = hardwareMap.servo.get("servoHookR");
    }

    /**
     * Initializes the IMU.
     * <p>
     * Uses the hardware map, chosen name of the IMU, the IMU address, and the operation mode to set the local "imu" variable to the object to be accessed later.
     * Call this method in init() for best results, the IMU can take a couple seconds to initialize.
     */
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

    /**
     * Updates all the motor speeds.
     * <p>
     * Calls motor.setPower(speed); for all the motors and their speeds
     */
    public void updateMotorSpeeds()
    {
        //Wheel motors
        motorFL.setPower(motorFLSpeed);
        motorBL.setPower(motorBLSpeed);
        motorFR.setPower(motorFRSpeed);
        motorBR.setPower(motorBRSpeed);
        //Lift motors
        motorLiftL.setPower(motorLiftLSpeed);
        motorLiftR.setPower(motorLiftRSpeed);
        //Manipulator motors
        motorManip.setPower(motorManipSpeed);
    }

    /**
     * Updates all the servos' positions
     * <p>
     * Calls servo.setPosition(position); for all the servos and their positions
     * These "positions" are determined by the servoPosition double variables
     */
    public void updateServoPositions()
    {
        //Basket servos
        servoBasketL.setPosition(servoBasketLPosition);
        servoBasketR.setPosition(servoBasketRPosition);
        //Dumper servos
        servoDumpL.setPosition(servoDumpLPosition);
        servoDumpR.setPosition(servoDumpRPosition);
        //Hook servos
        servoHookL.setPosition(servoHookLPosition);
        servoHookR.setPosition(servoHookRPosition);
    }

    /**
     * Stops all of the motors.
     * <p>
     * This method should be used to control the robot when precise movement is needed. Also use this vs.
     * scaleControlledSimple when one or less method calls per second are needed, or extreme precision.
     */
    public void stopMotors()
    {
        motorFLSpeed = 0.0;
    }

    /**
     * Sets the speed of the motors controlling the wheels on the left side of the robot
     * <p>
     * Calls servo.setPosition(position); for all the servos and their positions
     * These "positions" are determined by the servoPosition double variables
     */
    public void setLeftSideSpeed(double speed)
    {
        motorFLSpeed = speed;
        motorBLSpeed = speed;
    }

    //Sets the speed of the motors controlling the wheels on the right side of the robot
    public void setRightSideSpeed(double speed)
    {
        motorFRSpeed = speed;
        motorBRSpeed = speed;
    }

    //Sets the speed of the motors controlling the lift
    public void setLiftSpeed(double speed)
    {
        motorLiftLSpeed = speed;
        motorLiftRSpeed = speed;
    }

    public void setHookServosUpOrDown(boolean latchDown)
    {
        if(latchDown)
        {
            servoHookLPosition = 0.0;
            servoHookRPosition = 0.0;
        }
        else
        {
            servoHookLPosition = 0.0;
            servoHookRPosition = 0.0;
        }
    }

    public void dumpBasketRight()
    {
        servoDumpRPosition = 0.0;
    }

    public void retractBasketRight()
    {
        servoDumpRPosition = 1.0;
    }

    public void dumpBasketLeft()
    {
        servoDumpLPosition = 0.0;
    }

    public void retractBasketLeft()
    {
        servoDumpLPosition = 1.0;
    }

    public void moveBasketLeft()
    {
        servoBasketLPosition = 1.0;
        servoBasketRPosition = 1.0;
    }

    public void moveBasketRight()
    {
        servoBasketLPosition = 0.0;
        servoBasketRPosition = 0.0;
    }

    public void stopBasketMove()
    {
        servoBasketLPosition = 0.5;
        servoBasketLPosition = 0.5;
    }

	@Override
	public void init()
    {
        initializeMotors();
        initializeServos();
        //initializeIMU();
	}

	@Override
	public void init_loop()
	{

	}

    @Override
    public void start()
    {
        //startIMU();
    }

	//Parent loop
	@Override
	public void loop()
	{
        updateMotorSpeeds();
        updateServoPositions();
	}

	@Override
	public void stop()
	{

	}
}