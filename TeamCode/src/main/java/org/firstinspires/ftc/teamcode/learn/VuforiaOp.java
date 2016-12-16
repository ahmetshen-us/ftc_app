//package org.firstinspires.ftc.teamcode;
//
//import com.qualcomm.robotcore.eventloop.opmode.OpMode;
//
//import org.firstinspires.ftc.robotcontroller.internal.FTCVuforia;
//import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;
//
//import java.util.HashMap;
//
///**
// * Created by ahmetshen-us on 10/4/16.
// */
//
//public class VuforiaOp extends OpMode {
//    FTCVuforia mFTCVuforia;
//    String ObjectName = "ObjectName";
//
//    @Override
//    public void init() {
//        mFTCVuforia = FtcRobotControllerActivity.getVuforia();
//        mFTCVuforia.addTrackables("Object.xml");
//        mFTCVuforia.initVuforia();
//
//    }
//
//    @Override
//    public void loop() {
//        HashMap<String, double[]> data = mFTCVuforia.getVuforiaData();
//        if (data.containsKey(ObjectName)) {
//            telemetry.addData("Object ", data.get(ObjectName));
//        }
//    }
//
//    @Override
//    public void stop() {
//        super.stop();
//        try {
//            mFTCVuforia.destroy();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
