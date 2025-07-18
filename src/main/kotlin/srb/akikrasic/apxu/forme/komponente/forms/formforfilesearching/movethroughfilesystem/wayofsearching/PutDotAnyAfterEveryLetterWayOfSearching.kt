package srb.akikrasic.apxu.forme.komponente.forms.formforfilesearching.movethroughfilesystem.wayofsearching

class PutDotAnyAfterEveryLetterWayOfSearching(override val forSearch:String): ChangeWhitespaceWithDotAnyWayOfSearching(forSearch) {

    override fun createRegex(): Regex {
        val sb = StringBuilder(".*")

        for( c in super.forSearch){
            if(c.isWhitespace()){
               sb.append(".*")
            }
            else sb.append(c).append(".*")
        }
        return Regex(sb.toString())
    }



}