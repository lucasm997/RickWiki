package br.com.meneghin.rickwiki

import java.io.File

object TestUtils {

    fun getJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}