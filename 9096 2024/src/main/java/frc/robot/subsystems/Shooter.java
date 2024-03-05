package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandPS4Controller;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import com.revrobotics.RelativeEncoder;

public class Shooter extends SubsystemBase {
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

   

  }




  public float getLaunchVelocity() {
    return 0.0f;
  }

  public void setLaunchVelocity(float velocity) {
    //use this one for launching
    m_upperShooter.set(velocity);
    m_lowerShooter.set(velocity);
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