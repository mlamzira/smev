package domain.states

import domain.*
import domain.facts.ObservationMadeBuilder
import domain.facts.TalkAccepted
import java.time.Instant

data class UnderScrutiny(private val pushFact: PushFact,
                         private val talkId: TalkId,
                         private val observations: List<String> = emptyList()) : State {

    fun makeObservation(observation: String): UnderScrutiny {
        pushFact(observation
                .made()
                .at(Instant.now()))

        return observationMade(observation)
    }

    fun observationMade(observation: String) = this.copy(observations = observations + observation)

    fun accept(): WaitingForConfirmation {
        pushFact(TalkAccepted(talkId, Instant.now()))
        return talkAccepted()
    }

    fun talkAccepted() = WaitingForConfirmation(pushFact, talkId)

    fun refuse(reason: String): Refused {
        TODO()
    }
    private fun String.made() = ObservationMadeBuilder()
            .talkId(talkId)
            .observation(this)
}