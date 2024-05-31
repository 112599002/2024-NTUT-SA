package ntut.csie.sslab.kanban.workflow.adapter.out.repository;

import ntut.csie.sslab.kanban.workflow.entity.Workflow1;
import ntut.csie.sslab.kanban.workflow.usecase.port.out.Workflow1Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryWorkflow1Repository implements Workflow1Repository {
    private List<Workflow1> stores;

    public InMemoryWorkflow1Repository() {
        this.stores = new ArrayList<>();
    }

    @Override
    public Optional<Workflow1> findById(String id) {
        return stores.stream().filter(workflow1 -> workflow1.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Workflow1 workflow) {
        stores.add(workflow);
    }

    @Override
    public void deleteById(String id) {
        throw new UnsupportedOperationException("Not Available Yet. M3?");
    }
}
