package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.DriveTrainMotors;
@Autonomous
public class AutoV1 extends OpMode {

    public DriveTrainMotors driveMotors = new DriveTrainMotors();

    @Override
    public void init() {
        driveMotors.init(hardwareMap);
        driveMotors.driveFowardForFixedTime(1000);
    }

    @Override
    public void loop() {

    }
}
