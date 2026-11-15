package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AutoV1 extends OpMode {

    private AprilTagScanner aprilTagScanner = new AprilTagScanner();

    @Override
    public void init() {
        aprilTagScanner.initAprilTag(hardwareMap);
    }

    @Override
    public void loop() {
        aprilTagScanner.AprilTagTelemetry(telemetry);

        AprilTagDetection tag583 = aprilTagScanner.getAprilTagPositionByID(583);
        if (tag583 != null) {
            telemetry.addLine("Tag 583 is in view");
        }
    }
}

