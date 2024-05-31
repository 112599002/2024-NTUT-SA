package ntut.csie.sslab.kanban.workflow.usecase.port.in.create;

import ntut.csie.sslab.ddd.usecase.UseCase;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;

public interface CreateWorkflow1UseCase extends UseCase<CreateWorkflow1Input, CqrsOutput> {
    public CqrsOutput execute(CreateWorkflow1Input input);
}
