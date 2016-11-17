package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 * Created by ahmetshen-us on 10/4/16.
 */
@TeleOp
public class LearnOneMotor extends LinearOpMode {
	FTCRobotHardware robot = new FTCRobotHardware();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);
		robot.rightArmMotor.setPower(1);
	}
}
