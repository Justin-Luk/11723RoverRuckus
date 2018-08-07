package org.firstinspires.ftc.teamcode;

//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import android.util.Range;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
//import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Justin Luk 7/19/18
 * This is a Linear OpMode that contains the drive systems along with some other fun stuff
 */

@TeleOp (name = "RoverRuckusTeleOp", group = "RoverRuckus")
//@Disabled
public class RoverRuckusTeleOp extends LinearOpMode
{
    private DcMotor RightFrontMotor;  //sets DcMotor as RightFrontMotor
    private DcMotor RightBackMotor;  //sets DcMotor as RightBackMotor
    private DcMotor LeftFrontMotor;  //sets DcMotor as LeftFrontMotor
    private DcMotor LeftBackMotor;  //sets DcMotor as LeftBackMotor

    //private Servo JewelServo;  //sets Servo as JewelServo    //in case we need it later

    //boolean aButtonPreviousState = false;
    //boolean SportModeActive = false;

    //boolean bButtonPreviousState = false;
    //boolean EconomyModeActive = false;
    @Override
    public void runOpMode () throws InterruptedException {
        RightFrontMotor = hardwareMap.dcMotor.get("RightFrontMotor");
        RightBackMotor = hardwareMap.dcMotor.get("RightBackMotor");
        LeftFrontMotor = hardwareMap.dcMotor.get("LeftFrontMotor");
        LeftBackMotor = hardwareMap.dcMotor.get("LeftBackMotor");

        //JewelServo = hardwareMap.servo.get("JewelServo");    //in case we need it later

        LeftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        LeftBackMotor.setDirection(DcMotor.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            LeftFrontMotor.setPower(-(gamepad1.left_stick_y) / 2); //Regular mode:A happy medium between speed/power and maneuverability
            LeftBackMotor.setPower(-(gamepad1.left_stick_y) / 2);
            RightFrontMotor.setPower(-(gamepad1.right_stick_y) / 2);
            RightBackMotor.setPower(-(gamepad1.right_stick_y) / 2);

            while (gamepad1.right_trigger > 0)              //Strafing to the right
                LeftFrontMotor.setPower(-0.5);
            LeftBackMotor.setPower(0.5);
            RightFrontMotor.setPower(0.5);
            RightBackMotor.setPower(-0.5);

            while (gamepad1.right_trigger > 0)              //Strafing to the left
                LeftFrontMotor.setPower(0.5);
            LeftBackMotor.setPower(-0.5);
            RightFrontMotor.setPower(-0.5);
            RightBackMotor.setPower(0.5);

            //Sport mode: Faster with more power but harder to control
            //if (gamepad1.a && !aButtonPreviousState)
                //SportModeActive = !SportModeActive;

            //if (SportModeActive) {
                //LeftFrontMotor.setPower(Range.clip(gamepad1.left_stick_y));
            //LeftBackMotor.setPower(Range.clip(gamepad1.left_stick_y));
            //RightFrontMotor.setPower(Range.clip(gamepad1.right_stick_y));
            //RightBackMotor.setPower(Range.clip(gamepad1.right_stick_y));
        //}else
                //RightFrontMotor.setPower(-(gamepad1.right_stick_y)/2);
            //RightBackMotor.setPower(-(gamepad1.right_stick_y)/2);
            //LeftFrontMotor.setPower(-(gamepad1.left_stick_y)/2);
            //LeftBackMotor.setPower(-(gamepad1.left_stick_y)/2);

            //Easier to control for precise maneuvers but less speed and power
             //if (gamepad1.b && !bButtonPreviousState)
                 //EconomyModeActive = !EconomyModeActive;

             //if (EconomyModeActive) {
                 //LeftFrontMotor.setPower(Range.clip(gamepad1.left_stick_y)/4);
                 //LeftBackMotor.setPower(Range.clip(gamepad1.left_stick_y)/4);
               //  RightFrontMotor.setPower(Range.clip(gamepad1.right_stick_y)/4);
                // RightBackMotor.setPower(Range.cip(gamepad1.right_stick_y)/4);

             //}else
                 //RightFrontMotor.setPower(-(gamepad1.right_stick_y)/2);
            //RightBackMotor.setPower(-(gamepad1.right_stick_y)/2);
            //LeftFrontMotor.setPower(-(gamepad1.left_stick_y)/2);
            //LeftBackMotor.setPower(-(gamepad1.left_stick_y)/2);




            }
        idle();    //waitForNextHardwareCycle();

    }
}

