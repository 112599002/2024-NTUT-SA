package ntut.csie.sslab.kanban.board.usecase.service;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.in.notify.NotifyBoard1;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

public class NotifyBoard1Service implements NotifyBoard1 {
    private final BoardRepository boardRepository;
    private final DomainEventBus domainEventBus;

    public NotifyBoard1Service(BoardRepository boardRepository,
                              DomainEventBus domainEventBus) {
        this.boardRepository = boardRepository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public void whenWorkflow1Created(Workflow1Created workflow1Created) {
        Board board = boardRepository.findById(workflow1Created.getBoardId()).get();
        board.commitWorkflow1(workflow1Created.getWorkflowId());
        boardRepository.save(board);
        domainEventBus.postAll(board);
    }

}
