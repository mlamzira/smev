package usecase

import domain.*
import domain.states.Confirmed
import domain.states.UnderScrutiny
import domain.states.WaitingForConfirmation

class ConfirmTalk(private val talks: Talks) {
    operator fun invoke(talkId: TalkId): Confirmed =
            talks.findAs<WaitingForConfirmation>(talkId).confirm()
}