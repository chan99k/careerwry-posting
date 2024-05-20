package kr.co.careerwryposting.common.exeption

import kr.co.careerwryposting.common.response.ErrorCode

class InvalidInputException : BaseException {
    constructor() : super(ErrorCode.COMMON_INVALID_PARAMETER)

    constructor(errorCode: ErrorCode?) : super(errorCode!!)

    constructor(errorMsg: String?) : super(errorMsg, ErrorCode.COMMON_INVALID_PARAMETER)

    constructor(message: String?, errorCode: ErrorCode?) : super(message, errorCode)
}
