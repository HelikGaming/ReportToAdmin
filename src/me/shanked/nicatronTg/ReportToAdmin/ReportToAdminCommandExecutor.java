package me.shanked.nicatronTg.ReportToAdmin;

import me.shanked.nicatronTg.Conversations.InitialConversationBuilder;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.conversations.Conversable;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;

public class ReportToAdminCommandExecutor implements CommandExecutor {

	ReportToAdmin plugin;
	ConversationFactory conversationFactory;
	
	public ReportToAdminCommandExecutor(ReportToAdmin instance) {
		this.plugin = instance;
		conversationFactory = new ConversationFactory(plugin).withModality(true).withFirstPrompt(new InitialConversationBuilder()).withEscapeSequence("QUIT");
	}

	@Override
	public boolean onCommand(CommandSender commandSender, Command command, String commandLabel,
			String[] arguments) {

		if (commandSender instanceof Player) {
			Player ply = (Player) commandSender;
			if (command.getName().toLowerCase().equals("reporttoadmin")) {
				if (ply.hasPermission("shankshock.reporttoadmin")) {
					conversationFactory.buildConversation((Conversable) commandSender).begin();
				}
				return true;
			}
		}
		
		return false;
	}

}
