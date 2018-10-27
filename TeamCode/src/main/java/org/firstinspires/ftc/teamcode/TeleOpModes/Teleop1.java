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

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            /*double x = gamepad1.left_stick_x;
            double y = gamepad1.left_stick_y;
            double z = gamepad1.right_stick_x;

            robot.frontleft.setPower(y - x- z);
            robot.backleft.setPower(y + x - z);
            robot.frontright.setPower(-y - x - z);
            robot.backright.setPower(-y + x - z);*/

            robot.leftlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightlift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            robot.leftlift.setPower(gamepad2.right_stick_y);
            robot.rightlift.setPower(gamepad2.right_stick_y);

            telemetry.addData("gamepad2 value", gamepad2.right_stick_y);
            telemetry.update();
        }
        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.update();
    }
}