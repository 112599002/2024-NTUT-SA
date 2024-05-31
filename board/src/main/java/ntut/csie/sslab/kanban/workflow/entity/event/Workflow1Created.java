package ntut.csie.sslab.kanban.workflow.entity.event;

import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.model.common.DateProvider;

import java.util.Date;

public class Workflow1Created extends DomainEvent {
    private final String workflowId;
    private final String workflowName;
    private final String boardId;

    public Workflow1Created(String workflowId, String workflowName, String boardId) {
        super(DateProvider.now());
        this.workflowId = workflowId;
        this.workflowName = workflowName;
        this.boardId = boardId;
    }

    public String getWorkflowId() {
        return workflowId;
    }

    public String getWorkflowName() {
        return workflowName;
    }

    public String getBoardId() {
        return boardId;
    }
}
