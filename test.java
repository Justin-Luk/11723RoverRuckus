import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
@TeleOp
public class test extends OpMode {
    DcMotor RF, RB, LF, LB;
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

        if (gamepad1.a&gamepad1.b){
            RF.setPower((gamepad1.right_stick_y)/2);        //fairly safe and decently fast
            RB.setPower((gamepad1.right_stick_y)/2);
            LF.setPower((gamepad1.left_stick_y)/2);
            LB.setPower((gamepad1.left_stick_y)/2);
        }

        if (gamepad1.y&gamepad1.x) {
            RF.setPower((gamepad1.right_stick_y));      //Possible fuse burnout but extremely fast
            RB.setPower((gamepad1.right_stick_y));
            LF.setPower((gamepad1.left_stick_y));
            LB.setPower((gamepad1.left_stick_y));
        }

        if (gamepad1.right_bumper) {            //strafe right?
            RF.setPower(-1);
            RB.setPower(1);
            LF.setPower(1);
            LB.setPower(-1);
        }

        if (gamepad1.left_bumper) {     //strafe left?
            RF.setPower(1);
            RB.setPower(-1);
            LF.setPower(-1);
            LB.setPower(1);
        }




    }
}
