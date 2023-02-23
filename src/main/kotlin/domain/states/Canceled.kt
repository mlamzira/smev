package domain.states

import domain.TalkId

data class Canceled(val talkId: TalkId) : State