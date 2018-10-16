package org.firstinspires.ftc.teamcode.leaguemeetopmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;



@Autonomous(name="League Meet 1 Auto", group="League Meets")
//@Disabled
public class LeagueMeet1Auto extends LinearOpMode {

    LeagueMeet1Robot robot;

    private Color teamColor = Color.NULL;
    private StartingPosition startingPosition = StartingPosition.NULL;

    @Override
    public void runOpMode() throws InterruptedException {
        while(!isStarted()) {
            robot = new LeagueMeet1Robot(hardwareMap);
//            if (gamepad1.x) teamColor = Color.BLUE;
//            if (gamepad1.b) teamColor = Color.RED;
//
//            if (gamepad1.dpad_left)  startingPosition = StartingPosition.LEFT;
//            if (gamepad1.dpad_right) startingPosition = StartingPosition.RIGHT;
//
//            telemetry.addData("Alliance Color", teamColor);
//            telemetry.addData("Starting Position", startingPosition);
        }


    }

    enum Color {
        BLUE, RED, NULL
    }
    enum StartingPosition {
        LEFT, RIGHT, NULL
    }
}
