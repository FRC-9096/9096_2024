package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.ExpellRing;
import frc.robot.commands.Feed;
import frc.robot.commands.PositionRing;
import frc.robot.commands.ReloadLauncher;
import frc.robot.commands.Shoot;
import java.util.List;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;

public class RobotContainer {
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Shooter m_shooter = new Shooter(11, 10, 9, 12, 13); 
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandXboxController m_secondaryController = new CommandXboxController(OIConstants.kDriverControllerPort + 1);
  private SendableChooser<Command> autoChooser;

  public RobotContainer() {
    NamedCommands.registerCommand("Shoot", new Shoot(m_shooter, m_robotDrive));
    NamedCommands.registerCommand("Lock", new RunCommand(() -> m_robotDrive.lockWheels(), m_robotDrive));
    
    autoChooser = AutoBuilder.buildAutoChooser();
    configureButtonBindings();
    configureShuffleboard();
    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));
  }

  private void configureButtonBindings() {
    m_secondaryController.a().onTrue(new Shoot(m_shooter, m_robotDrive));
    m_secondaryController.x().whileTrue(new ReloadLauncher(m_shooter));
    m_secondaryController.b().whileTrue(new ExpellRing(m_shooter));
    m_secondaryController.y().whileTrue(
      new RunCommand(() -> m_robotDrive.lockWheels(), m_robotDrive)
    );
    m_secondaryController.rightTrigger().whileTrue(new Feed(m_shooter, m_robotDrive));
  }

  private void configureShuffleboard() {
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public Command getAutonomousCommand(){
    return autoChooser.getSelected();
  }

}  





















































































































































































//Rip.... and... Tear!