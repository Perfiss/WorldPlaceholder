package zxc.mrdrag0nxyt.worldPlaceholder.hook

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import org.bukkit.OfflinePlayer
import zxc.mrdrag0nxyt.worldPlaceholder.WorldPlaceholder
import zxc.mrdrag0nxyt.worldPlaceholder.config.Config

class PlaceholderApiHook(
    private val plugin: WorldPlaceholder,
    private val config: Config
) : PlaceholderExpansion() {

    override fun getIdentifier() = "worldplaceholder"
    override fun getAuthor() = plugin.description.authors.joinToString(", ")
    override fun getVersion() = plugin.description.version
    override fun persist() = true

    override fun onRequest(player: OfflinePlayer?, params: String): String? {
        player?.player?.let {
            val world = it.world.name

            config.worldColorsMap[world]?.let { color ->
                return color

            } ?: return "${config.defaultColor}${config.placeholderSymbol}"

        } ?: return null
    }
}