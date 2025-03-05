package zxc.mrdrag0nxyt.worldPlaceholder.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import zxc.mrdrag0nxyt.worldPlaceholder.WorldPlaceholder
import zxc.mrdrag0nxyt.worldPlaceholder.config.Config
import zxc.mrdrag0nxyt.worldPlaceholder.util.colorize

class ReloadCommand(
    private val plugin: WorldPlaceholder,
    private val config: Config,
) : CommandExecutor, TabCompleter {

    override fun onCommand(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<String>,
    ): Boolean {
        if (!commandSender.hasPermission("wp.reload")) {
            commandSender.sendMessage(
                colorize(config.noPermissionMessage)
            )
            return false
        }

        plugin.reload()
        commandSender.sendMessage(
            colorize(config.reloadedMessage)
        )
        return true
    }

    override fun onTabComplete(
        commandSender: CommandSender,
        command: Command,
        string: String,
        strings: Array<String>,
    ): List<String> {
        return emptyList()
    }

}
