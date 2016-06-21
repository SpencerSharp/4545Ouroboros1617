package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.EventLoopManager;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;
import java.math.*;
import android.util.Log;
import com.qualcomm.robotcore.exception.RobotCoreException;

public class TheOpMode extends OpMode
{
	//IMU name
	AdafruitIMU imu;

    //Motors
    DcMotor motorFL;
    DcMotor motorBL;
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

    //Motor values
    double motorFLSpeed;
    double motorBLSpeed;
    double motorFRSpeed;
    double motorBRSpeed;
    double motorLiftLSpeed;
    double motorLiftRSpeed;
    double motorManipLSpeed;
    double motorManipRSpeed;

    //Servo positions
    double servoBasketLPosition;
    double servoBasketRPosition;
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
        motorManipL = hardwareMap.dcMotor.get("motorManipL");
        motorManipR = hardwareMap.dcMotor.get("motorManipR");
    }

    /**
     * Initializes servos.
     * <p>
     * Sets up all the servos
     */
    public void initializeServos()
    {
        //Turntable servos
        servoBasketL = hardwareMap.servo.get("servoBasketL");
        servoBasketR = hardwareMap.servo.get("servoBasketR");
        //Hook servos
        servoHookL = hardwareMap.servo.get("servoHookL");
        servoHookR = hardwareMap.servo.get("servoHookR");
    }

    /**
     * Initializes the IMU.
     * <p>
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
        motorManipL.setPower(motorManipLSpeed);
        motorManipR.setPower(motorManipRSpeed);
    }

    /**
     * Updates all the servo positions.
     * <p>
     * Calls servo.setPosition(position); for all the servos and their positions
     */
    public void updateServoPositions()
    {
        //Turntable servos
        servoBasketL.setPosition(servoBasketLPosition);
        servoBasketR.setPosition(servoBasketRPosition);
        //Hook servos
        servoHookL.setPosition(servoHookLPosition);
        servoHookR.setPosition(servoHookRPosition);
    }

    //Sets the speed of the motors controlling the wheels on the left side of the robot
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

    public void setHookServosUpOrDown(boolean isLatchedDown)
    {
        if(isLatchedDown)
        {
            //Set servos to latched down position
        }
        else
        {
            //Set servos to not latched down position
        }
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
        updateMotorSpeeds();
        updateServoPositions();
	}

	@Override
	public void stop()
	{

	}
}