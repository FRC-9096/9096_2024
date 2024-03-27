package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;

public class Feed extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Shooter m_shooter;
  private final DriveSubsystem m_drive;
  PIDController pid;
  double startTS;
  
  public Feed(Shooter shooter, DriveSubsystem drive) {
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
    m_shooter.setUpperVelocity(-0.1);
    m_shooter.setLowerVelocity(-0.1);
  }

  @Override
  public void end(boolean interrupted) {
    m_shooter.setLaunchVelocity(0);
    m_drive.enable();
  }

}