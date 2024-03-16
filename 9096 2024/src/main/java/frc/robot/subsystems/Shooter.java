package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
//import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
//import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import com.revrobotics.RelativeEncoder;

public class Shooter extends SubsystemBase {
  private final CANSparkMax m_upperShooter;
  private final RelativeEncoder m_upperShooterEncoder;
  private final CANSparkMax m_lowerShooter;
  private final RelativeEncoder m_lowerShooterEncoder;
  private final CANSparkMax m_belt;
  private final RelativeEncoder m_beltEncoder;
  private final CANSparkMax m_feeder1;
  private final CANSparkMax m_feeder2;

  public Shooter(int upperCANId, int lowerCANId, int beltCANId, int feeder1, int feeder2) {
    m_upperShooter = new CANSparkMax(upperCANId, MotorType.kBrushless);
    m_lowerShooter = new CANSparkMax(lowerCANId, MotorType.kBrushless);
    m_belt = new CANSparkMax(beltCANId, MotorType.kBrushless);
    m_feeder1 = new CANSparkMax(feeder1, MotorType.kBrushless);
    m_feeder2 = new CANSparkMax(feeder2, MotorType.kBrushless);

    m_upperShooterEncoder = m_upperShooter.getEncoder();
    m_lowerShooterEncoder = m_upperShooter.getEncoder();
    m_beltEncoder = m_belt.getEncoder();
    
    m_upperShooter.burnFlash();
    m_lowerShooter.burnFlash();
    m_belt.burnFlash();
    m_feeder1.burnFlash();
    m_feeder2.burnFlash();
  }

public void feederVelocity(float velocity) {
m_feeder1.set(velocity);
m_feeder2.set(velocity);
}


  public double getLaunchVelocity() {
    return 0.0;
  }

  public void setLaunchVelocity(double velocity) {
    m_upperShooter.set(velocity);
    new WaitCommand(0.7);
    m_lowerShooter.set(velocity);
  }

  public double getBeltVelocity() {
    return 0.0;
  }

  public void setBeltVelocity(double velocity) {
    //
    m_belt.set(velocity);
  }

  public void resetEncoders() {
    //
  }

  
public void setReloadVelocity(double velocity) {
  m_feeder1.set(velocity);
  m_feeder2.set(velocity);
}
}