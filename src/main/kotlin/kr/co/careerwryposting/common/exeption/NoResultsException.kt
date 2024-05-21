package kr.co.careerwryposting.common.exeption

import kr.co.careerwryposting.common.response.ErrorCode

class NoResultsException : BaseException {
    constructor() : super(ErrorCode.POST_NO_RESULTS)

    constructor(errorCode: ErrorCode?) : super(errorCode!!)

    constructor(errorMsg: String?) : super(errorMsg, ErrorCode.POST_NO_RESULTS)

    constructor(message: String?, errorCode: ErrorCode?) : super(message, errorCode)
}