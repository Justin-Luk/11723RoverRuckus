import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class TestAuto extends LinearOpMode {
    public DcMotor RF, RB, LF, LB;

    @Override
    public void runOpMode() throws InterruptedException {
        RF = hardwareMap.dcMotor.get("RF");
        RB = hardwareMap.dcMotor.get("RB");
        LF = hardwareMap.dcMotor.get("LF");
        LB = hardwareMap.dcMotor.get("LB");

        LF.setDirection(DcMotor.Direction.REVERSE);
        LB.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();
        while (opModeIsActive()) {
            RF.setPower(1);
            RB.setPower(1);
            LF.setPower(1);
            LB.setPower(1);
            sleep(3000);

            RF.setPower(1);//turn
            RB.setPower(1);
            LF.setPower(-1);
            LB.setPower(-1);
            sleep(2500);

            RF.setPower(1);
            RB.setPower(1);
            LF.setPower(1);
            LB.setPower(1);
            sleep(3000);

            RF.setPower(1);//turn
            RB.setPower(1);
            LF.setPower(-1);
            LB.setPower(-1);
            sleep(2500);

        }

    }
}
