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
    Servo servoTurntableL;
    Servo servoTurntableR;
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
    double servoTurntableLPosition;
    double servoTurntableRPosition;
    double servoHookLPosition;
    double servoHookRPosition;

	@Override
	public void init()
    {

	}

	@Override
	public void init_loop()
	{

	}

    @Override
    public void start()
    {

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