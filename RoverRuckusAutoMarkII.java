package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Justin Luk 11723
 * 12/27/2018
 */
public class RoverRuckusAutoMarkII extends LinearOpMode {
    public DcMotor RFM, RBM, LFM, LBM, Hanger, SlideLifter, Slider;
    public  CRServo S1, S2;
    public Servo Lockservo;
    @Override
    public void runOpMode() throws InterruptedException {
        int phase = 0;
        int oneRev = 28 * 40;

        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");

        S1 = hardwareMap.crservo.get("S1");
        S2 = hardwareMap.crservo.get("S2");

        Lockservo = hardwareMap.servo.get("Lockservo");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

    }
}
