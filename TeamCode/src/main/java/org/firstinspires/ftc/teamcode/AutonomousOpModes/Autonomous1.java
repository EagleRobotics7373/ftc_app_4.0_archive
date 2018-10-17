package org.firstinspires.ftc.teamcode.AutonomousOpModes;

/* Imports all the neccessary robotcore files and configured hardware in HardwareRobot. */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@Autonomous(name="Autonomous1")
//@Disabled
public class Autonomous1 extends LinearOpMode {

    HardwareRobot robot = new HardwareRobot();
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 288;    // eg: TETRIX Motor Encoder
    static final double WHEEL_DIAMETER_INCHES = 4.0;
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 0.6;

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

        encoderDrive(DRIVE_SPEED, 12, 12);

        robot.servoleft.setPosition(.5);
        robot.servoright.setPosition(.5);

        NormalizedRGBA color_left = robot.CSleft.getNormalizedColors();
        NormalizedRGBA color_center = robot.CScenter.getNormalizedColors();
        NormalizedRGBA color_right = robot.CSright.getNormalizedColors();

        if (color_left.red > color_center.red && color_left.red > color_right.red) {
            MotorPower(0,-1,0,1);
            sleep(100);
            encoderDrive(DRIVE_SPEED, 12, 12);
        }
        if (color_center.red > color_left.red && color_center.red > color_right.red) {
            MotorPower(1, 1, 1, 1);
            sleep(1000);

        }
        if (color_right.red > color_center.red && color_right.red > color_left.red) {
            MotorPower(0, 1, 0, -1);
            sleep(100);
            MotorPower(0,0,0,0);
            sleep(250);
            encoderDrive(DRIVE_SPEED, 12, 12);
        }

    }

    public void encoderDrive(double speed, double leftinches, double rightinches) {

        int frontleftTarget;
        int backleftTarget;
        int frontrightTarget;
        int backrightTarget;

        if (opModeIsActive()) ;
        {

            frontleftTarget = robot.frontleft.getCurrentPosition() + (int) (leftinches * COUNTS_PER_INCH);
            backleftTarget = robot.backleft.getCurrentPosition() + (int) (leftinches * COUNTS_PER_INCH);
            frontrightTarget = robot.frontright.getCurrentPosition() + (int) (rightinches * COUNTS_PER_INCH);
            backrightTarget = robot.backright.getCurrentPosition() + (int) (rightinches * COUNTS_PER_INCH);

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

            // Stop all motion;
            MotorPower(0, 0, 0, 0);

        }
    }
    private void MotorPower(double frontleftMP, double backleftMP, double frontrightMP, double backrightMP) {
        robot.frontleft.setPower(frontleftMP);
        robot.backleft.setPower(backleftMP);
        robot.frontright.setPower(frontrightMP);
        robot.backright.setPower(backrightMP);
    }
}
