import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/**
 * Created by Justin Luk 11723
 * 9/8/2018
 */
public class RoverRuckusAutoMarkI extends LinearOpMode {
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

                //drop into landing zone

                RFM.setPower(0.5);  //move to mineral choice
                RBM.setPower(0.5);
                LFM.setPower(0.5);
                LBM.setPower(0.5);
                sleep(1000);

                //doge cv thing thing
                //collect gold mineral if that counts as removing it
                phase ++;
            }

            while (phase == 2) {
                telemetry.addLine("phase 2");

                RFM.setPower(0.5);  //move to depot
                RBM.setPower(0.5);
                LFM.setPower(0.5);
                LBM.setPower(0.5);
                sleep(1000);

                //place team marker and drop gold mineral into the depot

                phase ++;
            }

            while (phase == 3) {
                RFM.setPower(0.5);  //turn to face wall
                RBM.setPower(0.5);
                LFM.setPower(-0.5);
                LBM.setPower(-0.5);
                sleep(300);

                RFM.setPower(0.3);  //move to wall
                RBM.setPower(0.3);
                LFM.setPower(0.3);
                LBM.setPower(0.3);
                sleep(500);

                RFM.setPower(0.5);  //turn to face crater
                RBM.setPower(0.5);
                LFM.setPower(-0.5);
                LBM.setPower(-0.5);
                sleep(500);

                RFM.setPower(1);  //speed towards crater
                RBM.setPower(1);
                LFM.setPower(1);
                LBM.setPower(1);
                sleep(3000);

                RFM.setPower(0);  //go into crater
                RBM.setPower(0);
                LFM.setPower(0);
                LBM.setPower(0);

                phase ++;
            }
        }

    }
}


