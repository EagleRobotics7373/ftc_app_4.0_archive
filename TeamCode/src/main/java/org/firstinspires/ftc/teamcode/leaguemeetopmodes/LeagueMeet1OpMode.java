package org.firstinspires.ftc.teamcode.leaguemeetopmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

public abstract class LeagueMeet1OpMode extends OpMode {
    protected ElapsedTime runtime = new ElapsedTime();

    protected DcMotor frontLeftMotor;
    protected DcMotor backLeftMotor;
    protected DcMotor frontRightMotor;
    protected DcMotor backRightMotor;

    protected DcMotor frontTapeMeasure;
    protected DcMotor backTapeMeasure;

    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        frontLeftMotor = hardwareMap.dcMotor.get("frontLeftMotor");
        backLeftMotor = hardwareMap.dcMotor.get("backLeftMotor");
        frontRightMotor = hardwareMap.dcMotor.get("frontRightMotor");
        backRightMotor = hardwareMap.dcMotor.get("backRightMotor");

        frontTapeMeasure = hardwareMap.dcMotor.get("frontTapeMeasure");
        backTapeMeasure = hardwareMap.dcMotor.get("backTapeMeasure");
    }
}
