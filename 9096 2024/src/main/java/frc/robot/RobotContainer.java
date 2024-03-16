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
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.ExpellRing;
import frc.robot.commands.PositionRing;
import frc.robot.commands.ReloadLauncher;
import frc.robot.commands.Shoot;
import java.util.List;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotContainer {
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final Shooter m_shooter = new Shooter(11, 10, 9, 12, 13); 
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
  CommandXboxController m_secondaryController = new CommandXboxController(OIConstants.kDriverControllerPort + 1);
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();
  public RobotContainer() {
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
    m_secondaryController.a().onTrue(new Shoot(m_shooter));
    m_secondaryController.x().whileTrue(new ReloadLauncher(m_shooter));
    m_secondaryController.b().whileTrue(new ExpellRing(m_shooter));
  }

  private void configureShuffleboard() {
    autoChooser.setDefaultOption("Center", genAutoCommand(0));
    autoChooser.addOption("Left", genAutoCommand(0.5));
    autoChooser.addOption("Right", genAutoCommand(-0.5));

    edu.wpi.first.wpilibj.smartdashboard.SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  private Command genAutoCommand(double yMovement) {
    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        .setKinematics(DriveConstants.kDriveKinematics);

    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        new Pose2d(-0.0, 0.0, new Rotation2d(0)),
        List.of(new Translation2d(-1.0, 0.01)),
        new Pose2d(-2.0, yMovement, new Rotation2d(0)),
        config);

    var thetaController = new ProfiledPIDController(
        AutoConstants.kPThetaController, 0, 0, AutoConstants.kThetaControllerConstraints);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    SwerveControllerCommand swerveControllerCommand = new SwerveControllerCommand(
        exampleTrajectory,
        m_robotDrive::getPose,
        DriveConstants.kDriveKinematics,

        new PIDController(AutoConstants.kPXController, 0, 0),
        new PIDController(AutoConstants.kPYController, 0, 0),
        thetaController,
        m_robotDrive::setModuleStates,
        m_robotDrive);

    m_robotDrive.resetOdometry(exampleTrajectory.getInitialPose());

    return Commands.sequence(
        new PositionRing(m_shooter),
        new Shoot(m_shooter),
        swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false, false))
      );
  }

  public Command getAutonomousCommand(){
    return autoChooser.getSelected();
  }

}  





















































































































































































//Rip.... and... Tear!