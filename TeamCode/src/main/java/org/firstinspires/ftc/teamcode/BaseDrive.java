package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by ahmetshen-us on 9/30/16.
 */
@TeleOp(name = "FTCRobotHardware: Base Drive", group = "Drive")

public class BaseDrive extends LinearOpMode {
	FTCRobotHardware robot = new FTCRobotHardware();
	private ElapsedTime runtime = new ElapsedTime();

	double clawOffset = 0; // Servo mid position
	final double CLAW_SPEED = 0.02; // sets rate to move servo

	@Override
	public void runOpMode() throws InterruptedException {
		telemetry.addData("Status", "Initialized");
		telemetry.update();

		double leftMotorPower;
		double rightMotorPower;
		double max;

		/*
		 * Initialize the hardware variables. The init() method of the hardware
		 * class does all the work here
		 */
		robot.init(hardwareMap);

		// Send telemetry message to signify robot waiting;
		telemetry.addData("Say", "Hello Dear Driver"); //
		telemetry.update();

		// Wait for the game to start (driver presses PLAY)
		waitForStart();

		// run until the end of the match (driver presses STOP)
		while (opModeIsActive()) {

			// Run wheels in POV mode (note: The joystick goes negative when
			// pushed forwards, so negate it)
			// In this mode the Left stick moves the robot fwd and back, the
			// Right stick turns left and right.
			// leftMotorPower = -gamepad1.left_stick_y + gamepad1.right_stick_x;
			// rightMotorPower = -gamepad1.left_stick_y -
			// gamepad1.right_stick_x;
			//
			// // Normalize the values so neither exceed +/- 1.0
			// max = Math.max(Math.abs(leftMotorPower),
			// Math.abs(rightMotorPower));
			// if (max > 1.0) {
			// leftMotorPower /= max;
			// rightMotorPower /= max;
			// }
			// OR
			// //Clip the power values so that it only goes from -1 to 1
			// left = Range.clip(left, -1, 1);
			// right = Range.clip(right, -1, 1);
			// eg: Run wheels in tank mode (note: The joystick goes negative
			// when pushed forwards)

			// two joystick control
			// leftMotorPower = -gamepad1.left_stick_y ;
			// rightMotorPower = -gamepad1.right_stick_y ;

			// Only one joystick control
			// Get the values from the gamepads
			// Note: pushing the stick all the way up returns -1,
			// so we need to reverse the y values
			float xValue = gamepad1.left_stick_x;
			float yValue = -gamepad1.left_stick_y;

			// Calculate the power needed for each motor
			float leftPower = yValue + xValue;
			float rightPower = yValue - xValue;

			// Clip the power values so that it only goes from -1 to 1
			leftMotorPower = Range.clip(leftPower, -1, 1);
			rightMotorPower = Range.clip(rightPower, -1, 1);

			// reduce the speed of left and right motors
			leftMotorPower = leftMotorPower / 2;
			rightMotorPower = rightMotorPower / 2;

			telemetry.addData("Status", "Run Time: " + runtime.toString());
			telemetry.addData("left_stick_y", "info: " + -gamepad1.left_stick_y);
			telemetry.addData("right_stick_y", "info: " + -gamepad1.right_stick_y);

			robot.leftMotor.setPower(leftMotorPower);
			robot.rightMotor.setPower(rightMotorPower);

			// Use gamepad left & right Bumpers to open and close the claw
			// if (gamepad1.right_bumper)
			// clawOffset += CLAW_SPEED;
			// else if (gamepad1.left_bumper)
			// clawOffset -= CLAW_SPEED;

			// This code will open and close the gripper with two buttons
			// using 1 button to open and another to close the gripper
			if (gamepad1.x) {
				robot.leftClaw.setPosition(robot.LEFT_Claw_OPEN_POSITION);
				robot.rightClaw.setPosition(robot.RIGHT_Claw_OPEN_POSITION);
			} else if (gamepad1.b) {
				robot.leftClaw.setPosition(robot.LEFT_Claw_CLOSED_POSITION);
				robot.rightClaw.setPosition(robot.RIGHT_Claw_CLOSED_POSITION);
			}
			// else {
			// // Move both servos to new position. Assume servos are mirror
			// image of each other.
			// clawOffset = Range.clip(clawOffset, -0.5, 0.5);
			// robot.leftClaw.setPosition(robot.MID_SERVO + clawOffset);
			// robot.rightClaw.setPosition(robot.MID_SERVO - clawOffset);
			// }

			// Use gamepad buttons to move arm up (Y) and down (A)
			if (gamepad1.y)
				robot.leftArmMotor.setPower(robot.ARM_UP_POWER);
			else if (gamepad1.a)
				robot.leftArmMotor.setPower(robot.ARM_DOWN_POWER);
			else
				robot.leftArmMotor.setPower(0.0);

			// Send telemetry message to signify robot running;
			telemetry.addData("claw", "Offset = %.2f", clawOffset);
			telemetry.addData("left", "%.2f", leftMotorPower);
			telemetry.addData("right", "%.2f", rightMotorPower);

			telemetry.update();

			// Pause for metronome tick. 40 mS each cycle = update 25 times a
			// second.
			robot.waitForTick(40);
		}
	}
}
