package domain.states

import domain.TalkId

data class Refused(val talkId: TalkId, val reason: String) : State