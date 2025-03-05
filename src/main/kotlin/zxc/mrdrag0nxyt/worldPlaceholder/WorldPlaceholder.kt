package zxc.mrdrag0nxyt.worldPlaceholder

import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin
import zxc.mrdrag0nxyt.worldPlaceholder.command.ReloadCommand
import zxc.mrdrag0nxyt.worldPlaceholder.config.Config
import zxc.mrdrag0nxyt.worldPlaceholder.hook.PlaceholderApiHook

class WorldPlaceholder : JavaPlugin() {

    private val config = Config(this)

    override fun onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            logger.severe("Для работы ${description.name} необходим плагин PlaceholderAPI")
            Bukkit.getPluginManager().disablePlugin(this)
        }

        PlaceholderApiHook(this, config).register()
        getCommand("wpreload")?.setExecutor(ReloadCommand(this, config))

        logger.info("Плагин ${description.name} v.${description.version} успешно загружен")
    }

    override fun onDisable() {
        logger.info("Плагин ${description.name} v.${description.version} отключен")
    }

    fun reload() {
        config.reload()
    }
}
