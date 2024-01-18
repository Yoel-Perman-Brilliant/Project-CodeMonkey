package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {

    private Joystick left = new Joystick(0);
    private Joystick right = new Joystick(1);

    public double getLeftX() {
        return 0.75*left.getX();
    }

    public double getRightY() {
        return -0.75*right.getY();
    }
}
