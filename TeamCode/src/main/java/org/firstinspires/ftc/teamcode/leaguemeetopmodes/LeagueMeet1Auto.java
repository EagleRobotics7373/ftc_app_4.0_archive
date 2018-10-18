package org.firstinspires.ftc.teamcode.leaguemeetopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;


@Autonomous(name="League Meet 1 Auto", group="League Meets")
//@Disabled
public class LeagueMeet1Auto extends LinearOpMode {

    private LeagueMeet1Robot robot;
    private ElapsedTime elapsedTime;

    private Color teamColor = Color.NULL;
    private StartingPosition startingPosition = StartingPosition.NULL;

    @Override
    public void runOpMode() throws InterruptedException {
        // initialize robot and telemetry time counter
        robot = new LeagueMeet1Robot(hardwareMap);
        elapsedTime = new ElapsedTime();

        // allow gamepad input to choose which position the robot starts in
        while(!isStarted()) {}//changeRobotStartPosition();

        // after op mode starts:
        robot.holonomic.run(0.2,0);
        sleep(1000);
        robot.holonomic.run(0,0.5,0);
        sleep(1000);

    }


    private void changeRobotStartPosition() {
         if (gamepad1.x) teamColor = Color.BLUE;
    else if (gamepad1.b) teamColor = Color.RED;

         if (gamepad1.dpad_left)  startingPosition = StartingPosition.LEFT;
    else if (gamepad1.dpad_right) startingPosition = StartingPosition.RIGHT;

        telemetry.addData("Alliance Color", teamColor);
        telemetry.addData("Starting Position", startingPosition);
    }

    enum Color {
        BLUE, RED, NULL
    }
    enum StartingPosition {
        LEFT, RIGHT, NULL
    }
}
