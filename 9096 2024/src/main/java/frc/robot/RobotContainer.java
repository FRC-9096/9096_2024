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
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Shooter;
import frc.robot.commands.Shoot;
import frc.robot.subsystems.Limelight;
import java.util.List;

public class RobotContainer {
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();


  //REDEFINED FROM XBOXCONTROLLER TO COMMANDXBOXCONTROLLER
  CommandXboxController m_driverController = new CommandXboxController(OIConstants.kDriverControllerPort);
private final GenericHID JoystickButto = new GenericHID(0);
  public RobotContainer() {
    configureButtonBindings();

    //Limelight called
    Limelight CallCamera = new Limelight();
    CallCamera.setUpCameras();
    



    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> m_robotDrive.drive(
                -MathUtil.applyDeadband(m_driverController.getLeftX(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getLeftY(), OIConstants.kDriveDeadband),
                -MathUtil.applyDeadband(m_driverController.getRightX(), OIConstants.kDriveDeadband),
                true, true),
            m_robotDrive));
  }

  private void configureButtonBindings() {
    // map button to commands, lockwheels is a placeholder here
    //Trigger JoystickButton = new JoystickButton(m_driverController, XboxController.Button.kStart.value);
    Trigger JoystickButtonY = new JoystickButton(JoystickButto, XboxController.Button.kStart.value);
        JoystickButtonY.whileTrue(new RunCommand(
            () -> m_robotDrive.lockWheels(),
            m_robotDrive));

    
  }



  public Command getAutonomousCommand() {
    TrajectoryConfig config = new TrajectoryConfig(
        AutoConstants.kMaxSpeedMetersPerSecond,
        AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        .setKinematics(DriveConstants.kDriveKinematics);

    Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
        new Pose2d(0, 0, new Rotation2d(0)),
        List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        new Pose2d(3, 0, new Rotation2d(0)),
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

    return swerveControllerCommand.andThen(() -> m_robotDrive.drive(0, 0, 0, false, false));
  }

  public void SetUpShooterButtons() {
    
    

    Shooter foo = new Shooter(11, 10, 11, 12, 13);
    //Shooter food = new Shooter(0, 0, 0, 12, 13);
    
    Shooter FloopFoodFooFoop = new Shooter(11, 10, 9, 12, 13); 
    //I just thought this was funny 
   // Shoot food = new Shoot();
    


    Trigger xButton = m_driverController.x(); //<-- Currently Redundant 
   // m_driverController.xButton().onTrue(Shoot.BeginLaunch(foo));
    xButton.onTrue(Shoot.BeginLaunch(foo));
    //m_driverController.x().onFalse(Shoot.BeginLaunch(food));
    Trigger aButton = m_driverController.a();
   // m_driverController.a().whileTrue(Shoot.Reload(FloopFoodFooFoop));
   aButton.whileTrue(Shoot.Reload(FloopFoodFooFoop));

    
  }
}  





















































































































































































//Rip.... and... Tear!