package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

class SimpleExampleAutoMarkI extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        int counter = 0;
        int oneRev = 28 * 40;

        DcMotor RFM = hardwareMap.dcMotor.get("RFM");
        DcMotor LFM = hardwareMap.dcMotor.get("LFM");
        DcMotor RBM = hardwareMap.dcMotor.get("RBM");
        DcMotor LBM = hardwareMap.dcMotor.get("LBM");
        RFM.setDirection(DcMotor.Direction.REVERSE);
        RBM.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        if (opModeIsActive()) {  //move forwards for 3 seconds at half speed
            RFM.setPower(0.5);
            RBM.setPower(0.5);
            LFM.setPower(0.5);
            LBM.setPower(0.5);
            sleep(3000);
            RFM.setPower(0);
            RBM.setPower(0);
            LFM.setPower(0);
            LBM.setPower(0);

        if (opModeIsActive()) {
            RFM.setPower(0.5);
            RBM.setPower(0.5);
            LFM.setPower(-0.5);
            LBM.setPower(-0.5);
            sleep(500);
            RFM.setPower(0);
            RBM.setPower(0);
            LFM.setPower(0);
            LBM.setPower(0);


        while (counter == 1);

            RFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            RBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LFM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            LBM.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            RFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            RBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LFM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            LBM.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            RFM.setTargetPosition(oneRev*3);
            RBM.setTargetPosition(oneRev*3);
            LFM.setTargetPosition(oneRev*3);
            LBM.setTargetPosition(oneRev*3);
        }
        }

    }
}
