package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.Servo;

public class HardwareRobot
{
    /* Public OpMode members. */
    public DcMotor  frontleft = null;
    public DcMotor  backleft = null;
    public DcMotor  frontright = null;
    public DcMotor  backright = null;
    public Servo servoleft = null;
    public Servo servoright = null;
    public NormalizedColorSensor CSright = null;
    public NormalizedColorSensor CScenter = null;
    public NormalizedColorSensor CSleft = null;

    /* local OpMode members. */
    HardwareMap hwMap =  null;

    /* Constructor */
    public HardwareRobot(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        frontleft = hwMap.get(DcMotor.class, "frontleft");
        backleft = hwMap.get(DcMotor.class, "backleft");
        frontright = hwMap.get(DcMotor.class, "frontright");
        backright = hwMap.get(DcMotor.class, "backright");
        servoleft = hwMap.get(Servo.class, "servoleft");
        servoright = hwMap.get(Servo.class, "servoright");
        CSright = hwMap.get(NormalizedColorSensor.class, "CSright");
        CScenter = hwMap.get(NormalizedColorSensor.class, "CScenter");
        CSleft = hwMap.get(NormalizedColorSensor.class, "CSleft");

        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        frontright.setDirection(DcMotor.Direction.FORWARD);
        backright.setDirection(DcMotor.Direction.FORWARD);

        // Set all motors to zero power
        frontleft.setPower(0);
        backleft.setPower(0);
        frontright.setPower(0);
        backright.setPower(0);

        frontleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
 }

