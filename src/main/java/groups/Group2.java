package groups;

import frc.robot.utils.ProgramBase;

/**
 * <H2>Here you should write your program</H2>you can use the methods: <br>4.
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
public class Group2 extends ProgramBase {

    @Override
    public void writeProgram() {
        moveForwardInSeconds(1.8); //todo remove this line and start coding
        turnRightInDegrees(90); //todo remove this line and start coding
        moveForwardInSeconds(1.6);
        turnLeftInDegrees(90);
        moveForwardInSeconds(1);

    }
}
