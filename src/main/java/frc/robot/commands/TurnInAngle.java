package frc.robot.commands;

import com.spikes2212.command.drivetrains.commands.DriveArcade;
import frc.robot.subsystems.Drivetrain;

public class TurnInAngle extends DriveArcade {
    private final Drivetrain drivetrain;
    private final double angle;
    private double wantedAngle;

    public TurnInAngle(Drivetrain drivetrain, double angle) {
        super(drivetrain, () -> 0.0, () -> Drivetrain.ROTATE_SPEED);
        this.drivetrain = drivetrain;
        this.angle = angle;
    }

    @Override
    public void initialize() {
        drivetrain.resetGyro();
        double currentAngle = drivetrain.getAngle();
        wantedAngle = currentAngle + angle;
    }

    @Override
    public boolean isFinished() {
        return Math.abs(wantedAngle - drivetrain.getAngle()) < Drivetrain.ROTATE_TOLERANCE;
    }
}