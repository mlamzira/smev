package domain.facts

import domain.TalkId
import domain.states.WaitingForConfirmation
import domain.states.Confirmed
import java.time.Instant

data class TalkConfirmed(override val talkId: TalkId, override val timestamp: Instant)
    : Fact<WaitingForConfirmation, Confirmed> {
    override fun apply(state: WaitingForConfirmation): Confirmed = state.confirmed()
}