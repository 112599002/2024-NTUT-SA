package ntut.csie.sslab.kanban.workflow.usecase.service;

import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1UseCase;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;

public class CreateWorkflow1Service implements CreateWorkflow1UseCase {
    private final Workflow1Repository workflow1Repository;
    private final DomainEventBus domainEventBus;
    public CreateWorkflow1Service(Workflow1Repository workflow1Repository, DomainEventBus domainEventBus) {
        this.workflow1Repository = workflow1Repository;
        this.domainEventBus = domainEventBus;
    }

    @Override
    public CqrsOutput execute(CreateWorkflow1Input input) {
        Workflow1 workflow = new Workflow1(input.workflowId, input.workflowName, input.boardId);
        workflow1Repository.save(workflow);
        domainEventBus.postAll(workflow);

        CqrsOutput output = CqrsOutput.create();
        output.setId(workflow.getId());
        output.setExitCode(ExitCode.SUCCESS);
        return output;
    }
}
