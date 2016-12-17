package org.firstinspires.ftc.teamcode.learn;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FTCRobotHardware;

/**
 * Created by ahmetshen-us on 10/4/16.
 */
@Disabled

public class LearnOneMotor extends LinearOpMode {
	FTCRobotHardware robot = new FTCRobotHardware();

	@Override
	public void runOpMode() throws InterruptedException {
		robot.init(hardwareMap);
		robot.rightArmMotor.setPower(1);
	}
}
