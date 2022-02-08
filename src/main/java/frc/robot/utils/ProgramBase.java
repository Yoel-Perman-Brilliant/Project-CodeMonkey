package frc.robot.utils;

import com.spikes2212.command.drivetrains.commands.DriveArcade;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Program;
import frc.robot.commands.TurnInAngle;
import frc.robot.subsystems.Drivetrain;

/**
 * The backhand of {@link Program}.<br>
 * This class adds commands to a {@link SequentialCommandGroup} according to the user's demand.
 */
public class ProgramBase extends SequentialCommandGroup {

    private final Drivetrain drivetrain = Drivetrain.getInstance();

    protected void moveForwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, Drivetrain.DRIVE_SPEED, 0).withTimeout(seconds));
    }

    protected void moveBackwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, -Drivetrain.DRIVE_SPEED, 0).withTimeout(seconds));
    }

    protected void turnLeftInAngle(double angle) {
        addCommands(new TurnInAngle(drivetrain, angle));
    }

    protected void turnRightInAngle(double angle) {
        addCommands(new TurnInAngle(drivetrain, -angle));
    }

    protected void turnLeftInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, 0, Drivetrain.ROTATE_SPEED).withTimeout(seconds));
    }

    protected void turnRightInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, 0, -Drivetrain.ROTATE_SPEED).withTimeout(seconds));
    }
}
