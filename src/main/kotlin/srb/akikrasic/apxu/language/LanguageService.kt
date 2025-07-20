package srb.akikrasic.apxu.language

import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream

object LanguageService {

    private val serbian = "serbian"
    private val english = "english"

    private val mapOfLanguages = mutableMapOf<String, Map<String, Map<String, String>>>()

    private var currentLanguage = mapOf<String, Map<String, String>>()

    fun loadLanguageIntoMap(language: String) {
        val inpStream = FileInputStream(File("src/main/kotlin/srb/akikrasic/apxu/resource/language/${language}.yaml"))
        val yaml = Yaml()
        val values: Map<String, Map<String, String>> = yaml.load(inpStream)
        mapOfLanguages[language] = values
    }

    init {
        loadLanguageIntoMap(serbian)
        loadLanguageIntoMap(english)
    }

    fun setSerbianLanguage() {
        currentLanguage = mapOfLanguages[serbian]!!
    }

    fun setEnglishLanguage() {
        currentLanguage = mapOfLanguages[english]!!
    }

    fun getTranslationsForComponent(componentName: String) = currentLanguage[componentName]!!
}