import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

/**
 * Mark III of RoverRuckusTeleOp Created by Justin Luk on 2/5/19
 */

@TeleOp(name="Basic: Iterative OpMode", group="Iterative Opmode")
public class RoverRuckusTeleOpMarkIII extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor LFM = null;
    private DcMotor RFM = null;
    private DcMotor LBM = null;
    private DcMotor RBM = null;
    private DcMotor SlideLifter = null;
    private DcMotor Slider = null;
    private DcMotor HM = null;
    private CRServo S1 = null;
    private Servo Lockservo = null;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        LFM  = hardwareMap.get(DcMotor.class, "LFM");
        RFM = hardwareMap.get(DcMotor.class, "RFM");
        LBM  = hardwareMap.get(DcMotor.class, "LBM");
        RBM = hardwareMap.get(DcMotor.class, "RBM");
        SlideLifter  = hardwareMap.get(DcMotor.class, "SlideLifter");
        Slider = hardwareMap.get(DcMotor.class, "Slider");
        HM = hardwareMap.get(DcMotor.class, "HM");

        S1 = hardwareMap.get(CRServo.class,"S1");

        Lockservo = hardwareMap.get(Servo.class,"Lockerservo");


        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);
        SlideLifter.setDirection(DcMotor.Direction.REVERSE);

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");
    }
    @Override
    public void init_loop() {

    }
    @Override
    public void start() {
        runtime.reset();
    }


    public void loop() {
        double LFMpower;
        double LBMpower;
        double RFMpower;
        double RBMpower;

        double drive = -gamepad1.left_stick_y;
        double turn  =  gamepad1.right_stick_x;
        LFMpower    = Range.clip(drive + turn, -1.0, 1.0) ;
        RFMpower   = Range.clip(drive - turn, -1.0, 1.0) ;
        LBMpower    = Range.clip(drive + turn, -1.0, 1.0) ;
        RBMpower   = Range.clip(drive - turn, -1.0, 1.0) ;
        SlideLifter.setPower(-(gamepad2.right_stick_y)/2);
        Slider.setPower((gamepad2.left_stick_y));
        HM.setPower(gamepad2.right_stick_x);
        if (gamepad2.x) {
            S1.setPower(1);
        }
        if (gamepad2.y) {
            S1.setPower(-1);
        }
        if (gamepad2.left_stick_button){
            S1.setPower(0);
        }
        if (gamepad2.a) {
            Lockservo.setPosition(.6);
            telemetry.addLine("Ratchet engaged ");
        }

        if (gamepad2.b) {
            Lockservo.setPosition(0);
            telemetry.addLine("Ratchet disengaged ");
        }
        
        LFM.setPower(LFMpower);
        LBM.setPower(LBMpower);
        RFM.setPower(RFMpower);
        RBM.setPower(RBMpower);

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Motors", "left (%.2f), right (%.2f)",LFMpower,LBMpower,RFMpower,RBMpower);
    }
    @Override
    public void stop() {
        LFM.setPower(0);
        LBM.setPower(0);
        RFM.setPower(0);
        RBM.setPower(0);
        SlideLifter.setPower(0);
        Slider.setPower(0);
        S1.setPower(0);
    }

}
