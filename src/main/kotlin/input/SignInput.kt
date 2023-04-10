package input

import models.Sign

/**
 * Request sign from input.
 */
interface SignInput {

    /**
     * Request sign.
     * @return Return valid sign or null.
     */
    fun requestSign(): Sign?
}