package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@TeleOp(name="Teleop 1")
//@Disabled
public class Teleop1 extends LinearOpMode {

    // Declare OpMode members.
    HardwareRobot robot = new HardwareRobot();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        robot.leftlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightlift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.leftlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            /*robot.frontleft.setPower(gamepad1.left_stick_y);
            robot.backleft.setPower(-gamepad1.left_stick_y);
            robot.frontright.setPower(gamepad1.left_stick_y);
            robot.backright.setPower(-gamepad1.left_stick_y);

            robot.backleft.setPower(gamepad1.right_stick_x);
            robot.frontleft.setPower(gamepad1.right_stick_x);
            robot.backright.setPower(-gamepad1.right_stick_x);
            robot.frontright.setPower(-gamepad1.right_stick_x);*/

            int leftliftTarget;
            int rightliftTarget;

            leftliftTarget = robot.leftlift.getCurrentPosition() * 288;
            rightliftTarget = robot.rightlift.getCurrentPosition() * 288;

            robot.leftlift.setTargetPosition(leftliftTarget);
            robot.rightlift.setTargetPosition(rightliftTarget);

            robot.leftlift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightlift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            robot.leftlift.setPower(robot.DRIVE_SPEED);
            robot.rightlift.setPower(robot.DRIVE_SPEED);
        }
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}