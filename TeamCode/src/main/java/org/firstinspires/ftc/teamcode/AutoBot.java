package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by sen on 12/17/16.
 */
@Autonomous(name = "AutoBot Class")
public class AutoBot extends LinearOpMode {
    FTCRobotHardware robot = new FTCRobotHardware();
    private ElapsedTime runtime = new ElapsedTime();
    static final double     FORWARD_SPEED = 0.6;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Drive Forward for 1 Second
        robot.leftMotor.setPower(0.6);
        robot.rightMotor.setPower(0.3);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 0.4)) {
            telemetry.addData("Path", "Drive Forward: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);

        // Shoot Ball for 5 seconds
        robot.ballShooter.setPower(1.0);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 5.0)) {

            telemetry.addData("Path", "Shoot: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.ballShooter.setPower(0);



        // Drive Forward for 2 Second
        robot.leftMotor.setPower(0.6);
        robot.rightMotor.setPower(0.6);
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() < 1.52)) {
            telemetry.addData("Path", "Drive Forward: %2.5f S Elapsed", runtime.seconds());
            telemetry.update();
        }
        robot.leftMotor.setPower(0);
        robot.rightMotor.setPower(0);



    }
}
