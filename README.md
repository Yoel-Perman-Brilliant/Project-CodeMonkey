# Project-Athena
A program for showing our robot to kids. <br>
This project let them write a simple code to drive the robot around and surpass obstacles that stand in its way. <br>
The kids leans to code basic Java programs by calling the pre-written functions like `moveForwardInSeconds`, `turnLeftInAngle`, and many more. <br>
based on <a href="https://github.com/Spikes-2212-Programming-Guild/Freshman-Presentation-2021">this</a> code.

<h2>If you want to use it, you can!</h2>
<h3>All you got to do is:</h3>
<li>Change the ports in the <code>RobotMap.java</code></li>
<li>Change the correction of each side of the drivetrain in <code>subsystems/Drivetrain.java</code></li>
<li>Change the type of the gyro and motorController if necessary</li>

<h4>Now, all the user got to do is to write in <code>Program.java</code> the commands he wants the robot to do, and thats it!</h4>
<h2>The Commands that the user can call are:</h2>
<li>moveForwardInSeconds (double seconds)</li>
<li>moveBackwardInSeconds (double seconds)</li>
<li>turnLeftInDegrees (double degrees)</li>
<li>turnRightInDegrees (double degrees)</li>
<li>turnLeftInSeconds (double seconds)</li>
<li>turnRightInSeconds (double seconds)</li>
