<div style="font-family: Calibri">
A program for showing our robot to kids. <br>
This project let them write a simple code to drive the robot around and surpass obstacles that stand in its way. <br>
The kids leans to code basic Java programs by calling the pre-written functions like `moveForwardInSeconds`,
`turnLeftInAngle`, and others. <br>
Based on <a href="https://github.com/Spikes-2212-Programming-Guild/Freshman-Presentation-2021">this</a> code.
<span style="color: #0015AB">
<h2>The Commands that the user can call are:</h2>
</span>
<li><code>moveForwardInSeconds(double seconds)</code></li>
<li><code>moveBackwardInSeconds(double seconds)</code></li>
<li><code>turnLeftInDegrees(double degrees)</code></li>
<li><code>turnRightInDegrees(double degrees)</code></li>
<li><code>turnLeftInSeconds(double seconds)</code></li>
<li><code>turnRightInSeconds(double seconds)</code></li>
</div>
<span style="color: #0015AB">
<h2>If you want to use it, you can!</h2>
</span>
<b>All you have to do is:</b><br><br>
<li>Change the ports in the <code>RobotMap.java</code>.</li>
<li>Change the correction of each side of the drivetrain in <code>subsystems/Drivetrain.java</code>.</li>
<li>Change the type of the gyro and motorController if necessary.</li>
<br>
<b>Now, all the user has to do is to write in </b> <code>Program.java</code> <b> the commands that they want the robot
to perform, and that's it!</b>
