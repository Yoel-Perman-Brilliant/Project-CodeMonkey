package frc.robot.commands;

import com.spikes2212.command.drivetrains.commands.DriveArcade;
import com.spikes2212.command.drivetrains.commands.DriveArcadeWithPID;
import com.spikes2212.command.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class ProgramBase extends SequentialCommandGroup {

    private final Drivetrain drivetrain = Drivetrain.getInstance();

    protected void moveForwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, Drivetrain.SPEED, 0).withTimeout(seconds));
    }

    protected void moveBackwardInSeconds(double seconds) {
        addCommands(new DriveArcade(drivetrain, -Drivetrain.SPEED, 0).withTimeout(seconds));
    }

    protected void moveForwardInCM(double cm) {
        addCommands(new DriveTankWithPID(drivetrain, drivetrain.getDrivetrainPIDSettings(), drivetrain.getDrivetrainPIDSettings(),
        cm/100, cm/100, drivetrain::getLeftDistance, drivetrain::getRightDistance));
    }

    protected void moveBackwardInCM(double cm) {
        addCommands(new DriveTankWithPID(drivetrain, drivetrain.getDrivetrainPIDSettings(), drivetrain.getDrivetrainPIDSettings(),
        -cm/100, -cm/100, drivetrain::getLeftDistance, drivetrain::getRightDistance));
    }

    protected void turnLeftInAngle(double angle) {
        addCommands(
            new DriveArcadeWithPID(drivetrain, drivetrain::getAngle, angle, 0.0, drivetrain.getGyroPIDSettings())
        );
    }

    protected void turnRightInAngle(double angle) {
        addCommands(
            new DriveArcadeWithPID(drivetrain, drivetrain::getAngle, -angle, 0.0, drivetrain.getGyroPIDSettings())
        );
    }

    protected void turnLeftInSeconds(double seconds) {
        addCommands(
            new DriveArcade(drivetrain, 0, Drivetrain.SPEED).withTimeout(seconds)
        );
    }

    protected void turnRightInSeconds(double seconds) {
        addCommands(
            new DriveArcade(drivetrain, 0, -Drivetrain.SPEED).withTimeout(seconds)
        );
    }
}
