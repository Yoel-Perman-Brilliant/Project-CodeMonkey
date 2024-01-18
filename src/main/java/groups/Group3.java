package groups;

import frc.robot.utils.ProgramBase;

/**
 * <H2>Here you should write your program</H2>you can use the methods: <br>
 * <p>{@link  #moveForwardInSeconds(double)} <br>
 * {@link  #moveBackwardInSeconds(double)} <br>
 * {@link  #turnLeftInDegrees(double)} <br>
 * {@link  #turnRightInDegrees(double)} <br></p>
 * <p>
 * In the basic program below, the robot will <b>drive forward</b> for 2 seconds, and then <b>turn left</b> by 90 degrees<br>
 * <p>
 * To create your own program, just delete those 2 lines, and start coding.
 * Have fun!
 */
public class Group3 extends ProgramBase {

    @Override
    public void writeProgram() {

        moveForwardInSeconds(0.32);
        moveForwardInSeconds(0.6);
        turnLeftInSeconds(0.7);
        turnRightInSeconds(0.3);
        moveForwardInSeconds(4.5);
        turnRightInSeconds(4.6);
        moveForwardInSeconds(0.3);
    }
}