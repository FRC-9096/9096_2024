package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  PIDController pid;
  double startTS;
  
  public Shoot(Shooter shooter) {
    m_shooter = shooter;
    addRequirements(m_shooter);
  }

  @Override
  public void initialize() {
    startTS = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
        m_shooter.setLaunchVelocity(1.0f);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setLaunchVelocity(0);
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTS > 2;
  }
}