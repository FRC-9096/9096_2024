package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkBase;

import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Reload extends SubsystemBase {
    
private final CANSparkMax m_bottomFeeder;
private final CANSparkMax m_bottomFeeder2;
private final CANSparkMax m_belt;

public Reload(int lowerIntake1, int lowerIntake2, int intakeBelt) {
    m_bottomFeeder = new CANSparkMax(lowerIntake1, MotorType.kBrushless);
    m_bottomFeeder2 = new CANSparkMax(lowerIntake2, MotorType.kBrushless);
    m_belt = new CANSparkMax(intakeBelt, MotorType.kBrushless);




    m_bottomFeeder.burnFlash();
    m_bottomFeeder2.burnFlash();
    m_belt.burnFlash();
}

}
