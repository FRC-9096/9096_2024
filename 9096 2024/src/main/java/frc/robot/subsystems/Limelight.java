package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;

public class Limelight extends SubsystemBase {

  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = table.getEntry("tx");
  NetworkTableEntry ty = table.getEntry("ty");
  NetworkTableEntry ta = table.getEntry("ta");
  NetworkTableEntry tv = table.getEntry("tv");
  NetworkTableEntry pipeline = table.getEntry("pipeline");
  NetworkTableEntry botpose = table.getEntry("targetpose_cameraspace");
  double v = tv.getNumber(0).doubleValue();

  public final UsbCamera FrontFacingCamerma = new UsbCamera("Front Camera", 0);

  public Limelight() {
   
    
    

  }

  public void setUpCameras() {

 CameraServer.startAutomaticCapture();
    
    CvSink SetSink = CameraServer.getVideo();
    CvSource SendBack = CameraServer.putVideo("Front Camera", 800, 800);

  }

  public Number getPipeline() {
    return pipeline.getNumber(0);
  }

  public void setPipeline(Number newPipeline) {
    pipeline.setNumber(newPipeline);
  }

  public double getPose(int index) {
    double[] pose = botpose.getDoubleArray(new double[6]);
    return pose[index];
  }

  public double getAngle() {
    double[] pose = botpose.getDoubleArray(new double[6]);
    return pose[4];
  }

  public double getDist() {
    double[] pose = botpose.getDoubleArray(new double[6]);
    return pose[2];
  }

  public double getVisible() {
    return tv.getDouble(0.0);
  }

  public double getEntry(String entry) {
    return table.getEntry(entry).getDouble(0.0);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Apriltag distance", getPose(2));
    SmartDashboard.putNumber("Apriltag angle", getPose(4));
    SmartDashboard.putNumber("Visible", getVisible());
  }

}
