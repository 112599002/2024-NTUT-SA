package ntut.csie.sslab.kanban.board.usecase;

import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.board.adapter.in.eventbus.NotifyBoard1GoogleEventBusAdapter;
import ntut.csie.sslab.kanban.board.adapter.out.repository.InMemoryBoardRepository;
import ntut.csie.sslab.kanban.board.entity.Board;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardInput;
import ntut.csie.sslab.kanban.board.usecase.port.in.create.CreateBoardUseCase;
import ntut.csie.sslab.kanban.board.usecase.port.in.notify.NotifyBoard1;
import ntut.csie.sslab.kanban.board.usecase.port.out.BoardRepository;
import ntut.csie.sslab.kanban.board.usecase.service.CreateBoardService;
import ntut.csie.sslab.kanban.board.usecase.service.NotifyBoard1Service;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.InMemoryWorkflow1Repository;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1UseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflow1Service;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NotifyBoard1Test {
    private BoardRepository boardRepository;
    private Workflow1Repository workflow1Repository;
    private DomainEventBus domainEventBus;

    @Test
    public void create_a_workflow1_commits_itself_to_its_board() {
        boardRepository = new InMemoryBoardRepository();
        domainEventBus = new GoogleEventBusAdapter();
        workflow1Repository = new InMemoryWorkflow1Repository();

        NotifyBoard1 notifyBoard1 = new NotifyBoard1Service(boardRepository, domainEventBus);
        NotifyBoard1GoogleEventBusAdapter notifyBoard1GoogleEventBusAdapter = new NotifyBoard1GoogleEventBusAdapter(notifyBoard1);
        domainEventBus.register(notifyBoard1GoogleEventBusAdapter);

        String boardId = create_board();
        String workflow1Id = create_workflow1(boardId);

        assertTrue(boardRepository.findById(boardId).isPresent());
        Board board = boardRepository.findById(boardId).get();

        assertEquals(1, board.getCommittedWorkflows().size());
        assertTrue(board.getCommittedWorkflow(workflow1Id).isPresent());
    }

    private String create_board() {
        CreateBoardUseCase createBoardUseCase = new CreateBoardService(boardRepository, domainEventBus);
        CreateBoardInput input = new CreateBoardInput();
        input.setTeamId("teamId");
        input.setName("boardName");
        input.setUserId("userId");

        CqrsOutput output = createBoardUseCase.execute(input);

        return output.getId();
    }

    private String create_workflow1(String boardId) {
        CreateWorkflow1UseCase createWorkflow1UseCase = new CreateWorkflow1Service(workflow1Repository, domainEventBus);

        CreateWorkflow1Input input = new CreateWorkflow1Input();
        input.workflowId = "001";
        input.workflowName = "Scrum Workflow";
        input.boardId = boardId;

        CqrsOutput output = createWorkflow1UseCase.execute(input);

        return output.getId();
    }
}
