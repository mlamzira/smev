package domain.states

import domain.PushFact
import domain.Talk
import domain.TalkId
import domain.facts.TalkSubmittedBuilder
import java.time.Instant

data class Undefined(val pushFact: PushFact) : State {
    fun submit(talk: Talk): UnderScrutiny {
        val fact = talk.submitted()
                .at(Instant.now())
        pushFact(fact)

        return underScrutiny(talk.id)
    }

    fun underScrutiny(talkId: TalkId) = UnderScrutiny(pushFact, talkId)

    private fun Talk.submitted() = TalkSubmittedBuilder().talk(this)
}