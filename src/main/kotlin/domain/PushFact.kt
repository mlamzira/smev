package domain

import domain.facts.Fact

interface PushFact: (Fact<*, *>) -> Unit