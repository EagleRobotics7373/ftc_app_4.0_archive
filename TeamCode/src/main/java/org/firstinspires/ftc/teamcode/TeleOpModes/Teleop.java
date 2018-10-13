package org.firstinspires.ftc.teamcode.TeleOpModes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HardwareRobot;

@TeleOp(name="Teleop 1.0")
//@Disabled
public class Teleop extends LinearOpMode {

    // Declare OpMode members.
    HardwareRobot robot = new HardwareRobot();
    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            robot.frontleft.setPower(gamepad1.left_stick_y);
            robot.backleft.setPower(gamepad1.left_stick_y);
            robot.frontright.setPower(gamepad1.left_stick_y);
            robot.backright.setPower(gamepad1.left_stick_y);

            robot.frontleft.setPower(-gamepad1.right_stick_x);
            robot.backleft.setPower(-gamepad1.right_stick_x);
            robot.frontright.setPower(gamepad1.right_stick_x);
            robot.backright.setPower(gamepad1.right_stick_x);

                      // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.update();
        }
    }
}