import domain.*
import domain.facts.Fact
import domain.facts.ObservationMade
import domain.facts.TalkAccepted
import domain.facts.TalkSubmitted
import domain.states.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import usecase.AcceptTalk
import usecase.ConfirmTalk
import usecase.MakeAnObservation
import usecase.SubmitTalk
import java.time.Instant

class UseCasesTest {

    private val factSink = FactSink()
    private val talks = Talks(FactsRepo(factSink), factSink)
    private val submit = SubmitTalk(factSink)
    private val makeAnObservation = MakeAnObservation(talks)
    private val acceptTalk = AcceptTalk(talks)
    private val confirmTalk = ConfirmTalk(talks)

    @Test
    fun `talk should be under scrutiny once it's submitted`() {
        val submit = SubmitTalk(factSink)
        val talkId = TalkId("talk-123")

        val actual = submit(Talk(talkId, "les tests c'est nul, mais..."))

        assertThat(talks.findAs<UnderScrutiny>(talkId)).isEqualTo(actual)
    }

    @Test
    fun `talk should stay under scrutiny after making an observation about it`() {
        val talkId = TalkId("talk-123")
        submit(Talk(talkId, "les tests c'est nul, mais..."))

        val actual = makeAnObservation(talkId, "ça l'air nul comme sujet")

        assertThat(talks.findAs<UnderScrutiny>(talkId)).isEqualTo(actual)
    }
    @Test
    fun `talk should be waiting for confirmation once it's accepted`() {
        val talkId = TalkId("talk-123")
        submit(Talk(talkId, "les tests c'est nul, mais..."))
                .makeObservation("ça l'air nul comme sujet")
                .makeObservation("c'est une blague ?")

        val actual = acceptTalk(talkId)

        assertThat(talks.findAs<WaitingForConfirmation>(talkId)).isEqualTo(actual)
    }

    @Test
    fun `talk should be confirmed`() {
        val talkId = TalkId("talk-123")
        submit(Talk(talkId, "les tests c'est nul, mais..."))
                .makeObservation("ça l'air nul comme sujet")
                .makeObservation("c'est une blague ?")
                .accept()

        val actual = confirmTalk(talkId)

        assertThat(talks.findAs<Confirmed>(talkId)).isEqualTo(actual)
    }

    class FactSink: PushFact {

        val facts: MutableList<Fact<*, *>> = ArrayList()
        override fun invoke(fact: Fact<*, *>) {
            facts.add(fact)
        }
    }

    class FactsRepo(private val factSink: FactSink): Facts {
        override fun findBy(talkId: TalkId): List<Fact<State, State>> {
            return factSink.facts.map { it as Fact<State, State> }
        }

    }
}