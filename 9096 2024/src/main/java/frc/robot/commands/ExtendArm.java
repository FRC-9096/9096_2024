package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Pneumatics;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class ExtendArm extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Pneumatics m_pneumatics;
  PIDController pid;
  boolean toggled = false;
  double startTS;
  
  public ExtendArm(Pneumatics pneumatics) {
    m_pneumatics = pneumatics;
    addRequirements(m_pneumatics);
  }

  @Override
  public void initialize() {
    startTS = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    if (!toggled) {
      m_pneumatics.toggle();
      toggled = true;
    }
  }

  @Override
  public void end(boolean interrupted) {
    
  }
}