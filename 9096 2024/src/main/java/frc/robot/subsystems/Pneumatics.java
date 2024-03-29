package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Pneumatics extends SubsystemBase {
  private final Solenoid m_solenoid = new Solenoid(PneumaticsModuleType.REVPH, 0);
  public Pneumatics() {

  }

  public void toggle() {
    m_solenoid.toggle();
  }

}