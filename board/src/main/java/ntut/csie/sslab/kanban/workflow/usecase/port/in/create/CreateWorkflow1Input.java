package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;

import ntut.csie.sslab.ddd.usecase.Input;

public class CreateWorkflow1Input implements Input {
    public String workflowId;
    public String workflowName;
    public String boardId;
}