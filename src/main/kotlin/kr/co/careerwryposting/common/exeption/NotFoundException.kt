package kr.co.careerwryposting.common.exeption

import kr.co.careerwryposting.common.response.ErrorCode

class NotFoundException : BaseException {
    constructor() : super(ErrorCode.POST_NOT_FOUND)

    constructor(errorCode: ErrorCode?) : super(errorCode!!)

    constructor(errorMsg: String?) : super(errorMsg, ErrorCode.POST_NOT_FOUND)

    constructor(message: String?, errorCode: ErrorCode?) : super(message, errorCode)
}
