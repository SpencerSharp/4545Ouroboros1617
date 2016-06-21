package com.qualcomm.ftcrobotcontroller.opmodes;

public class Autonomous extends TheOpMode
{
    int state = 0;
    int distanceMovedSinceLastStep;
    double degreesTurnedSinceLastStep;
    boolean isStopped;
    long timeOfStop;

    @Override
    public void init()
    {
        super.init();
        distanceMovedSinceLastStep = 0;
        degreesTurnedSinceLastStep = 0.0;
        isStopped = false;
    }

    public void endStep()
    {
        stopMotors();
        isStopped = true;
        timeOfStop = System.nanoTime();
        distanceMovedSinceLastStep = 0;
        degreesTurnedSinceLastStep = 0.0;
        state++;
    }

    //Parent runOpMode
    @Override
    public void loop()
    {
        if(isStopped)
        {
            stopMotors();
            if(System.nanoTime()-timeOfStop > 1000000000/4)
                isStopped = false;
        }
        else
        {
            if (state == 0)
            {
                if (centerColorSensorReadsBlue())
                {
                    endStep();
                }
                else
                {
                    //moveForward(0.5);
                    setLeftSideSpeed(1.0);
                    setRightSideSpeed(-1.0);
                }
            }
            else if (state == 1)
            {
                if (backColorSensorReadBlue())
                {
                    endStep();
                }
                else
                {
                    //turnRight(0.5);
                }
            }
            else if (state == 2)
            {
                if (!centerColorSensorReadsBlue())
                {
                    endStep();
                }
                else
                {
                    moveForwardWithLineFollow();
                }
            }
            else if (state == 3)
            {
                int distanceToMoveBack;
                if (distanceMovedSinceLastStep > distanceToMoveBack)
                {
                    endStep();
                }
                else
                {
                    moveBackwards();
                }
            }
            else if (state == 4)
            {
                if (degreesTurnedSinceLastStep > 90.0)
                {
                    endStep();
                }
                else
                {
                    turnRight();
                }
            }
            else if(state == 5)
            {
                //if (timeSinceLastStep>5 seconds)
                {
                    endStep();
                }
                else
                {
                    moveForwardWithLineFollow();
                }
            }
            else if(state == 6)
            {
                if(timeSinceLastStep > toNano(1.0))
                {
                    endStep();
                }
                else
                {
                    dumpClimbers();
                }
            }
        }
        super.loop(); //Updates motor speeds and servo positions
    }
}