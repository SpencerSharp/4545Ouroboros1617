package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by spencersharp on 6/7/16.
 */
public class TeleOp extends TheOpMode
{
    int curMode;
    int prevMode;

    //Macro information
    boolean inMacroA;

    boolean isRedSide;
    boolean isBlueSide;

    //Controller values
    double g1y1;
    double g1y2;
    double g1x1;
    double g1x2;
    double g2y1;
    double g2y2;
    double g2x1;
    double g2x2;
    boolean g1Lbump;
    boolean g1Rbump;
    boolean g2Lbump;
    boolean g2Rbump;
    double g1Ltrig;
    double g1Rtrig;
    double g2Ltrig;
    double g2Rtrig;
    boolean g1XPressed;
    boolean g2XPressed;
    boolean g1YPressed;
    boolean g2YPressed;
    boolean g1APressed;
    boolean g2APressed;
    boolean g1BPressed;
    boolean g2BPressed;

    boolean haveSticksBeenPushedUpSinceModeChanged;

    @Override
    public void init()
    {
        prevMode = 1;
        curMode = 1;
        inMacroA = false;
        haveSticksBeenPushedUpSinceModeChanged = false;
        super.init();
    }

    @Override
    public void init_loop()
    {

    }

    @Override
    public void start()
    {
        super.start();
    }

    public void updateMode()
    {
        prevMode = curMode; //Changes the previous mode to the current mode
        if (curMode != 0) //Updates current mode
        {
            if (g2XPressed && curMode != 1) //If Controller 2 X is pressed, switch to mode 1
            {
                curMode = 1;
                if (g1y1 < -0.1 && g1y2 < -0.1) //Are sticks pushed down
                    haveSticksBeenPushedUpSinceModeChanged = true; //The sticks have been pushed up
                else
                    haveSticksBeenPushedUpSinceModeChanged = false; //The sticks have not been pushed up
            } else if ((g2BPressed && curMode != 2)) //|| (curPitch > 10 || curPitch < -10)) //If Controller 2 B is pressed, switch to mode 2
            {
                curMode = 2;
                if (g1y1 < -0.1 && g1y2 < -0.1) //Are sticks pushed up
                    haveSticksBeenPushedUpSinceModeChanged = true; //The sticks have been pushed up
                else
                    haveSticksBeenPushedUpSinceModeChanged = false; //The sticks have not been pushed up
            }
            //Else keep current mode
        }
        if (g1y1 < 0 || g1y2 < 0) //Are sticks pushed up
            haveSticksBeenPushedUpSinceModeChanged = true; //The sticks have been pushed up
    }

    public void updateControllerVals()
    {
        g1y1 = gamepad1.left_stick_y;
        g1y2 = gamepad1.right_stick_y;
        g1x1 = gamepad1.left_stick_x;
        g1x2 = gamepad1.right_stick_x;
        g2y1 = gamepad2.left_stick_y;
        g2y2 = gamepad2.right_stick_y;
        g2x1 = gamepad2.left_stick_x;
        g2x2 = gamepad2.right_stick_x;
        g1Lbump = gamepad1.left_bumper;
        g1Rbump = gamepad1.right_bumper;
        g2Lbump = gamepad2.left_bumper;
        g2Rbump = gamepad2.right_bumper;
        g1Ltrig = gamepad1.left_trigger;
        g1Rtrig = gamepad1.right_trigger;
        g2Ltrig = gamepad2.left_trigger;
        g2Rtrig = gamepad2.right_trigger;
        g1XPressed = gamepad1.x;
        g1APressed = gamepad1.a;
        g1YPressed = gamepad1.y;
        g1BPressed = gamepad1.b;
        g2XPressed = gamepad2.x;
        g2APressed = gamepad2.a;
        g2YPressed = gamepad2.y;
        g2BPressed = gamepad2.b;
    }

    //Parent runOpMode
    @Override
    public void loop()
    {
        updateControllerVals();
        updateMode();
        if(inMacroA)
        {
            /*
            //Check if done with macro OR interrupted
            if(isMacroAComplete()||macroInterrupted("A"))
            {
                inMacroA = false; //Set isMacroA to false
                loop(); //Back to normal teleop
            }



            //Otherwise
            setLiftSpeed(0.5);
            //Run the motors and servos for what the macro is supposed to do
            */
        }
        else //Standard mode stuff
        {
            updateMode();
            if(curMode == 1) //If mode 1 (ground movement standard mode)
            {
                setLeftSideSpeed(MotorScaler.scaleSimple(g1y1));
                setRightSideSpeed(MotorScaler.scaleSimple(-g1y2));
                setLiftSpeed(g2y1);
                setHookServosUpOrDown(g2Lbump);
            }
            else if(curMode == 2) //If mode 2 (near mountain mode)
            {
                if(!haveSticksBeenPushedUpSinceModeChanged) //Sticks have not been pushed up
                {
                    //Controller 1
                    //Left Stick
                    setLeftSideSpeed(MotorScaler.scaleSimple(g1y1)); //Moves left side of robot (Manipulator is front of robot)
                    //Right Stick
                    setRightSideSpeed(MotorScaler.scaleSimple(-g1y2)); //Moves right side of robot (Manipulator is front of robot)
                    setLiftSpeed(g2y1);
                    setHookServosUpOrDown(g2Lbump);
                }
                else
                {
                    setLeftSideSpeed(MotorScaler.scaleSimple(-g1y2));
                    setRightSideSpeed(MotorScaler.scaleSimple(g1y1));
                    setLiftSpeed(g2y1);
                    setHookServosUpOrDown(g2Lbump);
                }
            }
        }
        if(g2x2 > 0.1)
        {
            moveBasketRight();
        }
        else if(g2x2 < -0.1)
        {
            moveBasketLeft();
        }
        else
        {
            stopBasketMove();
        }

        if(g2Ltrig>0.1)
        {
            dumpBasketLeft();
        }
        else if(g2Lbump)
        {
            retractBasketLeft();
        }

        if(g2Rtrig>0.1)
        {
            dumpBasketRight();
        }
        else if(g2Rbump)
        {
            retractBasketRight();
        }
        super.loop(); //Updates motor speeds and servo positions
    }

    @Override
    public void stop()
    {

    }
}