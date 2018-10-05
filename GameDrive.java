import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Justin Luk 11723
 * 10/3/2018
 */
@TeleOp
//@Disabled
public class GameDrive extends OpMode {
    public DcMotor RFM,RBM,LFM,LBM;

    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        RFM.setPower(gamepad1.left_stick_y);// Forwards and backwards
        RBM.setPower(gamepad1.left_stick_y);
        LFM.setPower(gamepad1.left_stick_y);
        LBM.setPower(gamepad1.left_stick_y);

       while (gamepad1.left_stick_x > 0); { //strafing right
            LFM.setPower(-0.5);
            RFM.setPower(0.5);
            LBM.setPower(0.5);
            RBM.setPower(-0.5);
        }
        while(gamepad1.left_stick_x < 0) { //strafing left
            LFM.setPower(0.5);
            RFM.setPower(-0.5);
            LBM.setPower(-0.5);
            RBM.setPower(0.5);


    }
}
}
