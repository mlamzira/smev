package domain.facts

import domain.TalkId
import domain.states.State
import java.time.Instant

interface Fact<IN : State, OUT : State> {
    val talkId: TalkId
    val timestamp: Instant

    fun apply(state: IN): OUT
}

