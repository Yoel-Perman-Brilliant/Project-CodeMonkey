package frc.robot.utils;

import com.spikes2212.command.drivetrains.commands.DriveArcade;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TurnInAngle;
import frc.robot.subsystems.Drivetrain;

/**
 * The backend of student written programs.<br>
 * This class adds commands to a {@link SequentialCommandGroup} according to the user's demand.
 */
public abstract class ProgramBase extends SequentialCommandGroup {

    private final Drivetrain drivetrain = Drivetrain.getInstance();

    protected void moveForwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, Drivetrain.DRIVE_SPEED, 0).withTimeout(seconds));
    }

    protected void moveBackwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, -Drivetrain.DRIVE_SPEED, 0).withTimeout(seconds));
    }

    protected void turnLeftInDegrees(double angle) {
        addCommands(new TurnInAngle(drivetrain, -angle, true));
    }

    protected void turnRightInDegrees(double angle) {
        addCommands(new TurnInAngle(drivetrain, angle, false));
    }

    protected void turnLeftInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, 0, Drivetrain.DEFAULT_ROTATE_SPEED).withTimeout(seconds));
    }

    protected void turnRightInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, 0, -Drivetrain.DEFAULT_ROTATE_SPEED).withTimeout(seconds));
    }

    public abstract void writeProgram();
}
