package frc.robot.commands;

import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Limelight;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;

public final class Autos {
  public static Command getAuto(DriveSubsystem drivesubsystem, Limelight limelightsubsystem) {
    return Commands.sequence(
      //new MoveArm(drivesubsystem, limelightsubsystem)
    );
  }

}
