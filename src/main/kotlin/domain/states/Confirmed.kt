package domain.states

import domain.TalkId

data class Confirmed(val talkId: TalkId) : State