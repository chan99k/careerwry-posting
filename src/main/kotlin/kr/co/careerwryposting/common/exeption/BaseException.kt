package kr.co.careerwryposting.common.exeption

import kr.co.careerwryposting.common.response.ErrorCode

open class BaseException : RuntimeException {
    var errorCode: ErrorCode? = null

    constructor(errorCode: ErrorCode) : super(errorCode.message) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode?) : super(message) {
        this.errorCode = errorCode
    }

    constructor(message: String?, errorCode: ErrorCode?, cause: Throwable?) : super(message, cause) {
        this.errorCode = errorCode
    }
}
