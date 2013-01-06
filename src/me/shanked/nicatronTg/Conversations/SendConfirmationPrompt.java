package me.shanked.nicatronTg.Conversations;

import java.io.IOException;
import java.util.List;

import me.shanked.nicatronTg.jPushover.Pushover;

import org.bukkit.ChatColor;
import org.bukkit.conversations.BooleanPrompt;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.entity.Player;

public class SendConfirmationPrompt extends BooleanPrompt {

	public SendConfirmationPrompt() {
	}

	@Override
	public String getPromptText(ConversationContext context) {
		context.getForWhom().sendRawMessage(ChatColor.AQUA + "Admin Report Message Review:");
		context.getForWhom().sendRawMessage(ChatColor.AQUA + "Report Type: " + ChatColor.YELLOW + ((ReportType) context.getSessionData("reportType")).getName());
		context.getForWhom().sendRawMessage(ChatColor.AQUA + "Message: " + ChatColor.YELLOW + ((String) context.getSessionData("message")));
		context.getForWhom().sendRawMessage(ChatColor.AQUA + "Your Name: " + ChatColor.YELLOW + ((Player) context.getForWhom()).getName());
		context.getForWhom().sendRawMessage(ChatColor.AQUA + "Your IP/Hostname: " + ChatColor.YELLOW + ((Player) context.getForWhom()).getAddress());
		return ChatColor.AQUA + "This is your last chance to abort. Send the admin report?";
	}

	@Override
	protected Prompt acceptValidatedInput(ConversationContext context,
			boolean input) {
		if (input) {
			context.getForWhom().sendRawMessage(ChatColor.AQUA + "Your report will send shortly.");
			final List<String> pushoverDeviceKeys = context.getPlugin().getConfig().getStringList("pushoverDeviceKeys");
			final String pushoverApplicationKey = context.getPlugin().getConfig().getString("pushoverApplicationKey");
			final String pushoverTitlePrefix = context.getPlugin().getConfig().getString("pushoverMessageTitlePrefix");
			final boolean highPriority = context.getPlugin().getConfig().getBoolean("enableHighPriorityMessages");
			final String message = (String) context.getSessionData("message");
			final ReportType reportType = (ReportType) context.getSessionData("reportType");
			final String playerName = ((Player) context.getForWhom()).getName();
			context.getPlugin().getServer().getScheduler().runTaskAsynchronously(context.getPlugin(), new Runnable() {
				public void run() {
					for (String s : pushoverDeviceKeys) {
						Pushover p = new Pushover(pushoverApplicationKey, s);
						if (highPriority) {
							try {
								p.sendMessageHighPriority(playerName + ": " + message, pushoverTitlePrefix + " Report: " + reportType);
							} catch (IOException e) {
								e.printStackTrace();
							}
						} else {
							try {
								p.sendMessage(playerName + ": " + message, pushoverTitlePrefix + " Report: " + reportType);
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			});
		}
		return Prompt.END_OF_CONVERSATION;
	}

}
