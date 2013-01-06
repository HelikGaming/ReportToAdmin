package me.shanked.nicatronTg.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class ServerMisconfigurationPrompt extends BooleanPrompt {

	public ServerMisconfigurationPrompt() {

	}

	@Override
	public String getPromptText(ConversationContext context) {
		return ChatColor.AQUA + "Are you reporting a server misconfiguration, broken plugin, or other game breaking fault?";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context,
			boolean input) {
		if (input) {
			context.setSessionData("reportType", ReportType.SERVER_MISCONFIGURATION);
		} else {
			context.setSessionData("reportType", ReportType.OTHER);
		}
		return new ReportTextPrompt();
	}

}
