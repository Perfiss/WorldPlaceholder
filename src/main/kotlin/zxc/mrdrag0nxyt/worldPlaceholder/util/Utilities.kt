package zxc.mrdrag0nxyt.worldPlaceholder.util

import java.util.regex.Pattern

private val HEX_PATTERN = Pattern.compile("&#([a-fA-F\\d]{6})")
private val COLOR_CHAR = '§'

fun colorize(string: String): String {
    if (string.isBlank()) return string

    var result = string

    val matcher = HEX_PATTERN.matcher(string)
    val builder = StringBuilder()

    while (matcher.find()) {
        val group = matcher.group(1)

        matcher.appendReplacement(
            builder,
            "${COLOR_CHAR}x" +
                    COLOR_CHAR + group[0] +
                    COLOR_CHAR + group[1] +
                    COLOR_CHAR + group[2] +
                    COLOR_CHAR + group[3] +
                    COLOR_CHAR + group[4] +
                    COLOR_CHAR + group[5]
        )
    }
    result = matcher.appendTail(builder).toString()
    return translateAlternateColorCodes('&', result)
}

fun translateAlternateColorCodes(altColorChar: Char, textToTranslate: String): String {
    val b = textToTranslate.toCharArray()

    var i = 0
    val length = b.size - 1
    while (i < length) {
        if (b[i] == altColorChar && isValidColorCharacter(b[i + 1])) {
            b[i++] = COLOR_CHAR
            b[i] = (b[i].code or 0x20).toChar()
        }
        ++i
    }

    return String(b)
}

private fun isValidColorCharacter(c: Char): Boolean {
    return (c in '0'..'9') ||
            (c in 'a'..'f') || c == 'r' ||
            (c in 'k'..'o') || c == 'x' ||
            (c in 'A'..'F') || c == 'R' ||
            (c in 'K'..'O') || c == 'X'
}
