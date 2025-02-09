package kr.co.careerwryposting.domain.like

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.transaction.event.TransactionalEventListener

@Service
class LikeEventHandler(
    private val likeWriter: LikeWriter,
) {
    @Async
    @TransactionalEventListener(LikeCommand.CreateCommand::class)
    fun handleCreate(command: LikeCommand.CreateCommand) {
        Thread.sleep(1000)
        likeWriter.save(command)
    }

    @Async
    @TransactionalEventListener(LikeCommand.DeleteCommand::class)
    fun handleDelete(command: LikeCommand.DeleteCommand) {
        Thread.sleep(1000)
        likeWriter.delete(command)
    }
}
