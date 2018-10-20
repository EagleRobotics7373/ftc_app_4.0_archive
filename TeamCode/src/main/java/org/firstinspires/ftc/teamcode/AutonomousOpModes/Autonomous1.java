package org.firstinspires.ftc.teamcode.AutonomousOpModes;

/* Imports all the neccessary robotcore files and configured hardware in HardwareRobot. */


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@Autonomous(name="Autonomous1")
//@Disabled
public class Autonomous1 extends LinearOpMode {

    HardwareRobot robot = new HardwareRobot();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        //encoderDrive(robot.DRIVE_SPEED, 10, 10, 10, 10, 5);

        robot.leftlift.setPower(.85);
        robot.rightlift.setPower(1);
        sleep(13000);

        MotorPower(-1,-1,-1,-1);
        sleep(250);

        MotorPower(0,0,0,0);

        robot.frontleft.setPower(-1);
        robot.backleft.setPower(-1);
        robot.frontright.setPower(1);
        robot.backright.setPower(1);
        sleep(500);

        MotorPower(0,0,0,0);

        /*encoderDrive(robot.DRIVE_SPEED, 2, 2, 2, 2, 5);

        robot.frontleft.setPower(1);
        robot.backleft.setPower(1);
        robot.frontright.setPower(1);
        robot.backright.setPower(-1);
        sleep(1000);

        MotorPower(0,0,0,0);

        encoderDrive(robot.DRIVE_SPEED, 12, 12, 12,
                12, 5);

        //robot.servoright.setPosition(.4);
        //robot.servoleft.setPosition(.4);
        sleep(1000);*/

        /*NormalizedRGBA color_left = robot.CSleft.getNormalizedColors();
        NormalizedRGBA color_center = robot.CScenter.getNormalizedColors();
        NormalizedRGBA color_right = robot.CSright.getNormalizedColors();
        sleep(1000);

        if (color_left.red > color_center.red && color_left.red > color_right.red) {
            //Hit left?
            MotorPower(0, -1, 0, 1);
            sleep(1000);
            encoderDrive(robot.DRIVE_SPEED, -6, -6, -6,
                    -6, 5);

            telemetry.addLine("Hit left");
            telemetry.update();
            sleep(5000);
            }

            else if (color_center.red > color_left.red && color_center.red > color_right.red) {
            //Hit middle?
            MotorPower(-1, -1, -1, -1);
            sleep(100);

            telemetry.addLine("Hit center");
            telemetry.update();
            sleep(5000);
            }

            else if (color_right.red > color_center.red && color_right.red > color_left.red) {
            //Hit right?
            MotorPower(0, 1, 0, -1);
            sleep(1000);
            encoderDrive(robot.DRIVE_SPEED, -6, -6, -6,
                    -6,  5);

            telemetry.addLine("Hit right");
            telemetry.update();
            sleep(5000);
            }*/
    }
        private void MotorPower ( double frontright, double backright, double frontleft,
        double backleft){
            robot.frontright.setPower(frontright);
            robot.backright.setPower(backright);
            robot.frontleft.setPower(frontleft);
            robot.backleft.setPower(backleft);
        }

        public void encoderDrive ( double speed, double frontleftinches, double frontrightinches,
        double backleftinches, double backrightinches, double timeoutS){

            int frontleftTarget;
            int backleftTarget;
            int frontrightTarget;
            int backrightTarget;

            if (opModeIsActive()) {

                frontleftTarget = robot.frontleft.getCurrentPosition() + (int) (frontleftinches * robot.COUNTS_PER_INCH);
                backleftTarget = robot.backleft.getCurrentPosition() + (int) (backleftinches * robot.COUNTS_PER_INCH);
                frontrightTarget = robot.frontright.getCurrentPosition() + (int) (frontrightinches * robot.COUNTS_PER_INCH);
                backrightTarget = robot.backright.getCurrentPosition() + (int) (backrightinches * robot.COUNTS_PER_INCH);

                robot.frontleft.setTargetPosition(frontleftTarget);
                robot.backleft.setTargetPosition(backleftTarget);
                robot.frontright.setTargetPosition(frontrightTarget);
                robot.backright.setTargetPosition(backrightTarget);

                robot.frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                runtime.reset();
                robot.frontleft.setPower(speed);
                robot.backleft.setPower(speed);
                robot.frontright.setPower(speed);
                robot.backright.setPower(speed);

                while (opModeIsActive() &&
                        (runtime.seconds() < timeoutS) &&
                        (robot.frontleft.isBusy() && robot.frontright.isBusy())) {
                }

                // Stop all motion
                robot.frontleft.setPower(0);
                robot.backleft.setPower(0);
                robot.frontright.setPower(0);
                robot.backright.setPower(0);

                robot.frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }
        }
    }