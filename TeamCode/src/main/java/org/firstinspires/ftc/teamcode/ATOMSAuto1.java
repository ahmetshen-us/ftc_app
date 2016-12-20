package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.IllegalFormatCodePointException;

@Autonomous(name="Atoms Autonomous", group="Linear Opmode")
public class ATOMSAuto1 extends OpMode {

    public ElapsedTime  mRuntime = new ElapsedTime();   // Time into round.

    private ElapsedTime mStateTime = new ElapsedTime();  // Time into current state

    private State       mCurrentState;    // Current State Machine State.



    // A list of system States.
    private enum State
    {
        STATE_DRIVE_FORWARD,
        STATE_TURN_RIGHT,
        STATE_DRIVE_FORWARD_2,
        STATE_TURN_LEFT,
        STATE_LINE_FOLLOW,
        STATE_STOP,
    }

    // Drive Motors
    public DcMotor leftMotor = null;
    public DcMotor rightMotor = null;

    // Servo Motors
    public Servo arm = null;

    // Sensors
   // ColorSensor Color;
    OpticalDistanceSensor ODS;

    static final double FLOOR_REFLECTANCE = 0.2;
    static final double LINE_REFLECTANCE = 0.55;
    static final double THRESHHOLD_REFLECTANCE = (LINE_REFLECTANCE + FLOOR_REFLECTANCE)/2;

    @Override
    public void init() {

        leftMotor = hardwareMap.dcMotor.get("M1");
        rightMotor = hardwareMap.dcMotor.get("M2");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);

        arm = hardwareMap.servo.get("S1");
        arm.setPosition(1);

        //Color = hardwareMap.colorSensor.get("color");

        ODS = hardwareMap.opticalDistanceSensor.get("ods");

        double reflectance = 0.0;
        //double Range = Distance.getUltrasonicLevel();


        }

     @Override
     public void start ()
     {
         StopDriving();
         mRuntime.reset();
         newState(State.STATE_DRIVE_FORWARD);

     }

      @Override
      public void loop()  {

          switch (mCurrentState)
          {
              case STATE_DRIVE_FORWARD: // Follow path until last segment is completed
                  if (mStateTime.time() < 3){
                      DriveForward(0.5);
                  }
                  else {
                      StopDriving();
                      newState(State.STATE_TURN_RIGHT);

                  }
                  break;

              case STATE_TURN_RIGHT:     // Rotate until white tape is detected
                if (mStateTime.time()< 1){
                    TurnRight(0.5);
                }
                else{
                    StopDriving();
                    newState(State.STATE_DRIVE_FORWARD_2);
                }
                  break;
              case STATE_DRIVE_FORWARD_2:
                if (mStateTime.time()< 3) {
                    DriveForward(0.5);
                }
                  else {
                    StopDriving();
                    newState(State.STATE_TURN_LEFT);
                }
                  break;

              case STATE_TURN_LEFT:     // Track line until wall is reached
                 if (mStateTime.time() < 1) {
                     TurnLeft(0.5);
                 }
                  else {
                     StopDriving();
                     newState(State.STATE_LINE_FOLLOW);
                 }
                  break;
              case STATE_LINE_FOLLOW:

                      break;

              case STATE_STOP:

                  break;
          }

      }

      public void stop()
      {
        StopDriving();
      }


       public void DriveForward(double power)

       {
           leftMotor.setPower(power);
           rightMotor.setPower(power);
       }

       public void DriveBackwards(double power)
       {
           DriveForward(-power);
       }
        public void StopDriving()
        {
            DriveForward(0);
        }

        public void TurnRight(double power)
        {
            leftMotor.setPower(-power);
            rightMotor.setPower(power);
        }
        public void TurnLeft(double power)
        {
            leftMotor.setPower(power);
            rightMotor.setPower(-power);
        }

        public void newState (State newState){

            // Reset the state time, and then change to next state.
            mStateTime.reset();
            mCurrentState = newState;
        }
    }

