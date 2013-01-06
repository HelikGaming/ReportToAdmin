package me.shanked.nicatronTg.ReportToAdmin;

import org.bukkit.plugin.java.JavaPlugin;

public class ReportToAdmin extends JavaPlugin {

	ReportToAdminCommandExecutor commandExecutor;

	@Override
	public void onEnable() {
		commandExecutor = new ReportToAdminCommandExecutor(this);
		getCommand("reporttoadmin").setExecutor(commandExecutor);
	}
	
}
