package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class ReloadLauncher extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  PIDController pid;
  
  public ReloadLauncher(Shooter shooter) {
    m_shooter = shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void execute() {
    m_shooter.setReloadVelocity(-0.7);
    m_shooter.setBeltVelocity(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setReloadVelocity(0);
    m_shooter.setBeltVelocity(0);
  }
  
}