import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Justin Luk 11723
 * 2/1/2019
 */
@Autonomous
public class AutoQualifierTwoFinalDepot extends LinearOpMode {
    private DcMotor RFM, RBM, LFM, LBM, HM, Slidelifter, Slider;
    private CRServo S1, S2;
    private Servo LockServo, Marker;
    private GoldAlignDetector detector;

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "DogeCV 2018.0 - Gold Align Example");

        // Set up detector
        detector = new GoldAlignDetector();
        detector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        detector.useDefaults();

        // Optional tuning
        detector.alignSize = 100;
        detector.alignPosOffset = 0;
        detector.downscale = 0.4;

        detector.areaScoringMethod = DogeCV.AreaScoringMethod.MAX_AREA;
        detector.maxAreaScorer.weight = 0.005; //

        detector.ratioScorer.weight = 5; //
        detector.ratioScorer.perfectRatio = 1.0; // Ratio adjustment

        detector.enable(); // Start the detector!

        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");
        HM = hardwareMap.dcMotor.get("HM");
        Slidelifter = hardwareMap.dcMotor.get("SlideRot");
        Slider = hardwareMap.dcMotor.get("SlideLin");
        int phase = 0;
        S1 = hardwareMap.crservo.get("S1");
        LockServo = hardwareMap.servo.get("Lockservo");
        Marker = hardwareMap.servo.get("Marker");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        Marker.setPosition(.7);
        LockServo.setPosition(.55);

        waitForStart();
        while (opModeIsActive()) {
            if (phase == 0) {
                HM.setPower(1);
                LockServo.setPosition(0);
                sleep(1000);
                HM.setPower(-.3);
                sleep(3000);

                LFM.setPower(-0.3);
                RFM.setPower(-0.3);
                LBM.setPower(-0.3);
                RBM.setPower(-0.3);
                sleep(500);

                LFM.setPower(0.5);
                RFM.setPower(-0.5);
                LBM.setPower(0.5);
                RBM.setPower(-0.5);
                sleep(250);
                phase ++;
            }

            if (phase == 1) {
                telemetry.addLine("phase 1");
                LFM.setPower(0.5);
                RFM.setPower(-0.5);
                LBM.setPower(-0.5);
                RBM.setPower(0.5);
                sleep(1800);


                LFM.setPower(-0.3);
                RFM.setPower(-0.3);
                LBM.setPower(-0.3);
                RBM.setPower(-0.3);
                sleep(1200);

                LFM.setPower(-0.5);
                RFM.setPower(0.5);
                LBM.setPower(0.5);
                RBM.setPower(-0.5);
                sleep(300);

                sleep(50);
                phase++;
            }

            if (phase == 2) {
                telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
                telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.
                telemetry.addLine("phase 2");
                if (detector.getAligned() == true) {
                    LFM.setPower(0.4);
                    RFM.setPower(-0.4);
                    LBM.setPower(-0.4);
                    RBM.setPower(0.4);
                    sleep(1750);
                    RFM.setPower(0);
                    RBM.setPower(0);
                    LFM.setPower(0);
                    LBM.setPower(0);
                    detector.disable();
                    phase++;
                }
                else if (detector.getAligned() == false) {
                    RFM.setPower(0.12);
                    RBM.setPower(0.12);
                    LFM.setPower(0.12);
                    LBM.setPower(0.12);
                    sleep(1000);
                }

            }
            if (phase ==3) {
                telemetry.addLine("phase 3");

                LFM.setPower(0.5);
                RFM.setPower(-0.5);
                LBM.setPower(-0.5);
                RBM.setPower(0.5);
                sleep(3000);

                Marker.setPosition(1);

                LFM.setPower(0);
                RFM.setPower(0);
                LBM.setPower(0);
                RBM.setPower(0);
                sleep(50000);
            }

        }
    }
}