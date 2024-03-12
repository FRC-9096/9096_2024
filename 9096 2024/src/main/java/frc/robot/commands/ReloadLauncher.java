package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Reload;

public class ReloadLauncher extends Command {
    
    
    
    public void Reload(Reload Reloading) {
        private final Reload m_reload;
        m_reload = Reloading;
        addRequirements(m_reload);
    }


    

}
