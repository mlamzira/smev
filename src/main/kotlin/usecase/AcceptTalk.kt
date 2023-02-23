package usecase

import domain.*
import domain.states.UnderScrutiny
import domain.states.WaitingForConfirmation

class AcceptTalk(private val talks: Talks) {
    operator fun invoke(talkId: TalkId): WaitingForConfirmation =
            talks.findAs<UnderScrutiny>(talkId).accept()
}