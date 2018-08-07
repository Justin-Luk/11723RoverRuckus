package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class RoverRuckusTeleOpMarkII extends OpMode {

    private DcMotor RFM;  //Right Front Motor
    private DcMotor RBM;  //Right Back Motor
    private DcMotor LFM;  //Left Front Motor
    private DcMotor LBM;  //Left Back Motor


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
       RFM.setPower(-(gamepad1.right_stick_y)/2);
       RBM.setPower(-(gamepad1.right_stick_y)/2);
       LFM.setPower(-(gamepad1.left_stick_y)/2);
       LBM.setPower(-(gamepad1.left_stick_y)/2);

    }
}
