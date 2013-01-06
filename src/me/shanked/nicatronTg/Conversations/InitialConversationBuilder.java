package me.shanked.nicatronTg.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;

public class InitialConversationBuilder extends BooleanPrompt{

	public InitialConversationBuilder() {
	}

	@Override
	public String getPromptText(ConversationContext arg0) {
		arg0.getForWhom().sendRawMessage(ChatColor.RED + "This will send a report to the admins directly. Abuse of this feature may result in a ban.");
		arg0.getForWhom().sendRawMessage(ChatColor.GREEN + "To exit this prompt at any time and return to chat, type 'QUIT'.");
		return ChatColor.AQUA + "Are you reporting an attack in progress (grief/spam/etc.)? Respond with " + ChatColor.GREEN + "yes " + ChatColor.AQUA + "or " + ChatColor.RED + "no" + ChatColor.AQUA + ".";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext arg0, boolean arg1) {
		if (arg1) {
			arg0.setSessionData("reportType", ReportType.SERVER_ATTACK);
			return new ReportTextPrompt();
		} else {
			return new ServerMisconfigurationPrompt();
		}
	}

}
