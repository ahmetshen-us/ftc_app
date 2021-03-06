package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by ahmetshen-us on 9/30/16.
 */
public class FTCRobotHardware {
    /* Public OpMode members. */
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;
    public DcMotor ballPicker = null;
    public DcMotor ballShooter = null;
//    public DcMotor leftArmMotor = null;
//    public DcMotor rightArmMotor = null;
//    public Servo leftClaw = null;
//    public Servo rightClaw = null;

    public static final double MID_SERVO = 0.5;
    public static final double ARM_UP_POWER = 0.45;
    public static final double ARM_DOWN_POWER = -0.35;
    final double LEFT_Claw_OPEN_POSITION = 0.0;
    final double LEFT_Claw_CLOSED_POSITION = 0.5;
    final double RIGHT_Claw_OPEN_POSITION = 1.0;
    final double RIGHT_Claw_CLOSED_POSITION = 0.5;


    /* local OpMode members. */
    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();
    public FTCRobotHardware() {
    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftMotor   = hwMap.dcMotor.get("left_drive");
        rightMotor  = hwMap.dcMotor.get("right_drive");
        ballPicker = hwMap.dcMotor.get("ball_picker");
        ballShooter = hwMap.dcMotor.get("ball_shooter");

//        leftArmMotor = hwMap.dcMotor.get("left_arm");
//        rightArmMotor = hwMap.dcMotor.get("right_arm");
        leftMotor.setDirection(DcMotor.Direction.REVERSE); // Set to REVERSE if using AndyMark motors
        rightMotor.setDirection(DcMotor.Direction.FORWARD);// Set to FORWARD if using AndyMark motors
        ballPicker.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        ballShooter.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors

        // Set all motors to zero power
        leftMotor.setPower(0);
        rightMotor.setPower(0);
        ballPicker.setPower(0);
        ballShooter.setPower(0);
//        leftArmMotor.setPower(0);
//        rightArmMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ballPicker.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        ballShooter.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        leftArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//        rightArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
//        leftClaw = hwMap.servo.get("left_hand");
//        rightClaw = hwMap.servo.get("right_hand");
//        leftClaw.setPosition(MID_SERVO);
//        rightClaw.setPosition(MID_SERVO);
    }
    /***
     *
     * waitForTick implements a periodic delay. However, this acts like a metronome with a regular
     * periodic tick.  This is used to compensate for varying processing times for each cycle.
     * The function looks at the elapsed cycle time, and sleeps for the remaining time interval.
     *
     * @param periodMs  Length of wait cycle in mSec.
     * @throws InterruptedException
     */
    public void waitForTick(long periodMs) throws InterruptedException {

        long  remaining = periodMs - (long)period.milliseconds();

        // sleep for the remaining portion of the regular cycle period.
        if (remaining > 0)
            Thread.sleep(remaining);

        // Reset the cycle clock for the next pass.
        period.reset();
    }
}
