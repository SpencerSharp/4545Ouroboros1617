package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by spencersharp on 6/7/16.
 */
public class TeleOp extends TheOpMode
{
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

    //Parent runOpMode
    @Override
    public void loop()
    {
        //Check mode that the robot is in
            //If mode 1
                //Do mode 1 stuff
            //If mode 2
                //Do mode 2 stuff
            //If mode 3
                //Do mode 3 stuff
        boolean inMacroA = false;
        if(inMacroA)
        {
            //Check if done with macro OR interrupted
                //Run other things
                //Set isMacroA to false
            //Otherwise
                //Run the motors and servos for what the macro is supposed to doz
        }
        super.loop(); //Updates motor speeds and servo positions
    }

    @Override
    public void stop()
    {

    }
}
