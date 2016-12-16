package org.firstinspires.ftc.teamcode.learn;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.HINT;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by ahmetshen-us on 10/5/16.
 */

public class VuforiaOp2 extends LinearOpMode {

	@Override
	public void runOpMode() throws InterruptedException {
		VuforiaLocalizer.Parameters params = new VuforiaLocalizer.Parameters(R.id.cameraMonitorViewId);
		params.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
		params.vuforiaLicenseKey = "AUV1UAT/////AAAAGe9mJcbuf0TOvzxg98krkr9lfJvtp8sbPDJrnFKhVOXwUgnjPxYxM/l2k93pK3TAjZK1eDUpvy/Z6Hl+ATljofX8no6XfZcTNOhG3nc025XyzwfouLkkOayt6aHkPfDJYFjFyZxbfjzkpuuw8TwSZM125X8GES8wrQg8aMcJTJT4/0BXXm2B7yCDReEWBO+wmzbjVuSfvksAwTHSXDW9iuvx7TCD5pdCJ3sZX793M9SFekXpttS8NuAy3kQf5XRGUebP3EwCEswOck+rv+J/kQ1xxCwCIQOiMS3FI0drO0YRNDFvbVBcQmttJwgctmft6iBv/mHWmHxTtrly5SYjPMMthNL9AAcoE8+gfof0XK2z";
		params.cameraMonitorFeedback = VuforiaLocalizer.Parameters.CameraMonitorFeedback.AXES;
		// params.cameraMonitorFeedback =
		// VuforiaLocalizer.Parameters.CameraMonitorFeedback.TEAPOT;

		VuforiaLocalizer vuforia = ClassFactory.createVuforiaLocalizer(params);
		Vuforia.setHint(HINT.HINT_MAX_SIMULTANEOUS_IMAGE_TARGETS, 4);

		VuforiaTrackables beacons = vuforia.loadTrackablesFromAsset("FTC_2016-17");
		beacons.get(0).setName("Wheels");
		beacons.get(1).setName("Tools");
		beacons.get(2).setName("Legos");
		beacons.get(3).setName("Gears");

		waitForStart();
		beacons.activate();

		while (opModeIsActive()) {
			for (VuforiaTrackable beac : beacons) {
				OpenGLMatrix pose = ((VuforiaTrackableDefaultListener) beac.getListener()).getPose();
				if (pose != null) {
					VectorF translation = pose.getTranslation();

					telemetry.addData(beac.getName() + "- Translation ", translation);

					// if phone is in portrait 1 2
					// if phone is in landscape 0 1
					double degreesToTurn = Math.toDegrees(Math.atan2(translation.get(1), translation.get(2)));

					telemetry.addData(beac.getName() + "-degrees", degreesToTurn);
				}
			}
			telemetry.update();
		}
	}
}
