package ntut.csie.sslab.kanban.workflow.entity;

import ntut.csie.sslab.ddd.model.AggregateRoot;
import ntut.csie.sslab.kanban.workflow.entity.event.Workflow1Created;

public class Workflow1 extends AggregateRoot<String> {
    private String name;
    private final String boardId;

    public Workflow1(String id, String name, String boardId) {
        super(id);
        this.name = name;
        this.boardId = boardId;
        addDomainEvent(new Workflow1Created(id, name, boardId));
    }

    public String getName() {
        return name;
    }

    public String getBoardId() {
        return boardId;
    }
}
