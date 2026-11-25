package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
public class DriveTrainMotors {

    //motor wheels
    public DcMotor tl;
    //data types
    public DcMotor tr;
    public DcMotor bl;
    public DcMotor br;

    public void init(HardwareMap hwMap) {

        //My motor tings
        tl = hwMap.get(DcMotor.class, "tl");
        tr = hwMap.get(DcMotor.class, "tr");
        bl = hwMap.get(DcMotor.class, "bl");
        br = hwMap.get(DcMotor.class, "br");


        tl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        tr.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        bl.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        br.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        tr.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void setMotorSpeed(double speed) {

        tl.setPower(speed);
        tr.setPower(speed);
        bl.setPower(speed);
        br.setPower(speed);

    }

    public void driveFowardForFixedTime(long time) {
        setMotorSpeed(1.0);
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        setMotorSpeed(0);
    }

    public void driveFowardForFixedTime(long time, double motorSpeed) {
        setMotorSpeed(1.0);
        try {
            Thread.sleep(time);
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        setMotorSpeed(0);
    }

    public void rotate(int degrees) {

    }


    public void setFlywheelSpeed(double speed) {
//        flywheel.setPower(speed);

    }

}


/*
3---1
2---0
 */