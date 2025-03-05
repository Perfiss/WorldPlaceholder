package zxc.mrdrag0nxyt.worldPlaceholder.config

import org.bukkit.configuration.file.YamlConfiguration
import zxc.mrdrag0nxyt.worldPlaceholder.WorldPlaceholder
import java.io.File

class Config(
    private val plugin: WorldPlaceholder,
) {

    private val fileName = "config.yml"
    private val file = File(plugin.dataFolder, fileName)
    private val yamlConfiguration = initYamlConfiguration()

    var placeholderSymbol = "◆"
        private set
    var defaultColor = "<#c0c0c0>"
        private set
    var worldColorsMap = mutableMapOf<String, String>()
        private set

    var noPermissionMessage = "&#dc143cУ вас недостаточно прав для выполнения этого действия!"
        private set
    var reloadedMessage = "&#00ff7fПлагин успешно перезагружен"
        private set

    init {
        updateConfigValues()
    }

    private fun initYamlConfiguration(): YamlConfiguration {
        extractIfNotExists()
        return YamlConfiguration.loadConfiguration(file)
    }

    private fun extractIfNotExists() {
        if (!file.exists()) {
            plugin.saveResource(fileName, false)
        }
    }

    fun reload() {
        extractIfNotExists()
        try {
            yamlConfiguration.load(file)
        } catch (e: Exception) {
            plugin.logger.severe(e.toString())
        }
        updateConfigValues()
    }

    private fun updateConfigValues() {
        placeholderSymbol = yamlConfiguration.getString("worldSymbol", placeholderSymbol)!!
        defaultColor = yamlConfiguration.getString("defaultColor", defaultColor)!!

        val configurationSection = yamlConfiguration.getConfigurationSection("worlds")
        if (configurationSection != null) {
            for (key in configurationSection.getKeys(false)) {
                val color = yamlConfiguration.getString("worlds.$key", defaultColor)!!
                worldColorsMap[key] = "$color$placeholderSymbol"
            }
        }

        noPermissionMessage = yamlConfiguration.getString("messages.noPermission", noPermissionMessage)!!
        reloadedMessage = yamlConfiguration.getString("messages.reloaded", reloadedMessage)!!
    }

}