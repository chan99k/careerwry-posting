package kr.co.careerwryposting.infrastructure.file

import kr.co.careerwryposting.domain.file.File
import org.springframework.data.jpa.repository.JpaRepository

interface FileRepository : JpaRepository<File, Long>