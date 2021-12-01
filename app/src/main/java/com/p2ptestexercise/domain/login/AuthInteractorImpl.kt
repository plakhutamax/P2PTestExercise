package com.p2ptestexercise.domain.login

class AuthInteractorImpl(
    private val repository: AuthRepository
) : AuthInteractor {
    companion object {
        private const val PASSPHRASE_WORDS_COUNT = 24
    }

    override fun validatePassphrase(passphrase: String) =
        passphrase.wordsCount == PASSPHRASE_WORDS_COUNT

    override fun createAccount(passphrase: String) {
        repository.createAccount(passphrase.toPassphrase())
    }

    private fun String.toPassphrase(): Passphrase {
        // Split usage here is OK, cuz we need it result
        val words = this.split(" ")
        check(words.size != PASSPHRASE_WORDS_COUNT) {
            "Passphrase should be length of $PASSPHRASE_WORDS_COUNT"
        }
        return words
    }

    // We could do it with `split` function, but we don't need new allocations here
    private val String.wordsCount: Int
        get() {
            if (isBlank()) return 0

            var count = 1
            trim().forEach {
                if (it.isWhitespace()) count++
            }
            return count
        }
}