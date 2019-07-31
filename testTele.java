import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp
public class testTele extends OpMode {
    private DcMotor RF, RB, LF, LB;
    @Override
    public void init() {
        RF = hardwareMap.dcMotor.get("RF");
        RB = hardwareMap.dcMotor.get("RB");
        LF = hardwareMap.dcMotor.get("LF");
        LB = hardwareMap.dcMotor.get("LB");

        RF.setDirection(DcMotor.Direction.REVERSE);
        RB.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        RF.setPower(-(gamepad1.right_stick_y));
        RF.setPower(-(gamepad1.right_stick_y));
        LF.setPower(-(gamepad1.left_stick_y));
        LB.setPower(-(gamepad1.left_stick_y));

        while(gamepad1.left_stick_x < 0) {

            LF.setPower(-0.5);
            RF.setPower(0.5);
            LB.setPower(0.5);
            RB.setPower(-0.5);

        }

        while(gamepad1.right_stick_x > 0) {

            LF.setPower(0.5);
            RF.setPower(-0.5);
            LB.setPower(-0.5);
            RB.setPower(0.5);

        }

        if (gamepad1.a) {
            RF.setPower(1);
            RB.setPower(1);
            LF.setPower(1);
            LB.setPower(1);
        }

        if (gamepad1.b) {
            RF.setPower(0);
            RB.setPower(0);
            LF.setPower(0);
            LB.setPower(0);


        }

        if (gamepad1.left_bumper){
            RF.setPower(1);
            RB.setPower(1);
            LF.setPower(1);
            LB.setPower(1);

            RF.setPower(1);
            RB.setPower(1);
            LF.setPower(0);
            LB.setPower(0);

            RF.setPower(-1);
            RB.setPower(-1);
            LF.setPower(-1);
            LB.setPower(-1);

            RF.setPower(0);
            RB.setPower(0);
            LF.setPower(1);
            LB.setPower(1);
        }
    }
}
