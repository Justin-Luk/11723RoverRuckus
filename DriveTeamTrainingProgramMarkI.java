import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Justin Luk 11723
 */
@TeleOp
public class DriveTeamTrainingProgramMarkI extends OpMode{
    public DcMotor RFM,RBM,LFM,LBM;
    int level = 0;

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

        if (gamepad1.a) {
            level ++;
        }
        while (level == 0) {  //Low difficulty
            RFM.setPower(-(gamepad1.right_stick_y)/4);
            RBM.setPower(-(gamepad1.right_stick_y)/4);
            LFM.setPower(-(gamepad2.left_stick_y)/4);
            LBM.setPower(-(gamepad2.left_stick_y)/4);
        }
        while (level == 1) {  //Moderate difficulty
            RFM.setPower(-(gamepad1.right_stick_y));
            RBM.setPower(-(gamepad1.right_stick_y));
            LFM.setPower(-(gamepad2.left_stick_y));
            LBM.setPower(-(gamepad2.left_stick_y));


        }
        while (level == 2) {  //Insane difficulty
            RFM.setPower(-(gamepad1.right_stick_y)/4);
            RBM.setPower(-(gamepad1.left_stick_y)/4);
            LFM.setPower(-(gamepad2.right_stick_y));
            LBM.setPower(-(gamepad2.left_stick_y));
        }

    }
}
