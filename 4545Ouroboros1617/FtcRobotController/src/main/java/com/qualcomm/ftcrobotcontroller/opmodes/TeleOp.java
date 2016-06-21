package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by spencersharp on 6/7/16.
 */
public class TeleOp extends TheOpMode
{
    int curMode;
    boolean inMacroA;

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

    @Override
    public void init()
    {
        curMode = 1;
        inMacroA = false;
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
        boolean inMacroA = false;
        if(inMacroA)
        {
            //Check if done with macro OR interrupted
            //Run other things
            //Set isMacroA to false
            //Otherwise
            //Run the motors and servos for what the macro is supposed to do
        }
        else //Standard mode stuff
        {
            updateMode();
            if(curMode == 1) //If mode 1 (ground movement standard mode)
            {
                setLeftSideSpeed(g1y1);
                setRightSideSpeed(-g1y2);
                setLiftSpeed(g2y1);
                setHookServosUpOrDown(g2Lbump);
            }
            else if(curMode == 2) //If mode 2 (near mountain mode)
            {
                //Do mode 2 stuff
            }
            else if(curMode == 3) //If mode 3 (hooked onto mountain mode)
            {
                //Do mode 3 stuff
            }
        }
        super.loop(); //Updates motor speeds and servo positions
    }

    @Override
    public void stop()
    {

    }
}
