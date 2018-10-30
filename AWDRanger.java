import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class AWDRanger extends OpMode {
    private DcMotor RFM,RBM,LFM,LBM,HM;
    //private Servo HopperServo;
    // private int level = 0;
    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");
       // HopperServo = hardwareMap.servo.get("HopperServo");
        HM = hardwareMap.dcMotor.get("HM");

        RFM.setDirection(DcMotor.Direction.REVERSE);
        RBM.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        RFM.setPower(-(gamepad1.right_stick_y));
        RBM.setPower(-(gamepad1.right_stick_y));
        LFM.setPower(-(gamepad1.left_stick_y));
        LBM.setPower(-(gamepad1.left_stick_y));

       // HopperServo.setPosition(-(gamepad2.left_stick_y));
       HM.setPower(-(gamepad2.right_trigger));

        RFM.setPower(-(gamepad1.left_stick_x));
        RBM.setPower(-(gamepad1.right_stick_x));
        LFM.setPower(-(gamepad1.right_stick_x));
        LBM.setPower(-(gamepad1.left_stick_x));

        while(gamepad1.left_trigger > 0) {
            LFM.setPower(-0.5);
            RFM.setPower(0.5);
            LBM.setPower(0.5);
            RBM.setPower(-0.5);
        }
        while(gamepad1.right_trigger > 0) {
            LFM.setPower(0.5);
            RFM.setPower(-0.5);
            LBM.setPower(-0.5);
            RBM.setPower(0.5);
        }

    }
}
