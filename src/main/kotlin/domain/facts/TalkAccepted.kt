package domain.facts

import domain.TalkId
import domain.states.UnderScrutiny
import domain.states.WaitingForConfirmation
import java.time.Instant

data class TalkAccepted(override val talkId: TalkId, override val timestamp: Instant)
    : Fact<UnderScrutiny, WaitingForConfirmation> {
    override fun apply(state: UnderScrutiny): WaitingForConfirmation = state.talkAccepted()
}