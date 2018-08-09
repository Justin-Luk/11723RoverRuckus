package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class RangerBotLinearTele extends OpMode {

    private DcMotor RightDrive,LeftDrive;


    @Override
    public void init() {

        RightDrive = hardwareMap.dcMotor.get("RightDrive");
        LeftDrive = hardwareMap.dcMotor.get("LeftDrive");

        LeftDrive.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {

        RightDrive.setPower(-(gamepad1.right_stick_y) / 2);
        LeftDrive.setPower(-(gamepad1.left_stick_y) / 2);


        while (gamepad1.right_trigger > 0) {
            RightDrive.setDirection(DcMotor.Direction.REVERSE);
            LeftDrive.setDirection(DcMotor.Direction.FORWARD);

        }
    }
}
