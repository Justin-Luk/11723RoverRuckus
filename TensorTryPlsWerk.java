/* Copyright (c) 2018 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

/**
 * This 2018-2019 OpMode illustrates the basics of using the TensorFlow Object Detection API to
 * determine the position of the gold and silver minerals.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list.
 *
 * IMPORTANT: In order to use this OpMode, you need to obtain your own Vuforia license key as
 * is explained below.
 */
@Autonomous(name = "TensorTryPlsWerk", group = "Concept")
public class TensorTryPlsWerk extends LinearOpMode {
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private DcMotor LFM;
    private DcMotor LBM;
    private DcMotor RFM;
    private DcMotor RBM;
    private DcMotor SlideLifter;
    private DcMotor SlideLifter2;
    private DcMotor Slider;
    private DcMotor HM;
    private CRServo S1;
    private Servo LockServo;
    private Servo Marker;
    private int spot;

    private static final String VUFORIA_KEY = "AWExQGH/////AAABmUyf+WYnmELPieDUqdVTFetkngpjg7cgQWKbTL6UT8022kOxNZPQqC5WX4j/zxx20YYURnvVSBarfx6DRMFvzrxG6lRd8EcAN9pjrju0bOKof3PSP6u7U0xe9F/TbxlQ3hq338sy9FJsWS5jQyvldG9QBc9v5keOidHi/GAc8lMictGR/ttu+zVzHq48kxeAZ9JdkJxd23eew4OUi390isg1fd1bY6YF57YIik63MCz6HrR4ALlVjH+f5hL6aqYS8WjEcdU49+zO46D7bhxH7W/TrodUDadDLATs+I9OPTN/nZYg7REWxoberOzNDd7skrfqMfjwlLfJ1MsU7OaMtsfE+a1aTjxhbZ2yDTtlpZu4";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        RFM = hardwareMap.dcMotor.get("RFM");
        RBM = hardwareMap.dcMotor.get("RBM");
        LFM = hardwareMap.dcMotor.get("LFM");
        LBM = hardwareMap.dcMotor.get("LBM");
        HM = hardwareMap.dcMotor.get("HM");
       SlideLifter = hardwareMap.dcMotor.get("SlideRotLeft");
        SlideLifter2 = hardwareMap.dcMotor.get("SlideRotRight");
        Slider = hardwareMap.dcMotor.get("SlideLin");

        S1 = hardwareMap.crservo.get("S1");
        LockServo = hardwareMap.servo.get("Lockservo");
        Marker = hardwareMap.servo.get("Marker");

        LFM.setDirection(DcMotor.Direction.REVERSE);
        LBM.setDirection(DcMotor.Direction.REVERSE);

        Marker.setPosition(.7);
        LockServo.setPosition(.55);

        // The TFObjectDetector uses the camera frames from the VuforiaLocalizer, so we create that
        // first.
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        /** Wait for the game to begin */
        telemetry.addData(">", "Press Play to start tracking");
        telemetry.update();
        waitForStart();

        if (opModeIsActive()) {
            /** Activate Tensor Flow Object Detection. */
            if (tfod != null) {
                tfod.activate();
            }

            while (opModeIsActive()) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                      telemetry.addData("# Object Detected", updatedRecognitions.size());
                      if (updatedRecognitions.size() == 3) {
                        int goldMineralX = -1;
                        int silverMineral1X = -1;
                        int silverMineral2X = -1;
                        for (Recognition recognition : updatedRecognitions) {
                          if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                            goldMineralX = (int) recognition.getLeft();
                          } else if (silverMineral1X == -1) {
                            silverMineral1X = (int) recognition.getLeft();
                          } else {
                            silverMineral2X = (int) recognition.getLeft();
                          }
                        }
                        if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                          if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Left");
                             spot=1;


                          } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                            telemetry.addData("Gold Mineral Position", "Right");
                            spot=3;

                          } else {
                            telemetry.addData("Gold Mineral Position", "Center");
                              spot=2;

                          }
                        }
                      }
                      if (spot == 1) {
                          telemetry.addLine("going to the left");
                          HM.setPower(1);   //land
                          LockServo.setPosition(0);
                          sleep(1000);
                          HM.setPower(-.3);
                          sleep(3000);
                          HM.setPower(0);

                          LFM.setPower(-0.3);   //back up
                          RFM.setPower(-0.3);
                          LBM.setPower(-0.3);
                          RBM.setPower(-0.3);
                          sleep(450);

                          LFM.setPower(0.5);      //turn
                          RFM.setPower(-0.5);
                          LBM.setPower(0.5);
                          RBM.setPower(-0.5);
                          sleep(150);
                          LFM.setPower(0.5); // get out of lander range
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(0.5);
                          sleep(150);
                          LFM.setPower(.5); //fowards
                          RFM.setPower(.5);
                          LBM.setPower(.5);
                          RBM.setPower(.5);
                          sleep(600);
                          LFM.setPower(1);       //knock over gold and drive into depot
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(3000);

                          LFM.setPower(-.5);//Left turn
                          LBM.setPower(-.5);
                          RFM.setPower(.5);
                          RBM.setPower(.5);
                          sleep(250);
                          LFM.setPower(-1);        //back up a bit
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(1000);
                          LFM.setPower(-0.5); //move a little out of depot
                          RFM.setPower(0.5);
                          LBM.setPower(0.5);
                          RBM.setPower(-0.5);
                          sleep(200);
                          LFM.setPower(1); //turn
                          RFM.setPower(-1);
                          LBM.setPower(1);
                          RBM.setPower(-1);
                          sleep(1000);
                          Marker.setPosition(1);
                          LFM.setPower(-1); //turn
                          RFM.setPower(1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(900);
                          LFM.setPower(0.5); //move back towards wall
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(0.5);
                          sleep(200);
                          LFM.setPower(1); //fowards
                          RFM.setPower(1);
                          LBM.setPower(1);
                          RBM.setPower(1);
                          sleep(4000);
//                          SlideLifter.setPower(1);
//                          SlideLifter2.setPower(-1);
//                          sleep(100);
                          LFM.setPower(0); //stop
                          RFM.setPower(0);
                          LBM.setPower(0);
                          RBM.setPower(0);
                          sleep(10000);
                      }
                      if (spot ==2) {
                          telemetry.addLine("going straight");
                          HM.setPower(1);   //land
                          LockServo.setPosition(0);
                          sleep(1000);
                          HM.setPower(-.3);
                          sleep(3000);
                          HM.setPower(0);

                          LFM.setPower(-0.3);   //back up
                          RFM.setPower(-0.3);
                          LBM.setPower(-0.3);
                          RBM.setPower(-0.3);
                          sleep(400);

                          LFM.setPower(0.5);      //turn
                          RFM.setPower(-0.5);
                          LBM.setPower(0.5);
                          RBM.setPower(-0.5);
                          sleep(100);
                          LFM.setPower(0.5); // get out of lander range
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(0.5);
                          sleep(150);
                          LFM.setPower(-0.5); // Back-ity Up-ity a bity
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(-0.5);
                          sleep(500);

                          LFM.setPower(1);       //knock over gold and drive into depot
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(750);
                          LFM.setPower(1);       //forwards
                          RFM.setPower(1);
                          LBM.setPower(1);
                          RBM.setPower(1);
                          sleep(100);
                          LFM.setPower(1);       //knock over gold and drive into depot
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(2250);
                          LFM.setPower(-.5);//left turn
                          LBM.setPower(-.5);
                          RFM.setPower(.5);
                          RBM.setPower(.5);
                          sleep(250);

                          LFM.setPower(1);//right
                          LBM.setPower(1);
                          RFM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(500);

                          Marker.setPosition(1);

                          LFM.setPower(-1);//left turn
                          LBM.setPower(-1);
                          RFM.setPower(1);
                          RBM.setPower(1);
                          sleep(450);
                          LFM.setPower(0.5);
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(0.5);
                          sleep(150);
                          LFM.setPower(1); //fowards
                          RFM.setPower(1);
                          LBM.setPower(1);
                          RBM.setPower(1);
                          sleep(4000);
                          LFM.setPower(0); //stop
                          RFM.setPower(0);
                          LBM.setPower(0);
                          RBM.setPower(0);
                          sleep(10000);
                      }
                      if (spot == 3){
                          telemetry.addLine("going to the right");
                          HM.setPower(1);   //land
                          LockServo.setPosition(0);
                          sleep(1000);
                          HM.setPower(-.3);
                          sleep(3000);
                          HM.setPower(0);

                          LFM.setPower(-0.3);   //back up
                          RFM.setPower(-0.3);
                          LBM.setPower(-0.3);
                          RBM.setPower(-0.3);
                          sleep(400);

                          LFM.setPower(0.5);      //turn
                          RFM.setPower(-0.5);
                          LBM.setPower(0.5);
                          RBM.setPower(-0.5);
                          sleep(100);
                          LFM.setPower(0.5); // get out of lander range
                          RFM.setPower(-0.5);
                          LBM.setPower(-0.5);
                          RBM.setPower(0.5);
                          sleep(150);
                          LFM.setPower(-.5); //back up
                          RFM.setPower(-.5);
                          LBM.setPower(-.5);
                          RBM.setPower(-.5);
                          sleep(2000);
                          LFM.setPower(1);       //knock over gold and drive into depot
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(1000);
                          LFM.setPower(-1);       //back up
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(250);
                          LFM.setPower(1);       //knock over gold and drive into depot
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(2000);
                          //some kind of turn
                          LFM.setPower(.5);
                          LBM.setPower(.5);
                          RFM.setPower(-.5);
                          RBM.setPower(-.5);
                          sleep(500);
                          LFM.setPower(1);
                          LBM.setPower(1);
                          RFM.setPower(1);
                          RBM.setPower(1);
                          sleep(500);
                          Marker.setPosition(1);
                          sleep(500);
                          LFM.setPower(-1);
                          LBM.setPower(-1);
                          RFM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(500);
                          LFM.setPower(-1); //back up
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(1500);
                          LFM.setPower(1);
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(1);
                          sleep(250);
                          LFM.setPower(-1); //back up
                          RFM.setPower(-1);
                          LBM.setPower(-1);
                          RBM.setPower(-1);
                          sleep(3000);
                          LFM.setPower(0); //stop
                          RFM.setPower(0);
                          LBM.setPower(0);
                          RBM.setPower(0);
                          sleep(10000);

                      }
                      telemetry.update();
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
    }

    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
            "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }
}
