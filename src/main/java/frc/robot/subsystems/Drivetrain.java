package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.spikes2212.command.drivetrains.TankDrivetrain;
import com.spikes2212.dashboard.RootNamespace;
import com.spikes2212.util.BustedMotorControllerGroup;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.RobotMap;

import java.util.function.Supplier;

/**
 * <h2>The drivetrain we want to move.</h2>
 * <p>
 * While the {@link  TankDrivetrain} does most of the work, this class adds the gyro and uses the
 * {@link BustedMotorControllerGroup} to fix deviation
 * </p><br>
 * <b>All you need to change here is:
 * <li>The <b>type</b> of the gyro </li>
 * <li>The <b>correction</b> for each side of the drivetrain. (if you dont have deviation, put <b>1</b>)</li>
 * <li>The <b>type</b> of the SpeedControllers, in our case its {@link WPI_TalonSRX}</li>
 */
public class Drivetrain extends TankDrivetrain {

    public static final double RIGHT_CORRECTION = 1; //todo change according to *your* drivetrain deviation
    public static final double LEFT_CORRECTION = 0.9; //todo change according to *your* drivetrain deviation

    private final ADXRS450_Gyro gyro = new ADXRS450_Gyro(); //todo change to *your* gyro type

    public static final double DRIVE_SPEED = 0.5;
    public static final double DEFAULT_ROTATE_SPEED = 0.45;
    public static final double DEFAULT_ROTATE_TOLERANCE = 3;

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
            instance = new Drivetrain(new BustedMotorControllerGroup(
                    leftCorrection,
                    new WPI_TalonSRX(RobotMap.CAN.DRIVETRAIN_LEFT_TALON_1),
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

    private Drivetrain(MotorControllerGroup leftMotors, MotorControllerGroup rightMotors) {
        super(leftMotors, rightMotors);
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
