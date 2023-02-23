package domain

import domain.facts.Fact
import domain.states.State
import domain.states.Undefined

class Talks(private val facts: Facts,
            private val pushFact: PushFact) {

    fun findBy(talkId: TalkId): State {
        val initial: State = Undefined(pushFact)
        return facts.findBy(talkId)
                .fold(initial) { state, fact -> fact.apply(state) }
    }
}

interface Facts {
    fun findBy(talkId: TalkId): List<Fact<State, State>>
}

inline fun <reified T : State> Talks.findAs(talkId: TalkId): T {
    val state = findBy(talkId)
    if (state is T) {
        return state
    }
    throw IllegalStateException("inconherent state, state found ${state.javaClass.simpleName}")
}

data class Talk(val id: TalkId, val title: String)
data class TalkId(val value: String)