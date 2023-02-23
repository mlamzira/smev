package domain.facts

import domain.Talk
import domain.states.Undefined
import domain.states.UnderScrutiny
import java.time.Instant

data class TalkSubmitted(val talk: Talk, override val timestamp: Instant) : Fact<Undefined, UnderScrutiny> {
    override val talkId = talk.id
    override fun apply(state: Undefined): UnderScrutiny = state.underScrutiny(talkId)

}

class TalkSubmittedBuilder {
    private lateinit var talk: Talk
    private lateinit var timestamp: Instant

    fun talk(talk: Talk) = apply { this.talk = talk }
    fun at(timestamp: Instant): TalkSubmitted {
        this.timestamp = timestamp
        return TalkSubmitted(talk, timestamp)
    }
}