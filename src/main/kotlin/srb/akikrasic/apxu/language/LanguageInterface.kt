package srb.akikrasic.apxu.language

interface LanguageInterface {
    fun setTranslations(translations: Map<String, String>)
    fun translateComponent() {
        setTranslations(componentName())
    }

    fun componentName() = LanguageService.getTranslationsForComponent(this.javaClass.simpleName)
}