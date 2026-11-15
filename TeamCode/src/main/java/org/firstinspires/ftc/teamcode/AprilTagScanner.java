package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.apriltag.AprilTagSingleDetection;

import java.util.List;
import java.util.Vector;

public class AprilTagScanner {

    private AprilTagProcessor aprilTag;
    private VisionPortal visionPortal;

    private CoordinateTranslator coordinateTranslator = new CoordinateTranslator();

    public void initAprilTag(HardwareMap hwMap) {
        aprilTag = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagOutline(true)
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hwMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(800, 600))
                .addProcessor(aprilTag)
                .enableLiveView(true)
                .build();
    }

    // Using coordinateTranslator, this will add telemetry data from the center of the robot
    public void AprilTagTelemetry(Telemetry telemetry) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection.ftcPose != null) {
                double[] translatedFtcPose = coordinateTranslator.TranslateVector3(detection.ftcPose.x, detection.ftcPose.y, detection.ftcPose.z);

                telemetry.addLine(String.format("XYZ %6.1f %6.1f %6.1f (inch)",
                        translatedFtcPose[0], translatedFtcPose[1], translatedFtcPose[2]));

                telemetry.addLine(String.format("PRY %6.1f %6.1f %6.1f (deg)",
                        detection.ftcPose.pitch, detection.ftcPose.roll, detection.ftcPose.yaw));

                telemetry.addLine(String.format("RBE %6.1f %6.1f %6.1f (range in inch, rest are in deg)",
                        detection.ftcPose.range, detection.ftcPose.bearing, detection.ftcPose.elevation));
            }
            else {
                telemetry.addLine("No tags are visible");
            }
        }
    }

    public AprilTagSingleDetection getAprilTagPositionByID(int idToSearchFor) {
        List<AprilTagDetection> currentDetections = aprilTag.getDetections();

        for (AprilTagDetection detection : currentDetections) {
            if (detection instanceof AprilTagSingleDetection) {
                AprilTagSingleDetection singleDet = (AprilTagSingleDetection) detection;
                int tagId = singleDet.id;
                if (tagId == idToSearchFor) {
                    return singleDet; // remember to .addData both the id, metadata and decisionMargin
                }
            }
        }
        return null;
    }
}

// used to translate camera coordinates --> center of the robot coordinates
class CoordinateTranslator {

    private double xOffset = 0;
    private double yOffset = 0;
    private double zOffset = 0;

    public double[] TranslateVector3(double x, double y, double z) {
        double[] translatedVector3 = new double[] {x + xOffset, y + yOffset, z + zOffset};
        return translatedVector3;
    }
}
