package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Justin Luk 7/19/18
 * This is a OpMode that contains the basic drive systems
 */

@TeleOp (name = "RoverRuckusTeleOp", group = "RoverRuckus")
//@Disabled
public class RoverRuckusTeleOpIterative extends OpMode {
    private DcMotor RightFrontMotor;  //sets DcMotor as RightFrontMotor
    private DcMotor RightBackMotor;  //sets DcMotor as RightBackMotor
    private DcMotor LeftFrontMotor;  //sets DcMotor as LeftFrontMotor
    private DcMotor LeftBackMotor;  //sets DcMotor as LeftBackMotor

    //private Servo JewelServo;  //sets Servo as JewelServo    //in case we need it later

    @Override
    public void init() {
    {
        RightFrontMotor = hardwareMap.dcMotor.get ("RightFrontMotor");
        RightBackMotor = hardwareMap.dcMotor.get ("RightBackMotor");
        LeftFrontMotor = hardwareMap.dcMotor.get("LeftFrontMotor");
        LeftBackMotor = hardwareMap.dcMotor.get("LeftBackMotor");

        //JewelServo = hardwareMap.servo.get ("JewelServo");    //in case we need it later

        LeftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        LeftBackMotor.setDirection(DcMotor.Direction.REVERSE);


    @Override
    public void loop() {
        RightFrontMotor = (-(gamepad1.right_stick_y)/2);
        RightBackMotor = (-(gamepad1.left_stick_y)/2);

        while(gamepad1.right_trigger > 0) {          //Strafing to the right
            LeftFrontMotor.setPower(0.5);
            LeftBackMotor.setPower(-0.5);
            RightFrontMotor.setPower(-0.5);
            RightBackMotor.setPower(0.5);
        }
        while(gamepad1.left_trigger > 0)  {          //Strafing to the left
            LeftFrontMotor.setPower(0.5);
            LeftBackMotor.setPower (0.-5);
            RightFrontMotor.setPower (0.-5);
            RightBackMotor.setPower(0.5);
        }
        if (gamepad1.a) {                           // "nitro boost" Forwards at maximum speed. Cuts down on travel time
            LeftFrontMotor.setPower(1);
            RightFrontMotor.setPower(1);
            LeftBackMotor.setPower(1);
            RightBackMotor.setPower(1);
        }
    }

    }





    }
}

