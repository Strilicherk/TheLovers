package org.example.thelovers.core.domain.model

enum class Gender(val id: Int, val displayName: String) {
    MAN_CIS(1, "Homem Cis"),
    MAN_TRANS(2, "Homem Trans"),
    WOMAN_CIS(3, "Mulher Cis"),
    WOMAN_TRANS(4, "Mulher Trans"),
    NON_BINARY(5, "Não-Binário");

    companion object {
        fun fromId(id: Int?): Gender? =
            entries.firstOrNull { it.id == id }
    }
}