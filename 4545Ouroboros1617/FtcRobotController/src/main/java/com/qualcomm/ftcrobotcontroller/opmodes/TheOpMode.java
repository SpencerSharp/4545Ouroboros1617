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

	//Motor Power Scaling
	protected double scaleInput(double y)
	{
		double ret = 0.0;
		double pwr = 0.0;
		if(y < 0.0)
			pwr = -1 * 13620 * y;
		else
			pwr = 13620 * y;
		String stringPwr = "" + pwr;
		BigDecimal pwrF = new BigDecimal(stringPwr);
		BigDecimal pwrE = pwrF.multiply(pwrF);
		BigDecimal pwrD = pwrE.multiply(pwrF);
		BigDecimal pwrC = pwrD.multiply(pwrF);
		BigDecimal pwrB = pwrC.multiply(pwrF);
		BigDecimal pwrA = pwrB.multiply(pwrF);

		BigDecimal a = new BigDecimal("0.000000000000000000000007626656");
		BigDecimal b = new BigDecimal("0.000000000000000000272080701657");
		BigDecimal c = new BigDecimal("0.000000000000003715117053405310");
		BigDecimal d = new BigDecimal("0.000000000024215489818429200000");
		BigDecimal e = new BigDecimal("0.000000077305839163423100000000");
		BigDecimal f = new BigDecimal("0.000102195266110368000000000000");
		BigDecimal g = new BigDecimal("0.079417125827859500000000000000");
		a = a.multiply(pwrA);
		b = b.multiply(pwrB);
		c = c.multiply(pwrC);
		d = d.multiply(pwrD);
		e = e.multiply(pwrE);
		f = f.multiply(pwrF);

		BigDecimal h = a.subtract(b);
		h = h.add(c);
		h = h.subtract(d);
		h = h.add(e);
		h = h.subtract(f);
		h = h.add(g);

		ret = h.doubleValue();
		if(ret < 0.0)
			ret = 0.0;
		if(ret > 1.0)
			ret = 1.0;
		if(y < 0.0)
			ret *= -1;
		return ret;
	}

	protected double scaleInputSimple(double pwr) //Scales input power
    {
        if(pwr > 0.0)
        {
            if(pwr < 0.05) //0 PWR on chart
                return 0.0;
            else if(pwr >= 0.05 && pwr < 0.10) //0.05 on chart
                return 0.01;
            else if(pwr >= 0.10 && pwr < 0.15) //0.10 on chart
                return 0.02;
            else if(pwr >= 0.15 && pwr < 0.20) //0.15 on chart
                return 0.03;
            else if(pwr >= 0.20 && pwr < 0.25) //0.20 on chart
                return 0.04;
            else if(pwr >= 0.25 && pwr < 0.30) //0.25 on chart
                return 0.05;
            else if(pwr >= 0.30 && pwr < 0.35) //0.30 on chart
                return 0.06;
            else if(pwr >= 0.35 && pwr < 0.40) //0.35 on chart
                return 0.07;
            else if(pwr >= 0.40 && pwr < 0.45) //0.40 on chart
                return 0.075;
            else if(pwr >= 0.45 && pwr < 0.50) //0.45 on chart
                return 0.08;
            else if(pwr >= 0.50 && pwr < 0.55) //0.50 on chart
                return 0.09;
            else if(pwr >= 0.55 && pwr < 0.60) //0.55 on chart
                return 0.10;
            else if(pwr >= 0.60 && pwr < 0.65) //0.60 on chart
                return 0.113;
            else if(pwr >= 0.65 && pwr < 0.70) //0.65 on chart
                return 0.126;
            else if(pwr >= 0.70 && pwr < 0.75) //0.70 on chart
                return 0.14;
            else if(pwr >= 0.75 && pwr < 0.80) //0.75 on chart
                return 0.15;
            else if(pwr >= 0.80 && pwr < 0.85) //0.80 on chart
                return 0.19;
            else if(pwr >= 0.85 && pwr < 0.90) //0.85 on chart
                return 0.225;
            else
                return 1.0;
        }
        else
        {
            if(pwr > -0.05) //0 PWR on chart
                return 0.0;
            else if(pwr <= -0.05 && pwr > -0.10) //0.05 on chart
                return -0.01;
            else if(pwr <= -0.10 && pwr > -0.15) //0.10 on chart
                return -0.02;
            else if(pwr <= -0.15 && pwr > -0.20) //0.15 on chart
                return -0.03;
            else if(pwr <= -0.20 && pwr > -0.25) //0.20 on chart
                return -0.04;
            else if(pwr <= -0.25 && pwr > -0.30) //0.25 on chart
                return -0.05;
            else if(pwr <= -0.30 && pwr > -0.35) //0.30 on chart
                return -0.06;
            else if(pwr <= -0.35 && pwr > -0.40) //0.35 on chart
                return -0.07;
            else if(pwr <= -0.40 && pwr > -0.45) //0.40 on chart
                return -0.075;
            else if(pwr <= -0.45 && pwr > -0.50) //0.45 on chart
                return -0.08;
            else if(pwr <= -0.50 && pwr > -0.55) //0.50 on chart
                return -0.09;
            else if(pwr <= -0.55 && pwr > -0.60) //0.55 on chart
                return -0.10;
            else if(pwr <= -0.60 && pwr > -0.65) //0.60 on chart
                return -0.113;
            else if(pwr <= -0.65 && pwr > -0.70) //0.65 on chart
                return -0.126;
            else if(pwr <= -0.70 && pwr > -0.75) //0.70 on chart
                return -0.14;
            else if(pwr <= -0.75 && pwr > -0.80) //0.75 on chart
                return -0.15;
            else if(pwr <= -0.80 && pwr > -0.85) //0.80 on chart
                return -0.19;
            else if(pwr <= -0.85 && pwr > -0.90) //0.85 on chart
                return -0.225;
            else
                return -1.0;
        }
    }

	@Override
	public void init()
	{

	}

	@Override
	public void init_loop()
	{

	}

	//Parent runOpMode
	@Override
	public void loop()
	{

	}

	@Override
	public void stop()
	{

	}
}