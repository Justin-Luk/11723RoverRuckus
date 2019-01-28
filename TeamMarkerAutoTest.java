import com.disnodeteam.dogecv.CameraViewDisplay;
import com.disnodeteam.dogecv.DogeCV;
import com.disnodeteam.dogecv.detectors.roverrukus.GoldAlignDetector;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Justin Luk for Canton GearHounds 11723
 * 1/19/2019
 */

@Autonomous
public class TeamMarkerAutoTest extends LinearOpMode {
    private DcMotor RFM, RBM, LFM, LBM, HM, SlideRot, SlideLin;
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
        HM = hardwareMap.dcMotor.get("Hanger");
        SlideRot = hardwareMap.dcMotor.get("SlideRot");
        SlideLin = hardwareMap.dcMotor.get("SlideLin");

    S1 = hardwareMap.crservo.get("S1");
    S2 = hardwareMap.crservo.get("S2");
    LockServo = hardwareMap.servo.get("Lockservo");
    Marker = hardwareMap.servo.get("Marker");

    LFM.setDirection(DcMotor.Direction.REVERSE);
    LBM.setDirection(DcMotor.Direction.REVERSE);
    S2.setDirection(CRServo.Direction.REVERSE);
    int phase = 0;
    Marker.setPosition(.7);
    LockServo.setPosition(.55);

    waitForStart();
    while (opModeIsActive()) {


        if (phase == 0) {
            telemetry.addLine("phase 1");       //get down from the lander
            detector.disable();
            HM.setPower(0);
            sleep(100);
            HM.setPower(.5);
            sleep(100);
            LockServo.setPosition(0);
            HM.setPower(.5);
            sleep(500);
            HM.setPower(-.5);
            sleep(2000);

            RFM.setPower(-1);       //back up
            RBM.setPower(-1);
            LFM.setPower(-1);
            LBM.setPower(-1);
            sleep(100);

            LFM.setPower(0.5);      //strafe to the right
            RFM.setPower(-0.5);
            LBM.setPower(-0.5);
            RBM.setPower(0.5);
            sleep(500);

            RFM.setPower(.5);       //fowards to align with first mineral
            RBM.setPower(.5);
            LFM.setPower(.5);
            LBM.setPower(.5);
            sleep(100);
            phase ++;
        }
        if (phase == 1) {
            telemetry.addData("IsAligned", detector.getAligned()); // Is the bot aligned with the gold mineral?
            telemetry.addData("X Pos", detector.getXPosition()); // Gold X position.
            telemetry.addLine("phase 0");
            if (detector.getAligned() == true) {
                LFM.setPower(0.4);
                RFM.setPower(-0.4);
                LBM.setPower(-0.4);
                RBM.setPower(0.4);
                sleep(1500);
                phase ++;
            } else if (detector.getAligned() == false) {
                RFM.setPower(-0.2);
                RBM.setPower(-0.2);
                LFM.setPower(-0.2);
                LBM.setPower(-0.2);
                sleep(1000);
                RFM.setPower(0);
                RBM.setPower(0);
                LFM.setPower(0);
                LBM.setPower(0);
            }

            sleep(100);
            if (detector.getAligned() == true) {
                LFM.setPower(0.4);
                RFM.setPower(-0.4);
                LBM.setPower(-0.4);
                RBM.setPower(0.4);
                sleep(1500);
                phase ++;
            }
        }

        if (phase == 2) {
            RFM.setPower(-0.5);
            RBM.setPower(-0.5);
            LFM.setPower(0.5);
            LBM.setPower(0.5);
            sleep(1000);

            RFM.setPower(.75);
            RBM.setPower(.75);
            LFM.setPower(.75);
            LBM.setPower(.75);
            sleep(2000);

            Marker.setPosition(1);

            RFM.setPower(0);
            RBM.setPower(0);
            LFM.setPower(0);
            LBM.setPower(0);
            HM.setPower(0);
            sleep(1000000);
            }
        }
    }
}