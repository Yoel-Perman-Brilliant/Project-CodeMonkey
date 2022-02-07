package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.control.PIDSettings;
import com.spikes2212.dashboard.Namespace;
import com.spikes2212.dashboard.RootNamespace;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.RobotMap;
import frc.robot.utils.BustedMotorControllerGroup;

import java.util.function.Supplier;

public class Drivetrain extends TankDrivetrain {

    /**
     * The wheel moves 20.32 * PI (it's perimeter) each 360 ticks.
     * In meters.
     */
    private static final double DISTANCE_PER_PULSE = 20.32 * Math.PI / 360.0 / 100;

    public static final double SPEED = 0.5;

    private static Drivetrain instance;

    private static final RootNamespace rootNamespace = new RootNamespace("drivetrain");
    private static final Namespace encoderNamespace = rootNamespace.addChild("encoders");
    private static final Namespace GyroPIDNamespace = rootNamespace.addChild("Gyro PID");
    private static final Namespace drivetrainPIDNamespace = rootNamespace.addChild("drivetrain PID");

    /**
     * One side of the robot is faster than the other. To solve this we slow down one of the sides.
     */
    private static final Supplier<Double> rightCorrection = rootNamespace.addConstantDouble("right correction", 1);
    private static final Supplier<Double> leftCorrection = rootNamespace.addConstantDouble("left correction", 1);

    private final Encoder leftEncoder, rightEncoder;
    private final ADXRS450_Gyro gyro;

    private final Supplier<Double> gyroKp = GyroPIDNamespace.addConstantDouble("gyro kP", 0);
    private final Supplier<Double> gyroKi = GyroPIDNamespace.addConstantDouble("gyro kI", 0);
    private final Supplier<Double> gyroKd = GyroPIDNamespace.addConstantDouble("gyro kD", 0);
    private final Supplier<Double> gyroTolerance = GyroPIDNamespace.addConstantDouble("gyro tolerance", 5);
    private final Supplier<Double> gyroWaitTime = GyroPIDNamespace.addConstantDouble("gyro wait time", 0);
    private final PIDSettings gyroPIDSettings;

    private final Supplier<Double> drivetrainKp = drivetrainPIDNamespace.addConstantDouble("drivetrain kP", 0);
    private final Supplier<Double> drivetrainKi = drivetrainPIDNamespace.addConstantDouble("drivetrain kI", 0);
    private final Supplier<Double> drivetrainKd = drivetrainPIDNamespace.addConstantDouble("drivetrain kD", 0);
    private final Supplier<Double> drivetrainTolerance = drivetrainPIDNamespace.addConstantDouble("drivetrain tolerance", 0);
    private final Supplier<Double> drivetrainWaitTime = drivetrainPIDNamespace.addConstantDouble("drivetrain wait time", 0);
    private final PIDSettings drivetrainPIDSettings;

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain(new BustedMotorControllerGroup(
                    leftCorrection,
                    new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_RIGHT_TALON_1),
                    new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_LEFT_TALON_2)
            ),
                    new BustedMotorControllerGroup(
                            rightCorrection,
                            new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_RIGHT_TALON_1),
                            new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_RIGHT_TALON_2)
                    )
            );
        }
        return instance;
    }

    private Drivetrain(MotorControllerGroup leftMotors, BustedMotorControllerGroup rightMotors) {
        super(leftMotors, rightMotors);
        this.leftEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_LEFT_ENCODER_POS, RobotMap.DIO.DRIVETRAIN_LEFT_ENCODER_NEG);
        this.rightEncoder = new Encoder(RobotMap.DIO.DRIVETRAIN_RIGHT_ENCODER_POS, RobotMap.DIO.DRIVETRAIN_RIGHT_ENCODER_NEG);
        this.gyro = new ADXRS450_Gyro();
        this.leftEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        this.rightEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
        this.gyroPIDSettings = new PIDSettings(this.gyroKp, this.gyroKi, this.gyroKd, this.gyroTolerance,
                this.gyroWaitTime);
        this.drivetrainPIDSettings = new PIDSettings(this.drivetrainKp, this.drivetrainKi, this.drivetrainKd,
                this.drivetrainTolerance, this.drivetrainWaitTime);
    }

    public void resetEncoders() {
        leftEncoder.reset();
        rightEncoder.reset();
    }

    @Override
    public void periodic() {
        rootNamespace.update();
    }

    public double getAngle() {
        double angle = gyro.getAngle() % 360;
        if (angle > 180) angle -= 360;
        if (angle < -180) angle += 360;
        return angle;
    }

    public double getRightDistance() {
        return rightEncoder.getDistance();
    }

    public double getLeftDistance() {
        return leftEncoder.getDistance();
    }

    /**
     * Initializes namespaces and adds sensor data to dashboard.
     */
    public void configureDashboard() {
        encoderNamespace.putNumber("left ticks", leftEncoder::get);
        encoderNamespace.putNumber("right ticks", rightEncoder::get);
        encoderNamespace.putNumber("left distance", leftEncoder::getDistance);
        encoderNamespace.putNumber("right distance", rightEncoder::getDistance);
        encoderNamespace.putData("reset encoders", new InstantCommand(this::resetEncoders) {
            @Override
            public boolean runsWhenDisabled() {
                return true;
            }
        });
    }

    public PIDSettings getGyroPIDSettings() {
        return gyroPIDSettings;
    }

    public PIDSettings getDrivetrainPIDSettings() {
        return drivetrainPIDSettings;
    }
}
