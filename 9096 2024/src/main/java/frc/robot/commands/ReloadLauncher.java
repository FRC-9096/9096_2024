package frc.robot.commands;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Reload;
import frc.robot.RobotContainer;

public class ReloadLauncher extends Command {
    public final Reload m_reload;
    public PIDController PID;
    //public final Reload Reloading;
    
    
    
    public ReloadLauncher(Reload Reloading) {
        //TODO Auto-generated constructor stub
         m_reload = Reloading;
        addRequirements(m_reload);
    }

  
    @Override
    public void execute() {
        m_reload.setReloadVelocity(1);
    }

    @Override
    public void end(boolean interrupted) {
        m_reload.setReloadVelocity(0);
    }
    

}
