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

    static final double COUNTS_PER_MOTOR_REV = 288;
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

        encoderDrive(DRIVE_SPEED, -14, -14, -14, -14,5);
        NormalizedRGBA color_left = robot.CSleft.getNormalizedColors();
        NormalizedRGBA color_center = robot.CScenter.getNormalizedColors();
        NormalizedRGBA color_right = robot.CSright.getNormalizedColors();

        sleep(2000);

        if (color_left.red > color_center.red && color_left.red > color_right.red) {
            //Hit left?
            MotorPower(0, -1, 0, 1);
            sleep(1000);
            encoderDrive(DRIVE_SPEED, -6, -6, -6, -6, 5);

        } else if (color_center.red > color_left.red && color_center.red > color_right.red) {
            //Hit middle?
            MotorPower(-1, -1, -1, -1);
            sleep(100);

        } else if (color_right.red > color_center.red && color_right.red > color_left.red) {
            //Hit right?
            MotorPower(0, 1, 0, -1);
            sleep(1000);
            encoderDrive(DRIVE_SPEED, -6, -6,-6,-6, 5);
        }

    }

    public void encoderDrive(double speed, double frontleftinches, double frontrightinches, double backleftinches, double backrightinches, double timeoutS) {

        int frontleftTarget;
        int backleftTarget;
        int frontrightTarget;
        int backrightTarget;

        if (opModeIsActive()) {

            frontleftTarget = robot.frontleft.getCurrentPosition() + (int) (frontleftinches * COUNTS_PER_INCH);
            backleftTarget = robot.backleft.getCurrentPosition() + (int) (backleftinches * COUNTS_PER_INCH);
            frontrightTarget = robot.frontright.getCurrentPosition() + (int) (frontrightinches * COUNTS_PER_INCH);
            backrightTarget = robot.backright.getCurrentPosition() + (int) (backrightinches * COUNTS_PER_INCH);

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
            MotorPower(0, 0, 0, 0);

            robot.frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    private void MotorPower(double frontleftMP, double backleftMP, double frontrightMP, double backrightMP) {
        robot.frontleft.setPower(frontleftMP);
        robot.backleft.setPower(backleftMP);
        robot.frontright.setPower(frontrightMP);
        robot.backright.setPower(backrightMP);
    }
}
