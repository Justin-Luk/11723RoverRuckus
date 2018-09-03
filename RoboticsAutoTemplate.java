import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

/**
 * Created by Justin Luk 11723
 * 9/3/2018
 */
@Autonomous
public class RoboticsAutoTemplate extends LinearOpMode {
    public DcMotor RFM, RBM, LFM, LBM;

    @Override
    public void runOpMode() throws InterruptedException {
        int phase = 0;

        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            if (phase == 0) {
                telemetry.addLine("phase 0");




                phase ++;
            }

            while (phase == 1) {
                telemetry.addLine("phase 1");




                phase ++;
            }
        }

    }
}
