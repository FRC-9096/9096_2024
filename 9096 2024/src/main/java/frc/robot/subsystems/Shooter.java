package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.revrobotics.RelativeEncoder;

public class Shooter {
  private final CANSparkMax m_upperShooter;
  private final RelativeEncoder m_upperShooterEncoder;
  private final CANSparkMax m_lowerShooter;
  private final RelativeEncoder m_lowerShooterEncoder;
  private final CANSparkMax m_belt;
  private final RelativeEncoder m_beltEncoder;

  public Shooter(int upperCANId, int lowerCANId, int beltCANId) {
    m_upperShooter = new CANSparkMax(upperCANId, MotorType.kBrushless);
    m_lowerShooter = new CANSparkMax(lowerCANId, MotorType.kBrushless);
    m_belt = new CANSparkMax(beltCANId, MotorType.kBrushless);

    m_upperShooterEncoder = m_upperShooter.getEncoder();
    m_lowerShooterEncoder = m_upperShooter.getEncoder();
    m_beltEncoder = m_belt.getEncoder();

    /* configure motor as needed. For example:
    m_upperShooterEncoder.setPositionConversionFactor(some factor);
    m_upperShooterEncoder.setVelocityConversionFactor(some factor);
    m_upperShooterEncoder.setInverted(bool);
    m_upperShooter.setIdleMode(idle mode);
    m_upperShooter.setSmartCurrentLimit(limit);
    */
    m_upperShooter.burnFlash();
    m_lowerShooter.burnFlash();
    m_belt.burnFlash();

    //ps4 bindings code 
    //I just randomly chose port 1 because I didn't know what port we we're gonna use,
    //this will probably be changed in the future
    CommandXboxController MainController = new CommandXboxController(1);
     Trigger IntakeButton = MainController.a();
    
    //I chose a random button we will revise later
   //xbox controller mapping to DEVICE 11 for now 
      MainController.a().onTrue();
      
    //Hi henry i didn't finish and I learned that I hate command based programming + Java 
    //Enjoy the mess 
    //-Nikolas Graupe

  }

 

  public float getLaunchVelocity() {
    return 0.0f;
  }

  public void setLaunchVelocity(float velocity) {
    //
  }

  public float getBeltVelocity() {
    return 0.0f;
  }

  public void setBeltVelocity(float velocity) {
    //
    
  }

  public void resetEncoders() {
    //
  }
}