import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.robot.Robot;

/**
 * Mark II of RoverRuckusTeleOp Created by Justin Luk on 8/7/18
 */
@TeleOp
public class RoverRuckusTeleOpMarkII extends OpMode {
    private DcMotor RFM,RBM,LFM,LBM,SlideLifter,Slider, Hanger; //Establishes motors
    private CRServo S1, S2;
    private Servo Lockservo;

    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM"); //gets RFM on hardware map
        RBM = hardwareMap.dcMotor.get("RBM"); //gets RBM on hardware map
        LFM = hardwareMap.dcMotor.get("LFM"); //gets LFM on hardware map
        LBM = hardwareMap.dcMotor.get("LBM"); //gets LBM on hardware map
        SlideLifter = hardwareMap.dcMotor.get("SlideLifter");
        Slider = hardwareMap.dcMotor.get("Slider");
        Hanger = hardwareMap.dcMotor.get("Hanger");

        S1 = hardwareMap.crservo.get("S1");
        S2 = hardwareMap.crservo.get("S2");

        Lockservo = hardwareMap.servo.get("Lockservo");

        LFM.setDirection(DcMotor.Direction.REVERSE); //sets both left side motors on reverse
        LBM.setDirection(DcMotor.Direction.REVERSE);
        S2.setDirection(CRServo.Direction.REVERSE);
    }

    @Override
    public void loop() {

        RFM.setPower(-(gamepad1.right_stick_y)); //establishes basic tank drive controls
        RBM.setPower(-(gamepad1.right_stick_y));
        LFM.setPower(-(gamepad1.left_stick_y));
        LBM.setPower(-(gamepad1.left_stick_y));
        SlideLifter.setPower(-(gamepad2.left_stick_y)/2);
        Slider.setPower((gamepad2.right_stick_y)/2);
        S1.setPower(gamepad2.left_stick_y);
        S1.setPower(gamepad2.left_stick_y);
        Hanger.setPower(gamepad2.right_stick_x);

        RFM.setPower(-(gamepad1.left_stick_x));
        RBM.setPower(-(gamepad1.right_stick_x));
        LFM.setPower(-(gamepad1.right_stick_x));
        LBM.setPower(-(gamepad1.left_stick_x));

        if (gamepad2.dpad_up) {
            Lockservo.setPosition(.15);
            telemetry.addLine("Ratchet engaged ");
        }

        if (gamepad2.dpad_down) {
            Lockservo.setPosition(0);
            telemetry.addLine("Ratchet disengaged ");
        }

        while(gamepad1.right_trigger > 0) { //establishes strafing to the right

         LFM.setPower(-0.5);
         RFM.setPower(0.5);
         LBM.setPower(0.5);
         RBM.setPower(-0.5);
         }

         while(gamepad1.left_trigger > 0) { //establishes strafing to the left

           LFM.setPower(0.5);
           RFM.setPower(-0.5);
           LBM.setPower(-0.5);
           RBM.setPower(0.5);

    } }

}