package me.shanked.nicatronTg.Conversations;

import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.ValidatingPrompt;

public class ReportTextPrompt extends ValidatingPrompt {

	public ReportTextPrompt() {

	}

	@Override
	public String getPromptText(ConversationContext context) {
		context.getForWhom().sendRawMessage(ChatColor.YELLOW + "You will have one last chance after this prompt to abort sending your message.");
		return ChatColor.AQUA + "Please describe the problem in 256 characters (letters and numbers) or less.";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context,
			String input) {
		context.setSessionData("message", input);
		return new SendConfirmationPrompt();
	}

	@Override
	protected boolean isInputValid(ConversationContext context, String input) {
		if (input.length() <= 256 && input.length() > 15) {
			return true;
		}
		return false;
	}

}
