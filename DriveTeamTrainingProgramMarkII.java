import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import static java.lang.Thread.sleep;

/**
 * Created by Justin Luk 11723
 * 9/25/2018
 * It's like DriveTeamTrainingProgramMarkI but less ridiculous. Also motor failure incorporated
 */
public class DriveTeamTrainingProgramMarkII extends OpMode {

    private DcMotor RFM,RBM,LFM,LBM;
    private int level = 0;

    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        RFM.setDirection(DcMotor.Direction.REVERSE);
        RBM.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("Welcome,press play to begin!");
        telemetry.addLine("To move up one level, press 'a' on gamepad1 ");

    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            level ++;

        }

        while (level == 0) {  //Low difficulty:Each driver gets a different side of the robot
            RFM.setPower(-(gamepad1.right_stick_y) / 2);
            RBM.setPower(-(gamepad1.right_stick_y) / 2);
            LFM.setPower(-(gamepad1.left_stick_y) / 2);
            LBM.setPower(-(gamepad1.left_stick_y) / 2);
            telemetry.addLine("level 0");
        }
        while (level == 1) {  //Low difficulty:Each driver gets a different side of the robot
            RFM.setPower(-(gamepad1.right_stick_y) / 4);
            RBM.setPower(-(gamepad1.right_stick_y) / 4);
            LFM.setPower(-(gamepad2.left_stick_y) / 4);
            LBM.setPower(-(gamepad2.left_stick_y) / 4);
            telemetry.addLine("level 1");

            if (gamepad2.b){
              LFM.setPower(0);
            }
            if (gamepad2.start);{
                LFM.setPower(-(gamepad2.left_stick_y)/4);
            }

        }


    }
}
