package usecase

import domain.*
import domain.states.UnderScrutiny

class MakeAnObservation(private val talks: Talks) {
    operator fun invoke(talkId: TalkId, observation: String): UnderScrutiny =
            talks.findAs<UnderScrutiny>(talkId).makeObservation(observation)
}