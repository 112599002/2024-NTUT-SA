package ntut.csie.sslab.kanban.workflow.usecase;

import com.google.common.eventbus.Subscribe;
import ntut.csie.sslab.ddd.adapter.gateway.GoogleEventBusAdapter;
import ntut.csie.sslab.ddd.model.DomainEvent;
import ntut.csie.sslab.ddd.usecase.DomainEventBus;
import ntut.csie.sslab.ddd.usecase.cqrs.CqrsOutput;
import ntut.csie.sslab.ddd.usecase.cqrs.ExitCode;
import ntut.csie.sslab.kanban.workflow.adapter.out.repository.InMemoryWorkflow1Repository;
import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1Input;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;
import ntut.csie.sslab.kanban.workflow.usecase.service.CreateWorkflow1Service;
import ntut.csie.sslab.kanban.workflow.usecase.port.in.create.CreateWorkflow1UseCase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static org.junit.jupiter.api.Assertions.*;

public class CreateWorkflow1UseCaseTest {

    @Test
    public void test_create_workflow1_use_case() {
        DomainEventBus domainEventBus = new GoogleEventBusAdapter();
        FakeListener listener = new FakeListener();
        domainEventBus.register(listener);
        Workflow1Repository workflow1Repository = new InMemoryWorkflow1Repository();
        CreateWorkflow1UseCase createWorkflow1UseCase = new CreateWorkflow1Service(workflow1Repository, domainEventBus);

        CreateWorkflow1Input input = new CreateWorkflow1Input();
        input.workflowId = "001";
        input.workflowName = "Scrum Workflow";
        input.boardId = "001";

        CqrsOutput output = createWorkflow1UseCase.execute(input);

        assertEquals(ExitCode.SUCCESS, output.getExitCode());
        assertEquals("001" ,output.getId());

        assertTrue(workflow1Repository.findById("001").isPresent());

        assertEquals(1, listener.getEventCount());
        assertEquals(1, listener.getWorkflow1CreatedEvents().size());
        assertEquals("001", listener.getWorkflow1CreatedEvents().get(0).getWorkflowId());
    }

    public static class FakeListener {
        private int workflow1_created;
        List<Workflow1Created> workflow1CreatedEvents;

        public FakeListener() {
            this.workflow1_created = 0;
            this.workflow1CreatedEvents = new ArrayList<>();
        }

        @Subscribe
        public void whenWorkflow1Created(DomainEvent domainEvent) {
            workflow1_created++;
            if((Workflow1Created)domainEvent != null) {
                workflow1CreatedEvents.add((Workflow1Created)domainEvent);
            }
        }

        public int getEventCount() {
            return workflow1_created;
        }

        public List<Workflow1Created> getWorkflow1CreatedEvents() {
            return unmodifiableList(workflow1CreatedEvents);
        }
    }
}
