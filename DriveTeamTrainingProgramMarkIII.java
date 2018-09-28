import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Justin Luk 11723
 * 9/28/2018
 * function over fun
 */
@TeleOp
public class DriveTeamTrainingProgramMarkIII extends OpMode{

    public DcMotor LFM, LBM, RFM, RBM;

    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        RFM.setDirection(DcMotor.Direction.REVERSE);
        RBM.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        RFM.setPower(-(gamepad1.right_stick_y));
        RBM.setPower(-(gamepad1.right_stick_y));
        LFM.setPower(-(gamepad1.left_stick_y));
        LBM.setPower(-(gamepad1.left_stick_y));

    }
}
