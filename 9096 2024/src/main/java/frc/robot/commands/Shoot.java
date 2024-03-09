package frc.robot.commands;
import frc.robot.subsystems.Shooter;
//import edu.wpi.first.units.Time;
//import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Subsystem;
//import frc.robot.RobotContainer;



public class Shoot {
    
 public static Command BeginLaunch(Shooter shooter) {
    
    //Things needed for the code to work:
    //CANId's
    //Exact velocities
    //Decision on whether shooting logic (calculating time, velocity, etc) is going to be handled in the shooter class
    Shooter foop = new Shooter(11, 10, 9, 12,13);
    
    if (shooter == foop) {
        shooter.setLaunchVelocity(1.f);
        return null;
    } else if (shooter != foop) {

        System.out.println("Turned Launcher system off");
        return null;
    }

    
    //velocity to be determined
    //calculations will be done here or in shooter.java
    
    return null;

 }


 public static Command Reload(Shooter shooter) {

        
        
        shooter.setBeltVelocity(1);
        shooter.feederVelocity(1);
        return null;
 }
 }
