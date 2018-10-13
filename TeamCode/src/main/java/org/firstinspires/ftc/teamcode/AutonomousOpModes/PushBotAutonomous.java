package org.firstinspires.ftc.teamcode.AutonomousOpModes;

/* Imports all the neccessary robotcore files and configured hardware in HardwareRobot. */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@Autonomous(name="PushBotAutonomous")
//@Disabled
public class PushBotAutonomous extends LinearOpMode {

    HardwareRobot robot = new HardwareRobot();
    private ElapsedTime     runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 288 ;    // eg: TETRIX Motor Encoder
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV ) / (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;

    @Override
    public void runOpMode(){

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

        encoderDrive(DRIVE_SPEED, 12,12, 5);

    }

    public void encoderDrive(double speed,
                             double leftinches, double rightinches,
                             double timeoutseconds) {


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

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutseconds) &&
                    (robot.frontleft.isBusy() && robot.frontright.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", frontleftTarget,  frontrightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        robot.frontleft.getCurrentPosition(),
                        robot.frontright.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.frontleft.setPower(0);
            robot.backleft.setPower(0);
            robot.frontright.setPower(0);
            robot.backright.setPower(0);
        }
    }
}

