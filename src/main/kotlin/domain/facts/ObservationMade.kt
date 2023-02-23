package domain.facts

import domain.TalkId
import domain.states.UnderScrutiny
import java.time.Instant

data class ObservationMade(val observation: String, override val talkId: TalkId, override val timestamp: Instant)
    : Fact<UnderScrutiny, UnderScrutiny> {
    override fun apply(state: UnderScrutiny): UnderScrutiny = state.observationMade(observation)
}

class ObservationMadeBuilder {
    private lateinit var id: TalkId
    private lateinit var observation: String
    private lateinit var timestamp: Instant

    fun talkId(talkId: TalkId) = apply { this.id = talkId }
    fun observation(observation: String) = apply { this.observation = observation }
    fun at(timestamp: Instant): ObservationMade {
        this.timestamp = timestamp
        return ObservationMade(observation, id, timestamp)
    }
}