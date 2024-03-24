package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class Shoot extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private final DriveSubsystem m_drive;
  PIDController pid;
  double startTS;
  
  public Shoot(Shooter shooter, DriveSubsystem drive) {
    m_shooter = shooter;
    m_drive = drive;
    addRequirements(m_shooter, drive);
  }

  @Override
  public void initialize() {
    startTS = Timer.getFPGATimestamp();
    m_drive.disable();
  }

  @Override
  public void execute() {
    m_shooter.setUpperVelocity(1.1);
    if (Timer.getFPGATimestamp() - startTS > 0.5) {
      m_shooter.setLowerVelocity(1.1);
    }
    if (Timer.getFPGATimestamp() - startTS < 0.1) {
      m_drive.drive(0, 0, 0, false, false);
    } else {
      m_drive.disable();
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setLaunchVelocity(0);
    m_drive.enable();
  }

  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTS > 1.5;
  }
}