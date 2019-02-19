import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

import org.firstinspires.ftc.teamcode.vision.MasterVision;
import org.firstinspires.ftc.teamcode.vision.SampleRandomizedPositions;

@Autonomous
public class VisionJavaExample extends LinearOpMode{
    private DcMotor LFM;
    private DcMotor LBM;
    private DcMotor RFM;
    private DcMotor RBM;
    private DcMotor SlideLifter;
    private DcMotor SlideLifter2;
    private DcMotor Slider;
    private DcMotor HM;
    private CRServo S1;
    private Servo LockServo;
    private Servo Marker;

    MasterVision vision;
    SampleRandomizedPositions goldPosition;

    @Override
    public void runOpMode() throws InterruptedException {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");
        HM = hardwareMap.dcMotor.get("HM");
//        SlideLifter = hardwareMap.dcMotor.get("SlideRotLeft");
//        SlideLifter2 = hardwareMap.dcMotor.get("SliderRotRight");
//        Slider = hardwareMap.dcMotor.get("SlideLin");

        S1 = hardwareMap.crservo.get("S1");
        LockServo = hardwareMap.servo.get("Lockservo");
        Marker = hardwareMap.servo.get("Marker");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        Marker.setPosition(.7);
        LockServo.setPosition(.55);


        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;// recommended camera direction
        parameters.vuforiaLicenseKey = "AXxGX4//////AAABmaGzhDtLe0vztwUuFzptF78cmvdtkCQa4TLJaygK9Mued0mzNi3KaHkbVeeN1llvJDgiTItqnqEHP1SosYrZk3gZ948OKIw39IGN9dy+MV2AbXcAZEgkl26O6oK+Fr5728OXW75g04pt4+DRuf4GiUQgr6gBjJg0nbRV/7VzlYLwXHKrOK5SJ9rLugJ/rwsw1aVfJAwamNf4YNIaSh3SQgw0dL+nALMxEOC9Hb8aPSijZkW66JMgOz9bYJXZlJUGtRTodc8xes544zLyRNQx5j5aa0onYRADaqtcoNF2bw7PtgZCt0uDHJa+J1+5RZF0IS4X+Otj5VyxOC2z9kAMtbeLG90n71dYmRGgbAAk1DhO";
        vision = new MasterVision(parameters, hardwareMap, false, MasterVision.TFLiteAlgorithm.INFER_RIGHT);
        vision.init();// enables the camera overlay. this will take a couple of seconds
        vision.enable();// enables the tracking algorithms. this might also take a little time
        telemetry.addData("GoldPosition", goldPosition);

        waitForStart();

        goldPosition = vision.getTfLite().getLastKnownSampleOrder();

        while(opModeIsActive()){
//            HM.setPower(1);   //land
//            LockServo.setPosition(0);
//            sleep(1000);
//            HM.setPower(-.3);
//            sleep(3000);
//            HM.setPower(0);
//
//            LFM.setPower(-0.3);   //back up
//            RFM.setPower(-0.3);
//            LBM.setPower(-0.3);
//            RBM.setPower(-0.3);
//            sleep(400);
//
//            LFM.setPower(0.5);      //turn
//            RFM.setPower(-0.5);
//            LBM.setPower(0.5);
//            RBM.setPower(-0.5);
//            sleep(100);
            telemetry.addData("goldPosition was", goldPosition);// giving feedback

            switch (goldPosition){ // using for things in the autonomous program
                case LEFT:
                    telemetry.addLine("going to the left");
                    HM.setPower(1);   //land
                    LockServo.setPosition(0);
                    sleep(1000);
                    HM.setPower(-.3);
                    sleep(3000);
                    HM.setPower(0);

                    LFM.setPower(-0.3);   //back up
                    RFM.setPower(-0.3);
                    LBM.setPower(-0.3);
                    RBM.setPower(-0.3);
                    sleep(400);

                    LFM.setPower(0.5);      //turn
                    RFM.setPower(-0.5);
                    LBM.setPower(0.5);
                    RBM.setPower(-0.5);
                    sleep(100);
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(150);
                    LFM.setPower(.5); //fowards
                    RFM.setPower(.5);
                    LBM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(500);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(3000);
                    LFM.setPower(-.5);//Left turn
                    LBM.setPower(-.5);
                    RFM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(250);
                    LFM.setPower(1); //turn
                    RFM.setPower(-1);
                    LBM.setPower(1);
                    RBM.setPower(-1);
                    sleep(1000);
                    Marker.setPosition(1);
                    LFM.setPower(-1); //turn
                    RFM.setPower(1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(1000);
                    LFM.setPower(1); //fowards
                    RFM.setPower(1);
                    LBM.setPower(1);
                    RBM.setPower(1);
                    sleep(7000);
                    LFM.setPower(0); //stop
                    RFM.setPower(0);
                    LBM.setPower(0);
                    RBM.setPower(0);
                    sleep(10000);


                    break;
                case CENTER:
                    telemetry.addLine("going straight");
                    HM.setPower(1);   //land
                    LockServo.setPosition(0);
                    sleep(1000);
                    HM.setPower(-.3);
                    sleep(3000);
                    HM.setPower(0);

                    LFM.setPower(-0.3);   //back up
                    RFM.setPower(-0.3);
                    LBM.setPower(-0.3);
                    RBM.setPower(-0.3);
                    sleep(400);

                    LFM.setPower(0.5);      //turn
                    RFM.setPower(-0.5);
                    LBM.setPower(0.5);
                    RBM.setPower(-0.5);
                    sleep(100);
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(150);
                    LFM.setPower(-0.5); // Back-ity Up-ity a bity
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(-0.5);
                    sleep(500);

                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(3000);
                    Marker.setPosition(1);
                    LFM.setPower(-.5);//right turn
                    LBM.setPower(-.5);
                    RFM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(250);
                    LFM.setPower(1); //fowards
                    RFM.setPower(1);
                    LBM.setPower(1);
                    RBM.setPower(1);
                    sleep(7000);
                    LFM.setPower(0); //stop
                    RFM.setPower(0);
                    LBM.setPower(0);
                    RBM.setPower(0);
                    sleep(10000);
                    break;
                case RIGHT:
                    telemetry.addLine("going to the right");
                    HM.setPower(1);   //land
                    LockServo.setPosition(0);
                    sleep(1000);
                    HM.setPower(-.3);
                    sleep(3000);
                    HM.setPower(0);

                    LFM.setPower(-0.3);   //back up
                    RFM.setPower(-0.3);
                    LBM.setPower(-0.3);
                    RBM.setPower(-0.3);
                    sleep(400);

                    LFM.setPower(0.5);      //turn
                    RFM.setPower(-0.5);
                    LBM.setPower(0.5);
                    RBM.setPower(-0.5);
                    sleep(100);
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(150);
                    LFM.setPower(-.5); //back up
                    RFM.setPower(-.5);
                    LBM.setPower(-.5);
                    RBM.setPower(-.5);
                    sleep(2000);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(3000);
                    //some kind of turn
                    LFM.setPower(.5);
                    LBM.setPower(.5);
                    RFM.setPower(-.5);
                    RBM.setPower(-.5);
                    sleep(500);
                    Marker.setPosition(1);
                    LFM.setPower(-1); //back up
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(-1);
                    sleep(7000);
                    LFM.setPower(0); //stop
                    RFM.setPower(0);
                    LBM.setPower(0);
                    RBM.setPower(0);
                    sleep(10000);

                    break;
                case UNKNOWN:
                    telemetry.addLine("staying put");
                    HM.setPower(1);   //land
                    LockServo.setPosition(0);
                    sleep(1000);
                    HM.setPower(-.3);
                    sleep(3000);
                    HM.setPower(0);

                    LFM.setPower(-0.3);   //back up
                    RFM.setPower(-0.3);
                    LBM.setPower(-0.3);
                    RBM.setPower(-0.3);
                    sleep(400);

                    LFM.setPower(0.5);      //turn
                    RFM.setPower(-0.5);
                    LBM.setPower(0.5);
                    RBM.setPower(-0.5);
                    sleep(100);
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(150);
                    LFM.setPower(-0.5); // Back-ity Up-ity a bity
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(-0.5);
                    sleep(500);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(3000);
                    LFM.setPower(1);
                    RFM.setPower(-1);
                    LBM.setPower(1);
                    RBM.setPower(-1);
                    sleep(1000);
                    Marker.setPosition(1);
                    LFM.setPower(-1);
                    RFM.setPower(1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(1000);
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(200);
                    LFM.setPower(-.5);//right turn
                    LBM.setPower(-.5);
                    RFM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(250);
                    LFM.setPower(1); //fowards
                    RFM.setPower(1);
                    LBM.setPower(1);
                    RBM.setPower(1);
                    sleep(7000);
                    LFM.setPower(0);
                    RFM.setPower(0);
                    LBM.setPower(0);
                    RBM.setPower(0);
                    sleep(10000);

                    break;
            }

            telemetry.update();
        }

        vision.shutdown();
    }
}