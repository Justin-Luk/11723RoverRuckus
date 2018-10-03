import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Justin Luk 11723
 * 10/2/2018
 * diagonal driving too!
 */
public class AWDRangerBotMarkII extends OpMode {
    private DcMotor RFM,RBM,LFM,LBM;
    //private int level = 0;

    @Override
    public void init() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        telemetry.addLine("May the force be with you");


    }

    @Override
    public void loop() {
        RFM.setPower(-(gamepad1.right_stick_y) / 2);
        RBM.setPower(-(gamepad1.right_stick_y) / 2);
        LFM.setPower(-(gamepad1.left_stick_y) / 2);
        LBM.setPower(-(gamepad1.left_stick_y) / 2);

        RFM.setPower(-(gamepad1.left_stick_x) / 2);
        RBM.setPower(-(gamepad1.right_stick_x) / 2);
        LFM.setPower(-(gamepad1.right_stick_x) / 2);
        LBM.setPower(-(gamepad1.left_stick_x) / 2);


        while(gamepad1.right_trigger > 0) { //strafing right
            LFM.setPower(-0.5);
            RFM.setPower(0.5);
            LBM.setPower(0.5);
            RBM.setPower(-0.5);
        }
        while(gamepad1.left_trigger > 0) { //strafing left
            LFM.setPower(0.5);
            RFM.setPower(-0.5);
            LBM.setPower(-0.5);
            RBM.setPower(0.5);
        }
       // while (gamepad1.dpad_up) { //diagonal up-right
         //   LFM.setPower(1);
         //   RBM.setPower(1);
     //   }
       // while (gamepad1.dpad_left) { //diagonal up-left
       //     LBM.setPower(1);
       //     RFM.setPower(1);
      //  }
       // while (gamepad1.dpad_up) { //diagonal down-left
       //     LFM.setPower(-1);
      //      RBM.setPower(-1);
      //  }
     //   while (gamepad1.dpad_up) { //diagonal down-right
     //       LBM.setPower(-1);
     //       RFM.setPower(-1);
       // }
        //RFM.setPower(-(gamepad1.left_stick_y));
        //RBM.setPower(-(gamepad1.left_stick_y));
        //LFM.setPower(-(gamepad1.left_stick_y));
        //LBM.setPower(-(gamepad1.left_stick_y));




    }
}
