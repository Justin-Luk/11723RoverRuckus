import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Justin Luk 11723
 * 1/19/2019
 */

@Autonomous
public class TeamMarkerAutoTest extends LinearOpMode {
    private DcMotor RFM, RBM, LFM, LBM, HM, SlideRot, SlideLin;
    private CRServo S1, S2;
    private Servo LockServo, Marker;

    @Override
    public void runOpMode() throws InterruptedException {
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

    Marker.setPosition(.7);
    LockServo.setPosition(.55);

    waitForStart();
    while (opModeIsActive()) {

        HM.setPower(0);
        sleep(100);
        HM.setPower(.3);
        sleep(100);
        LockServo.setPosition(0);
        HM.setPower(.5);
        sleep(500);
        HM.setPower(-.5);
        sleep(2000);

        RFM.setPower(-1);
        RBM.setPower(-1);
        LFM.setPower(-1);
        LBM.setPower(-1);
        sleep(100);

        LFM.setPower(0.5);
        RFM.setPower(-0.5);
        LBM.setPower(-0.5);
        RBM.setPower(0.5);
        sleep(500);

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
