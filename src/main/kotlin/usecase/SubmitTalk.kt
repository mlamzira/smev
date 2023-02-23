package usecase

import domain.PushFact
import domain.Talk
import domain.states.Undefined
import domain.states.UnderScrutiny

class SubmitTalk(private val pushFact: PushFact) {
    operator fun invoke(talk: Talk): UnderScrutiny = Undefined(pushFact).submit(talk)
}