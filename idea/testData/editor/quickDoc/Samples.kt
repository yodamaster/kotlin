package magic

object Samples {
    fun sampleMagic() {
        castTextSpell("[asd] [dse] [asz]")
    }
}

fun sampleScroll() {
    val reader = Scroll("[asd] [dse] [asz]").reader()
    castTextSpell(reader.readAll())
}

/**
 * @sample Samples.sampleMagic
 * @sample sampleScroll
 */
fun <caret>castTextSpell(spell: String) {
    throw SecurityException("Magic prohibited outside Hogwarts")
}

//INFO: <b>public</b> <b>fun</b> castTextSpell(spell: String): Unit <i>defined in</i> magic<br/>
//INFO: <dl><dt><b>Samples:</b></dt><dd><a href="psi_element://Samples.sampleMagic"><code>Samples.sampleMagic</code></a></dd><dd><a href="psi_element://sampleScroll"><code>sampleScroll</code></a></dd></dl>
