package tw.teddysoft.clean.usecase.domainevent.handler;

import com.google.common.eventbus.Subscribe;
import tw.teddysoft.clean.domain.model.DomainEventBus;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.BoardCreated;
import tw.teddysoft.clean.domain.model.kanbanboard.board.event.WorkflowCommitted;
import tw.teddysoft.clean.domain.model.kanbanboard.workflow.Workflow;
import tw.teddysoft.clean.domain.model.kanbanboard.workspace.Workspace;
import tw.teddysoft.clean.domain.usecase.repository.Repository;


public class BoardEventHandler {

    private Repository<Workspace> workspaceRepository;
    private Repository<Workflow> workflowRepository;
    private DomainEventBus eventBus;

    public BoardEventHandler(Repository<Workspace> workspaceRepository,
                             Repository<Workflow> workflowRepository,
                             DomainEventBus eventBus){

        this.workspaceRepository = workspaceRepository;
        this.workflowRepository = workflowRepository;
        this.eventBus = eventBus;
    }

    @Subscribe
    public void handleEvent(BoardCreated domainEvent) {
        System.out.println("BoardEventHandler, event = " + domainEvent.detail());

        Workflow workflow = new Workflow("Default Workflow", domainEvent.getEntity().getId());
        workflowRepository.save(workflow);
        eventBus.postAll(workflow);


        Workspace workspace = workspaceRepository.findById(domainEvent.getEntity().getWorkspaceId());
        workspace.commitBoard(domainEvent.getEntity().getId());
        eventBus.postAll(workspace);
    }

    @Subscribe
    public void handleEvent(WorkflowCommitted domainEvent) {
        System.out.println("BoardEventHandler, event = " + domainEvent.detail());
    }

}
