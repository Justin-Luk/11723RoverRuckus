package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HardwareTestBot
{
    public DcMotor RightFrontMotor  = null;
    public DcMotor RightBackMotor  = null;
    public DcMotor LeftFrontMotor  = null;
    public DcMotor LeftBackMotor  = null;


    HardwareMap hwMap  = null;
    private ElapsedTime period  = new ElapsedTime();

    public HardwareTestBot() {


        public void init(HardwareMap ahwMap) {
        }
        hwMap = ahwMap;

        RightFrontMotor = hwMap.dcMotor.get("RightFrontMotor");
        RightBackMotor = hwMap.dcMotor.get ("RightBackMotor");
        LeftFrontMotor = hwMap.dcMotor.get ("LeftFrontMotor");
        LeftBackMotor = hwMap.dcMotor.get ("LeftBackMotor");
        LeftFrontMotor.setDirection(DcMotor.Direction.REVERSE);
        LeftBackMotor.setDirection(DcMotor.Direction.REVERSE);

        RightFrontMotor.setPower(0);
        RightBackMotor.setPower(0);
        LeftFrontMotor.setPower(0);
        LeftBackMotor.setPower(0);

        RightFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        RightBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftFrontMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LeftBackMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);



    }

    public void init(HardwareMap hardwareMap) {
    }
}
