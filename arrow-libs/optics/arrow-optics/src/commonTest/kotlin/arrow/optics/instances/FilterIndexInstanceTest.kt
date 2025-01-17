package arrow.optics.instances

import arrow.optics.test.functionAToB
import arrow.optics.test.laws.TraversalLaws
import arrow.optics.test.laws.testLaws
import arrow.optics.test.nonEmptyList
import arrow.optics.test.sequence
import arrow.optics.typeclasses.FilterIndex
import io.kotest.core.spec.style.StringSpec
import io.kotest.property.Arb
import io.kotest.property.arbitrary.char
import io.kotest.property.arbitrary.int
import io.kotest.property.arbitrary.list
import io.kotest.property.arbitrary.map
import io.kotest.property.arbitrary.string

class FilterIndexInstanceTest : StringSpec({

    testLaws(
      "FilterIndex list - ",
      TraversalLaws(
        traversal = FilterIndex.list<String>().filter { true },
        aGen = Arb.list(Arb.string()),
        bGen = Arb.string(),
        funcGen = Arb.functionAToB(Arb.string()),
      )
    )

    testLaws(
      "FilterIndex sequence - ",
      TraversalLaws(
        traversal = FilterIndex.sequence<String>().filter { true },
        aGen = Arb.sequence(Arb.string()),
        bGen = Arb.string(),
        funcGen = Arb.functionAToB(Arb.string()),
      ) { a, b -> a.toList() == b.toList() }
    )

    testLaws(
      "FilterIndex Nel - ",
      TraversalLaws(
        traversal = FilterIndex.nonEmptyList<String>().filter { true },
        aGen = Arb.nonEmptyList(Arb.string()),
        bGen = Arb.string(),
        funcGen = Arb.functionAToB(Arb.string()),
      )
    )

    testLaws(
      "FilterIndex map - ",
      TraversalLaws(
        traversal = FilterIndex.map<Char, Int>().filter { true },
        aGen = Arb.map(Arb.char(), Arb.int()),
        bGen = Arb.int(),
        funcGen = Arb.functionAToB(Arb.int()),
      )
    )

    testLaws(
      "FilterIndex string - ",
      TraversalLaws(
        traversal = FilterIndex.string().filter { true },
        aGen = Arb.string(),
        bGen = Arb.char(),
        funcGen = Arb.functionAToB(Arb.char()),
      )
    )
})
