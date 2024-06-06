package ntut.csie.sslab.kanban.board.usecase.port.in.notify;

import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

public interface NotifyBoard1 {
    void whenWorkflow1Created(Workflow1Created workflow1Created);
}
