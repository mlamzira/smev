package domain.states

import domain.PushFact
import domain.TalkId
import domain.facts.TalkConfirmed
import java.time.Instant

data class WaitingForConfirmation(private val pushFact: PushFact, private val talkId: TalkId) : State {
    fun confirm(): Confirmed {
        pushFact(TalkConfirmed(talkId, Instant.now()))
        return confirmed()
    }

    fun confirmed() = Confirmed(talkId)

    fun cancel(): Canceled {
        return Canceled(talkId)
    }
}