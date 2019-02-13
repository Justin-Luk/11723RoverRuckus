import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@Autonomous(name = "VermontAutoI")
public class VermontAutoI extends LinearOpMode {

    private DcMotor LFM = null;
    private DcMotor RFM = null;
    private DcMotor LBM = null;
    private DcMotor RBM = null;
    private DcMotor SlideLifter = null;
    private DcMotor SlideLifter2 = null;
    private DcMotor Slider = null;
    private DcMotor HM = null;
    private CRServo S1 = null;
    private Servo LockServo = null;
    private Servo Marker = null;

    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";

    private TFObjectDetector tfod;

    private static final String VUFORIA_KEY = "AXxGX4//////AAABmaGzhDtLe0vztwUuFzptF78cmvdtkCQa4TLJaygK9Mued0mzNi3KaHkbVeeN1llvJDgiTItqnqEHP1SosYrZk3gZ948OKIw39IGN9dy+MV2AbXcAZEgkl26O6oK+Fr5728OXW75g04pt4+DRuf4GiUQgr6gBjJg0nbRV/7VzlYLwXHKrOK5SJ9rLugJ/rwsw1aVfJAwamNf4YNIaSh3SQgw0dL+nALMxEOC9Hb8aPSijZkW66JMgOz9bYJXZlJUGtRTodc8xes544zLyRNQx5j5aa0onYRADaqtcoNF2bw7PtgZCt0uDHJa+J1+5RZF0IS4X+Otj5VyxOC2z9kAMtbeLG90n71dYmRGgbAAk1DhO";
    private VuforiaLocalizer vuforia;

    ElapsedTime time;

    @Override
    public void runOpMode() {

        //**********INIT START**********//

        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");
        HM = hardwareMap.dcMotor.get("HM");
        SlideLifter = hardwareMap.dcMotor.get("SlideRotLeft");
        SlideLifter2 = hardwareMap.dcMotor.get("SliderRotRight");
        Slider = hardwareMap.dcMotor.get("SlideLin");

        S1 = hardwareMap.crservo.get("S1");
        LockServo = hardwareMap.servo.get("Lockservo");
        Marker = hardwareMap.servo.get("Marker");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        Marker.setPosition(.7);
        LockServo.setPosition(.55);


        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        waitForStart();

        //**********INIT STOP**********//
        if (opModeIsActive()){
            if (tfod != null) {
                tfod.activate();
            }

            //**********DETECT START**********//

            time.reset();

            String pos = "right";

            while (time.seconds() < 4 && opModeIsActive()) {

                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        if (updatedRecognitions.size() == 2) {
                            int goldMineralX = -1;
                            int silverMineral1X = -1;
                            for (Recognition recognition : updatedRecognitions) {

                                if(!opModeIsActive()){
                                    break;
                                }

                                if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                                    goldMineralX = (int) recognition.getLeft();
                                } else if (silverMineral1X == -1) {
                                    silverMineral1X = (int) recognition.getLeft();
                                }
                                if (goldMineralX != -1 && silverMineral1X != -1) {
                                    if (goldMineralX < silverMineral1X) {
                                        pos = "left";
                                    } else if (goldMineralX > silverMineral1X) {
                                        pos = "center";
                                    }
                                } else {
                                    pos = "right";
                                }
                            }
                            telemetry.addData("Position", pos);
                            telemetry.update();
                        }
                    }
                }

            }

            if (tfod != null) {
                tfod.shutdown();
            }
            //**********DETECT STOP**********//

            //**********DROP START**********//

            //DROP
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
            sleep(500);

            LFM.setPower(0.5);      //turn
            RFM.setPower(-0.5);
            LBM.setPower(0.5);
            RBM.setPower(-0.5);
            sleep(200);
            //**********DROP STOP**********//



            //**********MINERAL TAP START**********//

            switch (pos) {
                case "left":
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(300);
                    LFM.setPower(-.5); //back up
                    RFM.setPower(-.5);
                    LBM.setPower(-.5);
                    RBM.setPower(-.5);
                    sleep(1000);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(4000);
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
                    sleep(10000);


                    break;
                case "right":
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(300);
                    LFM.setPower(.5); //fowards
                    RFM.setPower(.5);
                    LBM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(1000);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(5000);
                    LFM.setPower(-.5);//Left turn
                    LBM.setPower(-.5);
                    RFM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(500);
                    LFM.setPower(1); //turn
                    RFM.setPower(-1);
                    LBM.setPower(1);
                    RBM.setPower(-1);
                    sleep(2000);
                    Marker.setPosition(1);
                    LFM.setPower(-1); //turn
                    RFM.setPower(1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(2000);
                    LFM.setPower(1); //fowards
                    RFM.setPower(1);
                    LBM.setPower(1);
                    RBM.setPower(1);
                    sleep(10000);



                    break;
                default:
                    LFM.setPower(0.5); // get out of lander range
                    RFM.setPower(-0.5);
                    LBM.setPower(-0.5);
                    RBM.setPower(0.5);
                    sleep(300);
                    LFM.setPower(1);       //knock over gold and drive into depot
                    RFM.setPower(-1);
                    LBM.setPower(-1);
                    RBM.setPower(1);
                    sleep(5000);
                    Marker.setPosition(1);
                    LFM.setPower(-.5);//right turn
                    LBM.setPower(-.5);
                    RFM.setPower(.5);
                    RBM.setPower(.5);
                    sleep(500);
                    LFM.setPower(1); //fowards
                    RFM.setPower(1);
                    LBM.setPower(1);
                    RBM.setPower(1);
                    sleep(10000);

                    break;
            }



            //**********MINERAL TAP STOP**********//
        }

    }

    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }}

