package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.MotorControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.RobotMap;

import java.util.function.Supplier;

public class Drivetrain extends TankDrivetrain {

    public static final double RIGHT_CORRECTION = 1; //todo change according to *your* drivetrain deviation
    public static final double LEFT_CORRECTION = 0.9; //todo change according to *your* drivetrain deviation

    private final AHRS gyro = new AHRS(SerialPort.Port.kMXP); //todo change to *your* gyro type

    public static final double DRIVE_SPEED = 0.25;
    public static final double DEFAULT_ROTATE_SPEED = 0.25;
    public static final double DEFAULT_ROTATE_TOLERANCE = 6;

    private static Drivetrain instance;

    private static final RootNamespace rootNamespace = new RootNamespace("drivetrain");

    /**
     * One side of the robot is faster than the other. To solve this we slow down the fast side.
     */
    private static final Supplier<Double> rightCorrection = rootNamespace.addConstantDouble("right correction", RIGHT_CORRECTION);
    private static final Supplier<Double> leftCorrection = rootNamespace.addConstantDouble("left correction", LEFT_CORRECTION);
    public static final Supplier<Double> rotateSpeed = rootNamespace.addConstantDouble("rotate speed", DEFAULT_ROTATE_SPEED);
    public static final Supplier<Double> rotateTolerance = rootNamespace.addConstantDouble("rotate tolerance", DEFAULT_ROTATE_TOLERANCE);

    public static Drivetrain getInstance() {
        if (instance == null) {
            instance = new Drivetrain(new MotorControllerGroup(
                    new WPI_VictorSPX(RobotMap.CAN.DRIVETRAIN_LEFT_VICTOR_1),
                    new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_LEFT_VICTOR_2)
            ),
                    new MotorControllerGroup(
                            new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_RIGHT_TALON_1),
                            new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_RIGHT_TALON_2)
                    )
            );
        }
        return instance;
    }

    private Drivetrain(MotorControllerGroup leftMotors, MotorControllerGroup rightMotors) {
        super(leftMotors, rightMotors);
        namespace.putNumber("gyro angle", this::getAngle);
    }

    public void resetGyro() {
        gyro.reset();
    }

    public double getAngle() {
        double angle = gyro.getAngle() % 360;
        if (angle > 180) angle -= 360;
        if (angle < -180) angle += 360;
        return angle;
    }
}
