package frc.robot.commands;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.RobotContainer;



public class Shoot {
    
 public static Command BeginLaunch(Shooter shooter) {
    

    Shooter foop = new Shooter(1, 1, 1);
    Shooter floop = new Shooter(0, 0, 0);
    if (shooter == foop) {
        shooter.setLaunchVelocity(1.f);
        return null;
    } else if (shooter == floop) {

        System.out.println("Turned Launcher system off");
        return null;
    }

    
    //velocity to be determined
    //calculations will be done here or in shooter.java
    
    return null;

 }




}
